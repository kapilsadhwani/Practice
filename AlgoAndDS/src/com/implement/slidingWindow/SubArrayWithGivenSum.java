package com.implement.slidingWindow;

import java.util.Arrays;
import java.util.HashMap;

public class SubArrayWithGivenSum {
	public static int countOfSubarrayWithGivenSum(int[] nums, int target) {
		int count = 0;
		for (int start = 0; start < nums.length; start++) {
			int sum = 0;
			for (int end = start; end < nums.length; end++) {
				sum += nums[end];
				if (sum == target)
					count++;
			}
		}
		return count;
	}

	public static int[] subarraySum(int[] nums, int target) {
		for (int start = 0; start < nums.length; start++) {
			int sum = 0;
			for (int end = start; end < nums.length; end++) {
				sum += nums[end]; // Cumulative Sum for this start
				if (sum == target)
					return new int[] { start, end};
			}
		}
		return new int[] { -1, -1 };
	}

	// Using HashMap
	public static int subarraySumHM(int[] nums, int k) {
		int count = 0, sum = 0;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, 1);
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (map.containsKey(sum - k))
				count += map.get(sum - k);
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return count;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 7, 5 };
		int target = 12;
		int[] indices;
		indices = subarraySum(arr, target);
		System.out.println("Array: " + Arrays.toString(arr) + ", Slinding Window: " 
				+ countOfSubarrayWithGivenSum(arr, target) + ", HashMap: " 
				+ subarraySumHM(arr, target) + ", Indices: "
				+ indices[0] + " " + indices[1]);

		int[] arr1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		target = 15;
		indices = subarraySum(arr1, target);
		System.out.println("Array: " + Arrays.toString(arr1) + ", Slinding Window: "
				+ countOfSubarrayWithGivenSum(arr1, target) + ", HashMap: "
				+ subarraySumHM(arr1, target) + ", Indices: "
				+ indices[0] + " " + indices[1]);

		int[] arr2 = { -12, -2, -20, 10 };
		target = -10;
		indices = subarraySum(arr2, target);
		System.out.println("Array: " + Arrays.toString(arr2) + ", Slinding Window: "
				+ countOfSubarrayWithGivenSum(arr2, target) + ", HashMap: "
				+ subarraySumHM(arr2, target) + ", Indices: "
				+ indices[0] + " " + indices[1]);

		int[] arr3 = { 9, 4, 20, 3, 10, 5 };
		target = 33;
		indices = subarraySum(arr3, target);
		System.out.println("Array: " + Arrays.toString(arr3) + ", Slinding Window: "
				+ countOfSubarrayWithGivenSum(arr3, target) + ", HashMap: "
				+ subarraySumHM(arr3, target) + ", Indices: "
				+ indices[0] + " " + indices[1]);
		
		int[] arr4 = { 12, 2, 20, 8 };
		target = 10;
		indices = subarraySum(arr4, target);
		System.out.println("Array: " + Arrays.toString(arr4) + ", Slinding Window: "
				+ countOfSubarrayWithGivenSum(arr4, target) + ", HashMap: "
				+ subarraySumHM(arr4, target) + ", Indices: "
				+ indices[0] + " " + indices[1]);
		
		int[] arr5 = { 9, 3, 20, 4, 10, 5 };
		target = 33;
		indices = subarraySum(arr5, target);
		System.out.println("Array: " + Arrays.toString(arr5) + ", Slinding Window: "
				+ countOfSubarrayWithGivenSum(arr5, target) + ", HashMap: "
				+ subarraySumHM(arr5, target) + ", Indices: "
				+ indices[0] + " " + indices[1]);
		
		int[] arr6 = { 4, 2, 2, 7, 1, 2, 8, 10 };
		target = 17;
		indices = subarraySum(arr6, target);
		System.out.println("Array: " + Arrays.toString(arr6) + ", Slinding Window: "
				+ countOfSubarrayWithGivenSum(arr6, target) + ", HashMap: "
				+ subarraySumHM(arr6, target) + ", Indices: "
				+ indices[0] + " " + indices[1]);
	}
}