package com.prep.implement.google;

import java.util.ArrayList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class BSTIteratorRecursiveInOrder {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

    ArrayList<Integer> nodesSorted;
    int index;

    /*
     * Time complexity : O(N) is the time taken by the constructor for the iterator. 
     * In addition to the space occupied by the new array we initialized, the recursion stack for the inorder 
     * traversal also occupies space but that is limited to O(h) where h is the height of the tree.
     * next() would take O(1)
     * hasNext() would take O(1)
     * 
     * Space complexity : O(N) since we create a new array to contain all the nodes of the BST. 
     * This doesn't comply with the requirement specified in the problem statement that the maximum space 
     * complexity of either of the functions should be O(h) where h is the height of the tree and for a 
     * well balanced BST, the height is usually logN
     */
    public BSTIteratorRecursiveInOrder(TreeNode root) {

        // Array containing all the nodes in the sorted order
        this.nodesSorted = new ArrayList<Integer>();
        
        // Pointer to the next smallest element in the BST
        this.index = -1;
        
        // Call to flatten the input binary search tree
        this._inorder(root);
    }

    private void _inorder(TreeNode root) {

        if (root == null) {
            return;
        }

        this._inorder(root.left);
        this.nodesSorted.add(root.val);
        this._inorder(root.right);
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        return this.nodesSorted.get(++this.index);
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return this.index + 1 < this.nodesSorted.size();
    }
}