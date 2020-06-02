package com.prep.implement.dp;


public class Football {
    public static int recursion(int score) {
        if (score == 0)
            return 1;

        int total = 0;
        
        if(score >= 7) total += recursion(score - 7);
        if(score >= 3) total += recursion(score - 3);
        if(score >= 2) total += recursion(score - 2);
        
        return total;
    }
    
    // Top-down approach - DP: Memoization
    public static int countCombinationsR(int s, int[] comb) {
    	if (comb[s] > 0) return comb[s];
    	
    	if (s == 0)
            return 1;

        int total = 0;
        
        if(s >= 7) total += countCombinationsR(s - 7, comb);
        if(s >= 3) total += countCombinationsR(s - 3, comb);
        if(s >= 2) total += countCombinationsR(s - 2, comb);
        
        comb[s] = total;
        return total;
    }
    
    public static int countCombinations(int score) {
    	int comb[] = new int[score + 1];
    	
        return countCombinationsR(score,comb);
    }

    // Bottom-up approach - DP: Iterative
    public static int countCombinationsDP(int score) {
        /*
         * Return Type of DP array is same as return type of the recursive function
         * Dimensions of DP array is the number of non constant parameters to the recursive function
         */
    	
    	/*
    	 * Fn(s) = Fn(s-7) [if(s>=7)] + Fn(s-3) [if(s>=3)] + Fn(s-2) [if(s>=2)]
		 * Fn(0) = 1
		 * 
         * F(11) = F(4) + F(8) + F(9)
         */
    	int comb[] = new int[score + 1];
        
        
        comb[0] = 1;	// Halting Condition
        
        /*
         * Loop goes in the direction opposite to that of the recursion 
         */
        for (int s = 1; s <= score; ++s) {		// s is the score of the recursion
            int total = 0;
            if (s >= 7)
            	total = total + comb[s - 7];
            if (s >= 3)
            	total = total + comb[s - 3];
            if (s >= 2)
            	total = total + comb[s - 2];
            comb[s] = total;
        }

        return comb[score];
    }
    
    // Bottom-up approach - DP: Iterative - Order not important
    public static int countUniqueCombinationsDP(int score) {
        /*
         * Return Type of DP array is same as return type of the recursive function
         * Dimensions of DP array is the number of non constant parameters to the recursive function
         */
    	
    	/*
    	 * Fn(s) = Fn(s-7) [if(s>=7)] + Fn(s-3) [if(s>=3)] + Fn(s-2) [if(s>=2)]
		 * Fn(0) = 1
		 * 
         * F(11) = F(4) + F(8) + F(9)
         */
    	int[][] comb = new int[score + 1][3];
        // int[] points = {2,3,7};
        
        // Halting Conditions
        comb[0][0] = 1;
        comb[0][1] = 1;
        comb[0][2] = 1;
        
        /*
         * Loop goes in the direction opposite to that of the recursion 
         */
        for (int s = 1; s <= score; ++s) {		// s is the score of the recursion
        	for (int p = 0; p < 3; ++p) {
	        	int total = 0;
	            if (s >= 2 && p == 0)
	            	total = total + comb[s - 2][0];
	            if (s >= 3 && p <= 1)
	            	total = total + comb[s - 3][1];
	            if (s >= 7)			// No need of condition p<=2; as it will always be true
	            	total = total + comb[s - 7][2];
	            comb[s][p] = total;
        	}
        }

        return comb[score][0];
    }
    
    // Recursive
    static int countUniqueCombinationsR(int score) {
		int[] points = { 2, 3, 7 };
		int[][] map = new int[score+1][points.length]; // precomputed vals
		
		map[0][0] = 1;
		map[0][1] = 1;
		map[0][2] = 1;
		
		return countUniqueCombinationsR(score, points, 0, map);
	}

	static int countUniqueCombinationsR(int s, int[] points, int pos, int[][] map) {
		if (map[s][pos] > 0) {//retrieve value
			return map[s][pos];
		}
		
		if(s == 0) return 1;
		
		if(pos == points.length-1) 					// Check whether using 'last' point [i.e. 7 in set
			return (s%points[pos] == 0) ? 1 : 0;	// of {2,3,7}], can we achieve the remaining score	
		
		int point = points[pos];
		
		int ways = 0;
		for (int i= 0; i * point <= s; i++) {
			//go to next point, assuming i times of previous pointValue
			int scoreRemaining = s - i * point;
			
			ways = ways + countUniqueCombinationsR(scoreRemaining, points, pos+1, map);
		}
		map[s][pos] = ways;
		return ways;
	}

    public static void main(String[] args) {
        /*for (int i = 0; i < 20; ++i)
            System.out.println(i + " - Memo : " + countCombinations(i)
			            		 + ", DP : " + countCombinationsDP(i)
			            		 + ", Recursion : " + recursion(i)
			            		 + ", Order Not Imp : " + countCombinationsDPOrderNotImportant(i));*/
    	
    	for (int i = 0; i < 20; ++i)
            System.out.println(i + " - Unique Ways DP : " + countUniqueCombinationsDP(i)
			            		 + ", Unique Ways Recursive : " + countUniqueCombinationsR(i));
    }
}
