package com.prep.implement.recursion;

public class RecursiveMultiply {
	static int memoProduct(int a, int b) {
		int bigger = a > b ? a : b;
		int smaller= a < b ? a : b;

		int[] memo = new int[smaller + 1];
		return memoProduct(smaller, bigger, memo);
	}

	static int memoProduct(int smaller, int bigger, int[] memo) {
		if (smaller == 0) { // 0 x bigger = 0
			return 0;
		} 
		if (smaller == 1) { // 1 x bigger bigger
			return bigger;
		}
		if (memo[smaller] > 0) {
			return memo[smaller];
		}

		int s = smaller >> 1; // Divide by 2
		int sidel = memoProduct(s, bigger, memo);
		int side2;
		if (smaller% 2 == 1) {
			side2 = memoProduct(smaller - s, bigger, memo); // smaller-s will also be less than bigger
		}else{
			side2 = sidel;
		}

		/* Sum and cache.*/
		memo[smaller] = sidel + side2;

		return memo[smaller];
	}
	
	static int fastProduct(int a, int b) {
		int bigger  = a < b ? b : a;
		int smaller = a < b ? a : b;

		return fastProductHelper(smaller, bigger);
	}

	static int fastProductHelper(int smaller, int bigger) {
		if (smaller == 0) { // 0 x bigger = 0
			return 0;
		} 
		if (smaller == 1) { // 1 x bigger = bigger
			return bigger;
		}

		int s = smaller >> 1; // Divide by 2
		int halfProd = fastProductHelper(s, bigger);
		
		if (smaller% 2 == 0){
			return halfProd + halfProd;
		}

		return halfProd + halfProd + bigger;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("7 * 5 = " + memoProduct(7,5));
		System.out.println("7 * 6 = " + memoProduct(7,6));
		
		System.out.println("7 * 7 = " + fastProduct(7,7));
		System.out.println("7 * 8 = " + fastProduct(7,8));
	}

}
