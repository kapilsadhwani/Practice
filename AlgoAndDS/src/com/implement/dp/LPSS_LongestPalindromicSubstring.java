package com.implement.dp;

// Java Solution 
public class LPSS_LongestPalindromicSubstring {
	// A utility function to print a substring str[low..high]
	static void printSubStr(String str, int low, int high) {
		System.out.println(str.substring(low, high));
	}

	// This function prints the longest palindrome substring of str[].
	// It also returns the length of the longest palindrome
	static int longestPalSubstr(String str) {
		int n = str.length(); // get length of input string

		// table[i][j] will be false if substring str[i..j] is not palindrome.
		// Else table[i][j] will be true
		boolean table[][] = new boolean[n][n];

		// All substrings of length 1 are palindromes
		int maxLength = 1;
		for (int i = 0; i < n; ++i)
			table[i][i] = true;

		// Check for sub-string of length 2
		int start = 0;
		for (int i = 0; i < n - 1; ++i) { // i+k-1 < n, here k=2
			if (str.charAt(i) == str.charAt(i + 1)) {
				table[i][i + 1] = true;
				start = i;
				maxLength = 2;
			}
		}

		// Check for lengths greater than 2. k is length of substring
		for (int k = 3; k <= n; ++k) {

			// Fix the starting index
			for (int i = 0; i < n - k + 1; ++i) { // i+k-1 < n
				// Get the ending index of substring from
				// starting index i and length k
				int j = i + k - 1;

				// checking for sub-string from ith index to
				// jth index iff str.charAt(i+1) to
				// str.charAt(j-1) is a palindrome
				if (table[i + 1][j - 1] && str.charAt(i) == str.charAt(j)) {
					table[i][j] = true;

					if (k > maxLength) {
						start = i;
						maxLength = k;
					}
				}
			}
		}
		System.out.print("Longest palindrome substring is: ");
		printSubStr(str, start, start + maxLength);

		return maxLength; // return length of LPS
	}

	/* Returns length of LCSS for X[0..n-1], Y[0..m-1] */
	static int[][] iterativeLCSS(char[] X, char[] Y, int n, int m) {
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
				if (X[i - 1] == Y[j - 1]){ // Check if chars in X and Y at those indexes are same
					L[i][j] = L[i - 1][j - 1] + 1;
				}else
					L[i][j] = 0;
			}
		}
		return L;
	}

	/* Print the LCS for X[0..m-1], Y[0..n-1] */
	static void print(char[] X, char[] Y, int[][] LCS, int row, int col) {
		char[] lps = new char[LCS[row][col]];
		int index = lps.length;
		
		int strXPtr = row;
		while (index > 0) {
			lps[index - 1] = X[strXPtr - 1];
			index--;
			strXPtr--;
		}

		// Print the lps
		System.out.print("LPS of " + String.valueOf(X) + " and "
				+ String.valueOf(Y) + " is ");
		System.out.print(String.valueOf(lps));
	}
	
	static String getReverseString(String str){
		char[] strArray = str.toCharArray();
		char[] reverseStr = new char[str.length()];
		
		for(int i=str.length()-1, j=0; i>=0; i--, j++)
			reverseStr[j] = strArray[i];
		
		return String.valueOf(reverseStr);
	}

	/* 
	 * This function prints the longest palindrome substring of str[].
	 * It also returns the length of the longest palindrome. Based on AS
	 */
	
	static int LPS(String str) {
		int n = str.length(); // get length of input string
		
		String reverseStr = getReverseString(str);
				
		int L[][] = iterativeLCSS(str.toCharArray(), reverseStr.toCharArray(), n, n);
		int lpssLength = 0;
		int r = 0, c = 0;
		
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if(L[i][j] > lpssLength){
					lpssLength = L[i][j];
					r = i;
					c = j;
				}
			}
		}

		print(str.toCharArray(), reverseStr.toCharArray(),L, r, c);
		return L[r][c]; // return length of LPS
	}
	
	public static String longestPalindromicSubstrDPII(String s) {
		int n = s.length();

		if (n == 0) return "";

		/*
		 * palindrome[i][j] will be false if substring str[i..j] is not
		 * palindrome Else table[i][j] will be true
		 */
		boolean dp[][] = new boolean[n][n];
		int len = 0;
		int startIdx = 0;
		int endIdx = 0;

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
					if (s.charAt(i) == s.charAt(j)) {
						dp[i][j] = true;
					} else {
						dp[i][j] = false;
					}
				} else {
					if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == true) {
						dp[i][j] = true;
					} else {
						dp[i][j] = false;
					}
				}

				if (dp[i][j] == true) {
					if(g + 1 > len){
						len = g + 1;
						startIdx = i;
						endIdx = j;
					}
				}
			}
		}

		return s.substring(startIdx, endIdx + 1);
	}

	// Driver program to test above functions
	public static void main(String[] args) {

		String str = "forgeeksskeegfor";
		System.out.println("Length is: " + longestPalSubstr(str));
		System.out.println("\nLength is: " + LPS(str));
		System.out.println(longestPalindromicSubstrDPII(str));
		
		System.out.println(" ===================================== ");
		
		str = "abcd";
		//System.out.println("Length is: " + longestPalSubstr(str));
		int lpsLength = LPS(str);
		System.out.println("\nLength is: " + lpsLength + ", "
				+ "Number of insertions: " + (str.length()-lpsLength));
		System.out.println(longestPalindromicSubstrDPII(str));
		
		System.out.println(" ===================================== ");
		
		str = "aba";
		//System.out.println("Length is: " + longestPalSubstr(str));
		lpsLength = LPS(str);
		System.out.println("\nLength is: " + lpsLength + ", "
				+ "Number of insertions: " + (str.length()-lpsLength));
		System.out.println(longestPalindromicSubstrDPII(str));
		
		System.out.println(" ===================================== ");
		
		str = "geeks";
		//System.out.println("Length is: " + longestPalSubstr(str));
		lpsLength = LPS(str);
		System.out.println("\nLength is: " + lpsLength + ", "
				+ "Number of insertions: " + (str.length()-lpsLength));
		System.out.println(longestPalindromicSubstrDPII(str));
	}
}
