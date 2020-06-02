package com.prep.implement.misc;

import java.util.Random;

public class Random5WithDie7 {
	private static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public static int rand7(){
		return getRandomNumberInRange(1,7);
	}
	
	public static int rand5() {
		int result = 7; // arbitrarily large
		while (result > 5) {
			result = rand7();
		}
		return result;
	}
}
