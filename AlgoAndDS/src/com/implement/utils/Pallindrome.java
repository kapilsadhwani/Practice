package com.implement.utils;

public class Pallindrome {
	public static boolean isPallindrome(char[] letters){
	    int left = 0;
	    int right = letters.length - 1;
	    while (left < right) {
	        if (letters[left++] != letters[right--]) {
	            return false;
	        }
	    }
	    return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String word = "reliefpfpfeiler";
		String word = "areliefpfpfeilerc";
        char[] letters = word.toCharArray(); 
        System.out.println(isPallindrome(letters));
	}
}
