package com.prep.implement.dp;
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
        int writeIndex = 0;
        boolean isFirst = true;
        for ( int i = 0 ; i < pattern.length; i++) {
            if (pattern[i] == '*') {
            	if(isFirst){
            		pattern[writeIndex++] = pattern[i];
            		isFirst = false;
            	}
            } else {
                pattern[writeIndex++] = pattern[i];
                isFirst = true;
            }
        }

        // Dim1 = string; Dim2 = pattern
        boolean T[][] = new boolean[str.length + 1][writeIndex + 1];

        T[0][0] = true;

        if (writeIndex > 0 && pattern[0] == '*') {
            T[0][1] = true;
        }

        for (int i = 1; i < T.length; i++) {			// or i <= str.length()
            for (int j = 1; j < T[0].length; j++) {		// or j <= writeIndex
                if (str[i-1] == pattern[j-1]			// [i-1][j-1] because i,j starting from 1
                		|| pattern[j-1] == '?') {
                    T[i][j] = T[i-1][j-1];
                } else if (pattern[j-1] == '*'){
                    T[i][j] = T[i-1][j] || 			
                    		T[i][j-1];
                }
            }
        }
        
        // For Debugging
        /*for (int i = 0; i < T.length; i++) {
            for (int j = 0; j < T[0].length; j++) {
                System.out.print(T[i][j] + "\t");
            }
            System.out.println();
        }
        
        System.out.println();
        System.out.println();*/

        return T[str.length][writeIndex];
    }

    public static void main(String args[]) {
        WildCardMatching wcm = new WildCardMatching();
        System.out.println(wcm.isMatch("xaylmz", "x?y*z"));
        System.out.println(wcm.isMatch("", "?"));
    }
}