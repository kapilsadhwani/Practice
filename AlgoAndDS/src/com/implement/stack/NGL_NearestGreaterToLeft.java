package com.implement.stack;

import java.util.Arrays;
import java.util.Stack;

import javafx.util.Pair;

public class NGL_NearestGreaterToLeft {
	public static void nextLargestToLeft(int nums[], int result[]) {
		Stack<Integer> stack = new Stack<Integer>();

		// Arrays.fill(result, -1);

		for (int i = 0; i < nums.length; i++) {
			if (stack.size() == 0) {
				result[i] = -1;
			} else if (stack.peek() > nums[i]) {
				result[i] = stack.peek();
			} else {
				while (stack.size() > 0 && stack.peek() <= nums[i]) {
					stack.pop();
				}

				if (stack.size() == 0) {
					result[i] = -1;
				} else {
					result[i] = stack.peek();
				}
			}

			stack.push(nums[i]);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nums[] = { 1, 3, 2, 4 };
		int[] result = new int[nums.length];
		nextLargestToLeft(nums, result);

		System.out.println(Arrays.toString(result));
	}

}
