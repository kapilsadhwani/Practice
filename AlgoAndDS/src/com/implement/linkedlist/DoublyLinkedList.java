package com.implement.linkedlist;

class DoublyLinkedNode<T> {
	private T data;
	private DoublyLinkedNode<T> nextNode;
	private DoublyLinkedNode<T> previousNode;

	public DoublyLinkedNode(T data){
		this.data = data;
		this.nextNode = null;
		this.previousNode = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public DoublyLinkedNode<T> getNextNode() {
		return nextNode;
	}

	public void setNextNode(DoublyLinkedNode<T> nextNode) {
		this.nextNode = nextNode;
	}

	public DoublyLinkedNode<T> getPreviousNode() {
		return previousNode;
	}

	public void setPreviousNode(DoublyLinkedNode<T> previousNode) {
		this.previousNode = previousNode;
	}

	@Override
	public String toString(){
		return "Data:" + this.data;
	}

}

public class DoublyLinkedList<T> {
	private DoublyLinkedNode<T> head;

	public void insertAtHead(T data){
		DoublyLinkedNode<T> newNode = new DoublyLinkedNode<T>(data);

		newNode.setNextNode(this.head);

		if(this.head != null){
			this.head.setPreviousNode(newNode);
		}

		this.head = newNode;
	}
	
	public void insertAtTail(T data){
		DoublyLinkedNode<T> newNode = new DoublyLinkedNode<T>(data);
		
		if(this.head == null){
			this.head = newNode;
		}else{
			DoublyLinkedNode<T> current = this.head;
			
			while(current.getNextNode() != null){
				current = current.getNextNode();
			}
			
			current.setNextNode(newNode);
	        newNode.setPreviousNode(current);
		}
	}

	public int length(){
		int length = 0;
		DoublyLinkedNode<T> current = this.head;

		while(current != null){
			length++;
			current = current.getNextNode();
		}

		return length;
	}

	public DoublyLinkedNode<T> deleteAtStart(){
		DoublyLinkedNode<T> toDel = this.head;

		if(this.head != null)
			this.head = this.head.getNextNode();

		return toDel;
	}
	
	public DoublyLinkedNode<T> deleteNode(T data){
		if(this.head == null) return null;
		
		DoublyLinkedNode<T> current = this.head;
		
		// If node to be deleted is head node 
		if(current.getData() == data){
			this.head = this.head.getNextNode();
			return current;
		}

		do{
			current = current.getNextNode();
		}while(current != null && current.getData() != data);
		
		// Delete node not found !!!
		if(current == null) return null;
		
		// If node to be deleted is tail node, update only previous pointer
		if(current.getNextNode() == null){
			current.getPreviousNode().setNextNode(current.getNextNode());
		}else{
			current.getPreviousNode().setNextNode(current.getNextNode());
			current.getNextNode().setPreviousNode(current.getPreviousNode());
		}

		return current;
	}

	public DoublyLinkedNode<T> find(T data){
		DoublyLinkedNode<T> current = this.head;

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
		DoublyLinkedNode<T> current = this.head;

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
		DoublyLinkedList<Integer> integers = new DoublyLinkedList<Integer>();
		DoublyLinkedNode<Integer> delNode;

		integers.insertAtHead(5);
		integers.insertAtHead(10);
		integers.insertAtHead(2);
		integers.insertAtHead(12);
		integers.insertAtHead(19);
		integers.insertAtHead(20);
		
		System.out.println(integers);
		delNode = integers.deleteNode(12);
		System.out.print(integers);
		System.out.println(", Deleted Node:" +delNode.getData());
		
		delNode = integers.deleteNode(20);
		System.out.print(integers);
		System.out.println(", Deleted Node:" +delNode.getData());
		
		delNode = integers.deleteNode(5);
		System.out.print(integers);
		System.out.println(", Deleted Node:" +delNode.getData());
	}
}
