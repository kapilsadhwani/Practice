package com.prep.implement.tree;
// Java program to print all ancestors of a given key 
import java.util.Stack; 
  
public class Ancestors{
	// Iterative Function to print all ancestors of a given key 
    static void printAncestors(TreeNode root,int key) { 
        if(root == null) 
            return; 
          
         // Create a stack to hold ancestors 
        Stack<TreeNode> stack = new Stack<>(); 
        TreeNode current = root;
          
        // Traverse the complete tree in inorder iterative way till we find the key 
        while(current != null || !stack.isEmpty()) {              
            // Traverse the left side. While traversing, push the nodes into 
            // the stack so that their right subtrees can be traversed later 
            while(current != null && current.data != key) { 
            	stack.push(current);   	  // push current node 
            	current = current.left;   // move to next node 
            } 
              
            // If the node whose ancestors are to be printed is found, 
            // then break the while loop. 
            if(current != null && current.data == key) 
                break; 
              
            // Check if right sub-tree exists for the node at top 
            // If not then pop that node because we don't need this 
            // node any more. 
            if(!stack.isEmpty() && stack.peek().right == null){                 
                current = stack.pop();
                
                // If the popped node is right child of top, then remove the top 
                // as well. Left child of the top must have processed before. 
            	
                while( !stack.isEmpty() && stack.peek().right == current) { 
                	current = stack.pop(); 
                } 
            } 
              
            // if stack is not empty then simply set the current as right child 
            // of top and start traversing right sub-tree. 
            current = stack.isEmpty() ? null : stack.peek().right; 
        } 
          
        // If stack is not empty, print contents of stack 
        // Here assumption is that the key is there in tree 
        while( !stack.isEmpty() ) { 
        	TreeNode temp = stack.pop(); 
            System.out.print(temp.data + " ");
        } 
    } 
    
    // Recursive Function to print all ancestors of a given key 
    static boolean printAncestorsR(TreeNode root,int key) { 
    	/* base cases */
        if (root == null) 
            return false; 
   
        if (root.data == key) 
            return true; 
   
        /* If target is present in either left or right subtree  
           of this node, then print this node */
        if (printAncestorsR(root.left, key) 
                || printAncestorsR(root.right, key))  { 
            System.out.print(root.data + " "); 
            return true; 
        } 
   
        /* Else return false */
        return false; 
    }
      
    // Driver program to test above functions 
    public static void main(String[] args) { 
        // Let us construct a binary tree 
    	TreeNode root = new TreeNode(1); 
        root.left = new TreeNode(2); 
        root.right = new TreeNode(3); 
        root.left.left = new TreeNode(4); 
        root.left.right = new TreeNode(5); 
        root.right.left = new TreeNode(6); 
        root.right.right = new TreeNode(7); 
        root.left.left.left = new TreeNode(8); 
        root.left.right.right = new TreeNode(9); 
        root.right.right.left = new TreeNode(10); 
          
        System.out.println("Following are all keys and their ancestors");
        System.out.println("Recursive : ");
        for(int key = 1;key <= 10;key++){ 
            System.out.print(key+": "); 
            printAncestors(root, key); 
            System.out.println(); 
        }
        
        System.out.println("\nIterative : ");
        for(int key = 1;key <= 10;key++){ 
            System.out.print(key+": "); 
            printAncestors(root, key);
            System.out.println(); 
        } 
    } 
  
}