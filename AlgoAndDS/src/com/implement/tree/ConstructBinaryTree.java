package com.implement.tree;

import java.util.HashMap;

class ConstructBinaryTree {
	// From only preorder
	// start from first preorder element
	static int preIdx;
	static int postIdx;
	
	static int[] preorder;
	static int[] inorder;
	static int[] postorder;
	static HashMap<Integer, Integer> inIdxMap = new HashMap<>();

	/*
	 * Time complexity : O(n)
	 * Space complexity : O(n)
	 */
	public static TreeNode buildTreePreAndInOrderHelper(int inLeft, int inRight) {
		// if there is no elements to construct subtrees
		if (inLeft > inRight)
			return null;
		
		if (inLeft == inRight){
			int rootVal = preorder[preIdx];
			TreeNode root = new TreeNode(rootVal);
			
			preIdx++;
			return root;
            /*
             * preIdx++;
             * return new TreeNode(inorder[inLeft]);
             */
		}

		// pick up preStart element as a root
		int rootVal = preorder[preIdx];
		TreeNode root = new TreeNode(rootVal);

		// recursion
		preIdx++;
				
		// root splits inorder list into left and right subtrees
		int inOrderIndex = inIdxMap.get(rootVal);

		// build left subtree
		root.left = buildTreePreAndInOrderHelper(inLeft, inOrderIndex - 1);
		
		// build right subtree
		root.right = buildTreePreAndInOrderHelper(inOrderIndex + 1, inRight);
		
		return root;
	}

	static public TreeNode buildPreAndInOrder(int[] preArr, int[] inArr) {
		preorder = preArr;
		inorder = inArr;
		preIdx = 0;
		
		// HashMap of inorder values: value -> its index
		for (int i = 0; i < inorder.length; i++){
			inIdxMap.put(inorder[i], i);
        }
		return buildTreePreAndInOrderHelper(0, inorder.length - 1);
	}
	
	/*
	 * Time complexity : O(n)
	 * Space complexity : O(n)
	 */
	public static TreeNode buildTreePostAndInOrderHelper(int inLeft, int inRight) {
		// if there is no elements to construct subtrees
		if (inLeft > inRight)
			return null;
		
		if (inLeft == inRight){
			int rootVal = postorder[postIdx];
			TreeNode root = new TreeNode(rootVal);
			
			postIdx--;
			return root;
		}

		// pick up preStart element as a root
		int rootVal = postorder[postIdx];
		TreeNode root = new TreeNode(rootVal);

		// recursion
		postIdx--;
				
		// root splits inorder list into left and right subtrees
		int inOrderIndex = inIdxMap.get(rootVal);

		// build right subtree
		root.right = buildTreePostAndInOrderHelper(inOrderIndex + 1, inRight);
		
		// build left subtree
		root.left = buildTreePostAndInOrderHelper(inLeft, inOrderIndex - 1);
		
		return root;
	}
	
	static public TreeNode buildPostAndInOrder(int[] postArr, int[] inArr) {
		postorder = postArr;
		inorder = inArr;
		postIdx = postArr.length - 1;
		
		inIdxMap.clear();
		
		// HashMap of inorder values: value -> its index
		for (int i = 0; i < inorder.length; i++){
			inIdxMap.put(inorder[i], i);
        }
		return buildTreePostAndInOrderHelper(0, inorder.length - 1);
	}
	
	static private void inOrder(TreeNode root) {
		if (root == null)
			return;

		inOrder(root.left);
		System.out.print(root.data + " ");
		inOrder(root.right);
	}
	
	static private void preOrder(TreeNode root) {
		if (root == null)
			return;

		System.out.print(root.data + " ");
		preOrder(root.left);
		preOrder(root.right);
	}
	
	static private void postOrder(TreeNode root) {
		if (root == null)
			return;

		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.data + " ");
	}
	
	private TreeNode bstFromSortedArray(int[] nums, int left, int right) {
		// TODO Auto-generated method stub
		if (left > right)
			return null;

		int mid = left + (right - left) / 2;

		TreeNode root = new TreeNode(nums[mid]);
		root.left = bstFromSortedArray(nums, left, mid - 1);
		root.right = bstFromSortedArray(nums, mid + 1, right);

		return root;
	}

	public TreeNode bstFromSortedArray(int[] nums) {
		return bstFromSortedArray(nums, 0, nums.length - 1);
	}

	// Recursive function to build a BST from a preorder sequence
	public static TreeNode bstFromPreorder(int[] preorder, int min, int max) {
		if (preIdx == preorder.length) {
			return null;
		}

		// Return if next element of preorder traversal is not in the valid range
		int val = preorder[preIdx];
		if (val < min || val > max) {
			return null;
		}

		// Construct the root node and increment pIndex
		TreeNode root = new TreeNode(val);
		preIdx++;

		// Since all elements in the left sub-tree of a BST must be less
		// than the value of root node, set range as [min, val-1] and recur
		root.left = bstFromPreorder(preorder, min, val);

		// Since all elements in the right sub-tree of a BST must be greater
		// than the value of root node, set range as [val+1..max] and recur
		root.right = bstFromPreorder(preorder, val, max);

		return root;
	}

	// Build a BST from a preorder sequence
	public static TreeNode bstFromPreorder(int[] preorder) {
		// start from the root node (first element in preorder sequence)
		// use AtomicInteger as Integer is passed by value in Java
		preIdx = 0;

		// set range of the root node as [Integer.MIN_VALUE, Integer.MAX_VALUE] and recurse
		return bstFromPreorder(preorder, Integer.MIN_VALUE,	Integer.MAX_VALUE);
	}
	
	
	// Recursive function to build a BST from a preorder sequence
		public static TreeNode bstFromPostorder(int[] postorder, int min, int max) {
			if (postIdx < 0) {
				return null;
			}

			// Return if next element of preorder traversal is not in the valid range
			int val = postorder[postIdx];
			if (val < min || val > max) {
				return null;
			}

			// Construct the root node and increment pIndex
			TreeNode root = new TreeNode(val);
			postIdx--;
			
			// Since all elements in the right sub-tree of a BST must be greater
			// than the value of root node, set range as [val+1..max] and recur
			root.right = bstFromPreorder(postorder, val, max);

			// Since all elements in the left sub-tree of a BST must be less
			// than the value of root node, set range as [min, val-1] and recur
			root.left = bstFromPreorder(postorder, min, val);

			return root;
		}

		// Build a BST from a preorder sequence
		public static TreeNode bstFromPostorder(int[] postorder) {
			// start from the root node (first element in preorder sequence)
			// use AtomicInteger as Integer is passed by value in Java
			postIdx = postorder.length - 1;

			// set range of the root node as [Integer.MIN_VALUE, Integer.MAX_VALUE] and recurse
			return bstFromPostorder(postorder, Integer.MIN_VALUE,	Integer.MAX_VALUE);
		}
	
	public static void main(String[] args) {
		/*
		 *                        3
		 *                     	/   \
		 *                     9     20
		 *                         /    \
		 *                       15       7 
		 * 
		 */
		
		int[] preorder = {3,9,20,15,7};
		int[] inorder = {9,3,15,20,7};
		int[] postorder = {9,15,7,20,3};		
		
		TreeNode root = buildPreAndInOrder(preorder, inorder);
		
		System.out.print("\nPreOrder : ");
		preOrder(root);
		
		System.out.print("\nInOrder : ");
		inOrder(root);
		
		TreeNode root1 = buildPostAndInOrder(postorder, inorder);
		
		System.out.print("\nPostOrder : ");
		postOrder(root1);
		
		System.out.print("\nInOrder : ");
		inOrder(root1);
	}
}