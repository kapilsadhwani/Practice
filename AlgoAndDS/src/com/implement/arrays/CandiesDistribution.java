package com.implement.arrays;

import java.util.Arrays;

class CandiesDistribution {
	public static int[] distributeCandies(int candies, int num_people) {
		int[] arr = new int[num_people];

		int currCandyCount = 1;

		// While loop to keep distributing candies from a[0]...to...a[n-1]
		while (candies > 0) {
			for (int i = 0; i < num_people; i++) {
				// check if we have enough candies to distribute next
				if (candies >= currCandyCount) {
					arr[i] = arr[i] + currCandyCount;
					candies = candies - currCandyCount;
					currCandyCount++;
				} else { // we don't have enough candies to distribute
					arr[i] = arr[i] + candies; // distribute whatever is remaining
					candies = 0; // set to 0 so it exits the while loop
					break;
				}
			}
		}
		return arr;
	}
	
	public static void main(String[] args) {
		int candies = 7;
		int num_people = 4;
		
		int result[] = distributeCandies(candies, num_people);
		System.out.println(Arrays.toString(result));
		
		
		candies = 10;
		num_people = 3;
		
		result = distributeCandies(candies, num_people);
		System.out.println(Arrays.toString(result));
	}
}