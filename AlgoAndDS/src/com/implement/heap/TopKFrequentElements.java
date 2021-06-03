package com.implement.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class TopKFrequentElements {     
	public static int[] topKFrequent(int[] nums, int k) {
		if(nums == null || nums.length == 0) return new int[]{};
		
		// build hash map : character and how often it appears
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int n : nums) {
			map.put(n, map.getOrDefault(n, 0) + 1);
		}

		// init heap 'the less frequent element first'
		/*
		 * PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k, (n1,
		 * n2) -> map.get(n1) - map.get(n2));
		 */

		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k,
				new Comparator<Integer>() {
					@Override
					public int compare(Integer n1, Integer n2) {
						return map.get(n1) - map.get(n2);
					}
				});

		// keep k top frequent elements in the heap
		for (int n : map.keySet()) {
			if (minHeap.size() == k){
				if(map.get(minHeap.peek()) < map.get(n)){
					minHeap.poll();
					minHeap.offer(n);
				}
			}else{
				minHeap.offer(n);
			}
		}

		int[] topK = new int[k];

		for (int idx = k - 1; idx >= 0; idx--) {
			topK[idx] = minHeap.poll();
		}
		
		return topK;
	}
     
     public static void main(String[] args) {
 		// TODO Auto-generated method stub
    	int[] array1 = { 1,1,1,2,2,3};
    	int[] array2 = {1,2,4,4,5,5,6,6,6,7,7,7,8};
    	int[] array3 = {1};
    	int[] result = null;
 		
 		result = topKFrequent(array1,2);
 		System.out.println(Arrays.toString(result));
 		
 		result = topKFrequent(array2,4);
 		System.out.println(Arrays.toString(result));
 		
 		result = topKFrequent(array3,1);
 		System.out.println(Arrays.toString(result));
 	}
}