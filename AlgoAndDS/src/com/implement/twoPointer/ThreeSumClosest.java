package com.implement.twoPointer;

import java.util.Arrays;
import java.util.List;

import javafx.util.Pair;

public class ThreeSumClosest {	
	 /**
     * @param numbers: Give an array numbers of n integer
     * @param target   : An integer
     * @return : return the sum of the three integers, the sum closest target.
     */
    public Pair<Integer, List<Integer>> threeSumClosest(int[] numbers, int target) {
        int min = Integer.MAX_VALUE;
        Pair<Integer, List<Integer>> pair = null;

        Arrays.sort(numbers);

        for (int i = 0; i < numbers.length - 2; i++) {
        	if (i > 0 && numbers[i] == numbers[i - 1]) continue;
            int left = i + 1;
            int right = numbers.length - 1;

            while (left < right) {
                int sum = numbers[i] + numbers[left] + numbers[right];
                if (Math.abs(sum - target) < min) {
                    min = Math.abs(sum - target);
                    pair = new Pair<Integer, List<Integer>>(sum, 
                    		Arrays.asList(numbers[i], numbers[left], numbers[right]));
                }

                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return pair;
    }
    
	public int threeSumClosestSum(int[] nums, int target) {
		int min = Integer.MAX_VALUE;
        Pair<Integer, List<Integer>> pair = null;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
        	if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < min) {
                    min = Math.abs(sum - target);
                    pair = new Pair<Integer, List<Integer>>(sum, 
                    		Arrays.asList(nums[i], nums[left], nums[right]));
                }

                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return pair.getKey();
	}
 
    // Main to test the above function
    public static void main (String[] args)
    {
    	ThreeSumClosest ts = new ThreeSumClosest();
        int numbers[] = {1, 4, -5, 8, -1, 2, 6, -10};
        int target = 17;
        Pair<Integer, List<Integer>> threeeSumClosest = ts.threeSumClosest(numbers, target);
        
        System.out.println(threeeSumClosest.getKey());
        
        List<Integer> tuple = threeeSumClosest.getValue();
        System.out.println(tuple);
        
        numbers = new int[]{1,1,1,0};
        target = -100;
        
        threeeSumClosest = ts.threeSumClosest(numbers, target);
        
        System.out.println(threeeSumClosest.getKey());
        
        tuple = threeeSumClosest.getValue();
        System.out.println(tuple);
        
        System.out.println(ts.threeSumClosestSum(numbers, target));
    }

}
