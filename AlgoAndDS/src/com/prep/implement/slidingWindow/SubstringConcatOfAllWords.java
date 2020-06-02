package com.prep.implement.slidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringConcatOfAllWords {	
	public List<Integer> findSubstring(String inputStr, String[] words) {
		List<Integer> result = new ArrayList<Integer>();
		if (inputStr == null || inputStr.length() == 0 || words == null || words.length == 0) {
			return result;
		}

		int wordSize = words[0].length();
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String str : words) {
			if (map.containsKey(str)) {
				map.put(str, map.get(str) + 1);
			} else {
				map.put(str, 1);
			}
		}

		for (int i = 0; i < wordSize; i++) {
			int count=0, start = i;
			
			Map<String, Integer> curMap = new HashMap<String, Integer>();
			for (int end = i; end < inputStr.length()-wordSize+1; end += wordSize) {
				String token = inputStr.substring(end, end+wordSize);
				if (map.containsKey(token)) {
					if(curMap.containsKey(token)){ 
						curMap.put(token, curMap.get(token)+1);
					}else {
						curMap.put(token, 1);
					}
					count++;
					
					while(curMap.get(token) > map.get(token)){
						String tmp = inputStr.substring(start, start+wordSize);
						if(curMap.containsKey(tmp)){
							curMap.put(tmp, curMap.get(tmp)-1);
							count--;
						}
						start = start + wordSize;
					}
					
					if(count == words.length){
						result.add(start);
						String tmp = inputStr.substring(start, start+wordSize);
						if(curMap.containsKey(tmp)){
							curMap.put(tmp, curMap.get(tmp)-1);
							count--;
						}
						start = start + wordSize;
					}
				} else {
					curMap.clear();
					count=0;
					start = end + wordSize;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String s = "barfoothefoobarman";
		String[] L = new String[] { "foo", "bar" };
		SubstringConcatOfAllWords scaw = new SubstringConcatOfAllWords();
		
		List<Integer> result = scaw.findSubstring(s, L);
		
		for(int index:result){
			System.out.print(index + " ");
		}
		
		s = "wordgoodgoodgoodbestword";
		L = new String[] { "good","good","best","word" };
		
		result = scaw.findSubstring(s, L);
		
		System.out.println();
		
		for(int index:result){
			System.out.print(index + " ");
		}
	}
}