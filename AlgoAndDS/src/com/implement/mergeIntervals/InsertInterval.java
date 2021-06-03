package com.implement.mergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;

class InsertInterval {
	public static int[][] insert(int[][] intervals, int[] newInterval) {
		// init data
		int newStart = newInterval[0], newEnd = newInterval[1];
		int i = 0, n = intervals.length;
		ArrayList<int[]> output = new ArrayList<int[]>();

		// add all intervals before newInterval
		while (i < n && intervals[i][1] < newStart){
			output.add(intervals[i]);
			i++;
		}

		// merge newInterval
		while (i < n && newEnd >= intervals[i][0]) {	// Current start <= New Interval End
			newStart = Math.min(newStart, intervals[i][0]);
			newEnd = Math.max(newEnd, intervals[i][1]);
			i++;
		}
		output.add(new int[] { newStart, newEnd });

		// add all intervals after newInterval
		while (i < n){
			output.add(intervals[i++]);
		}
		
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