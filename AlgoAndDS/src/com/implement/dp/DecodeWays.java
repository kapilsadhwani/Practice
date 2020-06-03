package com.implement.dp;

import java.util.HashMap;
import java.util.Map;

public class DecodeWays {
	public int numDecodings(String s) {
		if (s == null || s.length() == 0)
			return 0;

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		return solve(s, 0, map);
	}

	private int solve(String str, int pos, Map<Integer, Integer> map) {
		Integer count = map.get(pos);
		if (count != null) {
			return count;
		}

		if (pos == str.length()) {
			return 1;
		}

		int numWays = 0;
		
		for (int i = pos; i < str.length() && i - pos < 2; i++) {
			if (str.charAt(pos) != '0' && Integer.parseInt(str.substring(pos, i + 1)) <= 26) {
				numWays = numWays + solve(str, i + 1, map);
			}
		}

		map.put(pos, numWays);

		return numWays;
	}
	
	public static void main(String[] args) {
		DecodeWays dw = new DecodeWays();
		
		String str = "12";
		System.out.println(dw.numDecodings(str));
		
		str = "226";
		System.out.println(dw.numDecodings(str));
		
		str = "2316";
		System.out.println(dw.numDecodings(str));
	}
}