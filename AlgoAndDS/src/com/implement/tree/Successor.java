package com.implement.tree;
// Java program to find minimum value node in Binary Search Tree 
  
// A binary tree node 
class SNode { 
  
    int data; 
    SNode left, right, parent; 
  
    SNode(int d) { 
        data = d; 
        left = right = parent = null; 
    } 
} 
  
class Successor { 
    static SNode head; 
  
    /* Given a binary search tree and a number,  
     inserts a new node with the given number in  
     the correct place in the tree. Returns the new  
     root pointer which the caller should then use  
     (the standard trick to avoid using reference  
     parameters). */
    SNode insert(SNode root, int data) {
        /* 1. If the tree is empty, return a new, single node */
        if (root == null) { 
            return (new SNode(data)); 
        } else {
        	SNode temp = null; 
              
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
  
    SNode inOrderSuccessor(SNode root, SNode node) {  
        // step 1 of the above algorithm  
        if (node.right != null) { 
            return minValue(node.right); 
        } 
  
        // step 2 of the above algorithm 
        SNode p = node.parent; 
        while (p != null && node == p.right) { 
            node = p; 
            p = p.parent; 
        } 
        return p; 
    }
    
    static SNode predecessor;
    static SNode successor;
    static int state;
    
    // Populate in inorder way
	static void InOrderPredecessorAndSuccessor(SNode root, SNode node) {
		if(root == null) return;
		
		if (root.left != null) {
			InOrderPredecessorAndSuccessor(root.left, node);
		}

		if (state == 0) {
			if (root.data == node.data) {
				state = 1;
			} else {
				predecessor = root;
			}
		} else if (state == 1) {
			successor = root;
			state = 2;
		}

		if (root.right != null) {
			InOrderPredecessorAndSuccessor(root.right, node);
		}
	}
  
    /* Given a non-empty binary search tree, return the minimum data   
     value found in that tree. Note that the entire tree does not need 
     to be searched. */
    SNode minValue(SNode node) { 
    	if (node == null) return null;
    	SNode current = node; 
  
        /* loop down to find the leftmost leaf */
        while (current.left != null) { 
            current = current.left; 
        } 
        return current; 
    } 
  
    // Driver program to test above functions 
    public static void main(String[] args) { 
    	Successor tree = new Successor(); 
    	SNode root = null, temp = null, suc = null; 
        root = tree.insert(root, 20); 
        root = tree.insert(root, 8); 
        root = tree.insert(root, 22); 
        root = tree.insert(root, 4); 
        root = tree.insert(root, 12); 
        root = tree.insert(root, 10); 
        root = tree.insert(root, 14); 
        temp = root.left.right.right; 
        suc = tree.inOrderSuccessor(root, temp); 
        if (suc != null) { 
            System.out.println("Inorder successor of " + temp.data +  
                                                      " is " + suc.data); 
        } else { 
            System.out.println("Inorder successor does not exist"); 
        } 
        
        predecessor = null;
        successor = null;
        state = 0;
        
        InOrderPredecessorAndSuccessor(root, temp);
        
        if (predecessor != null) { 
            System.out.println("Populated predecessor of " + temp.data +  
                                                      " is " + predecessor.data); 
        } else { 
            System.out.println("Populated predecessor does not exist"); 
        }
        
        if (predecessor != null) { 
            System.out.println("Populated successor of " + temp.data +  
                                                      " is " + successor.data); 
        } else { 
            System.out.println("Populated successor does not exist"); 
        }
    } 
}