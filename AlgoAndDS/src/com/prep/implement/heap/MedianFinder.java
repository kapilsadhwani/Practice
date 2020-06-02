package com.prep.implement.heap;

import java.util.PriorityQueue;

class MedianFinder {
	// max queue is always larger or equal to min queue
	PriorityQueue<Integer> minHeap;
	PriorityQueue<Integer> maxHeap;

	/** initialize your data structure here. */
	public MedianFinder() {
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
};