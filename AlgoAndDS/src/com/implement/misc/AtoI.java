package com.implement.misc;

import java.io.IOException;
import java.util.Scanner;

import com.sun.xml.internal.messaging.saaj.util.CharReader;

public class AtoI {
	static int convertCharToInt(char[] input, int startIdx, int endIdx){
		int intVal = (input == null || (endIdx-startIdx) <= 0) ? 0 : (input[startIdx] - '0');
		for (int i=startIdx+1;i<endIdx;i++){
			intVal = intVal * 10 +  		// Shift digits in hand by 1 (i.e multiply it by 10)
					(input[i] - '0');		// Add next digit to it
		}
		
		return intVal;
	}
	
	static boolean isDigit(char c) {
	    return c>='0' && c<='9';
	}
	
	static int atoiIK(String str) {
		// Invalid if empty, NULL, minus or plus
		if(str == null || str.trim().length() == 0 || str.trim().equals("-")  || str.trim().equals("+")){
			return 0;
		}
		char[] charArr = str.toCharArray();
		int startIndex = -1;
		int endIndex = -1;
		int idx = 0;			// Represents current char at hand
		int sign = 1;			// '+' or '-' represented by 1 or -1 resp.
		
		/* 1) Ignore spaces
		 * 2) Keep track of sign
		 * 3) Keep reading until you find first Digit, startIndex
		 * 4) Then stop when you see first Non-Digit character, endIndex
		 * 5) Extract Number and return (sign * Number)
		 */
		while(!isDigit(charArr[idx])){	// Keep reading until you find first Digit
			if(charArr[idx] == '+') sign=1;
			else if(charArr[idx] == '-') sign=-1;
			
			idx++;
			
			if(idx == charArr.length) return 0;	// For Input: + - + - abc
		}
		
		startIndex = idx;
		
		while(isDigit(charArr[idx])){ 
			idx++;
			
			if(idx == charArr.length) break;		// For Input: + - + - abc765
		}
		
		endIndex = idx;		
		
		return sign * convertCharToInt(charArr,startIndex, endIndex);	
    }
	
	public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        scan.close();
        
        int res = atoiIK(input);
        
        System.out.println(res);
        
        
        /*final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        int res;
        String _str;
        try {
            _str = in.nextLine();
        } catch (Exception e) {
            _str = null;
        }
        
        res = atoiIK(_str);
        bw.write(String.valueOf(res));
        bw.newLine();
        
        bw.close();*/
    }
}
