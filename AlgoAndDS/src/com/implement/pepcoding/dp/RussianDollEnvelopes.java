package com.implement.pepcoding.dp;

import java.util.Arrays;

public class RussianDollEnvelopes {
	static class Envelope implements Comparable<Envelope> {
		int w;
		int h;

		Envelope(int w, int h) {
			this.w = w;
			this.h = h;
		}

		@Override
		public int compareTo(Envelope o) {
			// TODO Auto-generated method stub
			return this.w - o.w;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "[" + this.w + " " + this.h + "]" ;
		}

	}
	
	public static int maxNumOfNestedEnvlopes(Envelope[] envlps){
		// Sort input based on northbound
		Arrays.sort(envlps);
		
		System.out.println(Arrays.toString(envlps));
		
		// Apply LIS on southbound values
		int n = envlps.length;
		
		int[] dp = new int[n];
		
		/* Pick maximum of all LIS values */
		int ans = 0;
		
		/*
		 * Compare till previous elements
		 */
		for (int i = 0; i < n; i++) {
			int max = 0;
			for (int j = 0; j < i; j++) {
				if (envlps[j].h < envlps[i].h && envlps[j].w < envlps[i].w) {
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
		Envelope[] envlps = new Envelope[11];

		envlps[0] = new Envelope(17, 5);
		envlps[1] = new Envelope(26, 18);
		envlps[2] = new Envelope(25, 34);
		envlps[3] = new Envelope(48, 84);
		envlps[4] = new Envelope(63, 72);
		envlps[5] = new Envelope(42, 86);
		envlps[6] = new Envelope(9, 55);
		envlps[7] = new Envelope(4, 70);
		envlps[8] = new Envelope(21, 45);
		envlps[9] = new Envelope(68, 76);
		envlps[10] = new Envelope(58, 51);
		
		System.out.println(maxNumOfNestedEnvlopes(envlps));
	}

}
