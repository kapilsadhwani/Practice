package com.implement.design;

// Java program to implement LRU cache using HashMap and DoublyLL 
import java.util.*;

public class LRUCacheMapAndDLL {
	public static class DNode{
		int key;		// Key in HashMap
	    int value;
	    DNode prev;
	    DNode next;
		
		public DNode(int key, int value){
			this.key=key;
	        this.value=value;
		}
	}

	DNode head; 
	DNode tail;
	static HashMap<Integer,DNode> map;
	int capacity;
	
	public LRUCacheMapAndDLL(int capacity) {
		this.capacity = capacity;
		this.map = new HashMap<Integer,DNode>(capacity);
	}

	// Add Node to the front	
	public void insertAtHead(DNode node){
		if(head != null){
			head.prev = node;
		}

		node.next = head;
		head = node;
		
		if (tail == null) {
			tail = head;
		}
	}

	// Add Node to the End
	/*private void addNode(DNode n) {
		if (tail != null) {
			tail.next = n;
		}
		n.prev = tail;
		n.next = null;
		tail = n;
		if (head == null) {
			head = tail;
		}
	}*/

	/*
	 *  Remove a given Node
	 *  
	 *  Adjust head, tail and node neighbor pointers
	 */
	private void removeNode(DNode node) {
		if (node.prev != null) {
			node.prev.next = node.next;
		} else {
			head = node.next;
		}

		if (node.next != null) {
			node.next.prev = node.prev;
		} else {
			tail = node.prev;
		}
	}
	
	// This function returns -1 if key is not present in map. Else it moves the key to
	// front by first removing it from DoublyLL and then adding it to the front, and returns value.
	public int get(int key) {
		if (!keyExists(key)) {
			return -1;
		}

		// Get node from Map
		DNode node = map.get(key);

		// Remove and move to the front
		removeNode(node);
		insertAtHead(node);

		return node.value;
	}

	// Overwrite data
	public void put(int key, int value) {
		// If already present, then remove it first. Note that we are going to add later
		if (keyExists(key)) {
			// Get node from Map
			DNode node = map.get(key);
			
			// Remove and move to the front
			removeNode(node);

			node.value = value;
			
			insertAtHead(node);
		} else {
			// If cache size is full, remove the least recently used.
			if (map.size() == capacity) {
				// Delete tail node
				map.remove(tail.key);
				removeNode(tail);
			}

			// add to the front
			DNode node = new DNode(key, value);
			insertAtHead(node);
			
			// Add entry in the map
			map.put(key, node);
		}
	}

	boolean keyExists(int key) {
		return map.containsKey(key);
	}

	// display contents of map
	public static void display() {
		/*Iterator<Map.Entry<Integer, DNode>> itr = map.entrySet().iterator();
		while (itr.hasNext()) {
			System.out.print(itr.next().getValue().value + " ");
		}*/
		
		for(Map.Entry<Integer, DNode> e : map.entrySet()){
			System.out.println(e.getKey() + ", " + e.getValue().value);
		}
	}

	public static void main(String[] args) {
		LRUCacheMapAndDLL cache = new LRUCacheMapAndDLL( 2 /* capacity */ );

		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));       // returns 1
		cache.put(3, 3);    					// evicts key 2
		System.out.println(cache.get(2));       // returns -1 (not found)
		cache.put(4, 4);    					// evicts key 1
		System.out.println(cache.get(1));       // returns -1 (not found)
		System.out.println(cache.get(3));       // returns 3
		System.out.println(cache.get(4));       // returns 4
		
		System.out.println("Map snapshot:");
		display();
	}
}
