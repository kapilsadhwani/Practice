package com.implement.stack;

import java.util.Arrays;
import java.util.Stack;

public class NSR_NearestSmallerToRight {
	public static void nextSmallestToRight(int nums[], int result[]) {
		Stack<Integer> stack = new Stack<Integer>();

		// Arrays.fill(result, -1);

		for (int i = nums.length - 1; i >= 0; i--) {
			if (stack.size() == 0) {
				result[i] = -1;
			} else if (stack.size() > 0 && stack.peek() < nums[i]) {
				result[i] = stack.peek();
			} else {
				while (stack.size() > 0 && stack.peek() >= nums[i]) {
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
		int nums[] = { 4, 5, 2, 10, 8 };
		int[] result = new int[nums.length];
		nextSmallestToRight(nums, result);

		System.out.println(Arrays.toString(result));
	}

}
