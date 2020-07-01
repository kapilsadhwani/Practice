package com.implement.graph;

import java.util.Arrays;

public class PaintFill {
	private int[] rowoffsets = {0, 1, 0, -1};
	private int[] coloffsets = {1, 0 ,-1, 0};
	
	enum Color {
		BLACK, WHITE, RED, YELLOW, GREEN
	}

	boolean paintFill(Color[][] screen, int r, int c, Color ncolor) {
		if( r < 0 || r >= screen.length || c < 0 || c >= screen[0].length) 
			return false;
		
		if (screen[r][c] == ncolor)
			return false;
		
		return paintFill(screen, r, c, screen[r][c], ncolor);
	}

	boolean paintFill(Color[][] screen, int r, int c, Color ocolor, Color ncolor) {
		screen[r][c] = ncolor;

		for (int i = 0; i < rowoffsets.length; i++) {
			int newX = r + rowoffsets[i];
			int newY = c + coloffsets[i];

			if (newX < 0 || newX >= screen.length || newY < 0
					|| newY >= screen[0].length)
				continue;

			if (screen[newX][newY] == ocolor) {
				paintFill(screen, newX, newY, ocolor, ncolor);
			}
		}
		return true;
	}
	
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		if( sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length) 
			return image;
		
		if (image[sr][sc] == newColor)
			return image;
		
		floodFill(image, sr, sc, image[sr][sc], newColor);
		
		return image;
	}
	
	private void floodFill(int[][] image, int sr, int sc, int ocolor, int ncolor) {
		image[sr][sc] = ncolor;

		for (int i = 0; i < rowoffsets.length; i++) {
			int newX = sr + rowoffsets[i];
			int newY = sc + coloffsets[i];

			if (newX < 0 || newX >= image.length || newY < 0
					|| newY >= image[0].length)
				continue;

			if (image[newX][newY] == ocolor) {
				floodFill(image, newX, newY, ocolor, ncolor);
			}
		}
	}
	
	public static void main(String[] args) {
		PaintFill pf = new PaintFill();
		int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
		int sr = 1, sc = 1, newColor = 2;
		
		int[][] result = pf.floodFill(image, sr, sc, newColor);

		for (int i = 0; i < result.length; i++)
			System.out.println(Arrays.toString(result[i]));
	}
}