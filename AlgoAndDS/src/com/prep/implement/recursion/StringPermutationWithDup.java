package com.prep.implement.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StringPermutationWithDup {
	static ArrayList<String> printPerms(String s) {
		ArrayList<String> result = new ArrayList<String>();
		Map<Character, Integer> map = buildFreqTable(s);
		char[] resultChar = new char[s.length()];
		printPerms(map, resultChar, 0, result);
		return result;
	}

	static Map<Character, Integer> buildFreqTable(String s) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c : s.toCharArray()) {
			if (map.get(c) == null) {
				map.put(c, 0);
			}
			
			map.put(c, map.get(c) + 1);
		}
		return map;
	}

	static void printPerms(Map<Character, Integer> map, char[] resultChar, int pos, 
					ArrayList<String> result) {
		/* Base case. Permutation has been completed. */
		if (pos == resultChar.length) {
			result.add(String.valueOf(resultChar));
			return;
		}

		/*
		 * Try remaining letters for next char, and generate remaining permutations.
		 */
		for (char c : map.keySet()) {
			int count = map.get(c);
			if (count > 0) {
				map.put(c, count - 1);	// Include
				resultChar[pos] = c;
				printPerms(map, resultChar, pos+1, result);
				map.put(c, count);		// Don't include i.e revert count, similar to unswap
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        scan.close();
        
        if(input == null || input.trim().length() == 0) return;
        
        ArrayList<String> results = printPerms(input);
        
        for(String s : results){
        	System.out.println(s);
        }
        
	}
}