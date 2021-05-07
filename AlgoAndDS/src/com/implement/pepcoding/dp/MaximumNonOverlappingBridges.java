package com.implement.pepcoding.dp;

import java.util.Arrays;

public class MaximumNonOverlappingBridges {
	static class Bridge implements Comparable<Bridge> {
		int n;
		int s;

		Bridge(int n, int s) {
			this.n = n;
			this.s = s;
		}

		@Override
		public int compareTo(Bridge o) {
			// TODO Auto-generated method stub
			if (this.n != o.n) {
				return this.n - o.n;
			}

			return this.s - o.s;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "[" + this.n + " " + this.s + "]" ;
		}

	}
	
	public static int maxNonOverlappingBridges(Bridge[] brdgs){
		// Sort input based on northbound
		Arrays.sort(brdgs);
		
		System.out.println(Arrays.toString(brdgs));
		
		// Apply LIS on southbound values
		int n = brdgs.length;
		
		int[] dp = new int[n];
		
		/* Pick maximum of all LIS values */
		int ans = 0;
		
		/*
		 * Compare till previous elements
		 */
		for (int i = 0; i < n; i++) {
			int max = 0;
			for (int j = 0; j < i; j++) {
				if (brdgs[j].s <= brdgs[i].s) {
					if (dp[j] > max) {
						max = dp[j];
					}
				}
			}

			dp[i] = max + 1;

			if (dp[i] > ans) {
				ans = dp[i];
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bridge[] brdgs = new Bridge[10];

		brdgs[0] = new Bridge(10, 20);
		brdgs[1] = new Bridge(2, 7);
		brdgs[2] = new Bridge(8, 15);
		brdgs[3] = new Bridge(17, 3);
		brdgs[4] = new Bridge(21, 40);
		brdgs[5] = new Bridge(50, 4);
		brdgs[6] = new Bridge(41, 57);
		brdgs[7] = new Bridge(60, 80);
		brdgs[8] = new Bridge(80, 90);
		brdgs[9] = new Bridge(1, 30);
		
		System.out.println(maxNonOverlappingBridges(brdgs));
	}

}
