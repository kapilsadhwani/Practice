package com.prep.implement.tree;

// Java program to find if there is a duplicate 
// sub-tree of size 2 or more. 
import java.util.HashSet;

public class DuplicateSubTree {
	class TreeNode {
		// Members
		char data;
		public TreeNode left, right, nextRight;

		// Constructor
		public TreeNode(char data) {
			this.data = data;
			this.left = this.right = this.nextRight = null;
		}

	}

	static char MARKER = '$';

	// This function returns empty string if tree
	// contains a duplicate subtree of size 2 or more.
	public static String dupSubUtil(TreeNode root, HashSet<String> subtrees) {
		String s = "";

		// If current node is NULL, return marker
		if (root == null)
			return s + MARKER;

		// If left subtree has a duplicate subtree.
		String lStr = dupSubUtil(root.left, subtrees);
		if (lStr.equals(s))			// if s is "" then it's a duplicate
			return s;

		// Do same for right subtree
		String rStr = dupSubUtil(root.right, subtrees);
		if (rStr.equals(s))
			return s;

		// Serialize current subtree
		s = root.data + lStr + rStr;

		// If current subtree already exists in hash
		// table. [Note that size of a serialized tree
		// with single node is 3 as it has two marker
		// nodes.
		if (s.length() > 3 && subtrees.contains(s)){
			//System.out.println(s);
			return "";
		}

		subtrees.add(s);
		return s;
	}

	// Function to find if the Binary Tree contains duplicate subtrees of size 2 or more
	public static String dupSub(TreeNode root) {
		HashSet<String> subtrees = new HashSet<>();
		return dupSubUtil(root, subtrees);
	}

	public static void main(String args[]) {
		DuplicateSubTree dst = new DuplicateSubTree();
		
		TreeNode root = dst.new TreeNode('A');
		root.left = dst.new TreeNode('B');
		root.right = dst.new TreeNode('C');
		root.left.left = dst.new TreeNode('D');
		root.left.right = dst.new TreeNode('E');
		root.right.right = dst.new TreeNode('B');
		root.right.right.right = dst.new TreeNode('E');
		root.right.right.left = dst.new TreeNode('D');
		String str = dupSub(root);
		if (str.equals(""))
			System.out.print(" Yes ");
		else
			System.out.print(" No ");
	}
}
