package com.prep.implement.tree;

import java.util.HashMap;

class ConstructBinaryTree {
	// start from first preorder element
	int preIdx = 0;
	int[] preorder;
	int[] inorder;
	HashMap<Integer, Integer> inIdxMap = new HashMap<Integer, Integer>();

	/*
	 * Time complexity : O(n)
	 * Space complexity : O(n)
	 */
	public TreeNode buildTreeHelper(int inLeft, int inRight) {
		// if there is no elements to construct subtrees
		if (inLeft > inRight)
			return null;
		
		if (inLeft == inRight){
			int rootVal = preorder[preIdx];
			preIdx++;
			TreeNode root = new TreeNode(rootVal);
			return root;
            /*
             * preIdx++;
             * return new TreeNode(inorder[inLeft]);
             */
		}

		// pick up preStart element as a root
		int rootVal = preorder[preIdx];
		TreeNode root = new TreeNode(rootVal);

		// root splits inorder list into left and right subtrees
		int inOrderIndex = inIdxMap.get(rootVal);

		// recursion
		preIdx++;
		
		// build left subtree
		root.left = buildTreeHelper(inLeft, inOrderIndex - 1);
		
		// build right subtree
		root.right = buildTreeHelper(inOrderIndex + 1, inRight);
		
		return root;
	}

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		this.preorder = preorder;
		this.inorder = inorder;

		// HashMap of inorder values: value -> its index
		for (int i = 0; i < inorder.length; i++){
			inIdxMap.put(inorder[i], i);
        }
		return buildTreeHelper(0, inorder.length - 1);
	}
	
	private void inOrder(TreeNode root) {
		if (root == null)
			return;

		inOrder(root.left);
		System.out.print(root.data + " ");
		inOrder(root.right);
	}
	
	private void preOrder(TreeNode root) {
		if (root == null)
			return;

		System.out.print(root.data + " ");
		preOrder(root.left);
		preOrder(root.right);
	}
	
	public static void main(String[] args) {
		int[] preorder = {3,9,20,15,7};
		int[] inorder = {9,3,15,20,7};
		
		ConstructBinaryTree cbt = new ConstructBinaryTree();
		
		TreeNode root = cbt.buildTree(preorder, inorder);
		
		System.out.print("\nPreOrder : ");
		cbt.preOrder(root);
		
		System.out.print("\nInOrder : ");
		cbt.inOrder(root);
	}
}