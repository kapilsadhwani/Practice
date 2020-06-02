package com.prep.implement.heap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortByFrequencyProblem {

	private static Map<Integer, Integer> createFrequencyMap(int[] arr) {

		// Use LinkedHashMap because it maintains insertion order of elements.
		Map<Integer, Integer> frequencyMap = new LinkedHashMap<>();

		for (int i = 0; i < arr.length; i++) {
			/*int key = arr[i];
			if (!frequencyMap.containsKey(key)) {
				frequencyMap.put(key, 0);
			}
			
			frequencyMap.put(key, frequencyMap.get(key) + 1);*/
			frequencyMap.put(arr[i], frequencyMap.getOrDefault(arr[i], 0) + 1);
		}
		return frequencyMap;
	}

	private static List<Entry<Integer, Integer>> sortByFrequency(Map<Integer, Integer> frequencyMap) {
		// List containing elements of map's entry set.
		List<Entry<Integer, Integer>> entryList = new ArrayList<Entry<Integer, Integer>>();
		
		entryList.addAll(frequencyMap.entrySet());

		// Sort the list in Reverse Order to get largest element as first item
		Collections.sort(entryList,
				new Comparator<Map.Entry<Integer, Integer>>() {
					@Override
					public int compare(Map.Entry<Integer, Integer> o1,
							Map.Entry<Integer, Integer> o2) {
						return o2.getValue().compareTo(o1.getValue());	// Freq is stored in Val field
					}
				});
		return entryList;
	}

	private static void putSortedElementsBackInArray(int[] arr,
			List<Entry<Integer, Integer>> list) {
		int index = 0;

		// Arrange array elements in sorted list of entry set of frequency map.
		for (Map.Entry<Integer, Integer> entry : list) {
			for (int i = 0; i < entry.getValue(); i++) {
				arr[index++] = entry.getKey();
			}
		}
	}
	
	private static void sortByFrequency(int[] arr) {
		Map<Integer, Integer> frequencyMap = createFrequencyMap(arr);
		List<Entry<Integer, Integer>> entryList = sortByFrequency(frequencyMap);

		putSortedElementsBackInArray(arr, entryList);
	}

	
	
	private static int getMaxValueWithMinFrequency(int[] arr) {
		Map<Integer, Integer> frequencyMap = createFrequencyMap(arr);
		
		List<Entry<Integer, Integer>> entryList = new ArrayList<Entry<Integer, Integer>>();
		entryList.addAll(frequencyMap.entrySet());

		// Sort the list in Asc order of frequency
		Collections.sort(entryList,
				new Comparator<Map.Entry<Integer, Integer>>() {
					@Override
					public int compare(Map.Entry<Integer, Integer> o1,
							Map.Entry<Integer, Integer> o2) {
						return o1.getValue().compareTo(o2.getValue());	// Freq is stored in Val field
					}
				});
		
		
		
		int result = -1;
		
		int minCount = entryList.get(0).getValue();

		/*
		 * Check entry value, if same, keep track of Max element. When different return Max at end
		 * Key = Integer Number; Value = Frequency
		 */
		for (Map.Entry<Integer, Integer> entry : entryList) {
			if(entry.getValue() == minCount){
				result = Math.max(result, entry.getKey());
			}else {
				break;
			}
		}
		
		return result;
	}

	private static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	public static void main(String[] args) {
		int[] arr = { 2, 5, 3, 8, 7, 2, 5, 2, 3, 3, 5, 3 };

		System.out.println("Input array before sorting elements by frequency.");
		printArray(arr);

		sortByFrequency(arr);

		System.out.println();
		System.out.println();

		System.out.println("Array after sorting elements by frequency.");
		printArray(arr);
		
		System.out.println("=========================================");
		System.out.println("Frequency Game");
		
		System.out.print(Arrays.toString(arr) + " : ");
		System.out.println(getMaxValueWithMinFrequency(arr));
		
		int[] arr1 = {2, 2, 5, 50, 1};
		System.out.print(Arrays.toString(arr1) + " : ");
		System.out.println(getMaxValueWithMinFrequency(arr1));
		
		int[] arr2 = {2, 2, 5, 50, 40, 50};
		System.out.print(Arrays.toString(arr2) + " : ");
		System.out.println(getMaxValueWithMinFrequency(arr2));
	}
}