package com.implement.arrays;
import java.util.Arrays;
import java.util.Map;

/* Java program to count minimum number of candies for all the students such that
 * neighboring student gets more candies if its rating is better than the other two.
 * 
 * Based on Greedy Algorithm
 */ 
class Candies { 

/* Function to count minimum number of candies for all the students such that
 * neighboring student gets more candies if its rating is better than the other two.
 */
	
	static long candies(int a[], int n) { 
		// result to store output 
		int c[] = new int[n];
		
		Arrays.fill(c,  1);
		
		for (int i = 1; i < n; i++) { 
			if(a[i] > a[i-1]){
				c[i] = c[i-1] + 1;
			}
		}
		
		for (int i = n-2; i >= 0; i--) { 
			if(a[i] > a[i+1]){
				c[i] = Math.max(c[i+1] + 1, c[i]);
			}
		}

		long sum = 0;
		
		for(int i=0; i<n; i++){
			sum = sum + (long) c[i];
		}
		
		System.out.println("Candies : " + Arrays.toString(c));
		
		return sum;
	} 

// main function 
	public static void main(String[] args) { 
		int[] nums = {1, 2, 3, 4, 1, 2, 4, 2};
		System.out.println(candies(nums, nums.length));
	} 
} 
