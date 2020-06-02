package com.prep.implement.dp;

import java.util.Arrays;

public class HouseRobber {
	public static int robMemo(int[] nums, int pos, int[] cache) {
		if(pos <= 0) return 0;
		
		if(cache[pos] != -1) return cache[pos];
		
		int maxProfit = 0;
		
		maxProfit = Math.max(nums[pos-1] + robMemo(nums, pos-2, cache),
				robMemo(nums, pos-1, cache));
		
		cache[pos] = maxProfit;
		
		return maxProfit;
	}

	public static int robR(int[] nums) {
		int n = nums.length;
		int[] cache = new int[n + 1];
		
		Arrays.fill(cache, -1);
		
		cache[0] = 0;
		
		return robMemo(nums, n, cache);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1,2,3,1};
		System.out.println(robR(nums));
		
		nums = new int[]{2,7,9,3,1};
		System.out.println(robR(nums));
	}

}
