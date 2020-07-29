package com.implement.linkedlist;

class ReorderList {
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}

		public String toString() {
			return String.valueOf(val);
		}
	}
	
	private static void print(ListNode head) {
		ListNode current = head;

		System.out.print("[ ");

		while (current != null) {
			System.out.print(current.val + " ");
			current = current.next;
		}

		System.out.println(" ]");
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
	
	/*
	 * If there are two middle nodes, return the first middle node.
	 */
	public static ListNode middleNode(ListNode head) {
		if (head == null)
			return head;

		ListNode slow = head, fast = head;

		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		return slow;
	}

	/* Function to reverse the linked list iteratively */
	public static ListNode reverse(ListNode node) {
		ListNode prev = null;
		ListNode current = node;
		ListNode next = null;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}

		return prev;
	}

	public static void reorderList(ListNode head) {
		if (head == null || head.next == null)
			return;

		// find the middle of linked list [Problem 876]
		// in 1->2->3->4->5->6 find 3
		ListNode firstHalfEnd = middleNode(head);

		// reverse the second part of the list [Problem 206]
		// convert 1->2->3->4->5->6 into 1->2->3 and 6->5->4
		// reverse the second half in-place
		ListNode secondHalfRev = reverse(firstHalfEnd.next);

		// merge two sorted linked lists [Problem 21]
		// merge 1->2->3 and 6->5->4 into 1->6->2->5->3->4
		ListNode first = head, second = secondHalfRev, next;
		while (second.next != null) {
			next = first.next;
			first.next = second;
			first = next;

			next = second.next;
			second.next = first;
			second = next;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReorderList listR = new ReorderList();
		ListNode headR = null;

		headR = listR.insertAtTail(headR, 12);
		headR = listR.insertAtTail(headR, 34);
		headR = listR.insertAtTail(headR, 55);
		headR = listR.insertAtTail(headR, 26);
		headR = listR.insertAtTail(headR, 73);
		headR = listR.insertAtTail(headR, 48);

		System.out.println("=== Merge Sort ========");
		print(headR);

		reorderList(headR);

		System.out.print("Reordered List: ");
		print(headR);
	}
}