package com.implement.arrays;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.implement.tree.TreeNode;

public class ArrayMisc {
	public boolean containsDuplicate(int[] nums) {
		if(nums.length < 2) return false;
		
		Set<Integer> setOfInts = new HashSet<Integer>();
		
		for (int x: nums) {
	        if (setOfInts.contains(x)) return true;
	        setOfInts.add(x);
	    }

		return false;
	}

	private int removeDuplicatesUnsorted(int[] nums) {
		Set<Integer> setOfInts = new HashSet<Integer>();
		int k=0, i=0;
		
		if(nums.length < 2) return nums.length;
		
		while(i < nums.length){
			if(!setOfInts.contains(nums[i])){
				setOfInts.add(nums[i]);
				nums[k++] = nums[i];
			}
			
			i++;
		}

		return k;
	}
	
	private int removeDuplicatesSorted(int[] nums) {
		int k=1, i=1;
		
		if(nums.length < 2) return nums.length;
		
		while(i < nums.length){
			if(nums[i] != nums[i-1]){
				nums[k++] = nums[i];
			}
			
			i++;
		}

		return k;
	}
	
	/**
     * @param A: a array of sorted integers
     * @return : return an integer
     * Duplicates are allowed at most twice
     */
    public int removeDuplicates(int[] arr) {
        if (arr.length < 3) {
            return arr.length;
        }
        boolean exceeded = false;
        int prevDup = -1;
        int k = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                if (prevDup == arr[i]) {
                    exceeded = true;
                } else {
                	prevDup = arr[i];
                    exceeded = false;
                }

                if (exceeded) {
                    continue;
                }
            }
            
            arr[k++] = arr[i];
        }
        return k;
    }
    
    /**
     * @param A:    A list of integers
     * @param elem: An integer
     * @return: The new length after remove
     */
    public int removeElement(int[] A, int elem) {
        int k = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] != elem) {
                A[k++] = A[i];
            }
        }
        return k;
    }


	private static void printArray(int[] arr, int length) {
		for (int i=0; i<length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
	
	/*
	 * Intersection with no Duplicates
	 * Use Map, if there are duplicates & ArrayList to store output
	 */
	public int[] set_intersection(HashSet<Integer> smaller, HashSet<Integer> bigger) {
		int[] output = new int[smaller.size()];
		int idx = 0;
		for (Integer s : smaller)
			if (bigger.contains(s))
				output[idx++] = s;

		return output;
	}

	/**
     * @param nums1 an integer array
     * @param nums2 an integer array
     * @return an integer array
     */
	public int[] intersection(int[] nums1, int[] nums2) {
		HashSet<Integer> set1 = new HashSet<Integer>();
		for (Integer n : nums1)
			set1.add(n);
		HashSet<Integer> set2 = new HashSet<Integer>();
		for (Integer n : nums2)
			set2.add(n);

		if (set1.size() < set2.size())
			return set_intersection(set1, set2);
		else
			return set_intersection(set2, set1);
	}
    
    private TreeNode bstFromSortedArray(int[] nums, int left, int right) {
		// TODO Auto-generated method stub
		if (left > right)
			return null;

		int mid = left + (right - left) / 2;

		TreeNode root = new TreeNode(nums[mid]);
		root.left = bstFromSortedArray(nums, left, mid - 1);
		root.right = bstFromSortedArray(nums, mid + 1, right);

		return root;
	}

    public TreeNode bstFromSortedArray(int[] nums){
		return bstFromSortedArray(nums, 0, nums.length - 1);
	}

	// Recursive function to build a BST from a preorder sequence
	public static TreeNode bstFromPreorder(int[] preorder, AtomicInteger arrIdx, int min, int max) {
		if (arrIdx.get() == preorder.length) {
			return null;
		}

		// Return if next element of preorder traversal is not in the valid range
		int val = preorder[arrIdx.get()];
		if (val < min || val > max) {
			return null;
		}

		// Construct the root node and increment pIndex
		TreeNode root = new TreeNode(val);
		arrIdx.incrementAndGet();

		// Since all elements in the left sub-tree of a BST must be less
		// than the value of root node, set range as [min, val-1] and recur
		root.left = bstFromPreorder(preorder, arrIdx, min, val - 1);

		// Since all elements in the right sub-tree of a BST must be greater
		// than the value of root node, set range as [val+1..max] and recur
		root.right = bstFromPreorder(preorder, arrIdx, val + 1, max);

		return root;
	}

	// Build a BST from a preorder sequence
	public static TreeNode bstFromPreorder(int[] preorder) {
		// start from the root node (first element in preorder sequence)
		// use AtomicInteger as Integer is passed by value in Java
		AtomicInteger arrIdx = new AtomicInteger(0);

		// set range of the root node as [Integer.MIN_VALUE, Integer.MAX_VALUE] and recur
		return bstFromPreorder(preorder, arrIdx, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	// Returns index of key in arr[l...]
	// Search the number in sorted Array NO SIZE
	// if key is present, otherwise returns -1
	static int search(int num[], int key) {
		// Check array indexes 0, 2^0, 2^1, 2^2, ...
		int index = 0, exp = 0;
		
		if(num[0] == key) return 0;
		
		while (true) {
			try {
				if (num[index] == key)
					return index;
				else if (num[index] < key) {
					index = (int) Math.pow(2, exp);
					exp += 1;
				} else
					break;
			} catch (IndexOutOfBoundsException ex) {
				break;
			}
		}
		
		// Binary Search
		// Since while loop determined that key is not available at indexes below index/2
		int low = (index/2)+1;
		int high = index - 1;
		int mid;

		while (low < high) {
			// Find mid
			mid = (low + high) / 2;

			try {
				if (num[mid] == key)
					return mid;
				else if (num[mid] < key) {
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			} catch (IndexOutOfBoundsException ex) {
				high = mid-1;
			}
		}

		return num[low] == key ? low : -1;
	}

	public static void main(String[] args) {
		int[] arrUnSorted = { 2, 5, 2, 3, 5, 7, 2, 5, 8, 3, 3, 5, 3 };
		int[] arrSorted = { 1, 2, 2, 2, 3, 3, 5, 5, 5, 7, 7, 8, 8 };
		int[] arrSortedTwice = { 1, 1, 1, 2, 2, 2, 3, 3, 5, 5, 5, 7, 7, 8, 8 };
		int[] nums1 = {1, 2, 2, 1};
		int[] nums2 = {2, 2};

		System.out.println("Unsorted array before removing duplicate elements");
		printArray(arrUnSorted, arrUnSorted.length);

		ArrayMisc am = new ArrayMisc();
		int newLength = am.removeDuplicatesUnsorted(arrUnSorted);

		System.out.println();

		System.out.println("UnSorted Array after removing duplicate elements : " + newLength + " (New length)");
		printArray(arrUnSorted, newLength);
		
		System.out.println();
		System.out.println();
		
		System.out.println("Sorted array before removing duplicate elements");
		printArray(arrSorted, arrSorted.length);
		
		newLength = am.removeDuplicatesSorted(arrSorted);
		
		System.out.println();
		
		System.out.println("Sorted Array after removing duplicate elements : " + newLength + " (New length)");
		printArray(arrSorted, newLength);
		
		System.out.println();
		System.out.println();
		
		System.out.println("Sorted array before removing duplicates (allowed only twice)");
		printArray(arrSortedTwice, arrSortedTwice.length);
		
		newLength = am.removeDuplicates(arrSortedTwice);
		
		System.out.println();
		
		System.out.println("Sorted Array after removing duplicates (allowed only twice) : " + newLength + " (New length)");
		printArray(arrSortedTwice, newLength);
		
		System.out.println();
		System.out.println();
		
		int[] intersect = am.intersection(nums1, nums2);
		printArray(intersect, intersect.length);
		
		int[] newSortedArray = Arrays.copyOf(arrSorted, 6);
		
		System.out.println("\n");
		printArray(newSortedArray, newSortedArray.length);
		System.out.println("\nFinding key, no size : ");
		System.out.println(search(newSortedArray, 8));
	}
}