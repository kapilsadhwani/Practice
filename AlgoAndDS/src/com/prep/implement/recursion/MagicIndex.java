package com.prep.implement.recursion;

public class MagicIndex {
	static int magicSlow(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == i) {
				return i;
			}
		}
		return -1;
	}

	static int magicFast(int[] array) {
		return magicFast(array, 0, array.length - 1);
	}

	// With Duplicates
	static int magicFast(int[] array, int start, int end) {
		if (end < start) {
			return -1;
		}
		int midIndex = (start + end) / 2;
		int midValue = array[midIndex];

		if (midValue == midIndex) {
			return midIndex;
		}

		/* Search left */
		int leftIndex = Math.min(midIndex - 1, midValue);
		int left = magicFast(array, start, leftIndex);
		if (left >= 0) {
			return left;
		}

		/* Search right */
		int rightIndex = Math.max(midIndex + 1, midValue);
		int right = magicFast(array, rightIndex, end);

		return right;
	}

	static int magicFastNoDup(int[] array, int start, int end) {
		if (end < start) {
			return -1;
		}
		int mid = (start + end) / 2;

		if (array[mid] == mid) {
			return mid;
		} else if (array[mid] > mid) {
			return magicFastNoDup(array, start, mid - 1);
		} else {
			return magicFastNoDup(array, mid + 1, end);
		}
	}

	static int magicIndexItr(int[] num) {
		int low = 0, high = num.length - 1, mid;

		while (low <= high) {
			mid = (low + high) / 2;
			
			if(num[mid] == mid) return mid;

			// Check if element (mid+1) is minimum element. Consider
			// the cases like {3, 4, 5, 1, 2}
			if (num[mid] > mid){
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
	  int[] arr = {-10, -5, 0, 3, 7};
	  System.out.println(magicFast(arr) + ", " + magicIndexItr(arr)); // arr[3] == 3

	  int[] arr1 = {0, 2, 5, 8, 17};
	  System.out.println(magicFast(arr1) + ", " + magicIndexItr(arr1)); // arr1[0] == 0 


	  int[] arr2 = {-10, -5, 3, 4, 7, 9};
	  System.out.println(magicFast(arr2) + ", " + magicIndexItr(arr2));  // No Fixed Point
	}
}
