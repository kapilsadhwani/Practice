package com.prep.implement.linkedlist;


class LinkedListRotateRight {
	/**
	 * Definition for singly-linked list.
	 */
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
	ListNode insertAtTail(ListNode head, int data) {
		ListNode node = new ListNode(data);

		if (head == null) {
			head = node;
		} else {
			ListNode itr = head;
			while (itr.next != null) {
				itr = itr.next;
			}

			itr.next = node;
		}

		return head;
	}
	
	private static void print(ListNode head, String msg) {
		ListNode current = head;

		System.out.print("[ ");

		while (current != null) {
			System.out.print(current.val + " ");
			current = current.next;
		}

		System.out.println(" ] --> " + msg);
	}
	
	public ListNode rotateRight(ListNode head, int k) {
		// base cases
		if (head == null || head.next == null || k == 0)
			return head;

		// close the linked list into the ring
		ListNode old_tail = head;
		int length = 1;
		
		for (length = 1; old_tail.next != null; length++)
			old_tail = old_tail.next;
		
		// If k > = length, take mod
		k = k % length;
		if (k == 0)
			return head;

		// Connect tail node to head
		old_tail.next = head;

		// find new tail : (n - k % n - 1)th node and new head : (n - k % n)th node
		ListNode new_tail = head;
		for (int i = 0; i < length - k - 1; i++)
			new_tail = new_tail.next;
		ListNode new_head = new_tail.next;

		// break the ring
		new_tail.next = null;

		return new_head;
	}
	
	public ListNode rotateRightCustom(ListNode head, int k) {
		// base cases
		if (head == null || head.next == null || k == 0)
			return head;
		
		// Calculate length of the list
		ListNode current = head;
		int length = 0;

		while (current != null) {
			length++;
			current = current.next;
		}

		// If k > = length, take mod
		k = k % length;
		if (k == 0) return head;
		
		current = head; 
		
		/*
		 * Here,
		 * trailingNode will be newHead
		 * prevTrailingNode will be newTail
		 * prevCurr = oldTail
		 */
		ListNode prevCurr = null;
		ListNode trailingNode = head, prevTrailingNode = null; 
		int count = 0;

		while (current != null) {
			prevCurr = current;
			current = current.next;

			if (count >= k) {
				prevTrailingNode = trailingNode;
				trailingNode = trailingNode.next;
			}

			count++;
		}
		
		/*
		 * Update pointers and return trailingNode
		 * 
		 * prevTrailingNode --> NULL
		 * prevCurr --> oldHead
		 */
		
		prevTrailingNode.next = null;
		prevCurr.next = head;

		return trailingNode;
	}
	
	public static void main(String[] args) {
		LinkedListRotateRight newList = new LinkedListRotateRight();
		ListNode newHead = null;

		newHead = newList.insertAtTail(newHead, 1);
		newHead = newList.insertAtTail(newHead, 2);
		newHead = newList.insertAtTail(newHead, 3);
		newHead = newList.insertAtTail(newHead, 4);
		newHead = newList.insertAtTail(newHead, 5);
		
		int k = 2;
		
		print(newHead, "Original List");
		newHead = newList.rotateRight(newHead, k);
		print(newHead, "New List after right rotate " + k);
		
		//Revert above rotate
		newHead = newList.rotateRight(newHead, k=3);
		print(newHead, "Original List");
		newHead = newList.rotateRightCustom(newHead, k=2);
		print(newHead, "New List after right rotate " + k);
		
		ListNode newHead1 = null;
		newHead1 = newList.insertAtTail(newHead1, 0);
		newHead1 = newList.insertAtTail(newHead1, 1);
		newHead1 = newList.insertAtTail(newHead1, 2);
		
		k = 4;
		
		print(newHead1, "Original List");
		newHead1 = newList.rotateRight(newHead1, k);
		print(newHead1, "New List after right rotate " + k);
		
		//Revert above rotate
		newHead1 = newList.rotateRight(newHead1, k=2);
		print(newHead1, "Original List");
		newHead1 = newList.rotateRightCustom(newHead1, k=4);
		print(newHead1, "New List after right rotate " + k);
	}
}