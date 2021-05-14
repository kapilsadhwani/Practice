package com.implement.slidingWindow;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
	public int[] maxSlidingWindow(int[] nums, int k) {
		int n = nums.length;

		if (n == 0 || k == 0 || n < k)
			return new int[0];

		if (k == 1)
			return nums;

		Deque<Integer> dq = new LinkedList<Integer>();
		int ans[] = new int[n - k + 1];

		int i = 0;

		while (i < k) {
			// Keep removing smaller elements from DQ
			while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
				dq.pollLast();
			}
			dq.offerLast(i); // Store index in Deque

			i++;
		}

		// i = k at this point
		while (i < n) {
			ans[i - k] = nums[dq.peekFirst()];

			/*
			 * If first element goes out of window, remove it and add next new
			 * element in the sliding window and find out max in the current
			 * window of size k
			 * 
			 * It may not happen all the time, hence checking !!!
			 */
			if (!dq.isEmpty() && dq.peekFirst() == i - k) {
				dq.pollFirst();
			}

			// Again keep removing smaller elements from DQ
			while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
				dq.pollLast();
			}
			dq.offerLast(i);

			i++;
		}

		// For last element remaining in DQ
		ans[i - k] = nums[dq.peekFirst()];

		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SlidingWindowMaximum swm = new SlidingWindowMaximum();

		int nums[];

		nums = new int[] { 4, 3, 1, 2, 5, 3, 4, 7, 1, 9 };
		System.out.println(Arrays.toString(swm.maxSlidingWindow(nums, 4)));

		nums = new int[] { 7, 2, 4 };
		System.out.println(Arrays.toString(swm.maxSlidingWindow(nums, 2)));
	}

}
