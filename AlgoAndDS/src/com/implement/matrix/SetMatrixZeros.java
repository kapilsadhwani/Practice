package com.implement.matrix;

import java.util.HashSet;
import java.util.Set;

public class SetMatrixZeros {

	/*
	 * Time Complexity : O(M×N)
	 * Space Complexity : O(1)
	 */
	public void setZeroes(int[][] matrix) {
		Boolean isCol = false;
		int R = matrix.length;
		int C = matrix[0].length;

		for (int i = 0; i < R; i++) {

			// Since first cell for both first row and first column is the same
			// i.e. matrix[0][0]
			// We can use an additional variable for either the first row/column.
			// For this solution we are using an additional variable for the
			// first column and using matrix[0][0] for the first row.
			if (matrix[i][0] == 0) {
				isCol = true;
			}

			for (int j = 1; j < C; j++) {
				// If an element is zero, we set the first element of the
				// corresponding row and column to 0
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}

		// Iterate over the array once again and using the first row and first
		// column, update the elements.
		for (int i = 1; i < R; i++) {
			for (int j = 1; j < C; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}

		// See if the first row needs to be set to zero as well
		if (matrix[0][0] == 0) {
			for (int j = 1; j < C; j++) {
				matrix[0][j] = 0;
			}
		}

		// See if the first column needs to be set to zero as well
		if (isCol) {
			for (int i = 1; i < R; i++) {
				matrix[i][0] = 0;
			}
		}
	}
	
	/*
	 * Time Complexity: O(M×N) where M and N are the number of rows and columns
	 * respectively. Space Complexity: O(M+N).
	 */
	public void setZeroesExtraSpace(int[][] matrix) {
		int R = matrix.length;
		int C = matrix[0].length;
		Set<Integer> rows = new HashSet<Integer>();
		Set<Integer> cols = new HashSet<Integer>();

		// Essentially, we mark the rows and columns that are to be made zero
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (matrix[i][j] == 0) {
					rows.add(i);
					cols.add(j);
				}
			}
		}

		// Iterate over the array once again and using the rows and cols sets,
		// update the elements.
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (rows.contains(i) || cols.contains(j)) {
					matrix[i][j] = 0;
				}
			}
		}
	}

	/*
	 * Time Complexity : O((M×N)×(M+N)) where M and N are the number of rows and
	 * columns respectively. Even though this solution avoids using space, but
	 * is very inefficient since in worst case for every cell we might have to
	 * zero out its corresponding row and column. Thus for all (M×N) cells
	 * zeroing out (M+N) cells.
	 * 
	 * Space Complexity : O(1)
	 */
	public void setZeroesConstantSpaceBF(int[][] matrix) {
		int MODIFIED = -1000000;
		int R = matrix.length;
		int C = matrix[0].length;

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (matrix[r][c] == 0) {
					// We modify the corresponding rows and column elements in
					// place.
					// Note, we only change the non zeroes to MODIFIED
					for (int k = 0; k < C; k++) {
						if (matrix[r][k] != 0) {
							matrix[r][k] = MODIFIED;
						}
					}
					for (int k = 0; k < R; k++) {
						if (matrix[k][c] != 0) {
							matrix[k][c] = MODIFIED;
						}
					}
				}
			}
		}

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				// Make a second pass and change all MODIFIED elements to 0 """
				if (matrix[r][c] == MODIFIED) {
					matrix[r][c] = 0;
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
