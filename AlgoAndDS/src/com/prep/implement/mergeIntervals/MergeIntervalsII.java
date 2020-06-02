package com.prep.implement.mergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Time Complexity: O(M + N), where M, NM,N are the lengths of A and B respectively.
 * Space Complexity: O(M + N), the maximum size of the answer.
 */
class MergeIntervalsII {
	public static int[][] intervalIntersection(int[][] A, int[][] B) {
		List<int[]> ans = new ArrayList();
		int i = 0, j = 0;

		while (i < A.length && j < B.length) {
			// Let's check if A[i] intersects B[j].
			// lo - the startpoint of the intersection
			// hi - the endpoint of the intersection
			int lo = Math.max(A[i][0], B[j][0]);
			int hi = Math.min(A[i][1], B[j][1]);
			if (lo <= hi)
				ans.add(new int[] { lo, hi });

			// Remove the interval with the smallest end point
			if (A[i][1] < B[j][1])
				i++;
			else
				j++;
		}

		return ans.toArray(new int[ans.size()][2]);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] A = { 	{ 0, 2 }, 
						{ 5, 10 }, 
						{ 13, 23 }, 
						{ 24, 25 }};

		int[][] B = {	{ 1, 5 }, 
						{ 8, 12 }, 
						{ 15, 24 }, 
						{ 25, 26 }};

		System.out.println();
		
		int[][] intersect = intervalIntersection(A, B);
		
		for(int[] interval:intersect)
			System.out.print(Arrays.toString(interval) + " ");
	}
}