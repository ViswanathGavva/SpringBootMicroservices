package com.vgavva.notme.emailprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EmailprocessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailprocessorApplication.class, args);
	}

}
