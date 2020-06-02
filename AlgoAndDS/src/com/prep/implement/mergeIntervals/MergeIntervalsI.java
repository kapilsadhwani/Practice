package com.prep.implement.mergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MergeIntervalsI {
	public static int[][] merge(int[][] intervals) {
		if(intervals.length < 1) return new int[0][];
		
		// Sort the intervals by start time
		Arrays.sort(intervals, new Comparator<int[]>() {
			public int compare(final int[] a, final int[] b) {
				return a[0] - b[0];
			}
		});

		LinkedList<int[]> merged = new LinkedList<>();
		int[] current = intervals[0];
		
		// Iterate over remaining intervals
		for (int i = 1; i < intervals.length; i++) {
			if(current[1] < intervals[i][0]){
				merged.add(current);
				current = intervals[i];
			}else{
				current[1] = Math.max(current[1], intervals[i][1]);
			}
		}
		
		merged.add(current);
		
		return merged.toArray(new int[merged.size()][2]);
	}

	/*
	 * Another Approach
	 */
	public static class Interval implements Comparable<Interval> {
		public Endpoint left = new Endpoint();
		public Endpoint right = new Endpoint();

		private static class Endpoint {
			public boolean isClosed;
			public int val;
		}

		public int compareTo(Interval i) {
			if (Integer.valueOf(left.val)
					.compareTo(Integer.valueOf(i.left.val)) != 0) {
				return left.val - i.left.val;
			}
			// Left endpoints are equal , so now see if one is closed and the
			// other open - closed intervals should appear first.
			if (left.isClosed && !i.left.isClosed) {
				return -1;
			}
			if (!left.isClosed && i.left.isClosed) {
				return 1;
			}
			return 0;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || !(obj instanceof Interval)) {
				return false;
			}
			if (this == obj) {
				return true;
			}
			Interval that = (Interval) obj;
			return (left.val == that.left.val
						&& left.isClosed == that.left.isClosed) &&
					(right.val == that.right.val
						&& right.isClosed == that.right.isClosed);
		}

		@Override
		public int hashCode() {
			return Objects.hash(left.val, left.isClosed);
		}
	}

	public static List<Interval> unionOfIntervals(List<Interval> intervals) {
		// Empty input.
		if (intervals.isEmpty()) {
			return Collections.EMPTY_LIST;
		}
		// Sort intervals according to left endpoints of intervals.
		Collections.sort(intervals);
		
		Interval curr = intervals.get(0);
		List<Interval> result = new ArrayList<>();
		for (int i = 1; i < intervals.size(); ++i) {
			if (intervals.get(i).left.val < curr.right.val
					|| (intervals.get(i).left.val == curr.right.val && (intervals
							.get(i).left.isClosed || curr.right.isClosed))) {
				if (intervals.get(i).right.val > curr.right.val
						|| (intervals.get(i).right.val == curr.right.val && intervals
								.get(i).right.isClosed)) {
					curr.right = intervals.get(i).right;
				}
			} else {
				result.add(curr);
				curr = intervals.get(i);
			}
		}
		result.add(curr);
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] intervals = { 	{ 1, 3 }, 
								{ 2, 6 }, 
								{ 15, 18 }, 
								{ 8, 10 }};

		System.out.println();
		
		int[][] merged = merge(intervals);
		
		for(int[] interval:merged)
			System.out.print(Arrays.toString(interval) + " ");

		intervals = new int[][] { { 1, 4 }, 
								  { 4, 5 } };

		System.out.println();
		
		merged = merge(intervals);
		
		for(int[] interval:merged)
			System.out.print(Arrays.toString(interval) + " ");
	}
}