package com.implement.google;

import java.util.Arrays;

public class MultipleContainers {

	public int solve(int n, int[] items, int m, int[][] containers) {
		// not needed if the arrays are pre-sorted
		Arrays.sort(items);
		
		int[] containerType;
		
		int min_so_far = Integer.MAX_VALUE;
		int minContainter = -1;

		for (int i = 0; i < m; i++) {
			containerType = containers[i];

			// no needed if the arrays are pre-sorted
			Arrays.sort(containerType);

			int itemIdx = 0;
			int containerIdx = 0;			
			int currMin = 0;
			
			while (itemIdx < n && containerIdx < containerType.length) {
				int itemVal = items[itemIdx];
				int containerVal = containerType[containerIdx];

				if (itemVal > containerVal) {
					containerIdx++;
				} else {
					currMin += containerVal - itemVal;

					itemIdx++;
				}
			}
			
			// If container can't hold all the items, continue with next container
			if(itemIdx < n) continue;
			
			if(min_so_far > currMin){
				min_so_far = currMin;
				minContainter = i;
			}
		}

		return minContainter == Integer.MAX_VALUE ? -1 : minContainter + 1;
	}

	public static void main(String[] args) {
		MultipleContainers multipleContainers = new MultipleContainers();

		// int n = 3;
		// int[] items = {4, 6, 7};
		// int m = 2;
		// int[][] containers = {{5, 8, 9}, {6, 7, 10}};
		// expected: container 2
		//

		// Explanation :
		// 1st type of container wastes minimum space of (5-4)+(8-6)+(8-7)=4
		// 2nd type of container wastes minimum space of (6-4)+(6-6)+(7-7)=2

		int n = 3;
		int[] items = { 7, 7, 7 };
		int m = 2;
		int[][] containers = { { 5, 8, 9 }, { 6, 7, 10 } };
		// expected: 2:

		// Explanation :
		// 1st type of container wastes minimum space of (8-7)+(8-7)+(8-7)=3
		// 2nd type of container wastes minimum space of (7-7)+(7-7)+(7-7)=0 as
		// same size can be used multiple times

		System.out.println(multipleContainers.solve(n, items, m, containers));
	}
}