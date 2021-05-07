package com.implement.recursion;

public class GameTheory {
	
	public static int coinMax(int[] num, int l, int r){
		if (l + 1 == r){
			return Math.max(num[l], num[r]);
		}
		
		return Math.max(num[l] + Math.min(coinMax(num, l+2, r), coinMax(num, l+1, r-1)), 
						num[r] + Math.min(coinMax(num, l+1, r-1), coinMax(num, l, r-2)));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] num = {1, 5, 7, 3, 2, 4};
		
		System.out.println(coinMax(num, 0, num.length-1));
		
		num = new int[]{1, 5, 700, 3};
		
		System.out.println(coinMax(num, 0, num.length-1));
	}

}
