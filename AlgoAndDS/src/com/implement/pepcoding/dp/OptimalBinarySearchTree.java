package com.implement.pepcoding.dp;

public class OptimalBinarySearchTree {
	
	private static void optimalBST(int[] keys, int[] frequency, int n){
		int[][] dp = new int[n][n];
		
		// Prefix sum array
		int[] psa = new int[n];
		psa[0] = frequency[0];

		for (int i = 1; i < frequency.length; i++) {
			psa[i] = psa[i - 1] + frequency[i];
		}
		
		for (int g = 0; g < dp.length; g++) {
			// Traverse diagonally with loop ending in last column
			for (int i = 0, j = g; j < dp.length; i++, j++) {
				// Trivial case: Single node tree, height 1. Search time = 1 * frequency[i]
				if (g == 0) {
					dp[i][j] = frequency[i];
				}
				// Trivial case: 2 nodes: Math.min( a + 2*b, b + 2*a)
				else if (g == 1) {
					int f1 = frequency[i];
					int f2 = frequency[j];
					dp[i][j] = Math.min(f1 + 2 * f2,  f2 + 2 * f1);
				} else {
					int min = Integer.MAX_VALUE;
					int fs = 0;
					
					// Adding a'+b'+c'+d'
					/*
					 * Approach 1:
					 	for(int x = i; x <=j ; x++){
							fs = fs + frequency[x];
						}
					*/
					
					// Approach 2: Using prefix sum
					//fs = psa[j] - (i == 0 ? 0 : psa[i - 1]);
								// OR
					fs = psa[j] - psa[i] + frequency[i];
					
					for(int k = i; k <=j ; k++){
						/*
						 *  k is root
						 *  Left side  : i...k-1
						 *  Right side : k+1...j
						 */
						
						int left = k == i ? 0 : dp[i][k - 1];
						int right = k == j ? 0 : dp[k + 1][j];
						
						if(left + right + fs < min){
							min = left + right + fs;
						}
					}
					
					dp[i][j] = min;
				}
			}
		}
		
		System.out.println(dp[0][n - 1]);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] keys = {1, 3, 4, 5, 6, 7, 8, 9, 11};
		int[] frequency = {3, 6, 4, 8, 7, 3, 7, 4, 7};
		
		optimalBST(keys, frequency, keys.length);

	}

}
