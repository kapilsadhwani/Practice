package com.implement.arrays;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

public class PopularElement {
	int findLeftMostIdx(int[] nums, int element, int left, int right) {
		if (left == right)
			return left;
		int mid = (left + right) / 2;
		return element <= nums[mid] ? findLeftMostIdx(nums, element, left, mid - 1)
				: findLeftMostIdx(nums, element, mid + 1, right);
	}
	
	public int findPopularElementUsingMap(int[] nums) {
		if (nums == null || nums.length == 0)
			return-1;

		// build hash map : character and how often it appears
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int n : nums) {
			map.put(n, map.getOrDefault(n, 0) + 1);
		}
		
		int frequentVal = Collections.max(
				map.entrySet(), 
                new Comparator<Entry<Integer,Integer>>(){
                    @Override
                    public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
                        return o1.getValue() - o2.getValue();
                    }
                }).getKey();
		
		return frequentVal;
	}
	
	public static void main(String[] args) {
		int[] nums = { 1, 1, 1, 1, 2, 4, 5, 5, 5, 6, 6, 6 };

		PopularElement pe = new PopularElement();

		System.out.println(pe.findPopularElementUsingMap(nums));

		System.out.println("=================================");

		nums = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 8, 8, 8, 8, 8, 8, 8, 8, 8 };

		System.out.println(pe.findPopularElementUsingMap(nums));

		System.out.println("=================================");

		nums = new int[] { 1, 3, 2, 1, 4, 3, 2, 1, 3, 5, 3, 7, 3 };

		System.out.println(pe.findPopularElementUsingMap(nums));
	}
}
