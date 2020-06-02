package com.prep.implement.heap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;

public class SortCharsByFrequency {
	public static String frequencySort(String s) {
		if (s == null || s.length() == 0)
			return s;

		// build hash map : character and how often it appears
		Map<Character, Integer> counts = new HashMap<>();
		for (char c : s.toCharArray()) {
			counts.put(c, counts.getOrDefault(c, 0) + 1);
		}

		// Make a list of the keys, sorted by frequency.
		List<Character> characters = new ArrayList<>(counts.keySet());
		Collections.sort(characters, (a, b) -> counts.get(b) - counts.get(a));

		// Convert the counts into a string with a sb.
		StringBuilder sb = new StringBuilder();
		for (char c : characters) {
			int copies = counts.get(c);
			for (int i = 0; i < copies; i++) {
				sb.append(c);
			}
		}
		return sb.toString();
	}
     
     public static void main(String[] args) {
 		// TODO Auto-generated method stub
 		
 		System.out.println(frequencySort("tree"));
 		
 		System.out.println(frequencySort("cccaaa"));
 		
 		System.out.println(frequencySort("Aabb"));
 		
 		System.out.println(frequencySort(""));
 	}
}