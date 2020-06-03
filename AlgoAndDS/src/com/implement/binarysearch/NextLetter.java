package com.implement.binarysearch;

public class NextLetter {
	// Find Smallest Letter Greater Than Target.
	public static char nextGreatestLetter(char[] letters, char target) {
		if(target < letters[0] || target >= letters[letters.length - 1])
			return letters[0];
		
		char res = 'a';
		int low = 0;
		int high = letters.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			
			// Find last occurrence of target
			if (letters[mid] == target){ 
				res = letters[mid + 1];
				low = mid + 1;
			}else if (letters[mid] > target){ 
				res = letters[mid];
				high = mid - 1;
			}else
				low = mid + 1;
		}
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] letters = { 'c','c','j', 'k', 'l' };
		char target = 'c';
		
		System.out.println("Next Letter : " + nextGreatestLetter(letters, target));
	}

}
