package com.implement.dp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class UnboundedKnapSack {
	static class Pair {
		int i;
		int j;
		String psf;

		public Pair(int i, int j, String psf) {
			this.i = i;
			this.j = j;
			this.psf = psf;
		}
	}

	private static int maxProfitMemo(int[] wt, int[] val, int W, int n,
			int[][] cache) {
		if (n == 0 || W == 0) {
			return 0;
		}

		if (cache[n][W] != -1) {
			return cache[n][W];
		}

		if (wt[n - 1] <= W) {
			cache[n][W] = Math.max(
					val[n - 1]
							+ maxProfitMemo(wt, val, W - wt[n - 1], n, cache),
					maxProfitMemo(wt, val, W, n - 1, cache));
		} else {
			cache[n][W] = maxProfitMemo(wt, val, W, n - 1, cache);
		}

		return cache[n][W];
	}

	public static int maxProfitR(int[] wt, int[] val, int W) {
		int n = wt.length;
		int cache[][] = new int[n + 1][W + 1];

		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < W + 1; j++) {
				cache[i][j] = -1;
			}
		}

		return maxProfitMemo(wt, val, W, n, cache);
	}

	public static int maxProfit(int[] wt, int[] val, int W) {
		int n = wt.length;
		int dp[][] = new int[n + 1][W + 1];

		// When n=0, we can avoid this as, by default, int value is 0
		for (int c = 0; c < W + 1; c++) {
			dp[0][c] = 0;
		}

		// When W=0, we can avoid this code as, by default, int value is 0
		for (int r = 0; r < n + 1; r++) {
			dp[r][0] = 0;
		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < W + 1; j++) {
				if (wt[i - 1] <= j) {
					dp[i][j] = Math.max(val[i - 1] + dp[i][j - wt[i - 1]],
							dp[i - 1][j]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}

		return dp[n][W];
	}

	public static void printPaths(int[] wt, int[] val, int W) {
		int n = wt.length;
		int dp[][] = new int[n + 1][W + 1];

		// When n=0, we can avoid this as, by default, int value is 0
		for (int c = 0; c < W + 1; c++) {
			dp[0][c] = 0;
		}

		// When W=0, we can avoid this code as, by default, int value is 0
		for (int r = 0; r < n + 1; r++) {
			dp[r][0] = 0;
		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < W + 1; j++) {
				if (wt[i - 1] <= j) {
					dp[i][j] = Math.max(val[i - 1] + dp[i][j - wt[i - 1]],
							dp[i - 1][j]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}

		System.out.println(dp[n][W]);

		System.out.println("Printing their indexes: ");

		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(n, W, ""));

		Pair rem = null;
		while (queue.size() > 0) {
			rem = queue.poll();

			if (rem.i == 0 || rem.j == 0) {
				System.out.println(rem.psf);
			} else {
				if (rem.j >= wt[rem.i - 1]) {
					int inc = dp[rem.i][rem.j - wt[rem.i - 1]] + val[rem.i - 1];

					if (dp[rem.i][rem.j] == inc) {
						queue.add(new Pair(rem.i, rem.j - wt[rem.i - 1],
									(rem.i - 1) + " " + rem.psf));
					}
				}

				int exc = dp[rem.i - 1][rem.j];
				if (dp[rem.i][rem.j] == exc) {
					// Exclude and do not print corresponding index
					queue.add(new Pair(rem.i - 1, rem.j, rem.psf));
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] val = { 1, 1 };
		int[] wt = { 2, 1 };
		int W = 3;

		System.out.println("Weights: " + Arrays.toString(wt) + ", Values: "
				+ Arrays.toString(val) + ", Capacity: " + W);
		System.out.println("Max profit - Memo: " + maxProfitR(wt, val, W)
				+ ", Iterative: " + maxProfit(wt, val, W));

		printPaths(wt, val, W);
		System.out.println("==========================");

		int[] val1 = { 1, 4, 5, 7 };
		int[] wt1 = { 1, 3, 4, 5 };
		W = 8;

		System.out.println("Weights: " + Arrays.toString(wt1) + ", Values: "
				+ Arrays.toString(val1) + ", Capacity: " + W);
		System.out.println("Max profit - Memo: " + maxProfitR(wt1, val1, W)
				+ ", Iterative: " + maxProfit(wt1, val1, W));

		printPaths(wt1, val1, W);
		System.out.println("==========================");

		int[] val2 = { 15, 14, 10, 45, 30 };
		int[] wt2 = { 2, 5, 1, 3, 4 };
		W = 7;

		System.out.println("Weights: " + Arrays.toString(wt2) + ", Values: "
				+ Arrays.toString(val2) + ", Capacity: " + W);
		System.out.println("Max profit - Memo: " + maxProfitR(wt2, val2, W)
				+ ", Iterative: " + maxProfit(wt2, val2, W));

		printPaths(wt2, val2, W);
	}

}
