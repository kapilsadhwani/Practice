package com.implement.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MissingPositive {
	/**
	 * @param nums
	 *            : an array of integers
	 * @return: an integer
	 */
	// Scalable to multiple such instances
	public static void missingAndDuplicateNums(int[] nums) {
		int i = 0;
		int temp;

		while (i < nums.length) {
			if (nums[i] != nums[nums[i] - 1]) {

				// Swap numbers
				temp = nums[i];
				nums[i] = nums[temp - 1];
				nums[temp - 1] = temp;

			} else {
				i++;
			}
		}

		System.out.println(Arrays.toString(nums));

		for (i = 0; i < nums.length; i++) {
			if (nums[i] != i + 1) {
				System.out.println("Missing: " + (i + 1) + ", Duplicate: "
						+ nums[i]);
			}
		}
	}

	public static int[] findErrorNums(int[] nums) {
		int i = 0;
		int temp;

		while (i < nums.length) {
			if (nums[i] != nums[nums[i] - 1]) {

				// Swap numbers
				temp = nums[i];
				nums[i] = nums[temp - 1];
				nums[temp - 1] = temp;

			} else {
				i++;
			}
		}

		for (i = 0; i < nums.length; i++) {
			if (nums[i] != i + 1) {
				break;
			}
		}
		
		return new int[]{nums[i],i+1};
	}

	public static List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> missing = new ArrayList<Integer>();
		int j = 0;
		for (int i = 0; i < nums.length; i++) {
			j = Math.abs(nums[i]);
			if (nums[j - 1] > 0) {
				nums[j - 1] = -nums[j - 1];
			}
		}

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) {
				missing.add(i + 1);
			}
		}

		return missing;
	}

	public int findDuplicate(int[] nums) {
		// Find the intersection point of the two runners.
		int tortoise = nums[0];
		int hare = nums[0];
		do {
			tortoise = nums[tortoise];
			hare = nums[nums[hare]];
		} while (tortoise != hare);

		// Find the "entrance" to the cycle.
		tortoise = nums[0];
		while (tortoise != hare) {
			tortoise = nums[tortoise];
			hare = nums[hare];
		}

		return hare;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr1 = { 1, 2, 1 };
		missingAndDuplicateNums(arr1);

		arr1 = new int[] { 3, 4, 4, 1 };
		missingAndDuplicateNums(arr1);

		arr1 = new int[] { 1, 3, 4, 3, 1 };
		missingAndDuplicateNums(arr1);

		int[] arr2 = { 4, 3, 2, 7, 8, 2, 3, 1 };
		List<Integer> missing = findDisappearedNumbers(arr2);
		System.out.println(" Missing numbers : " + missing.toString());

		arr2 = new int[] { 1 };
		missing = findDisappearedNumbers(arr2);
		System.out.println(" Missing numbers : " + missing.toString());

		arr2 = new int[] { 4, 2, 2, 4, 4 };
		missing = findDisappearedNumbers(arr2);
		System.out.println(" Missing numbers : " + missing.toString());

		arr2 = new int[] { 3, 2, 5, 6, 5, 2 };
		missing = findDisappearedNumbers(arr2);
		System.out.println(" Missing numbers : " + missing.toString());

		arr2 = new int[] { 1, 3, 2, 4, 6, 6 };
		missing = findDisappearedNumbers(arr2);
		System.out.println(" Missing numbers : " + missing.toString());

		arr2 = new int[] { 4, 3, 2, 1 };
		missing = findDisappearedNumbers(arr2);
		System.out.println(" Missing numbers : " + missing.toString());
		
		arr2 = new int[] { 1, 2, 2, 4 };
		System.out.println(Arrays.toString(findErrorNums(arr2)));
		
		arr2 = new int[] { 1, 1 };
		System.out.println(Arrays.toString(findErrorNums(arr2)));
	}
}
