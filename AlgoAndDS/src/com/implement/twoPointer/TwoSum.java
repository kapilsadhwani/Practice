package com.implement.twoPointer;

import java.util.HashMap;
import java.util.Map;

/*
 * Given an array of integers, 
 * return indices of the two numbers such that they add up to a specific target.
 * 
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * 
 * Time complexity : O(n). We traverse the list containing n elements only once. 
 * 							Each look up in the table costs only O(1) time.
 * Space complexity : O(n). The extra space required depends on the number of items stored in the hash table, 
 * 							which stores at most n elements.
 */
 
class TwoSum{
	public static int[] twoSum(int[] nums, int target) {
	    Map<Integer, Integer> map = new HashMap<>();
	    for (int i = 0; i < nums.length; i++) {
	        int delta = target - nums[i];
	        if (map.containsKey(delta)) {
	            return new int[] { map.get(delta), i };
	        }
	        map.put(nums[i], i);
	    }
	    
	    return new int[0];
	}
 
    // Main to test the above function
    public static void main (String[] args)
    {
        int A[] = {2, 7, 11, 15};
        int n = 9;
        int[] results = twoSum(A,  n);
        
        System.out.println();
        for (int value:results){
        	System.out.print(" " + value + " ");
        }
    }
}