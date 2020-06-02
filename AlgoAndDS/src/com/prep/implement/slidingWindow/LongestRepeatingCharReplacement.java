package com.prep.implement.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharReplacement {
	/*
	 * The dominant character will give us longest length
	 */
	public static int characterReplacement(String s, int k) {
		Map<Character, Integer> map = new HashMap<>();

		int left = 0, maxRepeat = 0, maxWindow = 0;

		for (int right = 0; right < s.length(); right++) {
			char ch = s.charAt(right);
			if (!map.containsKey(ch)) {
				map.put(ch, 0);
			}
			map.put(ch, map.get(ch) + 1);

			// IMPORTANT: maxRepeat is not the accurate number of dominant
			// character, It is the historical maximum count
			// We do not care about it because unless it gets greater, it won't
			// affect our final max window size.
			maxRepeat = Math.max(maxRepeat, map.get(ch));

			if (right - left + 1 - maxRepeat > k) {
				char remove = s.charAt(left);
				map.put(remove, map.get(remove) - 1);
				left++;
			}

			maxWindow = Math.max(maxWindow, right - left + 1);
		}

		return maxWindow;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "ABAB";
		int k = 2;
		System.out.println(characterReplacement(s, k));
		
		s = "AABABBA";
		k = 1;
		
		System.out.println(characterReplacement(s, k));
	}

}