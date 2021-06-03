package com.implement.pepcoding.dp;

public class GameTheory {
	
	public static int coinMax(int[] num, int l, int r){
		if (l + 1 == r){
			return Math.max(num[l], num[r]);
		}
		
		return Math.max(num[l] + Math.min(coinMax(num, l+2, r), coinMax(num, l+1, r-1)), 
						num[r] + Math.min(coinMax(num, l+1, r-1), coinMax(num, l, r-2)));
	}
	
	// Using Gap Strategy
	private static int coinMaxGapStrategy(int[] nums){
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
					int val1 = nums[i] + 
								Math.min(dp[i + 2][j], dp[i + 1][j - 1]);
					int val2 = nums[j] + 
								Math.min(dp[i + 1][j - 1], dp[i][j - 2]);
					
					dp[i][j] = Math.max(val1, val2);
				}
			}
		}
		
		return dp[0][n - 1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] num = {1, 5, 7, 3, 2, 4};
		
		System.out.println("Recursion : " + coinMax(num, 0, num.length-1) + 
					", Gap strategy: " + coinMaxGapStrategy(num));
		
		num = new int[]{1, 5, 700, 3};
		
		System.out.println("Recursion : " + coinMax(num, 0, num.length-1) + 
				", Gap strategy: " + coinMaxGapStrategy(num));
		
		num = new int[]{20, 30, 2, 2, 2, 10};
		
		System.out.println("Recursion : " + coinMax(num, 0, num.length-1) + 
				", Gap strategy: " + coinMaxGapStrategy(num));
	}

}
