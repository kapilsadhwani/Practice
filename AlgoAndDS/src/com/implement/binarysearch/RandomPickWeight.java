package com.implement.binarysearch;

public class RandomPickWeight {
	private int[] prefixSum;
	private int totalSum;

	public RandomPickWeight(int[] w) {
		prefixSum = new int[w.length];

		int sum = 0;

		for (int i = 0; i < w.length; i++) {
			sum = sum + w[i];
			prefixSum[i] = sum;
		}

		this.totalSum = sum;
	}

	public int pickIndex() {
		double target = this.totalSum * Math.random();

		int low = 0;
		int high = prefixSum.length - 1;
		int res = -1;

		// Find ceiling
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (prefixSum[mid] == target)
				return mid;
			else if (prefixSum[mid] > target) {
				res = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
