/**
 * 
 */
package com.techholution.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techholution.domain.ItemPojo;
import com.techholution.knapsack.KnapSack;


/**
 * @author GULAPALLY
 *
 */
@Service
public class SatisfactionServiceImpl implements SatisfactionService {
	
	private final static Logger LOG = LoggerFactory.getLogger(SatisfactionServiceImpl.class);
	
	@Autowired
	private KnapSack knapsack;

	/* (non-Javadoc)
	 * @see com.techholution.knapsack.KnapSackService#findMaxValue(java.io.File)
	 */
	@Override
	public int findMaxValue(File file) throws IOException {
		//read and parse the file
		List<ItemPojo> items = processFile(file);
		
		//read the first line which contains input time and total menu items
		int maxTime = items.get(0).getSatisfaction();
		
		//skipping the first item from the list
		List<ItemPojo> itemsSublist = items.subList(1, items.size());
		
		//read list of values from sublist
		int[] values = itemsSublist.stream().mapToInt(item -> item.getSatisfaction()).toArray();
		
		//read list of weights from sublist
		int[] weights = itemsSublist.stream().mapToInt(item -> item.getTimeTaken()).toArray();
		
		//get max value from KnapSack alogorithm
		return knapsack.knapSack(weights, values, maxTime);
	}
	
	private List<ItemPojo> processFile(File inputFile) throws IOException {
		//read all lines from the file and convert them into list of item pojo
		List<String> lines = readFromFile(inputFile);
		
		List<ItemPojo> items = new ArrayList<>();
		
		//split each line by delimiter and create item pojo and add them into list
		lines.stream().forEach(line -> { 
			String[] tokens = line.split("\\s+");
			ItemPojo item = new ItemPojo(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
			items.add(item);
		});
		
		return items;
	}
	
	private static List<String> readFromFile(File inputFile) throws IOException {
		List<String> lines = new ArrayList<>();
		try (Scanner fileScanner = new Scanner(new FileInputStream(inputFile))) {
			while (fileScanner.hasNextLine()) {
				lines.add(fileScanner.nextLine());
			}
		}
		LOG.debug("Lines : {}", lines);
		return lines;
	}

}
