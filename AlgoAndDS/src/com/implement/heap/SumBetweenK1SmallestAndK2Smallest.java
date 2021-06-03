package com.implement.heap;

import java.util.PriorityQueue;

public class SumBetweenK1SmallestAndK2Smallest {
	public static int findkthSmallest(int[] nums, int k) {
		if(k > nums.length) return -1; 
			
        PriorityQueue<Integer> maxheap = 
        		new PriorityQueue<Integer>(k,
        			(n1, n2) -> n2 - n1);
        
        for (int i = 0; i < k; i++) {
        	maxheap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] < maxheap.peek()) {
            	maxheap.poll();
            	maxheap.offer(nums[i]);
            }
        }
        
        return maxheap.peek();
    }
	
	public static int sumBetween(int[] nums, int k1, int k2){
		int first = findkthSmallest(nums, k1);
		int second = findkthSmallest(nums, k2);
		int sum = 0;
		
		for(int num : nums){
			if(num > first && num < second){
				sum = sum + num;
			}
		}
		
		return sum;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1, 3, 12, 5, 15, 11 };
		int k1 = 3;
		int k2 = 6;

		System.out.println(sumBetween(nums, k1, k2));
		
	}
}
