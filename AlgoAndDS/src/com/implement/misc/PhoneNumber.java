package com.implement.misc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PhoneNumber {
	static class Pair{
		String psf;
		int idx;
		
		public Pair(String psf, int idx) {
			this.psf = psf;
			this.idx = idx;
		}
	}
	
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
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair("", 0));
		
		Pair rem;

		while (!q.isEmpty()) {
			rem = q.poll();
			int pos = rem.idx;

			// If complete word is generated push it in the list
			if (pos == numOfDigits)
				result.add(rem.psf);
			else {
				int digit = digits.charAt(pos) - '0';
				
				String letters = table[digit];
				
				for (int i = 0; i < letters.length(); i++) {
					q.offer(new Pair(rem.psf + letters.charAt(i), pos + 1));
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PhoneNumber pn = new PhoneNumber();
		List<String> result = pn.letterCombinations("2334");
		
		System.out.println("Number of combinations : " + result.size());
		System.out.println(result);
	}
}
