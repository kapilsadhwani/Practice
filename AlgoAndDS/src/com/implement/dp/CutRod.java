package com.implement.dp;

import java.util.Arrays;

public class CutRod {
	static int getMaxPriceMemo(int[] length, int[] price, int L, int n, int[][] revenue){
		if(L == 0 || n == 0){
			return 0;
		}
		
		if(revenue[n][L] != -1) return revenue[n][L];
		
		if(length[n-1] <= L){
			revenue[n][L] = Math.max(price[n-1] + getMaxPriceMemo(length, price, L-length[n-1], n, revenue), 
										getMaxPriceMemo(length, price, L, n-1, revenue));
		}else{ 
			revenue[n][L] = getMaxPriceMemo(length, price, L, n-1, revenue);
		}

		return revenue[n][L];
	}
	
	static int getMaxPriceAV(int[] length, int[] price, int L){
		int n = price.length;
    	int cache[][] = new int[n+1][L+1];
    	
    	for(int i=0; i<n+1; i++){
    		for(int j=0;j<L+1; j++){
    			cache[i][j] = -1;
    		}
    	}

		return getMaxPriceMemo(length, price, L, n, cache);
	}
	
	static int getMaxPriceR(int[] price, int l, int[] revenue){
		if(revenue[l] != -1) return revenue[l];
		
		int maxPrice;
		if(l == 0){
			maxPrice = 0;
		}else{
			maxPrice = price[l-1];
			
			for(int c=1; c<l; c++){					// c = cut
				maxPrice = Math.max(maxPrice, 
						price[c-1]+getMaxPriceR(price,l-c,revenue));
			}
		}
		
		revenue[l] = maxPrice;
		return maxPrice;
	}
	
	// Top-down approach - DP: Memoization
	static int getMaxPrice(int[] price, int len) {
		int[] revenue = new int[len + 1];
		int errorCode = 1345;

		if (len < 1)
			return errorCode; // Check for error in non-recursive function

		Arrays.fill(revenue, -1);

		return getMaxPriceR(price, len, revenue);
	}

	// Bottom-up approach - DP: Iterative
	static int getMaxPriceDP(int[] price, int len, boolean printPath) {
		int[] revenue = new int[len + 1];
		int[] path = new int[len + 1];

		int errorCode = 1345;
		if (len < 1)
			return errorCode; // Check for error in non-recursive function

		if (printPath) {
			getMaxPriceDPWithPath(price, len, revenue, path);
			System.out.print("Path is : ");

			int l = len;
			while (l > 0) {
				System.out.print(path[l] + " --> ");
				l = l - path[l];
			}
			System.out.print("NULL");
		} else {
			revenue[0] = 0; // No need of halting condition

			for (int l = 1; l <= len; l++) {
				int maxPrice = price[l - 1];

				for (int c = 1; c < l; c++) { // c = cut
					maxPrice = Math
							.max(maxPrice, price[c - 1] + revenue[l - c]);
				}
				revenue[l] = maxPrice;
			}
		}

		return revenue[len];
	}
	
	static void getMaxPriceDPWithPath(int[] price, int len, int[] revenue,
			int[] path) {
		revenue[0] = 0;
		path[0] = 0;

		for (int l = 1; l <= len; l++) {
			int maxPrice = price[l - 1];
			path[l] = l;

			for (int c = 1; c < l; c++) { // c = cut
				if (maxPrice < price[c - 1] + revenue[l - c]) {
					maxPrice = price[c - 1] + revenue[l - c];
					path[l] = c;
				}
			}
			revenue[l] = maxPrice;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] price = {1,5,8,9,10,17,17,20,24,30};
		int[] length = {1,2,3,4,5,6,7,8,9,10};
		
		System.out.println("Rod of length 1 - Memo : " + getMaxPrice(price, 1) + " Iter : " + getMaxPriceDP(price, 1, false) + " AV: " + getMaxPriceAV(length, price, 1));
		System.out.println("Rod of length 2 - Memo : " + getMaxPrice(price, 2) + " Iter : " + getMaxPriceDP(price, 2, false) + " AV: " + getMaxPriceAV(length, price, 2));
		System.out.println("Rod of length 3 - Memo : " + getMaxPrice(price, 3) + " Iter : " + getMaxPriceDP(price, 3, false) + " AV: " + getMaxPriceAV(length, price, 3));
		System.out.println("Rod of length 4 - Memo : " + getMaxPrice(price, 4) + " Iter : " + getMaxPriceDP(price, 4, false) + " AV: " + getMaxPriceAV(length, price, 4));
		System.out.println("Rod of length 5 - Memo : " + getMaxPrice(price, 5) + " Iter : " + getMaxPriceDP(price, 5, false) + " AV: " + getMaxPriceAV(length, price, 5));
		System.out.println("Rod of length 6 - Memo : " + getMaxPrice(price, 6) + " Iter : " + getMaxPriceDP(price, 6, false) + " AV: " + getMaxPriceAV(length, price, 6));
		System.out.println("Rod of length 7 - Memo : " + getMaxPrice(price, 7) + " Iter : " + getMaxPriceDP(price, 7, false) + " AV: " + getMaxPriceAV(length, price, 7));
		System.out.println("Rod of length 8 - Memo : " + getMaxPrice(price, 8) + " Iter : " + getMaxPriceDP(price, 8, false) + " AV: " + getMaxPriceAV(length, price, 8));
		System.out.println("Rod of length 9 - Memo : " + getMaxPrice(price, 9) + " Iter : " + getMaxPriceDP(price, 9, false) + " AV: " + getMaxPriceAV(length, price, 9));
		System.out.println("Rod of length 10 - Memo : " + getMaxPrice(price, 10) + " Iter : " + getMaxPriceDP(price, 10, false) + " AV: " + getMaxPriceAV(length, price, 10));
		
		System.out.println();
		System.out.println("With Path of length 5 ");
		int maxPrice = getMaxPriceDP(price, 5, true);
		
		System.out.println("\nAnd Max Price is : " + maxPrice);
	}

}
