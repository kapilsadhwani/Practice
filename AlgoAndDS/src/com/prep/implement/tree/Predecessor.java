package com.prep.implement.tree;
// Java program to find minimum value node in Binary Search Tree 
  
// A binary tree node 
class PNode { 
  
    int data; 
    PNode left, right, parent; 
  
    PNode(int d) { 
        data = d; 
        left = right = parent = null; 
    } 
} 
  
class Predecessor { 
  
    static PNode head; 
  
    /* Given a binary search tree and a number,  
     inserts a new node with the given number in  
     the correct place in the tree. Returns the new  
     root pointer which the caller should then use  
     (the standard trick to avoid using reference  
     parameters). */
    PNode insert(PNode root, int data) {
        /* 1. If the tree is empty, return a new, single node */
        if (root == null) { 
            return (new PNode(data)); 
        } else {
            PNode temp = null; 
              
            /* 2. Otherwise, recur down the tree */
            if (data <= root.data) { 
                temp = insert(root.left, data); 
                root.left = temp; 
                temp.parent = root; 
  
            } else { 
                temp = insert(root.right, data); 
                root.right = temp; 
                temp.parent = root; 
            } 
  
            /* return the (unchanged) node pointer */
            return root; 
        } 
    } 
    
	// Find parent if it is not available in Node data
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
  
    PNode inOrderPredecessor(PNode root, PNode node) { 
  
        // step 1 of the above algorithm  
        if (node.left != null) { 
            return maxValue(node.left); 
        } 
  
        // step 2 of the above algorithm 
        PNode p = node.parent; 
        while (p != null && node == p.left) { 
            node = p; 
            p = p.parent; 
        } 
        return p; 
    } 
  
    /* Given a non-empty binary search tree, return the minimum data   
     value found in that tree. Note that the entire tree does not need 
     to be searched. */
    PNode maxValue(PNode node) { 
        PNode current = node; 
  
        /* loop down to find the leftmost leaf */
        while (current.right != null) { 
            current = current.right; 
        } 
        return current; 
    } 
  
    // Driver program to test above functions 
    public static void main(String[] args) { 
    	Predecessor tree = new Predecessor(); 
        PNode root = null, temp = null, pre = null; 
        root = tree.insert(root, 20); 
        root = tree.insert(root, 18); 
        root = tree.insert(root, 30); 
        root = tree.insert(root, 26); 
        root = tree.insert(root, 40); 
        root = tree.insert(root, 22); 
        root = tree.insert(root, 28); 
        temp = root.right.left.left; 
        pre = tree.inOrderPredecessor(root, temp); 
        if (pre != null) { 
            System.out.println("Inorder predecessor of " + temp.data +  
                                                      " is " + pre.data); 
        } else { 
            System.out.println("Inorder predecessor does not exist"); 
        } 
    } 
}