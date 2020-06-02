package com.prep.implement.twoPointer;

public class ContainerWithMostWater {
	public static int maxArea(int[] height) {
		int maxWater = 0, left = 0, right = height.length - 1;
		while (left < right) {
			maxWater = Math.max(maxWater,
					(right - left) * Math.min(height[left], height[right]));
			if (height[left] < height[right])
				left++;
			else
				right--;
		}
		return maxWater;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nums[] = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };

		System.out.println("Container with most water : " + maxArea(nums));
	}

}
