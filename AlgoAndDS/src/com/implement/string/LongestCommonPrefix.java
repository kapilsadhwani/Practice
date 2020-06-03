package com.implement.string;

import java.util.Arrays;

public class LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
	    if (strs == null || strs.length == 0) return "";    
	        return longestCommonPrefix(strs, 0 , strs.length - 1);
	}

	private String longestCommonPrefix(String[] strs, int l, int r) {
	    if (l == r) {
	        return strs[l];
	    }
	    else {
	        int mid = (l + r)/2;
	        String lcpLeft =   longestCommonPrefix(strs, l , mid);
	        String lcpRight =  longestCommonPrefix(strs, mid + 1,r);
	        return commonPrefix(lcpLeft, lcpRight);
	   }
	}

	String commonPrefix(String left,String right) {
	    int min = Math.min(left.length(), right.length());       
	    for (int i = 0; i < min; i++) {
	        if ( left.charAt(i) != right.charAt(i) )
	            return left.substring(0, i);
	    }
	    return left.substring(0, min);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LongestCommonPrefix lcp = new LongestCommonPrefix();
		
		String[] strs = new String[]{"geeksforgeeks","geeks","geek", "geezer"};
		System.out.println(" Longest common prefix of " + Arrays.toString(strs) + " : " + lcp.longestCommonPrefix(strs));
		
		strs = new String[]{"dog","racecar","car"};
		System.out.println(" Longest common prefix of " + Arrays.toString(strs) + " : " + lcp.longestCommonPrefix(strs));
		
		strs = new String[]{"leets","leetcode","leetc","leeds"};
		System.out.println(" Longest common prefix of " + Arrays.toString(strs) + " : " + lcp.longestCommonPrefix(strs));
	}

}
