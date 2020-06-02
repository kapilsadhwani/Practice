package com.prep.implement.tree;
public class TreeNode{
    // Members
    int data;
    public TreeNode left, right, nextRight;
 
    // Constructor
    public TreeNode(int data){
        this.data = data;
        this.left = this.right = this.nextRight = null;
    }   
}