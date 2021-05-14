package com.implement.pepcoding.dp;

class MinPerfectSquares {
	/*
	 *  f(11) = sq(1) + sq(1) + s1(3) = 3
	 *  f(8) = sq(2) + sq(2) = 2
	 */
	static int minNumbers(int n) {
		int[] dp = new int[n + 1];
		
		dp[0] = 0;
		dp[1] = 1;
		
		for (int i = 2; i <= n; i++) {
			int min = Integer.MAX_VALUE;
			
			for (int j = 1; j * j <= i; j++) {
				int rem = i - j * j;
				
				if (dp[rem] < min){
					min = dp[rem];
				}
			}
			
			dp[i] = min + 1;
		}

		return dp[n];
	}

	public static void main(String args[]) {
		for(int i = 2; i <= 15; i++)
		System.out.println("For " + i + " : " + minNumbers(i));
	}
}