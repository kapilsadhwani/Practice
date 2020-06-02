package com.prep.implement.recursion;

import java.util.HashMap;

/*
 * K-th Symbol in Grammar
 * On the first row, we write a 0. Now in every subsequent row, we look at the previous row and 
 * replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
 * Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).
 */
public class KthSymbolInGrammar {
	
	private static String generateGrammar(int rowIndex) {
		if(rowIndex < 1) return null;
		
		if(rowIndex == 1) return "0";
		if(rowIndex == 2) return "01";
		
		HashMap<Integer, String> cache = new HashMap<Integer, String>();
		
		cache.put(1, "0");
		cache.put(2, "01");
		
		return generateGrammar(rowIndex, cache);
	}
	
	private static String generateGrammar(int rowIndex, HashMap<Integer, String> cache) {
		if(rowIndex == 1) return "0";
		if(rowIndex == 2) return "01";
		
		if(cache.containsKey(rowIndex)) return cache.get(rowIndex);
		
		String prevRow = generateGrammar(rowIndex-1, cache);
		
		StringBuilder sb = new StringBuilder(prevRow);
		
		for(char c: prevRow.toCharArray()){
			sb.append(c == '0' ? '1' : '0');
		}
		
		return sb.toString();
	}
	
	public static int kthGrammar(int N, int K) {
		if(N == 1) return 0;
		
		int result;
		int currentStringLen = (int)Math.pow(2, N-1);
		int prevStringLen = currentStringLen/2;
		
		// Means are looking for K in second half, so invert
		if(K > prevStringLen){
			result = kthGrammar(N-1, K - prevStringLen);
			result = result == 1 ? 0 : 1;
		}else{
			result = kthGrammar(N-1, K);
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(generateGrammar(1));
		System.out.println(generateGrammar(2));
		System.out.println(generateGrammar(3));
		System.out.println(generateGrammar(4));
		
		System.out.println(kthGrammar(1, 1));
		System.out.println(kthGrammar(2, 1) + " " + kthGrammar(2, 2));
		System.out.println(kthGrammar(3, 1) + " " + kthGrammar(3, 2) + " " + 
						   kthGrammar(3, 3) + " " + kthGrammar(3, 4));
		System.out.println(kthGrammar(4, 1) + " " + kthGrammar(4, 2) + " " + 
						   kthGrammar(4, 3) + " " + kthGrammar(4, 4) + " " +
						   kthGrammar(4, 5) + " " + kthGrammar(4, 6) + " " + 
						   kthGrammar(4, 7) + " " + kthGrammar(4, 8));
	}

}
