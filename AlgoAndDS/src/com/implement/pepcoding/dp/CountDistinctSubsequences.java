package com.implement.pepcoding.dp;

import java.util.HashMap;
import java.util.Map;

//Java program to count number of Subsequences of type a+b+c+

public class CountDistinctSubsequences {
	public static long countStrings(String str) {
		long[] dp = new long[str.length() + 1];
		
		// Empty string
		dp[0] = 1;

		Map<Character, Integer> lo = new HashMap<>();
		
		for (int i = 1; i < dp.length; i++) {
			dp[i] = 2 * dp[i - 1];
			
			char ch = str.charAt(i - 1);

			/* Remove duplicates arising from previous occurrence of this character
			 * count[i]  = 2 * count[i-1] - count[j-1]
			 * where j = previous occurrence of duplicate character
			 */
			if (lo.containsKey(ch)){
				int j = lo.get(ch);
				
				dp[i] = dp[i] - dp[j - 1];
			}
			
			lo.put(ch, i);
		}

		// Excluding empty string
		return dp[str.length()] - 1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "abcbac";
		
		System.out.println(countStrings(str));
	}

}
