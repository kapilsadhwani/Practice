package com.prep.implement.datastructures;
import java.util.Stack;

public class BracketChecker {
  private String input;

  public BracketChecker(String in) {
    input = in;
  }

  public void check() {
    Stack<Character> theStack = new Stack<Character>();

    for (int j = 0; j < input.length(); j++) {
      char ch = input.charAt(j);
      switch (ch) {
	      case '{': 
	      case '[':
	      case '(':
	        theStack.push(ch);
	        break;
	      case '}': 
	      case ']':
	      case ')':
	        if (!theStack.isEmpty()) {
	          char chx = theStack.pop();
	          if ((ch == '}' && chx != '{') || (ch == ']' && chx != '[') || (ch == ')' && chx != '('))
	            System.out.println("Error: " + ch + " at " + j);
	        } else
	        	System.out.println("Error: " + ch + " at " + j);
	        break;
	      default:
	        break;
      }
    }
    if (!theStack.isEmpty()){
      System.out.println("Error: missing right delimiter");
    }
  }
  
  public static void main(String[] args) {
	    String input;
	    input = "[]]()()";

	    BracketChecker theChecker = new BracketChecker(input);
	    theChecker.check();
	  }
}