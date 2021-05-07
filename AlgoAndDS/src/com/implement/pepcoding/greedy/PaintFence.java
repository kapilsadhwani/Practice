package com.implement.pepcoding.greedy;

/*
 * Paint Fence
 * Given a number n and a number k in separate lines, representing the number of fences 
 * and number of colors.
 * Calculate the number of ways in which the fences could be painted so that not more 
 * than two fences have same colors.
 */
public class PaintFence {
	public static long countWays(int n, int k) {
		long same = k * 1;
		long diff = k * (k - 1);
		long total = same + diff;

		for (int i = 3; i <= n; i++) {
			same = diff * 1;
			diff = total * (k - 1);
			total = same + diff;
		}
		return total;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 8;
		int k = 3;

		System.out.println(countWays(n, k));
	}

}
