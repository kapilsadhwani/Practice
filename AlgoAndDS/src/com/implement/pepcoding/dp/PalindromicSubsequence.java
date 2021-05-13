package com.implement.pepcoding.dp;

import java.util.HashMap;

public class PalindromicSubsequence {
	/*
	 * Time complexity : O(n^2). This gives us a runtime complexity of O(n^2).
	 * Space complexity : O(n^2). It uses O(n^2) space to store the table.
	 */

	public static int countPalindromicSubsequenceDP(String s) {
		int n = s.length();

		if (n == 0)
			return 0;

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
					// Ex: cc [Palindromes are: 'c', 'c', 'cc']
					dp[i][j] = s.charAt(i) == s.charAt(j) ? 3 : 2;
				} else {
					if (s.charAt(i) == s.charAt(j)) {
						// C(c1mc2) = C(c1m) + C(mc2) + 1
						dp[i][j] = dp[i][j - 1] + dp[i + 1][j] + 1;
					} else {
						// C(c1mc2) = C(c1m) + C(mc2) - C(m)
						dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
					}
				}
			}
		}

		return dp[0][s.length() - 1];
	}
	
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
						// lps(m) + 2
						dp[i][j] = 2 + dp[i + 1][j - 1];
					} else {
						// Max(c1m, mc2)
						dp[i][j] = Math.max(dp[i + 1][j], // suffix
									dp[i][j - 1]);		  // prefix
					}
				}
			}
		}

		return dp[0][n - 1];
	}
	
	public static int countDistinctPalindromicSubsequenceDP(String s) {
		int n = s.length();

		if (n == 0)
			return 0;

		/*
		 * palindrome[i][j] will be false if substring str[i..j] is not
		 * palindrome Else table[i][j] will be true
		 */
		int dp[][] = new int[n][n];
		int[] prev = new int[n];
		
		HashMap<Character, Integer> map = new HashMap<>();
		
		for (int i = 0; i < n; i++) {
			char ch = s.charAt(i);

			if (map.containsKey(ch)) {
				prev[i] = map.get(ch);
			} else {
				prev[i] = -1;
			}

			map.put(ch, i);
		}

		int[] next = new int[n];
		map.clear();

		for (int i = n - 1; i >= 0; i--) {
			char ch = s.charAt(i);

			if (map.containsKey(ch)) {
				prev[i] = map.get(ch);
			} else {
				prev[i] = -1;
			}

			map.put(ch, i);
		}
		
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
					dp[i][j] = 2;
				} else {
					char sc = s.charAt(i);
					char ec = s.charAt(j);
							
					if (sc != ec) {
						// C(c1mc2) = C(c1m) + C(mc2) - C(m)
						dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
					} else {
						int nxt = next[i];
						int pre = prev[j];
						
						if(nxt > pre){
							// C(c1mc2) = 2 * C(m) + 2
							dp[i][j] = 2 * dp[i + 1][j - 1] + 2;
						}else if(nxt == pre){
							// C(c1mc2) = 2 * C(m) + 1
							dp[i][j] = 2 * dp[i + 1][j - 1] + 1;
						}else{
							/* C(c1mc2) = 2 * C(m) - C(m')
							 * where m' = nxt + 1 --> pre - 1							 
							 */
							dp[i][j] = 2 * dp[i + 1][j - 1] - dp[nxt + 1][pre - 1];
						}
						
					}
				}
			}
		}

		return dp[0][s.length() - 1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "ccbbgd";

		System.out.println("String: " + str);
		System.out.println("Count Palindromic Subsequence : " + countPalindromicSubsequenceDP(str));
		System.out.println("LPS : " + LPS(str));
		
		System.out.println(" ===================================== ");
		
		str = "forgeeksskeegfor";
		System.out.println("String: " + str);
		System.out.println("Count Palindromic Subsequence : " + countPalindromicSubsequenceDP(str));
		System.out.println("LPS : " + LPS(str));
		
		System.out.println(" ===================================== ");
		
		str = "abcgackbc";
		System.out.println("String: " + str);
		System.out.println("Count Palindromic Subsequence : " + countPalindromicSubsequenceDP(str));
		System.out.println("LPS : " + LPS(str));
		
		System.out.println(" ===================================== ");
		
		str = "bccb";
		System.out.println("String: " + str);
		System.out.println("Count Palindromic Subsequence : " + countPalindromicSubsequenceDP(str));
		System.out.println("Distinct Palindromic Subsequence : " + countDistinctPalindromicSubsequenceDP(str));
		
		System.out.println(" ===================================== ");
		
		str = "forgeeksskeegfor";
		System.out.println("String: " + str);
		System.out.println("Count Palindromic Subsequence : " + countPalindromicSubsequenceDP(str));
		System.out.println("Distinct Palindromic Subsequence : " + countDistinctPalindromicSubsequenceDP(str));
	}

}
