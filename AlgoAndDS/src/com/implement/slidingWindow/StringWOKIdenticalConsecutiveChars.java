package com.implement.slidingWindow;

public class StringWOKIdenticalConsecutiveChars {
	/*
	 * Time: O(n)
	 * Space: O(1)
	 */
	
	public boolean isValid(String coinSequence) {  // coinSequence is a string of 'H' or 'T'. Examples: "HHT", "TTTTHH", "HTHTHHHT".
		char prev = 'N';   // initialize prev with some character which is not 'H' or 'T'.
		int prev_cnt = 0;
		for (char c : coinSequence.toCharArray()) {
			if (c == prev) {
				prev_cnt++;
			} else {
				if (prev_cnt > 2)	return false;   // Saw more than 2 characters (coins) of the same type
				prev = c;
				prev_cnt = 1;
			}
		}
		return true;
	}
	
	/*
	 * Time: O(n)
	 * Space: O(1)
	 */
	public boolean isValidSW(String coinSequence) {
		int n = coinSequence.length();
		int left = -1, right = -1;
	        // Keep track of 'H' and 'T' count in the current window. count[0] is count of heads and count[1] is count of tails.
		int[] count = new int[2];
		while (++right < n) {
			char rChar = coinSequence.charAt(right);
			if (rChar == 'H')	count[0]++;
			else	count[1]++;

			if (count[0] > 2 || count[1] > 2)	return false;   // The current window has more than 2 heads or tails.

			if (right - left >= 3) {     // Reached the max size of the window. Reduce it to avoid having more than 3 element window in the next iteration.
				left++;
				char lChar = coinSequence.charAt(left);
				if (lChar == 'H')	count[0]--;
				else	count[1]--;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
