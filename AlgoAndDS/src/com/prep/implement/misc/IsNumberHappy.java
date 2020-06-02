package com.prep.implement.misc;

import java.util.HashSet;
import java.util.Set;


public class IsNumberHappy {
	public static int numberOfDigits(int n){
		int count = 0;
		
		while(n > 0){
			count++;
			n = n/10;
		}
		
		return count;
	}
		
	public static int sqOfDigits(int n){
		int sq = 0;
		int digit = 0;
		
		while(n%10 == 0){
			n = n/10;
		}
		
		while(n > 0){
			digit = n%10;
			
			sq += digit*digit;
			
			n = n/10;
		}
		
		return sq;
	}
	
	public static boolean isHappy(int n) {
		if(n < 1) return false;
		if(n == 1) return true;
		
		Set<Integer> setOfInts = new HashSet<Integer>();
		setOfInts.add(1);
		
		while(n > 1){
			if(setOfInts.contains(n)) break;
			setOfInts.add(n);
			
			n = sqOfDigits(n);
		}
		
		return n == 1;
	}

	public static void main(String[] args) {
		System.out.println(isHappy(19));
		System.out.println(numberOfDigits(1));
		System.out.println(numberOfDigits(10));
		System.out.println(numberOfDigits(100));
		System.out.println(numberOfDigits(101));
		System.out.println(numberOfDigits(999));
		System.out.println(numberOfDigits(1000));
		System.out.println(numberOfDigits(9999));
		System.out.println(numberOfDigits(10000));
		System.out.println(numberOfDigits(99999));
	}

}