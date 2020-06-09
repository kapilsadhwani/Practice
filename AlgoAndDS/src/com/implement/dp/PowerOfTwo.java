package com.implement.dp;

// Iterative Java program to 
// compute modular power 

class PowerOfTwo {
	public static boolean isPowerOfTwo(int n) {
		// Base Condition
		if (n < 4) {
			return (n == 1 || n == 2);
		}
		if (n % 4 != 0) {
			return false;
		}

		return isPowerOfTwo(n / 4);
	}

	// Driver Program to test above functions
	public static void main(String args[]) {
		System.out.println("Is power of two: " + isPowerOfTwo(0));
		System.out.println("Is power of two: " + isPowerOfTwo(1));
		System.out.println("Is power of two: " + isPowerOfTwo(16));
		System.out.println("Is power of two: " + isPowerOfTwo(218));
	}
}
