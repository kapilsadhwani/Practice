package com.prep.implement.slidingWindow;

public class TwoStringPermutation {
	public static boolean checkInclusion(String s1, String s2) {
		if (s1.length() > s2.length())
			return false;
		
		int smallerLen = s1.length();
		
		int[] s1map = new int[26];
		int[] s2map = new int[26];
		for (int i = 0; i < smallerLen; i++) {
			s1map[s1.charAt(i) - 'a']++;
			s2map[s2.charAt(i) - 'a']++;
		}
		
		// smaller string map will remain same
		for (int i = 0; i < s2.length() - smallerLen; i++) {
			if (matches(s1map, s2map))
				return true;
			
			s2map[s2.charAt(i) - 'a']--;
			s2map[s2.charAt(i + smallerLen) - 'a']++;
		}
		return matches(s1map, s2map);
	}

	public static boolean matches(int[] s1map, int[] s2map) {
		for (int i = 0; i < 26; i++) {
			if (s1map[i] != s2map[i])
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		String s1 = "ab", s2 = "eidbaooo";
		System.out.println(checkInclusion(s1, s2));
		
		s1= "ab";
		s2 = "eidboaoo";
		
		System.out.println(checkInclusion(s1, s2));
	}
}