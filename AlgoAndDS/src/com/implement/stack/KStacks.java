package com.implement.stack;

import java.util.Arrays;

// Java program to demonstrate implementation of k stacks in a single 
// array in time and space efficient way 

public class KStacks {
	// A Java class to represent k stacks in a single array of size n
	static class KStack {
		int[] topOfStack;
	    int[] stackData;
	    int[] nextIndex;

	    int nextAvailable;

		// constructor to create k stacks in an array of size capacity
		KStack(int numStacks, int capacity) {
			// Initialize n and k, and allocate memory for all arrays
			topOfStack = new int[numStacks];
	        
			// Initialize all stacks as empty
			/*for (int i = 0; i < topOfStack.length; i++)
				topOfStack[i] = -1;*/
			Arrays.fill(topOfStack, -1);
			
			stackData = new int[capacity];
			
	        nextIndex = new int[capacity];
			// Initialize all spaces as free
			nextAvailable = 0;
			
			for (int i = 0; i < nextIndex.length - 1; i++)
				nextIndex[i] = i + 1;
			nextIndex[nextIndex.length - 1] = -1; // -1 is used to indicate end of free list
		}

		// A utility function to check if there is space available
		boolean isFull() {
			return (nextAvailable == -1);
		}

		// To check whether stack number 'stack' is empty or not
		boolean isEmpty(int stack) {
			return (topOfStack[stack] == -1);
		}

		// To push an item in stack number 'stack' where stack is from 0 to numStacks-1
		void push(int stack, int value) {
			if (stack < 0 || stack >= topOfStack.length) {
	            throw new IndexOutOfBoundsException();
	        }
			
			// Overflow check
			if (isFull()) {
				System.out.println("Stack Overflow");
				return;
			}

			int currentIndex = nextAvailable; // Store index of first free slot

			// Update index of free slot to index of next slot in free list
			nextAvailable = nextIndex[currentIndex];

			// Update next of top and then top for stack number 'stack'
			nextIndex[currentIndex] = topOfStack[stack];
	        topOfStack[stack] = currentIndex;

			// Put the item in array
	        stackData[currentIndex] = value;
		}

		// To pop an from stack number 'stack' where stack is from 0 to numStacks-1
		int pop(int stack) {
			if (stack < 0 || stack >= topOfStack.length) {
	            throw new IndexOutOfBoundsException();
	        }
			
			// Underflow check
			if (isEmpty(stack)) {
				System.out.println("Stack Underflow");
				return Integer.MAX_VALUE;
			}

			// Find index of top item in stack number 'stack'
			int currentIndex = topOfStack[stack];

			topOfStack[stack] = nextIndex[currentIndex]; // Change top to store next of previous top

			// Attach the previous top to the beginning of free list
			nextIndex[currentIndex] = nextAvailable;
	        nextAvailable = currentIndex;

			// Return the previous top item
	        return stackData[currentIndex];
		}

	}

	// Driver program
	public static void main(String[] args) {
		// Let us create 3 stacks in an array of size 10
		int k = 3, n = 10;

		KStack ks = new KStack(k, n);

		ks.push(2, 15);
		ks.push(2, 45);

		// Let us put some items in stack number 1
		ks.push(1, 17);
		ks.push(1, 49);
		ks.push(1, 39);

		// Let us put some items in stack number 0
		ks.push(0, 11);
		ks.push(0, 9);
		ks.push(0, 7);

		System.out.println("Popped element from stack 2 is " + ks.pop(2));
		System.out.println("Popped element from stack 1 is " + ks.pop(1));
		System.out.println("Popped element from stack 0 is " + ks.pop(0));
	}
}