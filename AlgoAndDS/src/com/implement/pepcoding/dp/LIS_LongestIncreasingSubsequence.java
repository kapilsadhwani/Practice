package com.implement.pepcoding.dp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/* 
 * Dynamic Programming Java implementation of LIS problem
 * 
 * Print the length of longest increasing subsequence of array
 */

class LIS_LongestIncreasingSubsequence {
	static class Pair{
		int i;
		int s;
		int d;
		String psf;
		
		public Pair(int i, int s, int d, String psf) {
			super();
			this.i = i;
			this.s = s;
			this.d = d;
			this.psf = psf;
		}
	}
	
	/*
	 * lis() returns the length of the longest increasing subsequence in arr[]
	 * of size n
	 */
	static int findLIS(int nums[]) {
		int n = nums.length;
		
		if (n <= 1) return n;
		
		int[] dp = new int[n]; //lengths[i] = length of longest ending in nums[i]
		
		/* Pick maximum of all LIS values */
		int ans = 0;
		
		/*
		 * Compare till previous elements
		 */
		for (int i = 0; i < n; i++) {
			int max = 0;
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]){
					if(dp[j] > max){
						max = dp[j];
					}
				}
			}
			
			dp[i] = max + 1;
			
			if(dp[i] > ans){
				ans = dp[i];
			}
		}

		return ans;
	}
	
	static void printAllLIS(int nums[]) {
		int n = nums.length;
		
		if (n == 0) System.out.println("");
		if (n == 1) System.out.println(nums[0]);
		
		int[] dp = new int[n];
		
		/* Pick maximum of all LIS values */
		int ans = 0;
		
		/*
		 * Compare till previous elements
		 */
		for (int i = 0; i < n; i++) {
			int max = 0;
			for (int j = 0; j < i; j++) {
				if (nums[j] <= nums[i]){
					if(dp[j] > max){
						max = dp[j];
					}
				}
			}
			
			dp[i] = max + 1;
			
			if(dp[i] > ans){
				ans = dp[i];
			}
		}

		System.out.println(ans);
		
		Queue<Pair> queue = new LinkedList<>();
		
		for(int j = nums.length - 1; j >= 0; j--){
			if(dp[j] == ans){
				queue.add(new Pair(j, nums[j], dp[j], nums[j] + ""));
			}
		}
		
		Pair rem = null;
		while(queue.size() > 0){
			rem = queue.poll();
			
			if(rem.d == 1){
				System.out.println(rem.psf);
			}else{
				for(int j = rem.i - 1; j >= 0; j--){
					if(dp[j] == rem.d - 1 && nums[j] <= rem.s){
						queue.add(new Pair(j, nums[j], dp[j], nums[j] + " -> " + rem.psf));
					}
				}
			}
		}
	}
	
	static int findCountOfLIS(int nums[]) {
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
				if (nums[start] <= nums[end]){
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
		System.out.println("Length of lis is " + findLIS(nums) + 
				", Count is " + findCountOfLIS(nums));
		
		printAllLIS(nums);
		System.out.println(" ======================================= ");
		
		nums = new int[] { 10, 9, 2, 5, 3, 7, 101, 18 };
		System.out.println("Length of lis is " + findLIS(nums) + 
				", Count is " + findCountOfLIS(nums));
		printAllLIS(nums);
		System.out.println(" ======================================= ");
		
		nums = new int[] { 1, 3, 5, 4, 7 };
		System.out.println("Length of lis is " + findLIS(nums) + 
				", Count is " + findCountOfLIS(nums));
		printAllLIS(nums);
		System.out.println(" ======================================= ");
		
		nums = new int[] { 2, 2, 2, 2, 2 };
		System.out.println("Length of lis is " + findLIS(nums) + 
				", Count is " + findCountOfLIS(nums));
		printAllLIS(nums);
	}
}