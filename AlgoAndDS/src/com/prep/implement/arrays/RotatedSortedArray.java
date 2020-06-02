package com.prep.implement.arrays;

import java.util.Arrays;

public class RotatedSortedArray {
	/**
	 * @param num : a rotated sorted array
	 * @return: the minimum number in the array
	 */
	public static int findMin(int[] nums) {
		// write your code here
		int low = 0, high = nums.length - 1, mid;

		while (low < high) {
			mid = low + (high - low) / 2;

			// Check if element (mid+1) is minimum element. Consider
			// the cases like {3, 4, 5, 1, 2}
			if (mid < high && nums[mid + 1] < nums[mid])
				return nums[mid + 1];

			// Check if mid itself is minimum element
			if (mid > low && nums[mid] < nums[mid - 1])
				return nums[mid];

			// Decide whether we need to go to left half or right half
			if (nums[mid] > nums[high]) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return nums[low];
	}

	/**
	 * @param num : a rotated sorted array
	 * @return: the maximum number in the array
	 */
	public static int findMax(int[] nums) {
		// write your code here
		int low = 0, high = nums.length - 1, mid;

		while (low < high) {
			// Find mid
			mid = low + (high - low) / 2;

			// Check if mid itself is maximum element
			if (mid < high && nums[mid] > nums[mid + 1]) {
				return nums[mid];
			}

			// Check if element at (mid - 1) is maximum element
			// Consider the cases like {4, 5, 1, 2, 3}
			if (mid > low && nums[mid - 1] > nums[mid]) {
				return nums[mid - 1];
			}

			// Decide whether we need to go to the left half or the right half
			if (nums[low] > nums[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return nums[low];

	}

	// Returns index of key in arr[l..h]
	// if key is present, otherwise returns -1
	static int search(int nums[], int target) {
		if(nums.length == 0) return -1;
		
		int low = 0, high = nums.length - 1, mid;

		while (low <= high) {
			// Find mid
			mid = low + (high - low) / 2;

			if (nums[mid] == target)
				return mid;

			/*
			 * Either the left or right half must be normally ordered. Find out
			 * which side is normally ordered, and then use the normally ordered
			 * half to figure out which side to search to find key
			 */

			if (nums[low] < nums[mid]) { // Left is normally ordered
				if (nums[low] <= target && target < nums[mid]) {
					high = mid - 1; // Search Left
				} else {
					low = mid + 1;
				}
			} else if (nums[low] > nums[mid]) { // Right is normally ordered
				if (nums[mid] < target && target <= nums[high]) {
					low = mid + 1; // Search Right
				} else {
					high = mid - 1;
				}
			} else {	// since no duplicates in input. Only happens when array is size <=2, so (lo == mid).
				low = mid + 1;	// search right, since A[lo] was already compared to 'target'.
			}
		}
		
		return -1;
	}
	
	public boolean searchHavingDup(int[] nums, int target) {
		int low = 0, high = nums.length - 1, mid;

		while (low <= high) {
			// Find mid
			mid = low + (high - low) / 2;

			if (nums[mid] == target)
				return true;

			/*
			 * Either the left or right half must be normally ordered. Find out
			 * which side is normally ordered, and then use the normally ordered
			 * half to figure out which side to search to find key
			 */

			if (nums[low] < nums[mid]) { // Left is normally ordered
				if (nums[low] <= target && target < nums[mid]) {
					high = mid - 1; // Search Left
				} else {
					low = mid + 1;
				}
			} else if (nums[low] > nums[mid]) { // Right is normally ordered
				if (nums[mid] < target && target <= nums[high]) {
					low = mid + 1; // Search Right
				} else {
					high = mid - 1;
				}
			} else { // This iteration of while loop did not divide problem in half.
				low++; // Removes just 1 invalid match.
			}
		}

		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int idx, max, min;
		
		int[] arr = { 12, 7, 8, 9, 9, 9, 10, 11, 11, 12 };
		min = findMin(arr);
		max = findMax(arr);
		idx = search(arr, 11);
		System.out.println(Arrays.toString(arr));
		System.out.print("Min: " + min + ", Max: " + max + ", Element:");
		System.out.println(arr[idx] + " was found at index " + idx + "\n");

		int arr1[] = { 5, 6, 1, 2, 3, 4 };
		min = findMin(arr1);
		max = findMax(arr1);
		idx = search(arr1, 6);
		System.out.println(Arrays.toString(arr1));
		System.out.print("Min: " + min + ", Max: " + max + ", Element:");
		System.out.println(arr1[idx] + " was found at index " + idx + "\n");
		
		int arr2[] = { 1, 2, 3, 4 };
		min = findMin(arr2);
		max = findMax(arr2);
		idx = search(arr2, 4);
		System.out.println(Arrays.toString(arr2));
		System.out.print("Min: " + min + ", Max: " + max + ", Element:");
		System.out.println(arr2[idx] + " was found at index " + idx + "\n");
		
		int arr3[] = { 1 };
		min = findMin(arr3);
		max = findMax(arr3);
		idx = search(arr3, 1);
		System.out.println(Arrays.toString(arr3));
		System.out.print("Min: " + min + ", Max: " + max + ", Element:");
		System.out.println(arr3[idx] + " was found at index " + idx + "\n");
		
		int arr4[] = { 1, 2 };
		min = findMin(arr4);
		max = findMax(arr4);
		idx = search(arr4, 2);
		System.out.println(Arrays.toString(arr4));
		System.out.print("Min: " + min + ", Max: " + max + ", Element:");
		System.out.println(arr4[idx] + " was found at index " + idx + "\n");
		
		int arr5[] = { 2, 1 };
		min = findMin(arr5);
		max = findMax(arr5);
		idx = search(arr5, 3);
		System.out.println(Arrays.toString(arr5));
		System.out.print("Min: " + min + ", Max: " + max + ", Element:");
		System.out.println("3 not found !!! Index returned is " + idx + "\n");
		
		int arr6[] = { 5, 6, 7, 1, 2, 3, 4 };
		min = findMin(arr6);
		max = findMax(arr6);
		idx = search(arr6, 5);
		System.out.println(Arrays.toString(arr6));
		System.out.print("Min: " + min + ", Max: " + max + ", Element:");
		System.out.println(arr6[idx] + " was found at index " + idx + "\n");
		
		int arr7[] = { 1, 2, 3, 4, 5, 6, 7 };
		min = findMin(arr7);
		max = findMax(arr7);
		idx = search(arr7, 7);
		System.out.println(Arrays.toString(arr7));
		System.out.print("Min: " + min + ", Max: " + max + ", Element:");
		System.out.println(arr7[idx] + " was found at index " + idx + "\n");
		
		int arr8[] = { 2, 3, 4, 5, 6, 7, 8, 1 };
		min = findMin(arr8);
		max = findMax(arr8);
		idx = search(arr8, 3);
		System.out.println(Arrays.toString(arr8));
		System.out.print("Min: " + min + ", Max: " + max + ", Element:");
		System.out.println(arr8[idx] + " was found at index " + idx + "\n");
		
		int arr9[] = { 5, 1, 2, 3, 4 };
		min = findMin(arr9);
		max = findMax(arr9);
		idx = search(arr9, 2);
		System.out.println(Arrays.toString(arr9));
		System.out.print("Min: " + min + ", Max: " + max + ", Element:");
		System.out.println(arr9[idx] + " was found at index " + idx + "\n");
		
		int[] arr10 = { 1, 1, 2, 3, 4, 7, 7, 7, 8, 9, 1 };
		min = findMin(arr10);
		max = findMax(arr10);
		idx = search(arr10, 7);
		System.out.println(Arrays.toString(arr10));
		System.out.print("Min: " + min + ", Max: " + max + ", Element:");
		System.out.println(arr10[idx] + " was found at index " + idx + "\n");
		
		int[] arr11 = { 4,5,6,7,0,1,2 };
		idx = search(arr11, 0);
		System.out.println(Arrays.toString(arr11));
		System.out.println(arr11[idx] + " was found at index " + idx + "\n");
		
		int[] arr12 = { 4,5,6,7,0,1,2 };
		idx = search(arr12, 3);
		System.out.println(Arrays.toString(arr12));
		System.out.println(" 3 not found !!! Index returned is " + idx + "\n");
	}

}
