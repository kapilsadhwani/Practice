package com.implement.pepcoding.dp;

import java.util.Arrays;

/* Dynamic Programming Java implementation of LBS problem */

class LBS_LongestBitonicSubsequence {
	/*
	 * lis() returns the length of the longest bitonic subsequence in arr[]
	 * of size n
	 */
	static int findLBS(int nums[]) {
		int n = nums.length;
		
		if (n <= 1) return n;
		
		int[] lis = new int[n];
		
		/*
		 * Compare till previous elements
		 */
		for (int i = 0; i < n; i++) {
			int max = 0;
			
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]){
					if(lis[j] > max){
						max = lis[j];
					}
				}
			}
			
			lis[i] = max + 1;
		}
		
		int[] lds = new int[n];
		
		for (int i = n - 1; i >= 0; i--) {
			int max = 0;
			
			for (int j = n - 1; j > i; j--) {
				if (nums[j] < nums[i]){
					if(lds[j] > max){
						max = lds[j];
					}
				}
			}
			
			lds[i] = max + 1;
		}
		
		int ans = 0;
		
		for (int i = 0; i < n; i++) {
			if (lis[i] + lds[i] - 1 > ans){
				ans = lis[i] + lds[i] - 1;
			}
		}

		return ans;
	}
	
	public static void main(String args[]) {
		int nums[];
		nums = new int[] { 10, 22, 9, 33, 21, 50, 41, 60, 80, 3 };
		System.out.println("Length of LBS is " + findLBS(nums));
	}
}