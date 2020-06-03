package com.implement.mergeIntervals;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {
	static class myComparator implements Comparator<int[]> {
		public int compare(int[] a, int[] b) {
			return a[1] - b[1];
		}
	}

	public static int eraseOverlapIntervals(int[][] intervals) {
		if (intervals.length == 0) {
			return 0;
		}
		
		// Sort the intervals by end time
		Arrays.sort(intervals, new myComparator());
		
		int end = intervals[0][1];
		
		// Count of number of intervals to keep
		int count = 1;
		
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i][0] >= end) {
				end = intervals[i][1];
				count++;
			}
		}
		return intervals.length - count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] intervals = { { 1, 2 }, 
							  { 2, 3 }, 
							  { 3, 4 }, 
							  { 1, 3 } };

		System.out.println("Number of intervals to remove: " + eraseOverlapIntervals(intervals));

		intervals = new int[][] { { 1, 2 }, 
				                  { 1, 2 }, 
				                  { 1, 2 } };

		System.out.println("Number of intervals to remove: " + eraseOverlapIntervals(intervals));

		intervals = new int[][] { { 1, 2 }, 
                				  { 2, 3 }};

		System.out.println("Number of intervals to remove: " + eraseOverlapIntervals(intervals));
	}

}
