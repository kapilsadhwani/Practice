package com.prep.implement.matrix;

import java.util.LinkedList;
import java.util.List;

public class WordSearch {
	/*
	 * Time Complexity: O(Nx4^L ) where N is the
	 * number of cells in the board and LL is the length of the word to be
	 * matched.
	 * 
	 * For the backtracking function, its execution trace would be visualized as
	 * a 4-ary tree, each of the branches represent a potential exploration in
	 * the corresponding direction. Therefore, in the worst case, the total
	 * number of invocation would be the number of nodes in a full 4-nary tree,
	 * which is about 4^L.
	 * 
	 * We iterate through the board for backtracking, i.e. there could be NN
	 * times invocation for the backtracking function in the worst case.
	 * 
	 * As a result, overall the time complexity of the algorithm would be O(Nx4 ^ L ).
	 * 
	 * Space Complexity: O(L) where L is the length of the word to be matched.
	 * 
	 * The main consumption of the memory lies in the recursion call of the
	 * backtracking function. The maximum length of the call stack would be the
	 * length of the word. Therefore, the space complexity of the algorithm is O(L).
	 */
	
	private char[][] board;
	private int ROWS;
	private int COLS;
	private int[] rowOffsets = { 0, 1, 0, -1 };
	private int[] colOffsets = { 1, 0, -1, 0 };
	

	public boolean exist(char[][] board, String word) {
		this.board = board;
		this.ROWS = board.length;
		this.COLS = board[0].length;

		for (int row = 0; row < this.ROWS; ++row)
			for (int col = 0; col < this.COLS; ++col)
				if (this.backtrack(row, col, word, 0))
					return true;
		return false;
	}

	protected boolean backtrack(int row, int col, String word, int pos) {
		/* Step 1). check the bottom case. */
		if (pos >= word.length())
			return true;
		
		if(this.board[row][col] != word.charAt(pos)) return false;

		/* Step 2). explore the neighbors in DFS */
		boolean ret = false;
		
		// mark the path before the next exploration
		this.board[row][col] = '#';

		for (int d = 0; d < 4; ++d) {
            int newX = row + rowOffsets[d];
			int newY = col + colOffsets[d];
			
			if(newX < 0 || newX >= this.ROWS || newY < 0 || newY >= this.COLS)
				continue;
            
			ret = this.backtrack(newX, newY, word, pos + 1);
            
			if (ret) break;
		}

		/* Step 4). clean up and return the result. */
		this.board[row][col] = word.charAt(pos);
		return ret;
	}
	
	public List<String> findWords(char[][] board, String[] words) {
		List<String> result = new LinkedList<String>();
		
		for(String word: words){
			if(exist(board, word)){
				result.add(word);
			}
		}
		
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] board = { 
				{ 'o', 'a', 'a', 'n' }, 
				{ 'e', 't', 'a', 'e' },
				{ 'i', 'h', 'k', 'r' }, 
				{ 'i', 'f', 'l', 'v' } };
		
		String[] words = {"oath","pea","eat","rain","i"};
		
		WordSearch ws = new WordSearch();
		List<String> result = ws.findWords(board, words);
		
		System.out.println(result);
	}

}
