package com.prep.implement.mergeIntervals;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRoomsI {
	public static boolean canAttendMeetings(int[][] intervals) {
		// Sort the intervals by start time
		Arrays.sort(intervals, 
				new Comparator<int[]>() {
					public int compare(final int[] a, final int[] b) {
			            return a[0] - b[0];
			          }
				});
		
		for(int i=1; i<intervals.length; i++){
			// If next meeting start < previous meeting end, return false
			if(intervals[i][0] < intervals[i-1][1])
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] intervals = {
						{ 0, 30},
						{ 5, 10},
						{ 15, 20}
					};
		
		System.out.println(canAttendMeetings(intervals));
		
		intervals = new int[][]{
						{ 7, 10},
						{ 2, 4}
					};
		
		System.out.println(canAttendMeetings(intervals));
	}

}
