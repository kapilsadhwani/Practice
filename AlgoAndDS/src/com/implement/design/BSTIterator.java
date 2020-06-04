package com.implement.design;

import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

/*
 * hasNext is the easier of the lot since all we do in this is to return true if there are any elements 
 * left in the stack. Otherwise, we return false. So clearly, this is an O(1) operation every time.
 * 
 *  next involves two major operations. One is where we pop an element from the stack which becomes the next 
 *  smallest element to return. This is a O(1) operation. However, we then make a call to our helper 
 *  function _inorder_left which iterates over a bunch of nodes. This is clearly a linear time operation 
 *  i.e. O(N) in the worst case. 
 *  
 *  Space complexity: The space complexity is O(h) which is occupied by our custom stack for simulating the 
 *  inorder traversal. Again, we satisfy the space requirements as well as specified in the problem statement.
 */
class BSTIterator {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        
        // Stack for the recursion simulation
        this.stack = new Stack<TreeNode>();
        
        // Remember that the algorithm starts with a call to the helper function
        // with the root node as the input
        this._leftmostInorder(root);
    }

    private void _leftmostInorder(TreeNode root) {
      
        // For a given node, add all the elements in the leftmost branch of the tree
        // under it to the stack.
        while (root != null) {
            this.stack.push(root);
            root = root.left;
        }
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        // Node at the top of the stack is the next smallest element
        TreeNode topmostNode = this.stack.pop();

        // Need to maintain the invariant. If the node has a right child, call the 
        // helper function for the right child
        if (topmostNode.right != null) {
            this._leftmostInorder(topmostNode.right);
        }

        return topmostNode.val;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return this.stack.size() > 0;
    }
}