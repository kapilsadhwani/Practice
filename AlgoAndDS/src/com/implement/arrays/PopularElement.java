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
		return element <= nums[mid] ? findLeftMostIdx(nums, element, left, mid)
				: findLeftMostIdx(nums, element, mid + 1, right);
	}
	
	// Div = numberOfUniqueElements, in this case 5
	public int findPopularElement(int[] nums){
		int p = (nums.length + 3) / 4; // (400+3)/4 = 100

		for (int i = 1; i <= 4; ++i) {
			if(p * i - 1 >= nums.length)
				break;
			
			int candidate = nums[p * i - 1]; // a[100*1 - 1] = a[99], a[199], a[299], a[399]
			int l = findLeftMostIdx(nums, candidate, p * (i - 1), p * i - 1); // {a[99],100*(1-1),100*1-1}
																			// = {a[99],0,99};
																			// {a[199],100,199};
																			// {a[299],200,299};
																			// {a[399],300,399};
			if (l + p - 1 >= nums.length)
				break;
			if (nums[l + p - 1] == candidate) {
				//System.out.println(candidate);
				return candidate;
			}
		}
		
		return -1;
	}
	
	// Div = numberOfUniqueElements, in this case 5
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
                        return o1.getValue().compareTo(o2.getValue());
                    }
                }).getKey();
		
		return frequentVal;
	}

	public static void main(String[] args) {
		//int[] nums = {1,1,1,1,2,4,5,5,5,6,6,6};
		int[] nums = {1,1,1,1,1,1,1,1,8,8,8,8,8,8,8,8,8};
		
		PopularElement pe = new PopularElement();
		
		System.out.println(pe.findPopularElement(nums));
		
		System.out.println(pe.findPopularElementUsingMap(nums));
	}
}
