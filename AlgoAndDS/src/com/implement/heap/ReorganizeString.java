package com.implement.heap;

import java.util.PriorityQueue;

public class ReorganizeString {
	static class MultiChar {
		int count;
		char letter;

		MultiChar(int ct, char ch) {
			count = ct;
			letter = ch;
		}
	}

	public static String reorganizeString(String s) {
		int N = s.length();
		int[] count = new int[26];
		for (char c : s.toCharArray())
			count[c - 'a']++;
		PriorityQueue<MultiChar> pq = new PriorityQueue<MultiChar>(
				(a, b) -> a.count == b.count ? a.letter - b.letter : b.count
						- a.count);

		for (int i = 0; i < 26; ++i)
			if (count[i] > 0) {
				if (count[i] > (N + 1) / 2)
					return "";
				
				pq.add(new MultiChar(count[i], (char) ('a' + i)));
			}

		StringBuilder ans = new StringBuilder();
		while (pq.size() >= 2) {
			MultiChar mc1 = pq.poll();
			MultiChar mc2 = pq.poll();
			/*
			 * This code turns out to be superfluous, but explains what is
			 * happening if (ans.length() == 0 || mc1.letter !=
			 * ans.charAt(ans.length() - 1)) { ans.append(mc1.letter);
			 * ans.append(mc2.letter); } else { ans.append(mc2.letter);
			 * ans.append(mc1.letter); }
			 */
			ans.append(mc1.letter);
			ans.append(mc2.letter);
			if (--mc1.count > 0)
				pq.add(mc1);
			if (--mc2.count > 0)
				pq.add(mc2);
		}

		if (pq.size() > 0)
			ans.append(pq.poll().letter);
		return ans.toString();
	}
	
	/*
	 * Rearrange String k Distance Apart
	 */
	public static String rearrangeString(String s, int k) {
		if(s == null || s.length() == 0) return "";
		
		if(s.length() == 1 || k == 0) return s;
			
		int[] count = new int[26];
		for (char c : s.toCharArray())
			count[c - 'a']++;
		PriorityQueue<MultiChar> pq = new PriorityQueue<MultiChar>(
				(a, b) -> a.count == b.count ? a.letter - b.letter : b.count
						- a.count);

		for (int i = 0; i < 26; ++i)
			if (count[i] > 0) {
				pq.add(new MultiChar(count[i], (char) ('a' + i)));
			}

		StringBuilder ans = new StringBuilder();
		
		MultiChar[] hold = new MultiChar[k];
		
		while (pq.size() >= k) {
			for(int i=0; i<k; i++){
				hold[i] = pq.poll();
				ans.append(hold[i].letter);
                hold[i].count--;
			}
			
			for(int i=0; i<k; i++){
				if (hold[i].count > 0)
					pq.add(hold[i]);
			}
			
		}
		
		while(pq.size() > 0){
			hold[0] = pq.poll();
			
			if(hold[0].count > 1) return "";
			
			ans.append(hold[0].letter);
		}
		
		return ans.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "aab";
		System.out.println(reorganizeString(s));
		
		s = "aaab";
		System.out.println(reorganizeString(s));
		
		s = "aabbcc";
		int k=3;
		System.out.println(rearrangeString(s, k));
		
		k=3;
		s = "aaabc";
		System.out.println(rearrangeString(s, k));
		
		k=2;
		s = "aaadbbcc";
		System.out.println(rearrangeString(s, k));
		
		k=0;
		s = "a";
		System.out.println(rearrangeString(s, k));
		
		k=2;
		s = "aaab";
		System.out.println(rearrangeString(s, k));
	}

}
