package com.implement.dp;

import java.util.Arrays;

/* Dynamic Programming Java implementation of LIS problem */

class LIS_LongestIncreasingSubsequence {
	/*
	 * lis() returns the length of the longest increasing subsequence in arr[]
	 * of size n
	 */
	static int findLIS(int nums[]) {
		int n = nums.length;
		
		if (n <= 1) return n;
		
		int[] dp = new int[n]; //lengths[i] = length of longest ending in nums[i]

		/* Initialize LIS values for all indexes */
		Arrays.fill(dp, 1);
		/*for (int i = 0; i < n; i++)
			lengths[i] = 1;*/

		/*
		 * Don't compare (i, i)
		 */
		for (int end = 1; end < n; end++) {
			for (int start = 0; start < end; start++) {
				if (nums[start] < nums[end] && dp[end] < dp[start] + 1)
					dp[end] = dp[start] + 1;
			}
		}

		int max = 0;
		/* Pick maximum of all LIS values */
		for (int i = 0; i < n; i++)
			if (max < dp[i])
				max = dp[i];

		return max;
	}
	
	static int findNumberOfLIS(int nums[]) {
		int n = nums.length;
		
		if (n <= 1) return n;
		
		int[] lengths = new int[n]; //lengths[i] = length of longest ending in nums[i]
		int[] counts = new int[n]; //count[i] = number of longest ending in nums[i]

		/* Initialize LIS and count values for all indexes */
		/*for (int i = 0; i < n; i++)
			lengths[i] = 1;*/
		Arrays.fill(lengths, 1);
		Arrays.fill(counts, 1);

		/*
		 * Don't compare (i, i)
		 */
		for (int end = 1; end < n; end++) {
			for (int start = 0; start < end; start++)
				if (nums[start] < nums[end]){
					if(lengths[end] <= lengths[start]){
						lengths[end] = lengths[start] + 1;
						counts[end] = counts[start];
					}else if (lengths[start] + 1 == lengths[end]) {
						counts[end] = counts[end] + counts[start];
					}
			}
		}

		int max = 0;
		int count = 0;
		/* Pick maximum of all LIS values */
		for (int i = 0; i < n; i++)
			if (max < lengths[i])
				max = lengths[i];
		
		for (int i = 0; i < n; ++i) {
            if (lengths[i] == max) {
                count = count + counts[i];
            }
        }

		return count;
	}

	public static void main(String args[]) {
		int nums[];
		nums = new int[] { 10, 22, 9, 33, 21, 50, 41, 60 };
		System.out.println("Length of lis is " + findLIS(nums) + ", Count is " + findNumberOfLIS(nums));
		
		nums = new int[] { 10,9,2,5,3,7,101,18 };
		System.out.println("Length of lis is " + findLIS(nums) + ", Count is " + findNumberOfLIS(nums));
		
		nums = new int[]{1,3,5,4,7};
		System.out.println("Length of lis is " + findLIS(nums) + ", Count is " + findNumberOfLIS(nums));
		
		nums = new int[]{2,2,2,2,2};
		System.out.println("Length of lis is " + findLIS(nums) + ", Count is " + findNumberOfLIS(nums));
	}
}