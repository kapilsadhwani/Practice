package com.prep.implement.sort;

public class MergeArray {
	private static void printArray(Integer[] data) {
		System.out.println();
		if (data.length > 0) {
			System.out.print(data[0]);
		}
		for (int i = 1; i < data.length; i++) {
			System.out.print(" | " + data[i]);
		}
	}

	private static void merge(Integer[] nums1, Integer[] nums2, int m, int n) {
		int s = n - 1;
		int b = m - 1;
		int k = m + n - 1;

		while (s >= 0 && b >= 0) {
			if (nums1[b] > nums2[s])
				nums1[k--] = nums1[b--];
			else
				nums1[k--] = nums2[s--];
		}

		while (b >= 0) {
			nums1[k--] = nums1[b--];
		}

		while (s >= 0) {
			nums1[k--] = nums2[s--];
		}
	}

	public static int[] mergeArrays(int[] myArray, int[] alicesArray) {

		// set up our mergedArray
		int[] mergedArray = new int[myArray.length + alicesArray.length];

		int currentIndexAlices = 0;
		int currentIndexMine = 0;
		int currentIndexMerged = 0;

		while (currentIndexMerged < mergedArray.length) {

			boolean isMyArrayExhausted = currentIndexMine >= myArray.length;
			boolean isAlicesArrayExhausted = currentIndexAlices >= alicesArray.length;

			// case: next comes from my array
			// my array must not be exhausted, and EITHER:
			// 1) Alice's array IS exhausted, or
			// 2) the current element in my array is less
			// than the current element in Alice's array
			if (!isMyArrayExhausted
					&& (isAlicesArrayExhausted || (myArray[currentIndexMine] < alicesArray[currentIndexAlices]))) {

				mergedArray[currentIndexMerged] = myArray[currentIndexMine];
				currentIndexMine++;

				// case: next comes from Alice's array
			} else {
				mergedArray[currentIndexMerged] = alicesArray[currentIndexAlices];
				currentIndexAlices++;
			}

			currentIndexMerged++;
		}

		return mergedArray;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] nums1 = { 1, 2, 4, 0, 0, 0 };
		Integer[] nums2 = { 5, 6, 7 };

		printArray(nums1);
		printArray(nums2);

		merge(nums1, nums2, nums2.length, nums2.length);

		System.out.println("\n");

		System.out.println("After Merge -->");

		printArray(nums1);
	}

}
