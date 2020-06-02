package com.prep.implement.dp;
/* JAVA Code for Maximum size square sub-matrix with all 1s 
 */
public class MaximalSquareBinaryMatrix 
{ 
	/*
	 * Time complexity : O(mn). Single pass
	 * Space complexity : O(mn). Another matrix of same size is used for dp
	 */
	public static int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        
        int[][] cache = new int[rows][cols];
        int result = 0;
        
        // First row and first column are same as that of matrix
        for (int c = 0; c < cols; c++) {
        	cache[0][c] = matrix[0][c]-'0';
        	result = Math.max(result, cache[0][c]);
        }
        for (int r = 0; r < rows; r++) {
        	cache[r][0] = matrix[r][0]-'0';
        	result = Math.max(result, cache[r][0]);
        }
        
        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                if (matrix[r][c] == '1'){
                	cache[r][c] = Math.min(Math.min(cache[r-1][c-1], 
                			cache[r-1][c]), cache[r][c-1]) + 1;
                	
                	result = Math.max(result, cache[r][c]);
                }
            }
        }
        return result * result;
    }
	
	/*
	 * Time complexity : O(mn). Single pass
	 * Space complexity : O(n). Another array which stores elements in a row is used for dp
	 */
	public static int maximalSquareOptimal(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[] dp = new int[cols + 1];
        int maxsqlen = 0, prev = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxsqlen * maxsqlen;
    }
	
	// Driver program 
	public static void main(String[] args) 
	{ 
		char matrix[][] = {	{'0', '1', '1', '0', '1'}, 
							{'1', '1', '0', '1', '0'}, 
							{'0', '1', '1', '1', '0'}, 
							{'1', '1', '1', '1', '0'}, 
							{'1', '1', '1', '1', '1'}, 
							{'0', '0', '0', '0', '0'}}; 
			
		System.out.println("Area of Maximal Square matrix: " + maximalSquare(matrix)); 
		
		matrix = new char[][]{{'0', '0', '0', '0', '0'}, 
								{'0', '0', '0', '0', '0'}, 
								{'0', '0', '0', '0', '1'}, 
								{'0', '0', '0', '0', '0'}}; 

		System.out.println("Area of Maximal Square matrix: " + maximalSquare(matrix)); 
	} 

} 
