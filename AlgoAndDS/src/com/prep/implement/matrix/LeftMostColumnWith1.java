package com.prep.implement.matrix;

import java.util.Arrays;
import java.util.List;

// java implementation to search 
// an element in a sorted matrix 

class LeftMostColumnWith1 {
	public static int get(int nums[][], int x, int y) {
		return nums[x][y];
	}

	public static List<Integer> setAndGetdimensions(int m, int n) {
		return Arrays.asList(m, n);
	}

	public static int leftMostColumnWithOne(int nums[][]) {
		List<Integer> dims = setAndGetdimensions(nums.length, nums[0].length);
		int rows = dims.get(0);
		int cols = dims.get(1);

		int r = 0, c = cols - 1;
		int p = -1;

		while (r < rows && c >= 0) {
			p = get(nums, r, c);

			if (p == 0)
				r++;
			else {
				c--;
			}
		}

		return c == cols - 1 ? -1 : c + 1;

	}

	// Driver program
	public static void main(String[] args) {
		int mat1[][] = {{0,0,0,1},
						{0,0,1,1},
						{0,1,1,1}};
		
		System.out.println(leftMostColumnWithOne(mat1));
	}
}
