package com.implement.arrays;


public class FindDuplicate {
	/**
     * @param nums: an array of integers
     * @return: an integer
     */
    public static int findMissingNumberXOR(int[] nums) {
    	int n = nums.length;
    	
    	// For xor of all the elements in array 
        int x1 = nums[0]; 
      
        // For xor of all the elements from 1 to n+1 
        int x2 = 0; 
      
        for (int i = 1; i < n; i++) 
            x1 = x1 ^ nums[i]; 
      
        for (int i = 1; i <= n; i++) 
            x2 = x2 ^ i; 
      
        return (x1 ^ x2); 
    }
    
    public static int findMissingNumberSummation(int[] nums) {
        int n = nums.length;
        int result = (n+1)*(n+2)/2;
        
        for(int i=0; i<nums.length; i++){
        	result = result - nums[i];
        }
        
        return result;

    }
    
	public static void findMissingNumbersXOR(int[] nums) {
		/*
		 * Get the XOR of all elements in arr[] and {1, 2 .. n}
		 */
		int XOR = nums[0];
		for (int i = 1; i < nums.length; i++)
			XOR = XOR ^ nums[i];

		// Now XOR has XOR of two missing elements.
		// Any set bit in it must be set in one missing and unset in other missing number

		// Get a set bit of XOR (We get the rightmost set bit)
		int set_bit_no = XOR & ~(XOR - 1);
																			
		// Now divide elements in two sets by comparing
		// rightmost set bit of XOR with bit at same
		// position in each element.
		int x = 0, y = 0; // Initialize missing numbers
		for (int i = 0; i < nums.length; i++) {
			if ((nums[i] & set_bit_no) > 0)
				/* XOR of first set in arr[] */
				x = x ^ nums[i];
			else
				/* XOR of second set in arr[] */
				y = y ^ nums[i];
		}
		
		if(x > y){			// Swap x,y, if want to always display smaller number first
			x = XOR ^ x;	// At this point, x and y point to same number. Use any to get the other number in y
			y = XOR ^ y;
		}

		System.out.println("Two Missing Numbers are ");
		System.out.println(x + " " + y);

	}
	
	/**
     * @param nums: an array of integers
     * @return: an integer
     * 
     * Given a non-empty array of integers, every element appears twice except for one. 
     * Find that single one.
     */
    public static int singleNumber(int[] nums) {
    	// For xor of all the elements in array 
        int missing = nums[0]; 
      
        for (int i = 1; i < nums.length; i++) 
        	missing = missing ^ nums[i]; 
      
        return missing; 
    }
    
	public static int findDuplicate(int[] nums) {
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
		int[] arr1 = { 3,0,1 };
		System.out.println("XOR method : " + findMissingNumberXOR(arr1));
		System.out.println("Summation method : " + findMissingNumberSummation(arr1));
		
		int[] arr2 = {9,6,4,2,3,5,7,0,1};
		System.out.println("\nXOR method : " + findMissingNumberXOR(arr2));
		System.out.println("Summation method : " + findMissingNumberSummation(arr2));
		
		int[] arr3 = {1, 2, 3, 2, 1, 4};
		findMissingNumbersXOR(arr3);
		
		int[] arr4 = {2, 1, 3, 2};
		findMissingNumbersXOR(arr4);
		System.out.println("H and T: " + findDuplicate(arr4));
		
		int[] arr5 = { 2, 2, 1 };
		System.out.println("XOR method : " + singleNumber(arr5));
		
		int[] arr6 = {4, 1, 2, 1, 2};
		System.out.println("\nXOR method : " + singleNumber(arr6));
	}
}
