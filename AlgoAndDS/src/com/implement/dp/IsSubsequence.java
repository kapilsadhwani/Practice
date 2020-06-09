package com.implement.dp;

/* Dynamic Programming Java implementation of LCS problem */
public class IsSubsequence {
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
				if (X[i - 1] == Y[j - 1]) // Check if chars in X and Y at those
											// indexes are same
					L[i][j] = L[i - 1][j - 1] + 1;
				else
					L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
			}
		}
		return L;
	}

	public static boolean isSubsequenceLCS(String s, String t) {
		if ((s == null && t == null) || (s.length() == 0 && t.length() == 0))
			return true;

		int m = s.length();
		int n = t.length();
		int L[][] = iterativeLCS(s.toCharArray(), t.toCharArray(), m, n);

		return s.length() == L[m][n];
	}

	public static boolean isSubsequence(String s, String t) {
		if (s.length() > t.length()) {
			return false;
		}
		if (s.length() == 0)
			return true;
		int i = 0, j = 0;
		while (i < t.length() && j < s.length()) {
			if (t.charAt(i) == s.charAt(j))
				j++;
			i++;
		}
		if (j == s.length())
			return true;
		return false;
	}

	public static void main(String[] args) {
		String s = "abc";
		String t = "ahbgdc";

		System.out.println("LCS: " + isSubsequenceLCS(s, t) + ", 2P: " + isSubsequence(s, t));

		s = "axc";
		t = "ahbgdc";

		System.out.println("LCS: " + isSubsequenceLCS(s, t) + ", 2P: " + isSubsequence(s, t));
	}
}