package com.implement.misc;

public class AplusB {
	/*
     * param a: The first integer
     * param b: The second integer
     * return: The sum of a and b
     */
    public int aplusb(int a, int b) {
        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }

	public static void main(String[] args) {
		AplusB ab = new AplusB();
		// TODO Auto-generated method stub
		System.out.println(ab.aplusb(5,6));
	}

}
