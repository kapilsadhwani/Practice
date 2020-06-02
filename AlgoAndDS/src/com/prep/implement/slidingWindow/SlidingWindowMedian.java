package com.prep.implement.slidingWindow;

import java.util.PriorityQueue;

class SlidingWindowMedian {
	// max queue is always larger or equal to min queue
	PriorityQueue<Integer> minHeap;
	PriorityQueue<Integer> maxHeap;

	/** initialize your data structure here. */
	public SlidingWindowMedian() {
		minHeap = new PriorityQueue<Integer>();

		/*
		 * maxHeap = new PriorityQueue(1000, Collections.reverseOrder());
		 */

		maxHeap = new PriorityQueue<Integer>(1000, (a, b) -> b - a);
	}

	public void rebalance() {
		if (minHeap.size() > maxHeap.size())
			maxHeap.offer(minHeap.poll());

		/* 
		 * Max heap can has one extra node than min heap, as we will use for
		 * returning the median when total input size is odd number
		 */

		else if (maxHeap.size() > minHeap.size() + 1)
			minHeap.offer(maxHeap.poll());
	}
	
	void remove(int num) {
		if (num <= maxHeap.peek())
			maxHeap.remove(num);
		else
			minHeap.remove(num);
		
		rebalance();
	}

	// Adds a number into the data structure.
	public void addNum(int num) {
		if (maxHeap.size() == 0 || num <= maxHeap.peek()) {
			maxHeap.offer(num);
		} else {
			minHeap.offer(num);
		}

		rebalance();
	}

	// Returns the median of current data stream
	public double findMedian() {
		if (maxHeap.size() == minHeap.size())
			return (maxHeap.peek() + minHeap.peek()) / 2.0;
		else
			return maxHeap.peek();
	}
	
	public double[] medianSlidingWindow(int[] nums, int k) {
		int n = nums.length;

		if (n == 0 || k == 0)
			return new double[0];
		
		double[] result = new double[n - k + 1];

		int start = 0;
		for (int end = 0; end < n; end++) {
			addNum(nums[end]);
			
			/*
			 * Shrink the window & calculate the median, k starts from 0
			 */
			if (end + 1 >= k) {
				result[start] = findMedian();
				
				// remove the start number as we move forward the next window size
				remove(nums[start]); 
				
				start++; // move start window ahead
			}
		}
		
		return result;
	}
}