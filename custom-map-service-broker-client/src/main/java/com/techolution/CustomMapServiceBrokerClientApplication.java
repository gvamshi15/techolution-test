package com.techolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomMapServiceBrokerClientApplication {

	//private final static Logger LOG = LoggerFactory.getLogger(CustomMapServiceBrokerClientApplication.class);
	
	@Bean
	public Cloud cloud() {
	  return new CloudFactory().getCloud();
	}

	public static void main(String[] args) {
		SpringApplication.run(CustomMapServiceBrokerClientApplication.class, args);
	}
}
