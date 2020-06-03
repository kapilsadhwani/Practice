package com.implement.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MissingPositive {
	/**
     * @param nums: an array of integers
     * @return: an integer
     */
    public static int findMissingPositive(int[] nums) {
    	int n =  nums.length;
    	boolean oneExists = false;
    	
    	for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 1){
            	oneExists = true;
            	break;
            }
        }
    	
    	if(!oneExists) return 1;
    	
    	// Set 0 and num > n to 1 (To filter them out)
    	for (int i = 0; i < nums.length; i++) {
            if(nums[i] <= 0 || nums[i] > n){
            	nums[i] = 1;
            }
        }
    	
    	// Set values at arr[i] to -ve  (To filter them out)
    	// If value is n then set arr[0] to -ve
    	for (int i = 0; i < nums.length; i++) {
    		int value = Math.abs(nums[i]);
    		
            if(value == n){
            	nums[0] = -1 * Math.abs(nums[0]);
            }else{
            	nums[value] = -1 * Math.abs(nums[value]);
            }
        }
    	
    	// Ignore 0th index as it belongs to n
    	for (int i = 1; i < nums.length; i++) {
    		if(nums[i] > 0){
            	return i;
            }
        }
    	
    	if(nums[0] > 0) return n;
    	
    	return n+1;
    }
    
	public static List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> missing = new ArrayList<Integer>();
		int j = 0;
		for(int i=0; i<nums.length; i++){
			j = Math.abs(nums[i]);
			if(nums[j-1] > 0){
				nums[j-1] = -nums[j-1];
			}
		}
		
		for(int i=0; i<nums.length; i++){
			if(nums[i] > 0){
				missing.add(i+1);
			}
		}
		
		return missing;
	}
	
	public int findDuplicate(int[] nums) {
		// Find the intersection point of the two runners.
		int tortoise = nums[0];
		int hare = nums[0];
		do {
			tortoise = nums[tortoise];
			hare = nums[nums[hare]];
		} while (tortoise != hare);

		// Find the "entrance" to the cycle.
		tortoise = nums[0];
		while (tortoise != hare) {
			tortoise = nums[tortoise];
			hare = nums[hare];
		}

		return hare;
	}
    
   public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr1 = {1, 2, 0};
		System.out.println(" Missing number : " + Arrays.toString(arr1) +" is " + findMissingPositive(arr1));
		
		arr1 = new int[]{3, 4, -1, 1};
		System.out.println(" Missing number : " + Arrays.toString(arr1) +" is " + findMissingPositive(arr1));
		
		arr1 = new int[]{7, 8, 9, 11, 12};
		System.out.println(" Missing number : " + Arrays.toString(arr1) +" is " + findMissingPositive(arr1));
		
		int[] arr2 = {4,3,2,7,8,2,3,1};
		List<Integer> missing = findDisappearedNumbers(arr2);
		System.out.println(" Missing numbers : " + missing.toString());
		
		arr2 = new int[]{1};
		missing = findDisappearedNumbers(arr2);
		System.out.println(" Missing numbers : " + missing.toString());
		
		arr2 = new int[]{4, 2, 2, 4, 4};
		missing = findDisappearedNumbers(arr2);
		System.out.println(" Missing numbers : " + missing.toString());
		
		arr2 = new int[]{3, 2, 5, 6, 5, 2};
		missing = findDisappearedNumbers(arr2);
		System.out.println(" Missing numbers : " + missing.toString());
		
		arr2 = new int[]{1, 3, 2, 4, 6, 6};
		missing = findDisappearedNumbers(arr2);
		System.out.println(" Missing numbers : " + missing.toString());
		
		arr2 = new int[]{4, 3, 2, 1};
		missing = findDisappearedNumbers(arr2);
		System.out.println(" Missing numbers : " + missing.toString());
	}
}
