package com.implement.dp;

public class EggDroppingPuzzle {
	static int solveMemo(int e, int f, int[][] cache){
		/*if(e == 0){	//Invalid condition, can be ommitted
			return 0;
		}*/
		
		if(f == 0 || f == 1 ) 
			return f;
		
		if(e == 1)
			return f;
		
		if(cache[e][f] != -1){
			return cache[e][f];
		}
		
		int min = f;
		
		/*
		 * Recursive Function
		 * Math.max[solve(e-1, k-1), solve(e, f-k)]
		 * Then Minimum of above values for each k
		 */
		
		/*for(int k=1; k<=f; k++){			
			int tempAns = 1 + Math.max(solveMemo(e-1, k-1, cache), 
					solveMemo(e, f-k, cache));			// Max: We are looking for worst case
			min = Math.min(min, tempAns);				// Min: Minimum number of attempts
		}*/
		
		for(int k=1; k<=f; k++){
			int low;
			if(cache[e-1][k-1] != -1){
				low = cache[e-1][k-1];
			}else{
				low = solveMemo(e-1, k-1, cache);
				cache[e-1][k-1] = low;
			}
			
			int high;
			if(cache[e][f-k] != -1){
				high = cache[e][f-k];
			}else{
				high = solveMemo(e, f-k, cache);
				cache[e][f-k] = high;
			}
			
			int tempAns = 1 + Math.max(low, high);			// Max: We are looking for worst case
			min = Math.min(min, tempAns);					// Min: Minimum number of attempts
		}
		
		cache[e][f] = min;
		
		return min;			
	}
	
	static int solveR(int e, int f){
		/*if(e == 0){	//Invalid condition, can be ommitted
			return 0;
		}*/
		
		int cache[][] = new int[e+1][f+1];
		
		for(int i=0; i<e+1; i++){
    		for(int j=0;j<f+1; j++){
    			cache[i][j] = -1;
    		}
    	}
		
		return solveMemo(e, f, cache);			
	}
	
	/*static int solve(int e, int f){	
		int cache[][] = new int[e+1][f+1];
		
		// When e=0
    	for(int c=0; c<f+1; c++){
    		cache[0][c] = 0;
    	}
    	
    	// When f=0 || f=1
    	for(int r=0; r<e+1; r++){
    		cache[r][0] = 0;
    		cache[r][1] = 1;
    	}
		
		for(int i=1;i<e+1;i++){
			for(int j=2;j<f+1;j++){
				int min = Integer.MAX_VALUE;
				
				for(int k=1; k<=j; k++){
					int tempAns = 1 + Math.max(cache[i-1][k-1], 
									cache[i][j-k]);		// Max: We are looking for worst case
					min = Math.min(min, tempAns);		// Min: Minimum number of attempts
				}
				cache[i][j] = min;
			}
		}
		
		return cache[e][f];			
	}*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int e = 2;
		int f = 10;
		
		System.out.println("Minimum attempts - Recursive: " + solveR(e,f));
				//+ ", Iterative: " + solve(e,f));
		
		e = 3;
		f = 5;
		
		System.out.println("Minimum attempts : " + solveR(e,f));
				//+ ", Iterative: " + solve(e,f));
	}

}
