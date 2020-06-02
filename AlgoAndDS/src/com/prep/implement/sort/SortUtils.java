package com.prep.implement.sort;

public class SortUtils {

	public static void printArray(Integer[] data){
		System.out.println();
		if(data.length > 0){
			System.out.print("[ " + data[0]);
			
			for(int i=1;i<data.length-1;i++){
				System.out.print(" | " + data[i]);
			}
			
			System.out.print(" | " + data[data.length-1] + " ]");
		}
	}
	
	public static void printArray(int[] data){
		System.out.println();
		if(data.length > 0){
			System.out.print("[ " + data[0]);
			
			for(int i=1;i<data.length-1;i++){
				System.out.print(" | " + data[i]);
			}
			
			System.out.print(" | " + data[data.length-1] + " ]");
		}
	}
	
	public static void swap(Integer[] data,int lower,int upper){
		int temp;

		temp = data[lower];
		data[lower] = data[upper];
		data[upper] = temp;
	}
	
	public static void swap(int[] data,int lower,int upper){
		int temp;

		temp = data[lower];
		data[lower] = data[upper];
		data[upper] = temp;
	}
	
	public static void swap(char[] data,int lower,int upper){
		char temp;

		temp = data[lower];
		data[lower] = data[upper];
		data[upper] = temp;
	}
	
	public static void copyArray(Integer[] srcArray, Integer[] destArray, int start, int end) {
		for(int i=start,j=0; i<=end; i++){
			destArray[i] = srcArray[j++];
		}
	}
	
	public static void copyArray(int[] srcArray, int[] destArray, int start, int end) {
		for(int i=start,j=0; i<=end; i++){
			destArray[i] = srcArray[j++];
		}
	}
}
