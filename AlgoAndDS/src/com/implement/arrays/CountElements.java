package com.implement.arrays;

import java.util.HashSet;
import java.util.Set;

public class CountElements {
	public static int countElements(int[] arr) {
		int count = 0;
		Set<Integer> setOfInts = new HashSet<Integer>();
		
		for(int i=arr.length-1; i>=0; i--){
			setOfInts.add(arr[i]);
		}
		
		for(int i=arr.length-1; i>=0; i--){
			if(setOfInts.contains(arr[i]+1)){
				count++;
			}
		}
		
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr1 = {1,2,3};
		System.out.println(countElements(arr1));
		
		int[] arr2 = {1,1,3,3,5,5,7,7};
		System.out.println(countElements(arr2));
		
		int[] arr3 = {1,3,2,3,5,0};
		System.out.println(countElements(arr3));
		
		int[] arr4 = {1,1,2,2};
		System.out.println(countElements(arr4));
	}

}
