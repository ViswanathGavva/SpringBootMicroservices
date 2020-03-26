package com.vgavva.notme.controllers;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vgavva.notme.NotficationStats;
import com.vgavva.notme.entity.NotificationQ;
import com.vgavva.notme.proxy.EmailProcessorProxy;
//import com.vgavva.notme.proxy.EmailProcessorProxy;
import com.vgavva.notme.repository.NotificationQRepository;
import com.vgavva.notme.services.EmailProcessor;

@RestController
//@RequestMapping("/api")
public class NotMeController {
	Logger logger = LoggerFactory.getLogger(NotMeController.class);
	
	@Autowired
    private NotificationQRepository notfnQRepo;
	
	@Autowired
	private EmailProcessor emailProcessor;
	
	@Autowired
	private EmailProcessorProxy emailProcessorProxy;

    @GetMapping("/notfnInQ")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public Page<NotificationQ> getQugetNotificationsInQ(Pageable pageable) {    	
        return notfnQRepo.findAll(pageable);
    }
    
    @GetMapping("/moveEmailstoQ")
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public String moveEmails() { 
    	StopWatch fjWatch = new StopWatch("CrunchifyThreads");
    	fjWatch.start("Email Mover using Executor Service");
    	int result = emailProcessor.moveEmailsToQ();
    	fjWatch.stop();
    	logger.info("stopwatch"+fjWatch.prettyPrint());
    	return Integer.toString(result)+"-->"+fjWatch.getTotalTimeMillis();
    	
    }
    
    @GetMapping("/moveEmailstoQFJ")
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public String moveEmailsFJ() { 
    	StopWatch fjWatch = new StopWatch("CrunchifyThreads");
    	
    	fjWatch.start("Email Mover using Fork Join");
    	int result = emailProcessor.moveEmailsToQFJ();
    	fjWatch.stop();
    	
    	logger.info("stopwatch"+fjWatch.prettyPrint());
		return Integer.toString(result)+"-->"+fjWatch.getTotalTimeMillis();
    	
    }
    
    @GetMapping("/moveEmailstoQPS")
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public String moveEmailsPS() { 
    	StopWatch fjWatch = new StopWatch("CrunchifyThreads");
    	
    	fjWatch.start("Email Mover using Fork Join");
    	int result = emailProcessor.moveEmailsToQPS();
    	fjWatch.stop();
    	
    	logger.info("stopwatch"+fjWatch.prettyPrint());
		return Integer.toString(result)+"-->"+fjWatch.getTotalTimeMillis();
    	
    }
    
    
    @GetMapping("/notificationStats/from/{fromDate}/to/{toDate}")
    @PreAuthorize("#oauth2.hasScope('read')")
    public NotficationStats convertCurrencyFeign(@PathVariable String fromDate, @PathVariable String toDate) {
    	
    	List<NotificationQ> notfnList = notfnQRepo.findAll();
    	Map<String,Integer> response = emailProcessorProxy.getEmailStats(fromDate, toDate);
        logger.info("{}", response);

        return new NotficationStats(
        		notfnList.size(), 
        		response.get("totalEmailNotifications"), 
        		0, 
        		response.get("pendingEmailNotifications"), 
        		0,
        		0,
        		response.get("port"));
        /*
    	return new NotficationStats(
        		notfnList.size(), 
        		0, 
        		0, 
        		0, 
        		0,
        		0,
        		0);
        */
    }
    
    
	@GetMapping("/hello")
	@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public String handle() {
        return "Hello WebFlux";
    }
	
	

}
