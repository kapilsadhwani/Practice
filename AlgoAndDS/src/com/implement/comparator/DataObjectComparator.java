package com.implement.comparator;

import java.util.Comparator;

/**
 * @author xcne005
 * 
 * Comparator class which orders the input object first by year and then by month.
 */
public class DataObjectComparator implements Comparator<IDateComparable> {

	public int compare(IDateComparable arg0, IDateComparable arg1) {
		String year0 = arg0.getYear();
		String month0 = arg0.getMonth();
		
		String year1 = arg1.getYear();
		String month1 = arg1.getMonth();
		
		// Convert them all into ints and do the comparison.
		try {
			int year0a = Integer.parseInt(year0);
			int month0a = Integer.parseInt(month0);
			
			int year1a = Integer.parseInt(year1);
			int month1a = Integer.parseInt(month1);
			
			if ( year0a < year1a ) {
				return -1;
			} else if ( year0a > year1a ) {
				return 1;
			} else if ( year0a == year1a ) {
				if ( month0a < month1a ) {
					return -1;
				} else if ( month0a > month1a ) {
					return 1;
				} else {
					return 0;
				}
			}
		} catch ( NumberFormatException nfe ) {
			System.out.println("Exception while comparing the months " + nfe.getMessage());
		}
		return 0;
	}
}
