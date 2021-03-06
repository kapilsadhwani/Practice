package com.implement.matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordSearchTrie {
	class TrieNode {
		HashMap<Character, TrieNode> children;
		String word = null;

		public TrieNode() {
			children = new HashMap<>();
		}
	}

	char[][] _board = null;
	ArrayList<String> _result = new ArrayList<String>();

	public List<String> findWords(char[][] board, String[] words) {

		// Step 1). Construct the Trie
		TrieNode root = new TrieNode();
		for (String word : words) {
			TrieNode node = root;

			for (int i = 0; i < word.length(); i++) {
				char ch = word.charAt(i);
				if (node.children.containsKey(ch)) {
					node = node.children.get(ch);
				} else {
					TrieNode newNode = new TrieNode();
					node.children.put(ch, newNode);
					node = newNode;
				}
			}
			node.word = word; // store words in Trie
		}

		this._board = board;
		// Step 2). Backtracking starting for each cell in the board
		for (int row = 0; row < board.length; ++row) {
			for (int col = 0; col < board[row].length; ++col) {
				if (root.children.containsKey(board[row][col])) {
					backtracking(row, col, root);
				}
			}
		}

		return this._result;
	}

	private void backtracking(int row, int col, TrieNode parent) {
		Character letter = this._board[row][col];
		TrieNode currNode = parent.children.get(letter);
		
		// Mark as visited
		this._board[row][col] = '#';

		// check if there is any match
		if (currNode.word != null) {
			this._result.add(currNode.word);
			currNode.word = null;	// Prevent duplicates
		}

		// explore neighbor cells in around-clock directions: up, right, down,
		// left
		int[] rowOffset = { -1, 0, 1, 0 };
		int[] colOffset = { 0, 1, 0, -1 };
		for (int i = 0; i < 4; ++i) {
			int newRow = row + rowOffset[i];
			int newCol = col + colOffset[i];
			if (newRow < 0 || newRow >= this._board.length || newCol < 0 
					|| newCol >= this._board[0].length || this._board[newRow][newCol] == '#') {
				continue;
			}
			if (currNode.children.containsKey(this._board[newRow][newCol])) {
				backtracking(newRow, newCol, currNode);
			}
		}

		// End of EXPLORATION, restore the original letter in the board.
		this._board[row][col] = letter;

		// Optimization: incrementally remove the leaf nodes
		/*if (currNode.children.isEmpty()) {
			parent.children.remove(letter);
		}*/
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] board = { 
				{ 'o', 'a', 'a', 'n' }, 
				{ 'e', 't', 'a', 'e' },
				{ 'i', 'h', 'k', 'r' }, 
				{ 'i', 'f', 'l', 'v' } };
		
		String[] words = {"oath","pea","eat","rain", "i"};
		
		WordSearchTrie ws = new WordSearchTrie();
		List<String> result = ws.findWords(board, words);
		
		System.out.println(result);
	}

}
