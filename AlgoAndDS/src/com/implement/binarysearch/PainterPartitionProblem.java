package com.implement.binarysearch;

// Java Program for The painter's partition problem 

class PainterPartitionProblem {
	// function to calculate sum between two indices in array
	static int sum(int arr[], int start, int end) {
		int total = 0;
		for (int i = start; i <= end; i++)
			total += arr[i];
		return total;
	}
	
	private static int max(int[] arr) {
		// TODO Auto-generated method stub
		int max = arr[0];
		for (int i = 1; i < arr.length; i++)
			if(max < arr[i]){
				max = arr[i];
			}
		return max;
	}

	/*
	 *  For n boards and k painters
	 *  Initial call: i = 0, j = n - 1
	 */
	static int solve(int arr[], int i, int j, int P) {
		// base cases
		if (P <= 0 || i > j) // Invalid input
			return 0;
		if (P == 1) // one painter 
	        return sum(arr, i, j);  
		if (i == j) // one element
			return arr[i];

		int min = Integer.MAX_VALUE;
		
		/*
		 * Recursive Function
		 * Math.max[solve(arr, i, k, p-1), sum(arr, k+1, j)]
		 * Then Minimum of above values for each k
		 */

		for (int k = i; k < j; k++){
			int tempAns = Math.max(sum(arr, k+1, j),
							solve(arr, i, k, P-1));
		
			min = Math.min(min,tempAns);
		}

		return min;
	}
	
	private static boolean isValid(int[] arr, int n, int P, int max) {
		// TODO Auto-generated method stub
		int painter = 1;
		int sum = 0;
		
		for(int i=0; i<n; i++){
			sum = sum + arr[i];
			
			if(sum > max){
				painter ++;
				
				if(painter > P){
					return false;
				}
				
				sum = arr[i];
			}
		}
		return true;
	}
	
	static int solveBS(int arr[], int n, int P){
		if(n < P) return -1;
		
		int low = max(arr);
		int high = sum(arr, 0, n-1);
		int result = -1;
		
		while(low <= high){
			int mid = low +(high - low)/2;
			
			if(isValid(arr, n, P, mid)){
				result = mid;
				
				//Looking for minimum
				high = mid - 1; 
			}else{
				low = mid + 1;
			}
		}
		
		return result;
	}

	// Driver code
	public static void main(String args[]) {
		int arr[] = { 10, 20, 60, 50, 30, 40 }; 
		   
	    // Calculate size of array. 
	    int n = arr.length;
		int K = 3;
		System.out.println("DP : " + solve(arr, 0, n-1, K) + ", BS : " + solveBS(arr, n, K));

		// Calculate size of array.
		n = arr.length;
		K = 1;
		System.out.println("DP : " + solve(arr, 0, n - 1, K) + ", BS : " + solveBS(arr, n, K));
	}
}
