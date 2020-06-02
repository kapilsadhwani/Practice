package com.prep.implement.dp;

public class DistinctTransformations {
	public int countWaysR(String bigger, String smaller, int n, int m, int[][] cache) {
		// Make sure bigger String length is equal or greater
		if(m > n) return 0;

		// All elements will be either inserted or removed in 1 way.
		if (m == 0 || n == 0)
			return 1; // all elements will be removed.
		
		if(cache[n][m] != -1)
			return cache[n][m];

		/* If last characters are matching, 
		 * 	1. Ignore the last character of bigger String and make a recursive call (OR)
		 * 	2. Consider it and make a recursive call with 1 less length for both the strings
		 * Else
		 * 	Ignore the last character of bigger String and make a recursive call (OR)
		 */ 
		if (bigger.charAt(n - 1) == smaller.charAt(m - 1)) {
			cache[n][m] = countWaysR(bigger, smaller, n - 1, m, cache);
			cache[n][m] = cache[n][m]
					+ countWaysR(bigger, smaller, n - 1, m - 1, cache);
		} else
			cache[n][m] = countWaysR(bigger, smaller, n - 1, m, cache);

		return cache[n][m];
	}
	
	public int countWays(String bigger, String smaller, int n, int m) {	
		int[][] cache = new int[n+1][m+1];
		
		for(int i=0; i<n+1; i++)
			for(int j=0; j<m+1; j++)
				cache[i][j] = -1;
		
		return countWaysR(bigger, smaller, n, m, cache);
	}

	public static void main(String[] args) {
		//String small = "abccdf";
		String small = "abccdf";
		String big = "abcccdf";
		DistinctTransformations st = new DistinctTransformations();
		System.out.println("Number of ways: " + st.countWays(big, small, big.length(), small.length()));
		
		small = "uwnny";
		big = "uwnny";
		System.out.println("Number of ways: " + st.countWays(big, small, big.length(), small.length()));
	}
}