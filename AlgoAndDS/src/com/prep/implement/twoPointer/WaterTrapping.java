package com.prep.implement.twoPointer;

/*
 * Time complexity: O(n).
 * We store the maximum heights upto a point using 2 iterations of O(n) each.
 * We finally update ans using the stored values in O(n).
 * Space complexity: O(n) extra space.
 * Additional O(n) space for left_max and right_max arrays.
 */

public class WaterTrapping {
    // using extra space
	public static int maxArea(int[] height) {
		int n = height.length;
		int[] maxL = new int[n];
		int[] maxR = new int[n];

		maxL[0] = height[0];
		for (int i = 1; i < n; i++) {
			maxL[i] = Math.max(maxL[i - 1], height[i]);
		}

		maxR[n - 1] = height[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			maxR[i] = Math.max(maxR[i + 1], height[i]);
		}

		int[] water = new int[n];
		int sum = 0;

		for (int i = 0; i < n; i++) {
			water[i] = Math.min(maxL[i], maxR[i]) - height[i];
			sum = sum + water[i];
		}

		return sum;
	}
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nums[] = { 1,8,6,2,5,4,8,3,7 };

        System.out.println("Container with most water : " + maxArea(nums));
	}
}