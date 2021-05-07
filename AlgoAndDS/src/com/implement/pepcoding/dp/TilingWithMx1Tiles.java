package com.implement.pepcoding.dp;

/*
 * Tiling With M * 1 Tiles
 * Given a number n and a number m representing the length and breadth of a n * m floor.
 * Given an infinite supply of m * 1 tiles.
 * Calculate the number of ways floor can be tiled using tiles.
 */
public class TilingWithMx1Tiles {
	private static int countWays(int n, int m) {
		int[] dp = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			if (i < m) {
				// Place vertically
				dp[i] = 1;
			} else if (i == m) {
				/*
				 * We have 2 ways. 
				 * Way 1: Place vertically or
				 * Way 2: Place horizontally
				 */
				dp[i] = 2;
			} else {
				dp[i] = dp[i - 1] + 	// Place vertically, remaining floor is (n- 1) long
						dp[i - m]; 		// Place horizontally, remaining floor is (n - 2) long +  
										// some weird area that can be tiled only in 1 way
			}
		}

		return dp[n];
	}

	public static void main(String[] args) {
		int n = 8;
		int m = 3;
		
		System.out.println(countWays(n, m));
	}
}