package com.implement.pepcoding.dp;

import java.util.HashMap;
import java.util.Map;


/* Dynamic Programming Java implementation of Highway Billboard problem */

class HighwayBillboard {
	/*
	 * This solution depends on number of boards [Complexity: O(n ^ 2)]
	 */
	static int maxRevenueBasedOnBoards(int m, int[] nums, int[] rev, int t) {
		// DP of revenues
		int[] dp = new int[nums.length];

		int ans = 0;

		for (int i = 0; i < nums.length; i++) {
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
		
		// nums[i] is nothing but a mile point
		for(int i = 0; i < nums.length; i++){
			map.put(nums[i], rev[i]);
		}

		int[] dp = new int[m + 1];
		dp[0] = 0;
		
		for (int i = 1; i <= m; i++) {
			
			// i is a mile point
			if (map.containsKey(i)){	
				// Board can be installed or not
				
				int exclude = dp[i - 1];
				int include;
				
				if(i > t){
					include = map.get(i) + dp[i - t - 1];
				}else{
					include = map.get(i);
				}
				
				dp[i] = Math.max(include, exclude);
			}else{
				// No board at i'th mile. Hence, revenue will be same as that of previous mile
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