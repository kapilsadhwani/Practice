package com.implement.graph;

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
}