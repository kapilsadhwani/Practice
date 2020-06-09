package com.implement.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

class QueueReconstructionByHeight {
	public int[][] reconstructQueue(int[][] people) {
		Arrays.sort(people, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				// if the heights are equal, compare k-values
				return a[0] != b[0] ? b[0] - a[0] : a[1] - b[1];
			}
		});

		List<int[]> list = new LinkedList<int[]>();
		for (int[] p : people) {
			list.add(p[1], p);
		}

		int[][] result = new int[people.length][];

		for (int i = 0; i < people.length; i++) {
			result[i] = list.get(i);
		}

		return result;
	}
}