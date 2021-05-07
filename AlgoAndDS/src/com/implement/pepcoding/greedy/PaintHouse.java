package com.implement.pepcoding.greedy;

/*
 * Paint House - 3 Colors
 * 
 * Given 3 space separated numbers representing the cost of painting nth house with 
 * red or blue or green color.
 * Calculate the minimum cost of painting all houses without painting any consecutive 
 * house with same color.
 */
public class PaintHouse {
	public static long minCost(int[][] nums) {
		int n = nums.length;
		int m = nums[0].length;
		
		long[][] dp = new long[n][m];
		
		// Initialize with Red value of Color 1
		dp[0][0] = nums[0][0];
		// Initialize with Green value of Color 1
		dp[0][1] = nums[0][1];
		// Initialize with Blue value of Color 1
		dp[0][2] = nums[0][2];

		for (int i = 1; i < n; i++) {
			// If we apply red paint on ith house
			dp[i][0] = nums[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
			
			// If we apply green paint on ith house
			dp[i][1] = nums[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
			
			// If we apply blue paint on ith house
			dp[i][2] = nums[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
		}

		
		long result = Math.min(dp[n - 1][0],
				Math.min(dp[n - 1][1], dp[n - 1][2]));
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] nums = {{1, 5, 7},
						{5, 8, 4},
						{3, 2, 9},
						{1, 2, 4}};

		System.out.println(minCost(nums));
	}

}
