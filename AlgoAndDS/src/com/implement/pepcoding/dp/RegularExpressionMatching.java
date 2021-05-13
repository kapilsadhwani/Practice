package com.implement.pepcoding.dp;
/**
 *
 * Wild card matching with ? and *
 */
enum Result {
    TRUE, FALSE
}

public class RegularExpressionMatching {
	Result[][] memo;
	
	public boolean isMatchR(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                               (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatchR(text, pattern.substring(2)) ||
                    (first_match && isMatchR(text.substring(1), pattern)));
        } else {
            return first_match && isMatchR(text.substring(1), pattern.substring(1));
        }
    }
	
	public boolean isMatch(String text, String pattern) {
		memo = new Result[text.length() + 1][pattern.length() + 1];
		return dp(0, 0, text, pattern);
	}

	public boolean dp(int i, int j, String text, String pattern) {
		if (memo[i][j] != null) {
			return memo[i][j] == Result.TRUE;
		}
		boolean ans;
		if (j == pattern.length()) {
			ans = i == text.length();
		} else {
			boolean first_match = (i < text.length() && (pattern.charAt(j) == text
					.charAt(i) || pattern.charAt(j) == '.'));

			if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
				ans = (dp(i, j + 2, text, pattern) || first_match
						&& dp(i + 1, j, text, pattern));
			} else {
				ans = first_match && dp(i + 1, j + 1, text, pattern);
			}
		}
		memo[i][j] = ans ? Result.TRUE : Result.FALSE;
		return ans;
	}
	
    public boolean isMatchDP(String s, String p) {
        char[] text = s.toCharArray();
        char[] pattern = p.toCharArray();

        // Dim1 = string; Dim2 = pattern
        //boolean T[][] = new boolean[text.length + 1][pattern.length + 1];
        
        // Dim1 = pattern; Dim2 = string
        boolean dp[][] = new boolean[pattern.length + 1][text.length + 1];

		dp[0][0] = true;

		// Deals with patterns like a* or a*b* or a*b*c*
		
		// First Row, can be ignored
        for(int j = 1; j < dp[0].length; j++){
        	dp[0][j] = false;
        }

		// First Column
        for(int i = 1; i < dp.length; i++){
        	if(pattern[i - 1] == '*'){
        		// Look above i.e. exclude '*'
        		dp[i][0] = dp[i - 2][0];		// Exclude a*
        	}else{
        		dp[i][0] = false;
        	}
        }
		
		// dp[i][j] stores value for pattern[i-1] and text[j-1] 
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if (pattern[i - 1] == '.' || pattern[i - 1] == text[j - 1]) {
					// Look diagonally above
					dp[i][j] = dp[i - 1][j - 1];
				} else if (pattern[i - 1] == '*') {

					// Exclude a*
					dp[i][j] = dp[i - 2][j];

					// Include a* and check for smaller string
					// Only if pattern(i - 2) is '.' or same as text(j - 1)
					if (pattern[i - 2] == '.' || pattern[i - 2] == text[j - 1]) {
						dp[i][j] = dp[i][j] || dp[i][j - 1];
					}
				} else {
					dp[i][j] = false;
				}
			}
		}

		return dp[pattern.length][text.length];
    }

    public static void main(String args[]) {
        RegularExpressionMatching rem = new RegularExpressionMatching();
        System.out.println(rem.isMatchDP("aa", "a"));
        System.out.println(rem.isMatchDP("aa", "a*"));
        System.out.println(rem.isMatchDP("ab", ".*"));
        System.out.println(rem.isMatchDP("aab", "c*a*b"));
        System.out.println(rem.isMatchDP("mississipi", "mis*is*p*."));
    }
}