package com.implement.dp;

/* Dynamic Programming Java implementation of LCS problem */
public class LCSS_LongestCommonSubstring {
	/* Returns length of LCS for X[0..n-1], Y[0..m-1] */
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
	static void print(char[] X, char[] Y, int n, int m) {
		int L[][] = iterativeLCSS(X, Y, n, m);
		int lpssLength = 0;
		int r = 0;
		
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				if(L[i][j] > lpssLength){
					lpssLength = L[i][j];
					r = i;				
				}
			}
		}

		// Following code is used to print LCS
		char[] lcss = new char[lpssLength];
		int index = lcss.length;
		
		int strXPtr = r; 

		while (index > 0) {		
			lcss[index - 1] = X[strXPtr - 1];
			index--;
			strXPtr--;
		}
		System.out.println("Substring of " + String.valueOf(X) + " and " 
						+ String.valueOf(Y) + " is " + String.valueOf(lcss));
	}

	public static void main(String[] args) {
		String s1 = "RJKABCDEG";
		String s2 = "SWCABCCDEG";

		char[] X = s1.toCharArray();
		char[] Y = s2.toCharArray();
		int n = X.length;
		int m = Y.length;
		
		int LCSS[][] = iterativeLCSS(X, Y, n, m);
		
		int lcssLength = 0;
		
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				if(LCSS[i][j] > lcssLength){
					lcssLength = LCSS[i][j];
				}
			}
		}
		
		System.out.println("Length of LCSS is " + lcssLength);
		print(X, Y, n, m);
	}
}