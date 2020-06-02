package com.prep.implement.dp;

import java.util.Arrays;

// Java Program for Maximum Index 

class MaximumIndex {
	// Based on Sliding Window
	static int solve(int arr[]) {
		int max = -1;
		int n = arr.length;

		for (int w = 1; w < n; w++) {
			for (int i = 0; i + w < n; i++) {
				int j = i + w;

				if (arr[j] >= arr[i]) {
					max = Math.max(max, j - i);
				}
			}
		}

		return max;
	}

	/*
	 * For a given array arr[], returns the maximum j-i such that arr[j] >= arr[i]
	 */
	static int maxIndexDiff(int arr[], int n) {
		int maxDiff;
		int i, j;

		int RMax[] = new int[n];
		int LMin[] = new int[n];

		/*
		 * Construct LMin[] such that LMin[i] stores the minimum value from
		 * (arr[0], arr[1], ... arr[i])
		 */
		LMin[0] = arr[0];
		for (i = 1; i < n; ++i)
			LMin[i] = Math.min(arr[i], LMin[i - 1]);

		/*
		 * Construct RMax[] such that RMax[j] stores the maximum value from
		 * (arr[j], arr[j+1], ..arr[n-1])
		 */
		RMax[n - 1] = arr[n - 1];
		for (j = n - 2; j >= 0; --j)
			RMax[j] = Math.max(arr[j], RMax[j + 1]);

		/*
		 * Traverse both arrays from left to right to find optimum j - i This
		 * process is similar to merge() of MergeSort
		 */
		i = 0;
		j = 0;
		maxDiff = -1;
		while (i < n && j < n) {
			if (LMin[i] < RMax[j]) {
				maxDiff = Math.max(maxDiff, j - i);
				j = j + 1;
			} else
				i = i + 1;
		}
		
		System.out.println("L - " + Arrays.toString(LMin));
		System.out.println("R - " + Arrays.toString(RMax));

		return maxDiff;
	}

	// Driver code
	public static void main(String args[]) {
		int arr[] = { 34, 8, 10, 3, 2, 80, 30, 33, 1 };
		
		System.out.println("O - " + Arrays.toString(arr));
		int max = maxIndexDiff(arr, arr.length);
		System.out.println(solve(arr) + ", " + max);

		int arr1[] = { 9, 2, 3, 4, 5, 6, 7, 8, 18, 0 };
		System.out.println("\nO - " + Arrays.toString(arr1));
		max = maxIndexDiff(arr1, arr1.length);
		System.out.println(solve(arr1) + ", " + max);
	}
}
