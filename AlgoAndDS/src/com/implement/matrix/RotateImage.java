package com.implement.matrix;

// JAVA Code for left Rotation of a 
// matrix by 90 degree without using 
// any extra space 

class RotateImage {

	// After transpose we swap elements of column one by one for finding left
	// rotation of matrix by 90 degree
	static void reverseRows(int matrix[][]) {
		for (int c = 0; c < matrix[0].length; c++)
			for (int r = 0, k = matrix.length - 1; r < k; r++, k--) {
				int temp = matrix[r][c];
				matrix[r][c] = matrix[k][c];
				matrix[k][c] = temp;
			}
	}

	// After transpose we swap elements of row one by one for finding right
	// rotation of matrix by 90 degree
	static void reverseColumns(int matrix[][]) {
		for (int r = 0; r < matrix.length; r++)
			for (int c = 0, k = matrix[0].length - 1; c < k; c++, k--) {
				int temp = matrix[r][c];
				matrix[r][c] = matrix[r][k];
				matrix[r][k] = temp;
			}
	}

	// Function for do transpose of matrix
	static void transpose(int matrix[][]) {
		for (int r = 0; r < matrix.length; r++)
			for (int c = r; c < matrix[0].length; c++) {
				if( r == c) continue;
				
				int temp = matrix[r][c];
				matrix[r][c] = matrix[c][r];		
				matrix[c][r] = temp;
			}
	}

	// Function for print matrix
	static void printMatrix(int matrix[][]) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++)
				System.out.print(matrix[i][j] + " ");
			System.out.println("");
		}
	}

	// Function to anticlockwise rotate matrix by 90 degree
	static void rotate90AntiClockwiseOrLeftRotate(int matrix[][]) {
		transpose(matrix);
		
		/*System.out.println("After Transpose: ");
		printMatrix(matrix);
		System.out.println("");*/
		
		reverseRows(matrix);
	}

	// Function to clockwise rotate matrix by 90 degree
	static void rotate90ClockwiseorRightRotate(int matrix[][]) {
		transpose(matrix);
		reverseColumns(matrix);
	}

	/* Driver program to test above function */
	public static void main(String[] args) {
		int matrix[][] = { { 1, 2, 3, 4 }, 
						{ 5, 6, 7, 8 }, 
						{ 9, 10, 11, 12 },
						{ 13, 14, 15, 16 } };

		rotate90AntiClockwiseOrLeftRotate(matrix);
		printMatrix(matrix);
		
		System.out.println("");
		
		int matrix1[][] = { { 1, 2, 3, 4 }, 
						 { 5, 6, 7, 8 }, 
						 { 9, 10, 11, 12 },
						 { 13, 14, 15, 16 } };
		rotate90ClockwiseorRightRotate(matrix1);
		printMatrix(matrix1);
		
		System.out.println("");
		
		rotate90ClockwiseorRightRotate(matrix);
		printMatrix(matrix);
	}
}
