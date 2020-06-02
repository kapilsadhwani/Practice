package com.prep.implement.sort;

public class ArraySort {

	private static void bubbleSort(Integer[] data){	// largest numbers are sorted first (Exchanging, largest bubbles down)
		for(int i=0; i<data.length-1; i++){
			for(int j=0; j<data.length-1-i; j++){
				if(data[j] > data[j+1]){
					SortUtils.swap(data,j,j+1);
				}
				//System.out.println("");
				//System.out.print(" Iteration " + i + " : ");
				//printArray(data);
			}
		}
	}

	private static void selectionSort(Integer[] data){	// smallest numbers are sorted first (Selection of min & exchanging once)
		int minIndex;

		for(int i=0;i<data.length-1;i++){
			minIndex = i;

			for(int j=i+1;j<data.length;j++)
				if(data[j] < data[minIndex]){
					minIndex = j;
					//printArray(data);
				}

			SortUtils.swap(data,i,minIndex);
		}
	}

	private static void insertionSort(Integer[] data){	// Insertion of numbers to their correct position
		int current, j;

		for(int i=1;i<data.length;i++){
			current = data[i];
			j = i-1;

			while(j>=0 && data[j]>current){
				data[j+1] = data[j];
				j--;
			}

			data[j+1] = current;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] originalArray = {12,8,7,5,2};

		Integer[] data = originalArray.clone();

		System.out.println("===========================================");
		System.out.print(" Before Bubble sort : ");
		SortUtils.printArray(data);

		bubbleSort(data);

		System.out.println("");
		System.out.print(" After  Bubble sort : ");
		SortUtils.printArray(data);
		System.out.println(" ");
		System.out.println("===========================================");

		data = originalArray.clone();
		System.out.print(" Before Selection sort : ");
		SortUtils.printArray(data);

		selectionSort(data);

		System.out.println("");
		System.out.print(" After  Selection sort : ");
		SortUtils.printArray(data);
		System.out.println(" ");
		System.out.println("===========================================");

		data = originalArray.clone();
		System.out.print(" Before Insertion sort : ");
		SortUtils.printArray(data);

		insertionSort(data);

		System.out.println("");
		System.out.print(" After  Insertion sort : ");
		SortUtils.printArray(data);
		System.out.println(" ");
		System.out.println("===========================================");
	}

}
