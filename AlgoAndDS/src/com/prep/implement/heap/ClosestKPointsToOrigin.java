package com.prep.implement.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class ClosestKPointsToOrigin {
	class PointComparator implements Comparator<int[]> {
	    @Override
	    public int compare(int[] p0, int[] p1) {
	        return Integer.compare(p1[0]*p1[0] + p1[1]*p1[1], 
	        					   p0[0]*p0[0] + p0[1]*p0[1]);
	    }
	}
	
    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length < K) {
            return points;
        }
        
        PriorityQueue<int[]> maxHeap = new PriorityQueue<int[]>(K+1, new PointComparator());
        
        for (int[] p : points) {
            maxHeap.add(p);
            if (maxHeap.size() > K) {
                maxHeap.poll();
            }
        }
        
        int[][] result = new int[maxHeap.size()][2];
        for (int i = 0; i < result.length; i++) {
            int[] point = maxHeap.poll();
            result[i][0] = point[0];
            result[i][1] = point[1];
        }
        
        return result;
    }
    
    public static void main(String[] args) {
    	ClosestKPointsToOrigin pto = new ClosestKPointsToOrigin();
    	int[][] points = null;
    	
    	points = new int[][]{
    						{1,3},{-2,2}
    					};
    	int K = 1;
    	
    	int result[][] = pto.kClosest(points, K);
    	
    	for(int[] row:result){
    		System.out.print(Arrays.toString(row));
    	}
    	
    	points = new int[][]{
    			{3,3},{5,-1},{-2,4}
			};
		K = 2;
		
		result = pto.kClosest(points, K);
		
		System.out.println();
		for(int[] row:result){
			System.out.print(Arrays.toString(row) + ", ");
		}
	}
}