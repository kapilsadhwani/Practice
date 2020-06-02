package com.prep.implement.recursion;

import java.util.Scanner;

// abcdef"123.;3.2.1"fed;cba
// Never a foot too far, evenm.

public class Palindrome {
	static boolean isChar(char ch){
		return (ch >= 'a' && ch <= 'z') ||
				(ch >= 'A' && ch <= 'Z');
	}
	
	static boolean isPalindrome(char[] inpChar, int left, int right){
		if(left < right){
			if(!isChar(inpChar[left]) && 
					!isChar(inpChar[right])){
				return isPalindrome(inpChar, left+1, right-1);
			}
			
			if(!isChar(inpChar[left])){
				return isPalindrome(inpChar, left+1, right);
			}
			
			if(!isChar(inpChar[right])){
				return isPalindrome(inpChar, left, right-1);
			}
			
			return (inpChar[left] == inpChar[right]) && isPalindrome(inpChar, left+1, right-1);
		}
		
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        scan.close();
        
        char[] inputChar = input.toLowerCase().toCharArray();
        
        int left=0, right=inputChar.length-1;
        
        boolean isStrPalindrome = false;
        
        while(!isChar(inputChar[left])){
        	left++;
        }
        
        while(!isChar(inputChar[right])){
        	right--;
        }
        
        isStrPalindrome = isPalindrome(inputChar,left,right);
        
        System.out.println(isStrPalindrome);
	}

}
