package com.implement.dp;


/**
 http://www.geeksforgeeks.org/print-all-possible-paths-from-top-left-to-bottom-right-of-a-mxn-matrix/
 */
public class RoboInGrid {
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
    
	public static void bestPath (int[][] grid){
		int[][] memo = new int[grid.length][grid[0].length];
		
		for(int row=0;row<grid.length;row++)
			for(int col=0;col<grid[0].length;col++)
				memo[row][col] = grid[row][col];
		
		int result = bestPathTopDown(grid, 0, 0, memo);
		
		System.out.println("Best Path : " + result);
		
		for(int row=0;row<grid.length;row++){
			System.out.print("\n");
			for(int col=0;col<grid[0].length;col++)
				System.out.print("\t" + memo[row][col]);
		}
	}
	
	public static int minPathSum(int[][] grid){
		int R = grid.length;
		int C = grid[0].length;
		
		int dp[][] = new int[R][C];
		
		dp[R-1][C-1] = grid[R-1][C-1];
		
		int minRight;
		int minDown;
		
		for(int row=R-1; row>=0; row--){
			for(int col=C-1; col>=0; col--){
				if(row == R-1 && col == C-1) continue;
				
				if(row == R-1){
					dp[row][col] = grid[row][col] + dp[row][col+1];
				}
				
				else if(col == C-1){
					dp[row][col] = grid[row][col] + dp[row+1][col];
				}
				
				else{
					minRight = dp[row][col+1];
					minDown = dp[row+1][col];
					
					if(minDown < minRight){
						dp[row][col] = grid[row][col] + minDown;
					}else{
						dp[row][col] = grid[row][col] + minRight;
					}
				}
			}
		}
		
		return dp[0][0];
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
        
       int arr1[][] = {{1, 3, 1},
 			   {1, 5,  1},
 			   {4, 2, 1}};
		
        minPathSum(arr1);
    }
}