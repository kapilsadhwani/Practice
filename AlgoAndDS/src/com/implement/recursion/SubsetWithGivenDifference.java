package com.implement.recursion;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Target Sum problem is same as finding count of subsets with given difference
 */

public class SubsetWithGivenDifference {
	static class Pair{
		int i;
		int j;
		String psf;		
		
		public Pair(int i, int j, String psf) {
			this.i = i;
			this.j = j;
			this.psf = psf;
		}
	}
	
	private static int sumOfArray(int[] nums){
		int sum = 0;
		
		for(int num : nums){
			sum = sum + num;
		}
		
		return sum;
	}

	private static int countSubsetsWithGivenSumMemo(int[] nums, int n, int tgtSum, int[][] cache) {
		if (tgtSum == 0) {
			return 1;
		}

		if (n == 0) {
			return 0;
		}

		if (cache[n][tgtSum] != -1) {
			return cache[n][tgtSum];
		}

		if (tgtSum >= nums[n - 1]) {
			cache[n][tgtSum] = countSubsetsWithGivenSumMemo(nums, n - 1, tgtSum - nums[n - 1], cache)
							+ countSubsetsWithGivenSumMemo(nums, n - 1, tgtSum, cache);
		} else {
			cache[n][tgtSum] = countSubsetsWithGivenSumMemo(nums, n - 1, tgtSum, cache);
		}

		return cache[n][tgtSum];
	}

	public static int countSubsetsWithGivenSum(int[] nums, int tgtSum) {
		int n = nums.length;
		int cache[][] = new int[n + 1][tgtSum + 1];

		for (int i = 0; i < cache.length; i++) {
			for (int j = 0; j < cache[0].length; j++) {
				cache[i][j] = -1;
			}
		}

		return countSubsetsWithGivenSumMemo(nums, n, tgtSum, cache);
	}
	
	public static int countSubsetsWithGivenTarget(int[] nums, int tgtSum) {
		int n = nums.length;
		int dp[][] = new int[n + 1][tgtSum + 1];

		for (int j = 0; j < dp[0].length; j++) {
			dp[0][j] = 0;
		}

		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = 1;
		}

		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if (j >= nums[i - 1]) {
					dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}

		return dp[n][tgtSum];
	}
	
	public static void printSubsetsWithGivenTarget(int[] nums, int tgtSum) {
		int n = nums.length;
		boolean dp[][] = new boolean[n + 1][tgtSum + 1];

		for (int j = 0; j < dp[0].length; j++) {
			dp[0][j] = false;
		}

		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = true;
		}

		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if (j >= nums[i - 1]) {
					dp[i][j] = dp[i - 1][j] || 
								dp[i - 1][j - nums[i - 1]];
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		
		System.out.println(dp[nums.length][tgtSum]);
		
		System.out.println("Printing their indexes: ");
		
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(nums.length, tgtSum, ""));
		
		Pair rem = null;
		while(queue.size() > 0){
			rem = queue.poll();
			
			if(rem.i == 0 || rem.j == 0){
				System.out.println(rem.psf);
			}else{
				if(rem.j >= nums[rem.i - 1]){
					boolean inc = dp[rem.i - 1][rem.j - nums[rem.i - 1]];
					
					if(inc){
						// Include and Print indexes
						queue.add(new Pair(rem.i - 1, rem.j - nums[rem.i - 1], (rem.i - 1) + " " + rem.psf));
					}
				}
				
				boolean exc = dp[rem.i - 1][rem.j];
				if(exc){
					// Exclude and do not print corresponding index
					queue.add(new Pair(rem.i - 1, rem.j, rem.psf));
				}
			}
		}
	}
	
	public static int countSubsetSumGivenDifference(int[] nums, int difference) {
		int range = sumOfArray(nums);
		
		if(range < difference) return 0;
		
		// If difference + range is odd
		if((difference + range) % 2 != 0) return 0;
		
		int tgtSum = (difference + range) / 2;

		return countSubsetsWithGivenSum(nums, tgtSum);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nums[]; 
		nums = new int[]{1, 1, 1, 1, 1};
		
		int difference = 3;
		
		System.out.println(Arrays.toString(nums) + ", Difference = " + difference + 
				", Count =  " + countSubsetSumGivenDifference(nums, difference));
		
		System.out.println("===============================================");
		
		nums = new int[]{1};
		
		difference = 2;
		
		System.out.println(Arrays.toString(nums) + ", Difference = " + difference + 
				", Count = " + countSubsetSumGivenDifference(nums, difference));
		
		System.out.println("===============================================");
		
		nums = new int[]{4, 2, 7, 1, 3};
		int target = 10;
		
		System.out.println("DP Memo - " + Arrays.toString(nums) + ", target = " + target + 
				", Count = " + countSubsetsWithGivenSum(nums, target));
		System.out.println("DP Iter - " + Arrays.toString(nums) + ", target = " + target + 
				", Count = " + countSubsetsWithGivenTarget(nums, target));
		
		printSubsetsWithGivenTarget(nums, target);
	}

}
