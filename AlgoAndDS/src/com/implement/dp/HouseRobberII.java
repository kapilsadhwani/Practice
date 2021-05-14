package com.implement.dp;

import java.util.Arrays;

public class HouseRobberII {
	public static int robMemo(int[] nums, int pos, int start, int[][] cache) {
		if(pos <= 0 || (start == 1 && pos == 1)) return 0; // We can go till pos=2 (nums[pos-1])
		
		if(cache[start][pos] != -1) return cache[start][pos];
		
		int maxProfit = 0;
		
		maxProfit = Math.max(nums[pos-1] + robMemo(nums, pos-2, start, cache),
				robMemo(nums, pos-1, start, cache));
		
		cache[start][pos] = maxProfit;
		
		return maxProfit;
	}

	public static int robR(int[] nums) {
		if(nums.length == 1) return nums[0];
		if(nums.length == 2){
			return Math.max(nums[0], nums[1]);
		}
		
		int n = nums.length;
		int[][] cache = new int[2][n + 1];
		
		for(int i = 0; i < 2; i++){
			Arrays.fill(cache[i], -1);
		}
		
		cache[0][0] = 0;
		cache[1][0] = 0;
		
		/*
		 *  Find max of 
		 *  1. Select first element and exclude last one
		 *  2. Select last element and exclude first one
		 */
		return Math.max(robMemo(nums, n, 1, cache), 
						robMemo(nums, n - 1, 0, cache));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums;
		nums = new int[]{2,3,2};
		System.out.println(robR(nums));
		
		nums = new int[]{1,2,3,1};
		System.out.println(robR(nums));
		
		nums = new int[]{2};
		System.out.println(robR(nums));
		
		nums = new int[]{1,3};
		System.out.println(robR(nums));
		
		nums = new int[]{2,1,1,2};
		System.out.println(robR(nums));
	}

}
