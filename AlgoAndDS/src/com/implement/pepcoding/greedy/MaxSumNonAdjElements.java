package com.implement.pepcoding.greedy;

/*
 * Return the maximum sum of a subsequence with no adjacent elements.
 */

public class MaxSumNonAdjElements {
	public static int maxSum(int[] nums){
		int oldInc = nums[0];
		int oldExc = 0;
		
		for(int i=1; i<nums.length; i++){
			int newInc = oldExc + nums[i];
			int newExc = Math.max(oldInc, oldExc);
			
			oldInc = newInc;
			oldExc = newExc;
		}
		
		int result = Math.max(oldInc, oldExc);
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {5, 10, 10, 100, 5, 6};

		System.out.println(maxSum(nums));
	}

}
