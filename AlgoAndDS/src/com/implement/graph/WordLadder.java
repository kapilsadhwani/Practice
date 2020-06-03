package com.implement.graph;

// Java program to find length of the shortest chain 
// transformation from source to target 
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class WordLadder {
	static class QItem{
		String word;
		int length;
	   public QItem(String word, int length){
	        this.word = word;
	        this.length = length;
	   }
	}
	
	// To check if strings differ by exactly one character
	static boolean isAdjacent(char[] first, char[] second) {
		int count = 0; // to store count of differences
		/*
		 * Iterate through all characters and returns false if there are more
		 * than one missing characters
		 */
		for (int i = 0; i < first.length; i++) {
			if (first[i] != second[i])
				count++;
			if (count > 1)
				return false;
		}

		return count == 1 ? true : false;
	}

	/*
	 *  Returns length of shortest chain to reach 'target' from 'start'
	 *  using minimum number of adjacent moves.
	 *  D is dictionary
	 */
	static int shortestChainLen(String beginWord, String endWord, List<String> wordList, Set<String> visited) {
		// Create a Queue for BFS and push the starting word into the queue
		Queue<QItem> Q = new LinkedList<QItem>();
		
		Q.offer(new QItem(beginWord,1));	// Chain length for the start word is 1
		visited.add(beginWord);
		
		QItem current;
		// While the queue is non-empty
		while (!Q.isEmpty()) {
			// Take the front word
			current = Q.poll();
			
			// If we reached target
			if (current.word.equals(endWord)) {
				return current.length;
			}
						
			for(String dictWord: wordList){
				if (!visited.contains(dictWord) && 
						isAdjacent(dictWord.toCharArray(), current.word.toCharArray())) {
					// Add the dictionary word to Queue
					Q.offer(new QItem(dictWord, current.length + 1));
					visited.add(dictWord);
				}
			}
		}

		return 0;
	}
	
	public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
		if(!wordList.contains(endWord))
			return 0;
		
		Set<String> visited = new HashSet<String>();
		
		return shortestChainLen(beginWord, endWord, wordList, visited);
	}

	// Driver code
	public static void main(String[] args) {		// Use BFS Algorithm
		String beginWord = "hit";
		String endWord = "cog";
		
		List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
		
		System.out.println("Length of shortest chain from " + beginWord + " to " + endWord + " is: "
				+ ladderLength(beginWord, endWord, wordList));
		
		wordList = Arrays.asList("hot","dot","dog","lot","log");
		
		System.out.println("Length of shortest chain from " + beginWord + " to " + endWord + " is: "
				+ ladderLength(beginWord, endWord, wordList));
	}
}