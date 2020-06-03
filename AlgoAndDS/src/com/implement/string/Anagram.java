package com.implement.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Anagram {
	private static boolean isAnagram(String str1, String str2) {
		 // If length of strings are not same, the strings are not anagrams.
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }

        int[] letters = new int[26]; // Assumption
       
        for(int i=0; i<str1.length(); i++){
        	letters[str1.charAt(i)-'a']++;
        }
        
        for(int i=0; i<str2.length();i++){        	
        	letters[str2.charAt(i)-'a']--;
        	
        	if(letters[str2.charAt(i)-'a'] < 0){
        		//System.out.println(Arrays.toString(letters));
        		return false;
        	}
        }
        
        return true;
    }
	
	public static List<List<String>> groupAnagrams(String[] strs) {
	    List<List<String>> groups = new ArrayList<List<String>>();
	 
	    HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	    for(String str: strs){
	        char[] apha = new char[26];
	        for(int i=0; i<str.length(); i++){
	        	apha[str.charAt(i)-'a']++;
	        }
	        String ns = new String(apha);
	        
	        if(map.containsKey(ns)){
	            map.get(ns).add(str);
	        }else{
	            ArrayList<String> group = new ArrayList<String>();
	            group.add(str);
	            map.put(ns, group);
	        }
	    }
	 
	    groups.addAll(map.values());
	 
	    return groups;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String anagrams[] = {"lives","evils"};
		String anagrams1[] = {"livees","evils"};

		System.out.println("Are these words anagrams - " + anagrams[0] + " and " + anagrams[1] + " ? --> " + (isAnagram(anagrams[0],anagrams[1])?"Yes":"No"));
		
		List<List<String>> result = groupAnagrams(anagrams);
		System.out.println(result.toString());
		
		result = groupAnagrams(anagrams1);
		System.out.println(result.toString());
		
		result = groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
		System.out.println(result.toString());
	}

}
