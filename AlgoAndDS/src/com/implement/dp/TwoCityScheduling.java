package com.implement.dp;

import java.util.Arrays;
import java.util.Comparator;

public class TwoCityScheduling {
	private static int twoCitySchedCostMemo(int[][] costs, int pos, int N, 
						int countA, int countB, int[][][] cache) {
		if (pos == costs.length)
			return 0;
		
		if(cache[pos][countA][countB] != -1) return cache[pos][countA][countB];

		int minCost = Integer.MAX_VALUE;

		if (countA < N) {
			minCost = Math.min(
					minCost,
					costs[pos][0]
							+ twoCitySchedCostMemo(costs, pos + 1, N, countA + 1, countB, cache));
		}

		if (countB < N) {
			minCost = Math.min(
					minCost,
					costs[pos][1]
							+ twoCitySchedCostMemo(costs, pos + 1, N, countA, countB + 1, cache));
		}

		cache[pos][countA][countB] = minCost;
		return minCost;
	}

	public static int twoCitySchedCostDP(int[][] costs) {
		int n = costs.length;

		// If input is empty
		if (costs == null || costs.length == 0 || costs[0].length == 0)
			return -1;

		// If costs.length is odd
		if (costs.length % 2 == 1)
			return -1;

		// key = pos_countA_countB
		int[][][] cache = new int[n + 1][n/2 + 1][n/2 + 1];
		
		for (int i = 0; i < cache.length; i++)
			for (int j = 0; j < cache[0].length; j++)
				Arrays.fill(cache[i][j], -1);
				
		return twoCitySchedCostMemo(costs, 0, n / 2, 0, 0, cache);
	}

	public static int twoCitySchedCost(int[][] costs) {
		int n = costs.length/2;
		int minCost = 0;
		
		Arrays.sort(costs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o1[1] - (o2[0] - o2[1]);
			}
		});

		/*Arrays.sort(costs, (a, b) -> {
			return (a[0] - a[1]) - (b[0] - b[1]);
		});*/

		for (int i = 0; i < n; i++) {
			minCost = minCost + costs[i][0] + costs[i + n][1];
		}

		return minCost;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int costs[][] = { 
				{ 259, 770 }, 
				{ 448, 54 }, 
				{ 926, 667 }, 
				{ 184, 139 }, 
				{ 840, 118 }, 
				{ 577, 469 } };
		
		System.out.println("Total minimum costs (DP): " + twoCitySchedCostDP(costs));
		System.out.println("Total minimum costs (BS): " + twoCitySchedCost(costs));
	}

}
