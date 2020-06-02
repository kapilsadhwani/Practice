package com.prep.implement.arrays;

public class ArrayProblems {
	public static int removeDuplicates(int[] nums) {
	    if (nums.length == 0) return 0;
	    int i = 0;
	    for (int j = 1; j < nums.length; j++) {
	        if (nums[j] != nums[i]) {
	            i++;
	            nums[i] = nums[j];
	        }
	    }
	    return i + 1;
	}
	
	public static int removeElement(int[] nums, int val) {
	    int i = 0;
	    for (int j = 0; j < nums.length; j++) {
	        if (nums[j] != val) {
	            nums[i] = nums[j];
	            i++;
	        }
	    }
	    return i;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[]{1,1,2,2,3,3,4,5,6,7,8,8,8,9,9};
		int length = removeDuplicates(nums);
		
		for(int i=0;i<length;i++){
			System.out.print(nums[i] + " ");
		}
		
		
		nums = new int[]{4,8,2,6,4,5,9,7,3,1,6,5,5};
		length = removeElement(nums,5);
		
		System.out.println();
		for(int i=0;i<length;i++){
			System.out.print(nums[i] + " ");
		}
	}

}
