package com.implement.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubstringByRecursion {

	static void substring(char[] input, int pos, char[] result, int r_size) {
		if (pos == input.length) {
			printArray(result, r_size);
			return;
		}

		/*
		 * Print everything to left of the tree i.e Do not select the element
		 */
		substring(input, pos + 1, result, r_size);

		/*
		 * Select the element
		 */
		result[r_size] = input[pos];
		substring(input, pos + 1, result, r_size + 1);
	}

	static void printArray(char[] input, int size) {
		for (int i = 0; i < size; i++) {
			System.out.print(input[i]);
		}
		System.out.println("");
	}

	static String getString(char[] input, int size) {
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < size; i++) {
			sb.append(input[i]);
		}
		return sb.toString();
	}

	private static void permuteLetterSpace(char[] input, int pos, char[] result,
			int r_size, List<String> list) {
		if (pos == input.length) {
			list.add(String.valueOf(result, 0 ,r_size));
			//list.add(getString(result, r_size));
			return;
		}

		/*
		 * Do not add space
		 */
		result[r_size] = input[pos];
		permuteLetterSpace(input, pos + 1, result, r_size + 1, list);

		/*
		 * Add space
		 */
		if (pos > 0) {
			result[r_size] = '_';
			result[r_size + 1] = input[pos];
			permuteLetterSpace(input, pos + 1, result, r_size + 2, list);
		}
	}

	private static List<String> letterSpacePermutation(String input) {
		char[] inputChar = input.toCharArray();
		char[] result = new char[inputChar.length * 2 - 1];

		List<String> list = new ArrayList<String>();

		permuteLetterSpace(inputChar, 0, result, 0, list);

		return list;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		scan.close();

		if (input == null || input.trim().length() == 0)
			return;

		char[] inputChar = input.toCharArray();
		char[] result = new char[inputChar.length];

		substring(inputChar, 0, result, 0);

		List<String> list = letterSpacePermutation(input);
		System.out.println(list);
	}

}
