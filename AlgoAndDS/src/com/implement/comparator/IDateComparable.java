package com.implement.comparator;

/**
 * @author xcne005
 * 
 * This interface is implemented by all data object which require the ability
 * to sort by date (first by year and then by month).
 */
public interface IDateComparable {
	public String getYear();
	public String getMonth();
}
