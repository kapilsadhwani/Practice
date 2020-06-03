package com.implement.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class KTreeNode{
    // Members
    int data;
    public List<KTreeNode> child;
 
    // Constructor
    public KTreeNode(int data){
        this.data = data;
        child = new ArrayList<KTreeNode>();
    }
}

public class K_aryTree {
	
	// Q1
	private int getHeight(KTreeNode root) {
		if(root == null) return 0;
		
		int maxHeight = Integer.MIN_VALUE, height=0;
		
		for(int i=0; i<root.child.size();i++){
			height = getHeight(root.child.get(i));
			if(height > maxHeight) 
				maxHeight = height;
		}
		
		return maxHeight + 1;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* creating a binary tree and entering 
        the nodes */
		K_aryTree bt = new K_aryTree();
		KTreeNode root = null;
		
		// TBD Construct K-ary Tree
		/* Constructed binary tree is
			          25
			      	/     \
			     15         35
			    /  \	   /  \
			  10    20	  30   40
		*/
	}
}
