package com.implement.pepcoding.dp;

import java.util.HashMap;
import java.util.Map;


/* Dynamic Programming Java implementation of Highway Billboard problem */

class HighwayBillboard {
	/*
	 * This solution depends on number of boards [Complexity: O(n ^ 2)]
	 */
	static int maxRevenueBasedOnBoards(int m, int[] nums, int[] rev, int t) {
		int[] dp = new int[nums.length];

		dp[0] = rev[0];
		int ans = 0;

		for (int i = 1; i < nums.length; i++) {
			int max = 0;

			for (int j = 0; j < i; j++) {
				if (nums[i] - nums[j] > t) {
					max = Math.max(max, dp[j]);
				}
			}

			dp[i] = max + rev[i];
			ans = Math.max(ans, dp[i]);
		}

		return ans;
	}
	
	/*
	 * This solution depends on miles [Complexity: O(m)]
	 */
	static int maxRevenueBasedOnMiles(int m, int[] nums, int[] rev, int t) {
		Map<Integer, Integer> map = new HashMap<>();
		
		for(int i = 0; i < nums.length; i++){
			map.put(nums[i], rev[i]);
		}

		int[] dp = new int[m + 1];
		dp[0] = 0;
		
		for (int i = 1; i <= m; i++) {
			if (map.containsKey(i)){
				int exclude = dp[i - 1];
				int include = map.get(i);
				
				if(i > t){
					include = include + dp[i - t - 1];
				}
				
				dp[i] = Math.max(include, exclude);
			}else{
				dp[i] = dp[i - 1];
			}
		}

		return dp[m];
	}

	public static void main(String args[]) {
		int[] nums = new int[] { 6, 7, 12, 14, 16 };
		int[] rev = new int[] { 5, 8, 5, 3, 1 };
		int m = 20;
		int t = 5;
				
		System.out.println("Maximum revenue: " + maxRevenueBasedOnBoards(m, nums, rev, t));
		System.out.println("Maximum revenue: " + maxRevenueBasedOnMiles(m, nums, rev, t));
	}
}