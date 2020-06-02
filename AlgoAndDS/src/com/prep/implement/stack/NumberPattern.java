package com.prep.implement.stack;
import java.util.Stack; 

// Java program to print minimum number that can be formed 
// from a given sequence of Is and Ds 
class NumberPattern { 

// Function to decode the given sequence to construct 
// minimum number without repeated digits 
	static void PrintMinNumberForPattern(String seq) { 
		// result store output string 
		String result = ""; 

		// create an empty stack of integers 
		Stack<Integer> stack = new Stack<Integer>(); 

		// run n+1 times where n is length of input sequence 
		// Length of the output string is always one more than the input string. 
		for (int i = 0; i <= seq.length(); i++) { 
			// push number i+1 into the stack 
			stack.push(i + 1); 

			// if all characters of the input sequence are 
			// processed or current character is 'I' 
			// (increasing) 
			if (i == seq.length() || seq.charAt(i) == 'I') { 
				// run till stack is empty 
				while (!stack.empty()) { 
					// remove top element from the stack and 
					// add it to solution 
					result += String.valueOf(stack.pop());
				} 
			} 
		} 

		System.out.println(result); 
	} 

// main function 
	public static void main(String[] args) { 
		PrintMinNumberForPattern("IDID"); 
		PrintMinNumberForPattern("I"); 
		PrintMinNumberForPattern("DD"); 
		PrintMinNumberForPattern("II"); 
		PrintMinNumberForPattern("DIDI"); 
		PrintMinNumberForPattern("IIDDD"); 
		PrintMinNumberForPattern("DDIDDIID"); 
	} 
} 
