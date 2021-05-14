package com.implement.dp;

import java.util.HashMap;
import java.util.Map;

// Java program to check if a  given string is a scrambled form of another string

class ScrambledString {

	static Boolean solve(String s1, String s2, Map<String, Integer> map) {

		// Equal strings are scramble strings
		if (s1.equals(s2)) {
			return true;
		}
		
		// Constraint: String cannot be empty
		if(s1.length() <= 1)
			return false;
		
		StringBuilder key = new StringBuilder(s1);
		key.append("_");
		key.append(s2);
		
		if(map.containsKey(key.toString())){
			return map.get(key.toString()) == 1 ? true : false;
		}

		int n = s1.length();

		for (int i = 1; i <= n - 1; i++) {
			// Case 1: Swapping
			if (solve(s1.substring(0, i), s2.substring(n - i, n), map)
					&& solve(s1.substring(i, n), s2.substring(0, n - i), map)) {
				map.put(key.toString(), 1);
				return true;
			}

			// Case 2: No Swapping
			if (solve(s1.substring(0, i), s2.substring(0, i), map)
					&& solve(s1.substring(i, n), s2.substring(i, n), map)) {
				map.put(key.toString(), 1);
				return true;
			}
		}

		// If none of the above conditions are satisfied
		map.put(key.toString(), 0);
		return false;
	}

	// Driver Code
	public static void main(String[] args) {
		/*String S1 = "great";
		String S2 = "aterg";
		*/
		
		String S1 = "coder";
		String S2 = "dcroe";
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		if(S1.length() != S2.length())
			System.out.println("No");
		
		if(S1.length() == 0)
			System.out.println("Yes");
		
		if(S1.equals(S2))
			System.out.println("Yes");

		if (solve(S1, S2, map)) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
}
