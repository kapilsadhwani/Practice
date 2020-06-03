package com.implement.google;

import java.util.Arrays;

/*
 * Iterate through all the intervals and store the longest position value that can be reached
 * Every time choose to go to the farthest point it can reach
 */
public class WaterGardenWithMinTaps {
	public static int minTaps(int n, int[] ranges) {
		int[] intervals = new int[n];
		Arrays.fill(intervals, -1);					//[1, 2, 1, 0, 2, 1, 0, 1]
		for (int i = 0; i < ranges.length; i++) {
			if (ranges[i] == 0)
				continue;
			int left = i - ranges[i] < 0 ? 0 : i - ranges[i];
			int right = i + ranges[i] > n ? n : i + ranges[i];
			intervals[left] = Math.max(intervals[left], right);
		}
		if (intervals[0] == -1)
			return -1;
		int longest = intervals[0];
		int count = 1;
		int i = 0;
		while (longest < n) {
			int temp = Integer.MIN_VALUE;
			for (; i <= longest; i++) {
				int val = intervals[i];
				if (val == -1)
					continue;
				temp = Math.max(temp, val);
			}
			if (temp <= longest)
				return -1;
			longest = temp;
			count++;
		}
		
		//System.out.println(Arrays.toString(ranges));
		//System.out.print(Arrays.toString(intervals));
		
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ranges = null;
		int n;
		
		ranges = new int[]{3,4,1,1,0,0};
		n = 5;
		System.out.println(" : " + minTaps(n, ranges));
		
		ranges = new int[]{0,0,0,0};
		n = 3;
		System.out.println(" : " + minTaps(n, ranges));
		
		ranges = new int[]{1,2,1,0,2,1,0,1};
		n = 7;
		System.out.println(" : " + minTaps(n, ranges));
		
		ranges = new int[]{4,0,0,0,0,0,0,0,4};
		n = 8;
		System.out.println(" : " + minTaps(n, ranges));
		
		ranges = new int[]{4,0,0,0,4,0,0,0,4};
		n = 8;
		System.out.println(" : " + minTaps(n, ranges));
	}

}
