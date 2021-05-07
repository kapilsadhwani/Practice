package com.implement.pepcoding.dp;

/*
 * Tiling With 2 * 1 Tiles
 *  Given a number n representing the length of a floor space which is 2m wide. It's a 2 * n floor.
 *  Given an infinite supply of 2 * 1 tiles.
 *  Calculate number of ways floor can be tiled using tiles.
 */
public class TilingWith2x1Tiles {

	private static int countWays(int n) {
		int[] dp = new int[n + 1];

		/*
		 * Tiling 2 x 1 floor, will have 1 way. Place vertically
		 */

		dp[1] = 1;

		/*
		 * Tiling 2 x 2 floor, will have 2 ways. 
		 * Way 1: Place vertically   (2 tiles) or
		 * Way 2: Place horizontally (2 tiles)
		 */
		dp[2] = 2;

		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + 	// Place vertically, remaining floor is (n- 1) long
					dp[i - 2]; 		// Place horizontally, remaining floor is (n - 2) long +  
									// some weird area that can be tiled only in 1 way
		}

		return dp[n];
	}

	public static void main(String[] args) {
		int n = 4;
		
		System.out.println(countWays(n));
	}
}