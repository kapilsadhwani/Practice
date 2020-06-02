package com.prep.implement.linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}

	}

	private ListNode insertAtTail(ListNode head, int data) {
		ListNode node = new ListNode(data);

		if (head == null) {
			head = node;
		} else {
			ListNode current = head;
			while (current.next != null) {
				current = current.next;
			}

			current.next = node;
		}

		return head;
	}

	public static ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}

		PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(
				lists.length, new Comparator<ListNode>() {
					public int compare(ListNode n1, ListNode n2) {
						return n1.val - n2.val;
					}
				});

		ListNode dummyNode = new ListNode(0); // Dummy node to begin with new
												// list
		ListNode head = dummyNode; // Pointer to dummy node

		// Insert first node of each list
		for (ListNode node : lists) {
			if (node != null) {
				queue.offer(node);
			}
		}

		ListNode n = null;

		while (!queue.isEmpty()) {
			n = queue.poll();
			head.next = n;

			if (n.next != null) {
				queue.offer(n.next);
			}

			head = head.next;
		}

		return dummyNode.next;
	}

	private static void print(ListNode head) {
		ListNode itr = head;

		while (itr != null) {
			System.out.print(itr.val + " ");
			itr = itr.next;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MergeKSortedLists listA = new MergeKSortedLists();
		MergeKSortedLists listB = new MergeKSortedLists();
		MergeKSortedLists listC = new MergeKSortedLists();
		MergeKSortedLists listD = new MergeKSortedLists();

		ListNode headA = null, headB = null, headC = null, headD = null;

		headA = listA.insertAtTail(headA, 11);
		headA = listA.insertAtTail(headA, 12);
		headA = listA.insertAtTail(headA, 13);
		headA = listA.insertAtTail(headA, 14);
		headA = listA.insertAtTail(headA, 15);

		headB = listB.insertAtTail(headB, 1);
		headB = listB.insertAtTail(headB, 3);
		headB = listB.insertAtTail(headB, 5);

		headC = listC.insertAtTail(headC, 2);
		headC = listC.insertAtTail(headC, 7);
		headC = listC.insertAtTail(headC, 9);
		headC = listC.insertAtTail(headC, 10);

		headD = listD.insertAtTail(headD, 4);
		headD = listD.insertAtTail(headD, 6);
		headD = listD.insertAtTail(headD, 8);

		ListNode[] lists = { headA, headB, headC, headD };

		ListNode mergedHead = mergeKLists(lists);
		print(mergedHead);
	}

}
