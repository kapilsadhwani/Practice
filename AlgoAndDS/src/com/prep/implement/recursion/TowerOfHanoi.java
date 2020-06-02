package com.prep.implement.recursion;

import java.util.Stack;

public class TowerOfHanoi {
	private Stack<Integer> stackOfDisks;
	private int index;
	public TowerOfHanoi(int i) {
		stackOfDisks = new Stack<Integer>();
		index = i;
	}

	public int getIndex() {
		return index;
	}
	
	// If top disk in Stack is smaller than disk to be placed, raise error
	private void add(int disk) {
		if (!stackOfDisks.isEmpty() && stackOfDisks.peek() <= disk) {
			System.out.println("Error placing disk" + disk);
		} else {
			stackOfDisks.push(disk);
		}
	}

	private void moveTopTo(TowerOfHanoi destination) {
		int top = stackOfDisks.pop();
		
		System.out.println("Moving disc " + top + " from Tower[" + this.getIndex() + "] to Tower[" + destination.getIndex() + "]");
		
		destination.add(top);
	}
	
	public void moveDisks(int n, TowerOfHanoi destination, TowerOfHanoi buffer){
		/* Base Case */
		if(n <= 0 ) return;
		
		/* Move top n-1 disks from origin (this) to buffer, using destination as a buffer */
		this.moveDisks(n-1, buffer, destination);
		
		/* Move top from origin to destination */
		this.moveTopTo(destination);
		
		/* Move top n-1 disks from buffer to destination, using origin as a buffer */
		buffer.moveDisks(n-1, destination, this);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n = 3;
		TowerOfHanoi[] towers = new TowerOfHanoi[n];
		for (int i= 0; i < 3; i++) {
			towers[i] = new TowerOfHanoi(i);
		}

		 // Stack disks in tower 0, meeting the constraint [Lower numbered disk > disk above it] 
		for (int i= n - 1; i >= 0; i--) {
			towers[0].add(i);
		}
		towers[0].moveDisks(n, towers[2], towers[1]);
	}

}
