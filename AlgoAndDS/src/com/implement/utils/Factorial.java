package com.implement.utils;

public class Factorial {
	public static int factorial(int n){
		if (n == 0) return 1;
		return n * factorial(n-1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(factorial(5));
	}

}
