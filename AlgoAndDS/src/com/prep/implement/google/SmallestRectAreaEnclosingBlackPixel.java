package com.prep.implement.google;

/*
 * Based on Flood Fill Algo
 */

public class SmallestRectAreaEnclosingBlackPixel {
	private int top, bottom, left, right;
	
	public int minArea(char[][] image, int x, int y) {
        if(image.length == 0 || image[0].length == 0) return 0;
        top = bottom = x;
        left = right = y;
        dfs(image, x, y);
        return (right - left) * (bottom - top);
    }
    private void dfs(char[][] image, int x, int y){
        if(x < 0 || y < 0 || x >= image.length || y >= image[0].length ||
          image[x][y] == '0')
            return;
        image[x][y] = '0'; // mark visited black pixel as white
        top = Math.min(top, x);
        bottom = Math.max(bottom, x + 1);
        left = Math.min(left, y);
        right = Math.max(right, y + 1);
        dfs(image, x + 1, y);
        dfs(image, x - 1, y);
        dfs(image, x, y - 1);
        dfs(image, x, y + 1);
    }
    
    public int minAreaLinearSearch(char[][] image, int x, int y) {
        int topX = x, bottomX = x;
        int leftY = y, rightY = y;
        for (x = 0; x < image.length; ++x) {
            for (y = 0; y < image[0].length; ++y) {
                if (image[x][y] == '1') {
                	topX = Math.min(topX, x);
                	bottomX = Math.max(bottomX, x + 1);
                	leftY = Math.min(leftY, y);
                	rightY = Math.max(rightY, y + 1);
                }
            }
        }
        return (rightY - leftY) * (bottomX - topX);
    }
}