package com.prep.implement.binarysearch;

// Java Program for The painter's partition problem 

class AllocateBooks {
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

	// for n books and k students
	static int solveDP(int arr[], int i, int j, int M) {
		// base cases
		if (M <= 0 || i > j) // Invalid input
			return 0;
		if (M == 1) // one student
			return sum(arr, i, j);
		if (i == j) // one book
			return arr[i];

		int min = Integer.MAX_VALUE;
		
		/*
		 * Recursive Function
		 * Math.max[solve(arr, i, k, p-1), sum(arr, k+1, j)]
		 * Then Minimum of above values for each k
		 */

		for (int k = i; k < j; k++){			
			int tempAns = Math.max(sum(arr, i, k), 				// Array of size k, Given to other students
					solveDP(arr, k+1, j, M - 1));	// Remaining Array Sum: k to n-1, my books
			
			min = Math.min(min,tempAns);
		}

		return min;
	}

	private static boolean isValid(int[] arr, int n, int M, int max) {
		// TODO Auto-generated method stub
		int student = 1;
		int sum = 0;
		
		for(int i=0; i<n; i++){
			sum = sum + arr[i];
			
			if(sum > max){
				student ++;
				
				if(student > M){
					return false;
				}
				
				sum = arr[i];
			}
		}
		return true;
	}
	
	static int solveBS(int arr[], int n, int M){
		if(n < M) return -1;
		
		int low = max(arr);
		int high = sum(arr, 0, n-1);
		int result = -1;
		
		while(low <= high){
			int mid = low +(high - low)/2;
			
			if(isValid(arr, n, M, mid)){
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
		int arr[] = { 12, 34, 67, 90 };

		// Calculate size of array.
		int n = arr.length;
		int M = 2;
		System.out.println(" DP: " + solveDP(arr, 0, n-1, M) + ", BS : " + solveBS(arr, n, M));
		
		int arr1[] = { 15, 17, 20 };
		M = 2;
		n = arr1.length;
		System.out.println(" DP: " + solveDP(arr1, 0, n-1, M) + ", BS : " + solveBS(arr1, n, M));
		
		int arr2[] = { 100, 200, 300, 400 };
		M = 2;
		n = arr2.length;
		System.out.println(" DP: " + solveDP(arr2, 0, n-1, M) + ", BS : " + solveBS(arr2, n, M));
	}
}
