package com.implement.string;

// A Java program to remove "b" and 
// 'ac' from input String 
import java.util.Arrays;

class StringFilter {
	static final int ONE = 1;
	static final int TWO = 2;

	// The main function that removes occurrences
	// of "a" and "bc" in input String
	static char[] StringFilter(char[] str) {
		// state is initially ONE
		// (The previous character is not a)
		int state = ONE;

		// i and j are index variables,
		// i is used to read next
		// character of input String,
		// j is used for indexes of output
		// String (modified input String)
		int j = 0;

		// Process all characters of
		// input String one by one
		for (int i = 0; i < str.length; i++) {
			/*
			 * If state is ONE, then do NOT copy the current character to output
			 * if one of the following conditions is true ...a) Current
			 * character is 'b' (We need to remove 'b') ...b) Current character
			 * is 'a' (Next character may be 'c')
			 */
			if (state == ONE && str[i] != 'a' && str[i] != 'b') {
				str[j] = str[i];
				j++;
			}

			// If state is TWO and current character
			// is not 'c' (otherwise we ignore both
			// previous and current characters)
			if (state == TWO && str[i] != 'c') {
				// First copy the previous 'a'
				str[j] = 'a';
				j++;

				// Then copy the current character
				// if it is not 'a' and 'b'
				if (str[i] != 'a' && str[i] != 'b') {
					str[j] = str[i];
					j++;
				}
			}

			// Change state according to current character
			state = (str[i] == 'a') ? TWO : ONE;
		}

		// If last character was 'a',
		// copy it to output
		if (state == TWO) {
			str[j] = 'a';
			j++;
		}
		return Arrays.copyOfRange(str, 0, j);
	}

	// Driver Code
	public static void main(String[] args) {
		char str1[] = "ad".toCharArray();
		str1 = StringFilter(str1);
		System.out.print(String.valueOf(str1) + "\n");

		char str2[] = "acbac".toCharArray();
		str2 = StringFilter(str2);
		System.out.print(String.valueOf(str2) + "\n");

		char str3[] = "aaac".toCharArray();
		str3 = StringFilter(str3);
		System.out.print(String.valueOf(str3) + "\n");

		char str4[] = "react".toCharArray();
		str4 = StringFilter(str4);
		System.out.print(String.valueOf(str4) + "\n");

		char str5[] = "aa".toCharArray();
		str5 = StringFilter(str5);
		System.out.print(String.valueOf(str5) + "\n");

		char str6[] = "ababaac".toCharArray();
		str6 = StringFilter(str6);
		System.out.print(String.valueOf(str6) + "\n");
	}
}

// This code is contributed by 29AjayKumar
