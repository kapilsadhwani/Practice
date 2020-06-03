package com.implement.twoPointer;

import java.util.Arrays;

import com.implement.sort.SortUtils;

// Group Numbers such that evens are on left and odds on right part of an array
public class GroupNumbers {

	private static void groupEvenOdds(int[] input) {
		int left = 0, right = input.length - 1;

		while (left < right) {
			while (left <= right && input[left] % 2 == 0)
				left++;
			while (left <= right && input[right] % 2 == 1)
				right--;

			if (left < right) {
				SortUtils.swap(input, left, right);
				left++;
				right--;
			}
		}
	}

	private static int[] move0sToEnd(int[] input) {
		int left = 0, right = input.length - 1;

		while (left < right) {
			while (left <= right && input[left] != 0)
				left++;
			while (left < right && input[right] == 0)
				right--;

			if (left < right) {
				SortUtils.swap(input, left, right);
				left++;
				right--;
			}
		}
		
		return input;
	}

	/*/
	 * Maintains insertion order of non-zero elements
	 */
	private static int[] moveZeroes(int[] nums) {
		int left = 0;

		while (left < nums.length && nums[left] != 0) {
			left++;
		}

		int right = left + 1;

		while (right < nums.length && nums[right] == 0) {
			right++;
		}

		int temp;

		while (right < nums.length) {
			while (left < nums.length && nums[left] != 0)
				left++;
			while (right <= nums.length && nums[right] == 0)
				right++;

			if (right < nums.length) {
				temp = nums[left];
				nums[left] = nums[right];
				nums[right] = temp;

				left++;
				right++;
			}
		}
		
		return nums;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// int[] originalArray = {12,3,7,8,2};
		int[] originalArray = { 12, 3, 7, 8, 2, 5, 21, 13, 6, 9, 10 };
		// int[] originalArray = {1,3,4,2,9,6,5,11,3,2};
		// int[] originalArray = {1,2,3,4,5,6,7,8,9,10,11,12};
		// int[] originalArray = {12,11,10,9,8,7,6,5,4,3,2,1};

		SortUtils.printArray(originalArray);

		System.out.println("");
		for (int i = 0; i < 5 * originalArray.length; i++)
			System.out.print("-");

		groupEvenOdds(originalArray);

		SortUtils.printArray(originalArray);

		System.out.println("");
		for (int i = 0; i < 5 * originalArray.length; i++)
			System.out.print("=");

		int[] originalArray1 = { 1, 0, 3, 5, 0, 0, 34, 5, 0, 36 };

		SortUtils.printArray(originalArray1);

		System.out.println("");
		for (int i = 0; i < 5 * originalArray1.length; i++)
			System.out.print("-");

		System.out.println("");
		System.out.println(Arrays.toString(move0sToEnd(originalArray1.clone())));
		System.out.println(Arrays.toString(moveZeroes(originalArray1.clone())));
	}

}
