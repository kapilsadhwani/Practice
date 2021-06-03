package com.implement.pepcoding.dp;

/*
 *  Given a N*N chessboard and the starting position of the knight in the chessboard
 *  The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1)
 *  Find the probability of knight to remain in the chessboard after exactly k number of moves
 */

class KnightsProbability {
	public static boolean isValid(int ni, int nj, int n) {
		if (ni >= 0 && nj >= 0 && ni < n && nj < n) {
			return true;
		}

		return false;
	}

	public static double findProbability(int r, int c, int n, int k) {
		double[][] curr = new double[n][n];
		double[][] next = new double[n][n];

		// x and y direction, where a knight can move
		int X[] = { 1, 1, 2, 2, -1, -1, -2, -2 };
		int Y[] = { 2, -2, 1, -1, 2, -2, 1, -1 };

		// Initial state: Probability of Knight at position r, c is 1 (0th iteration)
		curr[r][c] = 1;	
		
		for (int m = 1; m <= k; m++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (curr[i][j] > 0) {		// or curr[i][j] != 0

						// Loop possible moves
						for (int p = 0; p < X.length; p++) {

							// Position of knight after move
							int x = i + X[p];
							int y = j + Y[p];

							// If a valid move
							if (isValid(x, y, n)) {
								next[x][y] = next[x][y] + curr[i][j] / 8.0;
							}
						}
					}
				}
			}

			curr = next;
			next = new double[n][n];
		}

		double sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sum += curr[i][j];
			}
		}

		return sum;
	}

	// Driver code
	public static void main(String[] args) {
		int n = 3;
		int r = 0;
		int c = 0;
		int k = 2;

		System.out.println(findProbability(r, c, n, k));
	}
}