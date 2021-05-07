package com.implement.pepcoding.dp;

/*
 * You are given a string of digits. (will never start with a 0)
 * You are required to decode the string as per following rules
    1 -> a
    2 -> b
    3 -> c
    ..
    25 -> y
    26 -> z
 * Count the number of decodings for a given string.
 */

public class DecodeWays {
	public int numDecodings(String s) {
		if (s == null || s.length() == 0)
			return 0;

		int[] dp = new int[s.length()];

		// Single digit (1 - 9)
		dp[0] = 1;		// Trivial case

		for (int i = 1; i < dp.length; i++) {
			// case 00
			if (s.charAt(i - 1) == '0' && s.charAt(i) == '0') {
				dp[i] = 0;
			} 
			// case 05
			else if (s.charAt(i - 1) == '0' && s.charAt(i) != '0') {
				dp[i] = dp[i - 1];
			} 
			// case 20, 50
			else if (s.charAt(i - 1) != '0' && s.charAt(i) == '0') {
				// case 20
				if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2') {
					dp[i] = i >= 2 ? dp[i - 2] : 1;
				} else {
					// case 50
					dp[i] = 0;
				}
			} else {
				// case 25, 55
				dp[i] = dp[i - 1];
				
				// case 25
				if (Integer.parseInt(s.substring(i - 1, i + 1)) <= 26) {
					dp[i] = dp[i] + (i >= 2 ? dp[i - 2] : 1);
				}
			}
		}

		return dp[s.length() - 1];
	}
	
	public static void main(String[] args) {
		DecodeWays dw = new DecodeWays();
		
		String str = "12";
		System.out.println(dw.numDecodings(str));
		
		str = "226";
		System.out.println(dw.numDecodings(str));
		
		str = "2316";
		System.out.println(dw.numDecodings(str));
		
		str = "21123";
		System.out.println(dw.numDecodings(str));
	}
}