package com.implement.slidingWindow;

class MaxSumCircularSubArray {	
	private static int maxSubArraySum(int[] nums) {
		int maxSum = Integer.MIN_VALUE, currentSum = 0;

		for (int num : nums) {
			currentSum = currentSum + num;
			if (maxSum < currentSum) { // We found new max, adjust start and end accordingly
				maxSum = currentSum;
			}
			if (currentSum < 0) {
				currentSum = 0;
			}
		}

		return maxSum;
	}

	public static int maxSubarraySumCircular(int[] nums) {
		// Step1: Find MaxSum using Kadane's Algorithm
		int maxSum = maxSubArraySum(nums);
		
		// Step2: Find Total Sum
		int totalSum = 0;
		for (int i = 0; i < nums.length; i++) {
			totalSum = totalSum + nums[i];
			nums[i] = -nums[i];
		}
		
		// Step3: Find MinSum using Kadane's Algorithm
		int minSum = -maxSubArraySum(nums);
		
		// If all elements in nums are negative, return MaxSum
		if (minSum == totalSum) {
			return maxSum;
		}
		
		// Otherwise, return Max of Step1 and Total - Step3 
		return Math.max(maxSum, totalSum - minSum);
	}

	public static void main(String[] args) {
		int[] nums = { 1, -2, 3, -2 };
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