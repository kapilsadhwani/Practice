package com.implement.binarysearch;

public class FirstBadVersion {
	static int[] badVersions = {0,0,1,1,1,1,1,1,1,1};
	
	public static int isBadVersion(int n) {
		return badVersions[n - 1];
	}

	public static int firstBadVersion(int n) {
		int low = 1;
		int high = n;
		int res = -1;

		if (isBadVersion(low) == 1) return low;

			while (low <= high) {
				int mid = low + (high - low) / 2;
				int isBad = isBadVersion(mid);
				if (isBad == 1) {
					res = mid;

					high = mid - 1;
				} else{
					low = mid + 1;
				}
			}
		return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("First Bad Version: " + firstBadVersion(10));
	}
}