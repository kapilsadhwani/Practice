package com.prep.implement.dp;


/* Dynamic Programming Java implementation of LCS problem */
public class InterleavedStrings {
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
	static int[][] iterativeLCS(char[] X, char[] Y, int n, int m) {
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
				if (X[i - 1] == Y[j - 1]) // Check if chars in X and Y at those indexes are same
					L[i][j] = L[i - 1][j - 1] + 1;
				else
					L[i][j] = Math.max(L[i - 1][j], 
										L[i][j - 1]);
			}
		}
		return L;
	}

	/* Print the LCS for X[0..m-1], Y[0..n-1] */
	static void print(char[] X, char[] Y, int m, int n) {
		int L[][] = iterativeLCS(X, Y, m, n);

		// Following code is used to print LCS
		int index = L[m][n];

		// Create a character array to store the lcs string
		char[] lcs = new char[index];
		// lcs[index] = ''; // Set the terminating character

		// Start from the right-most-bottom-most corner and
		// one by one store characters in lcs[]
		int i = m, j = n;
		while (i > 0 && j > 0) {
			// If current character in X[] and Y are same, then
			// current character is part of LCS
			if (X[i - 1] == Y[j - 1]) {
				// Put current character in result
				lcs[index - 1] = X[i - 1];

				// reduce values of i, j and index
				i--;
				j--;
				index--;
			}

			// If not same, then find the larger of two and
			// go in the direction of larger value
			else if (L[i - 1][j] > L[i][j - 1])
				i--;
			else
				j--;
		}

		// Print the lcs
		System.out.print("LCS of " + String.valueOf(X) + " and "
				+ String.valueOf(Y) + " is ");
		System.out.print(String.valueOf(lcs));
	}
	
	static int isInterleavedString(String A, String B, String C){
		int a = A.length();
		int b = B.length();
		int c = C.length(); 
		
		int LCSA[][] = iterativeLCS(A.toCharArray(), C.toCharArray(), a, c);
		if(A.length() != LCSA[a][c]) return 0;
		
		int LCSB[][] = iterativeLCS(B.toCharArray(), C.toCharArray(), b, c);
		if(B.length() != LCSB[b][c]) return 0;
		
		return 1;
		
	}

	public static void main(String[] args) {
		String s1 = "AGGTAB";
		String s2 = "GXTXAYB";

		char[] X = s1.toCharArray();
		char[] Y = s2.toCharArray();
		int m = X.length;
		int n = Y.length;

		int L[][] = iterativeLCS(X, Y, m, n);

		System.out.println("Length of LCS is" + " " + L[m][n]);
		
		String A = "YX";
		String B = "X";
		String C = "XXY";
		
		System.out.println(isInterleavedString(A, B, C));
		
		A = "XY";
		System.out.println(isInterleavedString(A, B, C));

		//print(X, Y, m, n);
	}
}