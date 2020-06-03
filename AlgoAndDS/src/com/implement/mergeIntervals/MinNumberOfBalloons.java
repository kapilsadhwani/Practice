package com.implement.mergeIntervals;

import java.util.Arrays;
import java.util.Comparator;

class MinNumberOfBalloons {
	public static int findMinArrowShots(int[][] points) {
		if (points.length == 0)
			return 0;

		// sort by end of given interval
		Arrays.sort(points, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});

		int arrows = 1;
		int prevEnd = points[0][1];

		for (int i = 1; i < points.length; i++) {
			// if the current balloon starts after the end of another one,
			// one needs one more arrow
			if (points[i][0] > prevEnd) {
				arrows++;

				prevEnd = points[i][1];
			}
		}

		return arrows;
	}
  
  public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] points = { 	{ 10, 16 }, 
							{ 2, 8 }, 
							{ 1, 6 }, 
							{ 7, 12 }};
		System.out.print(findMinArrowShots(points));
	}
}