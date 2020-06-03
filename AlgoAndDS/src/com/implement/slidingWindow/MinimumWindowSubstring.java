package com.implement.slidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MinimumWindowSubstring {
	public static String minWindow(String s, String t) {
		if (t.length() > s.length())
			return "";
		String result = "";

		// character counter for t
		HashMap<Character, Integer> targetMap = new HashMap<Character, Integer>();
		for (char c : t.toCharArray()) {
			targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
		}

		// character counter for s
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		int minLen = s.length() + 1; // Max length + 1 for String s

		int count = 0; // the total of mapped characters

		// Create Map for String s till tCount in s == t.length ( 0 --> t.length)
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (targetMap.containsKey(c)) { // Check if char is present in target Map
				if (map.containsKey(c)) {
					// We don't increment count after s.get(c) ==
					// targetMap.get(c)
					if (map.get(c) < targetMap.get(c)) {
						count++;
					}
					map.put(c, map.get(c) + 1);
				} else {
					map.put(c, 1);
					count++;
				}
			}

			if (count == t.length()) { // i will be s(0..i) that controls t; (i-left+1) will be length of the string
				int left = 0;
				char leftChar = s.charAt(left);
				while (!map.containsKey(leftChar) || map.get(leftChar) > targetMap.get(leftChar)) {
					if (map.containsKey(leftChar) && map.get(leftChar) > targetMap.get(leftChar)){
						map.put(leftChar, map.get(leftChar) - 1);
					}

					// Go to next char in left half
					left++;
					leftChar = s.charAt(left);
				}

				if (i - left + 1 < minLen) {
					result = s.substring(left, i + 1);
					minLen = i - left + 1;
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
		String str2 = "tist";

		System.out.println(minWindow(str1, str2));

		String str3 = "is";

		System.out.println(isSubstring(str3, str1));

		str1 = "ADOBECODEBANC";
		str2 = "ABC";
		System.out.println(minWindow(str1, str2));

	}

}
