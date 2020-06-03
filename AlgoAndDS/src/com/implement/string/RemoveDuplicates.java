package com.implement.string;

// Java prigram to remove duplicates 
import java.util.*;

class RemoveDuplicates {
	/*
	 * Function removes duplicate characters from the string This function work
	 * in-place
	 */
	void removeDuplicateChars(String str) {
		LinkedHashSet<Character> lhs = new LinkedHashSet<Character>();
		for (int i = 0; i < str.length(); i++)
			lhs.add(str.charAt(i));

		// print string after deleting duplicate elements
		for (Character ch : lhs)
			System.out.print(ch);
		
		/*lhs.forEach(ch -> {
			System.out.print(ch);
		});*/
	}
	
	/*
	 * Function removes duplicate characters from the string This function work
	 * in-place
	 */
	void removeDuplicateStrings(String[] strArray) {
		TreeSet<String> ts = new TreeSet<String>();
		for (int i = 0; i < strArray.length; i++)
			ts.add(strArray[i]);		

		// print string array after deleting duplicate strings
		for (String str : ts)
			System.out.print(str+", ");
		
		/*lhs.forEach(ch -> {
			System.out.print(ch);
		});*/
	}

	/* Driver program to test removeDuplicates */
	public static void main(String args[]) {
		String str = "geeksforgeeks";
		RemoveDuplicates r = new RemoveDuplicates();
		r.removeDuplicateChars(str);
	}
}