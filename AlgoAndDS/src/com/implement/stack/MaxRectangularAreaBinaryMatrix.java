package com.implement.stack;

import java.util.Stack;

import javafx.util.Pair;

public class MaxRectangularAreaBinaryMatrix {
	public static void indexOfNSL(int nums[], int index[]) {
		Stack<Pair<Integer, Integer>> stack = new Stack<Pair<Integer, Integer>>();

		// Arrays.fill(index, -1);

		for (int i = 0; i < nums.length; i++) {
			if (stack.size() == 0) {
				index[i] = -1;
			} else if (stack.size() > 0 && stack.peek().getKey() < nums[i]) {
				index[i] = stack.peek().getValue();
			} else {
				while (stack.size() > 0 && stack.peek().getKey() >= nums[i]) {
					stack.pop();
				}

				if (stack.size() == 0) {
					index[i] = -1;
				} else {
					index[i] = stack.peek().getValue();
				}
			}

			stack.push(new Pair<Integer, Integer>(nums[i], i));
		}
	}

	public static void indexOfNSR(int nums[], int index[]) {
		Stack<Pair<Integer, Integer>> stack = new Stack<Pair<Integer, Integer>>();

		// Arrays.fill(result, -1);

		for (int i = nums.length - 1; i >= 0; i--) {
			if (stack.size() == 0) {
				index[i] = -1;
			} else if (stack.size() > 0 && stack.peek().getKey() < nums[i]) {
				index[i] = stack.peek().getValue();
			} else {
				while (stack.size() > 0 && stack.peek().getKey() >= nums[i]) {
					stack.pop();
				}

				if (stack.size() == 0) {
					index[i] = nums.length;
				} else {
					index[i] = stack.peek().getValue();
				}
			}

			stack.push(new Pair<Integer, Integer>(nums[i], i));
		}
	}

	public static int maxAreaHistogram(int nums[]) {
		int[] idxOfNSL = new int[nums.length];
		int[] idxOfNSR = new int[nums.length];
		int[] width = new int[nums.length];
		int[] area = new int[nums.length];
		int maxArea = 0;

		indexOfNSL(nums, idxOfNSL);
		indexOfNSR(nums, idxOfNSR);

		for (int i = 0; i < nums.length; i++) {
			width[i] = idxOfNSR[i] - idxOfNSL[i] - 1;
			area[i] = nums[i] * width[i];

			if (maxArea < area[i])
				maxArea = area[i];
		}

		return maxArea;
	}

	public static int maxAreaHistogram(int matrix[][]) {
		int[] nums = new int[matrix[0].length];

		for (int j = 0; j < nums.length; j++) {
			nums[j] = matrix[0][j];
		}
		int maxArea = maxAreaHistogram(nums);

		for (int i = 1; i < matrix.length; i++) {
			
			for (int j = 0; j < nums.length; j++) {
				if (matrix[i][j] == 0)
					nums[j] = 0;
				else
					nums[j] = nums[j] + matrix[i][j];
			}

			maxArea = Math.max(maxArea, maxAreaHistogram(nums));
		}

		return maxArea;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int matrix[][] = { { 0, 1, 1, 0 }, 
				           { 1, 1, 1, 1 }, 
				           { 1, 1, 1, 1 },
				           { 1, 1, 0, 0 } };
		System.out.print("Area of maximum rectangle is "
				+ maxAreaHistogram(matrix));
	}

}
