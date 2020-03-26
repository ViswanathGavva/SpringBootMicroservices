package com.vgavva.notme.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vgavva.notme.entity.NotificationQ;

public class EmailMoverTaskFJ extends RecursiveTask<Integer> {
    private static final long serialVersionUID = 1L;
Logger logger = LoggerFactory.getLogger(EmailMoverTask.class);
	
	private List<NotificationQ> notfnBatch;
	
	
	public EmailMoverTaskFJ(List<NotificationQ> notfnBatch) {
        this.notfnBatch = notfnBatch;        
    }
	
	@Override
    protected Integer compute() {
		if(this.notfnBatch.size()>1000) {
			List<EmailMoverTaskFJ> subtasks = createSubtasks();
			return ForkJoinTask.invokeAll(subtasks)
		              .stream()
		              .mapToInt(ForkJoinTask::join)
		              .sum();
		}else {
			return processing(this.notfnBatch);
		}						    	    	
    }
	
	private List<EmailMoverTaskFJ> createSubtasks() {
		int batchSize = 1000;
		
		List<EmailMoverTaskFJ> subtasks = new ArrayList<>();	
		int endIndex = Math.min(batchSize, this.notfnBatch.size());
		
		subtasks.add(new EmailMoverTaskFJ(this.notfnBatch.subList(0,this.notfnBatch.size()/2)));
		subtasks.add(new EmailMoverTaskFJ(this.notfnBatch.subList((this.notfnBatch.size()/2),this.notfnBatch.size())));
		
		/*
		subtasks.add(new EmailMoverTaskFJ(this.notfnBatch.subList(0,endIndex)));
		subtasks.add(new EmailMoverTaskFJ(this.notfnBatch.subList(endIndex+1,this.notfnBatch.size())));
		*/
		/*for(int i=0;i<this.notfnBatch.size();i=i+batchSize) {
			subtasks.add(new EmailMoverTaskFJ(this.notfnBatch.subList(i,Math.min(i+batchSize, this.notfnBatch.size()))));
		}*/
        return subtasks;
    }
	
	private Integer processing(List<NotificationQ> notfnBatch) {
		try {
			TimeUnit.MILLISECONDS.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	logger.info("This result was processed by "+ Thread.currentThread().getName());
    	return this.notfnBatch.size();
    }

}
