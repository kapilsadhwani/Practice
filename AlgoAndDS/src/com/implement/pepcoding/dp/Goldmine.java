package com.implement.pepcoding.dp;

import java.util.LinkedList;
import java.util.Queue;

public class Goldmine {
	static class Pair{
		String psf;		
		int i;
		int j;
		
		public Pair(String psf, int i, int j) {
			this.psf = psf;
			this.i = i;
			this.j = j;
		}
	}
	
	public static int maxGold(int[][] grid) {
		int R = grid.length;
		int C = grid[0].length;

		int dp[][] = new int[R][C];

		for (int col = C - 1; col >= 0; col--) {
			for (int row = R - 1; row >= 0; row--) {
				if (col == C - 1) {
					dp[row][col] = grid[row][col];
				} else if (row == 0) {
					dp[row][col] = grid[row][col]
							+ Math.max(dp[row][col + 1], dp[row + 1][col + 1]);
				} else if (row == R - 1) {
					dp[row][col] = grid[row][col]
							+ Math.max(dp[row][col + 1], dp[row - 1][col + 1]);
				} else {
					dp[row][col] = grid[row][col]
							+ Math.max(dp[row][col + 1], Math.max(
									dp[row - 1][col + 1], dp[row + 1][col + 1]));
				}
			}
		}

		int max = dp[0][0];
		for (int i = 1; i < dp.length; i++) {
			if (dp[i][0] > max) {
				max = dp[i][0];
			}
		}

		System.out.println(max);

		/*for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}*/
		return max;
	}
	
	public static void printMaxGoldPaths(int[][] grid) {
		int R = grid.length;
		int C = grid[0].length;

		int dp[][] = new int[R][C];

		for (int col = C - 1; col >= 0; col--) {
			for (int row = R - 1; row >= 0; row--) {
				if (col == C - 1) {
					dp[row][col] = grid[row][col];
				} else if (row == 0) {
					dp[row][col] = grid[row][col]
							+ Math.max(dp[row][col + 1], dp[row + 1][col + 1]);
				} else if (row == R - 1) {
					dp[row][col] = grid[row][col]
							+ Math.max(dp[row][col + 1], dp[row - 1][col + 1]);
				} else {
					dp[row][col] = grid[row][col]
							+ Math.max(dp[row][col + 1], Math.max(
									dp[row - 1][col + 1], dp[row + 1][col + 1]));
				}
			}
		}

		int max = dp[0][0];
		for (int i = 1; i < dp.length; i++) {
			if (dp[i][0] > max) {
				max = dp[i][0];
			}
		}

		System.out.println(max);
		
		Queue<Pair> queue = new LinkedList<>();

		for (int i = 0; i < dp.length; i++) {
			if (dp[i][0] == max) {
				queue.add(new Pair(i+"", i, 0));
			}
		}
		
		Pair rem = null;
		while(queue.size() > 0){
			rem = queue.poll();
			
			if (rem.j == C - 1) {
				System.out.println(rem.psf);
			} else if (rem.i == 0) {
				int mx = Math.max(dp[rem.i][rem.j + 1], dp[rem.i + 1][rem.j + 1]);
				
				if(mx == dp[rem.i][rem.j + 1]){
					queue.add(new Pair(rem.psf+" d2", rem.i, rem.j + 1));
				}
				
				if(mx == dp[rem.i + 1][rem.j + 1]){
					queue.add(new Pair(rem.psf+" d3", rem.i + 1, rem.j + 1));
				}
			} else if (rem.i == R - 1) {
				int mx = Math.max(dp[rem.i - 1][rem.j + 1], dp[rem.i][rem.j + 1]);
				
				if(mx == dp[rem.i - 1][rem.j + 1]){
					queue.add(new Pair(rem.psf+" d1", rem.i - 1, rem.j + 1));
				}
				
				if(mx == dp[rem.i][rem.j + 1]){
					queue.add(new Pair(rem.psf+" d2", rem.i, rem.j + 1));
				}
			} else {
				int mx = Math.max(dp[rem.i - 1][rem.j + 1], 
						 Math.max(dp[rem.i][rem.j + 1], dp[rem.i + 1][rem.j + 1]));
				
				if(mx == dp[rem.i - 1][rem.j + 1]){
					queue.add(new Pair(rem.psf+" d1", rem.i - 1, rem.j + 1));
				}
				
				if(mx == dp[rem.i][rem.j + 1]){
					queue.add(new Pair(rem.psf+" d2", rem.i, rem.j + 1));
				}
				
				if(mx == dp[rem.i + 1][rem.j + 1]){
					queue.add(new Pair(rem.psf+" d3", rem.i + 1, rem.j + 1));
				}
			}
		}
	}
 
    public static void main(String args[]){
        int arr[][] = {{  1,   2,  3,  4},
        			   {  5,   6,  7,  8},
        			   {  9,  10, 11, -3},
        			   { 13, -14, 15, 16}};
        
        maxGold(arr);
        
        printMaxGoldPaths(arr);
        
        System.out.println("\n <========================>");
        
       int arr1[][] = {{1, 3, 11},
		 			   {1, 5, 1},
		 			   {4, 2, 1}};
		
       maxGold(arr1);
       
       printMaxGoldPaths(arr1);
       
       System.out.println("\n <========================>");
       
       int arr2[][] = {{0, 1, 4, 2, 8, 2},
		 			   {4, 3, 6, 5, 0, 4},
		 			   {1, 2, 4, 1, 4, 6},
		 			   {2, 0, 7, 3, 2, 2},
		 			   {3, 1, 5, 9, 2, 4},
		 			   {2, 7, 0, 8, 5, 1}};
		
       maxGold(arr2);
       
       printMaxGoldPaths(arr2);
    }
}