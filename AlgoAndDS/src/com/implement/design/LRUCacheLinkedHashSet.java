package com.implement.design;

// Java program to implement LRU cache 
// using LinkedHashSet 
import java.util.*;

class LRUCacheLinkedHashSet {

	Set<Integer> cache;
	int capacity;

	public LRUCacheLinkedHashSet(int capacity) {
		this.cache = new LinkedHashSet<Integer>(capacity);
		this.capacity = capacity;
	}

	// This function returns false if key is not
	// present in cache. Else it moves the key to
	// front by first removing it and then adding
	// it, and returns true.
	public boolean get(int key) {
		if (!cache.contains(key))
			return false;
		cache.remove(key);
		cache.add(key);
		return true;
	}

	/* Refers key x with in the LRU cache */
	public void refer(int key) {
		if (get(key) == false)
			put(key);
	}

	// display contents of cache
	public void display() {
		Iterator<Integer> itr = cache.iterator();
		while (itr.hasNext()) {
			System.out.print(itr.next() + " ");
		}
	}

	public void put(int key) {
		// If already present, then
		// remove it first. Note that
		// we are going to add later
		if (cache.contains(key))
			cache.remove(key);

		// If cache size is full, remove the least
		// recently used.
		else if (cache.size() == capacity) {
			int firstKey = cache.iterator().next();
			cache.remove(firstKey);
		}

		cache.add(key);
	}

	public static void main(String[] args) {
		LRUCacheLinkedHashSet ca = new LRUCacheLinkedHashSet(4);
		ca.refer(1);
		ca.refer(2);
		ca.refer(3);
		ca.refer(1);
		ca.refer(4);
		ca.refer(5);
		ca.display();
	}
}
