package com.implement.string;

public class LongestValidParentheses {
	public int longestValidParentheses(String s) {
		int open = 0, close = 0, maxlength = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				open++;
			} else {
				close++;
			}
			if (open == close) {
				maxlength = Math.max(maxlength, 2 * close);
			} else if (close > open) {
				open = close = 0;
			}
		}
		open = close = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == '(') {
				open++;
			} else {
				close++;
			}
			if (open == close) {
				maxlength = Math.max(maxlength, 2 * open);
			} else if (open > close) {
				open = close = 0;
			}
		}
		return maxlength;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LongestValidParentheses lvp = new LongestValidParentheses();
		System.out.println(lvp.longestValidParentheses("()((())"));
	}

}
