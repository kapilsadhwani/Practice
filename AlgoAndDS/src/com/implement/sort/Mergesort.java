package com.implement.sort;

public class Mergesort {
	private static void merge(Integer[] array, int start, int mid, int end,
			Integer[] temp) {
		// TODO Auto-generated method stub
		int left = start, right = mid + 1, k = 0;

		while (left <= mid && right <= end) {
			if (array[left] <= array[right])
				temp[k++] = array[left++];
			else
				temp[k++] = array[right++];
		}

		while (left <= mid)
			temp[k++] = array[left++];
		while (right <= end)
			temp[k++] = array[right++];

		SortUtils.copyArray(temp, array, start, end);
	}

	private static void mergeSort(Integer[] array, int start, int end,
			Integer[] temp) {
		if (start >= end)
			return;

		int mid = (start + end) / 2;
		mergeSort(array, start, mid, temp);
		mergeSort(array, mid + 1, end, temp);
		merge(array, start, mid, end, temp);
	}

	private static void mergeSortWrapper(Integer[] array, int start, int end) {
		Integer[] temp = new Integer[end - start + 1]; // array.length

		mergeSort(array, start, end, temp);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Integer[] originalArray = {12,3,7,8,2};
		// Integer[] originalArray = {12,3,7,8,2,5,21,13,6,9,10};
		// Integer[] originalArray = {1,3,4,2,9,6,5,11,3,2};
		// Integer[] originalArray = {1,2,3,4,5,6,7,8,9,10,11,12};
		Integer[] originalArray = { 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };

		SortUtils.printArray(originalArray);

		System.out.println("");
		for (int i = 0; i < 5 * originalArray.length; i++)
			System.out.print("=");

		mergeSortWrapper(originalArray, 0, originalArray.length - 1);

		SortUtils.printArray(originalArray);
	}

}
