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
	
	public boolean isPowerOfTwoBW(int n) {
        if (n == 0) return false;
        
        long x = (long) n;
        
        // X and its 1's complement
        return (x & (-x)) == x;
    }

	// Driver Program to test above functions
	public static void main(String args[]) {
		System.out.println("Is power of two: " + isPowerOfTwo(0));
		System.out.println("Is power of two: " + isPowerOfTwo(1));
		System.out.println("Is power of two: " + isPowerOfTwo(16));
		System.out.println("Is power of two: " + isPowerOfTwo(218));
	}
}
