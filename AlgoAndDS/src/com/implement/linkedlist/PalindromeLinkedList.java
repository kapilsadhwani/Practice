package com.implement.linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PalindromeLinkedList {
	private ListNode frontPointer;
	
	/*
	 * Time complexity : O(n), where n is the number of nodes in the Linked List.
	 * 
	 * Similar to the above approaches. Finding the middle is O(n),
	 * reversing a list in place is O(n), and then comparing the 2 resulting
	 * Linked Lists is also O(n).
	 * 
	 * Space complexity : O(1).
	 * 
	 * We are changing the next pointers for half of the nodes. This was all
	 * memory that had already been allocated, so we are not using any extra
	 * memory and therefore it is O(1).
	 * 
	 * I have seen some people on the discussion forum saying it has to be
	 * O(n) because we're creating a new list. This is incorrect, because we
	 * are changing each of the pointers one-by-one, in-place. We are not
	 * needing to allocate more than O(1) extra memory to do this work, and
	 * there is O(1) stack frames going on the stack. It is the same as
	 * reversing the values in an Array in place (using the two-pointer technique).
	 */
	public boolean isPalindrome(ListNode head) {

        if (head == null) return true;

        // Find the end of first half and reverse second half.
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // Check whether or not there is a palindrome.
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val){
            	result = false;
            	break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }        

        // Restore the list and return the result.
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    // Taken from https://leetcode.com/problems/reverse-linked-list/solution/
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    
	/*
	 * Time complexity : O(n), where n is the number of nodes in the LinkedList.
	 * 
	 * The recursive function is run once for each of the n nodes, and the body
	 * of the recursive function is O(1). Therefore, this gives a total of O(n).
	 * 
	 * Space complexity : O(n), where n is the number of nodes in the Linked List.
	 */
    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) return false;
            if (currentNode.val != frontPointer.val) return false;
            frontPointer = frontPointer.next;
        }
        return true;
    }

    public boolean isPalindromeRecursive(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }

	/*
	 * Time Complexity: O(n) 
	 * Space Complexity: O(n)
	 */
	public boolean isPalindromeUsingStack(Node head) {
		if (head == null || head.next == null)
			return true;

		Node fast = head, slow = head;

		Stack<Integer> stack = new Stack<Integer>();

		while (fast.next != null && fast.next.next != null) {
			stack.push(slow.data);
			slow = slow.next;
			fast = fast.next.next;
		}

		if(fast.next != null)
			slow = slow.next;

		while (slow != null) {
			int top = stack.pop().intValue();

			if (top != slow.data) {
				return false;
			}

			slow = slow.next;
		}

		return stack.isEmpty();
	}

	/*
	 * Time complexity : O(n), where n is the number of nodes in the Linked
	 * List.
	 * 
	 * In the first part, we're copying a Linked List into an Array List.
	 * Iterating down a Linked List in sequential order is O(n), and each of
	 * the n writes to the ArrayList is O(1). Therefore, the overall cost
	 * is O(n).
	 * 
	 * In the second part, we're using the two pointer technique to check
	 * whether or not the Array List is a palindrome. Each of the n values in
	 * the Array list is accessed once, and a total of O(n/2) comparisons
	 * are done. Therefore, the overall cost is O(n). The Python trick
	 * (reverse and list comparison as a one liner) is also O(n).
	 * 
	 * This gives O(2n) = O(n) because we always drop the constants.
	 * 
	 * Space complexity : O(n), where n is the number of nodes in the
	 * Linked List.
	 * 
	 * We are making an Array List and adding n values to it.
	 */
	public boolean isPalindromeUsingArray(ListNode head) {
		List<Integer> vals = new ArrayList<>();

		// Convert LinkedList into ArrayList.
		ListNode currentNode = head;
		while (currentNode != null) {
			vals.add(currentNode.val);
			currentNode = currentNode.next;
		}

		// Use two-pointer technique to check for palindrome.
		int front = 0;
		int back = vals.size() - 1;
		while (front < back) {
			// Note that we must use ! .equals instead of !=
			// because we are comparing Integer, not int.
			if (!vals.get(front).equals(vals.get(back))) {
				return false;
			}
			front++;
			back--;
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
