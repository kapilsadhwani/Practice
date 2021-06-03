package com.implement.slidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinimumWindowSubstring {
	public static String minWindowSubStringAS(String s, String t) {
		String result = "";

		if (t.length() > s.length())
			return result;
		
		// character counter map for t
		Map<Character, Integer> targetMap = new HashMap<Character, Integer>();
		for (char c : t.toCharArray()) {
			targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
		}

		// Number of unique characters in t
		int countOfDistinctLetters = targetMap.size();

		int minLen = Integer.MAX_VALUE;

		// sliding window left and right pointers
		int i = 0;
		
		for (int j = 0; j < s.length(); j++) {
			char ch = s.charAt(j);

			if(targetMap.containsKey(ch)){
				targetMap.put(ch, targetMap.get(ch) - 1);
				
				if(targetMap.get(ch) == 0)
					countOfDistinctLetters--;
				
				if(countOfDistinctLetters == 0){
					while(countOfDistinctLetters == 0){
						ch = s.charAt(i);
						
						if(targetMap.containsKey(ch)){
							int val = targetMap.get(ch);
							
							if(val == 0){
								if(j - i + 1 < minLen){
									minLen = j - i + 1;
									result = s.substring(i, j + 1);
								}
								
								// Since we are going to advance i (i.e left of window) by 1
								countOfDistinctLetters++;
								targetMap.put(ch, 1);
							}else{
								targetMap.put(ch, val + 1);
							}
						}
						
						i++;
					}	
				}
			}
		}

		return result;
	}

	// Returns true if pat is substring of text
	static int isSubstring(String pat, String text) {
		int M = pat.length();
		int N = text.length(); // N >= M

		/* A loop to slide pat[] one by one */
		for (int i = 0; i + (M - 1) < N; i++) {
			int j = 0;

			/*
			 * For current index i, check for pattern match
			 */
			while (j < M) {
				if (text.charAt(i + j) != pat.charAt(j)) {
					break;
				}
				j++;
			}

			if (j == M)
				return i;
		}

		return -1;
	}

	public static Set<Character> convertToSet(String string) {
		// Result hashset
		Set<Character> resultSet = new HashSet<Character>();

		for (int i = 0; i < string.length(); i++) {
			resultSet.add(new Character(string.charAt(i)));
		}

		// Return result
		return resultSet;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str1 = "this is a test string";

		String str2 = "is";

		System.out.println(isSubstring(str2, str1));

		String str3 = "tist";

		System.out.println(minWindowSubStringAS(str1, str3));

		str1 = "ADOBECODEBANC";
		str3 = "ABC";
		
		System.out.println(minWindowSubStringAS(str1, str3));
		
		str1 = "cabwefgewcwaefgcf";
		str3 = "cae";
		
		System.out.println(minWindowSubStringAS(str1, str3));
	}

}
