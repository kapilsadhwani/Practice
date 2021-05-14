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
	
	public static int smallestSubArrayEqualsGivenSum(int[] nums, int target) {
		int minWindowSize = Integer.MAX_VALUE;
		int currentSum = 0;
		int start = 0;
		
		for (int end = 0; end < nums.length; end++) {
			currentSum = currentSum + nums[end];
			
			while(currentSum >= target && start <= end) {
				if(currentSum == target){
					minWindowSize = Math.min(minWindowSize, end - start + 1);
	
					if(minWindowSize == 1) return minWindowSize;
				}

				currentSum = currentSum - nums[start];
				start++;
			}
		}
		return minWindowSize != Integer.MAX_VALUE ? minWindowSize : -1;
	}
	
	/*
	 *  Arrays can contain only non-negative numbers
	 *  Smallest SubArray Greater Than Or Equals Given Sum
	 */
	
	public static int smallestSubArrayGreaterThanOrEqualsGivenSum(int[] nums, int target) {
		if(nums.length == 0 || target <= 0) return 0;
		
		int minWindowSize = Integer.MAX_VALUE;
		int currentSum = 0;
		int start = 0;
		
		for (int end = 0; end < nums.length; end++) {
			currentSum = currentSum + nums[end];
			
			while(currentSum >= target && start <= end) {
				minWindowSize = Math.min(minWindowSize, end - start + 1);
				
				currentSum = currentSum - nums[start];
				start++;
			}
		}

		return minWindowSize == Integer.MAX_VALUE ? 0 : minWindowSize;
	}
	
	public static int largestSubArrayEqualsGivenSum(int[] nums, int target) {
		int maxWindowSize = 0;
		int currentSum = 0;
		int start = 0;
		
		for (int end = 0; end < nums.length; end++) {
			currentSum = currentSum + nums[end];
			
			while(currentSum >= target && start <= end) {
				if(currentSum == target){
					maxWindowSize = Math.max(maxWindowSize, end - start + 1);
				}

				currentSum = currentSum - nums[start];
				start++;
			}
		}
		
		return maxWindowSize;
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
		
		System.out.println("=================== Smallest Subarray ====================");
		
		
		int[] arr7 = { 1, 2, 3, 7, 5 };
		target = 12;
		System.out.print("Equals: " + smallestSubArrayEqualsGivenSum(arr7, target));
		System.out.println(", Greater Than or Equals: " + smallestSubArrayGreaterThanOrEqualsGivenSum(arr7, target));

		int[] arr8 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		target = 15;
		System.out.print("Equals: " + smallestSubArrayEqualsGivenSum(arr8, target));
		System.out.println(", Greater Than or Equals: " + smallestSubArrayGreaterThanOrEqualsGivenSum(arr8, target));

		int[] arr9 = { -12, -2, -20, 10 };
		target = -10;
		System.out.print("Equals: " + smallestSubArrayEqualsGivenSum(arr9, target));
		System.out.println(", Greater Than or Equals: " + smallestSubArrayGreaterThanOrEqualsGivenSum(arr9, target));
		
		int[] arr10 = { 9, 4, 20, 3, 10, 5 };
		target = 33;
		System.out.print("Equals: " + smallestSubArrayEqualsGivenSum(arr10, target));
		System.out.println(", Greater Than or Equals: " + smallestSubArrayGreaterThanOrEqualsGivenSum(arr10, target));
		
		int[] arr11 = { 12, 2, 20, 8 };
		target = 10;
		System.out.print("Equals: " + smallestSubArrayEqualsGivenSum(arr11, target));
		System.out.println(", Greater Than or Equals: " + smallestSubArrayGreaterThanOrEqualsGivenSum(arr11, target));

		int[] arr12 = { 9, 3, 20, 4, 10, 5 };
		target = 33;
		System.out.print("Equals: " + smallestSubArrayEqualsGivenSum(arr12, target));
		System.out.println(", Greater Than or Equals: " + smallestSubArrayGreaterThanOrEqualsGivenSum(arr12, target));
		
		int[] arr13 = { 4, 2, 2, 7, 1, 2, 8, 10 };
		target = 17;
		System.out.print("Equals: " + smallestSubArrayEqualsGivenSum(arr13, target));
		System.out.println(", Greater Than or Equals: " + smallestSubArrayGreaterThanOrEqualsGivenSum(arr13, target));
		
		
		System.out.println("=================== Largest Subarray ====================");
		
		int[] arr14 = { 4, 1, 1, 1, 2, 3, 5 };
		target = 5;
		System.out.print("Largest : " + largestSubArrayEqualsGivenSum(arr14, target));
	}
}