package com.prep.implement.slidingWindow;

import java.util.HashMap;

/*
 * Similar to Longest Substring with At Most Two Distinct Characters
 */
public class FruitBasket {
	public static int totalFruit(int[] tree) {
		if(tree == null || tree.length == 0) return 0;
		
		int max = 1;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		int start = 0;
		int end = 0;
		
		while(end < tree.length){
			// Store index of last seen fruit of that type as a value
			if(map.size() <= 2){
				map.put(tree[end], end);
			}
			
			if(map.size() == 3){
				/*
				 *  Minimum of indices stored in map
				 *  
				 *  As indices are overwritten, we cannot simply use start to remove element outside 
				 *  of sliding window and hence we need a min index from the Map
				 */
				int min = tree.length;	//or tree.length;
				
				for(int value: map.values()){
					min = Math.min(min, value);
				}
				
				start = min + 1;
				map.remove(tree[min]);
			}
			
			max = Math.max(max, end - start + 1);
			
			end++;
		}
		
		return max;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] trees = { 1, 2, 1 };
		System.out.println(totalFruit(trees));
		
		trees = new int[]{0,1,2,2};
		System.out.println(totalFruit(trees));
		
		trees = new int[]{1,2,3,2,2};
		System.out.println(totalFruit(trees));
		
		trees = new int[]{3,3,3,1,2,1,1,2,3,3,4};
		System.out.println(totalFruit(trees));
	}

}
