package com.prep.implement.dp;
/* Dynamic Programming Java implementation of Maximum Sum Increasing Subsequence (MSIS) problem */
class MSIS_MaxSumIncreasingSubsequence 
{ 
    /* maxSumIS() returns the  
    maximum sum of increasing 
    subsequence in arr[] of size n */
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
    } 
} 