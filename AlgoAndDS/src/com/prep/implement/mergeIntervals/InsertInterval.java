package com.prep.implement.mergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;

class InsertInterval {
	public static int[][] insert(int[][] intervals, int[] newInterval) {
		// init data
		int newStart = newInterval[0], newEnd = newInterval[1];
		int idx = 0, n = intervals.length;
		ArrayList<int[]> output = new ArrayList<int[]>();

		// add all intervals before newInterval
		while (idx < n && intervals[idx][1] < newStart)
			output.add(intervals[idx++]);

		// merge newInterval
		while (idx < n && intervals[idx][0] <= newEnd) {
			newStart = Math.min(newStart, intervals[idx][0]);
			newEnd = Math.max(newEnd, intervals[idx][1]);
			++idx;
		}
		output.add(new int[] { newStart, newEnd });

		// add all intervals after newInterval
		while (idx < n)
			output.add(intervals[idx++]);

		return output.toArray(new int[output.size()][2]);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] intervals = { 	{ 1, 3 }, 
								{ 6, 9 }};
		int[] newInterval = new int[]{2,5};
		
		System.out.println();
		
		int[][] merged = insert(intervals, newInterval);
		
		for(int[] interval:merged)
			System.out.print(Arrays.toString(interval) + " ");

		intervals = new int[][]{	{ 1, 2 }, 
									{ 3, 5 }, 
									{ 6, 7 }, 
									{ 8, 10 }, 
									{ 12, 16 }};
		newInterval = new int[]{4,8};
		
		System.out.println();
		
		merged = insert(intervals, newInterval);
		
		for(int[] interval:merged)
			System.out.print(Arrays.toString(interval) + " ");
	}
}