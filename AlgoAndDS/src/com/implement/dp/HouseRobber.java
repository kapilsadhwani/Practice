package com.implement.dp;

import java.util.Arrays;

public class HouseRobber {
	public static int robMemo(int[] nums, int pos, int[] cache) {
		if(pos <= 0) return 0;
		
		if(cache[pos] != -1) return cache[pos];
		
		int maxProfit = 0;
		
		maxProfit = Math.max(nums[pos-1] + robMemo(nums, pos-2, cache),
				robMemo(nums, pos-1, cache));
		
		cache[pos] = maxProfit;
		
		return maxProfit;
	}

	public static int robR(int[] nums) {
		int n = nums.length;
		int[] cache = new int[n + 1];
		
		Arrays.fill(cache, -1);
		
		cache[0] = 0;
		
		return robMemo(nums, n, cache);
	}
	
	private static int robGapStrategy(int[] nums) {
		int n = nums.length;
		int[][] dp = new int[n][n];

		for (int g = 0; g < dp.length; g++) {
			// Traverse diagonally with loop ending in last column
			for (int i = 0, j = g; j < dp.length; i++, j++) {
				// Trivial case: Single element
				if (g == 0) {
					dp[i][j] = nums[i];
				}
				// Trivial case: 2 elements
				else if (g == 1) {
					dp[i][j] = Math.max(nums[i], nums[j]);
				} else {
					int val1 = nums[i]
							+ Math.min(dp[i + 2][j], dp[i + 1][j - 1]);
					int val2 = nums[j]
							+ Math.min(dp[i + 1][j - 1], dp[i][j - 2]);

					dp[i][j] = Math.max(val1, val2);
				}
			}
		}

		return dp[0][n - 1];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1, 2, 3, 1 };
		
		System.out.println(robR(nums));
		System.out.println(robGapStrategy(nums));

		nums = new int[] { 2, 7, 9, 3, 1 };
		
		System.out.println(robR(nums));
		System.out.println(robGapStrategy(nums));
	}

}
