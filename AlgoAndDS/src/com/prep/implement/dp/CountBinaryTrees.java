package com.prep.implement.dp;


public class CountBinaryTrees {
	static int bottomUpCountTrees(int n){
		int[] trees = new int[n+1];

		trees[0] = 1;
		trees[1] = 1;
		
		for(int nodes=2; nodes<=n; nodes++){
			int count = 0;
			/*
			 * T(5) = T(0)*T(4) + T(1)*T(3) + T(2)*T(2) + T(3)*T(1) + T(4)*T(0) 
			 */
			for(int i=0; i<nodes; i++){
				count = count + trees[i] * trees[nodes-i-1];
			}
			
			trees[nodes] = count;
		}
		
		return trees[n];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Number of trees with 1 node : " + bottomUpCountTrees(1));
		System.out.println("Number of trees with 2 nodes : " + bottomUpCountTrees(2));
		System.out.println("Number of trees with 3 nodes : " + bottomUpCountTrees(3));
		System.out.println("Number of trees with 4 nodes : " + bottomUpCountTrees(4));
		System.out.println("Number of trees with 5 nodes : " + bottomUpCountTrees(5));
		System.out.println("Number of trees with 6 nodes : " + bottomUpCountTrees(6));
	}

}