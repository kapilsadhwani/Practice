package com.implement.pepcoding.dp;

import java.util.Arrays;

class EvaluateExpressionToTrue {
	public static int countWays(String str, int n) {
		int dp[][][] = new int[n + 1][n + 1][2];

		for (int row[][] : dp)
			for (int col[] : row)
				Arrays.fill(col, -1);
		return solve(str, 0, n - 1, 1, dp);	// i=0; j=n-1; Evaluate=True
	}

	public static int solve(String str, int i, int j, int isTrue, int[][][] dp) {
		// Empty String
		if (i > j)
			return 0;

		// String of length 1
		if (i == j) {
			if (isTrue == 1) {	// Expression to be evaluated as True
				return (str.charAt(i) == 'T') ? 1 : 0;
			} else {			// Expression to be evaluated as False
				return (str.charAt(i) == 'F') ? 1 : 0;
			}
		}

		if (dp[i][j][isTrue] != -1)
			return dp[i][j][isTrue];

		int ans = 0;

		// Temp Ans
		int leftTrue, rightTrue, leftFalse, rightFalse;

		for (int k = i + 1; k <= j - 1; k = k + 2) {
			if (dp[i][k - 1][1] != -1)
				leftTrue = dp[i][k - 1][1];
			else {
				// Count number of True in left Partition
				leftTrue = solve(str, i, k - 1, 1, dp);
			}
			
			if (dp[i][k - 1][0] != -1)
				leftFalse = dp[i][k - 1][0];
			else {

				// Count number of False in left Partition
				leftFalse = solve(str, i, k - 1, 0, dp);
			}
			
			if (dp[k + 1][j][1] != -1)
				rightTrue = dp[k + 1][j][1];
			else {

				// Count number of True in right Partition
				rightTrue = solve(str, k + 1, j, 1, dp);
			}
			
			if (dp[k + 1][j][0] != -1)
				rightFalse = dp[k + 1][j][0];
			else {

				// Count number of False in right Partition
				rightFalse = solve(str, k + 1, j, 0, dp);
			}

			// Calculate Ans from tempAns
			// Evaluate AND operation
			if (str.charAt(k) == '&') {
				if (isTrue == 1) {
					ans = ans + leftTrue * rightTrue;
				} else {
					ans = ans + leftTrue * rightFalse + 
								leftFalse * rightTrue + 
								leftFalse * rightFalse;
				}
			}
			
			// Evaluate OR operation
			else if (str.charAt(k) == '|') {
				if (isTrue == 1) {
					ans = ans + leftTrue * rightTrue + 
								leftTrue * rightFalse + 
								leftFalse * rightTrue;
				} else {
					ans = ans + leftFalse * rightFalse;
				}
			}

			// Evaluate XOR operation
			else if (str.charAt(k) == '^') {
				if (isTrue == 1) {
					ans = ans + leftTrue * rightFalse + 
								leftFalse * rightTrue;
				} else {
					ans = ans + leftTrue * rightTrue +
								leftFalse * rightFalse;
				}
			}

		}
		return dp[i][j][isTrue] = ans;
	}
	
	public static int countWaysDPII(String str1, String str2) {
		int n = str1.length();
		
		int[][] dpt = new int[n][n];
		int[][] dpf = new int[n][n];
		
		// Gap = 0 to n - 1 OR length = 1 to n
		for (int g = 0; g < dpt.length; g++) {

			// Traverse diagonally with loop ending in last column
			for (int i = 0, j = g; j < dpt[0].length; i++, j++) {
				// Trivial case: ch = 'T' or ch = 'F'
				if (g == 0) {
					char ch = str1.charAt(i);
					
					if(ch == 'T'){
						dpt[i][j] = 1;
						dpf[i][j] = 0;
					}else{
						dpt[i][j] = 0;
						dpf[i][j] = 1;
					}
				} else {
					for (int k = i; k < j; k++) {
						/*
						 * Left half  = i, k; 
						 * Right half = k + 1, j
						 * Operator   = str2[k] 
						 */
						char op = str2.charAt(k);
						int ltc = dpt[i][k];
						int rtc = dpt[k + 1][j];
						int lfc = dpf[i][k];
						int rfc = dpf[k + 1][j];
						
						if(op == '&'){
							dpt[i][j] = dpt[i][j] + 
										ltc * rtc;
							
							dpf[i][j] = dpf[i][j] +
										ltc * rfc + lfc * rtc + lfc * rfc;
						}else if(op == '|'){
							dpt[i][j] = dpt[i][j] +
										ltc * rtc + ltc * rfc + lfc * rtc;
							
							dpf[i][j] = dpf[i][j] +
										lfc * rfc;
						}else{
							// op == '^'
							dpt[i][j] = dpt[i][j] +
										ltc * rfc + lfc * rtc;
							
							dpf[i][j] = dpf[i][j] +
										ltc * rtc + lfc * rfc;
						}
					}
				}
			}
		}
		
		return dpt[0][n - 1];
	}

	// Driver code
	public static void main(String[] args) {
		String symbols = "TTFT";
		String operators = "|&^";
		StringBuilder str = new StringBuilder();
		int j = 0;

		for (int i = 0; i < symbols.length(); i++) {
			str.append(symbols.charAt(i));
			if (j < operators.length())
				str.append(operators.charAt(j++));
		}

		// We obtain the string T|T&F^T
		int n = str.length();

		// There are 4 ways
		// ((T|T)&(F^T)), (T|(T&(F^T))), (((T|T)&F)^T) and
		// (T|((T&F)^T))
		System.out.println("Count ways (AV): " + countWays(str.toString(), n));
		System.out.println("Count ways (Gap Strategy): " + countWaysDPII(symbols, operators));
	}
}
