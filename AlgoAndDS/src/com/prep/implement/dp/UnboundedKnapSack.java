package com.prep.implement.dp;

public class UnboundedKnapSack {
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
		int cache[][] = new int[n + 1][W + 1];

		// When n=0
		for (int c = 0; c < W + 1; c++) {
			cache[0][c] = 0;
		}

		// When W=0
		for (int r = 0; r < n + 1; r++) {
			cache[r][0] = 0;
		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < W + 1; j++) {
				if (wt[i - 1] <= j) {
					cache[i][j] = Math.max(
							val[i - 1] + cache[i][j - wt[i - 1]],
							cache[i - 1][j]);
				} else {
					cache[i][j] = cache[i - 1][j];
				}
			}
		}

		return cache[n][W];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] val = {1,1};
		int[] wt = {2,1};
		int W = 3;

		System.out.println("\n\n Number profit - Memo: " + maxProfitR(wt, val, W) + ", Iterative: " + maxProfit(wt, val, W));
		System.out.println("\n\n==========================");
		
		int[] val1 = {1,4,5,7};
		int[] wt1= {1,3,4,5};
		W = 8;
		
		System.out.println("\n\n Number profit - Memo: " + maxProfitR(wt1, val1, W) + ", Iterative: " + maxProfit(wt1, val1, W));
	}

}
