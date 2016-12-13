package com.techholution.knapsack;

import org.springframework.stereotype.Component;

/**
 * <p>
 * 0-1 KnapSack algorithm
 * </p>
 * 
 * <p>
 * Given a set of items, each with a weight and a value, determine the number of each item to include in a collection so
 * that the total weight is less than or equal to a given limit and the total value is as large as possible. It derives
 * its name from the problem faced by someone who is constrained by a fixed-size knapsack and must fill it with the most
 * valuable items. Informally, the problem is to maximize the sum of the values of the items in the knapsack so that the
 * sum of the weights is less than or equal to the knapsack's capacity.
 * </p>
 * 
 * <p>
 * Running time of this algorithm is O(n*W)
 * </p>
 * 
 * @author GULAPALLY
 *
 */
@Component
public class KnapSack {

	public int knapSack(int[] weights, int[] values, int maxWeight) {
		int[][] sack = new int[weights.length][maxWeight + 1];
		sack[0][0] = 0;
		// first populate the zeroth row of the grid
		for (int index = 1; index < sack[0].length; index++) {
			if (weights[0] <= index) {
				sack[0][index] = values[0];
			} else {
				sack[0][index] = 0;
			}
		}

		for (int index = 1; index < sack.length; index++) {
			for (int j = 1; j < sack[index].length; j++) {
				// if a weight is more than the allowed weight, that weight cannot be picked.
				if (weights[index] > j) {
					sack[index][j] = sack[index - 1][j];
				} else {
					sack[index][j] = Math.max(sack[index - 1][j], values[index] + sack[index - 1][j - weights[index]]);
				}
			}
		}

		// return last item in the table
		return sack[sack.length - 1][maxWeight];
	}

}
