package com.techholution;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import com.techholution.service.SatisfactionService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DishSatisfactionApplicationTests {

	@Autowired
	private SatisfactionService satisfactionService;
	
	@Value("${input-data}")
	public Resource inputFile;

	@Test
	public void shouldFindMaxiumSatisfactionValue() throws Exception {
		int max = satisfactionService.findMaxValue(inputFile.getFile());
		assertEquals(2493893, max);
	}

}
