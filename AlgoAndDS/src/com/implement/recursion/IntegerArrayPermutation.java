package com.implement.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegerArrayPermutation {
	private void permute(ArrayList<Integer> listOfNums, int pos, List<List<Integer>> result) {
		// TODO Auto-generated method stub
		
		if(pos == listOfNums.size()){
			result.add(new ArrayList<Integer>(listOfNums));
			return;
		}
		
		for(int i = pos; i < listOfNums.size(); i++){
			// Swap i-th integer first  in the current permutation
			Collections.swap(listOfNums, pos, i);
			
			// use next integers to complete the permutations			
			permute(listOfNums, pos+1, result);
			
			// backtrack
			Collections.swap(listOfNums, pos, i);
		}
	}
    
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
    	
    	// Convert nums into list since the output is a list of lists
	    ArrayList<Integer> listOfNums = new ArrayList<Integer>();
	    
	    for (int num : nums)
	    	listOfNums.add(num);
	    
        permute(listOfNums, 0, result);
		
		return result;
    }
    
    private Map<Integer, Integer> buildFreqTable(int[] nums) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int num : nums) {
			/*if (map.get(num) == null) {
				map.put(num, 0);
			}
			
			map.put(num, map.get(num) + 1);*/
			
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		return map;
	}
    
	private void permuteWithDup(Map<Integer, Integer> map, int pos, int n,
			List<Integer> perm, List<List<Integer>> result) {
		// TODO Auto-generated method stub

		if (pos == n) {
			result.add(new ArrayList<Integer>(perm));
			return;
		}

		/*
		 * Try remaining letters for next char, and generate remaining permutations.
		 */
		for (int num : map.keySet()) {
			int count = map.get(num);
			if (count > 0) {
				// Include
				map.put(num, count - 1);
				perm.add(num);
				
				permuteWithDup(map, pos+1, n, perm, result);
				
				// Don't include i.e revert count, similar to unswap
				perm.remove(perm.size()-1);
				map.put(num, count);
			}
		}
	}

    public List<List<Integer>> permuteUnique(int[] nums) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	
    	// Convert nums into list since the output is a list of lists
	    List<Integer> perm = new ArrayList<Integer>();
	    
	    Map<Integer, Integer> map = buildFreqTable(nums);
	    
        permuteWithDup(map, 0, nums.length, perm, result);
		
		return result;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1, 2, 3 };
		IntegerArrayPermutation iap = new IntegerArrayPermutation();

		List<List<Integer>> result = iap.permute(nums);

		System.out.println(result);
		
		nums = new int[]{1,1,2};
		
		result = iap.permuteUnique(nums);
		
		System.out.println(result);
	}

}
