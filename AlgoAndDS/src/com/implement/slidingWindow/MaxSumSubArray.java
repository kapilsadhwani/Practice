package com.implement.slidingWindow;

/* 
 * Java program to print largest contiguous array sum of a fixed size K
 * Based on Sliding Window Technique
 */

class MaxSumSubArray {
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
	
	private static int maxSubArraySumSizeAtLeastK(int[] a, int size, int k) {
		int ans = Integer.MIN_VALUE;
		int maxSum[] = new int[size];
		
		int currSum = a[0];
		maxSum[0] = currSum;
		
		for (int i = 1; i < size; i++) {
			if(currSum + a[i] < a[i]){
				currSum = a[i];
			}else{
				currSum = currSum + a[i];
			}
			
			maxSum[i] = currSum;
		}
		
		int windowStart = 0;
		int exactK = 0;

		for (int windowEnd = 0; windowEnd < size; windowEnd++) {
			exactK = exactK + a[windowEnd];
			
			if(windowEnd - windowStart + 1 == k){
				// Result size = k
				if(exactK > ans){
					ans = exactK;
				}
				
				// Result size > k
				if(windowStart > 0){			// windowEnd >= k
					if(exactK + maxSum[windowStart - 1] > ans){
						ans = exactK + maxSum[windowStart - 1];
					}
				}
				
				// Slide window
				exactK = exactK - a[windowStart];
				
				windowStart++;
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		int[] a = { -8, -7, -1, -3, -2 };
		maxSubArraySum(a);

		int[] arr1 = { 8, -1, -3, 4, -1, 2, 1, -5, 4 };
		int k = 3;

		maxSubArraySum(arr1);
		System.out.println("Max Sum of Window Size:3 is " + maxSubArraySumSizeK(arr1, arr1.length, k));
		
		int[] arr2 = { 4, 2, 1, 7, 8, 1, 2, 8, 1, 0 };
		maxSubArraySum(arr2);
		System.out.println("Max Sum of Window Size:3 is " + maxSubArraySumSizeK(arr2, arr2.length, k));
		
		System.out.println(" ================================================ ");
		System.out.println(" Maximum Sub Array Sum with at least size K ");
		System.out.println(" ================================================ ");
		
		System.out.println();
		
		int[] arr3 = { 2, 3, 1, -7, 6, -5, -4, 4, 3, 3, 2, -9, -5, 6, 1, 2, 1, 4 };
		k = 4;
		System.out.println(maxSubArraySumSizeAtLeastK(arr3, arr3.length, k));
	}
}