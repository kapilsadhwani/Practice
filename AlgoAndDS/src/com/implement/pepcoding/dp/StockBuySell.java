package com.implement.pepcoding.dp;

// Program to find best buying and selling days 
import java.util.ArrayList;
import java.util.Arrays;

// Solution structure 
class Interval {
	int buy, sell;
	
	Interval(){}
	
	Interval(int b, int s){
		this.buy = b;
		this.sell = s;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + this.buy + " " + this.sell + "]";
	}
}

class StockBuySell {
	// This function finds the buy sell schedule for maximum profit
	void stockBuySell(int price[], int n) {
		// Prices must be given for at least two days
		if (n == 1)
			return;

		int count = 0;

		// solution array
		ArrayList<Interval> sol = new ArrayList<Interval>();

		// Traverse through given price array
		int i = 0;
		while (i < n - 1) {
			// Find Local Minima. Note that the limit is (n-2) as we are
			// comparing present element to the next element.
			while ((i < n - 1) && (price[i] >= price[i + 1]))
				i++;

			// If we reached the end, break as no further solution possible
			if (i == n - 1)
				break;

			Interval e = new Interval();
			e.buy = i++;
			// Store the index of minima

			// Find Local Maxima. Note that the limit is (n-1) as we are
			// comparing to previous element
			while ((i < n) && (price[i] >= price[i - 1]))
				i++;

			// Store the index of maxima
			e.sell = i - 1;
			sol.add(e);

			// Increment number of buy/sell
			count++;
		}

		// print solution
		if (count == 0)
			System.out.println("There is no day when buying the stock "
					+ "will make profit");
		else
			for (int j = 0; j < count; j++)
				System.out.println("Buy on day: " + sol.get(j).buy + "	 "
						+ "Sell on day : " + sol.get(j).sell);

		return;
	}

	// Buy and Sell stock - Single Transaction Allowed
	public int maxProfitSingleTrans(int[] prices) {
		if (prices == null || prices.length < 2)
			throw new IllegalArgumentException(
					"Getting a profit requires at least 2 prices");

		// Minimum number visited so far
		int min_element = prices[0];

		// Maximum difference found so far
		int max_diff = 0;

		for (int i = 1; i < prices.length; i++) {
			if (prices[i] < min_element)
				min_element = prices[i];
			
			if (prices[i] - min_element > max_diff)
				max_diff = prices[i] - min_element;
		}
		return max_diff;
	}
	
	// Buy and Sell stock - Infinite Transaction Allowed
	public int maxProfitInfiniteTrans(int[] prices) {
		if (prices == null || prices.length < 2)
			throw new IllegalArgumentException(
					"Getting a profit requires at least 2 prices");

		int buyIdx = 0;
		int sellIdx = 0;

		int profit = 0;
		
		// solution array
		ArrayList<Interval> sol = new ArrayList<Interval>();

		for (int i = 1; i < prices.length; i++) {
			if (prices[i] >= prices[i - 1]) {
				sellIdx++;
			} else {
				profit = profit + prices[sellIdx] - prices[buyIdx];
				
				if(buyIdx != sellIdx){
					sol.add(new Interval(buyIdx, sellIdx));
				}

				buyIdx = i;
				sellIdx = i;
			}
		}

		profit = profit + prices[sellIdx] - prices[buyIdx];
		
		if(buyIdx != sellIdx){
			sol.add(new Interval(buyIdx, sellIdx));
		}
		
		System.out.println(sol);
		return profit;
	}
	
	// Buy and Sell stock - Infinite Transaction Allowed with sell transaction fee
	public int maxProfitInfiniteTransAndFee(int[] prices, int fee) {
		if (prices == null || prices.length < 2)
			throw new IllegalArgumentException(
					"Getting a profit requires at least 2 prices");

		int obsp = -prices[0];
		int ossp = 0;

		for (int i = 1; i < prices.length; i++) {
			int nbsp = 0;
			int nssp = 0;

			// Buy state - Having 1 stock at any time
			if (ossp - prices[i] > obsp) {
				nbsp = ossp - prices[i];
			} else {
				nbsp = obsp;
			}

			// Sell state - No stock in hand
			if (obsp + prices[i] - fee > ossp) {
				nssp = obsp + prices[i] - fee;
			} else {
				nssp = ossp;
			}

			obsp = nbsp;
			ossp = nssp;
		}

		return ossp;
	}
	
	// Buy and Sell stock - Infinite Transaction Allowed with cooldown
	public int maxProfitInfiniteTransAndCoolDown(int[] prices) {
		if (prices == null || prices.length < 2)
			throw new IllegalArgumentException(
					"Getting a profit requires at least 2 prices");

		int obsp = -prices[0];
		int ossp = 0;
		int ocsp = 0;

		for (int i = 1; i < prices.length; i++) {
			int nbsp = 0;
			int nssp = 0;
			int ncsp = 0;

			if (ocsp - prices[i] > obsp) {
				nbsp = ocsp - prices[i];
			} else {
				nbsp = obsp;
			}

			if (obsp + prices[i] > ossp) {
				nssp = obsp + prices[i];
			} else {
				nssp = ossp;
			}
			
			if (ossp > ocsp) {
				ncsp = ossp;
			} else {
				ncsp = ocsp;
			}

			obsp = nbsp;
			ossp = nssp;
			ocsp = ncsp;
		}

		return ossp;
	}
	
	// Find maximum profit if sold until today
	static int[] maxProfitIfSoldToday(int arr[]) {
		int[] dp = new int[arr.length];
		int min_so_far = arr[0];
		int currProfit = 0;

		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < min_so_far) {
				min_so_far = arr[i];
			}

			currProfit = arr[i] - min_so_far;

			if (currProfit > dp[i - 1]) {
				dp[i] = currProfit;
			} else {
				dp[i] = dp[i - 1];
			}
		}
		
		return dp;
	}
	
	// Find maximum profit if bought today
	static int[] maxProfitIfBoughtToday(int arr[]) {
		int[] dp = new int[arr.length];
		int max_so_far = arr[arr.length - 1];
		int currProfit = 0;

		for (int i = arr.length - 2; i >= 0; i--) {
			if (arr[i] > max_so_far) {
				max_so_far = arr[i];
			}

			currProfit = max_so_far - arr[i];

			if (currProfit > dp[i + 1]) {
				dp[i] = currProfit;
			} else {
				dp[i] = dp[i + 1];
			}
		}
		
		return dp;
	}

	// Buy and Sell stock - Two Transactions Allowed
	public int maxProfitTwoTrans(int[] prices) {
		if (prices == null || prices.length < 2)
			throw new IllegalArgumentException(
					"Getting a profit requires at least 2 prices");

		int[] dpIfSoldToday = maxProfitIfSoldToday(prices);
		int[] dpIfBoughtToday = maxProfitIfBoughtToday(prices);

		// Maximum profit if two transactions allowed
		int maxProfit = dpIfSoldToday[0] + dpIfBoughtToday[0];

		for (int i = 1; i < prices.length; i++) {
			if (dpIfSoldToday[i] + dpIfBoughtToday[i] > maxProfit){
				maxProfit = dpIfSoldToday[i] + dpIfBoughtToday[i];
			}
		}
		return maxProfit;
	}
	
	// Buy and Sell stock - K Transactions Allowed
	public int maxProfitKTrans(int[] prices, int k) {
		if (prices == null || prices.length < 2)
			throw new IllegalArgumentException(
					"Getting a profit requires at least 2 prices");

		int[][] dp = new int[k + 1][prices.length];

		for (int t = 1; t <= k; t++) {
			for (int d = 1; d < prices.length; d++) {
				int max = dp[t][d - 1];

				for (int pd = 0; pd < d; pd++) {
					int pdtm1 = dp[t - 1][pd];
					int bought_on_pd_profit = prices[d] - prices[pd];

					if (pdtm1 + bought_on_pd_profit > max) {
						max = pdtm1 + bought_on_pd_profit;
					}
				}

				dp[t][d] = max;
			}
		}
		return dp[k][prices.length - 1];
	}
	
	// Buy and Sell stock - K Transactions Allowed
	public int maxProfitKTransOptimized(int[] prices, int k) {
		if (prices == null || prices.length < 2)
			throw new IllegalArgumentException(
					"Getting a profit requires at least 2 prices");

		int[][] dp = new int[k + 1][prices.length];

		for (int t = 1; t <= k; t++) {
			int max = Integer.MIN_VALUE;
			
			for (int d = 1; d < prices.length; d++) {
				if(dp[t - 1][d - 1] - prices[d - 1] > max){
					max= dp[t - 1][d - 1] - prices[d - 1];
				}
				
				if(max + prices[d] > dp[t][d - 1]){
					dp[t][d] = max + prices[d];
				}else{
					dp[t][d] = dp[t][d - 1];
				}
			}
		}
		
		return dp[k][prices.length - 1];
	}

	public static void main(String args[]) {
		StockBuySell stock = new StockBuySell();

		// stock prices on consecutive days
		int price[] = { 100, 180, 260, 310, 40, 535, 695 };
		int n = price.length;

		// function call
		System.out.println(Arrays.toString(price));
		stock.stockBuySell(price, n);

		System.out.println(" ===================================== ");
		
		System.out.println(Arrays.toString(price));
		System.out.println("Single Transaction: " + stock.maxProfitSingleTrans(price) +
				", Multiple Transaction: " + stock.maxProfitInfiniteTrans(price));
		
		System.out.println(" ===================================== ");
		
		int price1[] = { 7, 1, 5, 3, 6, 4 };
		n = price1.length;

		System.out.println(Arrays.toString(price1));
		System.out.println("Single Transaction: " + stock.maxProfitSingleTrans(price1) +
				", Multiple Transaction: " + stock.maxProfitInfiniteTrans(price1));
		
		int price2[] = { 1, 2, 3, 4, 5 };

		System.out.println(" ===================================== ");
		System.out.println(Arrays.toString(price2));
		System.out.println("Single Transaction: " + stock.maxProfitSingleTrans(price2) +
				", Multiple Transaction: " + stock.maxProfitInfiniteTrans(price2));
		
		int price3[] = { 7, 6, 4, 3, 1 };

		System.out.println(" ===================================== ");
		System.out.println(Arrays.toString(price3));
		System.out.println("Single Transaction: " + stock.maxProfitSingleTrans(price3) +
				", Multiple Transaction: " + stock.maxProfitInfiniteTrans(price3));
		
		int price4[] = { 10, 20, 15, 30 };
		int fee = 3;

		System.out.println(" ===================================== ");
		System.out.println(Arrays.toString(price4));
		System.out.println("Transaction with fee: " + 
				stock.maxProfitInfiniteTransAndFee(price4, fee));
		
		System.out.println("Transaction with cooldown: " + 
				stock.maxProfitInfiniteTransAndCoolDown(price4));
		
		System.out.println(" ===================================== ");
		
		System.out.println(Arrays.toString(price4));
		System.out.println("2 Transactions allowed: " + 
				stock.maxProfitTwoTrans(price4));
		
		System.out.println(" ===================================== ");
		
		int price5[] = { 9, 6, 7, 6, 3, 8 };
		int k = 3;
		
		System.out.println(Arrays.toString(price5));
		System.out.println("K transactions allowed: " + 
				stock.maxProfitKTransOptimized(price5, k));
	}
}