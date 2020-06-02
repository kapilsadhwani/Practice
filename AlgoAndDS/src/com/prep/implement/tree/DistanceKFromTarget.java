package com.prep.implement.tree;

import java.util.LinkedList;
import java.util.List;

class DistanceKFromTarget {
    List<Integer> ans;
    TreeNode target;
    int K;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        ans = new LinkedList();
        this.target = target;
        this.K = K;
        dfs(root);
        return ans;
    }

	public int dfs(TreeNode node) {
		if (node == null)
			return -1;

		if (node == target) {
			subtree_add(node, 0);
			return 1;
		}

		int L = dfs(node.left);
		int R = dfs(node.right);

		// If target node is in left subtree of the root
		if (L != -1) {
			if (L == K) { // If target node is at Distance K from root node, add root
				ans.add(node.data);
			} else if (L < K) {
				// parent of left is at a distance of L+1 from left, then find
				// sibling on right at a distance (K - (L + 1)) from the parent
				subtree_add(node.right, L + 1);
			}
			return L + 1;
		}

		// If target node is in right subtree of the root
		else if (R != -1) {
			if (R == K) { // If target node is at Distance K from root node, add root
				ans.add(node.data);
			} else if (R < K) {
				// left sibling is at distance R+1 from right
				subtree_add(node.left, R + 1);
			}
			return R + 1;
		} else {
			return -1;
		}

	}

    // Find nodes at a distance (K - dist) from the root
    public void subtree_add(TreeNode node, int dist) {
        if (node == null) 
        	return;
        
        if (dist == K){
            ans.add(node.data);
            return;
        }

		if (node.left != null)
			subtree_add(node.left, dist + 1);

		if (node.right != null)
			subtree_add(node.right, dist + 1);
    }
    
    public static void main(String[] args) {
    	DistanceKFromTarget bt = new DistanceKFromTarget();
    	
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
        
        int K = 2;
        
        System.out.println(bt.distanceK(root, root.left.right, K));
	}
} 