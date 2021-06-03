package com.implement.recursion;

import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class SubSetSum {
	static class Pair{
		int i;
		int j;
		String psf;		
		
		public Pair(int i, int j, String psf) {
			this.i = i;
			this.j = j;
			this.psf = psf;
		}
	}
	
	private static void powerSet(int[] nums, int pos, LinkedList<Integer> selected,
			List<List<Integer>> result) {
		if (pos == nums.length) {
			// add selected elements so far to the result set
			result.add(new LinkedList<>(selected));
			return;

		}
		int elem = nums[pos];

		// select the element and recurse for others
		selected.add(elem);
		powerSet(nums, pos + 1, selected, result);
		selected.removeLast();
		
		// do not select the element
		powerSet(nums, pos + 1, selected, result);
	}

	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		LinkedList<Integer> selected = new LinkedList<>();

		//result.add(new ArrayList<>()); // Empty set

		powerSet(nums, 0, selected, result);

		return result;
	}

	private static void uniqueSubsets(int[] nums, int pos,
			LinkedList<Integer> selected, List<List<Integer>> result, boolean include) {
		if (pos == nums.length) {
			// add selected elements so far to the result set
			result.add(new LinkedList<>(selected));
			return;
		}

		int elem = nums[pos];

		// Duplicate Check
		if (pos == 0 || nums[pos] != nums[pos - 1] || include == true) {
			// select the element and recurse for others
			selected.add(elem);

			uniqueSubsets(nums, pos + 1, selected, result, true);
			
			selected.removeLast();
		}

		// do not select the element
		uniqueSubsets(nums, pos + 1, selected, result, false);
	}

	/*
	 * Given a collection of integers that might contain duplicates, nums, 
	 * return all possible subsets
	 */
	public static List<List<Integer>> uniqueSubsets(int[] nums) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		LinkedList<Integer> selected = new LinkedList<>();

		Arrays.sort(nums);

		uniqueSubsets(nums, 0, selected, result, true);

		return result;
	}
	
	private static void comboOfSizeK(int[] nums, int pos, LinkedList<Integer> selected,
			List<List<Integer>> result, int k) {
		if(selected.size() == k){
			result.add(new LinkedList<>(selected));
			return;
		}
		
		if (pos == nums.length) {
			return;
		}
		
		
		int elem = nums[pos];

		// select the element and recurse for others
		selected.add(elem);
		comboOfSizeK(nums, pos + 1, selected, result, k);
		selected.removeLast();
		
		// do not select the element
		comboOfSizeK(nums, pos + 1, selected, result, k);
	}
	
	public static List<List<Integer>> subsetsSizeK(int[] nums, int k) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		LinkedList<Integer> selected = new LinkedList<>();
		
		comboOfSizeK(nums, 0, selected, result, k);

		return result;
	}
	
	private static void subsetsWithTargetSum(int[] nums, int pos, LinkedList<Integer> selected,
			List<List<Integer>> result, int target) {		
		if(target == 0){
			result.add(new LinkedList<>(selected));
			return;
		}
		
		if (pos == nums.length) {
			return;
		}
		
		
		int elem = nums[pos];

		if(target >= elem){
			// select the element and recurse for others
			selected.add(elem);
			subsetsWithTargetSum(nums, pos + 1, selected, result, target - elem);
			selected.removeLast();
		}
		
		// do not select the element
		subsetsWithTargetSum(nums, pos + 1, selected, result, target);
	}

	public static List<List<Integer>> subsetsWithTargetSum(int[] nums, int target) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		LinkedList<Integer> selected = new LinkedList<>();
		
		subsetsWithTargetSum(nums, 0, selected, result, target);

		return result;
	}

	private static int countSubsetsWithTargetSumMemo(int[] nums, int pos, int tgtSum, int[][] cache) {
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
			cache[pos][tgtSum] = countSubsetsWithTargetSumMemo(nums, pos+1, tgtSum - nums[pos], cache)
							+ countSubsetsWithTargetSumMemo(nums, pos+1, tgtSum, cache);
		} else {
			cache[pos][tgtSum] = countSubsetsWithTargetSumMemo(nums, pos+1, tgtSum, cache);
		}

		return cache[pos][tgtSum];
	}

	public static int countSubsetsWithTargetSumMemo(int[] nums, int tgtSum) {
		int n = nums.length;
		int cache[][] = new int[n + 1][tgtSum + 1];

		for (int i = 0; i < cache.length; i++) {
			/*for (int j = 0; j < cache[0].length; j++) {
				cache[i][j] = -1;
			}*/
			
			Arrays.fill(cache[i], -1);
		}

		return countSubsetsWithTargetSumMemo(nums, 0, tgtSum, cache);
	}
	

	// Iterative DP: Approach 1
	public static int countCombinationsBoundedIterative(int[] A, int tgtSum) {
		int n = A.length;
		int cache[][] = new int[n + 1][tgtSum + 1];

		// When n = 0, no items to choose from, count = 0 except for 1st cell
		for (int j = 1; j < cache[0].length; j++) {
			cache[0][j] = 0;
		}

		// When Target = 0, One way to achieve it is empty subset
		for (int i = 0; i < cache.length; i++) {
			cache[i][0] = 1;
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
	
	// // Iterative DP: Approach 2 (Unique combinations)
	static long makeChangeUniqueCombo(int[] coins, int A) {
		long[] dp = new long[A + 1];
		dp[0] = 1;

		for (int coin : coins) {
			for (int amt = coin; amt < dp.length; amt++) {
				dp[amt] = dp[amt] + dp[amt - coin];
			}

		}

		return dp[A];
	}

	public static void printSubsetsWithTargetSum(int[] A, int tgtSum) {
		int n = A.length;
		boolean dp[][] = new boolean[n + 1][tgtSum + 1];

		// When n = 0, no items to choose from, count = 0 except for 1st cell
		for (int j = 1; j < dp[0].length; j++) {
			dp[0][j] = false;
		}

		// When Target = 0, One way to achieve it is empty subset
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = true;
		}

		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if (j >= A[i - 1]) {
					dp[i][j] = dp[i - 1][j - A[i - 1]] || dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}

		System.out.println(dp[n][tgtSum]);
		
		System.out.println("Printing their indexes: ");
		
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(n, tgtSum, ""));
		
		Pair rem = null;
		while(queue.size() > 0){
			rem = queue.poll();
			
			if(rem.i == 0 || rem.j == 0){
				System.out.println(rem.psf);
			}else{
				// Include
				if(rem.j >= A[rem.i - 1]){
					boolean inc = dp[rem.i - 1][rem.j - A[rem.i - 1]];
					
					if(inc == true){
						queue.add(new Pair(rem.i - 1, rem.j - A[rem.i - 1], (rem.i - 1) + " " + rem.psf));
					}
				}
				
				boolean exc = dp[rem.i - 1][rem.j];
				if(exc == true){
					// Exclude and do not print corresponding index
					queue.add(new Pair(rem.i - 1, rem.j, rem.psf));
				}
			}
		}
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
	
	// Number of ways to reach a given target
	static long countCombinationsUnboundedAndUniqueIter(int[] nums, int target) {
		long[] dp = new long[target + 1];
		dp[0] = 1;

		for (int num : nums) {
			for (int tgt = num; tgt < dp.length; tgt++) {
				dp[tgt] = dp[tgt] + dp[tgt - num];
			}
		}

		return dp[target];
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
    
	// Permutation i.e. Combinations with duplicates allowed - like 2,1,1 is different from 1,1,2
	static long countCombinationUnboundedAndDupIter(int[] nums, int target) {
		long[] dp = new long[target + 1];
		dp[0] = 1;

		for (int tgt = 1; tgt < dp.length; tgt++) {
			long total = 0;
			for (int num : nums) {
				if (tgt >= num) {
					total = total + dp[tgt - num];
				}
			}

			dp[tgt] = total;
		}

		return dp[target];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<Integer>> result;
		int[] nums = { 4, 11, 5, 10, 6, 20, 1 };
		
		result = subsets(nums);
		System.out.println(result);
		
		System.out.println("\n========== Subsets size K ==========");
		nums = new int[] { 1, 2, 3, 4 };
		int k = 2;
		result = subsetsSizeK(nums, k);
		System.out.println(result);
		
		System.out.println("\n====== Target=21 ==========");
		nums = new int[] { 4, 11, 5, 10, 6, 20, 1 };
		int tgtSum = 21;
		
		System.out.println("Given array: " + Arrays.toString(nums));
		
		result = subsetsWithTargetSum(nums, tgtSum);
		System.out.println(result);

		System.out.println("\n Number of ways to reach target (Memoization): "
						+ countSubsetsWithTargetSumMemo(nums, tgtSum));
		System.out.println(" Number of ways to reach target (Iterative DP): "
						+ countCombinationsBoundedIterative(nums, tgtSum));
		
		System.out.println("Subsets using iterative DP");
		
		printSubsetsWithTargetSum(nums, tgtSum);

		System.out.println("\n==========================");

		nums = new int[]{ 1, 2 };
		tgtSum = 4;

		System.out.println(Arrays.toString(nums) + ", Tagret: " + tgtSum);

		System.out.println("\n Number of ways to reach target (Bounded) : "
				+ countSubsetsWithTargetSumMemo(nums, tgtSum));
		
		System.out.println(" Number of ways to reach target (Unbounded with Unique Combo) Memo: "
				+ countCombinationsUnboundedAndUnique(nums, tgtSum));
		
		System.out.println(" Number of ways to reach target (Unbounded with Unique Combo) Iter: "
				+ countCombinationsUnboundedAndUniqueIter(nums, tgtSum));
		
		System.out.println(" Number of combinations to reach target (Unbounded with Duplicates Combo) Memo: "
				+ countCombinationUnboundedAndDup(nums, tgtSum));
		
		System.out.println(" Number of combinations to reach target (Unbounded with Duplicates Combo) Iter: "
				+ countCombinationUnboundedAndDupIter(nums, tgtSum));

		System.out.println("\n==========================");

		nums = new int[] { 4, 4, 4, 1, 4 };
		result = uniqueSubsets(nums);
		System.out.println(result);
	}

}
