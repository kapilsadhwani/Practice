package com.implement.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeBoundary {

	public boolean isLeaf(TreeNode t) {
		return t.left == null && t.right == null;
	}

	public void addLeaves(List<Integer> res, TreeNode root) {
		if (isLeaf(root)) {
			res.add(root.data);
		} else {
			if (root.left != null) {
				addLeaves(res, root.left);
			}
			if (root.right != null) {
				addLeaves(res, root.right);
			}
		}
	}

	public List<Integer> boundaryOfBinaryTree(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		
		// Check if root is a leaf
		if (!isLeaf(root)) {
			res.add(root.data);
		}
		
		// Traverse left subtree of the root
		TreeNode t = root.left;
		while (t != null) {
			if (!isLeaf(t)) {
				res.add(t.data);
			}
			if (t.left != null) {
				t = t.left;
			} else {
				t = t.right;
			}

		}
		addLeaves(res, root);
		
		
		Stack<Integer> s = new Stack<>();
		
		// Traverse right subtree of the root
		t = root.right;
		while (t != null) {
			if (!isLeaf(t)) {
				s.push(t.data);
			}
			if (t.right != null) {
				t = t.right;
			} else {
				t = t.left;
			}
		}
		while (!s.empty()) {
			res.add(s.pop());
		}
		return res;
	}
}