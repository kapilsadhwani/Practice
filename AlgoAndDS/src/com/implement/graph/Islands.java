package com.implement.graph;

// Java program to count islands in boolean 2D matrix 

class Islands {
	private int[] rowOffsets = { 0, 1, 0, -1 };
	private int[] colOffsets = { 1, 0, -1, 0 };

	// A utility function to exhaust a connected component from Vertex
	// grid[row][col]
	private void exhaustCC(char grid[][], int row, int col, boolean[][] visited) {
		// Mark this cell as visited
		visited[row][col] = true;

		for (int i = 0; i < rowOffsets.length; i++) {
			int newX = row + rowOffsets[i];
			int newY = col + colOffsets[i];

			// Boundary check
			if (newX < 0 || newX == grid.length || newY < 0
					|| newY == grid[0].length)
				continue;

			if (grid[newX][newY] == '1' && !visited[newX][newY]) {
				exhaustCC(grid, newX, newY, visited);
			}
		}
	}

	// The main function that returns count of islands in a given 2D matrix
	int numIslands(char[][] grid) {
		if (grid.length == 0 || grid[0].length == 0)
			return 0;

		// Make a bool array to mark visited cells.
		// Initially all cells are unvisited
		boolean visited[][] = new boolean[grid.length][grid[0].length];

		// Initialize count as 0 and travese through the all cells of given
		// matrix
		int count = 0;
		for (int i = 0; i < grid.length; ++i)
			for (int j = 0; j < grid[0].length; ++j)
				if (grid[i][j] == '1' && !visited[i][j]) {
					// If a cell with value 1 is not visited yet, then new
					// island found,
					// Visit all cells in this island and increment island count
					exhaustCC(grid, i, j, visited);
					count += 1;
				}

		return count;
	}

	// Driver method
	public static void main(String[] args) {
		char grid[][] = new char[][] { 
				{ '1', '1', '0', '0', '0' },
				{ '0', '1', '0', '0', '1' }, 
				{ '1', '0', '0', '1', '1' },
				{ '0', '0', '0', '0', '0' }, 
				{ '1', '0', '1', '0', '1' } };
		Islands island = new Islands();
		System.out.println("Number of islands is: " + island.numIslands(grid));
	}
}