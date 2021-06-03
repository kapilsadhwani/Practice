package com.implement.pepcoding.dp;

import java.util.HashMap;
import java.util.Map;

//Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

public class SubArrayWithMaxLengthOrMaxDiffBinary {
	public static int findMaxLength(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		int maxlen = 0, count = 0;
		int val = 0;

		for (int i = 0; i < nums.length; i++) {
			
			if (nums[i] == 0)
				val = -1;
			else if (nums[i] == 1) {
				val = 1;
			}
			
			count = count + val;

			// Array from index 0 to i contains equal number of 0's and 1's
			if (count == 0){
				maxlen = i + 1;
			}else{
				if (map.containsKey(count)) {
					// Subarray with equal number of 0's and 1's may not start from 0
					int j = map.get(count);
					maxlen = Math.max(maxlen, i - j);
				} else {
					map.put(count, i);
				}
			}
		}
		return maxlen;
	}
	
	// Maximum Difference of 0's and 1's : Number(0's) - Number(1's)
	public static int findMaxDifference(String str) {
		int max = 0;
		int currSum = 0;
		
		for (int i = 0; i < str.length(); i++) {
			int val = 0;

			if (str.charAt(i) == '0')
				val = 1;
			else if (str.charAt(i) == '1') {
				val = -1;
			}

			/*if (currSum > 0) {
				currSum = currSum + val;
			} else {
				currSum = val;
			}*/
			
			if (currSum + val < val) {
				currSum = val;
			} else {
				currSum = currSum + val;
			}

			if (currSum > max) {
				max = currSum;
			}
		}
		
		return max == 0 ? -1 : max;
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

		int arr1[] = { 1, 1, 0, 1, 0, 1 };
		System.out.println(findMaxLength(arr1));
		
		int arr2[] = { 0, 0, 0, 0 };
		System.out.println(findMaxLength(arr2));
		
		int arr3[] = { 1, 1, 1, 1 };
		System.out.println(findMaxLength(arr3));
		
		System.out.println(findMaxDifference("11000010001"));
	}
}