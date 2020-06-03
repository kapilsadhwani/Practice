package com.implement.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSeq {
	/*
	 * Time complexity : O(n). Although the time complexity appears to be
	 * quadratic due to the while loop nested within the for loop, closer
	 * inspection reveals it to be linear. Because the while loop is reached
	 * only when currentNum marks the beginning of a sequence (i.e. currentNum-1
	 * is not present in nums), the while loop can only run for n iterations
	 * throughout the entire runtime of the algorithm. This means that despite
	 * looking like O(nxn) complexity, the nested loops actually run
	 * in O(n+n)=O(n) time. All other computations occur in
	 * constant time, so the overall runtime is linear.
	 * 
	 * Space complexity : O(n).
	 * 
	 * In order to set up O(1) containment lookups, we allocate linear space
	 * for a hash table to store the O(n) numbers in nums. Other than that,
	 * the space complexity is identical to that of the brute force solution.
	 */
	
	public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num-1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum+1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

	/*
	 * Time complexity : O(nlgn).
	 * The main for loop does constant work n times, so the algorithm's time complexity 
	 * is dominated by the invocation of sort, which will run in O(nlgn) time for any 
	 * sensible implementation.
	 * 
	 * Space complexity : O(1) (or O(n)).
	 * For the implementations provided here, the space complexity is constant because we sort 
	 * the input array in place. If we are not allowed to modify the input array, we must spend 
	 * linear space to store a sorted copy.
	 */
	public int longestConsecutiveSortApproach(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);		// QuickSort Algo

        int longestStreak = 1;
        int currentStreak = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                if (nums[i] == nums[i-1]+1) {
                    currentStreak += 1;
                }
                else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }

        return Math.max(longestStreak, currentStreak);
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
