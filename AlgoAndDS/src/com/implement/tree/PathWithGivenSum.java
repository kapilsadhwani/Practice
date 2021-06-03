package com.implement.tree;

import java.util.ArrayList;
import java.util.List;

public class PathWithGivenSum {
	private static TreeNode insert(TreeNode root, int value) {
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
	
	private static boolean hasPathSum(TreeNode root, int sum) {
		if (root == null)
			return sum == 0;

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
	
	private static void pathSum(TreeNode root, int sum, List<Integer> path, List<List<Integer>> result) {
		if (root == null) {
			return;
		}
		
		if (root.left == null && root.right == null
				&& root.data == sum) {
			path.add(root.data);
			result.add(new ArrayList<Integer>(path));
			path.remove(path.size() - 1);
			return;
		}
		
		path.add(root.data);

		int subsum = sum - root.data;

		if (root.left != null) {
			pathSum(root.left, subsum, path, result);
		}

		if (root.right != null) {
			pathSum(root.right, subsum, path, result);
		}
		
		// backtrack
		path.remove(path.size() - 1);
	}
	
	public static List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> path = new ArrayList<Integer>();
		pathSum(root, sum, path, result);
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = null;
		
		/*
		 * Constructed binary tree is
		 * 				  25
		 * 				/	 \
		 * 			  15	  35
		 * 			/	\	 /	 \
		 * 		  10	20	30	  40
		 * 		  /			/
		 * 		 5		  26
		 *      /
		 *     3
		 *    /
		 *   2  
		 */
		root = insert(root, 25);
		root = insert(root, 15);
		root = insert(root, 35);
		root = insert(root, 10);
		root = insert(root, 5);
		root = insert(root, 3);
		root = insert(root, 2);
		root = insert(root, 20);
		root = insert(root, 30);
		root = insert(root, 40);
		root = insert(root, 26);
		
		int sum = 60;
		List<List<Integer>> result = pathSum(root, sum);
		if (hasPathSum(root, sum)){
			System.out.println("There is a root to leaf path with sum " + sum);
			System.out.println(result);
		}else{
			System.out.println("There is no root to leaf path with sum " + sum);
			System.out.println(result);
		}
	}

}
