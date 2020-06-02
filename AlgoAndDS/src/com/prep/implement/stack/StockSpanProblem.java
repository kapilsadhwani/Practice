package com.prep.implement.stack;

import java.util.Arrays;
import java.util.Stack;

import javafx.util.Pair;

public class StockSpanProblem {
	public static void indexOfNGL(int nums[], int index[]) {
		Stack<Pair<Integer, Integer>> stack = new Stack<Pair<Integer, Integer>>();

		// Arrays.fill(index, -1);

		for (int i = 0; i < nums.length; i++) {
			if (stack.size() == 0) {
				index[i] = -1;
			} else if (stack.size() > 0 && stack.peek().getKey() > nums[i]) {
				index[i] = stack.peek().getValue();
			} else {
				while (stack.size() > 0 && stack.peek().getKey() <= nums[i]) {
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nums[] = { 100, 80, 60, 70, 60, 75, 85 };
		int[] index = new int[nums.length];
		int[] result = new int[nums.length];
		indexOfNGL(nums, index);

		for (int i = 0; i < nums.length; i++) {
			result[i] = i - index[i];
		}

		System.out.println(Arrays.toString(result));

		int nums1[] = { 10, 4, 5, 90, 120, 80 };
		index = new int[nums1.length];
		result = new int[nums1.length];
		indexOfNGL(nums1, index);

		for (int i = 0; i < nums1.length; i++) {
			result[i] = i - index[i];
		}

		System.out.println(Arrays.toString(result));
	}

}
