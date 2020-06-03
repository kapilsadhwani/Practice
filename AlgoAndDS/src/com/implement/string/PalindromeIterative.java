package com.implement.string;

import java.util.Scanner;

public class PalindromeIterative {
	static boolean isAlphanumericChar(char ch){
		return (ch >= 'a' && ch <= 'z') ||
				(ch >= 'A' && ch <= 'Z') || 
				(ch >= '0' && ch <= '9');
	}
	
	static boolean isEqualTo(char ch1, char ch2){	
		if(ch1>= 'a' && ch1 <= 'z'){
			return (ch1 == ch2 || ch1 == ch2-'A'+'a');
		}
		
		if(ch1>= 'A' && ch1 <= 'Z'){
			return (ch1 == ch2 || ch1 == ch2-'a'+'A');
		}
		
		return ch1 == ch2;
	}
	
	static boolean isPalindrome(char[] charStr){
		if(charStr.length < 2) return true;
		
		int left=0, right=charStr.length-1;
		
		while(!isAlphanumericChar(charStr[left])){
			left++;
			if(left == right) return true;
		}
		while(!isAlphanumericChar(charStr[right])){
			right--;
			if(left == right) return true;
		}
		
		while(left < right){
			if(isAlphanumericChar(charStr[left]) && isAlphanumericChar(charStr[right])){
				if(!isEqualTo(charStr[left],charStr[right])){
					return false;	
				}
				
				left++;
				right--;
			}else if(!isAlphanumericChar(charStr[left]) && !isAlphanumericChar(charStr[right])){
				left++;
				right--;			
			}else if(!isAlphanumericChar(charStr[left])){
				left++;
			}else{
				right--;
			}
		}
		
		return true;
	}

	/* n+=-it^^^i%%%n
	 * I am :IronnorI Ma, i
	 * Ab?/Ba
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		scan.close();

		// Convert input String to an array of characters:
		char[] charStr = input.toCharArray();

		//Finally, print whether string s is palindrome or not.
		System.out.println( "The word [" + input + "] is " 
				+ (isPalindrome(charStr) ? " a palindrome" : " not a palindrome" ) );
	}
}
