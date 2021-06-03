package com.implement.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import javafx.util.Pair;

public class BinaryTree {
	private static class Counter {
		int val;
	}
	
	private static class ITPair {
		TreeNode node;
		int state;
		
		ITPair(TreeNode node, int state) {
	        this.node = node;
	        this.state = state;
	    }
	}
	
	private static class NodeDepthPair {
	    TreeNode node;
	    int depth;

	    NodeDepthPair(TreeNode node, int depth) {
	        this.node = node;
	        this.depth = depth;
	    }
	}
	
	private static class NodeBounds {
	    TreeNode node;
	    Integer lowerBound;
	    Integer upperBound;

	    NodeBounds(TreeNode node, Integer lowerBound, Integer upperBound) {
	        this.node = node;
	        this.lowerBound = lowerBound;
	        this.upperBound = upperBound;
	    }
	}
	
	private static class NodePositionDepth {
	    TreeNode node;
	    int pos;
        int depth;
        
		public NodePositionDepth(TreeNode node, int pos, int depth) {
			this.node = node;
			this.pos = pos;
			this.depth = depth;
		} 
	}
	
	private static class BSTPair {
	    boolean isBST;
	    int min;
        int max;
        TreeNode lbstroot;
        int lbstsize;
	}

	// Q1
	private void preOrder(TreeNode root) {
		if (root == null)
			return;

		System.out.print(root.data + " ");
		preOrder(root.left);
		preOrder(root.right);
	}

	// An iterative process to print preorder traversal of Binary tree
	private void preOrderIter(TreeNode root) {

		// Base Case
		if (root == null) {
			return;
		}

		// Create an empty stack and push root to it
		Stack<TreeNode> nodeStack = new Stack<TreeNode>();
		nodeStack.push(root);

		/*
		 * Pop all items one by one. Do following for every popped item a) print
		 * it b) push its right child c) push its left child Note that right
		 * child is pushed first so that left is processed first
		 */
		TreeNode current = null;
		
		while (!nodeStack.isEmpty()) {

			// Pop the top item from stack and print it
			current = nodeStack.pop();
			System.out.print(current.data + " ");

			// Push right and left children of the popped node to stack
			if (current.right != null) {
				nodeStack.push(current.right);
			}
			if (current.left != null) {
				nodeStack.push(current.left);
			}
		}
	}

	// Q2
	private void postOrder(TreeNode root) {
		if (root == null)
			return;

		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.data + " ");
	}

	private void postOrderIter(TreeNode root) {
		if (root == null)
			return;

		// Create two stacks
		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();

		// push root to first stack
		s1.push(root);
		
		TreeNode currentS1 = null;
		
		// Run while first stack is not empty
		while (!s1.isEmpty()) {
			// Pop an item from s1 and push it to s2
			currentS1 = s1.pop();
			s2.push(currentS1);

			// Push left and right children of removed item to s1
			// Note we are pushing left and right to s1 and not s2
			if (currentS1.left != null)
				s1.push(currentS1.left);
			if (currentS1.right != null)
				s1.push(currentS1.right);
		}
		
		TreeNode currentS2 = null;

		// Print all elements of second stack
		while (!s2.isEmpty()) {
			currentS2 = s2.pop();
			System.out.print(currentS2.data + " ");
		}
	}

	// Q3
	private void inOrder(TreeNode root) {
		if (root == null)
			return;

		inOrder(root.left);
		System.out.print(root.data + " ");
		inOrder(root.right);
	}

	private void inOrderIter(TreeNode root) {
		if (root == null)
			return;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode current = root;

		// traverse the tree
		while (current != null || !stack.isEmpty()) {
			/* Reach the left most Node of the curr Node */
			while (current != null) {
				/*
				 * place pointer to a tree node on the stack before traversing
				 * the node's left subtree
				 */
				stack.push(current);
				current = current.left;
			}

			/* Current must be NULL at this point */
			current = stack.pop();

			System.out.print(current.data + " ");

			/*
			 * we have visited the node and its left subtree. Now, it's right
			 * subtree's turn
			 */
			current = current.right;
		}
	}
	
	private static void treeTraversalIterative(TreeNode root) {
		if (root == null)
			return;
		
		Stack<ITPair> stack = new Stack<>();
		stack.push(new ITPair(root,0));
		
		ITPair top = null;
		
		String pre = "";
		String in = "";
		String post = "";

		// traverse the tree
		while (!stack.isEmpty()) {
			top = stack.peek();
			
			// If state is 0, push left
			if(top.state == 0){
				pre = pre + top.node.data + " ";
				
				if(top.node.left != null){
					stack.push(new ITPair(top.node.left,0));
				}
				
				top.state++;
			}else if(top.state == 1){
				// If state is 1, push right
				in = in + top.node.data + " ";
				
				if(top.node.right != null){
					stack.push(new ITPair(top.node.right,0));
				}
				
				top.state++;
			}else{
				post = post + top.node.data + " ";
				stack.pop();
			}
		}
		
		System.out.println(" Pre Order : " + pre);
		System.out.println(" In Order : " + in);
		System.out.println(" Post Order : " + post);
	}

	// Q4 :- Maximum Depth or Height of a Binary Tree
	/*
	 * Below code is for height if counting nodes
	 * For edges, do following code changes for base cases
	 * if (root == null) 								return -1
	 * if (root.left == null && root.right == null) 	return 0;
	 */
	public int getHeight(TreeNode root) {
		if (root == null){
			return 0;
		}
		
		if (root.left == null && root.right == null){
			return 1;
		}

		int leftHeight = getHeight(root.left);
		int rightHeight = getHeight(root.right);

		return 1 + Math.max(leftHeight, rightHeight);
	}
	
	// Using BFS
	public int maxDepthBFS(TreeNode root) {
		Queue<Pair<TreeNode, Integer>> queue = new LinkedList<Pair<TreeNode, Integer>>();
		if (root == null)
			return 0;

		/*
		 *  If using nodes as depth, root start with 1
		 *  If using edges as depth, root start with 0
		 */
		queue.offer(new Pair<TreeNode, Integer>(root, 1));

		int current_depth = 0, max_depth = 0;
		Pair<TreeNode, Integer> current;
		TreeNode node;
		
		while (!queue.isEmpty()) {
			current = queue.poll();
			node = current.getKey();
			current_depth = current.getValue();
			
			max_depth = Math.max(max_depth, current_depth);
			
			if (node.left != null) {
				queue.add(new Pair<TreeNode, Integer>(node.left, current_depth + 1));
			}
			if (node.right != null) {
				queue.add(new Pair<TreeNode, Integer>(node.right, current_depth + 1));
			}
		}
		return max_depth;
	}
	
	// DFS Recursive
	public int maxDepthDFS(TreeNode root) {
		if (root == null){
			return 0;
		}
		
		if (root.left == null && root.right == null){
			return 1;
		}

		int leftHeight = maxDepthDFS(root.left);
		int rightHeight = maxDepthDFS(root.right);

		return 1 + Math.max(leftHeight, rightHeight);
	}
	
	//DFS Iterative
	/*public int maxDepthDFS(TreeNode root) {
		if (root == null)
			return 0;

		Stack<Pair<TreeNode, Integer>> stack = new Stack<Pair<TreeNode, Integer>>();
		
		
		 //  If using nodes as depth, root start with 1
		 //  If using edges as depth, root start with 0
		 
		stack.push(new Pair<TreeNode, Integer>(root, 1));

		int current_depth = 0, max_depth = 0;
		Pair<TreeNode, Integer> current;
		TreeNode node;
		
		while (!stack.isEmpty()) {
			current = stack.pop();
			node = current.getKey();
			current_depth = current.getValue();
			
			max_depth = Math.max(max_depth, current_depth);
			
			// Push first right and then left
			if (node.right != null) {
				stack.add(new Pair<TreeNode, Integer>(node.right, current_depth + 1));
			}
			
			if (node.left != null) {
				stack.add(new Pair<TreeNode, Integer>(node.left, current_depth + 1));
			}
			
		}
		return max_depth;
	}*/

	// Use BFS
	public int minDepthBFS(TreeNode root) {
		Queue<Pair<TreeNode, Integer>> queue = new LinkedList<Pair<TreeNode, Integer>>();
		if (root == null) {
			return 0;
		} 
		
		// If counting nodes as depth, root start with 1
		queue.add(new Pair<>(root, 1));

		int current_depth = 0;
		Pair<TreeNode, Integer> current;
		TreeNode node;
		
		while (!queue.isEmpty()) {
			current = queue.poll();
			node = current.getKey();
			current_depth = current.getValue();
			
			// Once we get the first leaf node, break and return current depth
			if ((node.left == null) && (node.right == null)) {
				return current_depth;
			}
			
			if (node.left != null) {
				queue.add(new Pair<>(node.left, current_depth + 1));
			}
			if (node.right != null) {
				queue.add(new Pair<>(node.right, current_depth + 1));
			}
		}
		
		return current_depth;
	}
	
	// Recursive
	public int minDepthDFS(TreeNode root) {
		if (root == null) {
			return 0;
		}

		if (root.left == null && root.right == null) {
			return 1;
		}
		
		if (root.left == null) {
			return 1 + minDepthDFS(root.right);
		}
        
        if(root.right == null){
        	return 1 + minDepthDFS(root.left);
        }

		int leftHeight = minDepthDFS(root.left);
		int rightHeight = minDepthDFS(root.right);

		return 1 + Math.min(leftHeight, rightHeight);
	}
	
	// Use BFS
	public int maxWidthBFS(TreeNode root) {
		Queue<NodePositionDepth> queue = new LinkedList<NodePositionDepth>();
		int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int width = 0;
        
		if (root == null)
			return 0;

		/*
		 * If using nodes as depth, root start with 1 If using edges as depth,
		 * root start with 0
		 */
		// For root
		queue.offer(new NodePositionDepth(root, 1, 0));
		queue.offer(null);
		
		NodePositionDepth current;
		TreeNode node;

		while (!queue.isEmpty()) {
			current = queue.poll();
			
			if(current == null){
				width = Math.max(width, max - min + 1);
				min = Integer.MAX_VALUE;
	            max = Integer.MIN_VALUE;
	            
	            if (!queue.isEmpty())
					queue.offer(null);
			}else{
				node = current.node;
				
				min = Math.min(min, current.pos);
	            max = Math.max(max, current.pos);
	            
				if (node.left != null) {
					queue.offer(new NodePositionDepth(node.left, 2 * current.pos, 
										current.depth + 1));
				}
				
				if (node.right != null) {
					queue.offer(new NodePositionDepth(node.right, 2 * current.pos + 1, 
							current.depth + 1));
				}
			}
		}
		
		return width;
	}

	// Q5
	void goLeft(TreeNode root) {
		if (root.left != null) {
			goLeft(root.left);
		}
		System.out.print(root.data + " ");
	}

	void goRight(TreeNode root) {
		System.out.print(root.data + " ");

		if (root.right != null) {
			goRight(root.right);
		}
	}

	void top_view(TreeNode root) {
		if (root.left != null) {
			goLeft(root.left);
		}

		System.out.print(root.data + " ");

		if (root.right != null) {
			goRight(root.right);
		}
	}

	// This method prints nodes in top view of binary tree
	public void printTopView(TreeNode root) {
		// base case
		if (root == null) {
			return;
		}

		// Creates an empty hashset
		Set<Integer> set = new HashSet<>();

		// Create a queue and add root to it
		Queue<QItem> Q = new LinkedList<QItem>();
		Q.add(new QItem(root, 0)); // Horizontal distance of root is 0

		// Standard BFS or level order traversal loop
		while (!Q.isEmpty()) {
			// Remove the front item and get its details
			QItem qi = Q.poll();
			int hd = qi.hd;
			TreeNode n = qi.node;

			// If this is the first node at its horizontal distance,
			// then this node is in top view
			if (!set.contains(hd)) {
				set.add(hd);
				System.out.print(n.data + " ");
			}

			// Enqueue left and right children of current node
			if (n.left != null)
				Q.add(new QItem(n.left, hd - 1));
			if (n.right != null)
				Q.add(new QItem(n.right, hd + 1));
		}
	}
	
	// For top view, use level by level and set
	public List<Integer> topView(TreeNode root) {
		List<Integer> topView = new ArrayList<Integer>();
		
		if(root == null) return topView;
		
		HashSet<Integer> set = new HashSet<>();

		// Create a queue and add root to it
		Queue<Pair<TreeNode, Integer>> queue = new LinkedList<Pair<TreeNode, Integer>>();
		queue.offer(new Pair<>(root, 0));

		Pair<TreeNode, Integer> current;
		TreeNode node;
		
		// Standard BFS or level order traversal loop
		while (!queue.isEmpty()) {
			// Remove the front item and get its details
			current = queue.poll();
			int current_hd = current.getValue();
			node = current.getKey();
			
			// For top view, use level by level and set
			if (!set.contains(current_hd)) {
				set.add(current_hd);
				topView.add(node.data);
			}
			
			if (node.left != null) {
				queue.offer(new Pair<>(node.left, current_hd - 1));
			}

			if (node.right != null) {
				queue.offer(new Pair<>(node.right, current_hd + 1));
			}
		}

		return topView;
	}

	// This method prints nodes in bottom view of binary tree
	public void printBottomView(TreeNode root) {
		// base case
		if (root == null) {
			return;
		}

		// Creates an empty hashmap
		Map<Integer, Integer> map = new HashMap<>();

		// Create a queue and add root to it
		Queue<QItem> Q = new LinkedList<QItem>();
		Q.add(new QItem(root, 0)); // Horizontal distance of root is 0

		// Standard BFS or level order traversal loop
		while (!Q.isEmpty()) {
			// Remove the front item and get its details
			QItem qi = Q.poll();
			int hd = qi.hd;
			TreeNode n = qi.node;

			// Keep updating horizontal distance, with this node in bottom view
			map.put(hd, n.data);

			// Enqueue left and right children of current node
			if (n.left != null)
				Q.add(new QItem(n.left, hd - 1));
			if (n.right != null)
				Q.add(new QItem(n.right, hd + 1));
		}

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.print(entry.getValue() + " ");
		}
	}
	
	public List<Integer> bottomView(TreeNode root) {
		List<Integer> bottomView = new ArrayList<Integer>();
		
		if(root == null) return bottomView;
		
		Map<Integer, Integer> map = new HashMap<>();

		// Create a queue and add root to it
		Queue<Pair<TreeNode, Integer>> queue = new LinkedList<Pair<TreeNode, Integer>>();
		queue.offer(new Pair<>(root, 0));

		Pair<TreeNode, Integer> current;
		TreeNode node;
		
		// Standard BFS or level order traversal loop
		while (!queue.isEmpty()) {
			// Remove the front item and get its details
			current = queue.poll();
			int current_hd = current.getValue();
			node = current.getKey();
			
			// Keep updating horizontal distance, with this node in bottom view
			map.put(current_hd, node.data);
			
			// For bottom view, use level by level and map
			if (node.left != null) {
				queue.offer(new Pair<>(node.left, current_hd - 1));
			}

			if (node.right != null) {
				queue.offer(new Pair<>(node.right, current_hd + 1));
			}
		}
		
		/*for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.print(entry.getValue() + " ");
		}*/
		
		/*for (Integer value : map.values()) {
			bottomView.add(value);
		}*/
		
		bottomView.addAll(map.values());

		return bottomView;
	}
	
	// This method prints nodes in top view of binary tree
	public void printLeftView(TreeNode root) {
		// base case
		if (root == null) {
			return;
		}

		// Creates an empty hashset
		HashSet<Integer> set = new HashSet<>();

		// Create a queue and add root to it
		Queue<QItem> Q = new LinkedList<QItem>();
		Q.add(new QItem(root, 0));
		System.out.print(root.data + " ");

		// Standard BFS or level order traversal loop
		while (!Q.isEmpty()) {
			// Remove the front item and get its details
			QItem qi = Q.poll();
			int thisLevel = qi.hd + 1;
			TreeNode n = qi.node;

			// Enqueue left and right children of current node
			if (n.left != null) {
				// If this is the first node at this level,
				// then add it in left view

				if (!set.contains(thisLevel)) {
					set.add(thisLevel);
					System.out.print(n.left.data + " ");
				}

				Q.add(new QItem(n.left, thisLevel));
			}

			if (n.right != null) {
				// If this is the first node at this level,
				// then add it in left view

				if (!set.contains(thisLevel)) {
					set.add(thisLevel);
					System.out.print(n.right.data + " ");
				}

				Q.add(new QItem(n.right, thisLevel));
			}
		}
	}
	
	public List<Integer> leftSideView(TreeNode root) {
		List<Integer> leftView = new ArrayList<Integer>();
		
		if(root == null) return leftView;
		
		HashSet<Integer> set = new HashSet<>();

		// Create a queue and add root to it
		Queue<Pair<TreeNode, Integer>> queue = new LinkedList<Pair<TreeNode, Integer>>();
		queue.offer(new Pair<>(root, 0));

		Pair<TreeNode, Integer> current;
		TreeNode node;
		
		// Standard BFS or level order traversal loop
		while (!queue.isEmpty()) {
			// Remove the front item and get its details
			current = queue.poll();
			int current_depth = current.getValue();
			node = current.getKey();
			
			if (!set.contains(current_depth)) {
				set.add(current_depth);
				leftView.add(node.data);
			}
			
			// For left view, enqueue left and then right children of current node
			if (node.left != null) {
				queue.offer(new Pair<>(node.left, current_depth + 1));
			}

			if (node.right != null) {
				queue.offer(new Pair<>(node.right, current_depth + 1));
			}
		}

		return leftView;
	}

	// This method prints nodes in bottom view of binary tree
	public void printRightView(TreeNode root) {
		// base case
		if (root == null) {
			return;
		}

		// Creates an empty hashmap
		Map<Integer, Integer> map = new HashMap<>();

		// Create a queue and add root to it
		Queue<QItem> Q = new LinkedList<QItem>();
		Q.add(new QItem(root, 0));
		System.out.print(root.data + " ");

		// Standard BFS or level order traversal loop
		while (!Q.isEmpty()) {
			// Remove the front item and get its details
			QItem qi = Q.poll();
			int thisLevel = qi.hd + 1;
			TreeNode n = qi.node;

			// Enqueue left and right children of current node
			if (n.left != null) {
				// Keep updating level key, with this node in right view
				map.put(thisLevel, n.left.data);

				Q.add(new QItem(n.left, thisLevel));
			}
			if (n.right != null) {
				// Keep updating level key, with this node in right view
				map.put(thisLevel, n.right.data);

				Q.add(new QItem(n.right, thisLevel));
			}
		}

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.print(entry.getValue() + " ");
		}
	}

	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> rightView = new ArrayList<Integer>();
		
		if(root == null) return rightView;
		
		HashSet<Integer> set = new HashSet<>();

		// Create a queue and add root to it
		Queue<Pair<TreeNode, Integer>> queue = new LinkedList<Pair<TreeNode, Integer>>();
		queue.offer(new Pair<>(root, 0));

		Pair<TreeNode, Integer> current;
		TreeNode node;

		// Standard BFS or level order traversal loop
		while (!queue.isEmpty()) {
			// Remove the front item and get its details
			current = queue.poll();
			int current_depth = current.getValue();
			node = current.getKey();

			if (!set.contains(current_depth)) {
				set.add(current_depth);
				rightView.add(node.data);
			}

			/*
			 * For right view, enqueue right and then left children of current node
			 */
			if (node.right != null) {
				queue.offer(new Pair<>(node.right, current_depth + 1));
			}
			
			if (node.left != null) {
				queue.offer(new Pair<>(node.left, current_depth + 1));
			}
		}

		return rightView;
	}

	// Q6
	private void levelOrder(TreeNode root) {
		if (root == null)
			return;

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		TreeNode current;

		while (!queue.isEmpty()) {
			current = queue.poll();

			System.out.print(current.data + " ");

			if (current.left != null) {
				queue.offer(current.left);
			}

			if (current.right != null) {
				queue.offer(current.right);
			}
		}
	}

	// Q6.1
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
				levels.add(new LinkedList<Integer>(level));
				level.clear();

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
	
	// Q6.10
	private List<List<Integer>> levelOrderBottomUp(TreeNode root) {
		List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
		
		if (root == null)
			return wrapList;
		
		List<Integer> level = new LinkedList<Integer>();

		Queue<TreeNode> queue = new LinkedList<TreeNode>();

		queue.offer(root);
		queue.offer(null);

		TreeNode current;

		while (!queue.isEmpty()) {
			current = queue.poll();

			if (current == null) {
				wrapList.add(0, new LinkedList<Integer>(level));
				level.clear();

				if (!queue.isEmpty())
					queue.offer(null);
			} else {
				level.add(current.data);

				if (current.left != null) {
					queue.offer(current.left);
				}

				if (current.right != null) {
					queue.offer(current.right);
				}
			}	
		}

		return wrapList;
	}
	
	// Q6.11
	private void connectedNodesAtSameLevel(TreeNode root) {
		if (root == null)
			return;

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		queue.offer(null);

		TreeNode current, nextRight;

		while (!queue.isEmpty()) {
			current = queue.poll();
			nextRight = queue.peek();
			
			if (current == null) {
				if (!queue.isEmpty())
					queue.offer(null);
			} else {
				current.nextRight = nextRight;
				
				if (current.left != null) {
					queue.offer(current.left);
				}

				if (current.right != null) {
					queue.offer(current.right);
				}
			}
		}
	}
	
	// Q6.12
	private void alternateLevels(TreeNode root) {
		if (root == null)
			return;

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		boolean skip = false;

		queue.offer(root);
		queue.offer(null);

		TreeNode current;

		while (!queue.isEmpty()) {
			current = queue.poll();

			if (current == null) {
				if (!skip)
					System.out.println("");
				
				if (!queue.isEmpty())
					queue.offer(null);

				skip = !skip;
			} else {
				if (!skip)
					System.out.print(current.data + " ");

				if (current.left != null) {
					queue.offer(current.left);
				}

				if (current.right != null) {
					queue.offer(current.right);
				}
			}
		}
	}
	
	// Q6.13
	private List<Double> averageOfLevels(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<Double> averages = new LinkedList<Double>();
		long currentSum = 0;
		int countOfNodes = 0;
		
		if (root == null) return averages;

		queue.offer(root);
		queue.offer(null);

		TreeNode current;

		while (!queue.isEmpty()) {
			current = queue.poll();

			if (current == null) {
				// System.out.println("");
				averages.add(1.0 * currentSum / countOfNodes);
				currentSum = 0;
				countOfNodes = 0;
				
				if (!queue.isEmpty())
					queue.offer(null);
			} else {
				// System.out.print(current.data + " ");
				currentSum = currentSum + current.data;
				countOfNodes++;

				if (current.left != null) {
					queue.offer(current.left);
				}

				if (current.right != null) {
					queue.offer(current.right);
				}
			}
		}

		return averages;
	}

	private boolean findKeyIterativelyLevelOrder(TreeNode root, int data) {
		if (root == null)
			return false;

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);

		TreeNode current;

		while (!queue.isEmpty()) {
			current = queue.poll();

			if (current.data == data)
				return true;

			if (current.left != null)
				queue.offer(current.left);

			if (current.right != null)
				queue.offer(current.right);
		}

		return false;
	}

	private boolean findKeyIterativelyPreOrder(TreeNode root, int data) {
		if (root == null)
			return false;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);

		TreeNode current;

		while (!stack.isEmpty()) {
			current = stack.pop();

			if (current.data == data)
				return true;

			if (current.right != null)
				stack.push(current.right);

			if (current.left != null)
				stack.push(current.left);
		}

		return false;
	}

	public static ArrayList<LinkedList<TreeNode>> createLevelLinkedList(
			TreeNode root) {
		ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();

		LinkedList<TreeNode> parents = null;
		LinkedList<TreeNode> current = new LinkedList<TreeNode>();

		// Visit the root
		if (root != null) {
			current.add(root);
		}

		while (current.size() > 0) {
			result.add(current); // Add previous level

			parents = current;
			current = new LinkedList<TreeNode>();

			for (TreeNode parent : parents) {
				// Visit the children
				if (parent.left != null) {
					current.add(parent.left);
				}

				if (parent.right != null) {
					current.add(parent.right);
				}
			}
		}

		return result;
	}

	/*
	 * Java implementation of an O(n) approach of level order traversal in
	 * spiral form
	 */

	public List<List<Integer>> spiralOrder(TreeNode root) {
		List<List<Integer>> zigzagLevels = new LinkedList<List<Integer>>();
		
		if (root == null) return zigzagLevels;
		
		List<Integer> level = new LinkedList<Integer>();

		/* Create two stacks to store alternate levels
		 * 1. For levels to be printed from right to left
		 * 2. For levels to be printed from left to right
		 */
		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();

		// Push first level to first stack 's1'
		s1.push(root);
		
		TreeNode current = null;

		// Keep printing while any of the stacks has some nodes
		while (!s1.empty() || !s2.empty()) {
			// Print nodes of current level from s1 and push nodes of next level to s2
			while (!s1.empty()) {
				current = s1.pop();
				//System.out.print(temp.data + " ");
				level.add(current.data);

				// Note that left is pushed before right
				if (current.left != null)
					s2.push(current.left);

				if (current.right != null)
					s2.push(current.right);

			}
			
			//Add current level to the zigzag list
			if(level.size() > 0){
				zigzagLevels.add(new LinkedList<>(level));
				level.clear();
			}
			
			// Print nodes of current level from s2 and push nodes of next level to s1
			
			while (!s2.empty()) {
				current = s2.pop();
				//System.out.print(temp.data + " ");
				level.add(current.data);

				// Note that is right is pushed before left
				if (current.right != null)
					s1.push(current.right);
				
				if (current.left != null)
					s1.push(current.left);
			}
			
			//Add current level to the zigzag list
			if(level.size() > 0){
				zigzagLevels.add(new LinkedList<>(level));
				level.clear();
			}
		}
		
		return zigzagLevels;
	}

	// Q7 - Insert a node in Binary Search Tree
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

	// Q8 - Huffman Decoding
	/*
	 * class Node public int frequency; // the frequency of this tree public
	 * char data; public Node left, right;
	 */

	private void decode(String S, TreeNode root) {
		StringBuilder decodedStr = new StringBuilder();
		TreeNode temp = root;
		for (int i = 0; i < S.length(); i++) {
			temp = S.charAt(i) == '1' ? temp.right : temp.left;
			if (temp.left == null && temp.right == null) {
				decodedStr.append(temp.data);
				temp = root;
			}
		}
		System.out.print(decodedStr);
	}

	// Q9 - LCA of n1 and n2 in BST

	// This function returns pointer to LCA of two given
	// values n1 and n2. This function assumes that n1 and
	// n2 are present in Binary Tree
	private TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
		// Base case
		if (root == null)
			return null;

		// If either n1 or n2 matches with root's key, report
		// the presence by returning root (Note that if a key is
		// ancestor of other, then the ancestor key becomes LCA
		if (root.data == p.data || root.data == q.data)
			return root;

		// Look for keys in left and right subtrees
		TreeNode left_lca = findLCA(root.left, p, q);
		TreeNode right_lca = findLCA(root.right, p, q);

		// If both of the above calls return Non-NULL, then one key
		// is present in one subtree and other is present in other,
		// So this node is the LCA
		if (left_lca != null && right_lca != null)
			return root;

		// Otherwise check if left subtree or right subtree is LCA
		return (left_lca != null) ? left_lca : right_lca;
	}

	private TreeNode parent(TreeNode root, TreeNode node) {
		if (root == null || root.data == node.data) {
			return null;
		}

		if ((root.left != null && root.left.data == node.data)
				|| (root.right != null && root.right.data == node.data))
			return root;

		if (root.data < node.data) {
			return parent(root.right, node);
		} else {
			return parent(root.left, node);
		}
	}

	// Q10 - Check BST
	/*
	 * Returns true if the given tree is a BST and its values are >= min and <=
	 * max.
	 */
	boolean isBST(TreeNode root, int min, int max) {
		/* an empty tree is BST */
		if (root == null)
			return true;

		/* false if this node violates the min/max constraints */
		if (root.data <= min || root.data > max)
			return false;

		/*
		 * otherwise check the subtrees recursively tightening the min/max
		 * constraints
		 */
		// Allow only distinct values
		return (isBST(root.left, min, root.data) && 
				isBST(root.right, root.data, max));
	}

	public boolean isValidBST(TreeNode root) {
		//return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
		
		if (root == null) return true;
		return isBSTIter(root);
	}
	
	/*
	 * isBST Iteratively
	 */
	
	public static boolean isBSTIter(TreeNode root) {
	    // start at the root, with an arbitrarily low lower bound
	    // and an arbitrarily high upper bound
	    Stack<NodeBounds> nodeAndBoundsStack = new Stack<>();
	    nodeAndBoundsStack.push(new NodeBounds(root, null, null));

	    // depth-first traversal : Pre-order
	    while (!nodeAndBoundsStack.isEmpty()) {
	        NodeBounds nb = nodeAndBoundsStack.pop();
	        TreeNode node = nb.node;
	        Integer lowerBound = nb.lowerBound;
	        Integer upperBound = nb.upperBound;

	        /*
	         *  if this node is invalid, we return false right away
	         *  
	         *  Use NULL's instead of Min and Max value
	         */
	        if (lowerBound != null && node.data <= lowerBound) return false;
	        if (upperBound != null && node.data >= upperBound) return false;

	        if (node.right != null) {
	            // this node must be greater than the current node
	            nodeAndBoundsStack.push(new NodeBounds(node.right, node.data, upperBound));
	        }
	        
	        if (node.left != null) {
	            // this node must be less than the current node
	            nodeAndBoundsStack.push(new NodeBounds(node.left, lowerBound, node.data));
	        }
	    }

	    // if none of the nodes were invalid, return true
	    // (at this point we have checked all nodes)
	    return true;
	}
	
	public static BSTPair isBST(TreeNode root){
		if (root == null){
			BSTPair bp = new BSTPair();
			bp.isBST = true;
			bp.min = Integer.MAX_VALUE;
			bp.max = Integer.MIN_VALUE;
			bp.lbstroot = null;
			bp.lbstsize = 0;
			return bp;
		}
		
		BSTPair lp = isBST(root.left);
		BSTPair rp = isBST(root.right);
		
		BSTPair mp = new BSTPair();
		
		mp.isBST = lp.isBST && rp.isBST &&
					root.data >= lp.max && root.data <= rp.min;
		mp.min = Math.min(root.data, Math.min(lp.min, rp.min));
		mp.max = Math.max(root.data, Math.max(lp.max, rp.max));
		
		if(mp.isBST){
			mp.lbstroot = root;
			mp.lbstsize = lp.lbstsize + rp.lbstsize + 1;
		}else if(lp.lbstsize > rp.lbstsize) {
			mp.lbstroot = lp.lbstroot;
			mp.lbstsize = lp.lbstsize;
		}else{
			mp.lbstroot = rp.lbstroot;
			mp.lbstsize = rp.lbstsize;
		}
		
		return mp;
	}
	
	// depth-first traversal : Pre-order
	int getCountNodesInRange(TreeNode root, int low, int high){
		// Base Case 
        if(root == null) 
            return 0; 
  
        // If current node is in range, then include it in count and recur for  
        //left and right children of it 
        if(root.data >= low && root.data <= high) 
            return 1 + this.getCountNodesInRange(root.left, low, root.data) + 
                this.getCountNodesInRange(root.right, root.data, high); 
                  
        // If current node is smaller than low,  then recur for right child 
        else if(root.data < low) 
            return this.getCountNodesInRange(root.right, low, high); 
          
        // Else recur for left child 
        return this.getCountNodesInRange(root.left, low, high);
	}

	// Find min value in BST - Iteratively
	private TreeNode findMinIter(TreeNode root) {
		if (root == null)
			return null;
		TreeNode current = root;

		while (current.left != null) {
			current = current.left;
		}

		return current;
	}

	// Find min value in BST - Recursively
	private int findMinRecur(TreeNode root) {
		if (root == null)
			return -1;

		if (root.left == null)
			return root.data;

		return findMinRecur(root.left);
	}

	// Find max value in BST - Iteratively
	private TreeNode findMaxIter(TreeNode root) {
		if (root == null)
			return null;
		TreeNode current = root;

		while (current.right != null) {
			current = current.right;
		}

		return current;
	}

	// Find min value in BST - Recursively
	private int findMaxRecur(TreeNode root) {
		if (root == null)
			return -1;

		if (root.right == null)
			return root.data;

		return findMaxRecur(root.right);
	}
	
	/*
	 * Given a binary tree where each path going from the root to any leaf form a valid sequence, 
	 * check if a given string is a valid sequence in such binary tree. 
	 */

	private boolean checkValidSequence(TreeNode root, int[] arr, int pos) {
		if(root == null || root.data != arr[pos])
			return false;
		
		if(pos == arr.length-1) 
			return root.left == null && root.right == null && root.data == arr[pos];
		
		boolean isValid = false;
		
		// If we are at this point means root.data == arr[pos]
		if (root.left != null)
			isValid = isValid || checkValidSequence(root.left, arr, pos+1);
		if (root.right != null)
			isValid = isValid || checkValidSequence(root.right, arr, pos+1);
		
		return isValid;
	}

	public boolean isValidSequence(TreeNode root, int[] arr) {
		if (root == null) 
			return arr == null || arr.length == 0;
		
		if (arr == null || arr.length == 0) 
			return false;

		return checkValidSequence(root, arr, 0);
	}

	/*
	 * Given a tree and a sum, return true if there is a path from the root down
	 * to a leaf, such that adding up all the values along the path equals the
	 * given sum.
	 * 
	 * Strategy: subtract the node value from the sum when recurring down, and
	 * check to see if the sum is 0 when you run out of tree.
	 */
	private boolean hasPathSum(TreeNode root, int sum) {
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
	
	private void pathSum(TreeNode root, int sum, List<Integer> path, List<List<Integer>> result) {
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
		
		// Recurse left
		if (root.left != null) {
			pathSum(root.left, subsum, path, result);
		}

		// Recurse right
		if (root.right != null) {
			pathSum(root.right, subsum, path, result);
		}
		
		// Backtrack
		path.remove(path.size() - 1);
	}
	
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> path = new ArrayList<Integer>();
		pathSum(root, sum, path, result);
		return result;
	}

	int max;
	public int helperPathSumNodeToNode(TreeNode root) {
		if (root == null)
			return 0;

		int maxLeft = helperPathSumNodeToNode(root.left);
		int maxRight = helperPathSumNodeToNode(root.right);

		// When the longest path doesn't pass through this node
		int temp = Math.max(Math.max(maxLeft, maxRight) + root.data,	
								root.data);		// If left and right yields -ve value

		// When the longest path passes through this node
		//int ans = Math.max(temp, maxLeft + maxRight + root.data);
		int ans = Math.max(temp, maxLeft + maxRight + root.data);

		// Update result
		max = Math.max(max, ans);

		return temp;

	}
	
	public int maxPathSumNodeToNode(TreeNode root) {
		if (root == null)
			return 0;
		
		max = Integer.MIN_VALUE;
		helperPathSumNodeToNode(root);
		
		return max;
	}
	
	public int helperPathSumLeafToLeaf(TreeNode root) {
		if (root == null)
			return 0;
		
		// For leaf node there is no Leaf to Leaf path, so simply return temp to upstream
		if (root.left == null && root.right == null)
			return root.data;

		int maxLeft = helperPathSumLeafToLeaf(root.left);
		int maxRight = helperPathSumLeafToLeaf(root.right);

		/*
		 *  When the longest path doesn't pass through this node, 
		 *  calculate max path to leaf from this node and return to upstream
		 */
		int temp = Math.max(maxLeft, maxRight) + root.data;
		
		/*
		 *  When the longest path passes through this node, 
		 *  add root.data to leftMax and rightMax and compare with result so far
		 */
		int ans = maxLeft + maxRight + root.data;

		// Update result
		max = Math.max(max, ans);

		return temp;

	}
	
	/*
	 * Create following Binary Tree 
	 * 								-10 
	 * 							   / \ 
	 * 							  9    20 
	 *                          	 /   \
	 *                         		15     -7
	 */
	
	public int maxPathSumLeafToLeaf(TreeNode root) {
		if (root == null)
			return 0;

		max = Integer.MIN_VALUE;
		
		helperPathSumLeafToLeaf(root);
		
		return max;
	}

	// Print all nodes having k leaves in subtree rooted with them
	private int kLeaves(TreeNode root, int k) {
		if (root == null)
			return 0;

		if (root.left == null && root.right == null)
			return 1;

		int lc = kLeaves(root.left, k);
		int rc = kLeaves(root.right, k);
		int tc = lc + rc;

		if (k == tc) {
			System.out.print(root.data + " ");
		}

		return tc;
	}

	/* Diameter of a Binary Tree
	 * If root is not included in diameter, 
	 * Use Math.max(lheight + rheight),Math.max(ldiameter, rdiameter));
	 * Otherwise Math.max(1 + lheight + rheight),Math.max(ldiameter, rdiameter));
	 */

	private int diameter(TreeNode root) {
		if (root == null)
			return 0;

		int lheight = getHeight(root.left);
		int rheight = getHeight(root.right);

		int ldiameter = diameter(root.left);
		int rdiameter = diameter(root.right);

		/*
		 * If counting all nodes
		 * diameter = Math.max(1 + lheight + rheight, 
		 * 					Math.max(ldiameter, rdiameter))
		 * 
		 * Below code, if counting edges
		 */
		int diameter = Math.max(lheight + rheight,
								Math.max(ldiameter, rdiameter));

		return diameter;
	}
	
	private int diameterUsingMaxDepth(TreeNode root) {
		if (root == null)
			return 0;

		int lheight = maxDepthDFS(root.left);
		int rheight = maxDepthDFS(root.right);

		int ldiameter = diameterUsingMaxDepth(root.left);
		int rdiameter = diameterUsingMaxDepth(root.right);

		int diameter = Math.max(lheight + rheight,
				Math.max(ldiameter, rdiameter));

		return diameter;
	}
	
	// Double Tree
	private TreeNode doubleTree(TreeNode root) {
		if (root == null)
			return null;

		TreeNode left = doubleTree(root.left);
		TreeNode right = doubleTree(root.right);

		TreeNode newNode = new TreeNode(root.data);
		newNode.left = left;
		newNode.right = right;
		
		root.left = newNode;
		
		return root;
	}

	// Mirror Image of a Tree
	private TreeNode mirrorImage(TreeNode root) {
		if (root == null) {
	        return root;
	    }
		
		TreeNode left = mirrorImage(root.left);
	    TreeNode right = mirrorImage(root.right);
	    
	    root.left = right;
	    root.right = left;
	    return root;
	}

	// Every node is sum of itself and its children
	private void rootChildSum(TreeNode root) {
		if (root == null)
			return;

		rootChildSum(root.left);
		rootChildSum(root.right);

		if (root.left != null && root.right != null)
			root.data = root.data + root.left.data + root.right.data;
		else if (root.left != null)
			root.data = root.data + root.left.data;
		else if (root.right != null)
			root.data = root.data + root.right.data;
	}

	private boolean findKeyRecusively(TreeNode root, int data) {
		if (root == null)
			return false;
		if (root.data == data)
			return true;

		if (root.data > data)
			return findKeyRecusively(root.left, data);

		return findKeyRecusively(root.right, data);
	}
	
	public TreeNode searchBST(TreeNode root, int val) {
        if(root == null || root.data == val) return root;
        
        if(root.data > val)
            return searchBST(root.left, val);
        
        return searchBST(root.right, val);
    }

	/**
	 * @param root : The root of binary tree.
	 * @return: True if this Binary tree is Balanced, or false.
	 */
	public boolean isBalanced(TreeNode root) {
		return checkHeight(root) != Integer.MIN_VALUE;
	}

	private int checkHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		if(root.left == null && root.right == null){
			return 1;
		}

		int leftHeight = checkHeight(root.left);
		if (leftHeight == Integer.MIN_VALUE)
			return Integer.MIN_VALUE;

		int rightHeight = checkHeight(root.right);
		if (rightHeight == Integer.MIN_VALUE)
			return Integer.MIN_VALUE;

		int heightDiff = Math.abs(leftHeight - rightHeight);

		if (heightDiff > 1) {
			return Integer.MIN_VALUE;
		}

		return 1 + Math.max(leftHeight, rightHeight);
	}

	public static boolean isSuperBalanced(TreeNode root) {
		// a tree with no nodes is superbalanced, since there are no leaves!
		if (root == null) {
			return true;
		}

		// we short-circuit as soon as we find more than 2
		List<Integer> depths = new ArrayList<>(3);

		Queue<NodeDepthPair> nodes = new LinkedList<NodeDepthPair>();
		nodes.offer(new NodeDepthPair(root, 0));
		
		NodeDepthPair current;
		TreeNode node;
		
		while (!nodes.isEmpty()) {

			// pop a node and its depth from the top of our stack
			current = nodes.poll();
			node = current.node;
			int depth = current.depth;

			// case: we found a leaf
			if (node.left == null && node.right == null) {

				// we only care if it's a new depth
				if (!depths.contains(depth)) {
					depths.add(depth);

					// two ways we might now have an unbalanced tree:
					// 1) more than 2 different leaf depths
					// 2) 2 leaf depths that are more than 1 apart
					if (depths.size() > 2
							|| (depths.size() == 2 && Math.abs(depths.get(0)
									- depths.get(1)) > 1)) {
						return false;
					}
				}

				// case: this isn't a leaf - keep stepping down
			} else {
				if (node.left != null) {
					nodes.offer(new NodeDepthPair(node.left, depth + 1));
				}
				if (node.right != null) {
					nodes.offer(new NodeDepthPair(node.right, depth + 1));
				}
			}
		}

		return true;
	}

	/**
	 * @param root
	 *            the root of the binary tree
	 * @return all root-to-leaf paths
	 */
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> result = new ArrayList<String>();
		paths(root, null, result);
		return result;
	}

	private void paths(TreeNode root, String prefix, List<String> result) {
		if (root == null) {
			return;
		}

		String s = (prefix == null ? String.valueOf(root.data) : 
							prefix + "->" + String.valueOf(root.data));

		if (root.left == null && root.right == null) {
			result.add(s);
			return;
		}

		if (root.left != null) {
			paths(root.left, s, result);
		}

		if (root.right != null) {
			paths(root.right, s, result);
		}
	}

	/**
	 * @param root
	 *            : The root of binary tree
	 * @return root of new tree
	 */
	public TreeNode cloneTree(TreeNode root) {
		if (root == null)
			return null;
		TreeNode clone = new TreeNode(root.data);
		clone.left = cloneTree(root.left);
		clone.right = cloneTree(root.right);
		return clone;
	}

	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if(t1 == null) return t2;
		if(t2 == null) return t1;
		
		TreeNode root = new TreeNode(t1.data + t2.data);
		root.left = mergeTrees(t1.left, t2.left);
		root.right = mergeTrees(t1.right, t2.right);
		
		return root;
	}
	
	/**
	 * @param a
	 *            , b, the root of binary trees.
	 * @return true if they are identical, or false.
	 */
	public boolean isIdentical(TreeNode a, TreeNode b) {
		if (a == null && b == null) {
			return true;
		} else if (a == null || b == null) {
			return false;
		} else {
			return a.data == b.data && isIdentical(a.left, b.left)
					&& isIdentical(a.right, b.right);
		}
	}
	
	public boolean isSubtree(TreeNode s, TreeNode t) {
		return s != null
				&& (isIdentical(s, t) || 
					isSubtree(s.left, t) || 
					isSubtree(s.right, t));
	}

	// Return k smallest nodes
	private ArrayList<Integer> kSmallestNodes(TreeNode root, int k) {
		ArrayList<Integer> result = new ArrayList<Integer>(k);
		
		if (root == null || k == 0) 
			return result;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		int count = 0;

		TreeNode current = root;

		// traverse the tree
		while (current != null || !stack.isEmpty()) {
			while (current != null) {
				/*
				 * place pointer to a tree node on the stack before traversing
				 * the node's left subtree
				 */
				stack.push(current);
				current = current.left;
			}
			
			current = stack.pop();

			count = count + 1;
			if (count <= k)
				result.add(current.data);
			else break;

			/*
			 * we have visited the node and its left subtree. Now, it's right
			 * subtree's turn
			 */
			current = current.right;
		}
		
		return result;
	}

	private int kthSmallestNode(TreeNode root, int k) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		int count = 0;

		TreeNode current = root;

		// traverse the tree
		while (current != null || !stack.isEmpty()) {
			while (current != null) {
				/*
				 * place pointer to a tree node on the stack before traversing
				 * the node's left subtree
				 */
				stack.push(current);
				current = current.left;
			}
			
			current = stack.pop();

			count = count + 1;
			if (count == k)
				return current.data;

			/*
			 * we have visited the node and its left subtree. Now, it's right
			 * subtree's turn
			 */
			current = current.right;
		}
		
		return -1;
	}

	// Return k largest nodes
	private ArrayList<Integer> kLargestNodes(TreeNode root, int k) {
		ArrayList<Integer> result = new ArrayList<Integer>(k);
		
		if (root == null || k == 0) 
			return result;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		int count = 0;

		TreeNode current = root;

		// traverse the tree
		while (current != null || !stack.isEmpty()) {
			while (current != null) {
				/*
				 * place pointer to a tree node on the stack before traversing
				 * the node's left subtree
				 */
				stack.push(current);
				current = current.right;
			}
			
			current = stack.pop();

			count = count + 1;
			if (count <= k)
				result.add(current.data);
			else break;

			/*
			 * we have visited the node and its left subtree. Now, it's right
			 * subtree's turn
			 */
			current = current.left;
		}
		
		return result;
	}

	private int kthLargestNode(TreeNode root, int k) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		int count = 0;

		TreeNode current = root;

		// traverse the tree
		while (current != null || !stack.isEmpty()) {
			while (current != null) {
				/*
				 * place pointer to a tree node on the stack before traversing
				 * the node's left subtree
				 */
				stack.push(current);
				current = current.right;
			}
			
			current = stack.pop();

			count = count + 1;
			if (count == k)
				return current.data;

			/*
			 * we have visited the node and its left subtree. Now, it's right
			 * subtree's turn
			 */
			current = current.left;
		}
		
		return -1;
	}

	/**
	 * @param root: a TreeNode, the root of the binary tree
	 * @return: nothing
	 * The "linked list" should be in the same order as a pre-order traversal of the binary tree
	 */
	public void flattenRecursively(TreeNode root) {
		if (root == null)
			return;
		
		if (root.left == null && root.right == null)
			return;

		if(root.left != null){
			flattenRecursively(root.left);
			
			TreeNode right = root.right;
			
			root.right = root.left;
			root.left = null;
			
			TreeNode current = root.right;
			while (current.right != null)
				current = current.right;
			
			current.right = right;
		}
		
		// now call the same function for root->right 
		flattenRecursively(root.right);
	}
	
	// Function to perform in-order traversal recursively 
	void flattenSortedOrder(TreeNode root, LinkedListNode ptr){ 
	    // Base case 
	    if (root == null) 
	        return; 
	    
	    flattenSortedOrder(root.left, ptr); 
	    
	    LinkedListNode lNode = new LinkedListNode(root.data);
	    
	    ptr.next = lNode; 
	    ptr = lNode;
	    
	    flattenSortedOrder(root.right, ptr); 
	}
	
	/*
	 * Flatten BST to sorted list | Increasing order
	 * Function to flatten binary tree using in-order traversal 
	 */
	LinkedListNode flattenSortedOrder(TreeNode root) {
		// Dummy node
		LinkedListNode dummy = new LinkedListNode(-1);

		// Pointer to previous element
		LinkedListNode ptr = dummy;

		// Calling in-order traversal
		flattenSortedOrder(root, ptr);

		return dummy.next;
	}

	public void flatten(TreeNode root) {
		TreeNode cur = root;
		Stack<TreeNode> rtrees = new Stack<TreeNode>();
		while (cur != null) {
			while (cur.left != null) {
				if (cur.right != null)
					rtrees.push(cur.right);
				cur.right = cur.left;
				cur.left = null;
				cur = cur.right;
			}
			if (cur != null && cur.right == null && !rtrees.isEmpty()) {
				cur.right = rtrees.pop();
			}
			cur = cur.right;
		}
	}
	
	// This function returns distance of x from
	// root. This function assumes that x exists
	// in BST and BST is not NULL.
	public static int distanceFromRoot(TreeNode root, TreeNode node) {
		if (root.data == node.data)
			return 0;
		else if (root.data > node.data)
			return 1 + distanceFromRoot(root.left, node);
		return 1 + distanceFromRoot(root.right, node);
	}

	// Returns minimum distance beween a and b.
	// This function assumes that a and b exist
	// in BST.
	public static int distanceBetween2nodes(TreeNode root, TreeNode node1, TreeNode node2) {
		if (root == null)
			return 0;

		// Both keys lie in left
		if (root.data > node1.data && root.data > node2.data)
			return distanceBetween2nodes(root.left, node1, node2);

		// Both keys lie in right
		if (root.data < node1.data && root.data < node2.data) // same path
			return distanceBetween2nodes(root.right, node1, node2);

		// Lie in opposite directions (Root is
		// LCA of two nodes)
		if (root.data >= node1.data && root.data <= node2.data)
			return distanceFromRoot(root, node1) + distanceFromRoot(root, node2);

		return 0;
	}
	
	// Method to flip the binary tree
	public static TreeNode flipBinaryTree(TreeNode root) {
		if (root == null)
			return root;
		if (root.left == null && root.right == null)
			return root;

		// recursively call the same method
		TreeNode flippedRoot = flipBinaryTree(root.left);

		// rearranging main root Node after returning
		// from recursive call
		root.left.left = root.right;
		root.left.right = root;
		
		root.left = null;
		root.right = null;
		return flippedRoot;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * creating a binary tree and entering the nodes
		 */
		BinaryTree bt = new BinaryTree();
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

		System.out.print("Pre Order : ");
		bt.preOrder(root);
		System.out.println("");

		System.out.print("Post Order : ");
		bt.postOrder(root);
		System.out.println("");
		
		System.out.print("Post Order Iter : ");
		bt.postOrderIter(root);
		System.out.println("");

		System.out.print("In Order : ");
		bt.inOrder(root);
		System.out.println("");
		
		System.out.print("In Order Iter : ");
		bt.inOrderIter(root);
		System.out.println("");
		
		System.out.print("In Order Iterative : ");
		bt.inOrderIter(root);
		System.out.println("");
		/**/

		System.out.println("");
		System.out.print("Height : ");
		System.out.print(bt.getHeight(root));
		
		System.out.println("");
		System.out.print("Minimum Depth BFS: ");
		System.out.print(bt.minDepthBFS(root));
		
		System.out.println("");
		System.out.print("Minimum Depth DFS: ");
		System.out.print(bt.minDepthDFS(root));
		
		System.out.println("");
		System.out.print("Maximum Depth BFS: ");
		System.out.print(bt.maxDepthBFS(root));
		
		System.out.println("");
		System.out.print("Maximum Depth DFS: ");
		System.out.print(bt.maxDepthDFS(root));

		/**/
		System.out.println("");
		System.out.print("Level Order : ");
		List<List<Integer>> levelOrder = bt.levelOrder(root, true);
		System.out.println(levelOrder);
		
		System.out.println("");
		System.out.print("Level Order Bottom Up : ");
		List<List<Integer>> levelOrderBottomUp = bt.levelOrderBottomUp(root);
		System.out.println(levelOrderBottomUp);
		
		System.out.println("");
		System.out.print("Level Order Averages : ");
		List<Double> levelOrderAvg = bt.averageOfLevels(root);
		System.out.println(levelOrderAvg);
		
		System.out.println("");
		System.out.print("Connect Nodes at Same Level : ");
		bt.connectedNodesAtSameLevel(root);

		System.out.println("");
		System.out.print("Level Order in one line : ");
		bt.levelOrder(root);
		
		System.out.println("");
		System.out.println("Alternate levels : ");
		bt.alternateLevels(root);

		System.out.println("");
		System.out.print("Spiral Order : ");
		List<List<Integer>> zigzag = bt.spiralOrder(root);
		System.out.println(zigzag);

		TreeNode parent = bt.parent(root, new TreeNode(20));
		System.out.println("");
		System.out.print("Parent Of 20 is : " + parent.data);
		System.out.println("");

		System.out.print("\n5 Smallest elements : ");
		System.out.println(bt.kSmallestNodes(root, 5));
		System.out.println("4th Smallest : " + bt.kthSmallestNode(root, 4));

		System.out.println("");
		System.out.print("5 Largest elements : ");
		System.out.println(bt.kLargestNodes(root, 5));
		System.out.println("2nd Largest : " + bt.kthLargestNode(root, 2));
		/**/

		/**/
		System.out.println("Min/Max Iteratively");
		System.out.println(bt.findMinIter(root).data);
		System.out.println(bt.findMaxIter(root).data);

		System.out.println("Min/Max Recursively");
		System.out.println(bt.findMinRecur(root));
		System.out.println(bt.findMaxRecur(root));
		/**/

		/**/
		System.out.println("");
		int sum = 50;
		if (bt.hasPathSum(root, sum))
			System.out.println("There is a root to leaf path with sum " + sum);
		else
			System.out.println("There is no root to leaf path with sum " + sum);
		/**/
		
		System.out.println("Root to leaf with sum: " + sum);

		List<List<Integer>> result = bt.pathSum(root, sum);
		System.out.println(result);
		/**/
		
		System.out.println("");

		System.out.print("Level Order : ");
		bt.levelOrder(root);

		System.out.print("\nNodes with leaves = 1: ");
		bt.kLeaves(root, 1);
		System.out.print("\nNodes with leaves = 2: ");
		bt.kLeaves(root, 2);
		System.out.print("\nNodes with leaves = 3: ");
		bt.kLeaves(root, 3);

		System.out.println("");
		TreeNode n1 = root.left.right;
		TreeNode n2 = root.right.left;
		TreeNode lca = bt.findLCA(root, n1, n2);
		if (lca != null) {
			System.out.println("LCA of " + n1.data + " and " + n2.data + " is " + lca.data);
		} else {
			System.out.println("LCA of " + n1.data + " and " + n2.data + " is NULL ");
		}
		
		System.out.println("");
		n1 = root.right.left.left;
		n2 = root.right.right;
		lca = bt.findLCA(root, n1, n2);
		if (lca != null) {
			System.out.println("LCA of " + n1.data + " and " + n2.data + " is " + lca.data);
		} else {
			System.out.println("LCA of " + n1.data + " and " + n2.data + " is NULL ");
		}
		/**/

		/*
		 * Constructed binary tree 
		 * 								4 
		 * 							   / \ 
		 * 							  2    5 
		 *                          /   \   
		 *                         1     3   
		 */
		TreeNode root1 = new TreeNode(4);
		root1.left = new TreeNode(2);
		root1.right = new TreeNode(5);
		root1.left.left = new TreeNode(1);
		root1.left.right = new TreeNode(3);

		System.out.println("Original tree is : ");
		bt.inOrder(root1);

		System.out.println("\n\nRecursively Search : "
				+ bt.findKeyRecusively(root1, 6));
		System.out.println("Iteratively Level Order Search : "
				+ bt.findKeyIterativelyLevelOrder(root1, 6));
		System.out.println("Iteratively Pre Order Search : "
				+ bt.findKeyIterativelyPreOrder(root1, 6));

		root1 = bt.mirrorImage(root1);
		System.out.println("");
		System.out.println("Inorder traversal of mirror imaged tree is : ");
		bt.inOrder(root1);

		bt.rootChildSum(root1);
		System.out.println("");
		System.out
				.println("Inorder traversal of tree where node is sum of its children is : ");
		bt.inOrder(root1);

		TreeNode rootDT = bt.doubleTree(root1);
		System.out.println("");
		System.out.println("Inorder traversal of double tree is : ");
		bt.inOrder(rootDT);

		/*
		 * Create following Binary Tree 
		 * 								1 
		 * 							   / \ 
		 * 							  2   3 
		 *                             \   \ 
		 *                              4   5 
		 *                               \ 
		 *                                6
		 */
		TreeNode root2 = new TreeNode(1);
		root2.left = new TreeNode(2);
		root2.right = new TreeNode(3);
		root2.left.right = new TreeNode(4);
		root2.right.right = new TreeNode(5);
		root2.left.right.right = new TreeNode(6);

		System.out
				.println("\n\nFollowing are nodes in top view of Binary Tree");
		//bt.printTopView(root2);
		System.out.println(bt.topView(root2));
		
		System.out
				.println("\n\nFollowing are nodes in bottom view of Binary Tree");
		//bt.printBottomView(root2);
		System.out.println(bt.bottomView(root2));

		System.out
				.println("\n\nFollowing are nodes in left view of Binary Tree");
		//bt.printLeftView(root2);
		System.out.println(bt.leftSideView(root2));

		System.out
				.println("\n\nFollowing are nodes in right view of Binary Tree");
		//bt.printRightView(root2);
		System.out.println(bt.rightSideView(root2));

		System.out.println("\n\nTree with root is balanced? - "
				+ bt.isBalanced(root));
		System.out.println("Tree with root1 is balanced? - "
				+ bt.isBalanced(root1));
		System.out.println("Tree with root2 is balanced? - "
				+ bt.isBalanced(root2));

		createLevelLinkedList(root2);
		
		System.out.println("\nDiamter of a Binary Tree");
				
		/*
		 * Create following Binary Tree 
		 * 								1 
		 * 							   / \ 
		 * 							  2    3 
		 *                          /   \
		 *                         4     5
		 */
		TreeNode root3 = new TreeNode(1);
		root3.left = new TreeNode(2);
		root3.right = new TreeNode(3);
		root3.left.left = new TreeNode(4);
		root3.left.right = new TreeNode(5);
		//root3.right.right = new TreeNode(6);
		
		System.out.println("Height: " + bt.getHeight(root3));
		System.out.println("Diameter: " + bt.diameter(root3));
		System.out.println("Diameter using max depth: " + bt.diameterUsingMaxDepth(root3));
		
		System.out.println("\nMaximum Path Sum:");
		/*
		 * Create following Binary Tree 
		 * 								1 
		 * 							   / \ 
		 * 							  2    3
		 */
		TreeNode root4 = new TreeNode(1);
		root4.left = new TreeNode(2);
		root4.right = new TreeNode(3);
		
		System.out.println("Node to Node: " + bt.maxPathSumNodeToNode(root4));
		System.out.println("Leaf to Leaf: " + bt.maxPathSumLeafToLeaf(root4));
		
		/*
		 * Create following Binary Tree 
		 * 								-10 
		 * 							   / \ 
		 * 							  9    20 
		 *                          	 /   \
		 *                         		15     -7
		 */
		TreeNode root5 = new TreeNode(-10);
		root5.left = new TreeNode(9);
		root5.right = new TreeNode(20);
		root5.right.left = new TreeNode(15);
		root5.right.right = new TreeNode(-7);
		
		System.out.println("Node to Node: " + bt.maxPathSumNodeToNode(root5));
		System.out.println("Leaf to Leaf: " + bt.maxPathSumLeafToLeaf(root5));
		
		/*
		 * Constructed binary tree is
		 * 				  -15
		 * 				/	  \
		 * 			   5	    6
		 * 			/	\	   /  \
		 * 		  -8	 1	  3	 	9
		 * 		 /	\		 /		 \
		 * 		2	 6	    26		  0
		 * 							/	\
		 * 						   4	-1
		 * 								/
		 * 							   10
		 */
		TreeNode root6 = new TreeNode(-15); 
        root6.left = new TreeNode(5); 
        root6.right = new TreeNode(6); 
        root6.left.left = new TreeNode(-8); 
        root6.left.right = new TreeNode(1); 
        root6.left.left.left = new TreeNode(2); 
        root6.left.left.right = new TreeNode(6); 
        root6.right.left = new TreeNode(3); 
        root6.right.right = new TreeNode(9); 
        root6.right.right.right = new TreeNode(0); 
        root6.right.right.right.left = new TreeNode(4); 
        root6.right.right.right.right = new TreeNode(-1); 
        root6.right.right.right.right.left = new TreeNode(10);
        System.out.println("Max pathSum of the given binary tree is " + bt.maxPathSumLeafToLeaf(root6));
        
        System.out.println("Maximum width of Binary Tree:" + bt.maxWidthBFS(root6));
        
        /*
		 * Constructed binary tree is
		 * 					  0
		 * 					/	\
		 * 				   1	  0
		 * 				/	\	 / 
		 * 			  0	  	 1	0  
		 * 			   \	/ \	 
		 * 				1  0   0 
		 */
        TreeNode root7 = new TreeNode(0); 
        root7.left = new TreeNode(1); 
        root7.right = new TreeNode(0); 
        root7.left.left = new TreeNode(0); 
        root7.left.right = new TreeNode(1); 
        root7.left.left.right = new TreeNode(1); 
        root7.left.right.left = new TreeNode(0); 
        root7.left.right.right = new TreeNode(0); 
        root7.right.left = new TreeNode(0);
        
        System.out.println("Check Valid Sequence");
        
        int arr[] = {0,5,0,1};
        System.out.println(Arrays.toString(arr) + " : " + bt.isValidSequence(root7, arr));
        
        int arr1[] = {0,0,1};
        System.out.println(Arrays.toString(arr1) + " : " + bt.isValidSequence(root7, arr1));
        
        int arr2[] = {0,1,1};
        System.out.println(Arrays.toString(arr2) + " : " + bt.isValidSequence(root7, arr2));
        
        int arr3[] = {};
        System.out.println(Arrays.toString(arr3) + " : " + bt.isValidSequence(root7, arr3));
        
        int arr4[] = {0,1,1,0};
        System.out.println(Arrays.toString(arr4) + " : " + bt.isValidSequence(root7, arr4));
        
        int arr5[] = {0};
        System.out.println(Arrays.toString(arr5) + " : " + bt.isValidSequence(root7, arr5));
	}

}
