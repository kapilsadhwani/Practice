package com.implement.slidingWindow;

// Program to find best buying and selling days 
import java.util.ArrayList;

// Solution structure 
class Interval {
	int buy, sell;
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
			while ((i < n - 1) && (price[i + 1] <= price[i]))
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

	int maxProfit(int prices[]) {
		// Prices must be given for at least two days
		if (prices.length == 1)
			return 0;

		int n = prices.length;
		int start = 0, end = 0;
		int maxProfit = 0;

		// Traverse through given price array
		while (end < n - 1) {
			// Find Local Minima. Note that the limit is (n-2) as we are
			// comparing present element to the next element.
			while ((end < n - 1) && (prices[end + 1] <= prices[end])) {
				end++;
			}

			// If we reached the end, break as no further solution possible
			if (end == n - 1)
				break;

			start = end++;
			// Store the index of minima

			// Find Local Maxima. Note that the limit is (n-1) as we are
			// comparing to previous element
			while ((end < n) && (prices[end] >= prices[end - 1]))
				end++;

			// Store the index of maxima
			maxProfit = maxProfit + prices[end - 1] - prices[start];
		}
		return maxProfit;
	}

	public static void main(String args[]) {
		StockBuySell stock = new StockBuySell();

		// stock prices on consecutive days
		int price[] = { 100, 180, 260, 310, 40, 535, 695 };
		int n = price.length;

		// function call
		stock.stockBuySell(price, n);

		int price1[] = { 7, 1, 5, 3, 6, 4 };
		n = price1.length;

		System.out.println(stock.maxProfit(price1));

		int price2[] = { 1, 2, 3, 4, 5 };

		System.out.println(stock.maxProfit(price2));

		int price3[] = { 7, 6, 4, 3, 1 };

		System.out.println(stock.maxProfit(price3));
	}
}