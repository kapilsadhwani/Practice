package com.implement.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntegerArrayCombination {
	private void comboOfSizeK(int n, int k, int elem, List<Integer> selected,
			List<List<Integer>> result) {
		if(selected.size() == k){
			result.add(new ArrayList<>(selected));
			return;
		}
		
		if (elem > n) {
			return;
		}

		// select the element and recurse for others
		selected.add(elem);
		comboOfSizeK(n, k, elem + 1, selected, result);

		// do not select the element
		selected.remove(selected.size() - 1);
		comboOfSizeK(n, k, elem + 1, selected, result);
	}

	public List<List<Integer>> combine(int n, int k) {
		if(k > n) return new ArrayList<List<Integer>>();
			
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> selected = new ArrayList<>();
		
		/*
		 *  n will remain same in the recursion and is the upperbound
		 *  elem is the next element to be picked
		 */
		comboOfSizeK(n, k, 1, selected, result);

		return result;
	}
	
	private void combinationSum(int[] nums, int target, int pos,
			List<Integer> selected, List<List<Integer>> result) {
		if (target == 0){
			result.add(new ArrayList<>(selected));
			return;
		}
		
		if (pos == nums.length) {
			return;

		}
		int elem = nums[pos];

		/*
		 *  select the element and recurse
		 *  Same element is available for next recursion
		 */
        if(target >= elem){
		    selected.add(elem);
		    combinationSum(nums, target - elem, pos, selected, result);

		    selected.remove(selected.size() - 1);
        }
        
        // do not select the element
        combinationSum(nums, target, pos + 1, selected, result);
	}
	
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> selected = new ArrayList<>();

		combinationSum(candidates, target, 0, selected, result);

		return result;
	}
	
	// Top-down approach - DP: Memoization
    public static int countCombinationSumWithDupTupleMemo(int[] nums, int tgt, int[] cache) {
    	if (tgt == 0)
            return 1;
    	
    	if (cache[tgt] > 0) return cache[tgt];
    	
    	int total = 0;
    	
    	for (int num : nums){
    		if(tgt >= num){
    			total = total + countCombinationSumWithDupTupleMemo(nums, tgt - num, cache);
    		}
    	}        
        
		cache[tgt] = total;
        return cache[tgt];
    }
    
    /*
     * Given an integer array with all positive numbers and no duplicates, 
     * find the number of possible combinations that add up to a positive integer target.
     */
    public int countCombinationSumWithDupTuple(int[] nums, int target) {
    	int cache[] = new int[target + 1];
    	
        return countCombinationSumWithDupTupleMemo(nums, target, cache);
    }
    
    private void allComboSumsWithDup(int[] nums, int target, int pos,
			List<Integer> selected, List<List<Integer>> result,
			boolean include) {
		if (target == 0){
			result.add(new ArrayList<>(selected));
			return;
		}
		
		if (pos == nums.length) {
			return;
		}

		int elem = nums[pos];

		// duplicate checking (convert && to ||)
		if (pos == 0 || nums[pos - 1] != nums[pos] || include == true) {
            if(target >= elem){
			    // select the element and recurse for others
			    selected.add(elem);

			    allComboSumsWithDup(nums, target - elem, pos + 1, selected, result, true);

			    selected.remove(selected.size() - 1);
            }
		}

		// do not select the element
		allComboSumsWithDup(nums, target, pos + 1, selected, result, false);
	}

	public List<List<Integer>> combinationSumWithDup(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> selected = new ArrayList<>();

		Arrays.sort(candidates);

		allComboSumsWithDup(candidates, target, 0, selected, result, true);

		return result;
	}
	
	private void comboSumOfSizeK(int n, int k, int target, int elem, List<Integer> selected,
			List<List<Integer>> result) {
		if(selected.size() == k){
			if (target == 0){
				result.add(new ArrayList<>(selected));
			}			
			return;
		}
		
		if(elem > n) return;

        if(target >= elem){
            // select the element and recurse for others
            selected.add(elem);
            comboSumOfSizeK(n, k, target - elem, elem + 1, selected, result);

            selected.remove(selected.size() - 1);
        }
        
        // do not select the element
		comboSumOfSizeK(n, k, target, elem + 1, selected, result);
	}

	public List<List<Integer>> combinationSumOfSizeK(int k, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> selected = new ArrayList<>();
		int n = 9;
		
		/*
		 *  n will remain same in the recursion and is the upperbound
		 *  elem is the next element to be picked
		 */
		comboSumOfSizeK(n, k, target, 1, selected, result);

		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 5;
		int k = 2;
		
		IntegerArrayCombination iap = new IntegerArrayCombination();

		List<List<Integer>> result = iap.combine(n, k);

		System.out.println(result);
		
		System.out.println("\n=== Combination Sum ===");

		int[] nums = { 2, 3, 6, 7 };
		int target = 7;
		result = iap.combinationSum(nums, target);
		System.out.println(result);
		
		nums = new int[]{ 2, 3, 5 };
		target = 8;
		result = iap.combinationSum(nums, target);
		System.out.println(result);
		
		System.out.println("\n=== Combination Sum With Duplicates ===");

		nums = new int[]{ 10,1,2,7,6,1,5 };
		target = 8;
		result = iap.combinationSumWithDup(nums, target);
		System.out.println(result);
		
		nums = new int[]{ 2,5,2,1,2 };
		target = 5;
		result = iap.combinationSumWithDup(nums, target);
		System.out.println(result);
		
		System.out.println("\n=== Combination Sum of size K ===");

		k = 3;
		target = 7;
		result = iap.combinationSumOfSizeK(k, target);
		System.out.println(result);
		
		k = 3;
		target = 9;
		result = iap.combinationSumOfSizeK(k, target);
		System.out.println(result);
		
		System.out.println("\n=== Number of possible combinations ===");

		nums = new int[]{ 1, 2, 3 };
		target = 4;
		int count = iap.countCombinationSumWithDupTuple(nums, target);
		System.out.println(count);
		
		result = iap.combinationSum(nums, target);
		System.out.println(result);
	}

}
