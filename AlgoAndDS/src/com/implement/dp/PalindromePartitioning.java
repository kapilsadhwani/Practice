package com.implement.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PalindromePartitioning {
	/*
	 * Create a dp 
	 */
	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<>();
		boolean[][] dp = new boolean[s.length()][s.length()];
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j <= i; j++) {				//j = start, i = end
				if (s.charAt(i) == s.charAt(j)
						&& (i - j <= 2 || dp[j + 1][i - 1])) {
					dp[j][i] = true;
				}
			}
		}
		helper(res, new ArrayList<>(), dp, s, 0);
		return res;
	}

	private void helper(List<List<String>> res, List<String> path, boolean[][] dp, String s, int pos) {
		if (pos == s.length()) {
			res.add(new ArrayList<>(path));
			return;
		}

		for (int i = pos; i < s.length(); i++) {
			if (dp[pos][i]) {
				path.add(s.substring(pos, i + 1));
				helper(res, path, dp, s, i + 1);
				path.remove(path.size() - 1);
			}
		}
	}

	private boolean isPalindrome(String s, int i, int j) {
		// TODO Auto-generated method stub
		char[] strArray = s.toCharArray();

		if (i == j)
			return true;

		if (i > j)
			return true;

		while (i < j) {
			if (strArray[i] != strArray[j])
				return false;

			i++;
			j--;
		}

		return true;
	}

	private int solve(String s, int i, int j, int[][] cache) {
		if (i >= j)
			return 0;

		if (isPalindrome(s, i, j) == true)
			return 0;

		if (cache[i][j] != -1)
			return cache[i][j];

		int min = Integer.MAX_VALUE;

		for (int k = i; k <= j - 1; k++) {
			int temp = 1 + solve(s, i, k, cache) + solve(s, k + 1, j, cache);

			if (temp < min)
				min = temp;
		}

		return cache[i][j] = min;
	}

	private int numberOfPartitions(String s) {
		int len = s.length();

		int[][] cache = new int[len + 1][len + 1];

		for (int i = 0; i < cache.length; i++)
			Arrays.fill(cache[i], -1);

		return solve(s, 0, len - 1, cache);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "nitik";
		PalindromePartitioning pp = new PalindromePartitioning();

		int partitions = pp.numberOfPartitions(s);

		System.out.println("Number of partitions required is " + partitions);
		
		List<List<String>> result = pp.partition(s);
		
		System.out.println(result);
	}

}
