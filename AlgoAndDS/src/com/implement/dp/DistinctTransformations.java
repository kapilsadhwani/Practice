package com.implement.dp;

public class DistinctTransformations {
	public int countWaysR(String src, String tgt, int n, int m,
			int[][] cache) {
		// Make sure bigger String length is equal or greater
		if (m > n)
			return 0;

		// All elements will be either inserted or removed in 1 way.
		if (m == 0 || n == 0)
			return 1; // all elements will be removed.

		if (cache[n][m] != -1)
			return cache[n][m];

		/*
		 * If last characters are matching, 
		 * 1. Ignore the last character of bigger String and make a recursive call (OR) 
		 * 2. Consider it and make a recursive call with 1 less length for both the strings 
		 * 
		 * Else Ignore the last character of bigger String and make a recursive call (OR)
		 */
		if (src.charAt(n - 1) == tgt.charAt(m - 1)) {
			cache[n][m] = countWaysR(src, tgt, n - 1, m, cache);
			cache[n][m] = cache[n][m]
					+ countWaysR(src, tgt, n - 1, m - 1, cache);
		} else
			cache[n][m] = countWaysR(src, tgt, n - 1, m, cache);

		return cache[n][m];
	}

	public int countWays(String bigger, String smaller, int n, int m) {
		int[][] cache = new int[n + 1][m + 1];

		for (int i = 0; i < n + 1; i++)
			for (int j = 0; j < m + 1; j++)
				cache[i][j] = -1;

		return countWaysR(bigger, smaller, n, m, cache);
	}

	// s: source, t: target
	public int countWaysDP(String s, String t) {
		int n = s.length();
		int m = t.length();

		int[][] dp = new int[n + 1][m + 1];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				// Trivial case: Both strings are empty
				if (i == 0 && j == 0) {
					dp[i][j] = 1;
				} else if (i == 0) {
					// Trivial case: First Row, i.e. Source is empty
					dp[i][j] = 0;
				} else if (j == 0) {
					// Trivial case: First Column, i.e. Target is empty
					/*
					 * Remove all characters from src string
					 */
					dp[i][j] = 1;
				} else {
					if (s.charAt(i - 1) != t.charAt(j - 1)) {
						dp[i][j] = dp[i - 1][j]; // Exclude src character
					} else {
						dp[i][j] = dp[i - 1][j] + // Exclude src character
								dp[i - 1][j - 1]; // Include src character
					}
				}
			}
		}

		return dp[n][m];
	}

	public static void main(String[] args) {
		// String small = "abccdf";
		String small = "abccdf";
		String big = "abcccdf";
		DistinctTransformations st = new DistinctTransformations();
		System.out.println("Number of ways - Recursive: "
				+ st.countWays(big, small, big.length(), small.length())
				+ ", DP: " + st.countWaysDP(big, small));

		small = "uwnny";
		big = "uwnny";
		System.out.println("Number of ways - Recursive: "
				+ st.countWays(big, small, big.length(), small.length())
				+ ", DP: " + st.countWaysDP(big, small));

		small = "abcd";
		big = "aaaabbbccd";
		System.out.println("Number of ways - Recursive: "
				+ st.countWays(big, small, big.length(), small.length())
				+ ", DP: " + st.countWaysDP(big, small));
	}
}