package com.implement.dp;

/*
 * Given an infinite number of quarters, dimes, nickels and pennies, write code to calculate 
 * number of ways of representing n cents
 */

public class CoinPlay {
	static int makeChangeMemo(int[] coins, int A, int n, int[][] cache) {
		// If change is 0 there is 1 way to achieve it
		if (A == 0)
			return 1;

		if (n == 0)
			return 0; // No items and amount > 0

		if (cache[n][A] != -1) {// retrieve value
			return cache[n][A];
		}

		if (coins[n - 1] <= A) {
			cache[n][A] = makeChangeMemo(coins, A - coins[n - 1], n, cache) + 
								makeChangeMemo(coins, A, n - 1, cache);
		} else {
			cache[n][A] = makeChangeMemo(coins, A, n - 1, cache);
		}

		return cache[n][A];
	}

	static int makeChangeAV(int[] coins, int A) {
		int n = coins.length;

		// precomputed vals (n+1 --> starting from 0)
		int[][] cache = new int[n + 1][A + 1];

		for (int i = 0; i < n + 1; i++)
			for (int j = 0; j < A + 1; j++)
				cache[i][j] = -1;

		return makeChangeMemo(coins, A, n, cache);
	}

	// AV - Iterative way
	// Combinations
	static int makeChangeTopDownAV(int[] coins, int A) {
		int n = coins.length;
		int t[][] = new int[n + 1][A + 1];

		// Initialization 
		// Coin array is empty then 0
		for (int j = 1; j < A + 1; j++) {
			t[0][j] = 0;
		}

		// Amount = 0 then 1 way
		for (int i = 0; i < n + 1; i++) {
			t[i][0] = 1;
		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < A + 1; j++) {
				if (coins[i - 1] <= j) {
					t[i][j] = t[i][j - coins[i - 1]] + // Include this item
							t[i - 1][j]; 				// Exclude this item
				} else {
					t[i][j] = t[i - 1][j]; // Exclude this item
				}
			}
		}

		return t[n][A];
	}
	
	// Unique combinations
	static long makeChangeUniqueCombo(int[] coins, int A) {
		long[] dp = new long[A + 1];
		dp[0] = 1;
		
		for(int coin : coins){
			for (int amt = coin; amt < dp.length; amt++) {
				dp[amt] = dp[amt] + dp[amt - coin];
			}
			
		}

		return dp[A];
	}

	// Permutation i.e. Combinations with duplicates allowed - like 2,1,1 is different from 1,1,2
	static long makeChange(int[] coins, int A) {
		long[] dp = new long[A + 1];
		dp[0] = 1;
		
		for (int amt = 1; amt < dp.length; amt++){
			long total = 0;
			for (int coin: coins){
				if(amt >= coin){
					total = total + dp[amt - coin];
				}
			}
			
			dp[amt] = total;
		}
		
		return dp[A];
	}

	public static void main(String[] args) {
		int[] coins = { 2, 3, 5, 6 };
		for (int i = 1; i <= 100; ++i)
			System.out.println(i + " IK: " + makeChange(coins, i)
					+ " AV - Memo: " + makeChangeAV(coins, i)
					+ " AV - Iter: " + makeChangeTopDownAV(coins, i)
					+ " Unique Combo - Iter: " + makeChangeUniqueCombo(coins, i));
	}
}