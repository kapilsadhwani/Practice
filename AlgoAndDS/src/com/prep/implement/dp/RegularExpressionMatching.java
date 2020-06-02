package com.prep.implement.dp;
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
        boolean T[][] = new boolean[text.length + 1][pattern.length + 1];

        T[0][0] = true;
        
        // Deals with patterns like a* or a*b* or a*b*c*

        for (int i = 1; i < T[0].length; i++) {
            if (pattern[i-1] == '*'){
            	T[0][i] = T[0][i-2];
            }
        }

        // T[i][j] stores value for text[i-1] and pattern[j-1]
        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[0].length; j++) {
                if (pattern[j-1] == '.' || text[i-1] == pattern[j-1]) {
                    T[i][j] = T[i-1][j-1];
                } else if (pattern[j-1] == '*'){
                    T[i][j] = T[i][j-2];
                    
                    if(pattern[j-2] == '.' || text[i-1] == pattern[j-2]){
                    	 T[i][j] = T[i][j] || T[i-1][j];
                    }
                } else{
                	T[i][j] = false;
                }
            }
        }

        return T[text.length][pattern.length];
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