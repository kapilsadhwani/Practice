package com.implement.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class CountOccurrencesOfAnagrams {
	private static Map<Character, Integer> createFrequencyMap(String s) {
		Map<Character, Integer> frequencyMap = new HashMap<Character, Integer>();
		char ch;

		for (int i = 0; i < s.length(); i++) {
			ch = s.charAt(i);

			frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
		}

		return frequencyMap;
	}
	
	public static int countAnagrams(String s, String p) {
		int k = p.length();
		int size = s.length();
		char ch;
		int result = 0;
		
		Map<Character, Integer> frequencyMap = createFrequencyMap(p);
		
		// Keep track of Distinct characters, to avoid tracing Map
		int countOfDistinctLetters = frequencyMap.size();
		
		int windowStart = 0;

		for (int windowEnd = 0; windowEnd < size; windowEnd++) {
			ch = s.charAt(windowEnd);
			
			if(frequencyMap.containsKey(ch)){
				frequencyMap.put(ch, frequencyMap.get(ch) - 1);
				
				if(frequencyMap.get(ch) == 0)
					countOfDistinctLetters--;
			}
			
			if(windowEnd - windowStart + 1 == k){	// i.e window size = k
				if(countOfDistinctLetters == 0){
					result++;
				}
				
				ch = s.charAt(windowStart);
				
				if(frequencyMap.containsKey(ch)){
					frequencyMap.put(ch, frequencyMap.get(ch) + 1);
					
					if(frequencyMap.get(ch) == 1)
						countOfDistinctLetters++;
				}
				
				windowStart++;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String txt = "forxxorfxdofr";
		String pat = "for";

		System.out.println("String: " + txt + " Pattern: " + pat + ", Output: " + countAnagrams(txt, pat));
		
		txt = "aabaabaa";
		pat = "aaba";

		System.out.println("String: " + txt + " Pattern: " + pat + ", Output: " + countAnagrams(txt, pat));
		
		txt = "aaaaabbaa";
		pat = "aaba";

		System.out.println("String: " + txt + " Pattern: " + pat + ", Output: " + countAnagrams(txt, pat));
	}

}
