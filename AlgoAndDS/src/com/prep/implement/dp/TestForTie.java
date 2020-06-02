package com.prep.implement.dp;

import java.util.Arrays;

public class TestForTie {
	static boolean canPartition(int[] nums) {
		int sum = 0;
		for (int num : nums)
			sum = sum + num;
		if (sum % 2 == 1)
			return false;

		int target = sum / 2;

		int[][] cache = new int[nums.length + 1][target + 1];

		for (int i = 0; i < cache.length; i++)
			Arrays.fill(cache[i], -1);

		return canPartition(nums, 0, target, cache) == 1;
	}

	static int canPartition(int[] nums, int pos, int t, int[][] cache) {
		if (t == 0)
			return 1; // true
		
		if (pos == nums.length)
			return 0; // false
		
		if (cache[pos][t] != -1)
			return cache[pos][t];

		int flag = 0;

		// Do not include and check if we got the target
		flag = canPartition(nums, pos + 1, t, cache);

		if (flag == 1)
			return cache[pos][t] = flag;

		// Include only if t >= num at position and check if we got the target
		if (t >= nums[pos]) {
			flag = canPartition(nums, pos + 1, t - nums[pos], cache);
		}

		return cache[pos][t] = flag;
	}

	// O(nv) space;
	static boolean testTieDP(int[] nums) {
		int sum = 0;
		for (int num : nums)
			sum += num;
		if (sum % 2 == 1)
			return false;
		int target = sum / 2;

		/*
		 * Target goes from 0 to target sum Position goes from 0 to set length
		 * (i.e votes length)
		 * 
		 * dp[107][6] : Means our target is 107 and Items 0-5 are considered.
		 * Items 6 till end of the set are available. Is there a subarray
		 * set[6:] that can generate a subset with sum 107
		 */
		boolean[][] dp = new boolean[nums.length + 1][target + 1];

		/*
		 * For line 17 in the recursion We reversed the order because target==0
		 * executes first always even when pos == votes.length in the recursion
		 */
		for (int t = 1; t <= target; ++t)
			dp[nums.length][t] = false;

		/*
		 * For line 15 in the recursion We reversed the order because target==0
		 * executes first always even when pos == votes.length in the recursion
		 */
		for (int pos = 0; pos <= nums.length; ++pos)
			dp[pos][0] = true;

		// Recursion pos goes up in the recursion, hence in DP it will go down
		for (int pos = nums.length - 1; pos >= 0; pos--)
			for (int t = 1; t <= target; t++)
				dp[pos][t] = dp[pos + 1][t] || t >= nums[pos]
						&& dp[pos + 1][t - nums[pos]];

		return dp[0][target]; // Can we generate a subset (from the entire
								// array) with sum target
	}

	// O(V) space
	static int testTieFancy(int[] votes) {
		int sum = 0;
		for (int vote : votes)
			sum += vote;
		System.out.println("Sum: " + sum);
		if (sum % 2 == 1)
			return 0;
		sum /= 2;

		int[] scores = new int[sum + 1];
		for (int i = 0; i <= sum; i++)
			scores[i] = -1;

		for (int state = 0; state < votes.length; state++) {
			int v = votes[state];
			for (int j = sum; j > votes[state]; --j) {
				if (scores[j] == -1 && scores[j - votes[state]] != -1)
					scores[j] = state;
			}
			scores[votes[state]] = state;
			if (scores[sum] != -1)
				break;
		}

		int result = 0;
		if (scores[sum] != -1) {
			result = 1;
			int state;
			for (int i = sum; i != 0; i -= votes[state]) {
				state = scores[i];
				System.out.println(state + " " + votes[state] + " " + i);
			}
		}

		return result;
	}

	public static void main(String[] args) {
		int[] nums = { 9, 3, 11, 6, 55, 9, 7, 3, 29, 16, 4, 4, 20, 11, 6, 6, 8,
				8, 4, 10, 11, 16, 10, 6, 10, 3, 5, 6, 4, 14, 5, 29, 15, 3, 18,
				7, 7, 20, 4, 9, 3, 11, 38, 6, 3, 13, 12, 5, 10, 3, 3 };
		System.out.println(testTieDP(nums));

		System.out.println(canPartition(nums));
	}
}
