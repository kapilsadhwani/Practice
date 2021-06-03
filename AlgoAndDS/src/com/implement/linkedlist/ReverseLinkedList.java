package com.implement.linkedlist;

public class ReverseLinkedList {
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
	
	static class C { 
	    int count = 0; 
	} 
	
	ListNode insertAtHead(ListNode head, int data) {
		ListNode node = new ListNode(data);

		node.next = head;
		head = node;

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
	
	// Print all nodes of the linked list in reverse order
	static void reversePrint(ListNode head) {
		if (head == null) {
			return;
		}

		reversePrint(head.next);

		System.out.print(head.val + " ");
	}
	
	
	// Print the last k nodes of the linked list in reverse order
	static void printLastKNodes(ListNode head, C c, int k) {
		// if list is empty  
		if (head == null) {
			return;
		}
		
		// Recursive call with the next node of the list  
		printLastKNodes(head.next, c, k);

		//Count variable to keep track of the last k nodes 
		c.count++;
		
		// Print data
		if (c.count <= k){
			System.out.print(head.val + " ");
		}
	}
	
	// Reverse a LL	recursively							//			temp
	public static ListNode reverseR(ListNode head) {	//			  |
														//			  |	
		if (head == null || head.next == null) {		//		      ~
			return head;								// 		A --> B	--> NULL
		}												// 		|	  |
														//	  head  head.next
		ListNode temp = reverseR(head.next);	
														// Therefore, Reverse is head.next.next = head
		head.next.next = head;							// 		A --> B
		head.next = null;								//	  	A <==> B
														//	  head.next = null
		return temp;									//	 	A <-- B
	}									//	  return temp <-- Always point to the head of reversed LL

	/* Function to reverse the linked list iteratively */
	public static ListNode reverse(ListNode node) {
		ListNode prev = null;
		ListNode current = node;
		ListNode next = null;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;		// Don't use current.next
		}

		return prev;
	}
	
	// Reverse a Linked List from position m to n
	public static ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null || head.next == null || m == n)
			return head;
		
		int count = 1;
		/* 
		 * revStart is start of the portion of the linked list 
		 * which need to be reversed. revPrev is previous
		 * of starting position and revNext is next
		 * of end of list to be reversed. 
		 */		
		ListNode current = head;
		ListNode revPrev = null;
		ListNode revStart = null, revEnd = null;
		ListNode revNext = null;
		
		while (count < m && current != null) {
			revPrev = current;
			current = current.next;
			count++;
		}
		
		if(count < m) return head;
		
		revStart = current;	// Same as revPrev.next, count==m
		
		while (count < n && current != null) {
			current = current.next;
			count++;
		}
		
		if(count < n) return head;
		
		revEnd = current;		// count == n
		
		revNext = current.next;	// Same as revEnd.next
		
		current.next = null;		// To prevent reverse of nodes beyond n
		
		revEnd = reverse(revStart);
		
		if (revPrev != null) {
			revPrev.next = revEnd;
		} else {
			head = revEnd;
		}
		revStart.next = revNext;	// revStart will become end of reversed list
		return head;
	}
	
	// Reverse last k nodes of a Linked List
	public static ListNode reverseLastKNodes(ListNode head, int k) {
		if(head == null || head.next == null || k<=1) return head;
		
		ListNode current = head;
		ListNode trailingNode = head;
		ListNode revPrev = null;
		
		int count = 0;
		
		while (current != null) {
			current = current.next;

			if (count >= k) {
				revPrev = trailingNode;
				trailingNode = trailingNode.next;
			}
			count++;
		}
		
		if(count < k) return head;
		
		if(count == k){	// Number of nodes in LL = k. Hence reverse entire list
			return reverse(head);
		}
		
		ListNode revList = reverse(trailingNode);
		
		revPrev.next = revList;

		return head;
	}
	
	// Reverse first k nodes of a Linked List
	public static ListNode reverseFirstKNodes(ListNode head, int k) {
		if(head == null || head.next == null || k<=1) return head;
		
		ListNode prev = null;
		ListNode current = head;
		ListNode next = null;
		
		int count = 0; 
		
		// First, see if there are atleast k nodes in the linked list.
        while (count < k && current != null) {
        	current = current.next;
            count++;
        }
        
        if (count < k) {
        	return head;
        }
        
        count = 0;
		current = head;
		
		/* Reverse first k nodes of linked list */
		while (count < k) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;		// Don't use current.next
			
			count++;
		}
		
		 /* 
		  * prev will point to the kth node
		  * next is now a pointer to (k+1)th node
		  * head is 1st node in the original list 
		  */
		head.next = next;

		// prev point to the 1st node in reversed list and hence become the new head
		return prev;
	}
	
	// Reverse a Linked List in groups of given size k 
	public static ListNode reverseInKGroups(ListNode head, int k) {		
		ListNode prev = null;
		ListNode current = head;
		ListNode next = null;

		int count = 0;
		
		// First, see if there are atleast k nodes left in the linked list.
        while (count < k && current != null) {
        	current = current.next;
            count++;
        }
        
		if (count < k) return head;

		count = 0;
		current = head;

		/* Reverse first k nodes of linked list */
		while (count < k) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
			count++;
		}

		/*
		 * next is now a pointer to (k+1)th node Recursively call for the list
		 * starting from current. And make rest of the list as next of first
		 * node
		 */
		if (next != null)
			head.next = reverseInKGroups(next, k);
		else
			head.next = next;

		// prev is now head of input list
		return prev;

	}	
	
	// Swap Nodes in Pairs
	public static ListNode swapPairs(ListNode head) {
		if(head == null || head.next == null) return head;
		
		ListNode temp = swapPairs(head.next.next);
		
		ListNode result = head.next;
		head.next.next = head;
		head.next = temp;
		
		return result;
	}
	
	public static ListNode swapPairsIter(ListNode head) {
		if(head==null || head.next==null)
	        return head;
	 
	    //a fake head
		ListNode sentinel = new ListNode(0);
		sentinel.next = head;		// Attach dummy node to front of the list
	 
		ListNode prev = sentinel;
	    ListNode first = head;
	    ListNode second = head.next;
	    
	    ListNode temp = null;
	    
	    while(first != null && second != null){
	        // Save next of second node before it gets changed
	    	temp = second.next;
	        
	    	second.next = first;
	        first.next = temp;
	        prev.next = second;
	        
	        prev = first;
	        first = temp;
	 
	        if(temp != null)
	        	second = temp.next;
	    }
	 
	    return sentinel.next;
	}
	
	// Recursive function to reverse the consecutive
	// even nodes of the linked list
	static ListNode reverseEvenElements(ListNode head) {
		// Base case
		if (head == null)
			return null;

		// a fake head
		ListNode sentinel = new ListNode(0);
		ListNode ptr = sentinel;

		ListNode current = head;
		
		while (current != null) {
			if(current.val % 2 == 1){
				ptr.next = current;
				ptr = ptr.next;
				current = current.next;
			}else{
				/*
				 * We found start of the sublist to be reversed
				 * Next, we will find end of this sublist
				 */
				
				ListNode revStart = current;
				ListNode revNext = null;
				ListNode revList = null;
				
				// Keep traversing if next of current is even
				while(current != null && current.next != null && current.next.val % 2 == 0){
					current = current.next;
				}
				
				revNext = current.next;
				
				current.next = null;
				
				// Reverse of even sublist
				revList = reverse(revStart);
				
				// Attach the even list and adjust pointers
				ptr.next = revList;
				ptr = revStart;				
				current = revNext;
			}
		}
		
		return sentinel.next;
	}
	
	public static Node oddEvenList(Node head) {
		if (head == null || head.next == null) return head;
		
		Node oddptr = head, evenptr = head.next, evenHead = evenptr;
		while (evenptr != null && evenptr.next != null) {
			oddptr.next = evenptr.next;
			oddptr = oddptr.next;
			
			evenptr.next = oddptr.next;
			evenptr = evenptr.next;
		}
		oddptr.next = evenHead;
		
		return head;
	}
	
	public static ListNode reverseAlternateNodes(ListNode head) {
		 // If linked list has less than 3 nodes, no change is required 
        if (head == null || head.next == null || head.next.next == null) return head;
		
        ListNode oddptr = head, evenptr = head.next, evenHead = evenptr;
		while (evenptr != null && evenptr.next != null) {
			oddptr.next = evenptr.next;
			oddptr = oddptr.next;
			
			evenptr.next = oddptr.next;
			evenptr = evenptr.next;
		}
		
		// Same as above method except we have to reverse even List before appending
		oddptr.next = reverse(evenHead);
		
		return head;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReverseLinkedList listA = new ReverseLinkedList();
		ListNode headA = null;
		
		headA = listA.insertAtHead(headA,70);
		headA = listA.insertAtHead(headA,60);
		headA = listA.insertAtHead(headA,50);
		headA = listA.insertAtHead(headA,40);
		headA = listA.insertAtHead(headA,30);
		headA = listA.insertAtHead(headA,20);
		headA = listA.insertAtHead(headA,10);
		
		print(headA, "Original List");
		
		headA = reverseBetween(headA, 3, 6);
		print(headA, "Reverse between 3 and 6");
		
		headA = reverseBetween(headA, 3, 6);
		print(headA, "Reverse between 3 and 6");
		
		headA = reverseBetween(headA, 1, 10);
		print(headA, "Reverse between 1 and 10");
		
		headA = reverseBetween(headA, 1, 5);
		print(headA, "Reverse between 1 and 5");
		
		headA = reverseBetween(headA, 4, 7);
		print(headA, "Reverse between 4 and 7");
		
		headA = reverseBetween(headA, 4, 7);
		print(headA, "Reverse between 4 and 7");
		
		headA = reverseBetween(headA, 1, 7);
		print(headA, "Reverse between 1 and 7");
		
		headA = reverseFirstKNodes(headA, 7);
		print(headA, "Reverse First 7 nodes");
		
		headA = reverseLastKNodes(headA, 7);
		print(headA, "Reverse Last 7 nodes");
		
		headA = reverseFirstKNodes(headA, 2);
		print(headA, "Reverse First 2 nodes");
		
		headA = reverseInKGroups(headA, 3);
		print(headA, "Reverse in Groups of 3 nodes");
		
		System.out.print("[ ");
		reversePrint(headA);
		System.out.println(" ] --> Only print in Reverse Order");
		
		C c = new C();
		System.out.print("[ ");
		printLastKNodes(headA, c, 5);
		System.out.println(" ] --> Only print Last 5 elements");
		
		System.out.println("Swap in Pairs");
		headA = swapPairs(headA);
		print(headA, "After swapping in pairs recursive");
		
		headA = swapPairsIter(headA);
		print(headA, "After swapping in pairs iterative");
		
		ReverseLinkedList newList = new ReverseLinkedList();
		ListNode newHead = null;

		newHead = newList.insertAtHead(newHead, 5);
		newHead = newList.insertAtHead(newHead, 4);
		newHead = newList.insertAtHead(newHead, 3);
		newHead = newList.insertAtHead(newHead, 2);
		newHead = newList.insertAtHead(newHead, 1);
		
		print(newHead, "Original List");
		newHead = reverseInKGroups(newHead, 3);
		print(newHead, "Reverse in Groups of 3 nodes");
		
		ReverseLinkedList evenList = new ReverseLinkedList();
		ListNode evenHead = null;

		evenHead = evenList.insertAtHead(evenHead, 5);
		evenHead = evenList.insertAtHead(evenHead, 8);
		evenHead = evenList.insertAtHead(evenHead, 6);
		evenHead = evenList.insertAtHead(evenHead, 4);
		evenHead = evenList.insertAtHead(evenHead, 3);
		evenHead = evenList.insertAtHead(evenHead, 3);
		evenHead = evenList.insertAtHead(evenHead, 2);
		evenHead = evenList.insertAtHead(evenHead, 1);
		
		print(evenHead, "Original List");
		evenHead = reverseEvenElements(evenHead);
		print(evenHead, "Reverse even elements");
		
		ReverseLinkedList reverseAlternateInList = new ReverseLinkedList();
		ListNode alternateHead = null;

		alternateHead = reverseAlternateInList.insertAtHead(alternateHead, 6);
		alternateHead = reverseAlternateInList.insertAtHead(alternateHead, 5);
		alternateHead = reverseAlternateInList.insertAtHead(alternateHead, 4);
		alternateHead = reverseAlternateInList.insertAtHead(alternateHead, 3);
		alternateHead = reverseAlternateInList.insertAtHead(alternateHead, 2);
		alternateHead = reverseAlternateInList.insertAtHead(alternateHead, 1);
		
		print(alternateHead, "Original List");
		alternateHead = reverseAlternateNodes(alternateHead);
		print(alternateHead, "Reverse alternate nodes");
	}
}
