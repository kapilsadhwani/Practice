package com.implement.graph;

import java.util.*;

// Java program to find minimum steps to reach to 
// specific cell in minimum moves by Knight 
class KnightWalkProblem {

	// Class for storing a cell's data
	static class QNode {
		int x, y;
		int moves;

		public QNode(int x, int y, int moves) {
			this.x = x;
			this.y = y;
			this.moves = moves;
		}

	}

	// Utility method returns true if (x, y) lies inside Board
	// Cells are numbered 1 to N
	static boolean isInside(int newX, int newY, int N) {
		if (newX >= 1 && newX <= N && newY >= 1 && newY <= N)
			return true;
		return false;
	}

	// Method returns minimum step to reach target position
	static int minMovesToReachTarget(int knightPos[], int targetPos[], int N) {
		// x and y direction, where a knight can move
		int X[] = { 1, 1, 2, 2, -1, -1, -2, -2 };
		int Y[] = { 2, -2, 1, -1, 2, -2, 1, -1 };

		boolean visited[][] = new boolean[N + 1][N + 1];

		// queue for storing states of knight in board
		Queue<QNode> queue = new LinkedList<QNode>();

		// push starting position of knight with 0 distance
		queue.add(new QNode(knightPos[0], knightPos[1], 0));

		// visit starting state
		visited[knightPos[0]][knightPos[1]] = true;

		QNode current;

		// loop until we have one element in queue
		while (!queue.isEmpty()) {
			current = queue.poll();

			// if current cell is equal to target cell, return its distance
			if (current.x == targetPos[0] && current.y == targetPos[1])
				return current.moves;

			// loop for all reachable states
			for (int i = 0; i < X.length; i++) {
				int newX = current.x + X[i];
				int newY = current.y + Y[i];

				// If reachable state is not yet visited and
				// inside board, push that state into queue
				if (isInside(newX, newY, N) && !visited[newX][newY]) {
					visited[newX][newY] = true;
					queue.offer(new QNode(newX, newY, current.moves + 1));
				}
			}
		}
		return Integer.MAX_VALUE;
	}

	// To calculate possible moves
	static int countPossibleMoves(int[][] mat, int p, int q) {
		// All possible moves of a knight
		int X[] = { 1, 1, 2, 2, -1, -1, -2, -2 };
		int Y[] = { 2, -2, 1, -1, 2, -2, 1, -1 };

		int count = 0;

		// Check if each possible move is valid or not
		for (int i = 0; i < X.length; i++) {

			// Position of knight after move
			int x = p + X[i];
			int y = q + Y[i];

			// count valid moves
			if (x >= 0 && y >= 0 && x < mat.length && y < mat[0].length && mat[x][y] == 0)
				count++;
		}

		// Return number of possible moves
		return count;
	}
	
	public static void displayBoard(int[][] chess){
        for(int i = 0; i < chess.length; i++){
            for(int j = 0; j < chess[0].length; j++){
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }
	
	// To calculate possible moves
	static void printKnightTour(int[][] chess, int r, int c, int move) {
		if(move == chess.length * chess.length){
			chess[r][c] = move;
			displayBoard(chess);
			chess[r][c] = 0;
			return;
		}
		
		// All possible moves of a knight
		int X[] = { 1, 1, 2, 2, -1, -1, -2, -2 };
		int Y[] = { 2, -2, 1, -1, 2, -2, 1, -1 };

		chess[r][c] = move;

		// Check if each possible move is valid or not
		for (int i = 0; i < X.length; i++) {
			// Position of knight after move
			int x = r + X[i];
			int y = c + Y[i];

			// count valid moves
			if (x >= 0 && y >= 0 && x < chess.length && y < chess[0].length
					&& chess[x][y] == 0)
				printKnightTour(chess, x, y, move + 1);
		}

		chess[r][c] = 0;
	}

	// Driver code
	public static void main(String[] args) {
		int N = 30;
		int knightPos[] = { 1, 1 };
		int targetPos[] = { 30, 30 };
		System.out.println(minMovesToReachTarget(knightPos, targetPos, N));
		
		int mat[][] = { { 1, 0, 1, 0 }, 
						{ 0, 1, 1, 1 }, 
						{ 1, 1, 0, 1 }, 
						{ 0, 1, 1, 1 } }; 
	
		int p = 2, q = 2;
		System.out.println(countPossibleMoves(mat, p, q));
		
		int n = 5;
		p = 2;
		q = 0;
		int[][] chess = new int[n][n];
		printKnightTour(chess, p, q, 1);
	}
}
