package com.implement.pepcoding.dp;

/*
 * Given an array(arr)of length N which represents N number of balloons.
 * Each balloon is painted with a number on it.
 * You have to collect maximum coins by bursting all the balloons.
 * If you burst a balloon with index i, you will get (arr[i-1] * arr[i] * arr[i+1]) number of coins.
 * If arr[i-1] and arr[i+1] don't exist, then you may assume their value as 1.
 */

public class BurstBalloon {
	// Using Gap strategy
	public static int burstBalloon(int[] arr) {
		int n = arr.length;
		int dp[][] = new int[n][n];
		
		// Gap = 0 to n - 1 OR length = 1 to n
		for (int g = 0; g < dp.length; g++) {

			// Traverse diagonally with loop ending in last column
			for (int i = 0, j = g; j < dp[0].length; i++, j++) {
				int max = Integer.MIN_VALUE;
					
				for (int k = i; k <= j; k++) {
					/*
					 * k = Last balloon to be burst in i....j
					 * dp ->  Left half = i, k - 1; Right half = k + 1, j;
					 * arr -> arr[i - 1] * arr[k] * arr[j + 1]
					 */
					int left = (k == i) ? 0 : dp[i][k - 1];
					int right = (k == j) ? 0 : dp[k + 1][j];
					int val = arr[k];
					
					if (i > 0){
						val = val * arr[i - 1];
					}
					
					if (j < arr.length - 1){	// or j != arr.length - 1
						val = val * arr[j + 1];
					}
					
					int total = left + right + val;

					if (total > max) {
						max = total;
					}
				}

				dp[i][j] = max;
			}
		}

		return dp[0][dp.length - 1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = new int[] {1, 2, 3}; 
  
        System.out.println("Maximum Number of coins : " + 
        		burstBalloon(arr)); 
	}

}
