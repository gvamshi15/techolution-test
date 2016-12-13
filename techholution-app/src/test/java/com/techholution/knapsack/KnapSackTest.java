package com.techholution.knapsack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.techholution.knapsack.KnapSack;

public class KnapSackTest {

	private Integer weights[];

	private Integer values[];


	private KnapSack knapSack;

	@Before
	public void setup() {
		weights = new Integer[] { 10, 20, 30 };
		values = new Integer[] { 60, 100, 120 };
		knapSack = new KnapSack();
	}

	@Test
	public void shouldFindMaxValueWhenWeightIsBelowLeastWeight() {
		assertEquals(0, knapSack.knapSack(weights, values, 9));
	}

	@Test
	public void shouldFindMaxValueUsingKnapsackAlgorithm() {
		assertEquals(220, knapSack.knapSack(weights, values, 50));
	}

	@Test
	public void shouldFindMaxValueWhenValuesAndWeightsMaxWeightIsAboveMaximumWeight() {
		assertEquals(280, knapSack.knapSack(weights, values, 70));
	}

}
