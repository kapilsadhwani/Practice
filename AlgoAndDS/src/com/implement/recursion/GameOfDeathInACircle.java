package com.implement.recursion;

import java.util.ArrayList;
import java.util.List;

public class GameOfDeathInACircle {
	private static void play(int n, int k, int index, List<Integer> nums){
		if(nums.size() == 1){
			return;
		}
		
		index = (index + k) % nums.size();
		
		nums.remove(index);
		
		play(n, k, index, nums);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> nums = new ArrayList<Integer>();
		int n, k;

		System.out.println("================= n=2, k=1 =====================");
		n = 2;
		k = 1;

		for (int i = 1; i <= n; i++) {
			nums.add(i);
		}
		
		k = k - 1;
		play(n, k, 0, nums);
		
		System.out.println(nums);
		
		System.out.println("================= n=4, k=2 =====================");
		nums.clear();
		n = 4;
		k = 2;

		for (int i = 1; i <= n; i++) {
			nums.add(i);
		}
		
		k = k - 1;
		play(n, k, 0, nums);
		
		System.out.println(nums);
		
		System.out.println("================= n=40, k=7 =====================");
		nums.clear();
		n = 40;
		k = 7;

		for (int i = 1; i <= n; i++) {
			nums.add(i);
		}
		
		k = k - 1;
		play(n, k, 0, nums);
		
		System.out.println(nums);
		
		System.out.println("================= n=50, k=10 =====================");
		nums.clear();
		n = 50;
		k = 10;

		for (int i = 1; i <= n; i++) {
			nums.add(i);
		}
		
		k = k - 1;
		play(n, k, 0, nums);
		
		System.out.println(nums);
	}

}
