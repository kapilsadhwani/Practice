package com.implement.binarysearch;

public class SearchInNearlySortedArray {
	public static int binarySearch(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return -1;
		if (target < nums[0] || target > nums[nums.length - 1])
			return -1;

		int low = 0;
		int high = nums.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] == target)
				return mid;
			if (mid > low && nums[mid - 1] == target)
				return mid - 1;
			if (mid < high && nums[mid + 1] == target)
				return mid + 1;
			else if (nums[mid] > target)
				high = mid - 2;		// since we already checked for mid - 1
			else
				low = mid + 2;		// since we already checked for mid + 1
		}
		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int target = 40;
		int[] nums = { 10, 3, 40, 20, 50, 80, 70 };
		System.out.println("Binary Search - target : " + target + " is at "
				+ binarySearch(nums, target));
	}
}