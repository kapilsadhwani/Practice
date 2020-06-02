package com.prep.implement.slidingWindow;

public class CountSubArraySumDivByK {
	// Handles all the cases 
	// function to find all sub-arrays divisible by k
	// modified to handle negative numbers as well
	private int subCount(int arr[], int n, int k){
	    // create auxiliary hash array to count frequency
	    // of remainders
	    int cache[] = new int[k];
	  
	    // Traverse original array and compute cumulative
	    // sum take remainder of this current cumulative
	    // sum and increase count by 1 for this remainder
	    // in mod[] array
	    int cumSum = 0;
	    for (int i=0; i<n; i++){
	        cumSum += arr[i];
	        
	        if(cumSum < 0){
	        	// as the sum can be negative, taking modulo twice 
	        	cache[((cumSum % k) + k) % k]++; 
	        }else{
	        	cache[cumSum%k]++;
	        }
	    }
	  
	    int result = 0;  // Initialize result
	     
	    // Traverse cache[]
	    for (int i=0; i<k ; i++)
	  
	        // If there are more than one prefix subarrays with a particular mod value.
	    	// we can choose any two pair of indices for sub-array by 
	    	// (cache[i] * (cache[i] – 1))/2 number of ways 
	        if (cache[i] > 1)
	            result += (cache[i]*(cache[i]-1))/2;
	     
	    // add the elements which are divisible by k itself 
	    // as their individual set is to be considered as well
	    result += cache[0];
	     
	    return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CountSubArraySumDivByK csa = new CountSubArraySumDivByK();
		
		int arr[] = {4, 5, 0, -2, -3, 1};
	    int k = 5;
	    int n = arr.length;
	    System.out.println(csa.subCount(arr, n, k));
	    
	    int arr1[] = {4,5,1,10,5};
	    int k1 = 5;
	    int n1 = arr1.length;
	    System.out.println(csa.subCount(arr1, n1, k1));
	}

}
