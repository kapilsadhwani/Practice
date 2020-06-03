package com.implement.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSetSum {
	private static void allSubsets(int[] nums, int pos, List<Integer> selected,
			List<List<Integer>> result) {
		if (pos == nums.length) {
			// add selected elements so far to the result set
			result.add(new ArrayList<>(selected));
			return;

		}
		int elem = nums[pos];

		// select the element and recurse for others
		selected.add(elem);
		allSubsets(nums, pos + 1, selected, result);

		// add selected elements so far to the result set
		//result.add(new ArrayList<>(selected));

		// do not select the element
		selected.remove(selected.size() - 1);
		allSubsets(nums, pos + 1, selected, result);
	}

	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> selected = new ArrayList<>();

		//result.add(new ArrayList<>()); // Empty set

		allSubsets(nums, 0, selected, result);

		return result;
	}

	private static void allSubsetsWithDup(int[] nums, int pos,
			List<Integer> selected, List<List<Integer>> result, boolean include) {
		if (pos == nums.length) {
			// add selected elements so far to the result set
			result.add(new ArrayList<>(selected));
			return;
		}

		int elem = nums[pos];

		// duplicate checking (convert && to ||)
		if (pos == 0 || nums[pos] != nums[pos - 1] || include == true) {
			// select the element and recurse for others
			selected.add(elem);

			allSubsetsWithDup(nums, pos + 1, selected, result, true);

			selected.remove(selected.size() - 1);
		}

		// do not select the element
		allSubsetsWithDup(nums, pos + 1, selected, result, false);
	}

	public static List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> selected = new ArrayList<>();

		Arrays.sort(nums);

		allSubsetsWithDup(nums, 0, selected, result, true);

		return result;
	}
	
	private static void comboOfSizeK(int[] nums, int pos, List<Integer> selected,
			List<List<Integer>> result, int k) {
		if(selected.size() == k){
			result.add(new ArrayList<>(selected));
			return;
		}
		
		if (pos == nums.length) {
			return;
		}
		
		
		int elem = nums[pos];

		// select the element and recurse for others
		selected.add(elem);
		comboOfSizeK(nums, pos + 1, selected, result, k);

		// do not select the element
		selected.remove(selected.size() - 1);
		comboOfSizeK(nums, pos + 1, selected, result, k);
	}
	
	public static List<List<Integer>> subsetsSizeK(int[] nums, int k) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> selected = new ArrayList<>();
		
		comboOfSizeK(nums, 0, selected, result, k);

		return result;
	}
	
	private static void subsetsWithTargetSum(int[] nums, int pos, List<Integer> selected,
			List<List<Integer>> result, int target) {		
		if(target == 0){
			result.add(new ArrayList<>(selected));
			return;
		}
		
		if (pos == nums.length) {
			return;
		}
		
		
		int elem = nums[pos];

		// select the element and recurse for others
		selected.add(elem);
		subsetsWithTargetSum(nums, pos + 1, selected, result, target - elem);

		// do not select the element
		selected.remove(selected.size() - 1);
		subsetsWithTargetSum(nums, pos + 1, selected, result, target);
	}

	public static List<List<Integer>> subsetsWithTargetSum(int[] nums, int pos, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> selected = new ArrayList<>();
		
		subsetsWithTargetSum(nums, 0, selected, result, target);

		return result;
	}

	private static int countSubsetsWithGivenSumMemo(int[] nums, int pos, int tgtSum, int[][] cache) {
		if (tgtSum == 0) {
			return 1;
		}

		if (pos == nums.length) {
			return 0;
		}

		if (cache[pos][tgtSum] != -1) {
			return cache[pos][tgtSum];
		}

		if (tgtSum >= nums[pos]) {
			cache[pos][tgtSum] = countSubsetsWithGivenSumMemo(nums, pos+1, tgtSum - nums[pos], cache)
							+ countSubsetsWithGivenSumMemo(nums, pos+1, tgtSum, cache);
		} else {
			cache[pos][tgtSum] = countSubsetsWithGivenSumMemo(nums, pos+1, tgtSum, cache);
		}

		return cache[pos][tgtSum];
	}

	public static int countSubsetsWithGivenSumR(int[] nums, int tgtSum) {
		int n = nums.length;
		int cache[][] = new int[n + 1][tgtSum + 1];

		for (int i = 0; i < cache.length; i++) {
			/*for (int j = 0; j < cache[0].length; j++) {
				cache[i][j] = -1;
			}*/
			
			Arrays.fill(cache[i], -1);
		}

		return countSubsetsWithGivenSumMemo(nums, 0, tgtSum, cache);
	}
	

	// Iterative DP
	public static int countCombinationsBoundedIterative(int[] A, int tgtSum) {
		int n = A.length;
		int cache[][] = new int[n + 1][tgtSum + 1];

		// When n=0
		for (int c = 1; c < cache[0].length; c++) {
			cache[0][c] = 0;
		}

		// When Target = 0
		for (int r = 0; r < cache.length; r++) {
			cache[r][0] = 1;
		}

		for (int i = 1; i < cache.length; i++) {
			for (int j = 1; j < cache[0].length; j++) {
				if (j >= A[i - 1]) {
					cache[i][j] = cache[i - 1][j - A[i - 1]] + cache[i - 1][j];
				} else {
					cache[i][j] = cache[i - 1][j];
				}
			}
		}

		return cache[n][tgtSum];
	}
	
	private static int ks_CountSubsetUnboundedAndUniqueMemo(int[] nums, int pos, 
								int tgtSum, int[][] cache) {
		if (tgtSum == 0) {
			return 1;
		}

		if (pos == nums.length) {
			return 0;
		}

		if (cache[pos][tgtSum] != -1) {
			return cache[pos][tgtSum];
		}

		if (tgtSum >= nums[pos]) {
			cache[pos][tgtSum] = ks_CountSubsetUnboundedAndUniqueMemo(nums, pos, tgtSum - nums[pos], cache)
							+ ks_CountSubsetUnboundedAndUniqueMemo(nums, pos+1, tgtSum, cache);
		} else {
			cache[pos][tgtSum] = ks_CountSubsetUnboundedAndUniqueMemo(nums, pos+1, tgtSum, cache);
		}

		return cache[pos][tgtSum];
	}

	public static int countCombinationsUnboundedAndUnique(int[] nums, int tgtSum) {
		int n = nums.length;
		int cache[][] = new int[n + 1][tgtSum + 1];

		for (int i = 0; i < cache.length; i++) {
			for (int j = 0; j < cache[0].length; j++) {
				cache[i][j] = -1;
			}
		}

		return ks_CountSubsetUnboundedAndUniqueMemo(nums, 0, tgtSum, cache);
	}
	
	// Top-down approach - DP: Memoization
    public static int countCombinationUnboundedAndDupMemo(int[] nums, int target, int[] cache) {
    	if (target == 0)
            return 1;
    	
    	if (cache[target] > 0) return cache[target];
    	
    	int total = 0;
    	
    	for (int num : nums){
    		if(target >= num){
    			total = total + countCombinationUnboundedAndDupMemo(nums, target - num, cache);
    		}
    	}        
        
		cache[target] = total;
        return cache[target];
    }
    
    /*
     * Given an integer array with all positive numbers and no duplicates, 
     * find the number of possible combinations that add up to a positive integer target.
     */
    public static int countCombinationUnboundedAndDup(int[] nums, int target) {
    	int cache[] = new int[target + 1];
    	
        return countCombinationUnboundedAndDupMemo(nums, target, cache);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<Integer>> result;
		int[] nums = { 4, 11, 5, 10, 6, 20, 1 };
		int tgtSum = 21;

		result = subsetsWithTargetSum(nums, 0, tgtSum);
		System.out.println(result);

		System.out
				.println("\n Number of ways to reach target (Recursive DP): "
						+ countSubsetsWithGivenSumR(nums, tgtSum));
		System.out.println(" Number of ways to reach target (Iterative DP): "
				+ countCombinationsBoundedIterative(nums, tgtSum));

		System.out.println("\n==========================");

		nums = new int[]{ 1, 2 };
		tgtSum = 4;

		System.out.println(result);

		System.out.println("\n Number of ways to reach target (Bounded) : "
				+ countSubsetsWithGivenSumR(nums, tgtSum));
		
		System.out.println(" Number of ways to reach target (Unbounded with Unique Combo) : "
				+ countCombinationsUnboundedAndUnique(nums, tgtSum));
		
		System.out.println(" Number of combinations to reach target (Unbounded with Duplicates Combo): "
				+ countCombinationUnboundedAndDup(nums, tgtSum));

		System.out.println("\n==========================");

		nums = new int[] { 4, 4, 4, 1, 4 };
		result = subsetsWithDup(nums);
		System.out.println(result);
		
		System.out.println("\n=== Subsets size K ===");
		nums = new int[] { 1, 2, 3, 4 };
		int k=2;
		result = subsetsSizeK(nums, k);
		System.out.println(result);
	}

}
