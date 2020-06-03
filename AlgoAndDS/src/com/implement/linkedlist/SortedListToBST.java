package com.implement.linkedlist;

class TreeNode{
	int data;
	TreeNode left;
	TreeNode right;
	TreeNode (int val) {data = val;}
}

class LNode{
	int data;
	LNode next;
	LNode(int val) {data = val;}
}

public class SortedListToBST {
	private static int getCount(LNode head) {
		LNode current = head;
		int count = 0;

		while (current != null) {
			count++;
			current = current.next;
		}

		return count;
	}
	
	public TreeNode sortedListToBST(LNode head){
		if(head == null){
			return null;
		}
		
		if(head.next == null){
			return new TreeNode(head.data);
		}
		
		/* Count the number of nodes in Linked List */
		int count = getCount(head);
		return constructBSTRecusrive(head, count);
	}

	private TreeNode constructBSTRecusrive(LNode head, int n) {
		/* Base Case */
        if (n == 0)  
            return null;
		
        /* Recursively construct the left subtree */
        TreeNode left = constructBSTRecusrive(head, n/2); 
  
        /* head reference now refers to middle node,  
           make middle node as root of BST*/
        TreeNode root = new TreeNode(head.data); 
  
        // Set pointer to left subtree 
        root.left = left; 
  
        /* Change head pointer of Linked List for parent  
           recursive calls */
        head = head.next; 
  
        /* Recursively construct the right subtree and link it  
           with root. The number of nodes in right subtree  is  
           total nodes - nodes in left subtree - 1 (for root) */
        root.right = constructBSTRecusrive(head, n - n / 2 - 1); 
  
        return root;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
