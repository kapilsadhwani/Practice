package com.prep.implement.misc;

public class PrintExcelColumnNumber {
	private static void swap(char[] strArray,int i, int j) {
		// TODO Auto-generated method stub
		char temp = strArray[i];
		strArray[i] = strArray[j];
		strArray[j] = temp;
	}
	
	static void inplaceReverse(char[] strArray, int i, int j){
		if(i >= j){
			return;
		}
		
		swap(strArray, i, j);
		inplaceReverse(strArray, ++i, --j);
	}
	
	private static void printColumnNumber(int num){
		char[] str = new char[100];
		int i = 0;
		int rem;
		
		while(num>0){
			rem = num%26;
			if(rem == 0){
				str[i++] = 'Z';
				num = (num/26) -1;
			}else{
				str[i++] = (char) ('A' + (rem-1));
				num = num/26;
			}
				
		}
		
		inplaceReverse(str, 0, i);
		System.out.println(str);
	}
			

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printColumnNumber(26);
		printColumnNumber(51);
		printColumnNumber(52);
		printColumnNumber(80);
	    printColumnNumber(676);
	    printColumnNumber(702);
	    printColumnNumber(705);
	}

}
