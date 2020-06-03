package com.implement.recursion;

import java.util.Scanner;

// abcdef"123.;3.2.1"fed;cba
// Never a foot too far, evenm.

public class StringReverseByRecursion {
	private static void printArray(char[] result) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<result.length;i++)
			System.out.print(result[i]);
	}
	
	private static void swap(char[] strArray,int left, int right) {
		// TODO Auto-generated method stub
		char temp = strArray[left];
		strArray[left] = strArray[right];
		strArray[right] = temp;
	}
	
	static void inplaceReverse(char[] strArray, int left, int right){
		if(left < right){
			swap(strArray, left, right);
			inplaceReverse(strArray, left+1, right-1);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        scan.close();
        
        char[] inputArr = input.toCharArray();
        
        inplaceReverse(inputArr, 0, inputArr.length-1);
        printArray(inputArr);
	}

}
