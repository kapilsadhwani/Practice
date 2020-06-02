package com.prep.implement.linkedlist;

class LinkedNode<T> {
	private T data;
	private LinkedNode<T> nextNode;

	public LinkedNode(T data){
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public LinkedNode<T> getNextNode() {
		return nextNode;
	}

	public void setNextNode(LinkedNode<T> nextNode) {
		this.nextNode = nextNode;
	}

	@Override
	public String toString(){
		return "Data:" + this.data;
	}

}

public class GenericLinkedList<T> {
	private LinkedNode<T> head;

	public void insertAtHead(T data){
		LinkedNode<T> newNode = new LinkedNode<T>(data);

		newNode.setNextNode(this.head);
		this.head = newNode;
	}

	public int length(){
		int length = 0;
		LinkedNode<T> current = this.head;

		while(current != null){
			length++;
			current = current.getNextNode();
		}

		return length;
	}

	public LinkedNode<T> deleteAtStart(){
		LinkedNode<T> toDel = this.head;

		this.head = this.head.getNextNode();

		return toDel;
	}

	public LinkedNode<T> find(T data){
		LinkedNode<T> current = this.head;

		while(current != null){
			if(data.equals(current.getData())){
				return current;
			}

			current = current.getNextNode();
		}

		return null;
	}

	public void prepend(int k){
		/* If linked list is empty or it contains only
        one node then simply return. */
        if(head == null || head.getNextNode() == null || k <= 0)
           return;

	     /* Initialize second last and last pointers */
	     LinkedNode<T> prev = null;
	     LinkedNode<T> current = head;

	     for (int i=0;i<k;i++) {
	    	 current = current.getNextNode();
	    	 if (current == null)
	    		 return;
	     }
	     prev = head;

	     /* After this loop prev and current nodes are k apart */
	     while (current.getNextNode() != null){
	        current = current.getNextNode();
	        prev = prev.getNextNode();
	     }

	     current.setNextNode(head);
	     head = prev.getNextNode();
	     prev.setNextNode(null);
	}

	@Override
	public String toString(){
		String result = "{";
		LinkedNode<T> current = this.head;

		while(current != null){
			result += current.toString() + " -> ";
			current = current.getNextNode();
		}
		result += "}";

		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericLinkedList<Object> list = new GenericLinkedList<Object>();

		list.insertAtHead(5);
		list.insertAtHead(10);
		list.insertAtHead(2);
		list.insertAtHead(12);
		list.insertAtHead(19);
		list.insertAtHead(20);

		//Node<Object> toDel = list.deleteAtStart();

		System.out.println(list);
		//System.out.println("Length : " + list.length());

		//System.out.println("Found : " + list.find(12.5));
		//System.out.println("Found : " + list.find(15));

		list.prepend(3);

		System.out.println(list);

	}
}
