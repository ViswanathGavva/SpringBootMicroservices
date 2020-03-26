package com.vgavva.notme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.vgavva.*")
public class NotmeAuthserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotmeAuthserverApplication.class, args);
	}

}
