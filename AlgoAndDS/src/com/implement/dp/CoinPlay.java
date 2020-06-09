package com.implement.dp;

public class CoinPlay {
	static int makeChangeMemo(int amount, int[] coins, int n, int[][] cache) {
		 // If change is 0 or only one denom remaining then there is 1 way to achieve it
		if(amount == 0) return 1;
		
		if(n == 0) return 0;	// No items and C > 0
		
		if (cache[n][amount] != -1) {//retrieve value
			return cache[n][amount];
		}
		
		if(amount >= coins[n-1]){
			cache[n][amount] = makeChangeMemo(amount - coins[n-1], coins, n, cache) +
					makeChangeMemo(amount, coins, n-1, cache);
		}else{
			cache[n][amount] = makeChangeMemo(amount, coins, n-1, cache);
		}

		return cache[n][amount];
	}
	
	static int makeChangeAV(int amount, int[] coins) {
		int n = coins.length;

		// precomputed vals (n+1 --> starting from 0)
		int[][] cache = new int[n + 1][amount + 1];

		for (int i = 0; i < n + 1; i++)
			for (int j = 0; j < amount + 1; j++)
				cache[i][j] = -1;

		return makeChangeMemo(amount, coins, n, cache);
	}
	
	// Combinations with duplicates allowed - like 2,1,1,1 is different from 1,1,2,1
	static int makeChange(int n, int[] denoms) {
		int[][] cache = new int[n+1][denoms.length]; // precomputed vals (n+1 --> starting from 0)
		return makeChange(n, denoms, 0, cache);
	}

	static int makeChange(int amount, int[] denoms, int pos, int[][] map) {
		if (map[amount][pos] > 0) {//retrieve value
			return map[amount][pos];
		}
		
		if(amount == 0) return 1;
		
		if (pos >= denoms.length - 1) return 1; // one denom remaining
		int denomAmount = denoms[pos];
		int ways = 0;
		for (int i= 0; i * denomAmount <= amount; i++) {
			//go to next denom, assuming i coins of denomAmount
			int amountRemaining = amount - i * denomAmount;
			
			// index+1 means previous denom are not available again for counting ways
			ways = ways + makeChange(amountRemaining, denoms, pos + 1, map);
		}
		map[amount][pos] = ways;
		return ways;
	}

    public static void main(String[] args) {
    	int[] coins = { 1, 2, 5 };
    	for (int i = 1; i <= 100; ++i)
            System.out.println(i + " IK ==> " + makeChange(i, coins) + " AV ==> " + makeChangeAV(i, coins));
    }
}