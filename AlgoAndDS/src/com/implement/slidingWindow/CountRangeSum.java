package com.implement.slidingWindow;

import java.util.Arrays;

public class CountRangeSum {
	
	public static int countRangeSum(int[] nums, int lower, int upper) {
		int n = nums.length;
		
		long[] sums = new long[n + 1];
		
		for (int i = 0; i < n; ++i)
			sums[i + 1] = sums[i] + nums[i];
		
		System.out.println(Arrays.toString(nums));
		System.out.println(Arrays.toString(sums));
		
		int ans = 0;
		
		for (int i = 0; i < n; ++i)
			for (int j = i + 1; j <= n; ++j)
				if (sums[j] - sums[i] >= lower && sums[j] - sums[i] <= upper)
					ans++;
		
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {-2,5,-1}; 
		int lower = -2, upper = 2;
		
		System.out.println(countRangeSum(nums, lower, upper));
	}

}
