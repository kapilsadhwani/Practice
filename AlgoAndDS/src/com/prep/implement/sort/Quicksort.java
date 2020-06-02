package com.prep.implement.sort;

public class Quicksort {
	Integer[] numbers;
	int capacity;
	
	// Partition by pivot at start
	private static int partition(Integer[] array, int start, int end) {
		// TODO Auto-generated method stub
		int pivot=start, left=start+1, right=end;
		
		while(left<right){
			while (left <= right && array[left] < array[pivot])
				left++;
			while (left <= right && array[pivot] < array[right])
				right--;
			
			if(left<right) 
				SortUtils.swap(array, left, right);
		}
		
		if(array[right] < array[pivot]) 
			SortUtils.swap(array, pivot, right);
		
		//SortUtils.printArray(array);
		
		return right;
	}
	
	// Quicksort by pivot at start
	private static void quickSort(Integer[] array, int start, int end){
		if(start >= end) return;
		
		int pivot = partition(array, start, end);
		quickSort(array, start, pivot-1);
		quickSort(array, pivot+1, end);
	}
	
	private void quickSortByPivotAtMiddle(int start, int end) {		
        int left = start, right = end;
        // Get the pivot element from the middle of the list
        int pivot = (start+end)/2;

        // Divide into two lists
        while (left <= right) {
            // If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
            while (this.numbers[left] < this.numbers[pivot]) {
            	left++;
            }
            // If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
            while (this.numbers[right] > this.numbers[pivot]) {
            	right--;
            }

            // If we have found a value in the left list which is larger than
            // the pivot element and if we have found a value in the right list
            // which is smaller than the pivot element then we exchange the
            // values.
            // As we are done we can increase left and right
            if (left <= right) {
                SortUtils.swap(this.numbers, left, right);
                left++;
                right--;
            }
        }
        // Recursion
        if (start < right)
        	quickSortByPivotAtMiddle(start, right);
        if (left < end)
        	quickSortByPivotAtMiddle(left, end);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] originalArray = {12,3,7,8,2};
		Integer[] originalArray1 = {5,21,12,3,7,6,2,13};
		Integer[] originalArray2 = {1,1,3,4,2,9,6,5,11,3,2};
		Integer[] originalArray3 = {1,2,3,4,5,6,7,8,9,10,11,12};
		Integer[] originalArray4 = {12,11,10,9,8,7,6,5,4,3,2,1};
		
		SortUtils.printArray(originalArray);
		
		System.out.println("");
		for(int i=0; i<5*originalArray.length; i++) System.out.print("="); 
		
		quickSort(originalArray, 0, originalArray.length-1);
		
		SortUtils.printArray(originalArray);
		System.out.println("");
		
		SortUtils.printArray(originalArray1);
		
		System.out.println("");
		for(int i=0; i<5*originalArray1.length; i++) System.out.print("="); 
		
		quickSort(originalArray1, 0, originalArray1.length-1);
		
		SortUtils.printArray(originalArray1);
		System.out.println("");
		
		SortUtils.printArray(originalArray2);
		
		System.out.println("");
		for(int i=0; i<5*originalArray2.length; i++) System.out.print("="); 
		
		quickSort(originalArray2, 0, originalArray2.length-1);
		
		SortUtils.printArray(originalArray2);
		System.out.println("");
		
		SortUtils.printArray(originalArray3);
		
		System.out.println("");
		for(int i=0; i<5*originalArray3.length; i++) System.out.print("="); 
		
		quickSort(originalArray3, 0, originalArray3.length-1);
		
		SortUtils.printArray(originalArray3);
		System.out.println("");
		
		SortUtils.printArray(originalArray4);
		
		System.out.println("");
		for(int i=0; i<5*originalArray4.length; i++) System.out.print("="); 
		
		quickSort(originalArray4, 0, originalArray4.length-1);
		
		SortUtils.printArray(originalArray4);
		System.out.println("");
	}

}
