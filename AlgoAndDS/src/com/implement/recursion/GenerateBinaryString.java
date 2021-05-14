package com.implement.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GenerateBinaryString {
	
	private static void printForPattern(String pattern, Consumer<CharSequence> consumer) {
	    printForPattern(pattern, new StringBuilder(), 0, consumer);
	}
	
	private static void printForPattern(String pattern, StringBuilder sb, int pos,
			Consumer<CharSequence> consumer) {
	    int length = sb.length();
	    if (pattern.length() == pos) {
	        consumer.accept(sb);
	        return;
	    }
	    char ch = pattern.charAt(pos);
	    if (ch == '?' || ch == '0') {
	        sb.append('0');								// Include
	        printForPattern(pattern, sb, pos+1, consumer);
	        
	        sb.setLength(length);						// Revert
	    }
	    if (ch == '?' || ch == '1') {
	        sb.append('1');								// Include
	        printForPattern(pattern, sb, pos+1, consumer);
	        
	        sb.setLength(length);						// Revert
	    }
	}
	
	private static void printForPattern(String pattern) {
		char[] result = new char[pattern.length()];
		printForPattern(pattern, 0, result);
	}
	
	private static void printForPattern(String pattern,	int pos, char[] result) {
		//int length = sb.length();
		if (pattern.length() == pos) {
			System.out.println(String.valueOf(result));
			return;
		}
		
		char ch = pattern.charAt(pos);
		if (ch == '?' || ch == '0') {
			result[pos] = '0'; // Include 0
			printForPattern(pattern, pos + 1, result);
		}
		if (ch == '?' || ch == '1') {
			result[pos] = '1'; // Include 1
			printForPattern(pattern, pos + 1, result);
		}
	}
	
	private static void printNBitBinary(int n, int pos, int zeros, int ones, 
			char[] combo, List<String> result) {
		if (n == 0) {
			result.add(String.valueOf(combo));
			return;
		}

		combo[pos] = '1';
		printNBitBinary(n - 1, pos + 1, zeros, ones + 1, combo, result);

		if (zeros < ones) {
			combo[pos] = '0';
			printNBitBinary(n - 1, pos + 1, zeros + 1, ones, combo, result);
		}
	}
	
	// Print N-bit binary numbers having more or equal 1’s than 0’s for any prefix
	private static List<String> printNBitBinary(int n){
		char[] combo = new char[n];
		List<String> result = new ArrayList<String>();
		
		printNBitBinary(n, 0, 0, 0, combo, result);
		
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "1??0?101"; 
		//printForPattern(str, System.out::println);
		
		printForPattern(str);
		
		System.out.println("\n=============================================\n");
		
		List<String> result = printNBitBinary(5);
		System.out.println(result);
	}

}
