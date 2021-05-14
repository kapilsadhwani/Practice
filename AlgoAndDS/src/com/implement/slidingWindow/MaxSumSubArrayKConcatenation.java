package com.implement.slidingWindow;

import java.util.Arrays;

/* 
 * Java program to print largest contiguous array sum of a fixed size K
 * Based on Sliding Window Technique
 */

class MaxSumSubArrayKConcatenation {	
	private static int kadane(int nums[]) {
		int maxSum = nums[0];
		int currSum = nums[0];

		for (int i = 1; i < nums.length; i++) {
			if (currSum + nums[i] < nums[i]) {
				currSum = nums[i];
			} else {
				currSum = currSum + nums[i];
			}

			if (maxSum < currSum) {
				maxSum = currSum;
			}
		}
		return maxSum;
	}
	
	private static int kadaneOfTwo(int nums[]) {
		int[] narr = new int[nums.length * 2];

		for (int i = 0; i < nums.length; i++) {
			narr[i] = nums[i];
		}

		for (int i = 0; i < nums.length; i++) {
			narr[i + nums.length] = nums[i];
		}

		return kadane(narr);
	}
	
	private static int maxSumKConcat(int nums[], int k) {
		int sum = 0;

		for (int num : nums) {
			sum = sum + num;
		}

		if (k == 1) {
			return kadane(nums);
		} else if (sum < 0) {
			return kadaneOfTwo(nums);
		} else {
			return kadaneOfTwo(nums) + (k - 2) * sum;
		}
	}

	public static void main(String[] args) {
		int[] arr = { -8, -7, -1, -3, -2 };
		System.out.println(Arrays.toString(arr) + " : " + maxSumKConcat(arr, 3));

		int[] arr1 = { 8, -1, -3, 4, -1, -2, 1, -5, 4 };
		System.out.println(Arrays.toString(arr1) + " : " + maxSumKConcat(arr1, 3));
		
		int[] arr2 = { 4, 2, 1, 7, 8, 1, 2, 8, 1, 0};
		System.out.println(Arrays.toString(arr2) + " : " + maxSumKConcat(arr2, 3));
	}
}