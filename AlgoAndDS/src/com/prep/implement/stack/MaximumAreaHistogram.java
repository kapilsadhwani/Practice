package com.prep.implement.stack;

import java.util.Arrays;
import java.util.Stack;

import javafx.util.Pair;

public class MaximumAreaHistogram {
	public static void indexOfNSL(int nums[], int index[]) {
		Stack<Pair<Integer, Integer>> stack = new Stack<Pair<Integer, Integer>>();

		// Arrays.fill(index, -1);

		for (int i = 0; i < nums.length; i++) {
			if (stack.size() == 0) {
				index[i] = -1;
			} else if (stack.peek().getKey() < nums[i]) {
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
				index[i] = nums.length;
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
		
		System.out.println(Arrays.toString(idxOfNSL));
		System.out.println(Arrays.toString(idxOfNSR));

		for (int i = 0; i < nums.length; i++) {
			width[i] = idxOfNSR[i] - idxOfNSL[i] - 1;
			area[i] = nums[i] * width[i];

			if (maxArea < area[i])
				maxArea = area[i];
		}

		return maxArea;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nums[] = {1,8,6,2,5,4,8,3,7};

		System.out.println("Maximum Area Histogram: " + maxAreaHistogram(nums));
	}

}
