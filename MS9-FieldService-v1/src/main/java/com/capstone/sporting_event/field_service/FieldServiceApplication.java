package com.capstone.sporting_event.field_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FieldServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FieldServiceApplication.class, args);
	}

}
