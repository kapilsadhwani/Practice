package com.implement.pepcoding.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
 * Java program to count islands in boolean 2D matrix
 * Input:
 * 
8
8
0 0 1 1 1 1 1 1
0 0 1 1 1 1 1 1
1 1 1 1 1 1 1 0
1 1 0 0 0 1 1 0
1 1 1 1 0 1 1 0
1 1 1 1 0 1 1 0
1 1 1 1 1 1 1 0
1 1 1 1 1 1 1 0
 */

class Islands {
	private static int[] rowOffsets = { 0, 1, 0, -1 };
	private static int[] colOffsets = { 1, 0, -1, 0 };

	// A utility function to exhaust a connected component from Vertex grid[row][col]
	private static void exhaustCC(int grid[][], int row, int col, boolean[][] visited) {
		// Mark this cell as visited
		visited[row][col] = true;

		for (int i = 0; i < rowOffsets.length; i++) {
			int newX = row + rowOffsets[i];
			int newY = col + colOffsets[i];

			// Boundary check
			if (newX < 0 || newX == grid.length || newY < 0
					|| newY == grid[0].length)
				continue;

			// If cell is a land and not visited before
			if (grid[newX][newY] == 0 && !visited[newX][newY]) {
				exhaustCC(grid, newX, newY, visited);
			}
		}
	}

	// The main function that returns count of islands in a given 2D matrix
	static int countIslands(int[][] grid) {
		if (grid.length == 0 || grid[0].length == 0)
			return 0;

		// Make a bool array to mark visited cells.
		// Initially all cells are unvisited
		boolean visited[][] = new boolean[grid.length][grid[0].length];

		// Initialize count as 0 and travese through the all cells of given matrix
		int count = 0;
		
		// 0 - Land, 1 - Water
		for (int i = 0; i < grid.length; ++i){
			for (int j = 0; j < grid[0].length; ++j){
				if (grid[i][j] == 0 && visited[i][j] == false) {
					// If a cell with value 1 is not visited yet, then new island found,
					// Visit all cells in this island and increment island count
					exhaustCC(grid, i, j, visited);
					count++;
				}
			}
		}

		return count;
	}

	// Driver method
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	      int m = Integer.parseInt(br.readLine());
	      int n = Integer.parseInt(br.readLine());
	      int[][] arr = new int[m][n];

	      for (int i = 0; i < arr.length; i++) {
	         String parts = br.readLine();
	         for (int j = 0; j < arr[0].length; j++) {
	            arr[i][j] = Integer.parseInt(parts.split(" ")[j]);
	         }
	      }

	     // write your code here
		System.out.println("Number of islands is: " + countIslands(arr));
	}
}