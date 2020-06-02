package com.prep.implement.graph;

// Java program to count islands in boolean 2D matrix 
import java.util.Set;
import java.util.TreeSet;

class WordBoggle {
	// Let the given dictionary be following
	static final String dictionary[] = { "GEEKS", "FOR", "QUIZ", "GUQ", "EE", "SEEK" };
	//static final String dictionary[] = { "GEEKS", "FOR", "QUIZ", "GO"};
	static final int n = dictionary.length;
	static final int M = 3, N = 3;
	
	static int[] rowOffsets    = {0, 0, -1, -1, -1, 1, 1, 1};
	static int[] columnOffsets = {1, -1, 0, -1, 1, 0, -1, 1};
	
	static Set<String> result = new TreeSet<String>();

	// A given function to check if a given string is present in
	// dictionary. The implementation is naive for simplicity. As
	// per the question dictionary is given to us.
	static boolean isWord(String str) {
		// Linearly search all words
		for (int i = 0; i < n; i++)
			if (str.equals(dictionary[i]))
				return true;
		return false;
	}

	// A recursive function to print all words present on boggle
	static void findWordsUtil(char grid[][], boolean visited[][], int row, int col, StringBuilder sb) {
		// Mark current cell as visited and append current character to str
		visited[row][col] = true;
		sb.append(grid[row][col]);

		// If str is present in dictionary, then print it
		if (isWord(sb.toString()))
			result.add(sb.toString());
			//System.out.println(str);

		// Traverse 8 adjacent cells of boggle[i][j]
		for (int d = 0; d < rowOffsets.length; d++){
			int newX = row + rowOffsets[d];
			int newY = col + columnOffsets[d];
			
			if(newX < 0 || newX >= grid.length || newY < 0 || newY >= grid[0].length)
				continue;
			
			if(!visited[newX][newY])
				findWordsUtil(grid, visited, newX, newY, sb);
		}

		// Erase current character from string and mark visited of current cell as false
		visited[row][col] = false;
		sb.deleteCharAt(sb.length() - 1);
	}

	// Prints all words present in dictionary.
	static void findWords(char grid[][]) {
		// Mark all characters as not visited
		boolean visited[][] = new boolean[M][N];

		// Initialize current string
		StringBuilder sb = new StringBuilder();

		// Consider every character and look for all words
		// starting with this character
		for (int r = 0; r < M; r++)
			for (int c = 0; c < N; c++){
				findWordsUtil(grid, visited, r, c, sb);
		}
		
		System.out.println(result);
	}

	// Driver method
	public static void main(String[] args) {
		char grid[][] = { 
				{ 'G', 'I', 'Z' }, 
				{ 'U', 'E', 'K' },
				{ 'Q', 'S', 'E' } };

		System.out.println("Following words of dictionary are present");
		findWords(grid);
	}
}