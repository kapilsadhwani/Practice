package com.implement.recursion;

import java.util.Arrays;
import java.util.HashMap;

// Java implementation of the approach 

import com.implement.sort.SortUtils;

public class SurpasserCountInArray {

	// Function to count the number of inversions during the merge process
	private static void mergeAndCount(int[] arr, int l, int m, int r, int[] temp, 
			HashMap<Integer, Integer> hm) {
		int left=l, right=m+1, k=0;
		int swaps = 0;

		while(left<=m && right<=r){
			if(arr[left] <= arr[right]){
				// increment inversion count of arr[left] 
	            hm.put(arr[left], hm.getOrDefault(arr[left], 0) + swaps); 
				temp[k++] = arr[left++];
			}else{
				temp[k++] = arr[right++];
				
				// inversion found
				swaps++;
			}
		}
		
		// We need to update Map
		while(left<=m){
			// increment inversion count of arr[left] 
			hm.put(arr[left], hm.getOrDefault(arr[left], 0) + swaps);
			
			temp[k++] = arr[left++];
		}
		while(right<=r){
			temp[k++] = arr[right++];
		}
		
		//SortUtils.copyArray(temp,arr,l,r);
		for (int i = l, j = 0; i <= r; i++) {
			arr[i] = temp[j++];
		}
	}

	// Merge sort function
	private static void mergeSortAndCount(int[] arr, int l, int r, int[] temp, HashMap<Integer, Integer> hm) {

		if (l < r) {
			int m = l + (r-l)/2;

			// Save inversion count in a map for each integer as key

			// Left subarray
			mergeSortAndCount(arr, l, m, temp, hm);

			// Right subarray
			mergeSortAndCount(arr, m + 1, r,temp, hm);

			// Merge: Update merge count for each key
			mergeAndCount(arr, l, m, r, temp, hm);
		}
	}
	
	static void printSurpasser(int[] arr){
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		int[] copy = Arrays.copyOf(arr, arr.length);
		int[] temp = new int[arr.length];
		mergeSortAndCount(copy, 0, arr.length-1, temp, hm);
		
		/*hm.entrySet().forEach(entry -> {
			System.out.println(entry.getKey() + ", " + entry.getValue());
		});*/
		
		Arrays.fill(temp, 0);
		int n = arr.length;
		
		for(int i=0; i<arr.length; i++){
			//if(hm.get(arr[i]) != null){
			if(hm.containsKey(arr[i])){
				//Surpasser count = n - 1 – i – inversion-count
				temp[i] = n - 1 - i - hm.get(arr[i]);
			}
		}
		
		System.out.println(Arrays.toString(temp));
	}

	// Driver code
	public static void main(String[] args) {
		//int[] arr = { 4, 5, 1, 2, 3 };
		int[] arr = { 2, 7, 5, 3, 0, 8, 1 };
		
		printSurpasser(arr);
		
		int[] arr1 = { 5, 2, 6, 1 };
		
		printSurpasser(arr1);

		/*System.out.println(Arrays.toString(arr) + " - " + mergeSortAndCount(arr, 0, arr.length - 1, temp, hm));
		
		int[] arr1 = { 1, 2, 3, 4, 5 };
		System.out.println(Arrays.toString(arr1) + " - " + mergeSortAndCount(arr1, 0, arr1.length - 1, temp, hm));
		
		int[] arr2 = { 5, 4, 3, 2, 1 };
		System.out.println(Arrays.toString(arr2) + " - " + mergeSortAndCount(arr2, 0, arr2.length - 1, temp, hm));*/
	}
}