package com.implement.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*			  Index
 * 		c		0
 * 		a		1
 * 		t		2
 * 		s		3		cat
 * 		a		4		cats
 * 		n		5
 * 		d		6
 * 		d		7		and, sand
 * 		o		8
 * 		g		9
 * 			   10		dog
 * 
 * 
 */

public class WordBreak {
	public static boolean isWordBreak(String s, List<String> wordDict) {
		ArrayList<String> validWords[] = new ArrayList[s.length() + 1];
		validWords[0] = new ArrayList<String>();

		// In each iteration, check String starting with i and ending at j
		for (int i = 0; i < s.length(); i++) {
			if (validWords[i] != null) {
				for (int j = i + 1; j <= s.length(); j++) {
					String sub = s.substring(i, j);
					if (wordDict.contains(sub)) {
						if (validWords[j] == null) {
							validWords[j] = new ArrayList<String>();
						}
						validWords[j].add(sub);
					}
				}
			}
		}

		if (validWords[s.length()] == null) {
			return false;
		} 
		
		return true;
	}
	
	public static List<String> wordBreak(String s, List<String> wordDict) {
		ArrayList<String> validWords[] = new ArrayList[s.length() + 1];
		validWords[0] = new ArrayList<String>();

		// In each iteration, check String starting with i and ending at j
		for (int i = 0; i < s.length(); i++) {
			if (validWords[i] != null) {
				for (int j = i + 1; j <= s.length(); j++) {
					String sub = s.substring(i, j);
					if (wordDict.contains(sub)) {
						if (validWords[j] == null) {
							validWords[j] = new ArrayList<String>();
						}
						validWords[j].add(sub);
					}
				}
			}
		}

		if (validWords[s.length()] == null) {
			return new ArrayList<String>(); // or validWords[0]
		} else {
			ArrayList<String> result = new ArrayList<String>();
			dfs(validWords, result, new StringBuilder(), s.length());
			return result;
		}
	}

	public static void dfs(ArrayList<String>[] validWords,
			ArrayList<String> result, StringBuilder sb, int pos) {
		if (pos == 0) {
			result.add(sb.toString().trim());
			return;
		}

		/*
		 * for(String s: validWords[pos]){ String combined = s + " "+ curr;
		 * dfs(validWords, result, combined, pos-s.length()); }
		 */

		/*String s = validWords[pos].get(0);
		sb.insert(0, " ");
		sb.insert(0, s);
		
		dfs(validWords, result, sb, pos - s.length());*/
		
		ArrayList<String> s = validWords[pos];
		
		for(int i=0; i<s.size(); i++){			
			sb.insert(0, " ");
			sb.insert(0, s.get(i));
			
			dfs(validWords, result, sb, pos - s.get(i).length());
			
			// Backtrack for next word in validWords[pos]
			sb.delete(0, s.get(i).length() + 1);	// +1 is for the space
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> wordDict = Arrays.asList("mobile", "samsung", "sam",
				"sung", "man", "mango", "icecream", "and", "go", "i", "like",
				"ice", "cream");

		String str1 = "ilikesamsungmobile";
		String str2 = "ilikeicecreamandmango";

		List<String> result = wordBreak(str1, wordDict);
		System.out.println(result.toString());

		result = wordBreak(str2, wordDict);
		System.out.println(result.toString());
	}

}
