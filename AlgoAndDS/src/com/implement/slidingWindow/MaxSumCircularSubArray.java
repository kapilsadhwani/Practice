package com.implement.slidingWindow;

class MaxSumCircularSubArray {	
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

	public static int maxSubarraySumCircular(int[] nums) {
		// Step1: Find MaxSum using Kadane's Algorithm
		int maxSum = kadane(nums);
		
		
		// Step2: Find Total Sum
		int totalSum = 0;
		for (int i = 0; i < nums.length; i++) {
			totalSum = totalSum + nums[i];
			nums[i] = -nums[i];
		}
		
		// Step3: Find MinSum using Kadane's Algorithm
		int minSum = -kadane(nums);
		
		// If all elements in nums are negative, return MaxSum
		if (minSum == totalSum) {
			return maxSum;
		}
		
		/*
		 *  Otherwise, return Max of Step1 and Total - Step3
		 *  MaxSum = TotalSum - MinSum 
		 */
		
		return Math.max(maxSum, totalSum - minSum);
	}

	public static void main(String[] args) {
		int[] nums = { 1, -2, 3};
		System.out.println(maxSubarraySumCircular(nums));

		nums = new int[] { 5, -3, 5 };
		System.out.println(maxSubarraySumCircular(nums));

		nums = new int[] { 3, -1, 2, -1 };
		System.out.println(maxSubarraySumCircular(nums));

		nums = new int[] { 3, -2, 2, -3 };
		System.out.println(maxSubarraySumCircular(nums));

		nums = new int[] { -2, -3, -1 };
		System.out.println(maxSubarraySumCircular(nums));
	}
}