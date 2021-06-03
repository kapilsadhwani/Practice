package com.implement.pepcoding.dp;


public class TripleStep {
	private static int countWaysR(int s, int[] comb) {
    	if (s == 0){
            return 1;
        }
    	
    	if (comb[s] > 0)	return comb[s];
    	
        int total = 0;
        
        if(s >= 3) total += countWaysR(s-3,comb);
        if(s >= 2) total += countWaysR(s-2,comb);
        if(s >= 1) total += countWaysR(s-1,comb);
        
        comb[s] = total;
        
        return total;
    }
    
	private static int countWays(int steps) {
        int comb[] = new int[steps + 1];

        return countWaysR(steps, comb);
    }

	/*
	 * Find number of ways to climb the stairs from 0 to top.
	 * In one move, you are allowed to climb 1, 2 or 3 stairs.
	 */
	private static int countWaysDP(int steps) {
        int dp[] = new int[steps + 1];
        dp[0] = 1;
        
        for (int s = 1; s <= steps; ++s) {
            if (s == 1){
            	dp[s] = dp[s - 1];
            }else if (s == 2){
            	dp[s] = dp[s - 1] + dp[s - 2];
        	}else {
        		dp[s] = dp[s - 1] + dp[s - 2] + dp[s - 3];
        	}
        }

        return dp[steps];
    }
    
	private static int climbStairsW(int n) {
    	if(n == 0 || n == 1) return 1;
    	
        int dp[] = new int[3];
        dp[0] = 1;
            
        for (int s = 1; s <= n; ++s) {
        	if (s == 1){
            	dp[1] = dp[0];
            }else if (s == 2){
            	dp[2] = dp[0] + dp[1];
        	}else {
        		int total = dp[0] + dp[1] + dp[2];
        		
        		dp[0] = dp[1];
        		dp[1] = dp[2];
        		dp[2] = total;
        	}
        }

        return dp[2];
    }
    
	/*
	 * Find number of ways to climb the stairs from 0 to top.
	 * You are given n numbers, where ith element's value represents - till how far from the step you 
	 * could jump to in a single move. 
	 * You can of course jump fewer number of steps in the move.
	 */
    private static int climbStairsVariableJump(int[] arr) {
		int n = arr.length;

		int dp[] = new int[n + 1];
		dp[n] = 1;			// 1 path

		for (int i = n - 1; i >= 0; i--) {
			int total = 0;

			for (int j = 1; j <= arr[i] && (i + j < dp.length); j++) {
				total = total + dp[i + j];
			}

			dp[i] = total;
		}

		/*System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(dp));*/
		return dp[0];
    }

    /*
	 * Find minimum moves to climb the stairs from 0 to top.
	 * You are given n numbers, where ith element's value represents - till how far from the step you 
	 * could jump to in a single move.  You can of-course fewer number of steps in the move.
	 * You are required to print the number of minimum moves in which you can reach the top of staircase.
	 */
    private static Integer climbStairsMinMoves(int[] arr) {
		int n = arr.length;

		Integer dp[] = new Integer[n + 1];	// Null indicates no path
		dp[n] = 0;			// 0 moves to destination

		for (int i = n - 1; i >= 0; i--) {
			if(arr[i] != 0){
				int min = Integer.MAX_VALUE;
	
				for (int j = 1; j <= arr[i] && (i + j < dp.length); j++) {
					if (dp[i + j] != null){
						min = Math.min(min, dp[i + j]);
					}
				}
	
				dp[i] = min != Integer.MAX_VALUE ? min + 1 : null;
			}
		}

		return dp[0];
    }

    public static void main(String[] args) {
    	for (int i = 0; i < 20; ++i)
            System.out.println(i + " - Memo : " + countWays(i)
			            		 + ", DP : " + countWaysDP(i));
    	
    	System.out.println("=============================================");
    	
    	for (int i = 0; i < 20; ++i)
            System.out.println(i + " - ClimbStairs : " + countWaysDP(i)
			            		 + ", Climb Stairs W : " + climbStairsW(i));
    	
    	System.out.println("=============================================");
    	
		int[] nums = { 5, 0, 2, 4, 1, 5, 9, 8, 6, 3, 8, 9, 1 };
    	
    	System.out.println(climbStairsVariableJump(nums));
    	
		nums = new int[] { 1, 1, 1, 4, 9, 8, 1, 1, 10, 1 };
		
		System.out.println("=============================================");
    	
    	System.out.println(climbStairsMinMoves(nums));
    }
}
