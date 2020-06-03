package com.implement.twoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

	/**
     * @param numbers : Give an array numbers of n integer
     * @return : Find all unique triplets in the array which gives the sum of zero.
     * Replace sum=0 to sum=target to achieve same for a target sum
     */
    public List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    while(left < right && nums[left] == nums[left + 1]) left++;
                    
                    while(left < right && nums[right] == nums[right - 1]) right--;
                    
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
 
    // Main to test the above function
    public static void main (String[] args){
    	ThreeSum ts = new ThreeSum();
        int numbers[] = {1, 4, -5, 0, 8, -1, 2, 6, -10, 2, -4};
        List<List<Integer>> threeeSumZero = ts.threeSum(numbers);
        
        System.out.println(threeeSumZero);
    }

}
