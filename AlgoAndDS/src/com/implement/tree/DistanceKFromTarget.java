package com.implement.tree;

import java.util.ArrayList;
import java.util.List;

class DistanceKFromTarget {
    static List<Integer> ans;
    static List<TreeNode> path;

	// Path will contain ancestors of data including itself
	static boolean find(TreeNode root, TreeNode node) {
		if (root == null) {
			return false;
		}

		if (root.data == node.data) {
			path.add(root);
			return true;
		}

		boolean found = find(root.left, node);

		if (found == true) {
			path.add(root);
			return true;
		}

		found = find(root.right, node);

		if (found == true) {
			path.add(root);
			return true;
		}

		return false;
	}

	// Get Nodes at distance K from the root node
	static void getKLevelsDown(TreeNode root, int k, TreeNode blocker) {
		if (root == null || root == blocker) {
			return;
		}

		if (k == 0) {
			ans.add(root.data);
		}

		if (k > 0) {
			getKLevelsDown(root.left, k - 1, blocker);
			getKLevelsDown(root.right, k - 1, blocker);
		}
	}

	// Get Nodes at distance K from the target node
	public static void distanceKNodes(TreeNode root, TreeNode target, int k) {
		path = new ArrayList<TreeNode>();
		ans = new ArrayList<Integer>();

		// Populate path with its ancestors
		find(root, target);

		/*
		 *  Get path from itself and all of its eligible ancestors
		 *  Don't forget to add a blocker node
		 */
		for (int i = 0; i < path.size(); i++) {
			getKLevelsDown(path.get(i), k - i, i == 0 ? null : path.get(i - 1));
		}
	}
    
    public static void main(String[] args) {
    	
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
		TreeNode root = new TreeNode(3); 
        root.left = new TreeNode(5); 
        root.right = new TreeNode(1); 
        root.left.left = new TreeNode(6); 
        root.left.right = new TreeNode(2); 
        root.left.right.left = new TreeNode(7); 
        root.left.right.right = new TreeNode(4);
        root.left.right.left.left = new TreeNode(9); 
        root.left.right.right.right = new TreeNode(10);
        root.right.left = new TreeNode(0); 
        root.right.right = new TreeNode(8); 
        
        int k = 2;
        
        // Target is node 2
        distanceKNodes(root, root.left.right, k);
		System.out.println(ans);
	}
} 