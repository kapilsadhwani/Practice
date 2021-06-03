package com.implement.recursion;

import java.util.Scanner;
import java.util.TreeSet;

// abcdef"123.;3.2.1"fed;cba
//    Never a    foot  too     far, evenm.

public class WordReverseByRecursion {
	private static void swap(char[] strArray,int left, int right) {
		// TODO Auto-generated method stub
		char temp = strArray[left];
		strArray[left] = strArray[right];
		strArray[right] = temp;
	}
	
	static void inplaceReverse(char[] strArray, int left, int right){
		while(left < right){
			swap(strArray, left, right);
			left++;
			right--;
		}
	}
	
	public static String reverseWordWithNoLeadingOrTrailingSpaces(String word){
		char[] inputArr = word.toCharArray();
        
        inplaceReverse(inputArr, 0, inputArr.length-1);
        
        int start=0, end=0;
        
        while(end<inputArr.length){
        	if(inputArr[end] == ' '){
        		inplaceReverse(inputArr, start, end-1);
        		start = end+1;
        	}
        	
        	end++;
        }
        
        inplaceReverse(inputArr, start, end-1);	// To handle last part of string
        return new String(inputArr).trim();
	}
	
	public static String reverseWordWithLeadingOrTrailingSpaces(String word){
		int start = word.length()-1;
        StringBuilder result = new StringBuilder("");
        
        int end=word.length();
		
        while(start >= 0){
	        while(start >= 0 && word.charAt(start) == ' ') start--;
	        
	        if(start < 0) break;
	        
	        end = start + 1;
	        
	        while(start >= 0 && word.charAt(start) != ' ') start--;
	        
	        if(result.length() == 0){
	        	result.append(word.substring(start+1, end));	// end points to a non-space character
	        }else{
	        	result.append(" ");
	        	result.append(word.substring(start+1, end));
	        }
        }
        
        return result.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        scan.close();
        
        System.out.println(reverseWordWithNoLeadingOrTrailingSpaces(input));
        System.out.println(reverseWordWithLeadingOrTrailingSpaces(input));
	}

}
