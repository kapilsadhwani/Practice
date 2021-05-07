package com.implement.pepcoding.dp;

import java.util.LinkedList;
import java.util.Queue;


/**
 http://www.geeksforgeeks.org/print-all-possible-paths-from-top-left-to-bottom-right-of-a-mxn-matrix/
 */
public class RoboInGrid {
	static class Pair{
		String psf;
		int i;
		int j;
		
		public Pair(String psf, int i, int j) {
			super();
			this.psf = psf;
			this.i = i;
			this.j = j;
		}
	}
	
	static int bestPathTopDown(int[][] grid, int row, int col, int[][] memo){
		int n = grid.length;
		int k = grid[0].length;
		
		if(memo[row][col] != grid[row][col]) return memo[row][col];
		
		if(row == n-1 && col == k-1){
			return grid[row][col];
		}
		
		int best = grid[row][col];
		
		int down = 0, right = 0;
		
		if(row < n-1) {
			down = bestPathTopDown(grid, row + 1, col, memo);
		}
		
		if(col < k-1){
			right = bestPathTopDown(grid, row, col + 1, memo);
		}
		
		memo[row][col] = best + Math.max(down, right);
		
		return memo[row][col];
	}
    
	/*
	 * Max Profit In Maze Traversal
	 */
	public static void bestPath(int[][] grid) {
		int[][] memo = new int[grid.length][grid[0].length];

		for (int row = 0; row < grid.length; row++)
			for (int col = 0; col < grid[0].length; col++)
				memo[row][col] = grid[row][col];

		int result = bestPathTopDown(grid, 0, 0, memo);

		System.out.println("Best Path : " + result);

		for (int row = 0; row < grid.length; row++) {
			System.out.print("\n");
			for (int col = 0; col < grid[0].length; col++)
				System.out.print("\t" + memo[row][col]);
		}
	}
	
	/*
	 * Min Cost In Maze Traversal
	 */
	public static int minPathSum(int[][] grid) {
		int R = grid.length;
		int C = grid[0].length;

		int dp[][] = new int[R][C];

		for (int row = R - 1; row >= 0; row--) {
			for (int col = C - 1; col >= 0; col--) {
				if (row == R - 1 && col == C - 1) {
					dp[row][col] = grid[row][col];
				} else if (row == R - 1) {
					dp[row][col] = grid[row][col] + dp[row][col + 1];
				} else if (col == C - 1) {
					dp[row][col] = grid[row][col] + dp[row + 1][col];
				} else {
					int minRight = dp[row][col + 1];
					int minDown = dp[row + 1][col];

					if (minDown < minRight) {
						dp[row][col] = grid[row][col] + minDown;
					} else {
						dp[row][col] = grid[row][col] + minRight;
					}
				}
			}
		}

		return dp[0][0];
	}
	
	public static void minCostPaths(int[][] grid) {
		int R = grid.length;
		int C = grid[0].length;

		int dp[][] = new int[R][C];

		int minRight;
		int minDown;

		for (int row = R - 1; row >= 0; row--) {
			for (int col = C - 1; col >= 0; col--) {
				if (row == R - 1 && col == C - 1) {
					dp[row][col] = grid[row][col];
				} else if (row == R - 1) {
					dp[row][col] = grid[row][col] + dp[row][col + 1];
				} else if (col == C - 1) {
					dp[row][col] = grid[row][col] + dp[row + 1][col];
				} else {
					minRight = dp[row][col + 1];
					minDown = dp[row + 1][col];

					if (minDown < minRight) {
						dp[row][col] = grid[row][col] + minDown;
					} else {
						dp[row][col] = grid[row][col] + minRight;
					}
				}
			}
		}
		
		System.out.println(dp[0][0]);
		
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair("", 0, 0));
		
		Pair rem = null;
		while(queue.size() > 0){
			rem = queue.poll();
			
			if(rem.i == dp.length - 1 && rem.j == dp[0].length - 1){
				System.out.println(rem.psf);
			}else if(rem.i == dp.length - 1){
				queue.add(new Pair(rem.psf + "H", rem.i, rem.j + 1));
			}else if(rem.j == dp[0].length - 1){
				queue.add(new Pair(rem.psf + "V", rem.i + 1, rem.j));
			}else{
				
				if(dp[rem.i][rem.j + 1] < dp[rem.i + 1][rem.j]){
					queue.add(new Pair(rem.psf + "H", rem.i, rem.j + 1));
				}else if(dp[rem.i + 1][rem.j] < dp[rem.i][rem.j + 1]){
					queue.add(new Pair(rem.psf + "V", rem.i + 1, rem.j));
				}else{
					queue.add(new Pair(rem.psf + "V", rem.i + 1, rem.j));
					queue.add(new Pair(rem.psf + "H", rem.i, rem.j + 1));
				}
			}
		}
	}
 
    public static void main(String args[]){
        RoboInGrid pam = new RoboInGrid();
        int arr[][] = {{  1,   2,  3,  4},
        			   {  5,   6,  7,  8},
        			   {  9,  10, 11, -3},
        			   { 13, -14, 15, 16}};
        
        bestPath(arr);
        
        int result[] = new int[arr.length + arr[0].length-1];
        
        /*System.out.println("<====================================================>");
        pam.print(arr, 0, 0, result,0);
        System.out.println("<====================================================>");*/
        
        System.out.println("\n");
        
       int arr1[][] = {	{1, 3, 1},
 			   			{1, 5,  1},
 			   			{4, 2, 1}};
		
        minPathSum(arr1);
        
		int arr2[][] = {{ 0, 1, 4, 2, 8, 2 }, 
						{ 4, 3, 6, 5, 0, 4 },
						{ 1, 2, 4, 1, 4, 6 }, 
						{ 2, 0, 7, 3, 2, 2 },
						{ 3, 1, 5, 9, 2, 4 }, 
						{ 2, 7, 0, 8, 5, 1 } };
		
		minCostPaths(arr2);
	}
}