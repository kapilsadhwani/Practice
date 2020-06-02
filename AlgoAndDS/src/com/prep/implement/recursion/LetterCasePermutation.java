package com.prep.implement.recursion;

import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation {
	List<String> result = new ArrayList<String>();
	
	private void permute(char[] inputChar, int pos) {
		// TODO Auto-generated method stub
		if (pos == inputChar.length) {
			result.add(String.valueOf(inputChar));
			return;
		}

		// Do Nothing, go to next position
		permute(inputChar, pos + 1);

		// Change case only for letters and revert to original state
		char thisChar = inputChar[pos];

		if (thisChar >= 'a' && thisChar <= 'z') {
			// Convert to Upper-case
			inputChar[pos] = (char) (thisChar - 'a' + 'A');
			permute(inputChar, pos + 1);
			
			// Revert to original state
			inputChar[pos] = thisChar;
		} else if (thisChar >= 'A' && thisChar <= 'Z') {
			// Convert to Lower-Case
			inputChar[pos] = (char) (thisChar + 'a' - 'A');
			permute(inputChar, pos + 1);
			
			// Revert to original state
			inputChar[pos] = thisChar;
		}
	}
	
	public List<String> letterCasePermutation(String S) {
        char[] charArray = S.toCharArray();
        
        permute(charArray, 0);
        
        return result;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String S = "a1b";

		LetterCasePermutation lcp = new LetterCasePermutation();

		List<String> result = lcp.letterCasePermutation(S);
		
		System.out.println(result);
	}

}
