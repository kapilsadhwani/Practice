package com.prep.implement.dp;

// Java program to count number of strings of n characters with 

class CountStrings {
	// n is total number of characters. bCount and cCount are counts of 'b' and 'c' respectively.

	static private int countWaysMemo(int[][][] cache, int n, int bCount, int cCount) {
		// Base cases
		if (n == 0) {
			return 1;
		}
		if (bCount == 0 && cCount == 0) {
			return 1;
		}

		// if we had saw this combination previously
		if (cache[n][bCount][cCount] != -1) {
			return cache[n][bCount][cCount];
		}

		// Three cases, we choose, a or b or c
		// In all three cases n decreases by 1.
		int res = countWaysMemo(cache, n - 1, bCount, cCount);
		
		if(bCount > 0){
			res += countWaysMemo(cache, n - 1, bCount - 1, cCount);
		}
		
		if(cCount > 0){
			res += countWaysMemo(cache, n - 1, bCount, cCount - 1);
		}

		return (cache[n][bCount][cCount] = res);
	}

	// A wrapper over countStrUtil()
	static int countWaysR(int n, int bCount, int cCount) {
		int[][][] cache = new int[n+1][bCount+1][cCount+1];
		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < bCount+1; j++) {
				for (int k = 0; k < cCount+1; k++) {
					cache[i][j][k] = -1;
				}
			}
		}
		return countWaysMemo(cache, n, bCount, cCount);
	}

	// Driver code
	public static void main(String[] args) {
		int n = 3; // Total number of characters
		int bCount = 1, cCount = 2;
		System.out.println(countWaysR(n, bCount, cCount));
	}
}