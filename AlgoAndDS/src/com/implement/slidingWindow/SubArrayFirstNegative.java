package com.implement.slidingWindow;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class SubArrayFirstNegative {
	public static int[] subArrayFirstNegative(int[] nums, int k) {
		Queue<Integer> queue = new LinkedList<Integer>();
		int size = nums.length;
		
		int[] result = new int[size - k + 1];
		int windowStart = 0;
		int i = 0;

		for (int windowEnd = 0; windowEnd < size; windowEnd++) {
			if(nums[windowEnd] < 0)
				queue.add(nums[windowEnd]);
			
			if(windowEnd - windowStart + 1 == k){	// i.e window size = k
				if(queue.size() == 0){
					result[i++] = 0;
				}else{
					result[i++] = queue.peek();
					
					if(nums[windowStart] == queue.peek()){
						queue.poll();
					}
				}
				
				windowStart++;
			}
		}

		return result;
	}
	
	public static void main(String[] args) {
		int[] arr = { -8, 2, 3, -6, 10 };
		int k = 2;
		System.out.println("Input: " + Arrays.toString(arr));
		System.out.println("Output: " + Arrays.toString(subArrayFirstNegative(arr, k)));

		int[] arr1 = { 12, -1, -7, 8, -15, 30, 16, 28 };
		k = 3;
		System.out.println("Input: " + Arrays.toString(arr));
		System.out.println("Output: " + Arrays.toString(subArrayFirstNegative(arr1, k)));
	}
}