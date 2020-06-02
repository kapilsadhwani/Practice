package com.prep.implement.dp;

import java.util.Arrays;
import java.util.HashSet;

/* Dynamic Programming Java implementation of LCS problem */
public class LCS_LongestDictionaryWord {
	/* Returns length of LCS for X[0..n-1], Y[0..m-1] */
	static int memoizedLCS(char[] X, char[] Y, int n, int m, int[][] cache) {
		if(n==0 || m==0) 
			return 0;
		
		if(cache[n][m] != -1)
			return cache[n][m];
		
		if (X[n - 1] == Y[m - 1]) // Check if chars in X and Y at those indexes are same
			cache[n][m] = 1 + memoizedLCS(X, Y, n-1, m-1, cache);
		else
			cache[n][m] = Math.max(memoizedLCS(X, Y, n-1, m, cache), 
								memoizedLCS(X, Y, n, m-1, cache));
		return cache[n][m];
	}
	
	/* Returns length of LCS for X[0..n-1], Y[0..m-1] */
	static int[][] memoizedLCS(char[] X, char[] Y, int n, int m) {
		int cache[][] = new int[n + 1][m + 1];

		/*
		 * Following steps build L[m+1][n+1] in bottom up fashion. Note that
		 * L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1]
		 */
		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < m + 1; j++) {
				cache[i][j] = -1;
			}
		}

		memoizedLCS(X, Y, n, m, cache);
		
		return cache;
	}
	/* Returns length of LCS for X[0..n-1], Y[0..m-1] */
	static int[][] iterativeLCS(String X, String Y, int n, int m) {
		int L[][] = new int[n + 1][m + 1];

		/*
		 * Following steps build L[m+1][n+1] in bottom up fashion. Note that
		 * L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1]
		 */
		for (int i = 0; i < n + 1; i++) {
			L[i][0] = 0;
		}

		for (int j = 0; j < m + 1; j++) {
			L[0][j] = 0;
		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				if (X.charAt(i-1) == Y.charAt(j-1)) // Check if chars in X and Y at those indexes are same
					L[i][j] = L[i - 1][j - 1] + 1;
				else
					L[i][j] = Math.max(L[i - 1][j], 
										L[i][j - 1]);
			}
		}
		return L;
	}

	/* Print the LCS for X[0..m-1], Y[0..n-1] */
	static boolean isSubSequenceOf(String X, String Y) {
		int n = X.length();
		int m = Y.length();
		
		int L[][] = iterativeLCS(X, Y, n, m);

		return L[n][m] == n; 
	}
	
	// Returns the longest String in dictionary which is a subsequence of str.
	static String findLongestString(HashSet<String> dict, String str) {
		String result = "";
		int maxLength = -1;

		// Traverse through all words of dictionary
		for (String word : dict) {
			// If current word is subsequence of str
			// and is largest such word so far.
			if (maxLength < word.length() && isSubSequenceOf(word, str)) {
				result = word;
				maxLength = word.length();
			}
		}

		// Return longest String
		return result;
	}

	public static void main(String[] args) {
		String[] arr = {"ale", "apple", "monkey", "plea"}; 
		HashSet<String> dict = new HashSet<String>(Arrays.asList(arr)); 
        
        String str = "abpcplea"; 
        System.out.println(findLongestString(dict, str));
	}
}