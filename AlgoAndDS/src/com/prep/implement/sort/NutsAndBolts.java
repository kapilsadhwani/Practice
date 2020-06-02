package com.prep.implement.sort;

public class NutsAndBolts {
	private static int partition(char[] nuts, int start, int end, char bolt) {
		// TODO Auto-generated method stub
		int left = start, right = end;

		while (left < right) {
			while (left <= right && nuts[left] < bolt)
				left++;
			while (left <= right && bolt < nuts[right])
				right--;

			if (left < right) {
				SortUtils.swap(nuts, left, right);
			}
		}

		// if(nuts[right] < bolt) SortUtils.swap(nuts, start, right);

		return right;
	}

	// Method which works just like quick sort
	private static void matchPairs(char[] nuts, char[] bolts, int start, int end) {
		if (start > end)
			return;

		// Choose first character of bolts array for nuts partition.
		int pivot = partition(nuts, start, end, bolts[start]);

		// Now using the partition of nuts choose that for bolts partition.
		partition(bolts, start, end, nuts[pivot]);

		// Recur for [low...pivot-1] & [pivot+1...high] for nuts and bolts
		// array.
		matchPairs(nuts, bolts, start, pivot - 1);
		matchPairs(nuts, bolts, pivot + 1, end);
	}

	public static void printArray(char[] data, char type) {
		System.out.println();
		if (data.length > 0) {
			System.out.print(type + "[" + data[0] + "]");
		}
		for (int i = 1; i < data.length; i++) {
			System.out.print(" | " + type + "[" + data[i] + "]");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char nuts[] = { '#', '@', '$', '%', '^', '&' };
		char bolts[] = { '&', '%', '#', '^', '@', '$' };

		matchPairs(nuts, bolts, 0, bolts.length - 1);

		printArray(nuts, 'N');
		printArray(bolts, 'B');
	}

}
