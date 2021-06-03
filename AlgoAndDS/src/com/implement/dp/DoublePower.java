package com.implement.dp;

public class DoublePower {
	public static double myPowR(double x, int n) {
		if (n == 0)
			return 1;
		if (n == 1)
			return x;

		double result = myPowR(x, n / 2);

		if (n % 2 == 0) {
			result = result * result;
		} else {
			result = result * result * x;
		}

		return result;
	}

	public static double myPow(double x, int n) {
		if (n == 0)
			return 1;
		if (n == 1)
			return x;

		if (n < 0) {
			return 1 / myPowR(x, -n);
		}

		return myPowR(x, n);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(myPow(2.00000, 10));
		System.out.println(myPow(2.10000, 3));
		System.out.println(myPow(2.00000, -2));
		System.out.println(myPow(0.00001, 2147483647));
		System.out.println(myPow(2.00000, -2147483648));
	}

}
