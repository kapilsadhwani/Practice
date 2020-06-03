package com.implement.matrix;

// java implementation to search 
// an element in a sorted matrix 

class Array2DBinarySearch {
	static int MAX = 100;

	// This function does Binary search for x in i-th
	// row. It does the search from mat[i][j_low] to
	// mat[i][j_high]
	static void binarySearch(int mat[][], int row, int col_low, int col_high, int val) {
		while (col_low <= col_high) {
			int col_mid = (col_low + col_high) / 2;

			// Element found
			if (mat[row][col_mid] == val) {
				System.out.println("Found at (" + row + ", " + col_mid + ")");
				return;
			}

			else if (mat[row][col_mid] > val)
				col_high = col_mid - 1;

			else
				col_low = col_mid + 1;
		}

		// element not found
		System.out.println("Element not found");
	}

	// Function to perform binary search on the mid
	// values of row to get the desired pair of rows
	// where the element can be found
	static void sortedMatrixSearch(int mat[][], int row, int col, int val) {
		// Single row matrix
		if (row == 1) {
			binarySearch(mat, 0, 0, col - 1, val);
			return;
		}

		/*
		 *  Do binary search in middle column.
		 *  Condition to terminate the loop when the
		 *  2 desired rows are found
		 */
		int r_low = 0;
		int r_high = row-1;
		int c_mid = col / 2;
		while (r_low < r_high-1) {
			int r_mid = (r_low + r_high) / 2;

			// element found
			if (mat[r_mid][c_mid] == val) {
				System.out.println("Found at (" + r_mid + ", " + c_mid + ")");
				return;
			}

			else if (mat[r_mid][c_mid] > val)
				r_high = r_mid;
			else
				r_low = r_mid;
		}

		// If element is present on the mid of the two rows
		if (mat[r_low][c_mid] == val)
			System.out.println("Found at (" + r_low + "," + c_mid + ")");
		else if (mat[r_low + 1][c_mid] == val)
			System.out.println("Found at (" + (r_low + 1) + ", " + c_mid + ")");

		// Search element on 1st half of 1st row
		else if (val <= mat[r_low][c_mid - 1])
			binarySearch(mat, r_low, 0, c_mid - 1, val);

		// Search element on 2nd half of 1st row
		else if (val >= mat[r_low][c_mid + 1] && val <= mat[r_low][col - 1])
			binarySearch(mat, r_low, c_mid + 1, col - 1, val);

		// Search element on 1st half of 2nd row
		else if (val <= mat[r_low + 1][c_mid - 1])
			binarySearch(mat, r_low + 1, 0, c_mid - 1, val);

		// search element on 2nd half of 2nd row
		else
			binarySearch(mat, r_low + 1, c_mid + 1, col - 1, val);
	}
	
	public static boolean searchMatrixI(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return false;
		int rows = matrix.length, cols = matrix[0].length;

		/*
		 * binary search #1: looking for the right row to search for the value
		 * Use last column for this search
		 */
		int low = 0, high = rows;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (matrix[mid][cols - 1] < target)
				low = mid + 1;
			else
				high = mid;
		}

		if (low == rows)
			return false;
		
		if (matrix[low][cols - 1] == target) {
			//System.out.println("Found at (" + low + "," + (cols - 1) + ")");
			return true;
		}

		int r = low;

		// binary search #2: looking for the target in that row
		low = 0;
		high = cols;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (matrix[r][mid] < target)
				low = mid + 1;
			else
				high = mid;
		}
		
		if (low == cols)
			return false;

		if (matrix[r][low] == target) {
			//System.out.println("Found at (" + r + "," + low + ")");
			return true;
		}

		//System.out.println("Element not found");
		return false;
	}
	
	public static boolean searchMatrixII(int[][] matrix, int target) {
		if(matrix == null || matrix.length == 0) return false;
		
        // start our "pointer" in the top-right
        int row = 0;
        int col = matrix[0].length-1;

        while (col >= 0 && row < matrix.length) {
        	if(matrix[row][col] == target)
        		return true;
        	
            if (matrix[row][col] < target) {
                row++;
            } else {
               col--;
            }
        }

        return false;
    }

	// Driver program
	public static void main(String[] args) {
		int row = 4, col = 5, target = 36;
		int matrix[][] = { 
				{  0,  6,   8,   9,  11 }, 
				{ 20, 22,  28,  29,  31 },
				{ 36, 38,  50,  61,  63 }, 
				{ 64, 66, 100, 122, 128 } };

		System.out.println(searchMatrixI(matrix, target));
		sortedMatrixSearch(matrix, row, col, target);
		
		int matrix1[][] = {
				  {1,   4,  7, 11, 15},
				  {2,   5,  8, 12, 19},
				  {3,   6,  9, 16, 22},
				  {10, 13, 14, 17, 24},
				  {18, 21, 23, 26, 30}
				};
		target = 5;
		System.out.println(searchMatrixII(matrix1, target));
		
		target = 20;
		System.out.println(searchMatrixII(matrix1, target));
	}
}
