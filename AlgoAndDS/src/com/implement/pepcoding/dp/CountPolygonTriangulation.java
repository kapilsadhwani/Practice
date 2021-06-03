package com.implement.pepcoding.dp;


public class CountPolygonTriangulation {
	static int numberOfChords(int n) {
		int c = n - 2;	// Triangulation of 5  = Catalan of 3
		
		if(c == 0 || c == 1) return 1;
		
		int[] dp = new int[c + 1];

		dp[0] = 1;
		dp[1] = 1;

		for (int i = 2; i <= c; i++) { // or i < dp.length
			/*
			 * Catalan Number
			 * T(5) = T(0)*T(4) + T(1)*T(3) + T(2)*T(2) + T(3)*T(1) + T(4)*T(0)
			 */
			for (int j = 0; j < i; j++) {
				dp[i] = dp[i] + dp[j] * dp[i - j - 1];
			}
		}

		return dp[c];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 2; i <= 10; i++) {
			System.out.println("Triangulation of : " + i + " --> " + numberOfChords(i));
		}
	}

}