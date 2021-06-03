package com.implement.pepcoding.dp;

public class PartitionIntoSubsets {

	/*
	 * Partition Into Subsets
	 * Given n elements and k teams
	 * Calculate number of ways in which these elements can be partitioned in k non-empty subsets.
	 * 
	 * E.g.
	 * For n = 4 and k = 3 total ways is 6
	 * 12-3-4
	 * 1-23-4
	 * 13-2-4
	 * 14-2-3
	 * 1-24-3
	 * 1-2-34
	 */
	private static long countWays(int n, int k) {
		/*
		 * If there are no elements or
		 * If there is no teams or
		 * If elements are less than teams 
		 * Then answer is 0 ways
		 */

		if (n == 0 || k == 0 || n < k) {
			System.out.println(0);
			return -1;
		}
		
		long[][] dp = new long[n + 1][k + 1];
		
		for (int p = 1; p <= n; p++) {
			for (int t = 1; t <= k; t++) {
				if (p < t) {	// Persons < Teams => 0 way
					dp[p][t] = 0;
				} else if (p == t) {	// Persons == Teams => 1 Way
					dp[p][t] = 1;
				} else {
					dp[p][t] = dp[p - 1][t - 1] + // (p-1) persons makes (t-1) teams, then
												  // pth person will make a separate team  
							   t * dp[p - 1][t];  // (p-1) persons makes (t) teams, then
												  // pth person can join any of the (t) teams
				}
			}
		}
		
		return dp[n][k];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 4;
		int k = 3;
		
		System.out.println(n + " persons and " + k + " teams: " + countWays(n, k));
		
		System.out.println("========================");
		
		n = 5;
		k = 4;
		System.out.println(n + " persons and " + k + " teams: " + countWays(n, k));
	}

}
