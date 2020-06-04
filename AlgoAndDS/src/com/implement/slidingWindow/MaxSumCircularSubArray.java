package com.implement.slidingWindow;

class MaxSumCircularSubArray {
	private static int maxSubArraySum(int[] nums) {
		int maxSum = nums[0], currentSum = nums[0];

		for (int i = 1; i < nums.length; i++) {
			currentSum = Math.max(nums[i], currentSum + nums[i]);
			maxSum = Math.max(maxSum, currentSum);
		}

		return maxSum;
	}

	public static int maxSubarraySumCircular(int[] nums) {
		int maxSum = maxSubArraySum(nums);
		
		int totalSum = 0;
		for (int i = 0; i < nums.length; i++) {
			totalSum = totalSum + nums[i];
			nums[i] = -nums[i];
		}
		
		int minSum = -maxSubArraySum(nums);
		
		// all elements in nums are negative:
		if (minSum == totalSum) {
			return maxSum;
		}
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