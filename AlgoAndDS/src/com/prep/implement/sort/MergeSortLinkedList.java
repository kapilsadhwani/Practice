package com.prep.implement.sort;

public class MergeSortLinkedList {
	static class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

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
	
	// Merge 2 Lists
	private static ListNode mergeListsIteratively(ListNode head1, ListNode head2) {
		if (head1 == null)
			return head2;
		if (head2 == null)
			return head1;

		ListNode dummyNode = new ListNode(0); // Dummy node to begin with new list
		ListNode newHead = dummyNode; // Pointer to dummy node

		while (head1 != null && head2 != null) {
			if (head1.val <= head2.val) {
				newHead.next = head1;
				head1 = head1.next;
			} else {
				newHead.next = head2;
				head2 = head2.next;
			}
			newHead = newHead.next;
		}

		if (head1 != null) {
			newHead.next = head1;
		} else if (head2 != null) {
			newHead.next = head2;
		}

		return dummyNode.next; // Ignore dummy node
	}

	/*
	 * If there are two middle nodes, return the first middle node.
	 */
	public static ListNode getMiddle(ListNode head) {
		if (head == null)
			return head;

		ListNode slow = head, fast = head;

		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	public static ListNode mergeSort(ListNode head) {
		// Base case : if head is null
		if (head == null || head.next == null) {
			return head;
		}

		// get the middle of the list
		ListNode middle = getMiddle(head);
		ListNode nextofmiddle = middle.next;

		// set the next of middle node to null
		middle.next = null;

		// Apply mergeSort on left list
		ListNode left = mergeSort(head);

		// Apply mergeSort on right list
		ListNode right = mergeSort(nextofmiddle);

		// Merge the left and right lists
		ListNode sortedlist = mergeListsIteratively(left, right);
		return sortedlist;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MergeSortLinkedList listR = new MergeSortLinkedList();
		ListNode headR = null;

		headR = listR.insertAtTail(headR, 12);
		headR = listR.insertAtTail(headR, 34);
		headR = listR.insertAtTail(headR, 55);
		headR = listR.insertAtTail(headR, 26);
		headR = listR.insertAtTail(headR, 73);
		headR = listR.insertAtTail(headR, 48);

		System.out.println("=== Merge Sort ========");
		print(headR);
		
		ListNode sortedList = mergeSort(headR);

		System.out.print("Sorted List: ");
		print(sortedList);
	}

}
