package com.implement.twoPointer;

class SquaresOfSortedArray {
	public int[] sortedSquares(int[] A) {
		int n = A.length;
		int j = 0;
		while (j < n && A[j] < 0)
			j++;

		int i = j - 1;

		int[] ans = new int[n];
		int k = 0;

		while (i >= 0 && j < n) {
			if (A[i] * A[i] < A[j] * A[j]) {
				ans[k++] = A[i] * A[i];
				i--;
			} else {
				ans[k++] = A[j] * A[j];
				j++;
			}
		}

		while (i >= 0) {
			ans[k++] = A[i] * A[i];
			i--;
		}

		while (j < n) {
			ans[k++] = A[j] * A[j];
			j++;
		}

		return ans;
	}
}