package com.prep.implement.queue;

// A Java program to demonstrate implementation of k queues in a single 
// array in time and space efficient way 
public class KQueues {
	int[] front;
	int[] rear;
    int[] queueData;
    int[] nextIndex;

    int nextAvailable;

	KQueues(int numQueues, int capacity) {

		// Initialize capacity and numQueues, and allocate memory for all arrays
		this.queueData = new int[capacity];
		this.nextIndex = new int[capacity];
		this.front = new int[numQueues];
		this.rear = new int[numQueues];
		
		// Initialize all queues as empty
		for (int i = 0; i < numQueues; i++) {
			front[i] = rear[i] = -1;
		}

		// Initialize all spaces as free
		nextAvailable = 0;
		
		for (int i = 0; i < nextIndex.length - 1; i++) {
			nextIndex[i] = i + 1;
		}
		nextIndex[nextIndex.length - 1] = -1;

	}

	// To check whether queue number 'i' is empty or not
	private boolean isEmpty(int queue) {
		return front[queue] == -1;
	}

	// To dequeue an from queue number 'i' where i is from 0 to k-1
	private boolean isFull() {
		return nextAvailable == -1;
	}

	// To enqueue an item in queue number 'j' where j is from 0 to k-1
	private void enqueue(int queue, int value) {
		if (isFull()) {
			System.out.println("queue overflow");
			return;
		}

		// We are going to set nextIndex[nextAvailable] = -1 to indicate end of queue
		int nextFree = nextIndex[nextAvailable];

		if (isEmpty(queue)) {
			rear[queue] = front[queue] = nextAvailable;
		} else {
			// Update next of rear and then rear for queue number 'j'
			nextIndex[rear[queue]] = nextAvailable;
			rear[queue] = nextAvailable;
		}
		
		// Indicates end of queue
		nextIndex[nextAvailable] = -1;

		// Put the item in array
		queueData[nextAvailable] = value;

		// Update index of free slot to index of next slot in free list
		nextAvailable = nextFree;
	}

	// To dequeue an from queue number 'i' where i is from 0 to k-1
	private int dequeue(int queue) {
		// Underflow checkSAS
		if (isEmpty(queue)) {
			System.out.println("Queue underflow");
			return Integer.MIN_VALUE;
		}

		// Find index of front item in queue number 'i'
		int frontIndex = front[queue];

		// Change front to store next of previous front
		front[queue] = nextIndex[frontIndex];

		/* Attach the previous front to the beginning of free list
		 * To avoid hole in the array, we will use current freed index as nextAvailable
		 * and hence current nextAvailable will go to nextIndex[] at current freed position
		 */
		nextIndex[frontIndex] = nextAvailable;
		nextAvailable = frontIndex;

		return queueData[frontIndex];
	}
	
	public static void main(String[] args) {
		// Let us create 3 queue in an array of size 10
		int k = 3, n = 10;
		KQueues ks = new KQueues(k, n);

		// Let us put some items in queue number 2
		ks.enqueue(2, 15);
		ks.enqueue(2, 45);

		// Let us put some items in queue number 1
		ks.enqueue(1, 17);
		ks.enqueue(1, 49);
		ks.enqueue(1, 39);

		// Let us put some items in queue number 0
		ks.enqueue(0, 11);
		ks.enqueue(0, 9);
		ks.enqueue(0, 7);

		System.out.println("Dequeued element from queue 2 is " + ks.dequeue(2));
		System.out.println("Dequeued element from queue 1 is " + ks.dequeue(1));
		System.out.println("Dequeued element from queue 0 is " + ks.dequeue(0));
		
		System.out.println("Dequeued element from queue 2 is " + ks.dequeue(2));
		System.out.println("Dequeued element from queue 1 is " + ks.dequeue(1));
		System.out.println("Dequeued element from queue 0 is " + ks.dequeue(0));

	}

}
