package com.implement.matrix;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthSmallestMatrix {
	static class MyHeapNode {
		int row;
		int column;
		int value;

		public MyHeapNode(int v, int r, int c) {
			this.value = v;
			this.row = r;
			this.column = c;
		}

		public int getValue() {
			return this.value;
		}

		public int getRow() {
			return this.row;
		}

		public int getColumn() {
			return this.column;
		}
	}

	static class MyHeapComparator implements Comparator<MyHeapNode> {
		public int compare(MyHeapNode x, MyHeapNode y) {
			return x.value - y.value;
		}
	}

	public static int kthSmallest(int[][] matrix, int k) {

		int cols = matrix[0].length;
		//int pqSize = Math.min(rows, k);

		/*PriorityQueue<MyHeapNode> minHeap = new PriorityQueue<MyHeapNode>(
				matrix.length, new Comparator<MyHeapNode>(){
					public int compare(MyHeapNode x, MyHeapNode y){
						return x.value - y.value;
					}
				});*/
		
		PriorityQueue<MyHeapNode> minHeap = new PriorityQueue<>(
				matrix.length, (x, y) -> x.value - y.value);

		// Preparing our min-heap
		for (int r = 0; r < matrix.length; r++) {

			// We add triplets of information for each cell
			if(matrix[r].length > 0)
				minHeap.offer(new MyHeapNode(matrix[r][0], r, 0));
		}

		MyHeapNode element = null;
		int count = 0;
		
		while (count < k) {

			// Extract-Min
			element = minHeap.poll();
			int r = element.getRow(), c = element.getColumn();

			// If we have any new elements in the current row, add them
			if (c < cols - 1) {
				minHeap.offer(new MyHeapNode(matrix[r][c + 1], r, c + 1));
			}
			
			count++;
		}

		return element != null ? element.getValue() : -1;
	}

	public static void main(String[] args) {
		int k = 8;
		int matrix[][] = { 
				{  1, 5, 9 }, 
				{ 10, 11, 13 },
				{ 12, 13, 15 } };

		System.out.println(kthSmallest(matrix, k));

	}

}
