package com.implement.stack;

// Java program to sort a stack using 
// a auxiliary stack. 
import java.util.*;

class StackOps {
	// This function return the sorted stack
	public static Stack<Integer> sortStack(Stack<Integer> input) {
		Stack<Integer> tmpStack = new Stack<Integer>();
		while (!input.isEmpty()) {
			// pop out the first element
			int tmp = input.pop();

			// while temporary stack is not empty and
			// top of stack is greater than temp
			while (!tmpStack.isEmpty() && tmpStack.peek() > tmp) {
				// pop from temporary stack and
				// push it to the input stack
				input.push(tmpStack.pop());
			}

			// push temp in tempory of stack
			tmpStack.push(tmp);
		}
		return tmpStack;
	}
	
	public static void insertSorted(Stack<Integer> stack, Integer ele) {
		if(stack.size() == 0 || stack.peek() < ele){
			stack.push(ele);
			return;
		}
		
		Integer temp = stack.pop();
		
		insertSorted(stack, ele);
		
		stack.push(temp);
	}

	// This function return the sorted stack
	public static void sortByRecursion(Stack<Integer> stack) {
		if (stack.size() <= 1)
			return;

		Integer ele = stack.pop();

		sortByRecursion(stack);

		insertSorted(stack, ele);
	}
	
	public static void remove(Stack<Integer> stack, int k) {
		if(k == 1){
			stack.pop();
			return;
		}
		
		Integer ele = stack.pop();
		
		remove(stack, k - 1);
		
		stack.push(ele);
	}

	// This function deletes middle element from a stack
	public static void deleteMidElement(Stack<Integer> stack) {
		if (stack.size() == 0)
			return;
		
		int mid = stack.size() / 2 + 1;

		remove(stack, mid);
	}
	
	public static void insertReversed(Stack<Integer> stack, Integer ele) {
		if (stack.size() == 0) {
			stack.push(ele);
			return;
		}

		Integer temp = stack.pop();

		insertReversed(stack, ele);

		stack.push(temp);
	}
	
	// This function reverses a given stack
	public static void reverse(Stack<Integer> stack) {
		if (stack.size() <= 1)
			return;

		Integer ele = stack.pop();

		reverse(stack);

		insertReversed(stack, ele);
	}

	// Driver Code
	public static void main(String args[]) {
		Stack<Integer> input = new Stack<Integer>();
		input.add(34);
		input.add(3);
		input.add(31);
		input.add(98);
		input.add(92);
		input.add(23);

		// This is the temporary stack
		Stack<Integer> tmpStack = sortStack(input);
		System.out.println("Sorted numbers are:");

		while (!tmpStack.empty()) {
			System.out.print(tmpStack.pop() + " ");
		}
		
		input = new Stack<Integer>();
		input.add(34);
		input.add(3);
		input.add(31);
		input.add(98);
		input.add(92);
		input.add(23);
		
		if(input.size() > 1){
			sortByRecursion(input);
		}
		
		System.out.println("\nSorted numbers, using resursion, are:");

		while (!input.empty()) {
			System.out.print(input.pop() + " ");
		}
		
		input = new Stack<Integer>();
		input.add(34);
		input.add(3);
		input.add(31);
		input.add(98);
		input.add(92);
		input.add(23);
		
		if(input.size() > 1){
			sortByRecursion(input);
		}
		deleteMidElement(input);
		System.out.println("\nAfter removing middle element:");

		while (!input.empty()) {
			System.out.print(input.pop() + " ");
		}
		
		input = new Stack<Integer>();
		input.add(34);
		input.add(3);
		input.add(31);
		input.add(98);
		input.add(92);
		input.add(23);
		
		if(input.size() > 1){
			sortByRecursion(input);
		}
		deleteMidElement(input);
		reverse(input);
		System.out.println("\nAfter reversing the elements:");

		while (!input.empty()) {
			System.out.print(input.pop() + " ");
		}
	}
}
