package com.prep.implement.arrays;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {
	// Time Complexity: O(n), Space Complexity: O(n)
	public static int[] productExceptSelf(int[] nums) {
		// The length of the input array
		int n = nums.length;

		// The left and right arrays as described in the algorithm
		int[] left = new int[n];
		int[] right = new int[n];

		// Final result array to be returned
		int[] result = new int[n];

		// L[i] contains the product of all the elements to the left
		// Note: for the element at index '0', there are no elements to the
		// left,nso L[0] would be 1
		left[0] = 1;
		for (int i = 1; i < n; i++) {
			// L[i - 1] already contains the product of elements to the left of 'i - 1'
			// Simply multiplying it with nums[i - 1] would give the product of all
			// elements to the left of index 'i'
			left[i] = nums[i - 1] * left[i - 1];
		}

		// R[i] contains the product of all the elements to the right
		// Note: for the element at index 'length - 1', there are no elements to
		// the right, so the R[length - 1] would be 1
		right[n-1] = 1;
		for (int i = n-2; i >= 0; i--) {

			// R[i + 1] already contains the product of elements to the right of 'i + 1'
			// Simply multiplying it with nums[i + 1] would give the product of all
			// elements to the right of index 'i'
			right[i] = nums[i + 1] * right[i + 1];
		}

		// Constructing the answer array
		for (int i = 0; i<n; i++) {
			// For the first element, product except self would be R[i]
			// For the last element of the array, product except self would be L[i]
			// Else, multiple product of all elements to the left and to the right
			result[i] = left[i] * right[i];
		}

		return result;
	}
	
	// Time Complexity: O(n), Space Complexity: O(n)
	public static int[] productExceptSelfInConstantSpace(int[] nums) {

		// The length of the input array
		int length = nums.length;

		// Final answer array to be returned
		int[] answer = new int[length];

		// answer[i] contains the product of all the elements to the left
		// Note: for the element at index '0', there are no elements to 
		// the left, so the answer[0] would be 1
		answer[0] = 1;
		for (int i = 1; i < length; i++) {
			// answer[i - 1] already contains the product of elements to the
			// left of 'i - 1'
			// Simply multiplying it with nums[i - 1] would give the product of
			// all
			// elements to the left of index 'i'
			answer[i] = nums[i - 1] * answer[i - 1];
		}

		// R contains the product of all the elements to the right
		// Note: for the element at index 'length - 1', there are no elements to
		// the right, so the R would be 1
		int temp = 1;
		for (int i = length - 1; i >= 0; i--) {
			// For the index 'i', R would contain the
			// product of all elements to the right. We update R accordingly
			answer[i] = answer[i] * temp;
			temp = temp * nums[i];
		}

		return answer;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1,2,3,4};
		int[] result = productExceptSelf(nums);
		
		System.out.println(Arrays.toString(result));
		
		result = productExceptSelfInConstantSpace(nums);
		System.out.println(Arrays.toString(result));
	}

}
