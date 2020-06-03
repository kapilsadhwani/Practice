package com.implement.dp;

/* Dynamic Programming Java implementation of LCS problem */
public class LRSS_LongestRepeatingSubstring {
	/* Returns length of LCS for X[0..n-1], Y[0..m-1] */
	static int[][] iterativeLRSS(char[] X, char[] Y, int n, int m) {
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
			for (int j = i + 1; j < m + 1; j++) {
				if (X[i - 1] == Y[j - 1] && i != j){	//  && L[i - 1][j - 1] < (j - i)
					L[i][j] = L[i - 1][j - 1] + 1;
				}else
					L[i][j] = 0;
			}
		}
		return L;
	}
	
	/* Print the LCS for X[0..m-1], Y[0..n-1] */
	static void print(char[] X, char[] Y, int m, int n) {
		int L[][] = iterativeLRSS(X, Y, m, n);
		int lrssLength = 0;
		int r=0, c=0;
		
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				if(L[i][j] > lrssLength){
					lrssLength = L[i][j];
					r = i;
					c = j;
				
				}
			}
		}

		// Following code is used to print LCS
		char[] lrss = new char[lrssLength];
		int index = lrss.length;
		
		int i = r, j = c;
		while (index > 0) {
			// If current character in X[] and Y are same, then
			// current character is part of LCS
			if (X[i - 1] == Y[j - 1]) {
				// Put current character in result
				lrss[index - 1] = X[i - 1];
				index--;
			}
			
			i--;
			j--;
		}
		System.out.println("Longest repeating substring of " + String.valueOf(X) + " and " 
						+ String.valueOf(Y) + " is " + String.valueOf(lrss));
	}

	public static void main(String[] args) {
		String s1 = "geeksforgeeks";

		char[] X = s1.toCharArray();

		int n = X.length;
		
		int LRSS[][] = iterativeLRSS(X, X, n, n);
		
		int lrssLength = 0;
		
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if(LRSS[i][j] > lrssLength){
					lrssLength = LRSS[i][j];
				}
			}
		}
		
		System.out.println("Length of LRSS is " + lrssLength);
		print(X, X, n, n);
		
		s1 = "aabaabaaba";

		X = s1.toCharArray();

		n = X.length;
		
		LRSS = iterativeLRSS(X, X, n, n);
		
		lrssLength = 0;
		
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if(LRSS[i][j] > lrssLength){
					lrssLength = LRSS[i][j];
				}
			}
		}
		
		System.out.println("Length of LRSS is " + lrssLength);
		print(X, X, n, n);
	}
}