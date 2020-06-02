package com.prep.implement.twoPointer;

// Given 2 sorted array, find is one is a subset of other
public class SubsetProblem {
	
	public static boolean subsetIfSorted(int[] smaller, int[] bigger, int m, int n){
		int i=0, j=0;
		
		while(i<m && j<n){
			if(smaller[i] < bigger[j]) i++;
			else if(bigger[j] < smaller[i]) j++;
			else {
				i++;
				j++;
			}
		}
		
		//System.out.println("Math.min (i,j) = " + Math.min(i, j) + " <==> " + "Math.min (m,n) = " + Math.min(m, n));
		return Math.min(i, j) == Math.min(m, n);
	}
	
	// n >= m
	public static boolean isSubArray(int[] smaller, int[] bigger, int m, int n){
		for(int i=0; i + (m-1) < n; i++){
			int j=0;
			
			while(j < m){
				if(bigger[i+j] != smaller[j]){
					break;
				}
				j++;
			}
			
			if(j == m)
				return true;
		}
		
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] s1 = {3,3,5,5};
		int[] s2 = {1,2,3,3,4,4,5,5};
		int[] s3 = {3,4,4,5};
		
		System.out.println("S1 is a Subset of S2 : " + subsetIfSorted(s1, s2, s1.length, s2.length));
		System.out.println("S1 is Subarray of S2 : " + isSubArray(s1, s2, s1.length, s2.length));
		System.out.println("S3 is Subarray of S2 : " + isSubArray(s3, s2, s3.length, s2.length));
	}

}
