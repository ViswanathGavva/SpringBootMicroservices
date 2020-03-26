package com.vgavva.notme.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.jdi.LongValue;
import com.vgavva.notme.entity.NotificationQ;
import com.vgavva.notme.repository.NotificationQRepository;

@Service
public class EmailProcessor {
	Logger logger = LoggerFactory.getLogger(EmailProcessor.class);
	
	@Autowired
    private NotificationQRepository notfnQRepo;
	
	public int moveEmailsToQ() {
		List<NotificationQ> allNotifications = notfnQRepo.findAll();
		int batchSize = 1000;		
		ExecutorService executor = Executors.newFixedThreadPool(120);
		List<Callable<Integer>> callables = new ArrayList<>();	
		int numOfEmailsMoved = 0;
		for(int i=0;i<allNotifications.size();i=i+batchSize) {
			callables.add(new EmailMoverTask(allNotifications.subList(i, Math.min(i+batchSize, allNotifications.size()))));
		}
		try {
			List<Future<Integer>> resultFutureList = executor.invokeAll(callables);
			for (Future<Integer> future : resultFutureList) {								
				numOfEmailsMoved += future.get();
			} 
		} catch (InterruptedException | ExecutionException e) {			
			e.printStackTrace();
		}
		
		executor.shutdown();
		logger.info("is Executor Shutdown1? "+ executor.isShutdown());
		logger.info("is Executor Terminated? "+ executor.isTerminated());
		try {
		    if (!executor.awaitTermination(1200, TimeUnit.MILLISECONDS)) {
		    	executor.shutdownNow();
		    	logger.info("is Executor Shutdown2? "+ executor.isShutdown());
		    } 
		} catch (InterruptedException e) {
			executor.shutdownNow();
		}
		logger.info("is Executor Shutdown3? "+ executor.isShutdown());
		return numOfEmailsMoved;
	}
	
	public int moveEmailsToQFJ() {
		int numOfEmailsMoved = 0;
		List<NotificationQ> allNotifications = notfnQRepo.findAll();			
		int numOfCores = Runtime.getRuntime().availableProcessors();
		System.out.println("Num OF Cores");
		System.out.println(numOfCores);
        ForkJoinPool pool = new ForkJoinPool(120);
        numOfEmailsMoved = pool.invoke(new EmailMoverTaskFJ(allNotifications));
        		
		return numOfEmailsMoved;
	}
	
	public int moveEmailsToQPS() {
		int numOfEmailsMoved = 0;
		List<NotificationQ> allNotifications = notfnQRepo.findAll();			
		numOfEmailsMoved = allNotifications.stream().parallel()
			.filter(n->n.getId()%1000 ==0 || n.getId().equals(new Long(1)))
			.mapToInt(from -> {
				int res=0;
				try {
					logger.info("fromId:"+from.getId().intValue()+"::toId:"+Math.min(from.getId().intValue()+1000,allNotifications.size()));
					res= process(allNotifications.subList(from.getId().intValue(),  Math.min(from.getId().intValue()+1000,allNotifications.size())));
				} catch (IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return res;
			})
			.sum();
        		
		return numOfEmailsMoved;
	}
	
	public Integer process(List<NotificationQ> notfnBatch) throws Exception {
    	//convert the batch of notifications to batch of emails    	
    	//Convert from notification to Email
    	//Insert into emailQ using jdbc batch update
    	TimeUnit.MILLISECONDS.sleep(300);
    	logger.info("This result was processed by "+ Thread.currentThread().getName());
    	return notfnBatch.size();
    	//return 0;
    }
	
	
}
