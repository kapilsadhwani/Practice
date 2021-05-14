package com.implement.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//class QueueNode implements Comparable<QueueNode> {
class QueueNode {
	int array_idx, element_idx, value;

	QueueNode(int array_idx, int element_idx, int value) {
		this.array_idx = array_idx;
		this.element_idx = element_idx;
		this.value = value;
	}

	/*
	 * @Override public int compareTo(QueueNode n) { // TODO Auto-generated
	 * method stub if(value > n.value) return 1; if(value < n.value) return -1;
	 * return value - n.value; }
	 */

}

public class MergeKSortedArrays {
	public static int[] merge(Integer[][] arrays) {
		PriorityQueue<QueueNode> queue = new PriorityQueue<QueueNode>(
				arrays.length, (a, b) -> a.value - b.value);

		// Calculate result array size and insert first element from each array into Queue
		int size = 0;

		for (int i = 0; i < arrays.length; i++) {
			size += arrays[i].length;

			// Adding 1st element of each row into queue
			if (arrays[i].length > 0) {
				queue.offer(new QueueNode(i, 0, arrays[i][0]));
			}
		}

		int[] result = new int[size];
		QueueNode minNode = null;

		for (int resultIdx = 0; resultIdx < size; resultIdx++) { // or !queue.isEmpty()
			minNode = queue.poll();
			result[resultIdx] = minNode.value;

			int newIndex = minNode.element_idx + 1;

			if (newIndex < arrays[minNode.array_idx].length) {
				queue.offer(new QueueNode(minNode.array_idx, newIndex,
						arrays[minNode.array_idx][newIndex]));
			}
		}

		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer arrays[][] = { { 11, 12, 13, 14, 15 }, { 1, 3, 5 },
				{ 2, 7, 9, 10 }, { 4, 6, 8 } };

		int[] result = merge(arrays);

		System.out.println(Arrays.toString(result));
		// SortUtils.printArray(result);
	}

}
