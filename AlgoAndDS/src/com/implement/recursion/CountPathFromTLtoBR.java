package com.implement.recursion;

import java.util.Arrays;

/**
 http://www.geeksforgeeks.org/print-all-possible-paths-from-top-left-to-bottom-right-of-a-mxn-matrix/
 */
public class CountPathFromTLtoBR {
	static int countAllPaths (int[][] grid, int row, int col){
		int n = grid.length;
		int k = grid[0].length;
		
		if(row == n-1 || col == k-1) 
			return 1;
		
		int right=0, down=0;
		
		if(row < n-1){ 
			down = countAllPaths (grid, row+1, col);
		}
		if(col < k-1){ 
			right = countAllPaths (grid, row, col+1);
		}
		
		return right + down;
	}
	
	static int countValidPathsR(int[][] grid, int row, int col){
		int n = grid.length;
		int k = grid[0].length;
		
		if(row == n-1 && col == k-1) 
			return 1;
		
		int right=0, down=0;
		
		if(row < n-1 && grid[row+1][col] != -1){ 
			down = countValidPathsR (grid, row+1, col);
		}
		if(col < k-1 && grid[row][col+1] != -1){ 
			right = countValidPathsR (grid, row, col+1);
		}
		
		return right + down;
	}
	
	static int countValidPathsDP(boolean[][] grid){
		int R = grid.length;
		int C = grid[0].length;
		
		if(grid[R-1][C-1] == false){
			return 0;
		}
		
		int[][] dp = new int[R][C];
		
		dp[R-1][C-1] = 1;
		
		for(int row=R-1; row>=0; row--){
			for(int col=C-1; col>=0; col--){
				if(row == R-1 && col == C-1) continue;
				
				if(grid[row][col] == false){
					dp[row][col] = 0;
				}else{
					int right=0, down=0;
					
					if(row < R-1){
						down = dp[row+1][col];
					}
					
					if(col < C-1){
						right = dp[row][col+1];
					}
					
					dp[row][col] = down + right;
				}
			}
		}
		
		return dp[0][0];
	}
	
	public int countAllPathsDP(int m, int n) {
		int[][] dp = new int[m][n];

		dp[m - 1][n - 1] = 1;

		for (int row = m - 1; row >= 0; row--) {
			for (int col = n - 1; col >= 0; col--) {
				if (row == m - 1 && col == n - 1)
					continue;

				int right = 0, down = 0;

				if (row < m - 1) {
					down = dp[row + 1][col];
				}

				if (col < n - 1) {
					right = dp[row][col + 1];
				}

				dp[row][col] = down + right;
			}
		}

		return dp[0][0];
	}

	public void print(int[][] grid,int row, int col,int[] result,int pos){
    	int n = grid.length;
		int k = grid[0].length;
		
        if(row == n-1 && col == k-1){
            result[pos] = grid[row][col];
            System.out.println(Arrays.toString(result));
            return;
        }
        
        result[pos] = grid[row][col];
        
        if(row < n-1){ 
        	print(grid,row+1,col,result,pos+1);
        }
        
        if(col < k-1){ 
        	print(grid,row,col+1,result,pos+1);
        }
    }
	
	public void printValidPaths(int[][] grid,int row, int col,int[] result,int pos){
    	int n = grid.length;
		int k = grid[0].length;
		
        if(row == n-1 && col == k-1){
            result[pos] = grid[row][col];
            System.out.println(Arrays.toString(result));
            return;
        }
        
        result[pos] = grid[row][col];
        
        if(row < n-1 && grid[row+1][col] != -1){
        	printValidPaths(grid,row+1,col,result,pos+1);
        }
        
        if(col < k-1 && grid[row][col+1] != -1){
        	printValidPaths(grid,row,col+1,result,pos+1);
        }
    }
    
    public static int countPaths (int[][] grid, boolean validPaths){
		if (grid == null) return 0; 
		if(validPaths) return countValidPathsR(grid, 0, 0);

		return countAllPaths(grid, 0, 0);
	}
    
    public static void main(String args[]){
        CountPathFromTLtoBR pam = new CountPathFromTLtoBR();
        int arr[][] = {	{1,2,3,4},
        				{5,6,7,8},
        				{9,10,11,12},
        				{13,14,-1,16}};
        
        boolean grid[][] = { {true,true,true,true},
							 {true,true,true,true},
							 {true,true,true,true},
							 {true,true,false,true}};
        
        int result[] = new int[arr.length + arr[0].length-1];
        
        System.out.println("Available paths : " + countPaths(arr, false));
        pam.print(arr, 0, 0, result,0);
        
        System.out.println("<====================================================>");
        
        System.out.println("Allowed paths   : " + countPaths(arr, true));
        pam.printValidPaths(arr, 0, 0, result,0);
        
        System.out.println("<====================================================>");
        System.out.println("Valid paths   : " + countValidPathsDP(grid));
        
    }
}