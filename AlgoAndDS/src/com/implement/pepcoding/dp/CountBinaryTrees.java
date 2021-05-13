package com.implement.pepcoding.dp;

public class CountBinaryTrees {
	static int bottomUpCountTrees(int n) {
		int[] dp = new int[n + 1];

		dp[0] = 1;
		dp[1] = 1;

		for (int i = 2; i <= n; i++) { // or i < dp.length
			/*
			 * Catalan Number
			 * T(5) = T(0)*T(4) + T(1)*T(3) + T(2)*T(2) + T(3)*T(1) + T(4)*T(0)
			 */
			for (int j = 0; j < i; j++) {
				dp[i] = dp[i] + dp[j] * dp[i - j - 1];
			}
		}

		return dp[n];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Number of trees with 1 node : " + bottomUpCountTrees(1));
		System.out.println("Number of trees with 2 nodes : " + bottomUpCountTrees(2));
		System.out.println("Number of trees with 3 nodes : " + bottomUpCountTrees(3));
		System.out.println("Number of trees with 4 nodes : " + bottomUpCountTrees(4));
		System.out.println("Number of trees with 5 nodes : " + bottomUpCountTrees(5));
		System.out.println("Number of trees with 6 nodes : " + bottomUpCountTrees(6));
	}

}