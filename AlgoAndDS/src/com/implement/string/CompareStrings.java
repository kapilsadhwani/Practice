package com.implement.string;
public class CompareStrings {
    /**
     * @param A : A string includes Upper Case letters
     * @param B : A string includes Upper Case letter
     * @return :  if string A contains all of the characters in B return true else return false
     */
    public boolean compareStrings(String A, String B) {
        int[] cnt = new int[26];
        for (char c : A.toCharArray()) {
            cnt[c - 'A']++;
        }

        for (char b : B.toCharArray()) {
            if (cnt[b - 'A'] == 0) {
                return false;
            }
            cnt[b - 'A']--;
        }
        return true;
    }
    
    public static void main(String[] args) {
		CompareStrings cs = new CompareStrings();
		
		String a = "ABCD";
		String b = "ACD";
		String c = "AABD";
		
		System.out.println("A==B : " + cs.compareStrings(a,b));
		
		System.out.println("B==C : " + cs.compareStrings(a,c));
	}
}