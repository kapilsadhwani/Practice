package com.implement.slidingWindow;


public class SmallestSubArrayGivenSum {
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

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 7, 5 };
		int target = 12;
		System.out.print("Equals: " + smallestSubArrayEqualsGivenSum(arr, target));
		System.out.println(", Greater Than or Equals: " + smallestSubArrayGreaterThanOrEqualsGivenSum(arr, target));

		int[] arr1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		target = 15;
		System.out.print("Equals: " + smallestSubArrayEqualsGivenSum(arr1, target));
		System.out.println(", Greater Than or Equals: " + smallestSubArrayGreaterThanOrEqualsGivenSum(arr1, target));

		int[] arr2 = { -12, -2, -20, 10 };
		target = -10;
		System.out.print("Equals: " + smallestSubArrayEqualsGivenSum(arr2, target));
		System.out.println(", Greater Than or Equals: " + smallestSubArrayGreaterThanOrEqualsGivenSum(arr2, target));
		
		int[] arr3 = { 9, 4, 20, 3, 10, 5 };
		target = 33;
		System.out.print("Equals: " + smallestSubArrayEqualsGivenSum(arr3, target));
		System.out.println(", Greater Than or Equals: " + smallestSubArrayGreaterThanOrEqualsGivenSum(arr3, target));
		
		int[] arr4 = { 12, 2, 20, 8 };
		target = 10;
		System.out.print("Equals: " + smallestSubArrayEqualsGivenSum(arr4, target));
		System.out.println(", Greater Than or Equals: " + smallestSubArrayGreaterThanOrEqualsGivenSum(arr4, target));

		int[] arr5 = { 9, 3, 20, 4, 10, 5 };
		target = 33;
		System.out.print("Equals: " + smallestSubArrayEqualsGivenSum(arr5, target));
		System.out.println(", Greater Than or Equals: " + smallestSubArrayGreaterThanOrEqualsGivenSum(arr5, target));
		
		int[] arr6 = { 4, 2, 2, 7, 1, 2, 8, 10 };
		target = 17;
		System.out.print("Equals: " + smallestSubArrayEqualsGivenSum(arr6, target));
		System.out.println(", Greater Than or Equals: " + smallestSubArrayGreaterThanOrEqualsGivenSum(arr6, target));
	}
}