package com.implement.pepcoding.dp;

/*
 * Count number of Subsequences of type a+b+c+
 * For abbc -> there are 3 subsequences. abc, abc, abbc
 * For abca'b'c' -> there are 7 subsequences. abcc', abc, abb'c', abc', aa'b'c', ab'c', a'b'c'.
 * Note: In the 2nd example (') is used only to denote different instance of a character. Input string
 * will not contain (') in it. 
 */

public class AplusBplusCplusSubsequences {
	public static int countStrings(String str) {
		if(str == null || str.length() == 0 || str.charAt(0) != 'a'){
			return 0;
		}
		
		int aCount = 1;
		int abCount = 0;
		int abcCount = 0;

		for (int i = 1; i < str.length(); i++) {
			char ch = str.charAt(i);

			if (ch == 'a') {
				// Exclude this occurrence of 'a' + 
				// Include this occurrence of 'a' with previous instances of 'a' strings + 
				// Exclude previous instances of 'a' strings and use only this instance of 'a' to start a new sequence
				aCount = aCount + aCount + 1;
			} else if (ch == 'b') {
				// Exclude this occurrence of 'b' + 
				// Include this occurrence of 'b' with previous instances of 'ab' strings + 
				// Exclude previous instances of 'ab' strings and use only this instance of 'b'
				// with instances of 'a' strings
				abCount = abCount + abCount + aCount;
			} else if (ch == 'c') {
				// Exclude this occurrence of 'c' + 
				// Include this occurrence of 'c' with previous instances of 'abc' strings + 
				// Exclude previous instances of 'abc' strings and use only this instance of 'c'
				// with instances of 'ab' strings
				abcCount = abcCount + abcCount + abCount;
			}
		}

		return abcCount;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "abcabcbbc";
		
		System.out.println(countStrings(str));
	}

}
