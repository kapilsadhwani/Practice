package com.implement.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.implement.sort.SortUtils;

public class TopKElements {     
     /*
      * @param nums an integer array
      * @param k an integer
      * @return the top k largest numbers in array
      */
     public static Integer[] topkMinHeap(Integer[] nums, int k) {
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
         
         Integer[] result = new Integer[k];
         for (int i = result.length-1; i >=0; i--) {
             result[i] = minheap.poll();
         }
         return result;
     }
     
     public static Integer[] bottomkMaxHeap(Integer[] nums, int k) {
         PriorityQueue<Integer> maxheap = new PriorityQueue<Integer>(
        		 new Comparator<Integer>(){
		        	 @Override
		        	 public int compare(Integer n1, Integer n2){
		        		 return n2 - n1;
		        	 }
		         });
         for (int i = 0; i < k; i++) {
        	 maxheap.offer(nums[i]);
         }
         for (int i = k; i < nums.length; i++) {
             if (nums[i] < maxheap.peek()) {
            	 maxheap.poll();
            	 maxheap.offer(nums[i]);
             }
         }
         
         Integer[] result = new Integer[k];
         for (int i = result.length-1; i >=0; i--) {
             result[i] = maxheap.poll();
         }
         return result;
     }
     
     public static void main(String[] args) {
 		// TODO Auto-generated method stub
 		Integer[] array1 = {54553, 201557, 858524, 95183, 665451, 314047, 875607, 596111, 952362};
 		Integer[] array2 = {1,2,3,4,5,6,7};
 		Integer[] array3 = {7,6,5,4,3,2,1};
 		Integer[] array4 = {12,5,787,1,23};
 		Integer[] array5 = {1,23,12,9,30,2,50};
 		
 		Integer[] result = null;
 		
 		result = topkMinHeap(array1,3);
 		SortUtils.printArray(result);
 		result = bottomkMaxHeap(array1,3);
 		SortUtils.printArray(result);
 		
 		result = topkMinHeap(array2,4);
 		SortUtils.printArray(result);
 		result = bottomkMaxHeap(array2,4);
 		SortUtils.printArray(result);
 		
 		result = topkMinHeap(array3,4);
 		SortUtils.printArray(result);
 		result = bottomkMaxHeap(array3,4);
 		SortUtils.printArray(result);
 		
 		result = topkMinHeap(array4,2);
 		SortUtils.printArray(result);
 		result = bottomkMaxHeap(array4,2);
 		SortUtils.printArray(result);
 		
 		result = topkMinHeap(array5,3);
 		SortUtils.printArray(result);
 		result = bottomkMaxHeap(array5,3);
 		SortUtils.printArray(result);
 	}
}