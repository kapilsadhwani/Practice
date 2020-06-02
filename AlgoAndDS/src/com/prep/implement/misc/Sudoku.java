package com.prep.implement.misc;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Sudoku {
	// Checks whether there is any duplicate in current row or not
	public static boolean isUniqueInRow(int arr[][], int row) {
		// Set to store integers seen so far.
		Set<Integer> set = new HashSet<Integer>();

		for (int i = 0; i < 9; i++) {
			// If already encountered before, return false
			if (set.contains(arr[row][i]))
				return false;

			// If it is not an empty cell, insert value
			// at the current cell in the set
			if (arr[row][i] != 0)
				set.add(arr[row][i]);
		}
		return true;
	}

	// Checks whether there is any duplicate in current column or not.
	public static boolean isUniqueInCol(int arr[][], int col) {
		// Set to store integers seen so far.
		Set<Integer> set = new HashSet<Integer>();

		for (int i = 0; i < 9; i++) {
			// If already encountered before, return false
			if (set.contains(arr[i][col]))
				return false;

			// If it is not an empty cell,
			// insert value at the current cell in the set
			if (arr[i][col] != 0)
				set.add(arr[i][col]);
		}
		return true;
	}

	// Checks whether there is any duplicate in current 3x3 box or not.
	public static boolean isUniqueInBox(int arr[][], int startRow, int startCol) {
		// Set to store integers seen so far.
		Set<Integer> set = new HashSet<Integer>();

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				int key = arr[row + startRow][col + startCol];

				// If already encountered before, return false
				if (set.contains(key))
					return false;

				// If it is not an empty cell,
				// insert value at current cell in set
				if (key != 0)
					set.add(key);
			}
		}
		return true;

	}

	// Checks whether current row and current column and
	// current 3x3 box is valid or not
	static boolean isValidConfig(int arr[][], int n) {		
		for (int row = 0; row < n; row++) {
			if(!isUniqueInRow(arr, row))
				return false;
		}
		
		for (int col = 0; col < n; col++) {
			if(!isUniqueInCol(arr, col))
				return false;
		}
		
		//isUniqueInBox(arr, row - row % 3, col - col % 3);
		
		for (int row = 0; row < n; row = row+3) {
			for (int col = 0; col < n; col = col+3) {
				// If current row or current column or
				// current 3x3 box is not valid, return false
				if (!isUniqueInBox(arr, row, col))
					return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int arr[][] = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				arr[i][j] = s.nextInt();
			}
		}

		s.close();

		boolean flag = isValidConfig(arr, arr.length);

		// if flag is still true then it is valid sudoku
		if (flag)
			System.out.println("1");
		else
			System.out.println("0");
	}
}