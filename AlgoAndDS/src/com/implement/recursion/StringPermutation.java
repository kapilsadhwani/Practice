package com.implement.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class StringPermutation {
	private static void swap(char arr[],int i, int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
	
	private static void printArray(char str[]){
        for(int i=0; i< str.length; i++){
            System.out.print(str[i]);
        }
        System.out.print("\n");
    }
	
	private static void permute(char[] inputChar, int pos, ArrayList<String> result) {
		// TODO Auto-generated method stub
		
		if(pos == inputChar.length){
			//System.out.println(String.valueOf(inputChar));
			result.add(String.valueOf(inputChar));
			return;
		}
		
		for(int i=pos; i<inputChar.length; i++){
			swap(inputChar, pos, i);
			permute(inputChar, pos+1, result);
			swap(inputChar, pos, i);			
		}
	}
	
	private static boolean isVowel(char ch){
		return ch == 'a' || ch == 'e' || ch == 'i'
				|| ch == 'o' || ch == 'u';
	}
	
	private static void printConsoVowelsSeq(char[] inputChar, int pos) {
		// TODO Auto-generated method stub
		
		if(pos == inputChar.length){
			printArray(inputChar);
			return;
		}
		
		for(int i=pos; i<inputChar.length; i++){
			
			/*
			 *  If position is even and is not consonant; continue, pruning OR
			 *  If position is odd and is not vowel; continue, pruning
			 */
			
			if(pos%2 == 0 && isVowel(inputChar[i])) continue;
			
			if(pos%2 == 1 && !isVowel(inputChar[i])) continue;

			
			swap(inputChar, pos, i);
			printConsoVowelsSeq(inputChar, pos+1);
			swap(inputChar, pos, i);
		}
	}
	
	private static void printFirstThreeConsoSeq(char[] inputChar, int pos) {
		// TODO Auto-generated method stub
		
		if(pos == inputChar.length){
			printArray(inputChar);
			return;
		}
		
		for(int i=pos; i<inputChar.length; i++){
			 // If first 3 are not consonants; continue, pruning
			if(pos < 3 && isVowel(inputChar[i])) 
				continue;
			
			swap(inputChar, pos, i);
			printFirstThreeConsoSeq(inputChar, pos+1);
			swap(inputChar, pos, i);
		}
	}
	
	public static final Set<String> dictionary = 
			new	TreeSet<String>(Arrays.asList("witeya","twyaie","tewiya","yetawi","tyweai","ywteia"));
	
	private static boolean isValidWord(char[] inputChar) {
		return dictionary.contains(String.valueOf(inputChar));
	}
	
	// Input = wyatie, pos - beginning of the array you are responsible for
	private static void printFirstValidWord(char[] inputChar, int pos) {
		// TODO Auto-generated method stub
		if(pos == inputChar.length){
			if(isValidWord(inputChar)){
				printArray(inputChar);
			}
			
			return;
		}
		
		/* a[0] to a[pos-1] is fixed.
		 * My job is to see who goes at position a[pos] and then pass it over
		 * so that means, all chars from pos to length-1 can go at position pos
		 */
		for(int i=pos; i<inputChar.length; i++){
			swap(inputChar, pos, i);
			
			printFirstValidWord(inputChar, pos+1);
			
			swap(inputChar, pos, i);
		}
	}
	
	public static Set<String> getPermutations(String inputString) {
		// base case
		if (inputString.length() <= 1) {
			HashSet <String> hs = new HashSet<>();
			hs.add(inputString);
			return hs;
		}

		// All characters except the first character
		String remainingStr = inputString.substring(1);
		char firstChar = inputString.charAt(0);

		// Recursive call: get all possible permutations for all chars except first
		Set<String> permutationsOfRemainingStr = getPermutations(remainingStr);

		// put the first char in all possible positions for each of the above permutations
		Set<String> permutations = new HashSet<String>();
		for (String s : permutationsOfRemainingStr) {
			for (int pos = 0; pos <= s.length(); pos++) {
				String permutation = s.substring(0, pos)
						+ firstChar
						+ s.substring(pos);
				permutations.add(permutation);
			}
		}

		return permutations;
	}

	public static void main(String[] args) {
		ArrayList<String> result = new ArrayList<String>();
		
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
        String inputString = scan.nextLine();
        scan.close();
        
        if(inputString == null || inputString.trim().length() == 0) return;
        
        Set<String> permutations = getPermutations(inputString);
        
        for(String permutation:permutations){
        	System.out.println(permutation);
        }
        
        System.out.println("===== Permute Chars =====");
        
        char[] inputChar = inputString.toLowerCase().toCharArray();
        
        permute(inputChar, 0, result);
        
        System.out.println(result);
        
        System.out.println("===== Consonant Vowel Sequence =====");
        
        printConsoVowelsSeq(inputChar, 0);
        
        System.out.println("===== First Three Consonants ======");
        
        printFirstThreeConsoSeq(inputChar, 0);
        
        System.out.println("===== First Valid Word ======");
        
        printFirstValidWord(inputChar, 0);
	}

}
