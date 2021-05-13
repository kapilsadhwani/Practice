package com.implement.pepcoding.dp;

import java.util.Arrays;
import java.util.Queue;

import java.util.LinkedList;

public class JumpGame {
	static class Pair{
		int i;
		int s;
		int d;
		String psf;
		
		public Pair(int i, int s, int d, String psf) {
			super();
			this.i = i;
			this.s = s;
			this.d = d;
			this.psf = psf;
		}
	}
	
	public static boolean canJump(int[] nums) {
		if (nums.length <= 1)
			return true;

		int max = nums[0]; // max stands for the largest index that can be
							// reached.

		for (int i = 0; i < nums.length; i++) {
			// if not enough to go to next
			if (max <= i && nums[i] == 0)
				return false;

			// update max
			if (i + nums[i] > max) {
				max = i + nums[i];
			}

			// max is enough to reach the end
			if (max >= nums.length - 1)
				return true;
		}

		return false;
	}
	
	private static Integer jumpMinMoves(int[] arr) {
		int n = arr.length;

		Integer dp[] = new Integer[n + 1];	// Null indicates no path
		dp[n] = 0;							// 0 moves to destination

		for (int i = n - 1; i >= 0; i--) {
			int min = Integer.MAX_VALUE;

			for (int j = 1; j <= arr[i] && (i + j < dp.length); j++) {
				if (dp[i + j] != null) {
					min = Math.min(min, dp[i + j]);
				}
			}

			dp[i] = min != Integer.MAX_VALUE ? min + 1 : null;
		}

		System.out.println(Arrays.toString(dp));
		return dp[0];
    }
	
	private static void pathsWithMinMoves(int[] arr) {
		int n = arr.length;

		Integer dp[] = new Integer[n]; // Null indicates no path
		dp[n - 1] = 0; // 0 moves to destination

		for (int i = n - 2; i >= 0; i--) {
			int steps = arr[i];
			int min = Integer.MAX_VALUE;

			for (int j = 1; j <= steps && (i + j < dp.length); j++) {
				if (dp[i + j] != null && dp[i + j] < min) {
					min = dp[i + j];
				}
			}

			if (min != Integer.MAX_VALUE) {
				dp[i] = min + 1;
			}
		}

		System.out.println(Arrays.toString(dp));
		System.out.println("Minimum moves DP2: " + dp[0]);

		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(0, arr[0], dp[0], "0"));
		Pair rem = null;

		while (queue.size() > 0) {
			rem = queue.poll();

			if (rem.d == 0) {
				System.out.println(rem.psf);
			}else{

				for (int j = 1; j <= rem.s && rem.i + j < dp.length; j++) {
					int ci = rem.i + j;		// Current Index
	
					if (dp[ci] != null && dp[ci] == rem.d - 1) {
						queue.add(new Pair(ci, arr[ci], dp[ci], rem.psf + " -> "
								+ ci));
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 2, 3, 1, 1, 4 };
		System.out.println("Can Jump? " + Arrays.toString(arr) + " : " + canJump(arr));
		System.out.println("Minimum moves: " + jumpMinMoves(arr));
		pathsWithMinMoves(arr);	
		System.out.println("====================================");

		int[] arr1 = { 3, 2, 1, 0, 4 };
		System.out.println("Can Jump? " + Arrays.toString(arr1) + " : " + canJump(arr1));
		System.out.println("Minimum moves: " + jumpMinMoves(arr1));
		System.out.println("====================================");
		
		int[] arr2 = { 3, 3, 0, 2, 1, 2, 4, 2, 0, 0 };
		System.out.println("Can Jump? " + Arrays.toString(arr2) + " : " + canJump(arr2));
		System.out.println("Minimum moves: " + jumpMinMoves(arr2));
		pathsWithMinMoves(arr2);
		System.out.println("====================================");
		
		int[] arr3 = { 1, 1, 1, 4, 9, 8, 1, 1, 10, 1 };
		System.out.println("Can Jump? " + Arrays.toString(arr3) + " : " + canJump(arr3));
		System.out.println("Minimum moves: " + jumpMinMoves(arr3));
		pathsWithMinMoves(arr3);
		System.out.println("====================================");
	}

}
