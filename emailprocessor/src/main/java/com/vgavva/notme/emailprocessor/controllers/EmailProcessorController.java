package com.vgavva.notme.emailprocessor.controllers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vgavva.notme.emailprocessor.entity.EmailQ;
import com.vgavva.notme.emailprocessor.repository.EmailQRepository;


@RestController
public class EmailProcessorController {
	Logger logger = LoggerFactory.getLogger(EmailProcessorController.class);
	
	@Autowired
    private EmailQRepository notfnQRepo;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/emailStats/from/{from}/to/{to}")
	public Map<String,Integer> getEmailStats(@PathVariable String from, @PathVariable String to){
	    
		Map<String,Integer> emailStatsMap = new HashMap<>();
		
		List<EmailQ> emailList = notfnQRepo.findAll();
		Map<String, List<EmailQ>> emailsGrouped = emailList.stream()
												 .collect(Collectors.groupingBy(EmailQ::getStatus,Collectors.toList()));
		
		int numOfPendingEmails = (null == emailsGrouped.get("C") || emailsGrouped.get("C").isEmpty())? 0: emailsGrouped.get("C").size();
		int numOfInProcessEmails = (null == emailsGrouped.get("C") || emailsGrouped.get("P").isEmpty())? 0: emailsGrouped.get("P").size();
		int numOfFailedEmails = (null == emailsGrouped.get("C") || emailsGrouped.get("F").isEmpty())? 0: emailsGrouped.get("F").size();
		
		emailStatsMap.put("totalEmailNotifications", emailList.size());
		emailStatsMap.put("pendingEmailNotifications", numOfPendingEmails);
		emailStatsMap.put("InProcessEmailNotifications", numOfInProcessEmails);
		emailStatsMap.put("FailedEmailNotifications", numOfFailedEmails);
		emailStatsMap.put("port",Integer.valueOf(environment.getProperty("local.server.port")));
			    
	    return emailStatsMap;
	  }
}
