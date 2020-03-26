package com.vgavva.notme.services;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.vgavva.notme.entity.NotificationQ;

@Service
public class EmailMoverTask implements Callable<Integer>{	
	Logger logger = LoggerFactory.getLogger(EmailMoverTask.class);
	
	private List<NotificationQ> notfnBatch;
	
    public EmailMoverTask(List<NotificationQ> notfnBatch) {
        this.notfnBatch = notfnBatch;
    }

    @Override
    public Integer call() throws Exception {
    	//convert the batch of notifications to batch of emails    	
    	//Convert from notification to Email
    	//Insert into emailQ using jdbc batch update
    	TimeUnit.MILLISECONDS.sleep(300);
    	logger.info("This result was processed by "+ Thread.currentThread().getName());
    	return this.notfnBatch.size();
    	//return 0;
    }
}
