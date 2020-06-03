package com.implement.string;

public class ClosingParenthesis {
	public static int getClosingParen(String sentence, int openingParenIndex) {
	    int openNestedParens = 0;

	    for (int position = openingParenIndex + 1; position < sentence.length(); position++) {
	        char c = sentence.charAt(position);

	        if (c == '(') {
	            openNestedParens++;
	        } else if (c == ')') {
	            if (openNestedParens == 0) {
	                return position;
	            } else {
	                openNestedParens--;
	            }
	        }
	    }

	    throw new IllegalArgumentException("No closing parenthesis :(");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sentence = "Sometimes (when I nest them (my parentheticals) too much (like this (and this))) they get confusing";
		int openingParenIndex = 10;
		
		System.out.println("Corresponding closing parenthesis : " + getClosingParen(sentence, openingParenIndex));
	}

}
