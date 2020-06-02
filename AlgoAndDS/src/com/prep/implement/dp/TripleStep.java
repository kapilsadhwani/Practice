package com.prep.implement.dp;

public class TripleStep {
    public static int countWaysR(int s, int[] comb) {
    	if (comb[s] > 0)	return comb[s];
    	
    	if (s == 0){
            return 1;
        }

        int total = 0;
        
        if(s >= 3) total += countWaysR(s-3,comb);
        if(s >= 2) total += countWaysR(s-2,comb);
        if(s >= 1) total += countWaysR(s-1,comb);
        
        comb[s] = total;
        
        return total;
    }
    
    public static int countWays(int steps) {
        int comb[] = new int[steps + 1];

        return countWaysR(steps, comb);
    }

    public static int countWaysDP(int steps) {
        int comb[] = new int[steps + 1];
        comb[0] = 1;
        for (int s = 1; s <= steps; ++s) {
            int total = 0;
            if (s >= 3)
            	total = total + comb[s - 3];
            if (s >= 2)
            	total = total + comb[s - 2];
            if (s >= 1)
            	total = total + comb[s - 1];
            comb[s] = total;
        }

        return comb[steps];
    }
    
    public static int climbStairs(int n) {
    	if(n == 0 || n == 1) return 1;
    	
        int comb[] = new int[n+1];
        comb[0] = 1;
        comb[1] = 1;

        for (int s = 2; s <= n; ++s) {
        	int total = 0;
            
            if (s >= 2)
            	total = total + comb[s - 2];
            if (s >= 1)
            	total = total + comb[s - 1];
            
            comb[s] = total;
        }

        return comb[n];
    }
    
    public static int climbStairsW(int n) {
    	if(n == 0 || n == 1) return 1;
    	
        int comb[] = new int[2];
        comb[0] = 1;
        comb[1] = 1;
            
        for (int s = 2; s <= n; ++s) {
        	int total = 0;
            
            if (s >= 2)
            	total = total + comb[0];
            if (s >= 1)
            	total = total + comb[1];
            
            comb[0] = comb[1];
            comb[1] = total;
        }

        return comb[1];
    }

    public static void main(String[] args) {
    	for (int i = 0; i < 20; ++i)
            System.out.println(i + " - Memo : " + countWays(i)
			            		 + ", DP : " + countWaysDP(i));
    	
    	System.out.println("=============================================");
    	
    	for (int i = 0; i < 20; ++i)
            System.out.println(i + " - ClimbStairs : " + climbStairs(i)
			            		 + ", Climb Stairs W : " + climbStairs(i));
    }
}
