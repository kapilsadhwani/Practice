package com.implement.stack;

import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses {
	// Hash table that takes care of the mappings.
	private HashMap<Character, Character> mappings;

	// Initialize hash map with mappings. This simply makes the code easier to
	// read.
	public ValidParentheses() {
		this.mappings = new HashMap<Character, Character>();
		this.mappings.put(')', '(');
		this.mappings.put('}', '{');
		this.mappings.put(']', '[');
	}

	public boolean isValid(String s) {

		// Initialize a stack to be used in the algorithm.
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			// If the current character is a closing bracket.
			if (this.mappings.containsKey(c)) {

				// Get the top element of the stack. If the stack is empty, set
				// a dummy value of '#'
				char topElement = stack.empty() ? '#' : stack.pop();

				// If the mapping for this bracket doesn't match the stack's top
				// element, return false.
				if (topElement != this.mappings.get(c)) {
					return false;
				}
			} else {
				// If it was an opening bracket, push to the stack.
				stack.push(c);
			}
		}

		// If the stack still contains elements, then it is an invalid
		// expression.
		return stack.isEmpty();
	}
	
	public static boolean checkValidString(String s) {
		int leftBalance = 0;
		for (int i = 0; i < s.length(); i++) {
			if ((s.charAt(i) == '(') || (s.charAt(i) == '*'))
				leftBalance++;
			else
				leftBalance--;

			if (leftBalance < 0)
				return false; // We know we have unbalanced parenthesis
		}

		// We can already check if parenthesis are valid
		if (leftBalance == 0)
			return true;

		int rightBalance = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			if ((s.charAt(i) == ')') || (s.charAt(i) == '*'))
				rightBalance++;
			else
				rightBalance--;

			if (rightBalance < 0)
				return false; // We know we have unbalanced parenthesis
		}

		// Here we know we have never been unbalanced parsing from left to right
		// e.g. ')('
		// We've also already substituted '*' either by '(' or by ')'
		// So we only have 3 possible scenarios here:
		// 1. We had the same amount of '(' and ')'
		// 2. We had more '(' then ')' but enough '*' to substitute
		// 3. We had more ')' then '(' but enough '*' to substitute
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ValidParentheses vp = new ValidParentheses();
		/*System.out.println("() is" + (vp.isValid("()") ? " valid " : " not valid "));
		System.out.println("()[]{} is" + (vp.isValid("()[]{}") ? " valid " : " not valid "));
		System.out.println("(] is" + (vp.isValid("(]") ? " valid " : " not valid "));
		System.out.println("([)] is" + (vp.isValid("([)]") ? " valid " : " not valid "));
		System.out.println("{[]} is" + (vp.isValid("{[]}") ? " valid " : " not valid "));*/
		
		System.out.println("() is valid? " + checkValidString("()"));
		System.out.println("(*) is valid? " + checkValidString("(*)"));
		System.out.println("(*)) is valid? " + checkValidString("(*))"));
		
		System.out.println("=============================================");
		
		System.out.println("()) is valid? " + checkValidString("())"));
		System.out.println("(() is valid? " + checkValidString("(()"));
		System.out.println("((( is valid? " + checkValidString("((("));
		System.out.println("))) is valid? " + checkValidString(")))"));
		
		System.out.println("=============================================");
		
		System.out.println("(**) is valid? " + checkValidString("(**)"));
		System.out.println("(*)()* is valid? " + checkValidString("(*)()*"));
		System.out.println("(*)** is valid? " + checkValidString("(*)**"));
		System.out.println(")*)) is valid? " + checkValidString(")*))"));
		
		System.out.println("=============================================");
		System.out.println("*)**) is valid? " + checkValidString("*)**)"));
		System.out.println("*(*(* is valid? " + checkValidString("*(*(*"));
		System.out.println("*())** is valid? " + checkValidString("*())**"));
		System.out.println("*((()) is valid? " + checkValidString("*((())"));
		
		System.out.println("=============================================");
	}

}
