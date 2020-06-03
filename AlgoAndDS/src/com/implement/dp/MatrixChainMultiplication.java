package com.implement.dp;

public class MatrixChainMultiplication {
	// Matrix Ai has dimension p[i-1] x p[i] for i = 1..n 
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = new int[] {30, 35, 15, 5, 10, 20, 25}; 
        int size = arr.length; 
  
        System.out.println("Minimum number of multiplications is "+ 
        		topDownMatrixChainOrder(arr)); 
        
        System.out.println("Minimum number of multiplications is " 
                + bottomUpMatrixChainOrder(arr, size));
	}

}
