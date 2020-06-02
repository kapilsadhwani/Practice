package com.prep.implement.string;

public class MultiplyStrings {
	public static String multiply(String num1, String num2) {
	    int m = num1.length(), n = num2.length();
	    int[] res = new int[m + n];
	   
	    for(int i = m - 1; i >= 0; i--) {
	        for(int j = n - 1; j >= 0; j--) {
	            int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); 
	            int sum = res[i + j + 1] + mul;

	            res[i + j] += sum / 10;
	            res[i + j + 1] = sum % 10;
	        }
	    }  
	    
	    StringBuilder sb = new StringBuilder();
	    
	    for(int val : res){ 
	    	if(sb.length() != 0 || val != 0) 
	    		sb.append(val);
	    }
	    return sb.length() == 0 ? "0" : sb.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String num1 = "123";
		String num2 = "456";
		
		System.out.println("Multiplication result : " + multiply(num1,num2));
	}

}
