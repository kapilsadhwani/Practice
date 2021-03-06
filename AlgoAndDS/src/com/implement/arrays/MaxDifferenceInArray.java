package com.implement.arrays;

public class MaxDifferenceInArray {
	public int maximumDifference(int[] nums) {
		if(nums == null || nums.length < 2) return 0;
		
		// Minimum number visited so far 
	    int min_element = nums[0] < nums[1] ? nums[0] : nums[1];
	    
	    // Maximum difference found so far 
	    int max_diff = nums[1] - min_element;
		
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] - min_element > max_diff)
				max_diff = nums[i] - min_element;
			
			if (nums[i] < min_element)
				min_element = nums[i];
		}
		return max_diff;
	}
	
	// Return -ve if prices always goes down
	public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) 
        	throw new IllegalArgumentException("Getting a profit requires at least 2 prices");
		
		// Minimum number visited so far 
	    int min_element = prices[0];
	    
	    // Maximum difference found so far 
	    int max_diff = prices[1] - min_element;
		
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] - min_element > max_diff)
				max_diff = prices[i] - min_element;
			
			if (prices[i] < min_element)
				min_element = prices[i];
		}
		return max_diff;
    }
	
	// Return -ve if prices always goes down
	public int maxProfitExtraSp(int[] prices) {
        if(prices == null || prices.length < 2) return 0;
        
        int minVal[] = new int[prices.length];
        
        minVal[0] = prices[0];
        
		for (int i = 1; i < prices.length; i++) {
			minVal[i] = Math.min(prices[i], minVal[i-1]);
		}
		
		// Maximum difference found so far 
	    int max_diff = prices[1] - minVal[0];
	   
	    for (int i = 1; i < prices.length; i++) {
	    	if(prices[i] - minVal[i] > max_diff)
	    		max_diff = prices[i] - minVal[i - 1];
		}

		return max_diff;
    }
	
	public int maxDifference(int[] elements) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for(int val : elements){
			if(val < min){
				min = val;
			}
			if(val > max){
				max = val;
			}
		}
		
		return max-min;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] a = new int[n];
		
		for(int i=0;i<n;i++){
			a[i] = sc.nextInt();
		}
		
		sc.close();*/
		
		int a[] = {90, 2, 80, 85, 1}; 
		
		MaxDifferenceInArray difference = new MaxDifferenceInArray();

		System.out.print(difference.maximumDifference(a) + ", ");
		System.out.print(difference.maxDifference(a) + ", ");
		System.out.print(difference.maxProfit(a) + ", ");
		System.out.println(difference.maxProfitExtraSp(a));
		
		a = new int[]{7,1,5,3,6,4}; 
		
		System.out.print(difference.maximumDifference(a) + ", ");
		System.out.print(difference.maxDifference(a) + ", ");
		System.out.print(difference.maxProfit(a) + ", ");
		System.out.println(difference.maxProfitExtraSp(a));
		
		a = new int[]{17,13,8,4,1};
		
		System.out.print(difference.maximumDifference(a) + ", ");
		System.out.print(difference.maxDifference(a) + ", ");
		System.out.print(difference.maxProfit(a) + ", ");
		System.out.println(difference.maxProfitExtraSp(a));
	}
}