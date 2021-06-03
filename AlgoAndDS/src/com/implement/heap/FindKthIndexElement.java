package com.implement.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FindKthIndexElement {
	public static int findkthLargest(int[] nums, int k) {
		if(k > nums.length) return -1; 
			
        PriorityQueue<Integer> minheap = new PriorityQueue<Integer>(k);
        for (int i = 0; i < k; i++) {
           minheap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minheap.peek()) {
                minheap.poll();
                minheap.offer(nums[i]);
            }
        }
        
        return minheap.peek();
	}
	
	public static int findkthSmallest(int[] nums, int k) {
		if(k > nums.length) return -1; 
			
        PriorityQueue<Integer> maxheap = new PriorityQueue<Integer>(k,
        										(n1, n2) -> n2 - n1);
        
        for (int i = 0; i < k; i++) {
        	maxheap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] < maxheap.peek()) {
            	maxheap.poll();
            	maxheap.offer(nums[i]);
            }
        }
        
        return maxheap.peek();
    }
	
	public static void swap(int[] data,int lower,int upper){
		int temp;

		temp = data[lower];
		data[lower] = data[upper];
		data[upper] = temp;
	}
	
	// Partition by pivot at start
	private static int partition(int[] nums, int start, int end) {
		// TODO Auto-generated method stub
		int pivot = start, left = start + 1, right = end;

		while (left < right) {
			while (left <= right && nums[left] < nums[pivot])
				left++;
			while (left <= right && nums[pivot] < nums[right])
				right--;

			if (left < right)
				swap(nums, left, right);
		}

		if (nums[right] < nums[pivot])
			swap(nums, pivot, right);

		return right;
	}
	
	private static int findKthSmallestQS(int[] nums, int k) {
		if(k > nums.length) return -1;
		
		int low = 0, high = nums.length - 1;

		// TODO Auto-generated method stub
		while (low < high) {
			int pivot = partition(nums, low, high);
			
			if(pivot == k-1)
				return nums[pivot];
			if (pivot < k)
				low = pivot + 1;
			else 
				high = pivot - 1;
		}
		
		return nums[low];
	}
	
	public static int findKthLargestQS(int[] nums, int k) {
		if(k > nums.length) return -1;
		
		return findKthSmallestQS(nums, nums.length - k + 1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		try {
			int[] originalArray = {53, 48, 10, 73, 13, 26, 12, 31, 39, 54};
			int k = 5; // sc.nextInt();
			
			Arrays.sort(originalArray);
			System.out.println(Arrays.toString(originalArray));
			
			originalArray = new int[]{53, 48, 10, 73, 13, 26, 12, 31, 39, 54};
			
			int kthSmallest = findKthSmallestQS(originalArray, k);
			int kthLargest = findKthLargestQS(originalArray, k);
			
			System.out.println("Quick Select:");
			System.out.println(kthSmallest + ", " + kthLargest);
			
			System.out.println("Heap:");
			System.out.println(findkthSmallest(originalArray, k) + ", " + findkthLargest(originalArray, k));
			
			k = 3;
			
			kthSmallest = findKthSmallestQS(originalArray, k);
			kthLargest = findKthLargestQS(originalArray, k);
			
			System.out.println("Quick Select:");
			System.out.println(kthSmallest + ", " + kthLargest);
			
			System.out.println("Heap:");
			System.out.println(findkthSmallest(originalArray, k) + ", " + findkthLargest(originalArray, k));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sc.close();
		}
		
	}

	

}
