package com.implement.slidingWindow;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.util.Pair;

public class LongestSubstringWithDistinctChars {
	public static final int MAX_CHARS = 257;		// or 26
	
	/*
	 * Longest Substring Without Repeating Characters
	 * 2-Pointer Algorithm/Sliding Window
	 * 
	 * Using Set
	 * Time complexity : O(2n) = O(n). In the worst case each character will be visited twice by i and j.
	 */
	public static int lengthOfLongestSubstringUsingSet(String s) {
		if(s == null || s.length() == 0) return 0;
		
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, start = 0, end = 0;
        while (start <= end && end < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(end))){
                set.add(s.charAt(end));
                end++;
                
                ans = Math.max(ans, end - start);			// Update ans
            }
            else {
                set.remove(s.charAt(start++));
            }
        }
        return ans;
    }
	
	/*
	 * Longest String With Distinct Chars
	 * Sliding Window Algorithm
	 * 
	 * Using HashMap or Array of 256 chars as auxiliary space
	 * Time complexity : O(n). In the worst case each character will be visited once. 
	 */	
	
	public static int lengthOfLongestSubstringWithDistinctChars(String str) {
		if (str == null || str.length() == 0)
			return 0;

		int ans = 0;

		// Map of character and index at which it was seen last

		int start = 0;

		int[] charFrequencyMap = new int[MAX_CHARS];
		Arrays.fill(charFrequencyMap, -1);

		for (int end = 0; end < str.length(); end++) {
			char rightChar = str.charAt(end);

			// If this char of right was seen before, i.e its repeating
			if (charFrequencyMap[rightChar] != -1) {
				// +1 to ignore instance of right char last seen
				start = Math.max(start, charFrequencyMap[rightChar] + 1);
			}

			charFrequencyMap[rightChar] = end; // Save indices of chars last seen at

			ans = Math.max(ans, end - start + 1);
		}
		return ans;
	}
	
	public static int lengthOfLongestSubstringTwoDistinct(String s) {
		int n = s.length();
		if (n < 3)
			return n;

		// sliding window left and right pointers
		int left = 0;
		int right = 0;
		
		int maxLeft = -1;
		int maxRight = -1;
		
		// hashmap character -> its rightmost position
		// in the sliding window
		HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

		int max_len = 0;

		while (right < n) {
			// slidewindow contains less than 3 characters
			if (hashmap.size() <= 2){
				// save index of character last seen at
				hashmap.put(s.charAt(right), right);
			}

			// slidewindow contains 3 characters
			if (hashmap.size() == 3) {
				// delete the leftmost character
				int del_idx = Collections.min(hashmap.values());
				char leftChar = s.charAt(del_idx);

				hashmap.remove(leftChar);
				// move left pointer of the slidewindow
				left = del_idx + 1;
			}

			if(max_len < right - left + 1){
				max_len = right - left + 1;
				maxLeft = left;
				maxRight = right;
			}
			
			right++;
		}
		
		System.out.println("Max left : " + maxLeft);
		System.out.println("Max Right : " + maxRight);
		System.out.println("Longest Substring : " + s.substring(maxLeft, maxRight + 1));
		
		return max_len;
	}
	
	public static Pair<Integer, Pair<Integer, Integer>> longestStringWithKDistinctChars(String s, int k) {
		int n = s.length();
		if (n <= k)
			return new Pair<Integer, Pair<Integer,Integer>>(n, new Pair<Integer,Integer>(0,n-1));

		// sliding window left and right pointers
		int left = 0;
		int right = 0;
		
		int maxLeft = -1;
		int maxRight = -1;
		
		// hashmap character -> its rightmost position
		// in the sliding window
		HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

		int max_len = 0;

		while (right < n) {
			// slidewindow contains less than 3 characters
			if (hashmap.size() <= k){
				// save index of character last seen at
				hashmap.put(s.charAt(right), right);
			}

			// slidewindow contains 3 characters
			if (hashmap.size() == k + 1) {
				// delete the leftmost character
				int del_idx = Collections.min(hashmap.values());
				char leftChar = s.charAt(del_idx);

				hashmap.remove(leftChar);
				// move left pointer of the slidewindow
				left = del_idx + 1;
			}

			if (max_len < right - left + 1) {
				max_len = right - left + 1;
				maxLeft = left;
				maxRight = right;
			}
			
			right++;
		}
		
		/*System.out.println("Max left : " + maxLeft);
		System.out.println("Max Right : " + maxRight);
		System.out.println("Longest Substring : " + s.substring(maxLeft, maxRight + 1));*/
		
		return new Pair<Integer, Pair<Integer,Integer>>(max_len, new Pair<Integer,Integer>(maxLeft,maxRight));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "aabacbebebe";

		System.out.println("Given String : " + s);
		
		System.out.println("Longest Substring Using Set (2-pointer) : " + lengthOfLongestSubstringUsingSet(s));
		System.out.println("Longest Substring Using Sliding Window : " + lengthOfLongestSubstringWithDistinctChars(s));
		
		Pair<Integer, Pair<Integer,Integer>> pair = longestStringWithKDistinctChars(s, 3);
		System.out.print("Longest Substring length with 3 Distinct Chars : ");
		System.out.print(pair.getKey() + ", " + pair.getValue().getKey() + " " + pair.getValue().getValue());
		
		if(pair.getValue().getKey() != -1)
			System.out.println(", " + s.substring(pair.getValue().getKey(), pair.getValue().getValue() + 1));
		else
			System.out.println(", Empty String");
		
		s = "abcabcbb";
		System.out.println(s + " : " + lengthOfLongestSubstringWithDistinctChars(s));
		
		s = "bbbbb";
		System.out.println(s + " : " + lengthOfLongestSubstringWithDistinctChars(s));
		
		s = "pwwkew";
		System.out.println(s + " : " + lengthOfLongestSubstringWithDistinctChars(s));
		
		s = "abaccc";
		System.out.println("Original String : " + s);
		System.out.println("Length of Longest Substring with at most 2 distinct characters: " + lengthOfLongestSubstringTwoDistinct(s));
		
	}

}