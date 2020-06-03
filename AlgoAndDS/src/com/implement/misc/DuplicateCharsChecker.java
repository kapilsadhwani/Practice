package com.implement.misc;

public class DuplicateCharsChecker {

	/**
	 * @param args
	 */

	private static boolean isUniqueChars(String s) { // Set bit at position =
														// charCode to 1
		if (s.length() > 26) {
			return false;
		}

		int checker = 0;
		for (int i = 0; i < s.length(); i++) {
			int val = s.charAt(i) - 'A';

			if ((checker & (1 << val)) > 0)
				return false;

			checker |= (1 << val);
		}
		return true;
	}

	private static boolean isAnagram(String str1, String str2) {
		// If length of strings are not same, the strings are not anagrams.
		if (str1 == null || str2 == null || str1.length() != str2.length()) {
			return false;
		}

		char[] letters = new char[128]; // Assumption

		char[] str1Chars = str1.toCharArray();

		for (Character ch : str1Chars) {
			letters[ch]++;
		}

		for (int i = 0; i < str2.length(); i++) {
			int ch = (int) str2.charAt(i);

			letters[ch]--;

			if (letters[ch] < 0)
				return false;
		}
		return true;
	}

	private static int firstRepeated(String str) {
		// An integer to store presence/absence
		// of 26 characters using its 32 bits.
		int checker = 0;

		for (int i = 0; i < str.length(); ++i) {
			int val = (str.charAt(i) - 'a');

			// If bit corresponding to current
			// character is already set
			if ((checker & (1 << val)) > 0)
				return i;

			// set bit in checker
			checker |= (1 << val);
		}

		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "ertysdE";

		System.out.println("Do we have duplicates in " + str + " ? --> "
				+ (isUniqueChars(str) ? "Unique" : "Duplicate"));

		String anagrams[] = { "lives", "evisk" };

		System.out.println("Are these words anagrams - " + anagrams[0]
				+ " and " + anagrams[1] + " ? --> "
				+ (isAnagram(anagrams[0], anagrams[1]) ? "Yes" : "No"));

	}

}
