package com.prep.implement.recursion;

import java.util.ArrayList;
import java.util.List;

public class OpenCloseBrackets {
	public void generateParenthesis(int n, int pos, int open, int close, char[] combo, List<String> result){
		if(close == n){
			result.add(String.valueOf(combo));
			return;
		}
		
		if(open < n){
			combo[pos] = '(';
			generateParenthesis(n, pos+1, open+1, close, combo, result);
		}
			
		if(close < open){
			combo[pos] = ')';
			generateParenthesis(n, pos+1, open, close+1, combo, result);
		}
	}
	
	public List<String> generateParenthesis(int n) {
		char[] combo = new char[2*n];
		List<String> result = new ArrayList<String>();
		
		generateParenthesis(n, 0, 0, 0, combo, result);
		
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 3;
		
		OpenCloseBrackets ocb = new OpenCloseBrackets();
		
		List<String> result = ocb.generateParenthesis(n);
		
		System.out.println(result);
	}

}
