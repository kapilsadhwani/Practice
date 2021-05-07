package com.implement.pepcoding.dp;

/*
 *  Count number of ways in which the buildings can be built on both side of roads
 *  are no consecutive zeros.
 *  The road has n plots on it's each side.
 *  The road is to be planned such that there should not be consecutive buildings on 
 *  either side of the road.
 */

class ArrangeBuildings {
	
	// Driver code
	public static void main(String[] args) {
		long n = 5; // length of binary string

		// For length 1
		long oldBuildings = 1;
		long oldSpaces = 1;

		for (long i = 2; i <= n; i++) {
			long newBuildings = oldSpaces;
			long newSpaces = oldBuildings + oldSpaces;

			oldBuildings = newBuildings;
			oldSpaces = newSpaces;
		}

		long total = oldBuildings + oldSpaces;
		total = total * total;

		System.out.println(total);
	}
}