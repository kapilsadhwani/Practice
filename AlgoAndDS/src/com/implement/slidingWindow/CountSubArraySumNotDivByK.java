package com.implement.slidingWindow;

public class CountSubArraySumNotDivByK {
	// Returns maximum size of subset with no pair
	// sum divisible by K
	int subsetPairNotDivisibleByK(int arr[], int N,
	                                         int K)
	{
	    // Array for storing frequency of modulo values
	    int[] f = new int[K];
	 
	    // Fill frequency array with values modulo K
	    for (int i = 0; i < N; i++)
	        f[arr[i] % K]++;
	 
	    //  if K is even, then update f[K/2]
	    if (K % 2 == 0)
	        f[K/2] = Math.min(f[K/2], 1);
	 
	    // Initialize result by minimum of 1 or
	    // count of numbers giving remainder 0
	    int res = Math.min(f[0], 1);
	 
	    // Choose maximum of count of numbers
	    // giving remainder i or K-i
	    for (int i = 1; i <= K/2; i++)
	        res += Math.max(f[i], f[K-i]);
	 
	    return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CountSubArraySumNotDivByK csa = new CountSubArraySumNotDivByK();
		
		int arr[] = {3, 7, 2, 9, 1};
	    int k = 3;
	    int n = arr.length;
	    System.out.println(csa.subsetPairNotDivisibleByK(arr, n, k));
	}

}
