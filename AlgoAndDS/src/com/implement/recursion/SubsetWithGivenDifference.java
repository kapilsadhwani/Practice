package com.implement.recursion;

/*
 * Target Sum problem is same as finding count of subsets with given difference
 */

public class SubsetWithGivenDifference {
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
	
	public static int countSubsetSumGivenDifference(int[] nums, int difference) {
		int range = sumOfArray(nums);
		
		if(range < difference) return 0;
		
		if((difference + range) % 2 != 0) return 0;
		
		int tgtSum = (difference + range) / 2;

		return countSubsetsWithGivenSum(nums, tgtSum);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nums[]; 
		nums = new int[]{1, 1, 1, 1, 1};
		
		int difference = 3;
		
		System.out.println(countSubsetSumGivenDifference(nums, difference));
		
		nums = new int[]{1};
		
		difference = 2;
		
		System.out.println(countSubsetSumGivenDifference(nums, difference));
	}

}
