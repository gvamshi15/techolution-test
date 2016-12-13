package com.techholution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;

import com.techholution.service.SatisfactionService;


@SpringBootApplication
@ComponentScan(basePackages = { "com.techholution" })
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class DishSatisfactionApplication implements CommandLineRunner {

	private final static Logger LOG = LoggerFactory.getLogger(DishSatisfactionApplication.class);

	@Autowired
	private SatisfactionService satisfactionService;

	@Value("${input-data}")
	public Resource inputFile;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DishSatisfactionApplication.class);
		System.exit(SpringApplication.exit(app.run(args)));
	}

	@Override
	public void run(String... arg0) throws Exception {

		// get max value from KnapSack alogorithm
		int max = satisfactionService.findMaxValue(inputFile.getFile());
		LOG.info("Max amount of Satisfaction :{}", max);
	}
}
