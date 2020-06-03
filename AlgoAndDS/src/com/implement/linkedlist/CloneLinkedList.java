package com.implement.linkedlist;

// Java program to clone a linked list with random pointers
import java.util.HashMap;
import java.util.Map;

// Linked List Node class
class CloneNode {
	int data;// Node data
	CloneNode next, random;// Next and random reference

	// Node constructor
	public CloneNode(int data) {
		this.data = data;
		this.next = this.random = null;
	}
}

// linked list class
public class CloneLinkedList {
	CloneNode head;// Linked list head reference

	// Linked list constructor
	public CloneLinkedList(CloneNode head) {
		this.head = head;
	}

	// Insert always at the head in the linked list.
	public void insertAtHead(int data) {
		CloneNode node = new CloneNode(data);
		node.next = this.head;
		this.head = node;
	}

	// Method to print the list.
	void print() {
		CloneNode current = head;
		while (current != null) {
			CloneNode random = current.random;
			int randomData = (random != null) ? random.data : -1;
			System.out.println("Data = " + current.data + ", Random data = "
					+ randomData);
			current = current.next;
		}
	}

	// Actual clone method which returns head
	// reference of cloned linked list.
	public CloneLinkedList clone() {
		// Initialize two references, one with original
		// list's head.
		CloneNode origCurr = this.head, cloneCurr = null;

		// Hash map which contains node to node mapping of
		// original and clone linked list.
		Map<CloneNode, CloneNode> map = new HashMap<CloneNode, CloneNode>();

		// Traverse the original list and make a copy of that
		// in the clone linked list.
		while (origCurr != null) {
			// Reference pointers will be updated
			cloneCurr = new CloneNode(origCurr.data);
			map.put(origCurr, cloneCurr); // in second iteration
			origCurr = origCurr.next;
		}

		// Adjusting the original list reference again.
		origCurr = this.head;

		// Traversal of original list again to adjust the next
		// and random references of clone list using hash map.
		while (origCurr != null) {
			cloneCurr = map.get(origCurr);
			cloneCurr.next = map.get(origCurr.next);
			cloneCurr.random = map.get(origCurr.random);
			origCurr = origCurr.next;
		}

		// return the head reference of the clone list.
		CloneLinkedList cloneListHead = new CloneLinkedList(map.get(this.head));
		return cloneListHead;
	}

	// Main method.
	public static void main(String[] args) {
		// Pushing data in the linked list.
		CloneLinkedList list = new CloneLinkedList(new CloneNode(5));
		list.insertAtHead(4);
		list.insertAtHead(3);
		list.insertAtHead(2);
		list.insertAtHead(1);

		// Setting up random references.
		list.head.random = list.head.next.next;
		list.head.next.random = list.head.next.next.next;
		list.head.next.next.random = list.head.next.next.next.next;
		list.head.next.next.next.random = list.head.next.next.next.next.next;
		list.head.next.next.next.next.random = list.head.next;

		// Making a clone of the original linked list.
		CloneLinkedList clone = list.clone();

		// Print the original and cloned linked list.
		System.out.println("Original linked list");
		list.print();
		System.out.println("\nCloned linked list");
		clone.print();
	}
}