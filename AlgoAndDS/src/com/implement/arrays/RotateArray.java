package com.implement.arrays;

import java.util.Arrays;
import java.util.Scanner;

public class RotateArray {
	// First reverse all elements ie 0 to n-1
	// Second reverse elements 0 to k-1
	// Third reverse elements k to n-1
	private static void rightRotate(int[] nums, int k) {
		if (nums.length == 0) return;

		k = k % nums.length;

		if (k == 0) return;

		int left = 0, right = nums.length - 1, tmp;

		while (left < right) {
			tmp = nums[left];
			nums[left] = nums[right];
			nums[right] = tmp;

			left++;
			right--;
		}

		left = 0;
		right = k - 1;

		while (left < right) {
			tmp = nums[left];
			nums[left] = nums[right];
			nums[right] = tmp;

			left++;
			right--;
		}

		left = k;
		right = nums.length - 1;

		while (left < right) {
			tmp = nums[left];
			nums[left] = nums[right];
			nums[right] = tmp;

			left++;
			right--;
		}
	}

	private static void leftRotate(int[] nums, int k) {
		if (nums.length == 0) return;
        
		k = k % nums.length;
        
        if (k == 0) return;
        
        int left = 0, right = nums.length - 1, tmp;

		while (left < right) {
			tmp = nums[left];
			nums[left] = nums[right];
			nums[right] = tmp;

			left++;
			right--;
		}

		left = 0;
		right = nums.length - k - 1;

		while (left < right) {
			tmp = nums[left];
			nums[left] = nums[right];
			nums[right] = tmp;

			left++;
			right--;
		}

		left = nums.length - k;
		right = nums.length - 1;

		while (left < right) {
			tmp = nums[left];
			nums[left] = nums[right];
			nums[right] = tmp;

			left++;
			right--;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		try {
			int inputSize = scan.nextInt();
			int numOfRotations = scan.nextInt();
			
			if(inputSize < 1 || numOfRotations < 1) return;
			
			int[] input = new int[inputSize];
			
			for(int i=0; i<inputSize; i++){
				input[i] = scan.nextInt();
			}
			
			leftRotate(input, numOfRotations);
			
			System.out.println("Left rotate --> " + Arrays.toString(input));
			
			System.out.println("------------------------------------");
			
			rightRotate(input, numOfRotations);
			
			System.out.println("Right rotate --> " + Arrays.toString(input));
			
			System.out.println("------------------------------------");
			
			rightRotate(input, numOfRotations);
			
			System.out.println("Right rotate --> " + Arrays.toString(input));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			scan.close();
		}
	}
}
