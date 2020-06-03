package com.implement.google;

import java.util.Arrays;

public class TreasureHunt {
	private static int solve(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int[][][] cache = new int[m][n][n];
		
		for(int[][] arr : cache){
			for(int[] arr1: arr)
				Arrays.fill(arr1, -1);
		}
		
		return Math.max(0,
				dfs(grid, 0, 0, grid[0].length - 1, cache));
	}

	private static int dfs(int[][] grid, int row, int j1, int j2,
			int[][][] memo) {
		int m = grid.length, n = grid[0].length;
		
		if (row >= m || j1 < 0 || j1 >= n || j2 < 0 || j2 >= n)
			return 0;
		
		/* If curr_row > last_row/2 and
		 * Robot 1 is at column > last_row-curr_row
		 */
		if (row > (m-1)/2 && j1 > (m-1) - row)
			return 0;
		
		/* If curr_row > last_row/2 and
		 * Robot 2 is at column > last_row-curr_row
		 */
		if (row > (m-1)/2 && (n-1)-j2 > (m-1) - row)
			return 0;
		
		// Check in cache
		if (memo[row][j1][j2] != -1)
			return memo[row][j1][j2];
		
		// No need to recurse further if at the final points for Robots
		if (row == m-1 && j1 == 0 && j2 == n-1)
			return grid[row][j1] + grid[row][j2];
		
		int res = 0;
		if (j1 == j2)
			res += grid[row][j1];
		else {
			res += grid[row][j1] + grid[row][j2];
		}
		
		int next = Math.max(dfs(grid, row+1, j1, j2, memo), 
				   Math.max(dfs(grid, row+1, j1, j2-1, memo),
						    dfs(grid, row+1, j1, j2+1, memo)));
		
		int next1 = Math.max(dfs(grid, row+1, j1-1, j2, memo), 
					Math.max(dfs(grid, row+1, j1-1, j2-1, memo),
							 dfs(grid, row+1, j1-1, j2+1, memo)));
		
		int next2 = Math.max(dfs(grid, row+1, j1+1, j2, memo), 
					Math.max(dfs(grid, row+1, j1+1, j2-1, memo),
							 dfs(grid, row+1, j1+1, j2+1, memo)));
		
		res = res + Math.max(next, Math.max(next1, next2));
		memo[row][j1][j2] = res;
		
		return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] grid1 = {{9, 10, 15, 12, 11},
				{7, 5, 11, 6, 8},
				{4, 1, 27, 13, 17},
				{2, 4, 18, 2, 1},
				{15, 3, 22, 6, 10},
				{8, 2, 5, 9, 6}};
		int[][] grid2 = {{3, 6, 8, 2},
				 {5, 2, 4, 3},
				 {1, 1, 20, 10},
				 {1, 1, 20, 10},
				 {1, 1, 20, 10}};
		System.out.println(solve(grid1));
		System.out.println(solve(grid2));
	}

}
