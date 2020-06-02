package com.prep.implement.linkedlist;

class DoublyEndedNode<T> {
	private T data;
	private DoublyEndedNode<T> nextNode;

	public DoublyEndedNode(T data){
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public DoublyEndedNode<T> getNextNode() {
		return nextNode;
	}

	public void setNextNode(DoublyEndedNode<T> nextNode) {
		this.nextNode = nextNode;
	}

	@Override
	public String toString(){
		return "Data:" + this.data;
	}

}

public class DoublyEndedList<T> {
	private DoublyEndedNode<T> head;
	private DoublyEndedNode<T> tail;

	public void insertAtHead(T data){
		DoublyEndedNode<T> newNode = new DoublyEndedNode<T>(data);

		newNode.setNextNode(this.head);
		this.head = newNode;
	}

	public void insertAtTail(T data){
		DoublyEndedNode<T> newNode = new DoublyEndedNode<T>(data);

		if(this.head == null){
			this.head = newNode;
		}

		if(this.tail != null){
			this.tail.setNextNode(newNode);
		}

		this.tail = newNode;
	}

	public int length(){
		int length = 0;
		DoublyEndedNode<T> current = this.head;

		while(current != null){
			length++;
			current = current.getNextNode();
		}

		return length;
	}

	public DoublyEndedNode<T> deleteAtStart(){
		DoublyEndedNode<T> toDel = this.head;

		this.head = this.head.getNextNode();

		return toDel;
	}

	public DoublyEndedNode<T> find(T data){
		DoublyEndedNode<T> current = this.head;

		while(current != null){
			if(data.equals(current.getData())){
				return current;
			}

			current = current.getNextNode();
		}

		return null;
	}

	@Override
	public String toString(){
		String result = "{";
		DoublyEndedNode<T> current = this.head;

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
		DoublyEndedList<Object> dlist = new DoublyEndedList<Object>();

		dlist.insertAtTail(19);
		dlist.insertAtTail(18);
		dlist.insertAtTail(17);

		System.out.println(dlist);

	}
}
