package com.implement.arrays;

/*
 *  Given a non-empty array of integers, return the third maximum number in this array.
 *  If it does not exist, return the maximum number. The time complexity must be in O(n). 
 */

class ThirdMaximum {
	/*
	 * Function to return the third maximum number in this array or if it 
	 * does not exist, return the maximum number
	 */
	public static int thirdMax(int[] nums) {
		int max = Integer.MIN_VALUE, secondMax = Integer.MIN_VALUE, thirdMax = Integer.MIN_VALUE;
		boolean foundIntegerMin = false;
		
		for(int n: nums){
			if(n == Integer.MIN_VALUE)
				foundIntegerMin = true;
			
			if(n > max){
				thirdMax = secondMax;
				secondMax = max;
				max = n;
			}else if(n > secondMax && n < max){
				thirdMax = secondMax;
				secondMax = n;
			}else if(n > thirdMax && n < secondMax){
				thirdMax = n;
			}
		}
		
		return (thirdMax != Integer.MIN_VALUE) ? thirdMax : 
					((foundIntegerMin && secondMax > thirdMax) ? thirdMax : max);
	}

	/* Driver program to test above functions */
	public static void main(String[] args) {
		int nums[] = { 3, 2, 1 };
		System.out.println(thirdMax(nums));
		
		int nums1[] = { 1, 2 };
		System.out.println(thirdMax(nums1));
		
		int nums2[] = { 2, 2, 3, 4 };
		System.out.println(thirdMax(nums2));
		
		int nums4[] = { 2, Integer.MIN_VALUE };
		System.out.println(thirdMax(nums4));
		
		int nums5[] = { 2, 2, Integer.MIN_VALUE };
		System.out.println(thirdMax(nums5));
		
		int nums6[] = { Integer.MIN_VALUE, 2, 3 };
		System.out.println(thirdMax(nums6));
		
		int nums7[] = { 1, 2, Integer.MIN_VALUE };
		System.out.println(thirdMax(nums7));
		
		int nums8[] = { 1, Integer.MIN_VALUE, 2};
		System.out.println(thirdMax(nums8));
	}
}