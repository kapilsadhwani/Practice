package com.implement.dp;

public class MaximumProductSubArray {
	
	/*
	 * We have to compare among max * A[i], min * A[i] as well as A[i], 
	 * since this is product, a negative * negative could be positive.
	 * maxProduct[i] means maximum product that can be achieved ending with i
	 * minProduct[i] means minimum product that can be achieved ending with i
	 */
	public static int maxProduct(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		
		int n = nums.length;
		
		int[] maxProduct = new int[n];
		int[] minProduct = new int[n];
		maxProduct[0] = nums[0];
		minProduct[0] = nums[0];
		int result = nums[0];
		
		for (int i = 1; i < nums.length; i++) {
			maxProduct[i] = Math.max(
					Math.max(maxProduct[i - 1] * nums[i], minProduct[i - 1] * nums[i]), 
					nums[i]);
			minProduct[i] = Math.min(
					Math.min(maxProduct[i - 1] * nums[i], minProduct[i - 1] * nums[i]), 
					nums[i]);
			
			result = Math.max(result, maxProduct[i]);
		}
		
		return result;
	}
	
	public static int maxProductOptimized(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		
		int n = nums.length;
		
		int maxSoFar = nums[0];
		int minSoFar = nums[0];
		int result = nums[0];
		
		for (int i = 1; i < n; i++) {
			int prodMax = maxSoFar * nums[i];
            int prodMin = minSoFar * nums[i];
			
			maxSoFar = Math.max(Math.max(prodMax, prodMin), 
								nums[i]);
			minSoFar = Math.min(Math.min(prodMax, prodMin), 
								nums[i]);
			
			result = Math.max(result, maxSoFar);	
		}
		
		return result;
	}

	public static void main(String[] args) {
		int nums[];
		nums = new int[]{2,3,-2,4};
		System.out.println(maxProduct(nums) + ", " + maxProductOptimized(nums));
		
		nums = new int[]{-2,0,-1};
		System.out.println(maxProduct(nums) + ", " + maxProductOptimized(nums));
		
		nums = new int[]{-4,-3,-2};
		System.out.println(maxProduct(nums) + ", " + maxProductOptimized(nums));
	}
}