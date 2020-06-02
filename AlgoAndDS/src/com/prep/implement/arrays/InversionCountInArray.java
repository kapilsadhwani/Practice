package com.prep.implement.arrays;

import java.util.Arrays;

// Java implementation of the approach 
import com.prep.implement.sort.SortUtils;

public class InversionCountInArray {

	// Function to count the number of inversions
	// during the merge process
	private static int mergeAndCount(int[] arr, int l, int m, int r, int[] temp) {
		int left = l, right = m + 1, k = 0;
		int swaps = 0;

		while (left <= m && right <= r) {
			if (arr[left] <= arr[right]) {
				temp[k++] = arr[left++];
			} else {
				temp[k++] = arr[right++];
				swaps = swaps + (m - l + 1 - left);
			}
		}

		while (left <= m) {
			temp[k++] = arr[left++];
		}
		while (right <= r) {
			temp[k++] = arr[right++];
		}

		SortUtils.copyArray(temp, arr, l, r);

		return swaps;
	}

	// Merge sort function
	private static int mergeSortAndCount(int[] arr, int l, int r, int[] temp) {

		// Keeps track of the inversion count at a
		// particular node of the recursion tree
		int count = 0;
		
		if(l >= r) return count;

		int m = l + (r - l) / 2;

		// Total inversion count = left subarray count
		// + right subarray count + merge count

		// Left subarray count
		count += mergeSortAndCount(arr, l, m, temp);

		// Right subarray count
		count += mergeSortAndCount(arr, m + 1, r, temp);

		// Merge count
		count += mergeAndCount(arr, l, m, r, temp);

		return count;
	}

	// Driver code
	public static void main(String[] args) {
		int[] arr = { 1, 20, 6, 4, 5 };
		int[] temp = new int[arr.length];

		System.out.println(Arrays.toString(arr) + " - "
				+ mergeSortAndCount(arr, 0, arr.length - 1, temp));

		int[] arr1 = { 1, 2, 3, 4, 5 };
		System.out.println(Arrays.toString(arr1) + " - "
				+ mergeSortAndCount(arr1, 0, arr1.length - 1, temp));

		int[] arr2 = { 5, 4, 3, 2, 1 };
		System.out.println(Arrays.toString(arr2) + " - "
				+ mergeSortAndCount(arr2, 0, arr2.length - 1, temp));
	}
}