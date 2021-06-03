package com.implement.dp;

/* Dynamic Programming Java implementation of LCS problem */
public class LRS_LongestRepeatingSubsequence {
	/* Returns length of LCS for X[0..n-1], Y[0..m-1] */
	static int memoizedLRS(char[] X, char[] Y, int n, int m, int[][] cache) {
		if (n == 0 || m == 0)
			return 0;

		if (cache[n][m] != -1)
			return cache[n][m];

		if (X[n - 1] == Y[m - 1] &&
				m != n) // Check if chars in X and Y at those indexes
									// are same
			cache[n][m] = 1 + memoizedLRS(X, Y, n - 1, m - 1, cache);
		else
			cache[n][m] = Math.max(memoizedLRS(X, Y, n - 1, m, cache),
					memoizedLRS(X, Y, n, m - 1, cache));
		return cache[n][m];
	}

	/* Returns length of LCS for X[0..n-1], Y[0..m-1] */
	static int[][] memoizedLRS(char[] X, char[] Y, int n, int m) {
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

		memoizedLRS(X, Y, n, m, cache);

		return cache;
	}

	/* Returns length of LCS for X[0..n-1], Y[0..m-1] */
	static int[][] iterativeLRS(char[] X, char[] Y, int n, int m) {
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
				//Check if chars in X and Y at those indexes are same
				if (X[i - 1] == Y[j - 1] &&
						i != j )
					L[i][j] = L[i - 1][j - 1] + 1;
				else
					L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
			}
		}
		return L;
	}

	public static int longestRepeatingSubsequence(String text1) {
		if (text1 == null || text1.length() == 0)
			return 0;

		int m = text1.length();

		int L[][] = iterativeLRS(text1.toCharArray(), text1.toCharArray(), m, m);

		return L[m][m];
	}

	public static void main(String[] args) {
		String s1 = "AABEBCDD";

		char[] X = s1.toCharArray();

		int m = X.length;

		int L[][] = iterativeLRS(X, X, m, m);

		System.out.println("Length of LRS is" + " " + L[m][m]);

		System.out.println("\n" + longestRepeatingSubsequence(s1));
	}
}