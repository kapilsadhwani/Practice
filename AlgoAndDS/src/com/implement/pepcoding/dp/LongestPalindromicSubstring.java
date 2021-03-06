package com.implement.pepcoding.dp;

public class LongestPalindromicSubstring {
	/*
	 * Time complexity : O(n^2). This gives us a runtime complexity of O(n^2).
	 * Space complexity : O(n^2). It uses O(n^2) space to store the table.
	 */

	public static String longestPalindromicSubstrDP(String s) {
		int strLength= s.length();
		
		if(strLength == 0) return "";
		
		/* palindrome[i][j] will be false if substring str[i..j] is not palindrome
		 * Else table[i][j] will be true
		 */
		boolean palindrome[][] = new boolean[strLength][strLength];
		
		// All substrings of length 1 are palindromes 
        int maxLength = 1; 
        int palindromeBeginsAt = 0; // Index where the longest palindrome begins
		
		// Trivial case: Single letter palindromes
		for (int i=0; i<strLength; i++){
			palindrome[i][i]= true;		
		}
		
		// Finding palindrome of 2 characters
		for (int i=0; i<strLength-1; i++){
			if(s.charAt(i) == s.charAt(i+1)){
				palindrome[i][i+1] = true;
				
				if(maxLength < 2){
					palindromeBeginsAt = i;
					maxLength = 2;
				}
			}
		}
		
		// Finding palindromes of lengths greater than 2 and saving the longest
		for(int curr_len=3; curr_len <= strLength; curr_len++){
			
			// Fix the starting index
			for(int start=0; start<strLength-curr_len+1; start++){
				
				// Get ending os substring from start and current length
				int end = start + curr_len - 1;	
				
				if(s.charAt(start) == s.charAt(end) &&	// The first and last characters should match
						palindrome[start+1][end-1]){	// Rest of the substring should be a palindrome
					palindrome[start][end] = true;
					
					if(curr_len > maxLength){
						palindromeBeginsAt = start;
						maxLength = curr_len;
					}
				}
			}
		}
		
		return s.substring(palindromeBeginsAt, palindromeBeginsAt+maxLength);
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
					//if(g + 1 > len){
						len = g + 1;
						startIdx = i;
						endIdx = j;
					//}
				}
			}
		}

		System.out.println("Length of longest palindromic substring: " + len);
		return s.substring(startIdx, endIdx + 1);
	}
	
	/*
	 * Time complexity : O(n^2). This gives us a runtime complexity of O(n^2).
	 * Space complexity : O(1). It uses constant space.
	 */
	public static String longestPalindromeManachers(String s) {
	    if (s == null || s.length() < 1) return "";
	    int start = 0, end = 0;
	    for (int i = 0; i < s.length(); i++) {
	        int len1 = expandAroundCenter(s, i, i);
	        int len2 = expandAroundCenter(s, i, i + 1);
	        int len = Math.max(len1, len2);
	        if (len > end - start) {
	            start = i - (len - 1) / 2;
	            end = i + len / 2;
	        }
	    }
	    return s.substring(start, end + 1);
	}

	private static int expandAroundCenter(String s, int left, int right) {
	    int L = left, R = right;
	    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
	        L--;
	        R++;
	    }
	    return R - L - 1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "forgeeksskeegfor";
		String result = longestPalindromicSubstrDP(s);
		
		System.out.println("Longest palindrome substring is (DP - 1) : " + result);
		System.out.println("Longest palindrome substring is (DP - 2) : " + longestPalindromicSubstrDPII(s));
		
		result = longestPalindromeManachers(s);
		System.out.println("Using Constant Space : " + result);
	}

}
