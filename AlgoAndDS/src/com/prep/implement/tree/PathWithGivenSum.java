package com.prep.implement.tree;

import java.util.ArrayList;
import java.util.List;

public class PathWithGivenSum {
	private TreeNode insert(TreeNode root, int value) {
		if (root == null) {
			TreeNode node = new TreeNode(value);
			root = node;
		} else if (root.data > value) {
			root.left = insert(root.left, value);
		} else if (root.data < value) {
			root.right = insert(root.right, value);
		}

		return root;
	}
	
	private boolean hasPathSum(TreeNode root, int sum) {
		if (root == null)
			return false;

		if (root.left == null && root.right == null)
			return sum == root.data;

		/* otherwise check both subtrees */
		int subsum = sum - root.data;
		
		boolean hasPath = false;
		
		if(root.left != null)
			hasPath = hasPath || hasPathSum(root.left, subsum);
		
		if(root.right != null)
			hasPath = hasPath || hasPathSum(root.right, subsum);
		
		return hasPath;
	}
	
	private void pathSum(TreeNode root, int sum, List<Integer> path, List<List<Integer>> result) {
		if (root == null) {
			return;
		}
		
		path.add(root.data);

		if (root.left == null && root.right == null
				&& root.data == sum) {
			result.add(new ArrayList<Integer>(path));
		}
		
		int subsum = sum - root.data;

		if (root.left != null) {
			pathSum(root.left, subsum, path, result);
		}

		if (root.right != null) {
			pathSum(root.right, subsum, path, result);
		}
		
		path.remove(path.size() - 1);
	}
	
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> path = new ArrayList<Integer>();
		pathSum(root, sum, path, result);
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PathWithGivenSum bt = new PathWithGivenSum();
		TreeNode root = null;
		
		/*
		 * Constructed binary tree is
		 * 				  25
		 * 				/	 \
		 * 			  15	  35
		 * 			/	\	 /	 \
		 * 		  10	20	30	  40
		 * 					/
		 * 				  26
		 */
		root = bt.insert(root, 25);
		root = bt.insert(root, 15);
		root = bt.insert(root, 35);
		root = bt.insert(root, 10);
		root = bt.insert(root, 20);
		root = bt.insert(root, 30);
		root = bt.insert(root, 40);
		root = bt.insert(root, 26);
		
		int sum = 50;
		if (bt.hasPathSum(root, sum))
			System.out.println("There is a root to leaf path with sum " + sum);
		else
			System.out.println("There is no root to leaf path with sum " + sum);
	}

}
