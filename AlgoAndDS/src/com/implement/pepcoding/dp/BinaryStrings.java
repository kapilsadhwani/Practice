package com.implement.pepcoding.dp;

/*
 *  Java program to count number of binary strings of n characters such that there
 *  are no consecutive zeros
 */

class BinaryStrings {
	public static long countStr(int n) {
		int oldZeros = 1;
		int oldOnes = 1;

		for (int i = 2; i <= n; i++) {
			int newZeros = oldOnes;
			int newOnes = oldZeros + oldOnes;

			oldOnes = newOnes;
			oldZeros = newZeros;
		}

		return oldZeros + oldOnes;
	}

	// Driver code
	public static void main(String[] args) {
		int n = 6;

		System.out.println(countStr(n));
	}
}