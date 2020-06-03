package com.implement.arrays;

public class PeakElement {
	/**
	 * @param A : An integers array.
	 * @return: return any of peek positions.
	 */
	public static int findPeakElement(int[] nums) {
		int low = 0, high = nums.length - 1;
		while (low < high) {
			int mid = (low + high) / 2;
			if (nums[mid] > nums[mid + 1])
				high = mid;
			else
				low = mid + 1;
		}
		return low;
	}
	
	public static int findPeakElementBS(int[] nums) {
		if(nums.length < 2) return 0;
		
		int low = 0, high = nums.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (mid > 0 && mid < nums.length - 1){
				if(nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1])
					return mid;
				else if(nums[mid-1] > nums[mid]){
					high = mid-1;
				}else{
					low = mid+1;
				}
			}else if(mid == 0){
				if(nums[0] > nums[1]){ 
					return 0;
				}else{
					return 1;
				}
			}else{
				if(nums[nums.length - 1] > nums[nums.length - 2]){ 
					return nums.length - 1;
				}else{
					return nums.length - 2;
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 4, 5, 6, 1, 2, 3 };
		int idx = findPeakElement(arr);
		System.out.println("Peak - Index : " + idx + ", Value is : " + arr[idx]);
		idx = findPeakElementBS(arr);
		if(idx != -1) System.out.println("Value is : " + arr[idx]);
		
		int[] arr1 = { 8, 9, 10, 2, 5, 6 };
		idx = findPeakElement(arr1);
		System.out.println("Peak - Index : " + idx + ", Value is : " + arr1[idx]);
		idx = findPeakElementBS(arr1);
		if(idx != -1) System.out.println("Value is : " + arr1[idx]);
		
		int[] arr2 = {10, 20, 30, 40, 50};
		idx = findPeakElement(arr2);
		System.out.println("Peak - Index : " + idx + ", Value is : " + arr2[idx]);
		idx = findPeakElementBS(arr2);
		if(idx != -1) System.out.println("Value is : " + arr2[idx]);
		
		int[] arr3 = { 100, 80, 60, 50, 20 };
		idx = findPeakElement(arr3);
		System.out.println("Peak - Index : " + idx + ", Value is : " + arr3[idx]);
		idx = findPeakElementBS(arr3);
		if(idx != -1) System.out.println("Value is : " + arr3[idx]);
	}

}
