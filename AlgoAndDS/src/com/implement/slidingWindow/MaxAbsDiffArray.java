package com.implement.slidingWindow;


// Java program to find two non-overlapping contiguous sub-arrays such that the 
// absolute difference is maximum

class MaxAbsDiffArray {

	// Find maximum subarray sum for subarray
	// [0..i] using standard Kadane's algorithm.
	// This version of Kadane's Algorithm will
	// work if all numbers are negative.
	static int maxLeftSubArraySum(int a[], int size, int sum[]) {
		int max_so_far = a[0];
		int curr_max = a[0];
		sum[0] = max_so_far;

		for (int i = 1; i < size; i++) {
			curr_max = Math.max(a[i], curr_max + a[i]);
			max_so_far = Math.max(max_so_far, curr_max);
			sum[i] = max_so_far;
		}

		return max_so_far;
		
		/*int maxSum = Integer.MIN_VALUE, currentSum = 0;
		
		for (int i = 0; i < size; i++) {
			currentSum = currentSum + a[i];
			maxSum = Math.max(maxSum, currentSum);
			currentSum = currentSum < 0 ? 0 : currentSum;
			sum[i] = maxSum;
		}
		
		return maxSum;*/
	}

	// Find maximum subarray sum for subarray [i..n]
	// using Kadane's algorithm. This version of Kadane's
	// Algorithm will work if all numbers are negative
	static int maxRightSubArraySum(int a[], int size, int sum[]) {
		int max_so_far = a[size-1];
		int curr_max = a[size-1];
		sum[size-1] = max_so_far;

		for (int i = size - 2; i >= 0; i--) {
			curr_max = Math.max(a[i], curr_max + a[i]);
			max_so_far = Math.max(max_so_far, curr_max);
			sum[i] = max_so_far;
		}

		return max_so_far;
		
		/*int maxSum = Integer.MIN_VALUE, currentSum = 0;
		
		for (int i = size-1; i >= 0; i--) {
			currentSum = currentSum + a[i];
			maxSum = Math.max(maxSum, currentSum);
			currentSum = currentSum < 0 ? 0 : currentSum;
			sum[i] = maxSum;
		}
		
		return maxSum;*/
	}

	// The function finds two non-overlapping contiguous
	// sub-arrays such that the absolute difference
	// between the sum of two sub-array is maximum.
	static int findMaxAbsDiff(int arr[], int n) {
		// create and build an array that stores
		// maximum sums of subarrays that lie in
		// arr[0...i]
		int leftMax[] = new int[n];
		maxLeftSubArraySum(arr, n, leftMax);

		// create and build an array that stores
		// maximum sums of subarrays that lie in
		// arr[i+1...n-1]
		int rightMax[] = new int[n];
		maxRightSubArraySum(arr, n, rightMax);

		// Invert array (change sign) to find minumum
		// sum subarrays.
		int invertArr[] = new int[n];
		for (int i = 0; i < n; i++)
			invertArr[i] = -arr[i];

		// create and build an array that stores
		// minimum sums of subarrays that lie in
		// arr[0...i]
		int leftMin[] = new int[n];
		maxLeftSubArraySum(invertArr, n, leftMin);
		for (int i = 0; i < n; i++)
			leftMin[i] = -leftMin[i];

		// create and build an array that stores
		// minimum sums of subarrays that lie in
		// arr[i+1...n-1]
		int rightMin[] = new int[n];
		maxRightSubArraySum(invertArr, n, rightMin);
		for (int i = 0; i < n; i++)
			rightMin[i] = -rightMin[i];
		
		/*System.out.println("MxLt: " + Arrays.toString(leftMax));
		System.out.println("MnRt: " + Arrays.toString(rightMin));
		
		System.out.println("MxRt: " + Arrays.toString(rightMax));
		System.out.println("MnLt: " + Arrays.toString(leftMin));*/
		
		int ans = -2147483648;
		for (int i = 0; i < n - 1; i++) {

			/*
			 * For each index i, take maximum of 1. abs(max sum subarray that
			 * lies in arr[0...i] - min sum subarray that lies in
			 * arr[i+1...n-1]) 2. abs(min sum subarray that lies in arr[0...i] -
			 * max sum subarray that lies in arr[i+1...n-1])
			 */
			int tempAns = Math.max(Math.abs(leftMax[i] - rightMin[i + 1]),
					Math.abs(leftMin[i] - rightMax[i + 1]));
			if (tempAns > ans)
				ans = tempAns;
		}

		return ans;
	}

	// driver code
	public static void main(String[] args) {
		int arr1[] = { -2, -3, 4, -1, -2, 1, 5, -3 };
		int n = arr1.length;
		
		System.out.println(findMaxAbsDiff(arr1, n));
		
		int arr2[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		n = arr2.length;
		
		System.out.println(findMaxAbsDiff(arr2, n));
		
		int arr3[] = { -8, -7, -1, -3, -2 };
		n = arr3.length;
		
		System.out.println(findMaxAbsDiff(arr3, n));
	}
}
