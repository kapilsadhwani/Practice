package com.implement.tree;
public class BinaryTreeNode<T> {
  private T item;
  private BinaryTreeNode<T> leftChild;
  private BinaryTreeNode<T> rightChild;

  public BinaryTreeNode(T newItem) {
  // Initializes tree node with item and no children.
    item = newItem;
    leftChild  = null;
    rightChild = null;
  }  // end constructor
    
  public BinaryTreeNode(T newItem, 
                  BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
  // Initializes tree node with item and
  // the left and right children references.
    item = newItem;
    leftChild  = left;
    rightChild = right;
  }  // end constructor

  public T getItem() {
  // Returns the item field.
    return item;
  }  // end getItem

  public void setItem(T newItem) {
  // Sets the item field to the new value newItem.
    item  = newItem;
  }  // end setItem

  public BinaryTreeNode<T> getLeft() {
  // Returns the reference to the left child.
    return leftChild;
  }  // end getLeft

  public void setLeft(BinaryTreeNode<T> left) {
  // Sets the left child reference to left.
    leftChild  = left;
  }  // end setLeft

  public BinaryTreeNode<T> getRight() {
  // Returns the reference to the right child.
    return rightChild;
  }  // end getRight

  public void setRight(BinaryTreeNode<T> right) {
  // Sets the right child reference to right.
    rightChild  = right;
  }  // end setRight
} 