package com.implement.pepcoding.dp;

public class EggDroppingPuzzle {
	static int solveMemo(int e, int f, int[][] cache){
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
		
		/*
		 * Looking for best of the worst or
		 * Min of the maxes
		 */
		/*for(int k=1; k<=f; k++){			
			int tempAns = 1 + 
						Math.max(solveMemo(e-1, k-1, cache), 
								solveMemo(e, f-k, cache));	// Max: We are looking for worst case
			
			min = Math.min(min, tempAns);	// Min: Minimum number of attempts
		}*/
		
		for (int k = 1; k <= f; k++) {
			// Egg breaks
			int eggBreaks;
			if (cache[e - 1][k - 1] != -1) {
				eggBreaks = cache[e - 1][k - 1];
			} else {
				eggBreaks = solveMemo(e - 1, k - 1, cache);
				cache[e - 1][k - 1] = eggBreaks;
			}

			// Egg survives
			int eggSurvives;
			if (cache[e][f - k] != -1) {
				eggSurvives = cache[e][f - k];
			} else {
				eggSurvives = solveMemo(e, f - k, cache);
				cache[e][f - k] = eggSurvives;
			}

			int tempAns = 1 + Math.max(eggBreaks, eggSurvives); // Max: We are looking for worst case
			
			min = Math.min(min, tempAns); 			// Min: Minimum number of attempts
		}
		
		cache[e][f] = min;
		
		return min;			
	}
	
	static int solveR(int e, int f) {
		int cache[][] = new int[e + 1][f + 1];

		for (int i = 0; i < e + 1; i++) {
			for (int j = 0; j < f + 1; j++) {
				cache[i][j] = -1;
			}
		}

		return solveMemo(e, f, cache);
	}
	
	static int solveIter(int e, int f){	
		int cache[][] = new int[e+1][f+1];
		
		for (int i = 1; i < e + 1; i++) {
			for (int j = 1; j < f + 1; j++) {
				if (i == 1) {
					// Trivial case: One egg, will require f attempts
					cache[i][j] = j;
				} else if (j == 1) {
					// Trivial case: One floor, will require 1 attempt
					cache[i][j] = 1;
				} else {
					int min = Integer.MAX_VALUE;
					
					for(int k=1; k<=j; k++){
						// Egg survives
						int v1 = cache[i][j - k];
						
						// Egg breaks
						int v2 = cache[i - 1][k - 1];
	
						// Max: We are looking for worst case
						int val = Math.max(v1, v2);
	
						// Min: Minimum number of attempts
						min = Math.min(min, val);
					}
				
					// +1 for this attempt
					cache[i][j] = min + 1;	
				}
			}
		}
		
		return cache[e][f];			
	}
	
	static int solveDP(int e, int f) {
		int dp[][] = new int[e + 1][f + 1];

		for (int i = 1; i < e + 1; i++) {
			for (int j = 1; j < f + 1; j++) {
				if (i == 1) {
					// Trivial case: One egg, will require f attempts
					dp[i][j] = j;
				} else if (j == 1) {
					// Trivial case: One floor, will require 1 attempt
					dp[i][j] = 1;
				} else {
					int min = Integer.MAX_VALUE;
					/*
					 * cj = current row j pj = previous row j
					 */
					for (int cj = j - 1, pj = 0; cj >= 0; cj--, pj++) {
						int v1 = dp[i][cj]; 	// Egg survives
						int v2 = dp[i - 1][pj]; // Egg breaks

						int val = Math.max(v1, v2);

						min = Math.min(min, val);
					}

					// 1 attempt we perform at each floor j, to see if egg
					// breaks or survives
					dp[i][j] = min + 1;
				}
			}
		}

		return dp[e][f];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int e = 2;
		int f = 10;
		
		System.out.println("Minimum attempts - Recursive: " + solveR(e,f)
				+ ", Iterative AV: " + solveIter(e,f)
				+ ", Iterative SM: " + solveDP(e,f));
		
		e = 3;
		f = 5;
		
		System.out.println("Minimum attempts - Recursive: " + solveR(e,f)
				+ ", Iterative AV: " + solveIter(e,f)
				+ ", Iterative SM: " + solveDP(e,f));
	}

}
