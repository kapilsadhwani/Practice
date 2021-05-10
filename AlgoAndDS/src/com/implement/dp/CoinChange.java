package com.implement.dp;

import java.util.Arrays;

public class CoinChange {
	public static int coinChangeMemo(int[] coins, int change, int[] cache) {
		if (change == 0) 
        	return 0;

        if (cache[change] != -2)	//retrieve value
            return cache[change];

        int min = Integer.MAX_VALUE;
        for (Integer coin : coins){
        	if(change >= coin){
	        	int count = coinChangeMemo(coins, change - coin, cache);
	        	
	        	if(count == -1) continue;
	        	
	        	if (count + 1 < min){	// +1 is for this coin
	                min = count + 1;
	        	}
        	}
        }

        cache[change] = (min == Integer.MAX_VALUE) ? -1 : min;
        return cache[change];
    }
    
    public static int memoization(int[] coins, int change) {
    	int[] cache = new int[change + 1];
        
        Arrays.fill(cache,  -2);
        
        cache[0] = 1;
        
        return coinChangeMemo(coins, change, cache);
    }
    
    private static int coinChangeAVMemo(int[] coins, int C, int n, int[][] cache) {
		if (C == 0) {
			return 0;
		}
		
		if(n == 0)
			return -1;	// Some invalid number

		if (cache[n][C] != -2) {
			return cache[n][C];
		}
		
		if (coins[n - 1] <= C) {
			/*cache[n][C] = Math.min(
					1 + coinChangeAVMemo(coins, C - coins[n - 1], n, cache),
					coinChangeAVMemo(coins, C, n - 1, cache));*/
			
			int minCoins = Integer.MAX_VALUE;

			// Select this coin
			int count = coinChangeAVMemo(coins, C - coins[n - 1], n, cache);
        	
        	if(count != -1){
        		minCoins = count + 1;	// +1 is for this coin
        	}
        	
        	// Do not select this coin
        	count = coinChangeAVMemo(coins, C, n - 1, cache);
        	if (count != -1 && count < minCoins){
        		minCoins = count;
        	}
			
        	cache[n][C] = minCoins == Integer.MAX_VALUE ? -1 : minCoins;
		} else {        	
			cache[n][C] = coinChangeAVMemo(coins, C, n - 1, cache);
		}

		return cache[n][C];
	}

	public static int coinChangeAV(int[] coins, int C) {
		int n = coins.length;
		int cache[][] = new int[n + 1][C + 1];

		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < C + 1; j++) {
				cache[i][j] = -2;
			}
		}

		return coinChangeAVMemo(coins, C, n, cache);
	}

	// AV - Iterative way
	static int coinChangeTopDownAV(int[] coins, int C) {
		int n = coins.length;
		int t[][] = new int[n + 1][C + 1];

		// Initialization
		// Amount = 0 then 0 coins
		for (int i = 1; i < n + 1; i++) {
			t[i][0] = 0;
		}
		
		// Coin array is empty then INT_MAX - 1  (First Row)
		for (int j = 1; j < C + 1; j++) {
			t[0][j] = Integer.MAX_VALUE - 1;
		}
		
		// If there is a single coin and C is divisible by coins[0] 
		// then (C / coins[0]) else INT_MAX - 1 (Second Row)
		for (int j = 1; j < C + 1; j++) {
			if(j % coins[0] == 0)
				t[1][j] = j / coins[0];
			else
				t[1][j] = Integer.MAX_VALUE - 1;
		}

		for (int i = 2; i < n + 1; i++) {
			for (int j = 1; j < C + 1; j++) {
				if (coins[i - 1] <= j) {
					t[i][j] = Math.min(1 + t[i][j - coins[i - 1]], // Include this item
										t[i - 1][j]); 				// Exclude this item
				} else {
					t[i][j] = t[i - 1][j]; // Exclude this item
				}
			}
		}

		return t[n][C];
	}

    public static int dynamicProgramming(int[] coins, int change) {
        int minCoins[] = new int[change + 1];

        minCoins[0] = 0;

        for (int c = 1; c <= change; ++c) {  // c is the change of the recursion
            int min = c;
            for (int coin : coins)
                if (c >= coin)
                    min = Math.min(min, 1 + minCoins[c - coin]);
            minCoins[c] = min;
        }

        return minCoins[change];
    }


    public static void main(String[] args) {
        int[] coins = new int[]{5, 3, 2};

        for (int change = 1; change < 100; ++change) {
            System.out.print(" Change for " + change + " : Memo: " + memoization(coins, change));
            System.out.print(" Iter: " + dynamicProgramming(coins, change));
            System.out.print(" AV - Memo: " + coinChangeAV(coins, change));
            System.out.println(" AV - Iter: " + coinChangeTopDownAV(coins, change));
        }
    }
}
