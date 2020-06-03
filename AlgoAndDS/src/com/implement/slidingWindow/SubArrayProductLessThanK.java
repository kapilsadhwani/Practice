package com.implement.slidingWindow;

public class SubArrayProductLessThanK {
	/*
	 *  Sliding Window
	 *  For all subarrays, include if product is less than K, otherwise increase left pointer
	 */
	public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        
        int prod = 1, count = 0, start = 0;
        
        for (int end = 0; end < nums.length; end++) {
            prod = prod * nums[end];
            
            while (prod >= k){
            	prod = prod / nums[start];
            	start++;	// Increment left pointer
            }
            
            count = count + end - start + 1;
        }
        return count;
    }

	public static void main(String[] args) {
		int[] arr = { 10, 5, 2, 6 };
		int target = 100;
		System.out.println(numSubarrayProductLessThanK(arr, target));
	}
}