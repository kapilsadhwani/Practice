package com.prep.implement.tree;
class LinkedListNode {
	int data;
	LinkedListNode child;
	LinkedListNode next;

	LinkedListNode() {
		this.next = null;
		this.child = null;
	}

	LinkedListNode(int d) {
		this.data = d;
		this.child = null;
		this.next = null;
	}

	LinkedListNode(LinkedListNode child, int d) {
		this.data = d;
		this.child = child;
		this.next = null;
	}

	public String toString() {
		return String.valueOf(data);
	}
}