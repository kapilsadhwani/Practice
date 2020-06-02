package com.prep.implement.tree;

import java.util.LinkedList;
import java.util.Stack;

public class SerDeBT {
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if (root == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

		queue.add(root);
		TreeNode node;

		while (!queue.isEmpty()) {
			node = queue.poll();

			if (node != null) {
				sb.append(String.valueOf(node.data) + ",");
				queue.add(node.left);
				queue.add(node.right);
			} else {
				sb.append("#,");
			}
		}

		sb.deleteCharAt(sb.length() - 1);

		return sb.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String str) {
		if (str == null || str.length() == 0)
			return null;

		String[] arr = str.split(",");

		TreeNode root = new TreeNode(Integer.parseInt(arr[0]));

		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);

		int i = 1;
		TreeNode node;

		while (!queue.isEmpty()) {
			node = queue.poll();

			if (!arr[i].equals("#")) {
				node.left = new TreeNode(Integer.parseInt(arr[i]));
				queue.offer(node.left);

			} else {
				node.left = null;
			}

			i++;

			if (!arr[i].equals("#")) {
				node.right = new TreeNode(Integer.parseInt(arr[i]));
				queue.offer(node.right);

			} else {
				node.right = null;
			}
			
			i++;
		}

		return root;
	}
	
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
	
	private void preOrder(TreeNode root) {
		if (root == null)
			return;

		System.out.print(root.data + " ");
		preOrder(root.left);
		preOrder(root.right);
	}

	private void inOrder(TreeNode root) {
		if (root == null)
			return;

		inOrder(root.left);
		System.out.print(root.data + " ");
		inOrder(root.right);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SerDeBT bt = new SerDeBT();
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
		
		String serializedStr = bt.serialize(root);
		System.out.println(serializedStr);
		
		TreeNode root1 = bt.deserialize(serializedStr);
		
		System.out.println("Original:");
		System.out.print("PreOrder: ");
		bt.preOrder(root);
		
		System.out.print("\nInorder: ");
		bt.inOrder(root);
		
		System.out.println("\nConstructed:");
		System.out.print("PreOrder: ");
		bt.preOrder(root1);
		System.out.print("\nInorder: ");
		bt.inOrder(root1);
		
		
		TreeNode root2 = null;
		String serializedStr2 = bt.serialize(root2);
		
		TreeNode root3 = bt.deserialize(serializedStr2);
		
		System.out.println("\nRoot3:");
		System.out.print("PreOrder: ");
		bt.preOrder(root3);
		System.out.print("\nInorder: ");
		bt.inOrder(root3);
	}

}
