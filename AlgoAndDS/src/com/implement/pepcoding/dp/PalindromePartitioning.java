package com.implement.pepcoding.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PalindromePartitioning {
	/*
	 * Create a dp 
	 */
	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<>();
		boolean[][] dp = new boolean[s.length()][s.length()];
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j <= i; j++) {				//j = start, i = end
				if (s.charAt(i) == s.charAt(j)
						&& (i - j <= 2 || dp[j + 1][i - 1])) {
					dp[j][i] = true;
				}
			}
		}
		helper(res, new ArrayList<>(), dp, s, 0);
		return res;
	}

	private void helper(List<List<String>> res, List<String> path, boolean[][] dp, String s, int pos) {
		if (pos == s.length()) {
			res.add(new ArrayList<>(path));
			return;
		}

		for (int i = pos; i < s.length(); i++) {
			if (dp[pos][i]) {
				path.add(s.substring(pos, i + 1));
				helper(res, path, dp, s, i + 1);
				path.remove(path.size() - 1);
			}
		}
	}

	private boolean isPalindrome(String s, int i, int j) {
		// TODO Auto-generated method stub
		char[] strArray = s.toCharArray();

		if (i == j)
			return true;

		if (i > j)
			return true;

		while (i < j) {
			if (strArray[i] != strArray[j])
				return false;

			i++;
			j--;
		}

		return true;
	}

	private int solve(String s, int i, int j, int[][] cache) {
		if (i >= j)
			return 0;

		if (isPalindrome(s, i, j) == true)
			return 0;

		if (cache[i][j] != -1)
			return cache[i][j];

		int min = Integer.MAX_VALUE;

		for (int k = i; k < j; k++) {
			int temp = 1 + solve(s, i, k, cache) + solve(s, k + 1, j, cache);

			if (temp < min)
				min = temp;
		}

		return cache[i][j] = min;
	}

	private int numberOfPartitionsAV(String s) {
		int len = s.length();

		int[][] cache = new int[len + 1][len + 1];

		for (int i = 0; i < cache.length; i++)
			Arrays.fill(cache[i], -1);

		return solve(s, 0, len - 1, cache);
	}
	
	// Iterative approach - O(n^2)
	private int numberOfPartitionsDP(String s) {
		int n = s.length();

		/*
		 * palindrome[i][j] will be false if substring str[i..j] is not
		 * palindrome Else table[i][j] will be true
		 */
		boolean dp[][] = new boolean[n][n];

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
			}
		}

		// Storage array will contain min number of partitions
		int[] strg = new int[n];
		
		// Trivial case: Single letter is always a palindrome
		strg[0] = 0;

		/*
		 * i = start of a string j = end of a string
		 */

		for (int j = 1; j < strg.length; j++) {
			if (dp[0][j]) {
				// string is a palindrome
				strg[j] = 0;
			} else {
				int min = Integer.MAX_VALUE;

				for (int i = j; i >= 1; i--) {
					// Suffix i...j is a palindrome
					if (dp[i][j]) {
						if (strg[i - 1] < min) {
							min = strg[i - 1];
						}
					}
				}

				strg[j] = min + 1;
			}
		}

		return strg[n - 1];
	}
	
	// Iterative approach - O(n^3)
	private int numberOfPartitionsDPII(String s) {
		int n = s.length();

		/*
		 * palindrome[i][j] will be false if substring str[i..j] is not
		 * palindrome Else table[i][j] will be true
		 */
		boolean dp[][] = new boolean[n][n];

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
			}
		}
		
		// Storage array will contain min number of partitions
		int[][] strg = new int[n][n];
		
		// Gap = 0 to n - 1 OR length = 1 to n
		for (int g = 0; g < n; g++) {

			// Traverse diagonally with loop ending in last column
			for (int i = 0, j = g; j < strg[0].length; i++, j++) {
				// Trivial case: Single letter is always a palindrome
				if (g == 0) {
					strg[i][j] = 0;
				}
				// Trivial case: String of 2 characters
				else if (g == 1) {
					if(s.charAt(i) == s.charAt(j)){
						strg[i][j] = 0;
					}else{
						strg[i][j] = 1;
					}
				} else {
					if (dp[i][j] == true) {
						strg[i][j] = 0;
					} else {
						int min = Integer.MAX_VALUE;
						
						for(int k = i; k < j; k++){
							int left = strg[i][k];
							int right = strg[k + 1][j];
							
							if(left + right + 1 < min){
								min = left + right + 1;
							}
						}
						
						strg[i][j] = min;
					}
				}
			}
		}
		
		return strg[0][n - 1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "nitik";
		//String s = "abcbbccbc";
		//String s = "abcde";
		
		PalindromePartitioning pp = new PalindromePartitioning();

		int partitions = pp.numberOfPartitionsAV(s);

		System.out.println("Number of partitions required is " + partitions);
		
		List<List<String>> result = pp.partition(s);
		
		System.out.println(result);
		
		System.out.println("===================");
		
		System.out.println("Number of partitions (Iterative Order n^3) : " + pp.numberOfPartitionsDPII(s));
		System.out.println("Number of partitions (Iterative Order n^2) : " + pp.numberOfPartitionsDP(s));
	}

}
