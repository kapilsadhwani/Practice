package com.prep.implement.dp;

public class CoinPlay {
	static int makeChangeMemo(int[] denoms, int C, int n, int[][] cache) {
		 // If change is 0 or only one denom remaining then there is 1 way to achieve it
		if(C == 0 || n == 1) return 1;
		
		if (cache[n][C] != -1) {//retrieve value
			return cache[n][C];
		}
		
		if(C >= denoms[n-1]){
			cache[n][C] = makeChangeMemo(denoms, C - denoms[n-1], n, cache) +
					makeChangeMemo(denoms, C, n-1, cache);
		}else{
			cache[n][C] = makeChangeMemo(denoms, C, n-1, cache);
		}

		return cache[n][C];
	}
	
	static int makeChangeAV(int C) {
		int[] denoms = { 1, 5, 10, 25 };
		int n = denoms.length;
		int[][] cache = new int[n+1][C+1]; // precomputed vals (n+1 --> starting from 0)
		
		for(int i=0; i<n+1; i++)
			for(int j=0;j<C+1;j++)
				cache[i][j] = -1;
		
		return makeChangeMemo(denoms, C, n, cache);
	}
	
	static int makeChange(int n) {
		int[] denoms = { 25, 10, 5, 1 };
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
    	for (int i = 1; i <= 100; ++i)
            System.out.println(i + " IK ==> " + makeChange(i) + " AV ==> " + makeChangeAV(i));
    }
}