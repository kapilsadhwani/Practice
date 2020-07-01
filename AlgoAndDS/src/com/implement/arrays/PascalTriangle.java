package com.implement.arrays;

import java.util.ArrayList;
import java.util.List;

class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();

        // First base case; if user requests zero rows, they get zero rows.
        if (numRows == 0) {
            return triangle;
        }

        // Second base case; first row is always [1].
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);

        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = triangle.get(rowNum-1);

            // The first row element is always 1.
            row.add(1);

            // Each triangle element (other than the first and last of each row)
            // is equal to the sum of the elements above-and-to-the-left and
            // above-and-to-the-right.
            for (int j = 1; j < rowNum; j++) {
                row.add(prevRow.get(j-1) + prevRow.get(j));
            }

            // The last row element is always 1.
            row.add(1);

            triangle.add(row);
        }

        return triangle;
    }
    
    public static void copyListToArray(List<Integer> fromList, int[] toArray){
    	int i=0;
        for(int n: fromList){
        	toArray[i++] = n; 
        }
        fromList.clear();
    	
    }
    
    
    // Note that the row index starts from 0.
	public static List<Integer> getRow(int rowIndex) {
		List<Integer> row = new ArrayList<Integer>();
		int[] cache = new int[rowIndex];

        // First base case; if user requests zero rows, they get zero rows.
        if (rowIndex < 0) {
            return row;
        }

        // Second base case; first row is always [1].
        row.add(1);
        if (rowIndex == 0) {
            return row;
        }
        
        copyListToArray(row, cache);

        for (int rowNum = 1; rowNum <= rowIndex; rowNum++) {
            // The first row element is always 1.
            row.add(1);

            // Each triangle element (other than the first and last of each row)
            // is equal to the sum of the elements above-and-to-the-left and
            // above-and-to-the-right.
            for (int j = 1; j < rowNum; j++) {
                row.add(cache[j-1] + cache[j]);
            }

            // The last row element is always 1.
            row.add(1);

            if(rowNum < rowIndex)
            	copyListToArray(row, cache);
        }

        return row;
	}
	
	public static void main(String[] args) {
		System.out.println(getRow(3));
	}
}