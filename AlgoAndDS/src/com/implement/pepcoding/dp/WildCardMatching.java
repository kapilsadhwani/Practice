package com.implement.pepcoding.dp;
/**
 *
 * Wild card matching with ? and *
 */
public class WildCardMatching {    
    public boolean isMatch(String s, String p) {
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();

        //replace multiple * with one *
        //e.g a**b***c --> a*b*c
        int patternIndex = 0;
        boolean isFirst = true;
        for ( int i = 0 ; i < pattern.length; i++) {
            if (pattern[i] == '*') {
            	if(isFirst){
            		pattern[patternIndex++] = pattern[i];
            		isFirst = false;
            	}
            } else {
                pattern[patternIndex++] = pattern[i];
                isFirst = true;
            }
        }

        // Dim1 = pattern; Dim2 = string
        boolean dp[][] = new boolean[patternIndex + 1][str.length + 1];
        
        // String of zero length [j] and pattern of zero length [i]
        dp[0][0] = true;
        
        // First Row, can be ignored
        for(int j = 1; j < dp[0].length; j++){
        	dp[0][j] = false;
        }
        
        // First Column
        for(int i = 1; i < dp.length; i++){
        	if(pattern[i - 1] == '*'){
        		// Look above i.e. exclude '*'
        		dp[i][0] = dp[i - 1][0];
        	}else{
        		dp[i][0] = false;
        	}
        }
        
        // dp[i][j] stores value for pattern[i-1] and str[j-1]
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if (pattern[i - 1] == '?' || pattern[i - 1] == str[j - 1]) {
					// Look diagonally above
					dp[i][j] = dp[i - 1][j - 1];
				} else if (pattern[i - 1] == '*') {
					dp[i][j] = dp[i - 1][j] || 		// Exclude pattern (*)
							   dp[i][j - 1];		// Include pattern (*) and check for smaller string
				} else {
					dp[i][j] = false;
				}

			}
		}

        return dp[patternIndex][str.length];
    }

    public static void main(String args[]) {
        WildCardMatching wcm = new WildCardMatching();
        String str = "xaylmz";
        String p = "x?y*z";
        
        System.out.println("Is Match (" + str + ", " + p + ") ? --> "
        		+ "Is Match: " + wcm.isMatch(str, p));
        
        System.out.println("================================================================");
        
        str = "";
        p = "?";
        
        System.out.println("Is Match (" + str + ", " + p + ") ? --> "
        		+ "Is Match: " + wcm.isMatch(str, p));
        
        System.out.println("================================================================");
        
        str = "baaabab";
        p = "ba*a?";
        
        System.out.println("Is Match (" + str + ", " + p + ") ? --> "
        		+ "Is Match: " + wcm.isMatch(str, p));
        
        System.out.println("================================================================");
        
        str = "baaabab";
        p = "ba*b?*";
        
        System.out.println("Is Match (" + str + ", " + p + ") ? --> "
        		+ "Is Match: " + wcm.isMatch(str, p));
    }
}