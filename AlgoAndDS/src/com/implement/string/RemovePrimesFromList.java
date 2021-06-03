package com.implement.string;

import java.util.ArrayList;

public class RemovePrimesFromList {
	private static boolean isPrime(int val) {
		// TODO Auto-generated method stub
		
		for(int div = 2; div * div <= val; div++){
			if(val % div == 0){
				return false;
			}
		}
		return true;
	}
	
	public static void solution(ArrayList<Integer> al){
		for (int i = al.size() - 1; i >= 0; i--) {
			int val = al.get(i);

			if (isPrime(val) == true) {
				al.remove(i);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
