package com.implement.pepcoding.dp;

/*
 * Friends Pairing
 * Given a number n, representing the number of friends.
 * Each friend can stay single or pair up with any of it's friends.
 * Calculate the number of ways in which these friends can stay single or pair up.
 * 
 * E.g.
 * 1 person can stay single or pair up in 1 way.
 * 2 people can stay singles or pair up in 2 ways. 12 => 1-2, 12.
 * 3 people (123) can stay singles or pair up in 4 ways. 123 => 1-2-3, 12-3, 13-2, 23-1.
 */
public class FriendsPairing {
	private static int countWays(int n) {
		int[] dp = new int[n + 1];
		
		if (n == 1 || n == 2) {
			return n;
		}
		
		/*
		 * Single person can stay single
		 */
		dp[1] = 1;

		/*
		 * 2 persons can stay single or make a pair
		 */
		dp[2] = 2;

		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + 				// First person stay single
					(i - 1) * dp[i - 2]; 		// First person make a pair with (n - 1)  others
		}

		return dp[n];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for (int i = 1; i <= 10; i++) {
			System.out.println("Number of friends: " + i + " ==> " + countWays(i) + " ways.");
		}
	}

}
