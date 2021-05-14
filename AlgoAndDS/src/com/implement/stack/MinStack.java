package com.implement.stack;

import java.util.Stack;

class MinStack {
	long minElement;
	Stack<Long> stack;

	/** initialize your data structure here. */
	public MinStack() {
		stack = new Stack<Long>();
		minElement = Long.MAX_VALUE;
	}

	public void push(int x) {
		if (stack.size() == 0) {
			stack.push((long)x);
			minElement = (long)x;
		} else if(x >= minElement){
			stack.push((long)x);
		} else {
			stack.push(2 * (long)x - minElement);
			minElement = (long)x;
		}
	}

	public void pop() {
		if(stack.size() == 0) return;

		if(stack.peek() >= minElement){
			stack.pop();
		}else{
			minElement = 2 * minElement - stack.peek();
			stack.pop();
		}
	}

	public int top() {	// or peek
		if(stack.size() == 0) return -1;

		if(stack.peek() >= minElement){
			return (int) stack.peek().longValue();
		}
		return (int) minElement;
	}

	public int getMin() {
		if(stack.size() == 0)
			return -1;
		return (int) minElement;
	}
	
	public static void main(String[] args) {
		MinStack ms = new MinStack();
		
		ms.push(6);
		ms.push(5);
		ms.push(5);
		ms.push(7);
		
		/*ms.push(2147483646);
		ms.push(2147483646);
		ms.push(2147483647);*/
		
		System.out.println(ms.top());
		ms.pop();
		System.out.println(ms.getMin());
		ms.pop();
		System.out.println(ms.getMin());
		ms.pop();
		System.out.println(ms.getMin());
		
		/*ms.push(2147483647);
		System.out.println(ms.top());
		System.out.println(ms.getMin());
		
		ms.push(-2147483648);
		System.out.println(ms.top());
		System.out.println(ms.getMin());
		ms.pop();
		System.out.println(ms.getMin());*/
	}
}