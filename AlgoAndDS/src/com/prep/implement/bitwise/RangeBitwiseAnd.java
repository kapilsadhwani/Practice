package com.prep.implement.bitwise;

public class RangeBitwiseAnd {
	// Find position of MSB in n. For example
	// if n = 17, then position of MSB is 4.
	// If n = 7, value of MSB is 3

	public static int posOfMSB(int n) {
		int pos = -1;
		while (n > 0) {
			n = n >> 1;
			pos++;
		}

		return pos;
	}

	// Function to find Bit-wise & of all numbers from x to y.
	public static int rangeBitwiseAnd(int m, int n) {
		long result = 0L; // Initialize result

		while (m > 0 && n > 0) {

			// Find positions of MSB in x and y
			int posOfMSB_in_M = posOfMSB(m);
			int posOfMSB_in_N = posOfMSB(n);

			// If positions are not same, return
			if (posOfMSB_in_M != posOfMSB_in_N)
				break;

			// Add 2^posOfMSB_in_M to result
			long twoPowMSBPos = (1 << posOfMSB_in_M);
			result = result + twoPowMSBPos;

			// subtract 2^msb_p1 from x and y.
			m = (int) (m - twoPowMSBPos);
			n = (int) (n - twoPowMSBPos);
		}

		return (int) result;
	}

	public static void main(String[] args) {

		System.out.println(rangeBitwiseAnd(2147483647, 2147483647));
	}
}
