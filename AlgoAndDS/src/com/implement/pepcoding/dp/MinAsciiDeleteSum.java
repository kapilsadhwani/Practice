package com.implement.pepcoding.dp;
/**
 *
 * Minimum ASCII Delete Sum for Two Strings
 */
public class MinAsciiDeleteSum {
	public int minCost(String s1, String s2) {
        // Dim1 = s1; Dim2 = s2
        int dp[][] = new int[s1.length() + 1][s2.length() + 1];
        
        // String s1 of zero length and String s2 of zero length
        dp[0][0] = 0;
        
        // First Row
        for(int j = 1; j < dp[0].length; j++){
        	dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }
        
        // First Column
        for(int i = 1; i < dp.length; i++){
        	dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }
        
        // dp[i][j] stores minimum ASCII delete sum for s1[0...i-1] and s2[0...j-1]
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					/*
					 *  Starting chars same (c1 == c2), Look diagonally above
					 *  f(c1r1, c2r2) ==> f(r1, r2)
					 */
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					/*
					 *  Starting chars different (c1 != c2), take min of available options
					 *  f(c1r1, c2r2) ==> f(r1, r2)
					 */
					dp[i][j] = Math.min(dp[i][j - 1] + s2.charAt(j - 1), 
							dp[i - 1][j] + s1.charAt(i - 1));
				}

			}
		}
		return dp[s1.length()][s2.length()];
    }

    public static void main(String args[]) {
        MinAsciiDeleteSum wcm = new MinAsciiDeleteSum();
        String s1 = "sea";
        String s2 = "eat";
        
        System.out.println("Minimum ASCII delete sum for (" + s1 + ", " + s2 + ") : " 
        				+ wcm.minCost(s1, s2));
    }
}