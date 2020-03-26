
package com.vgavva.notme.proxy;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="email-service")
public interface EmailProcessorProxy {
	@GetMapping("/emailStats/from/{from}/to/{to}")
	public Map<String,Integer> getEmailStats
	(@PathVariable("from") String fromDate, @PathVariable("to") String toDate);
}


