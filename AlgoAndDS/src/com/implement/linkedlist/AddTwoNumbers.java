package com.implement.linkedlist;

/**
 * Definition for singly-linked list.
 */
class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class AddTwoNumbers {
    /**
     * @param l1: the first list
     * @param l2: the second list
     * @return: the sum list of l1 and l2
     */
    public ListNode addLists(ListNode l1, ListNode l2) {
    	if(l1 == null)
    		return l2;
    	if(l2 == null)
    		return l1;
    	
        int carry = 0;
        ListNode dummyNode = new ListNode(0);
        ListNode ptr = dummyNode;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                ptr.next = new ListNode((l2.val + carry) % 10);
                carry = (l2.val + carry) / 10;
                l2 = l2.next;
            } else if (l2 == null) {
                ptr.next = new ListNode((l1.val + carry) % 10);
                carry = (l1.val + carry) / 10;
                l1 = l1.next;
            } else {
                ptr.next = new ListNode((l1.val + l2.val + carry) % 10);
                carry = (l1.val + l2.val + carry) / 10;
                l1 = l1.next;
                l2 = l2.next;
            }
            ptr = ptr.next;
        }
        if (carry == 1) {
            ptr.next = new ListNode(1);
        }
        return dummyNode.next;
    }
}