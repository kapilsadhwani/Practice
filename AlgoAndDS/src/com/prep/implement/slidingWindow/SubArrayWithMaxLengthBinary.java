package com.prep.implement.slidingWindow;

import java.util.HashMap;
import java.util.Map;

//Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

public class SubArrayWithMaxLengthBinary {
	public static int findMaxLength(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		int maxlen = 0, count = 0;
		for (int i = 0; i < nums.length; i++) {
			count = count + (nums[i] == 1 ? 1 : -1);

			// Array from index 0 to i contains equal number of 0's and 1's
			if (count == 0)
				maxlen = i + 1;
			else if (map.containsKey(count)) {
				maxlen = Math.max(maxlen, i - map.get(count));
			} else {
				map.put(count, i);
			}
		}
		return maxlen;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Scanner sc = new Scanner(System.in);
		 * 
		 * int n = sc.nextInt(); int[] a = new int[n];
		 * 
		 * for(int i=0;i<n;i++){ a[i] = sc.nextInt(); }
		 * 
		 * sc.close();
		 */

		int arr[] = { 0, 1 };
		System.out.println(findMaxLength(arr));

		int arr1[] = { 0, 1, 0 };
		System.out.println(findMaxLength(arr1));
		
		int arr2[] = { 0, 0, 0, 0 };
		System.out.println(findMaxLength(arr2));
		
		int arr3[] = { 1, 1, 1, 1 };
		System.out.println(findMaxLength(arr3));
	}
}