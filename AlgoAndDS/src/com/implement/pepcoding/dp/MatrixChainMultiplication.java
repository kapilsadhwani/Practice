package com.implement.pepcoding.dp;

import java.util.Arrays;

public class MatrixChainMultiplication {
	// Matrix Ai has dimension p[i-1] x p[i] for i = 1..n 
	// DP - Memoized
    static int topDownMatrixChainOrderAux(int m[][], int p[], int i, int j){ 
    	if(m[i][j] < Integer.MAX_VALUE) 
    		return m[i][j];
    	
        if (i == j) 
        	m[i][j] = 0; 
        else{
        	int min;  
            // place parenthesis at different places between first 
            // and last matrix, recursively calculate count of 
            // multiplications for each parenthesis placement and 
            // return the minimum count 
            for (int k=i; k<j; k++) { 
            	min = topDownMatrixChainOrderAux(m, p, i, k) + 
                		topDownMatrixChainOrderAux(m, p, k+1, j) + 
                            p[i-1]*p[k]*p[j]; 
      
                if (min < m[i][j]) 
                	m[i][j] = min; 
            } 
        }
        
       // Return minimum count 
        return m[i][j]; 
    }
    
	// Matrix Ai has dimension p[i-1] x p[i] for i = 1..n 
    // DP - Tabulation
    static int topDownMatrixChainOrder(int p[]){ 
    	int n = p.length;
    	int m[][] = new int[n][n];
    	
    	for(int i=1;i<n;i++)
    		for(int j=i;j<n;j++)
    			m[i][j] = Integer.MAX_VALUE;
    	
    	return topDownMatrixChainOrderAux(m, p, 1, n-1);
    }
    
    static int bottomUpMatrixChainOrder(int p[], int n) { 
        /* For simplicity of the program, one extra row and one 
        extra column are allocated in m[][].  0th row and 0th 
        column of m[][] are not used */
        int m[][] = new int[n][n]; 
  
        int i, j, k, L, min; 
  
        /* m[i, j] = Minimum number of scalar multiplications needed 
        to compute the matrix A[i]A[i+1]...A[j] = A[i..j] where 
        dimension of A[i] is p[i-1] x p[i] */
  
        // cost is zero when multiplying one matrix. 
        for (i = 1; i < n; i++) 
            m[i][i] = 0; 
  
        // L is chain length. 
        for (L = 2; L < n; L++) { 
            for (i = 1; i < n - L + 1; i++) { 
                j = i + L - 1; 
                m[i][j] = Integer.MAX_VALUE; 
                for (k = i; k < j; k++) { 
                    // q = cost/scalar multiplications 
                	min = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j]; 
                    if (min < m[i][j]) 
                        m[i][j] = min; 
                } 
            } 
        } 
  
        return m[1][n - 1]; 
    }
    
    // Using AS approach
	static int solve(int[] arr, int i, int j, int cache[][]) {
		if (i >= j)
			return 0;

		int ans = Integer.MAX_VALUE;
		
		if(cache[i][j] != -1)
			return cache[i][j];

		for (int k = i; k < j; k++) {
			int tempAns = solve(arr, i, k, cache) + solve(arr, k + 1, j, cache) + 
							arr[i - 1] * arr[k] * arr[j];

			if (tempAns < ans) {
				ans = tempAns;
			}
		}

		return cache[i][j] = ans;
	}
	
	// Using AS approach
	static int matrixChainOrderAS(int[] arr, int size) {
		int cache[][] = new int[size][size];
        
        for(int i=0; i<size; i++)
        	Arrays.fill(cache[i],-1);
        
        return solve(arr, 1, size-1, cache);
	}
	
	// Using Gap strategy
	public static int matrixChainOrderDPII(int[] arr, int n) {
		
		// We are given n dimensions which can form (n - 1) matrices
		int dp[][] = new int[n - 1][n - 1];
		
		// Gap = 0 to n - 1 OR length = 1 to n
		for (int g = 0; g < dp.length; g++) {

			// Traverse diagonally with loop ending in last column
			for (int i = 0, j = g; j < dp[0].length; i++, j++) {
				// Trivial case: Single matrix - No multiplication, hence cost is 0
				if (g == 0) {
					dp[i][j] = 0;
				}
				// Trivial case: 2 matrices [ M1: i * (i+1) and M2: j * (j+1) ]
				else if (g == 1) {
					dp[i][j] = arr[i] * arr[j] * arr[j + 1];
				} else {
					int min = Integer.MAX_VALUE;
					
					for(int k = i; k < j; k++){
						/*
						 * dp  -> Left half = i, k; Right half = k + 1, j
						 * arr -> Left half = i * (k + 1); Right half = (k + 1) * (j + 1)
						 */
						int lc = dp[i][k];
						int rc = dp[k + 1][j];
						int mc = arr[i] * arr[k + 1] * arr[j + 1];
						int tc = lc + rc + mc;
						
						if(tc < min){
							min = tc;
						}
					}
					
					dp[i][j] = min;
				}
			}
		}

		return dp[0][dp.length - 1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = new int[] {30, 35, 15, 5, 10, 20, 25}; 
        int size = arr.length; 
  
        System.out.println("Minimum number of multiplications is "+ 
        		topDownMatrixChainOrder(arr)); 
        
        System.out.println("Minimum number of multiplications is " 
                + bottomUpMatrixChainOrder(arr, size));
        
        System.out.println("Minimum number of multiplications [AS approach] is " 
                + matrixChainOrderAS(arr, size));
        
        System.out.println("Minimum number of multiplications [Gap approach] is " 
                + matrixChainOrderDPII(arr, size));
	}

}
