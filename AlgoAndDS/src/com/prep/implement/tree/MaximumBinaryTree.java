package com.prep.implement.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaximumBinaryTree {
	private List<List<Integer>> levelOrder(TreeNode root, boolean newLine) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<List<Integer>> levels = new LinkedList<List<Integer>>();

		List<Integer> level = new LinkedList<Integer>();

		if (root == null) return levels;

		queue.offer(root);

		if (newLine) queue.offer(null);

		TreeNode current;

		while (!queue.isEmpty()) {
			current = queue.poll();

			if (current == null) {
				// System.out.println("");
				levels.add(level);
				level = new LinkedList<Integer>();

				if (!queue.isEmpty())
					queue.offer(null);
			} else {
				// System.out.print(current.data + " ");
				level.add(current.data);

				if (current.left != null) {
					queue.offer(current.left);
				}

				if (current.right != null) {
					queue.offer(current.right);
				}
			}
		}

		return levels;
	}
	
	public TreeNode maxBinaryTree(int[] nums, int left, int right) {
		if (left > right){
			return null;
		}
		
		int maxIndex = maxAsPivot(nums, left, right);
		TreeNode root = new TreeNode(nums[maxIndex]);
		
		root.left = maxBinaryTree(nums, left, maxIndex - 1);
		root.right = maxBinaryTree(nums, maxIndex + 1, right);
		return root;
	}

	// Left and right inclusive
	public int maxAsPivot(int[] nums, int left, int right) {
		int pivot = left;
		
		while(left <= right){
			if (nums[pivot] < nums[left])
				pivot = left;
			left++;
		}
		
		return pivot;
	}
	
	public TreeNode constructMaximumBinaryTree(int[] nums) {
		return maxBinaryTree(nums, 0, nums.length - 1);
	}

	public static void main(String[] args) {
		int[] nums = {3,2,1,6,0,5};
		MaximumBinaryTree mbt = new MaximumBinaryTree();
		
		TreeNode root = mbt.constructMaximumBinaryTree(nums);
		
		System.out.println("Level Order traversal of constructed tree: ");
		List<List<Integer>> levelOrder = mbt.levelOrder(root, true);
		System.out.println(levelOrder);
	}
}