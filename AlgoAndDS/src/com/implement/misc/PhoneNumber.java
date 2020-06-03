package com.implement.misc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PhoneNumber {
	public List<String> letterCombinations(String digits) {
		// table[i] stores all characters that corresponds to ith digit in phone
		String[] table = {  "", "", "abc", "def", 
							"ghi", "jkl", "mno", 
							"pqrs",	"tuv", "wxyz" };

		List<String> result = new ArrayList<String>();

		if (digits == null || digits.length() == 0) {
			return result;
		}

		int numOfDigits = digits.length();
		Queue<String> q = new LinkedList<>();
		q.offer("");

		while (!q.isEmpty()) {
			String s = q.poll();

			// If complete word is generated push it in the list
			if (s.length() == numOfDigits)
				result.add(s);
			else {
				int digit = digits.charAt(s.length()) - '0';
				
				String letters = table[digit];
				
				for (int i = 0; i < letters.length(); i++) {
					q.offer(s + letters.charAt(i));
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PhoneNumber pn = new PhoneNumber();
		List<String> result = pn.letterCombinations("21");

		System.out.println(result);
	}
}
