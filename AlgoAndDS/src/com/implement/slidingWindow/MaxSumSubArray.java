package com.implement.slidingWindow;

/* 
 * Java program to print largest contiguous array sum of a fixed size K
 * Based on Sliding Window Technique
 */

class MaxSumSubArray {
	private static int maxSubArraySum(int[] a, int size) {
		int maxSum = Integer.MIN_VALUE, currentSum = 0;
		int max_start = 0, max_end = 0, current_start = 0;

		for (int i = 0; i < size; i++) {
			currentSum = currentSum + a[i];
			if (maxSum < currentSum) { // We found new max, adjust start and end accordingly
				maxSum = currentSum;
				max_start = current_start;
				max_end = i;
			}
			if (currentSum < 0) { // Start with new "start" index, as Sum is -ve
				currentSum = 0;
				current_start = i + 1;
			}
		}

		System.out.println("Maximum contiguous sum is " + maxSum);
		System.out.println("Starting index " + max_start);
		System.out.println("Ending index " + max_end);

		return maxSum;
	}
	
	private static int maxSubArraySumSizeK(int[] a, int size, int k) {
		int maxSum = Integer.MIN_VALUE, currentSum = 0;
		int windowStart = 0;

		for (int windowEnd = 0; windowEnd < size; windowEnd++) {
			currentSum = currentSum + a[windowEnd];
			if(windowEnd - windowStart + 1 == k){	// i.e window size = k
				maxSum = Math.max(maxSum, currentSum);
				currentSum = currentSum - a[windowStart];
				
				windowStart++;
			}
		}

		return maxSum;
	}

	static int maxSubArraySum(int nums[]) {
		int maxSum = nums[0];
		int currSum = nums[0];
		int maxStart = 0, maxEnd = 0;
		int currStart = 0;

		for (int i = 1; i < nums.length; i++) {
			if (currSum + nums[i] < nums[i]) {
				currSum = nums[i];
				currStart = i;
			} else {
				currSum = currSum + nums[i];
			}

			if (maxSum < currSum) {
				maxSum = currSum;
				maxStart = currStart;
				maxEnd = i;
			}
		}

		System.out.println("Maximum contiguous sum is " + maxSum);
		System.out.println("Starting index " + maxStart);
		System.out.println("Ending index " + maxEnd);
		return maxSum;
	}

	public static void main(String[] args) {
		int[] a = { -8, -7, -1, -3, -2 };
		maxSubArraySum(a, a.length);
		maxSubArraySum(a);

		int[] arr1 = { 8, -1, -3, 4, -1, 2, 1, -5, 4 };
		maxSubArraySum(arr1, arr1.length);
		maxSubArraySum(arr1);
		System.out.println("Max Sum of Window Size:3 is " + maxSubArraySumSizeK(arr1, arr1.length, 3));
		
		int[] arr2 = { 4, 2, 1, 7, 8, 1, 2, 8, 1, 0};
		maxSubArraySum(arr2, arr2.length);
		maxSubArraySum(arr2);
		System.out.println("Max Sum of Window Size:3 is " + maxSubArraySumSizeK(arr2, arr2.length, 3));
	}
}