package com.implement.binarysearch;

public class SortedArray {
	// returns leftmost (or rightmost) index at which `target` should be
	// inserted in sorted array `nums` via binary search.
	private static int extremeInsertionIndex(int[] nums, int target, boolean left) {
		int lo = 0;
		int hi = nums.length;

		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			if (nums[mid] > target || (left && target == nums[mid])) {
				hi = mid;
			} else {
				lo = mid + 1;
			}
		}

		return lo;
	}

	public static int[] searchRange(int[] nums, int target) {
		int[] targetRange = { -1, -1 };

		int leftIdx = extremeInsertionIndex(nums, target, true);

		// assert that `leftIdx` is within the array bounds and that `target`
		// is actually in `nums`.
		if (leftIdx == nums.length || nums[leftIdx] != target) {
			return targetRange;
		}

		targetRange[0] = leftIdx;
		targetRange[1] = extremeInsertionIndex(nums, target, false) - 1;

		return targetRange;
	}
	
	public static int searchInsert(int[] nums, int target) {
		int len = nums.length;
		if ((len == 0) || (target <= nums[0]))
			return 0;
		if (target >= nums[len - 1])
			return len;

		int low = 0;
		int high = len - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (nums[mid] == target)
				return mid;
			else if (nums[mid] > target)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return low;
	}
	
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
			else if (nums[mid] > target)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return -1;
	}

	public static int floorBS(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return Integer.MIN_VALUE;

		if (target < nums[0])
			return Integer.MIN_VALUE;
		if (target > nums[nums.length - 1])
			return nums[nums.length - 1];

		int res = -1;
		int low = 0;
		int high = nums.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] == target)
				return nums[mid];
			else if (nums[mid] > target)
				high = mid - 1;
			else {
				res = nums[mid];
				low = mid + 1;
			}
		}
		return res;
	}
	
	public static int ceilBS(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return Integer.MAX_VALUE;
		if (target < nums[0])
			return nums[0];
		if (target > nums[nums.length - 1])
			return Integer.MAX_VALUE;

		int res = -1;
		int low = 0;
		int high = nums.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] == target)
				return nums[mid];
			else if (nums[mid] > target) {
				res = nums[mid];
				high = mid - 1;
			} else
				low = mid + 1;
		}
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 1, 2, 2, 2, 2, 3, 4, 7, 8, 8 };
		int target = 8;
		int[] range = searchRange(arr,target);
		System.out.println("First and Last occurence of " + target + " : [" + range[0] + ", " + range[1] + "]");
		
		target = 2;
		range = searchRange(arr,target);
		System.out.println("First and Last occurence of " + target + " : [" + range[0] + ", " + range[1] + "]");
		
		arr = new int[]{1, 3, 5, 6};
		target = 5;
		System.out.println("Target index or insert position : " + searchInsert(arr,target));
		
		target = 2;
		System.out.println("Target index or insert position : " + searchInsert(arr,target));
		
		target = 7;
		System.out.println("Target index or insert position : " + searchInsert(arr,target));
		
		target = 0;
		System.out.println("Target index or insert position : " + searchInsert(arr,target));
		
		target=9;
		int[] nums = {-1,0,3,5,9,12};
		System.out.println("Binary Search - target : " + target + " is at " + binarySearch(nums, target));
		
		target=2;
		System.out.println("Binary Search - target : " + target + " is at " + binarySearch(nums, target));
		
		target=6;
		System.out.println("Target (" + target + "), Floor : " + floorBS(nums, target) + ", Ceil : " + ceilBS(nums, target));
		
		target=1;
		System.out.println("Target (" + target + "), Floor : " + floorBS(nums, target) + ", Ceil : " + ceilBS(nums, target));
		
		target=-2;
		System.out.println("Target (" + target + "), Floor : " + floorBS(nums, target) + ", Ceil : " + ceilBS(nums, target));
		
		target=15;
		System.out.println("Target (" + target + "), Floor : " + floorBS(nums, target) + ", Ceil : " + ceilBS(nums, target));
	}

}
