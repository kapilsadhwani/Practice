package com.implement.linkedlist;

import java.util.HashSet;
import java.util.Set;

class Node {
	int data;
	Node child;
	Node next;

	Node() {
		this.next = null;
		this.child = null;
	}

	Node(int d) {
		this.data = d;
		this.child = null;
		this.next = null;
	}

	Node(Node child, int d) {
		this.data = d;
		this.child = child;
		this.next = null;
	}

	public String toString() {
		return String.valueOf(data);
	}
}

class DoublyLLNode {
	int data;
	DoublyLLNode next;
	DoublyLLNode prev;

	DoublyLLNode() {
		next = null;
		prev = null;
	}

	DoublyLLNode(int d) {
		data = d;
		next = null;
		prev = null;
	}
}

public class LinkedList {
	// Q1
	private static void print(Node head) {
		Node current = head;

		System.out.print("[ ");

		while (current != null) {
			System.out.print(current.data + " ");
			current = current.next;
		}

		System.out.println(" ]");
	}

	// Q2
	Node insertAtTail(Node head, int data) {
		Node node = new Node(data);

		if (head == null) {
			head = node;
		} else {
			Node itr = head;
			while (itr.next != null) {
				itr = itr.next;
			}

			itr.next = node;
		}

		return head;
	}

	// Q3
	Node insertAtHead(Node head, int data) {
		Node node = new Node(data);

		node.next = head;
		head = node;

		return head;
	}

	// Q4
	Node insertAtPosition(Node head, int data, int position) {
		Node node = new Node(data);

		if (head == null) {
			head = node;
		} else if (position == 0) {
			node.next = head;
			head = node;
		} else {
			Node itr = head;

			position--;

			while (position > 0 && itr.next != null) {
				position--;
				itr = itr.next;
			}

			node.next = itr.next;
			itr.next = node;
		}

		return head;
	}

	// Q5
	Node deleteNodeAt(Node head, int position) {
		Node current = (head != null) ? head.next : null;
		Node previous = head;

		if (position == 0) {
			head = current;
		} else {
			position--;
			while (position > 0 && current != null) {
				current = current.next;
				previous = previous.next;
				position--;
			}

			previous.next = current.next;
		}

		return head;
	}

	// Q6
	void reversePrint(Node head) {
		if (head == null) {
			return;
		}

		if (head.next == null) {
			System.out.print(head.data + " ");
			return;
		}

		reversePrint(head.next);

		System.out.print(head.data + " ");
	}

	// Q7 - Reverse a LL 							// 		temp
	Node reverse(Node head) { 						// 		  |
													// 		  |
		if (head == null || head.next == null) {	// 		  ~
			return head; 							// 	A --> B --> NULL
		} 											// 	| 	  |
													// head  head.next
		Node temp = reverse(head.next); 			//
													// Therefore, Reverse is head.next.next = head
		head.next.next = head; 						// A --> B becomes A <==> B
		head.next = null; 							// head.next = null will make it A <-- B
		return temp; 
	}

	/* Function to reverse the linked list iteratively */
	Node reverseIter(Node node) {
		Node prev = null;
		Node current = node;
		Node next = null;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		//node = prev;
		return prev;
	}

	// Q8 - Compare 2 Lists
	private static int compareLists(Node headA, Node headB) {
		// This is a "method-only" submission.
		// You only need to complete this method

		if (headA == null && headB == null)
			return 1;

		if (headA == null)
			return 0;

		if (headB == null)
			return 0;

		Node headAItr = headA;
		Node headBItr = headB;

		while (headAItr != null && headBItr != null) {
			if (headAItr.data != headBItr.data)
				return 0;

			headAItr = headAItr.next;
			headBItr = headBItr.next;
		}

		if (headAItr == null && headBItr == null)
			return 1;

		return 0;
	}

	// Q9
	private static Node mergeLists(Node head1, Node head2) {
		// This is a "method-only" submission.
		// You only need to complete this method
		if (head1 == null)
			return head2;
		if (head2 == null)
			return head1;

		Node newHead = null;

		if (head1.data <= head2.data) {
			newHead = head1;
			newHead.next = mergeLists(head1.next, head2);
		} else {
			newHead = head2;
			newHead.next = mergeLists(head1, head2.next);
		}

		return newHead;
	}

	// Merge 2 Lists
	private static Node mergeListsIteratively(Node head1, Node head2) {
		// This is a "method-only" submission.
		// You only need to complete this method
		if (head1 == null)
			return head2;
		if (head2 == null)
			return head1;

		Node dummyNode = new Node(0); // Dummy node to begin with new list
		Node newHead = dummyNode; // Pointer to dummy node

		while (head1 != null && head2 != null) {
			if (head1.data <= head2.data) {
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

	// Utility function to get the middle of the linked list
	/*
	 * If there are two middle nodes, return the first middle node.
	 */
	public static Node getMiddle(Node head) {
		if (head == null)
			return head;

		Node slow = head, fast = head;

		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	
	/*
	 * If there are two middle nodes, return the second middle node.
	 */
	public static Node middleNode(Node head) {
		if (head == null)
			return head;

		Node slow = head, fast = head;

		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		if(fast.next != null)
			slow = slow.next;
		return slow;
	}

	public static Node mergeSort(Node head) {
		// Base case : if head is null
		if (head == null || head.next == null) {
			return head;
		}

		// get the middle of the list
		/*
		 * If there are two middle nodes, get the first middle node.
		 */
		Node middle = getMiddle(head);
		Node nextofmiddle = middle.next;

		// set the next of middle node to null
		middle.next = null;

		// Apply mergeSort on left list
		Node left = mergeSort(head);

		// Apply mergeSort on right list
		Node right = mergeSort(nextofmiddle);

		// Merge the left and right lists
		//Node sortedlist = mergeLists(left, right);
		Node sortedlist = mergeListsIteratively(left, right);
		return sortedlist;
	}

	// Q10 - Get Nth node from the end
	private static Node getNthNodeFromEnd(Node head, int k) {
		// This is a "method-only" submission.
		// You only need to complete this method.
		Node current = head;
		Node trailingNode = head;
		int count = 0;

		while (current != null) {
			current = current.next;

			if (count >= k) {
				trailingNode = trailingNode.next;
			}

			count++;
		}
		
		// As count starts with 0-->k i.e 1st node-->null, if k>lengthOfList
		if(count < k) return null;	

		return trailingNode;
	}

	// Q11 - Remove Duplicates from Sorted LL
	private static Node removeDuplicatesFromSortedList(Node head) {
		// This is a "method-only" submission.
		// You only need to complete this method.

		if (head == null || head.next == null) {
			return head;
		}

		Node temp; // keeping it so that last node would be eligible for garbage collection
		Node previous = head;
		Node current = head.next;

		while (current != null) {
			if (previous.data != current.data) {
				previous = current;
				current = current.next;
			} else {
				previous.next = current.next;
				temp = current;
				current = current.next;
				temp.next = null; // Free node's next pointer
				temp = null;
			}
		}
		return head;
	}

	private static Node removeDuplicatesFromUnsortedList(Node head) {
		// This is a "method-only" submission.
		// You only need to complete this method.

		Set<Integer> setOfInts = new HashSet<Integer>();

		if (head == null || head.next == null) {
			return head;
		}

		Node previous = head;
		Node current = head.next;

		Node temp; // keeping it so that last node would be eligible for garbage collection
		setOfInts.add(head.data);

		while (current != null) {
			int data = current.data;

			if (setOfInts.contains(data)) {
				previous.next = current.next;
				temp = current;
				current = current.next;
				temp.next = null;
				temp = null;
			} else {
				setOfInts.add(data);
				previous = current;
				current = current.next;
			}
		}
		return head;
	}

	// Remove elements with given value
	private static Node removeElements(Node head, int val) {
		Node sentinel = new Node(0);
		sentinel.next = head;

		Node prev = sentinel, curr = head;
		Node temp; // keeping it so that last node would be eligible for garbage collection
		while (curr != null) {
			if (curr.data == val){
				prev.next = curr.next;
				
				temp = curr;
				curr = curr.next;
				
				temp.next = null;
				temp = null;
			}else{
				prev = curr;
				curr = curr.next;
			}
		}
		return sentinel.next;
	}
	
	private static Node removeElementsVer2(Node head, int val) {
		// This is a "method-only" submission.
		// You only need to complete this method.

		if (head == null)
			return head;

		Node temp; // keeping it so that last node would be eligible for garbage collection

		while (head != null && head.data == val) { // Remove if head is the element with given value
			temp = head;
			head = head.next;
			temp.next = null;
		}
		
		if (head == null)
			return head;
		
		Node previous = head;
		Node current = head.next;

		while (current != null) {
			if (current.data != val) {
				previous = current;
				current = current.next;
			} else {
				previous.next = current.next;
				temp = current;
				current = current.next;
				temp.next = null;
			}
		}
		return head;
	}

	/* Remove n’th node from the end of a Linked List */
	private static void removeNthFromEnd(Node head, int k) {
		Node node = getNthNodeFromEnd(head, k);
		if(node != null){
			deleteNode(node);
		}else{
			System.out.println(k + " is greater than the no "
					+ "of nodes in the list");
		}
	}
	
	private static Node removeNthFromEndBetterVersion(Node head, int k) {
		Node current = head;
		Node trailingNode = head;
		Node prev = null;
		int count = 0;

		while (current != null) {
			current = current.next;

			if (count >= k) {
				prev = trailingNode;
				trailingNode = trailingNode.next;
			}

			count++;
		}
		
		// As count goes from 0 --> k i.e 1st node --> null, if k > lengthOfList
		if(count < k) return head;
		
		if(prev == null){
			head = trailingNode.next;	// or head = head.next;
		}else{
			prev.next = trailingNode.next;
		}
		
		return head;
	}

	// Q12
	private static boolean hasCycle(Node head) {
		if (head == null)
			return false;

		Node slow = head, fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			// If slow and fast meet at same point then loop is present
			if (fast == slow) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Q12.1 Given a head of a linked list, detect if there is a cycle, if
	 * present return the start of cycle else return null
	 */
	private static Node startOfCycle(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			System.out.println("List is empty or No cycle detected !!!");
			return null;
		}

		Node slow = head, fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			// If slow and fast meet at same point then loop is present
			if (fast == slow) {
				break;
			}
		}
		
		if(slow != fast) return null;

		System.out.println("Cycle found !!!");

		// Calculate the length of cycle.
		// Count all nodes until Slow == Fast again
		int lengthOfCycle = 1;
		slow = slow.next;
		while (slow != fast) {
			slow = slow.next;
			lengthOfCycle++;
		} 

		// Find the start of the cycle
		slow = head;
		fast = head;

		int count = 0;
		while (count < lengthOfCycle) {
			count++;
			slow = slow.next;
		}

		// Move both (fast and slow) nodes ahead till they meet each other
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}

		return slow;
	}

	// Q13
	private static int getCount(Node head) {
		Node current = head;
		int count = 0;

		while (current != null) {
			count++;
			current = current.next;
		}

		return count;
	}

	private static Node getIntersectionNode(int d, Node longer, Node shorter) {
		Node current1 = longer;
		Node current2 = shorter;

		for (int i = 0; i < d; i++) {
			current1 = current1.next;
		}

		while (current1 != null && current2 != null) {
			if (current1 == current2)
				return current1;

			current1 = current1.next;
			current2 = current2.next;
		}

		return null;
	}

	/*
	 * function to get the intersection point of two linked lists headA and
	 * headB
	 */
	private static Node findMergeNode(Node headA, Node headB) {
		// Complete this function
		// Do not write the main method.

		/*
		 * while (current != null){ count++; current = current.next; }
		 */
		
		if(headA == null || headB == null){
			return null;
		}

		Node tailA = getTail(headA);
		Node tailB = getTail(headB);

		if (tailA != tailB)
			return null;

		int c1 = getCount(headA);
		int c2 = getCount(headB);
		int difference;

		if (c1 > c2) {
			difference = c1 - c2;
			return getIntersectionNode(difference, headA, headB);
		} else {
			difference = c2 - c1;
			return getIntersectionNode(difference, headB, headA);
		}
	}

	// Q14
	private static void print(DoublyLLNode head) {
		DoublyLLNode itr = head;

		System.out.print("[ ");

		while (itr != null) {
			System.out.print(itr.data + " ");
			itr = itr.next;
		}
		
		System.out.println(" ]");
	}

	private static DoublyLLNode insertAtTailDoubly(DoublyLLNode head, int data) {
		DoublyLLNode newNode = new DoublyLLNode(data);

		if (head == null) {
			head = newNode;
		} else {
			DoublyLLNode current = head;

			while (current.next != null) {
				current = current.next;
			}

			current.next = newNode;
			newNode.prev = current;
		}

		return head;
	}

	private static DoublyLLNode sortedInsert(DoublyLLNode head, int data) {
		/* Create Node to insert */
		DoublyLLNode newNode = new DoublyLLNode();
		newNode.data = data;

		if (head == null) { // insert in empty list
			return newNode;
		} else if (data < head.data) { // insert in front of list
			head.prev = newNode;
			newNode.next = head;
			return newNode;
		} else {
			/* Walk list with 2 pointers */
			DoublyLLNode current = head; // n2
			DoublyLLNode prev = null; // n1
			while (current != null && current.data < data) {
				prev = current;
				current = current.next;
			}

			if (current == null) { // insert at end of list
				prev.next = newNode;
				newNode.prev = prev;
			} else { // insert in empty list
				prev.next = newNode;
				current.prev = newNode;
				newNode.prev = prev;
				newNode.next = current;
			}
			return head;
		}
	}

	// Q15
	/* Function to reverse a Doubly Linked List */
	private static DoublyLLNode reverseDoubly(DoublyLLNode head) {
		if (head == null || head.next == null)
			return head;

		DoublyLLNode temp = null;
		DoublyLLNode current = head;

		/*
		 * swap next and prev for all nodes of doubly linked list
		 */
		while (current != null) {
			temp = current.prev;
			current.prev = current.next;
			current.next = temp;
			current = current.prev;
		}

		/*
		 * Before changing head, check for the cases like empty list and list
		 * with only one node
		 */
		if (temp != null) {
			head = temp.prev;
		}

		return head;
	}

	// Misc examples

	/* Find k’th node from the end of a Linked List (Using 2 pointers) */
	private static void findAndPrintNthFromLast(Node head, int k) {
		Node main_ptr = head;
		Node ref_ptr = head;

		if (head == null)
			return;

		int count = 0;

		while (count < k) { // Different from removeNthNode (One less)
			if (ref_ptr == null) {
				System.out.println(k + " is greater than the no "
						+ " of nodes in the list");
				return;
			}
			ref_ptr = ref_ptr.next; 	// 12 -> 34 -> 55 -> 26 -> 73 -> 48 -> null
			count++; 					// k=2 	  | <-- k --> |
		} 								// 	  	main 		reference

		while (ref_ptr != null) { 		// 12 -> 34 -> 55 -> 26 -> 73 -> 48 -> null
			main_ptr = main_ptr.next; 	// k=2 						| 			|
			ref_ptr = ref_ptr.next; 	// 	  					  main 		reference
		}

		if(main_ptr != null)
			System.out.println("Node no. " + k + " from last is " + main_ptr.data);
		else
			System.out.println("Invalid Input !!!");

	}

	/*
	 * Delete a node in which only a single pointer is known pointing to that node
	 */
	static void deleteNode(Node node) {
		if(node == null || node.next == null){
			node = null;
			return;
		}
		
		Node temp = node.next; // Needed so as to free space
		node.data = temp.data;
		node.next = temp.next;

		temp.next = null; // Free temp pointer
		temp = null;
	}

	/* Function to print middle of linked list */
	public static void printMiddle(Node head) {
		Node slow = getMiddle(head);
		//Node slow = middleNode(head);

		if (slow == null) {
			System.out.println("The list is empty !!!");
		} else {
			System.out.println("The middle element is [" + slow.data + "] \n");
		}
	}

	/* Function to delete middle of linked list */
	public static Node deleteMiddleNode(Node head) {
		// Base cases
		if (head == null || head.next == null) {
			return null;
		}

		// Initialize slow and fast pointers to reach
		// middle of linked list
		Node slow = head;
		Node fast = head;

		// Find the middle and previous of middle.
		Node prev = null;

		// To store previous of slow_ptr
		while (fast.next != null && fast.next.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		
		if(prev == null)
			head = slow.next;
		else prev.next = slow.next;

		return head;
	}

	public static Node getTail(Node head) {
		Node current = head;

		if (head == null || head.next == null)
			return head;
		
		while (current.next != null) {
			current = current.next;
		}

		return current;
	}

	// Flattened a linked list
	public static Node getFlattenedList(Node head) {
		Node current = head;
		Node tail = getTail(current);

		while (current != null) {
			if (current.child != null) {
				tail.next = current.child;
				tail = getTail(current.child);
			}
			current = current.next;
		}

		return head;
	}

	// Partition linked list around a value x
	public static Node partitionList(Node head, int value) {
		if(head == null || head.next == null)
			return head;
		
		/*
		 * Let us initialize first and last nodes of three linked lists 1)
		 * Linked list of values smaller than x. 2) Linked list of values equal
		 * to x. 3) Linked list of values greater than x.
		 */
		Node current = head;
		Node smallerHead = null, smallerTail = null;
		Node greaterHead = null, greaterTail = null;
		Node equalHead = null, equalTail = null;

		// Now iterate original list and connect nodes
		// of appropriate linked lists.
		while (current != null) {
			// If current node is equal to x, append it
			// to the list of x values
			if (current.data == value) {
				if (equalHead == null)
					equalHead = equalTail = current;
				else {
					equalTail.next = current;
					equalTail = current;
				}
			} else if (current.data < value) {
				// If current node is less than X, append
				// it to the list of smaller values
				
				if (smallerHead == null)
					smallerTail = smallerHead = current;
				else {
					smallerTail.next = current;
					smallerTail = current;
				}
			} else {
				// Append to the list of greater values
				if (greaterHead == null)
					greaterTail = greaterHead = current;
				else {
					greaterTail.next = current;
					greaterTail = current;
				}
			}
			current = current.next;
		}

		// Fix end of greater linked list to NULL if this list has some nodes
		if (greaterTail != null)
			greaterTail.next = null;

		// Connect three lists

		// If smaller list is empty
		if (smallerHead == null) {
			if (equalHead == null)
				return greaterHead;
			
			equalTail.next = greaterHead;
			return equalHead;
		}

		// If smaller list is not empty and equal list is empty
		if (equalHead == null) {
			smallerTail.next = greaterHead;
			return smallerHead;
		}

		// If both smaller and equal list are non-empty
		smallerTail.next = equalHead;
		equalTail.next = greaterHead;
		return smallerHead;
	}

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		Node head = null;

		System.out.println("========= Insert ===============");
		head = list.insertAtTail(head, 7);
		head = list.insertAtTail(head, 8);
		head = list.insertAtPosition(head, 4, 0);

		head = list.insertAtPosition(head, 5, 1);
		head = list.insertAtPosition(head, 6, 2);

		head = list.insertAtHead(head, 3);
		head = list.insertAtHead(head, 2);
		head = list.insertAtHead(head, 1);

		print(head);

		System.out.println("========= Delete ===============");
		head = list.deleteNodeAt(head, 7);
		System.out.print("Node at 8 : ");
		print(head);
		
		head = list.deleteNodeAt(head, 0);
		System.out.print("Node at 1 : ");
		print(head);
		
		head = list.deleteNodeAt(head, 4);
		System.out.print("Node at 5 : ");
		print(head);

		System.out.println("========= Reverse Print Only ========");
		list.reversePrint(head);

		System.out.println("\n========= Reverse LL Recursively ==============");
		head = list.reverse(head);
		print(head);
		
		System.out.println("\n========= Reverse LL Iteratively ==============");
		head = list.reverseIter(head);
		print(head);
		
		Node headA = null, headB = null;

		System.out.println("========= Compare Lists ==========");
		headA = list.insertAtHead(headA, 3);
		headA = list.insertAtHead(headA, 2);
		headA = list.insertAtHead(headA, 1);

		headB = list.insertAtHead(headB, 3);
		headB = list.insertAtHead(headB, 2);
		headB = list.insertAtHead(headB, 1);

		print(headA);
		print(headB);
		System.out.println("1=Equal 0=Otherwise: " + compareLists(headA, headB));

		System.out.println("========= Merge Lists ==========");
		headA = null;
		headB = null;

		headA = list.insertAtTail(headA, 1);
		headA = list.insertAtTail(headA, 3);
		headA = list.insertAtTail(headA, 5);
		headA = list.insertAtTail(headA, 6);

		headB = list.insertAtTail(headB, 2);
		headB = list.insertAtTail(headB, 4);
		headB = list.insertAtTail(headB, 7);

		print(headA);
		print(headB);

		Node mergedList1 = mergeLists(headA, headB);
		System.out.println("Recursive:");
		print(mergedList1);

		headA = null;
		headB = null;

		headA = list.insertAtTail(headA, 1);
		headA = list.insertAtTail(headA, 3);
		headA = list.insertAtTail(headA, 5);
		headA = list.insertAtTail(headA, 6);

		headB = list.insertAtTail(headB, 2);
		headB = list.insertAtTail(headB, 4);
		headB = list.insertAtTail(headB, 7);

		Node mergedList2 = mergeListsIteratively(headA, headB);
		System.out.println("Iterative:");
		print(mergedList2);

		System.out.println("====== Find and Print Nth From End ==========");
		headA = null;
		headA = list.insertAtTail(headA, 3);
		headA = list.insertAtTail(headA, 36);
		headA = list.insertAtTail(headA, 15);
		headA = list.insertAtTail(headA, 15);
		headA = list.insertAtTail(headA, 30);

		print(headA);
		
		findAndPrintNthFromLast(headA, 0);
		Node result;
		result = getNthNodeFromEnd(headA, 0);
		System.out.println("0th Node (Invalid input): " + 
							(result != null ? result.data : -1));

		findAndPrintNthFromLast(headA, 4);
		result = getNthNodeFromEnd(headA, 4);
		System.out.println("4th Node: " + 
							(result != null ? result.data : -1));

		findAndPrintNthFromLast(headA, 2);
		result = getNthNodeFromEnd(headA, 2);
		System.out.println("2nd Node: " + 
							(result != null ? result.data : -1));

		System.out.println("====== Remove Duplicates ==========");
		headA = null;

		headA = list.insertAtTail(headA, 3);
		headA = list.insertAtTail(headA, 2);
		headA = list.insertAtTail(headA, 2);
		headA = list.insertAtTail(headA, 4);
		headA = list.insertAtTail(headA, 3);
		headA = list.insertAtTail(headA, 4);

		System.out.println("UnSorted List:");
		print(headA);
		headA = removeDuplicatesFromUnsortedList(headA);
		print(headA);

		headB = null;

		headB = list.insertAtTail(headB, 2);
		headB = list.insertAtTail(headB, 2);
		headB = list.insertAtTail(headB, 3);
		headB = list.insertAtTail(headB, 3);
		headB = list.insertAtTail(headB, 4);
		headB = list.insertAtTail(headB, 4);

		System.out.println("Sorted List:");
		print(headB);
		headB = removeDuplicatesFromSortedList(headB);
		print(headB);

		System.out.println("====== Intersection of 2 lists ==========");
		headA = null;
		headA = list.insertAtTail(headA, 3);
		headA = list.insertAtTail(headA, 36);
		headA = list.insertAtTail(headA, 15);
		headA = list.insertAtTail(headA, 15);
		headA = list.insertAtTail(headA, 30);

		print(headA);

		headB = null;
		headB = list.insertAtTail(headB, 10);
		headB = list.insertAtTail(headB, 15);
		headB = list.insertAtTail(headB, 30);

		print(headB);

		Node merge = findMergeNode(headA, headB);	// To be fixed - Fix It

		System.out.println("The node of intersection is " + merge);

		System.out.println("====== Reverse Doubly LL ==========");
		DoublyLLNode doublyHeadA = null;
		doublyHeadA = LinkedList.insertAtTailDoubly(doublyHeadA, 3);
		doublyHeadA = LinkedList.insertAtTailDoubly(doublyHeadA, 10);
		doublyHeadA = LinkedList.insertAtTailDoubly(doublyHeadA, 15);
		doublyHeadA = LinkedList.insertAtTailDoubly(doublyHeadA, 15);
		doublyHeadA = LinkedList.insertAtTailDoubly(doublyHeadA, 30);

		print(doublyHeadA);

		doublyHeadA = reverseDoubly(doublyHeadA);
		print(doublyHeadA);

		System.out.println("====== Sorted Insert Doubly LL ==========");
		doublyHeadA = reverseDoubly(doublyHeadA);
		print(doublyHeadA);

		System.out.print("Insert 2: ");
		doublyHeadA = sortedInsert(doublyHeadA, 2);
		print(doublyHeadA);

		System.out.print("Insert 16: ");
		doublyHeadA = sortedInsert(doublyHeadA, 16);
		print(doublyHeadA);

		System.out.print("Insert 32: ");
		doublyHeadA = sortedInsert(doublyHeadA, 32);
		print(doublyHeadA);

		System.out.println("====== Remove Given Value ==========");
		headA = null;
		headA = list.insertAtTail(headA, 3);
		headA = list.insertAtTail(headA, 36);
		headA = list.insertAtTail(headA, 15);
		headA = list.insertAtTail(headA, 15);
		headA = list.insertAtTail(headA, 30);

		print(headA);
		System.out.print("Remove 3: ");
		headA = removeElements(headA, 3);
		print(headA);

		System.out.println("========== Print Middle=============");
		LinkedList listR = new LinkedList();
		Node headR = null;

		headR = listR.insertAtTail(headR, 12);
		headR = listR.insertAtTail(headR, 34);
		headR = listR.insertAtTail(headR, 55);
		headR = listR.insertAtTail(headR, 26);
		headR = listR.insertAtTail(headR, 73);
		headR = listR.insertAtTail(headR, 48);

		print(headR);
		printMiddle(headR);

		System.out.println("=== Merge Sort ========");
		print(headR);
		
		Node sortedList = mergeSort(headR);

		System.out.print("Sorted List: ");
		print(sortedList);

		System.out.println("===== Remove Nth from Last =======");
		print(headR);

		System.out.print("Remove 6th from last: ");
		removeNthFromEnd(headR, 6);
		print(headR);

		System.out.print("Remove 2nd from last: ");
		removeNthFromEnd(headR, 2);
		print(headR);

		System.out.print("Remove 3rd from last: ");
		removeNthFromEnd(headR, 3);
		print(headR);
		
		System.out.println("===== Remove Nth from Last - Another version =======");
		LinkedList newList = new LinkedList();
		Node newHead = null;

		newHead = newList.insertAtTail(newHead, 12);
		newHead = newList.insertAtTail(newHead, 34);
		newHead = newList.insertAtTail(newHead, 55);
		newHead = newList.insertAtTail(newHead, 26);
		newHead = newList.insertAtTail(newHead, 73);
		newHead = newList.insertAtTail(newHead, 48);
		
		print(newHead);

		System.out.print("Remove 6th from last: ");
		newHead = removeNthFromEndBetterVersion(newHead, 6);
		//newHead = removeNthFromEndAnotherVersion(newHead, 6);
		print(newHead);

		System.out.print("Remove 2nd from last: ");
		newHead = removeNthFromEndBetterVersion(newHead, 2);
		//newHead = removeNthFromEndAnotherVersion(newHead, 2);
		print(newHead);

		System.out.print("Remove 3rd from last: ");
		newHead = removeNthFromEndBetterVersion(newHead, 3);
		//newHead = removeNthFromEndAnotherVersion(newHead, 3);
		print(newHead);
		
		System.out.println("==== Delete Middle ======");
		print(headR);
		
		headR = deleteMiddleNode(headR);
		print(headR);
		//System.out.println("Deleted Node : " + deletedNode);
		
		headR = deleteMiddleNode(headR);
		print(headR);
		
		headR = deleteMiddleNode(headR);
		print(headR);
	}

}