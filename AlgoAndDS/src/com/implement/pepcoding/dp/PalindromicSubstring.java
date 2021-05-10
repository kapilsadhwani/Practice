package com.implement.pepcoding.dp;

public class PalindromicSubstring {
	/*
	 * Time complexity : O(n^2). This gives us a runtime complexity of O(n^2).
	 * Space complexity : O(n^2). It uses O(n^2) space to store the table.
	 */

	public static int countSubstringDP(String s) {
		int strLength = s.length();

		if (strLength == 0)
			return 0;

		/*
		 * palindrome[i][j] will be false if substring str[i..j] is not
		 * palindrome Else table[i][j] will be true
		 */
		boolean palindrome[][] = new boolean[strLength][strLength];
		int count = 0;

		// Trivial case: Single letter palindromes
		for (int i = 0; i < strLength; i++) {
			palindrome[i][i] = true;
			count++;
		}

		// Finding palindrome of 2 characters
		for (int i = 0; i < strLength - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				palindrome[i][i + 1] = true;
				count++;
			}
		}

		// Finding palindromes of lengths greater than 2
		for (int curr_len = 3; curr_len <= strLength; curr_len++) {
			// Fix the starting index
			for (int start = 0; start + curr_len <= strLength; start++) {

				// Get ending of substring from start and current length
				int end = start + curr_len - 1;

				if (s.charAt(start) == s.charAt(end) && // The first and last
														// characters should
														// match
						palindrome[start + 1][end - 1]) { // Rest of the
															// substring should
															// be a palindrome
					palindrome[start][end] = true;

					count++;
				}
			}
		}

		return count;
	}
	
	/*
	 * Time complexity : O(n^2). This gives us a runtime complexity of O(n^2).
	 * Space complexity : O(n^2). It uses O(n^2) space to store the table.
	 */

	public static int countSubstringDPII(String s) {
		int n = s.length();

		if (n == 0)
			return 0;

		/*
		 * palindrome[i][j] will be false if substring str[i..j] is not
		 * palindrome Else table[i][j] will be true
		 */
		boolean dp[][] = new boolean[n][n];
		int count = 0;

		// Gap = 0 to n - 1 OR length = 1 to n
		for (int g = 0; g < n; g++) {

			// Traverse diagonally with loop ending in last column
			for (int i = 0, j = g; j < dp[0].length; i++, j++) {
				// Trivial case: Single letter is always a palindrome
				if (g == 0) {
					dp[i][j] = true;
				}
				// Trivial case: String of 2 characters
				else if (g == 1) {
					dp[i][j] = s.charAt(i) == s.charAt(j);
				} else {
					if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == true) {
						dp[i][j] = true;
					} else {
						dp[i][j] = false;
					}
				}

				if (dp[i][j]) {
					count++;
				}
			}
		}

		return count;
	}

	/*
	 * Time complexity : O(n^2). This gives us a runtime complexity of O(n^2).
	 * Space complexity : O(1). It uses constant space.
	 * 
	 * Using Center and Expand around it
	 */
	public static int countSubstringsExpandAoundCenter(String s) {
		if (s == null || s.length() < 1)
			return 0;

		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			count = count + expandAroundCenter(s, i, i);
			count = count + expandAroundCenter(s, i, i + 1);
		}

		return count;
	}

	private static int expandAroundCenter(String s, int start, int end) {
		int count = 0;

		while (start >= 0 && end < s.length()
				&& s.charAt(start) == s.charAt(end)) {
			start--;
			end++;

			count++;
		}
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s;
		s = "forgeeksskeegfor";

		System.out.println("DP1 : " + countSubstringDP(s) + ", "
				+ "DP2 : " + countSubstringDPII(s) + ", "
				+ "Expand Around Center : "
				+ countSubstringsExpandAoundCenter(s));

		s = "abc";
		System.out.println("DP1 : " + countSubstringDP(s) + ", "
				+ "DP2 : " + countSubstringDPII(s) + ", "
				+ "Expand Around Center : "
				+ countSubstringsExpandAoundCenter(s));

		s = "aaa";
		System.out.println("DP1 : " + countSubstringDP(s) + ", "
				+ "DP2 : " + countSubstringDPII(s) + ", "
				+ "Expand Around Center : "
				+ countSubstringsExpandAoundCenter(s));
	}

}
