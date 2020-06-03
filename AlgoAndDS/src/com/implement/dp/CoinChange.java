package com.implement.dp;

import java.util.Arrays;

public class CoinChange {
	public static int coinChangeMemo(int[] coins, int change, int[] cache) {
		if (change == 0) 
        	return 0;

        if (cache[change] != -2)
            return cache[change];

        int min = Integer.MAX_VALUE;
        for (Integer coin : coins){
        	if(change >= coin){
	        	int count = coinChangeMemo(coins, change - coin, cache);
	        	
	        	if(count == -1) continue;
	        	
	        	if (count + 1 < min)
	                min = count + 1;
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
    
    private static int coinChangeAVMemo(int[] coins, int C, int n,	int[][] cache) {
		if (C == 0) {
			return 0;
		}
		
		if(n == 0)
			return C + 1;	// Some invalid number

		if (cache[n][C] != -1) {
			return cache[n][C];
		}

		if (coins[n - 1] <= C) {
			cache[n][C] = Math.min(
					1 + coinChangeAVMemo(coins, C - coins[n - 1], n, cache),
					coinChangeAVMemo(coins, C, n - 1, cache));
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
				cache[i][j] = -1;
			}
		}

		return coinChangeAVMemo(coins, C, n, cache);
	}

    public static int dynamicProgramming(int[] coins, int change) {
        int minCoins[] = new int[change + 1];

        minCoins[0] = 0;

        for (int c = 1; c <= change; ++c) {  // c is the change of the recursion
            int min = c;
            for (Integer coin : coins)
                if (c >= coin)
                    min = Math.min(min, 1 + minCoins[c - coin]);
            minCoins[c] = min;
        }

        return minCoins[change];
    }


    public static void main(String[] args) {
        int[] coins = new int[]{1, 5, 10, 25};

        for (int change = 1; change < 45; ++change) {
            System.out.print(" Change for " + change + " : Memoization: " + memoization(coins, change));
            System.out.print(" Dynamic Programming: " + dynamicProgramming(coins, change));
            System.out.println(" AV: " + coinChangeAV(coins, change));
        }
    }
}
