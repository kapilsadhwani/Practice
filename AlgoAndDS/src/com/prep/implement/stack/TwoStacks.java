package com.prep.implement.stack;

// Java program to implement two stacks in a 
// single array 
class TwoStacks {
	int size;
	int top1, top2;
	int arr[];

	// Constructor
	TwoStacks(int n) {
		arr = new int[n];
		size = n;
		top1 = -1;
		top2 = n;
	}

	// Method to push an element x to stack1
	void push1(int x) {
		// There is at least one empty space for new element
		if (top1 < top2 - 1) {
			arr[++top1] = x;
		} else {
			System.out.println("Stack Overflow");
			//System.exit(1);
		}
	}

	// Method to push an element x to stack2
	void push2(int x) {
		// There is at least one empty space for
		// new element
		if (top1 < top2 - 1) {
			arr[--top2] = x;
		} else {
			System.out.println("Stack Overflow");
			//System.exit(1);
		}
	}

	// Method to pop an element from first stack
	int pop1() {
		if (top1 >= 0) {
			int x = arr[top1--];
			return x;
		} else {
			System.out.println("Stack Underflow");
			//System.exit(1);
		}
		return -1;
	}

	// Method to pop an element from second stack
	int pop2() {
		if (top2 < size) {
			int x = arr[top2++];
			return x;
		} else {
			System.out.println("Stack Underflow");
			//System.exit(1);

		}
		return -1;
	}
	
	int stackSize(){
		int stackSize = 0;
		
		if(top1 >= 0)
			stackSize = stackSize + (top1 + 1);  // +1 for element at 0th index
		
		if(top2 < size)
			stackSize = stackSize + (size - top2);	// As top2 = size means stack2 is empty
		
		return stackSize;
	}

	// Driver program to test twoStack class
	public static void main(String args[]) {
		TwoStacks ts = new TwoStacks(5);
		ts.push1(5);
		ts.push2(10);
		ts.push2(15);
		ts.push1(11);
		ts.push2(7);
		
		ts.push2(9);
		System.out.println("Stack size : " + ts.stackSize());
		
		System.out.println("\nPopped element from" + " stack1 is " + ts.pop1());
		System.out.println("Stack size : " + ts.stackSize());
		
		System.out.println("\nPushed 40 : ");
		ts.push2(40);
		System.out.println("Stack size : " + ts.stackSize());
		
		System.out.println("\nPopped element from" + " stack2 is " + ts.pop2());
		System.out.println("Stack size : " + ts.stackSize());
		
		System.out.println("\nPopped element from" + " stack2 is " + ts.pop2());
		System.out.println("Popped element from" + " stack2 is " + ts.pop2());
		System.out.println("Popped element from" + " stack1 is " + ts.pop1());
		System.out.println("Popped element from" + " stack2 is " + ts.pop2());
		
		System.out.println("Popped element from" + " stack1 is " + ts.pop1());
		System.out.println("Popped element from" + " stack2 is " + ts.pop2());
		System.out.println("Stack size : " + ts.stackSize());
	}
}
