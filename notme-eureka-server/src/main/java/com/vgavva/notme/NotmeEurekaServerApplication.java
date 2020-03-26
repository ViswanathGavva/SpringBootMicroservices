package com.vgavva.notme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NotmeEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotmeEurekaServerApplication.class, args);
	}

}
