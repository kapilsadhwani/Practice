package com.prep.implement.dp;

// Java implementation of the approach 
import java.util.*;

class CuttingBinaryString {
	// Function that returns true if n is a power of 5
	static boolean ispower(long n) {
		if (n < 25) {
			return (n == 1 || n == 5);
		}
		if (n % 25 != 0) {
			return false;
		} else {
			return ispower(n / 25);
		}
	}

	// Function to return the decimal value of binary equivalent
	static long number(String s, int i, int j) {
		long ans = 0;
		for (int k = i; k <= j; k++) {
			ans = ans * 2 + (s.charAt(k) - '0');
		}
		return ans;
	}

	// Function to return the minimum partitions required
	// Final Output = Number of min partitions + 1
	static int solve(String s, int i, int j) {
		if(i >= j) return j-i;					// Invalidate by returning some max possible cuts
		
		if(s.charAt(i) == '0') return j-i;		// Invalidate by returning some max possible cuts
		
		long deci = number(s, i, j);
		if(ispower(deci)) return 0;
		
		int min = j-i;
		
		// k will go from i to j-1
		for (int k = i; k <= j - 1; k++) {
			int tempAns = 1 + solve(s, i, k) +
							solve(s, k+1, j);
			
			min = Math.min(min,tempAns);
		}

		return min;
	}

	// Function to return the minimum cuts required
	static int solveR(String s, int n) {
		if(number(s, 0, n-1) == 0) return -1;
		return solve(s, 0, n-1) + 1;
	}

	// Function to return the minimum cuts required
	static int minCuts(String s, int n) {
		int[] dp = new int[n + 1];

		// Alongocating memory for dp[] array
		Arrays.fill(dp, n + 1);
		dp[0] = 0;

		// From length 1 to n
		for (int i = 1; i <= n; i++) {

			// If previous character is '0' then ignore
			// to avoid number with leading 0s.
			if (s.charAt(i - 1) == '0') {
				continue;
			}
			for (int j = 0; j < i; j++) {

				// Ignore s[j] = '0' starting numbers
				if (s.charAt(j) == '0') {
					continue;
				}

				// Number formed from s[j....i]
				long num = number(s, j, i);

				// Check for power of 5
				if (!ispower(num)) {
					continue;
				}

				// Assigning min value to get min cut possible
				dp[i] = Math.min(dp[i], dp[j] + 1);
			}
		}

		// (n + 1) to check if all the Strings are traversed
		// and no divisible by 5 is obtained like 000000
		return ((dp[n] < n + 1) ? dp[n] : -1);
	}

	// Driver code
	public static void main(String[] args) {
		String s = "101101101";
		System.out.println(solveR(s, s.length()));
		
		s = "1111101";
		System.out.println(solveR(s, s.length()));
		
		s = "00000";
		System.out.println(solveR(s, s.length()));
		
		//System.out.println(number("100", 0, 2));
	}
}

// This code is contributed by 29AjayKumar
