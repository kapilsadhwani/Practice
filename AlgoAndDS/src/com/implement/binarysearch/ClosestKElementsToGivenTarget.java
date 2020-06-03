package com.implement.binarysearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class ClosestKElementsToGivenTarget {
	// Using Binary Search
	public static List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
	    int lo = 0, hi = arr.size() - k;
	    while (lo < hi) {
	        int mid = (lo + hi) / 2;
	        if (x - arr.get(mid) > arr.get(mid+k) - x)
	            lo = mid + 1;
	        else
	            hi = mid;
	    }
	    return arr.subList(lo, lo + k);
	}
	
	// Using Priority Queue, max heap as we have to retain minimum
	public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        // maxHeap, sort descendingly according to diff to x
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
            (a, b) -> Math.abs(x - b) == Math.abs(x - a) ? b - a : Math.abs(x - b) - Math.abs(x - a)
        );
        // each time, if we have better option, delete num with max diff from x, and insert the new num
        for (int num: arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        // convert heap back to List<Integer> and sort them to get the original order
        List<Integer> res = new ArrayList(maxHeap);
        Collections.sort(res);
        return res;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1,2,3,4,5 };
		int k=4, target=3;
		
		System.out.println("Priority Queue: " + findClosestElements(nums, k, target));
		System.out.println("Binary Search: " + findClosestElements(nums, k, target));
		
		k=4;
		target=5;
		
		System.out.println("Priority Queue: " + findClosestElements(nums, k, target));
		System.out.println("Binary Search: " + findClosestElements(nums, k, target));
	}

}
