package com.implement.pepcoding.dp;

public class ArithmeticSlices {
	
	private static int countAP(int[] nums) {
		int[] dp = new int[nums.length];
		int ans = 0;

		for (int i = 2; i < dp.length; i++) {
			if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
				dp[i] = dp[i - 1] + 1;

				ans = ans + dp[i];
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 2, 5, 9, 12, 15, 18, 22, 26, 30, 34, 36, 38, 40, 41 };
    	
    	System.out.println(countAP(nums));
	}

}
