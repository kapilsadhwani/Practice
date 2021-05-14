package com.implement.dp;

// Java Solution 
public class LPS_LongestPalindromicSubsequence {
	public static int LPS(String s) {
		int n = s.length();

		if (n == 0) return 0;

		/*
		 * palindrome[i][j] will be false if substring str[i..j] is not
		 * palindrome Else table[i][j] will be true
		 */
		int dp[][] = new int[n][n];

		// Gap = 0 to n - 1 OR length = 1 to n
		for (int g = 0; g < n; g++) {

			// Traverse diagonally with loop ending in last column
			for (int i = 0, j = g; j < dp[0].length; i++, j++) {
				// Trivial case: Single letter is always a palindrome
				if (g == 0) {
					dp[i][j] = 1;
				}
				// Trivial case: String of 2 characters
				else if (g == 1) {
					dp[i][j] = s.charAt(i) == s.charAt(j) ? 2 : 1;
				} else {
					if (s.charAt(i) == s.charAt(j)) {
						dp[i][j] = 2 + dp[i + 1][j - 1];
					} else {
						dp[i][j] = Math.max(dp[i + 1][j], // suffix
									dp[i][j - 1]);		  // prefix
					}
				}
			}
		}

		return dp[0][n - 1];
	}

	// Driver program to test above functions
	public static void main(String[] args) {

		String str = "forgeeksskeegfor";
		System.out.println(LPS(str));
		
		System.out.println(" ===================================== ");
		
		str = "abcgackbc";
		System.out.println(LPS(str));
		
	}
}
