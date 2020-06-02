package com.prep.implement.arrays;

public class MinMaxInRotatedSortedArray {
	/**
	 * @param num : a rotated sorted array
	 * @return: the minimum number in the array
	 */
	public static int findMin(int[] num) {
		// write your code here
		int low = 0, high = num.length - 1, mid;

		while (low < high) {
			mid = (low + high) / 2;

			// Check if element (mid+1) is minimum element. Consider
			// the cases like {3, 4, 5, 1, 2}
			if (mid < high && num[mid + 1] < num[mid])
				return num[mid + 1];

			// Check if mid itself is minimum element
			if (mid > low && num[mid] < num[mid - 1])
				return num[mid];

			// Decide whether we need to go to left half or right half
			if (num[mid] > num[high]) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return num[low];
	}

	/**
	 * @param num : a rotated sorted array
	 * @return: the maximum number in the array
	 */
	public static int findMax(int[] num) {
		// write your code here
		int low = 0, high = num.length - 1, mid;

		while (low < high) {
			// Find mid
			mid = (low + high) / 2;

			// Check if mid itself is maximum element
			if (mid < high && num[mid + 1] < num[mid]) {
				return num[mid];
			}

			// Check if element at (mid - 1) is maximum element
			// Consider the cases like {4, 5, 1, 2, 3}
			if (mid > low && num[mid] < num[mid - 1]) {
				return num[mid - 1];
			}

			// Decide whether we need to go to the left half or the right half
			if (num[low] > num[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return num[low];

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 12, 7, 8, 9, 9, 9, 10, 11, 11, 12 };

		System.out.print("The minimum and maximum elements are " + findMin(arr) + ", ");
		System.out.println(findMax(arr));

		int arr1[] = { 5, 6, 1, 2, 3, 4 };
		System.out.print("The minimum and maximum elements are " + findMin(arr1) + ", ");
		System.out.println(findMax(arr1));

		int arr2[] = { 1, 2, 3, 4 };
		System.out.print("The minimum and maximum elements are " + findMin(arr2) + ", ");
		System.out.println(findMax(arr2));

		int arr3[] = { 1 };
		System.out.print("The minimum and maximum elements are " + findMin(arr3) + ", ");
		System.out.println(findMax(arr3));

		int arr4[] = { 1, 2 };
		System.out.print("The minimum and maximum elements are " + findMin(arr4) + ", ");
		System.out.println(findMax(arr4));

		int arr5[] = { 2, 1 };
		System.out.print("The minimum and maximum elements are " + findMin(arr5) + ", ");
		System.out.println(findMax(arr5));

		int arr6[] = { 5, 6, 7, 1, 2, 3, 4 };
		System.out.print("The minimum and maximum elements are " + findMin(arr6) + ", ");
		System.out.println(findMax(arr6));

		int arr7[] = { 1, 2, 3, 4, 5, 6, 7 };
		System.out.print("The minimum and maximum elements are " + findMin(arr7) + ", ");
		System.out.println(findMax(arr7));

		int arr8[] = { 2, 3, 4, 5, 6, 7, 8, 1 };
		System.out.print("The minimum and maximum elements are " + findMin(arr8) + ", ");
		System.out.println(findMax(arr8));

		int arr9[] = { 3, 4, 5, 1, 2 };
		System.out.print("The minimum and maximum elements are " + findMin(arr9) + ", ");
		System.out.println(findMax(arr9));

		int[] arr10 = { 1, 1, 2, 3, 4, 7, 7, 7, 8, 9, 1 };
		System.out.print("The minimum and maximum elements are " + findMin(arr10) + ", ");
		System.out.println(findMax(arr10));
	}

}
