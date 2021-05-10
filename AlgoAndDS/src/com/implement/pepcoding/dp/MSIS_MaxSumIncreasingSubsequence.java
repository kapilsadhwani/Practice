package com.implement.pepcoding.dp;

/* 
 * Dynamic Programming Java implementation of Maximum Sum Increasing Subsequence (MSIS) problem 
 * 
 * Print the sum of elements of the increasing subsequence with maximum sum for the array
 */
class MSIS_MaxSumIncreasingSubsequence {
	
	static int findMSIS(int nums[]) {
		int n = nums.length;

		if (n <= 1)
			return n;

		int[] dp = new int[n]; // lengths[i] = length of longest ending in
								// nums[i]

		/* Pick maximum of all MSIS values */
		int ans = Integer.MIN_VALUE;

		/*
		 * Compare till previous elements
		 */
		for (int i = 0; i < n; i++) {
			Integer max = null;
			
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]) {
					if(max == null || dp[j] > max){
						max = dp[j];
					}
				}
			}
			
			if(max == null){
				dp[i] = nums[i];
			}else{
				dp[i] = max + nums[i];
			}

			if (dp[i] > ans) {
				ans = dp[i];
			}
		}

		return ans;
	}
	
    /* maxSumIS() returns the   
     * maximum sum of increasing 
     * subsequence in arr[] of size n 
     */
    static int maxSumIS(int arr[], int n){ 
        int end, start, max = 0; 
        int msis[] = new int[n]; 
  
        /* Initialize msis values  
           for all indexes */
        for (int i = 0; i < n; i++) 
            msis[i] = arr[i]; 
  
        /* Compute maximum sum values 
           in bottom up manner */
        for (end = 1; end < n; end++) 
            for (start = 0; start < end; start++) 
                if (arr[end] > arr[start] && 
                    msis[end] < msis[start] + arr[end]) {
                    msis[end] = msis[start] + arr[end]; 
                }
        
        /* Pick maximum of all 
           msis values */
        for (int i = 0; i < n; i++) 
            if (max < msis[i]) 
                max = msis[i]; 
  
        return max; 
    } 
  
    // Driver code 
    public static void main(String args[]) 
    { 
        int arr[] = new int[]{1, 101, 2, 3, 100, 4, 5}; 
        int n = arr.length; 
        System.out.println("Sum of maximum sum "+ 
                            "increasing subsequence is "+ 
                              maxSumIS(arr, n)); 
        
        System.out.println("Sum of maximum sum "+ 
                "increasing subsequence is "+ 
                findMSIS(arr)); 
    } 
} 