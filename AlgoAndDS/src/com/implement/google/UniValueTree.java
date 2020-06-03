package com.implement.google;

public class UniValueTree {
	public class TreeNode {
		int data;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			data = x;
		}
	}

	int count = 0;

	boolean is_uni(TreeNode node) {
		// base case - if the node has no children this is a univalue subtree
		if (node.left == null && node.right == null) {

			// found a univalue subtree - increment
			count++;
			return true;
		}

		boolean is_unival = true;

		// check if all of the node's children are univalue subtrees and if they
		// have the same value
		// also recursively call is_uni for children
		if (node.left != null) {
			is_unival = is_uni(node.left) && node.left.data == node.data;
		}

		if (node.right != null) {
			is_unival = is_uni(node.right) && is_unival && node.right.data == node.data;
		}

		// return if a univalue tree exists here and increment if it does
		if (!is_unival)
			return false;
		count++;
		return true;
	}

	public int countUnivalSubtrees(TreeNode root) {
		if (root == null)
			return 0;
		is_uni(root);
		return count;
	}
	
	public static void main(String[] args) {
		UniValueTree bt = new UniValueTree();
    	
    	/*
		 * Constructed binary tree is
		 * 				 5
		 * 			  /	    \
		 * 		   1	      5
		 * 		 /	 \	     /
		 * 	    5	  5	   5	  
		 * 		 		\
		 * 		   		 5
		 */
		TreeNode root = bt.new TreeNode(5); 
        root.left = bt.new TreeNode(1); 
        root.right = bt.new TreeNode(5); 
        root.left.left = bt.new TreeNode(5); 
        root.left.right = bt.new TreeNode(5); 
        root.left.right.right = bt.new TreeNode(5);
        root.right.left = bt.new TreeNode(5); 
        
        bt.is_uni(root);
        
        System.out.println(bt.count);
	}
}