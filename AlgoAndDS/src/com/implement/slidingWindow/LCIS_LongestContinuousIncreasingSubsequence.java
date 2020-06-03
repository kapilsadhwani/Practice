package com.implement.slidingWindow;


/* Dynamic Programming Java implementation of LIS problem */

class LCIS_LongestContinuousIncreasingSubsequence {
	public static int findLengthOfLCIS(int[] nums) {
        int maxLength = 0, start = 0;
        //int currLength = 0;
        
        /*
         * currLength = i - start + 1
         */
        for (int i = 0; i < nums.length; ++i) {
        	if (i > 0 && nums[i] <= nums[i-1]){
            	start = i;
            	//currLength = 1;
            }
        	/*else{
            	currLength = currLength + 1;
            }*/
        	
        	if(maxLength < i - start + 1)
        		maxLength = i - start + 1;
        }
        
        return maxLength;
    }

	public static void main(String args[]) {
		int nums[];
		nums = new int[] { 1,3,5,4,2,3,4,5 };
		System.out.println("Length of lcis is " + findLengthOfLCIS(nums));
	}
}