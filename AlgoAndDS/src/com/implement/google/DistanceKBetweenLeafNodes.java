package com.implement.google;

import java.util.ArrayList;

class DistanceKBetweenLeafNodes {
	public class TreeNode {
		int data;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			data = x;
		}
	}
	
    ArrayList<TreeNode> leaves;
    
	private void leaves(TreeNode node) {
		if (node == null)
			return;

		if (node.left == null && node.right == null)
			leaves.add(node);

		leaves(node.left);
		leaves(node.right);
	}

	public TreeNode LCA(TreeNode root, int n1, int n2) {
		if (root == null)
			return root;
		if (root.data == n1 || root.data == n2)
			return root;

		TreeNode leftLCA = LCA(root.left, n1, n2);
		TreeNode rightLCA = LCA(root.right, n1, n2);

		if (leftLCA != null && rightLCA != null)
			return root;
		
		return leftLCA != null ? leftLCA : rightLCA;
	}

	public int findLevel(TreeNode root, int data, int level) {
		if (root == null)
			return -1;
		if (root.data == data)
			return level;
		int left = findLevel(root.left, data, level + 1);
		if (left == -1)
			return findLevel(root.right, data, level + 1);
		return left;
	}

	public int findDistance(TreeNode root, TreeNode a, TreeNode b) {
		TreeNode lca = LCA(root, a.data, b.data);

		int d1 = findLevel(lca, a.data, 0); // Distance of node 1 from LCA
		int d2 = findLevel(lca, b.data, 0); // Distance of node 2 from LCA

		return d1 + d2;
	}

	public int countPairs(TreeNode root, int K) {
		int count = 0;

		this.leaves = new ArrayList<TreeNode>();

		// Getting leaves of tree
		leaves(root); 

		for (int i = 0; i < leaves.size() - 1; i++) { // for every leaf pair
			TreeNode one = leaves.get(i);

			for (int j = i + 1; j < leaves.size(); j++) {
				TreeNode two = leaves.get(j);
				
				// find shortest distance using LCA
				int d = findDistance(root, one, two); 
								
				// if distance less than or equals k, increment answer
				if (d <= K) { 
					count++;
				}
			}
		}

		return count;
	}
	
	public static void main(String[] args) {
    	DistanceKBetweenLeafNodes bt = new DistanceKBetweenLeafNodes();
    	
    	/*
		 * Constructed binary tree is
		 * 				 3
		 * 			  /	    \
		 * 		   5	      1
		 * 		 /	 \	     /  \
		 * 	    6	  2	   0	  8
		 * 		 	/	\
		 * 		   7	 4
		 * 		  /		  \	
		 * 	    9		  10
		 */
		TreeNode root = bt.new TreeNode(3); 
        root.left = bt.new TreeNode(5); 
        root.right = bt.new TreeNode(1); 
        root.left.left = bt.new TreeNode(6); 
        root.left.right = bt.new TreeNode(2); 
        root.left.right.left = bt.new TreeNode(7); 
        root.left.right.right = bt.new TreeNode(4);
        root.left.right.left.left = bt.new TreeNode(9); 
        root.left.right.right.right = bt.new TreeNode(10);
        root.right.left = bt.new TreeNode(0); 
        root.right.right = bt.new TreeNode(8); 
        
        int k = 4;
        
        System.out.println(bt.countPairs(root, k));
	}
} 