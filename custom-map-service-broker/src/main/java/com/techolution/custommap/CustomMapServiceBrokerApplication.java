package com.techolution.custommap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomMapServiceBrokerApplication {
	
	@Bean
	public Cloud cloud() {
	  return new CloudFactory().getCloud();
	}


	public static void main(String[] args) {
		SpringApplication.run(CustomMapServiceBrokerApplication.class, args);
	}
}
