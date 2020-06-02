package com.prep.implement.google;

import java.util.ArrayList;
import java.util.HashSet;

import javafx.util.Pair;

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
    HashSet<Pair<Integer, Integer>> set;
    
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

		TreeNode left = LCA(root.left, n1, n2);
		TreeNode right = LCA(root.right, n1, n2);

		if (left != null && right != null)
			return root;
		
		return left != null ? left : right;
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
		this.set = new HashSet<Pair<Integer, Integer>>();

		leaves(root); // getting leaves of tree

		for (int i = 0; i < leaves.size(); i++) { // for every leaf pair
			TreeNode one = leaves.get(i);

			for (int j = i + 1; j < leaves.size(); j++) {
				TreeNode two = leaves.get(j);
				Pair<Integer, Integer> p1 = new Pair<Integer, Integer>(
						one.data, two.data); // pair of actual values of nodes
												// can be (n1, n2) or (n2, n1)
												// already stored in hashset
				Pair<Integer, Integer> p2 = new Pair<Integer, Integer>(
						two.data, one.data);
				if (set.contains(p1) || set.contains(p2)) // so check both
					continue;

				int d = findDistance(root, one, two); // find shortest distance
														// using LCA
				if (d <= K) { // if distance less than k, add to hashset and
								// increment answer
					// System.out.println(one.data+" "+two.data);
					set.add(p1);
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
        
        int K = 4;
        
        System.out.println(bt.countPairs(root, K));
        System.out.println(bt.set);
	}
} 