package com.implement.pepcoding.greedy;

/*
 *  Paint House - Many Colors
 *  
 *  Given a number n and a number k separated by a space, representing the number of houses 
 *  and number of colors.
 *  Given k space separated numbers representing the cost of painting nth house with one of the k colors.
 *  Calculate the minimum cost of painting all houses without painting any consecutive house with same color.
 */
public class PaintHouseII {
	public static long minCost(int[][] nums) {
		long[][] dp = new long[nums.length][nums[0].length];
		
		long least = Integer.MAX_VALUE;
		long secLeast = Integer.MAX_VALUE;

		for (int j = 0; j < dp[0].length; j++) {
			dp[0][j] = nums[0][j];
			
			if(dp[0][j] <= least){
				secLeast = least;
				least = dp[0][j];
			}else if(dp[0][j] < secLeast){
				secLeast = dp[0][j];
			}
		}

		for (int i = 1; i < dp.length; i++) {
			long newLeast = Integer.MAX_VALUE;
			long newSecLeast = Integer.MAX_VALUE;
			
			for (int j = 0; j < dp[0].length; j++) {
				if(least == dp[i - 1][j]){
					dp[i][j] = nums[i][j] + secLeast;
				}else{
					dp[i][j] = nums[i][j] + least;
				}
				
				if(dp[i][j] <= newLeast){
					newSecLeast = newLeast;
					newLeast = dp[i][j];
				}else if(dp[i][j] < newSecLeast){
					newSecLeast = dp[i][j];
				}
			}

			least = newLeast;
			secLeast = newSecLeast;
		}

		
		return least;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] nums = {{1, 5, 7},
						{5, 8, 4},
						{3, 2, 9},
						{1, 2, 4}};

		System.out.println(minCost(nums));
	}

}
