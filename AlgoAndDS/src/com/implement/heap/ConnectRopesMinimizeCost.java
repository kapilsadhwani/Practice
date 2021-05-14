package com.implement.heap;

import java.util.PriorityQueue;

public class ConnectRopesMinimizeCost {

	public static int minCost(int[] nums) {
		int cost = 0;

		if (nums == null || nums.length == 0)
			return 0;

		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

		// keep k top frequent elements in the heap
		for (int n : nums) {
			minHeap.offer(n);
		}

		while (minHeap.size() > 1) {
			int first = minHeap.poll();
			int second = minHeap.poll();

			cost = cost + first + second;
			minHeap.offer(first + second);
		}

		return cost;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ropes = { 1, 2, 3, 4, 5 };
		System.out.println(minCost(ropes));
	}

}
