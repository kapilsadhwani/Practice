package com.prep.implement.arrays;

import java.util.Scanner;

public class LeftRotateArray {
	
	// First reverse all elements ie 0 to n-1
	// Second reverse elements 0 to k-1
	// Third reverse elements k to n-1
	private static void rightRotate(int[] nums, int k) {
	        if (nums.length == 0) return;
	        
	        k %= nums.length;
	        
	        if (k == 0) return;
	        
	        int left=0, right=nums.length-1, tmp;
	        
	        while(left < right) {
	            tmp = nums[left];
	            nums[left] = nums[right];
	            nums[right] = tmp;
	            
	            left++;
	            right--;
	        }
	        
	        left=0;
	        right=k-1;
	        
	        while(left < right) {
	        	tmp = nums[left];
	        	nums[left] = nums[right];
	        	nums[right] = tmp;
	    	            
	        	left++;
	        	right--;
	        }
	        
	        left=k;
	        right=nums.length-1;
	        
	        while(left < right) {
	        	tmp = nums[left];
	        	nums[left] = nums[right];
	        	nums[right] = tmp;
	    	            
	        	left++;
	        	right--;
	        }
	    }

	private static void leftRotate(int[] nums, int numOfLeftRotations) {
		if (nums.length == 0) return;
        
		numOfLeftRotations = numOfLeftRotations % nums.length;
        
        if (numOfLeftRotations == 0) return;
		
		int numOfRightRotations = nums.length - numOfLeftRotations;
		
		rightRotate(nums, numOfRightRotations);
	}
	
	private static void printArray(int[] input) {
		System.out.print(input[0]);
		
		for(int i=1; i<input.length; i++){
			System.out.print(" " + input[i]);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		try {
			int inputSize = scan.nextInt();
			int numOfLeftRotations = scan.nextInt();
			
			if(inputSize < 1 || numOfLeftRotations < 1 || inputSize < numOfLeftRotations) return;
			
			int[] input = new int[inputSize];
			
			for(int i=0; i<inputSize; i++){
				input[i] = scan.nextInt();
			}
			
			leftRotate(input, numOfLeftRotations);
			
			printArray(input);
			
			System.out.println("");
			
			rightRotate(input, numOfLeftRotations);
			
			printArray(input);
			
			System.out.println("");
			
			rightRotate(input, numOfLeftRotations);
			
			printArray(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			scan.close();
		}
	}
}
