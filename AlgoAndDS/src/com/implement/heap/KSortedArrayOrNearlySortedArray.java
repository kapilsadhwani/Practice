package com.implement.heap;

import java.util.PriorityQueue;

import com.implement.sort.SortUtils;

public class KSortedArrayOrNearlySortedArray {
	/*
	 * @param nums an integer array
	 * 
	 * @param k an integer
	 * 
	 * Sort a K Sorted Array | Sort Nearly Sorted Array
	 */
	public static Integer[] sort(Integer[] nums, int k) {
		PriorityQueue<Integer> minheap = new PriorityQueue<Integer>(k + 1);
		Integer[] result = new Integer[nums.length];

		int j = 0;
		
		for (int num: nums) {
			minheap.offer(num);
			
            if (minheap.size() > k) {
            	result[j++] = minheap.poll();
            }
        }
		
		while(minheap.size() > 0){
			result[j++] = minheap.poll();
		}

		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] array1 = { 6, 5, 3, 2, 8, 10, 9 };
		Integer[] result = null;

		result = sort(array1, 3);
		SortUtils.printArray(result);
	}
}