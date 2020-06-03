package com.implement.recursion;

import java.util.Scanner;

public class SubstringByRecursion {
	
	static void substring(char[] input,int pos,char[] result,int r_size){
		if(pos == input.length){
			printArray(result,r_size);
			return;
		}
		
		/*
		 * Print everything to left of the tree
		 * i.e Do not select the element
		 */
		substring(input,pos+1,result,r_size);
		
		/*
		 * Select the element
		 */
		result[r_size] = input[pos];
		substring(input, pos+1, result, r_size+1);
	}
	
	static void printArray(char[] input,int size){
		for (int i = 0; i < size; i++) {
			System.out.print( input[i] );
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        scan.close();
        
        if(input == null || input.trim().length() == 0) return;
        
        char[] inputChar = input.toCharArray();
        char[] result = new char[inputChar.length];
        
        substring(inputChar, 0, result, 0);        
	}

}
