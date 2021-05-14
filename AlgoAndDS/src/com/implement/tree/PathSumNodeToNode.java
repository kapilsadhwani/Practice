package com.implement.tree;

class PathSumNodeToNode {
	int count = 0;

	public int pathSum(TreeNode root, int sum) {
		if (root == null) {
			return count;
		}

		helper(root, sum);

		if (root.left != null) {
			pathSum(root.left, sum);
		}

		if (root.right != null) {
			pathSum(root.right, sum);
		}

		return count;
	}

	private void helper(TreeNode root, int sum) {
		if (root == null) {
			return;
		}
		if (root.data == sum) {
			count++;
		}
		if (root.left != null) {
			helper(root.left, sum - root.data);
		}
		if (root.right != null) {
			helper(root.right, sum - root.data);
		}
	}

	public static void main(String[] args) {
		PathSumNodeToNode bt = new PathSumNodeToNode();

		/*
		 * Constructed binary tree is 
		 * 							10 
		 * 							/ \ 
		 * 						   5   -3 
		 * 						  / \    \ 
		 * 						 3   2   11 
		 *                      / \   \ 
		 *                     3  -2   1
		 */

		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(5);
		root.right = new TreeNode(-3);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(2);
		root.left.left.left = new TreeNode(3);
		root.left.left.right = new TreeNode(-2);
		root.left.right.right = new TreeNode(1);
		root.right.right = new TreeNode(11);

		System.out.println("");
		int sum = 8;
		System.out.println("Number of path with given Sum: "
				+ bt.pathSum(root, sum));
		System.out.println("");
	}
}