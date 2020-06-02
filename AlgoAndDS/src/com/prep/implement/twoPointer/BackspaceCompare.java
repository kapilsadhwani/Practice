package com.prep.implement.twoPointer;

public class BackspaceCompare {
	public static int process(char[] chars) {
		int pos = -1;
		
		for(int i=0;i<chars.length;i++){
			if(chars[i] != '#'){
				chars[++pos] = chars[i];
			}else{
				if(pos > -1){
					pos--;
				}
			}
		}
		
		return pos;
	}
	
	public static boolean backspaceCompare(String S, String T) {
		char[] sChars = S.toCharArray();
		char[] tChars = T.toCharArray();
		
		int sPos = process(sChars);
		int tPos = process(tChars);
		
		if(sPos == -1 && tPos == -1) return true;
		
		if(sPos != tPos) return false;
		
		for(int i=0; i<=sPos; i++){
			if(sChars[i] != tChars[i]){
				return false;
			}
		}
		
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String S = "ab#c", T = "ad#c";
		System.out.println("Strings " + S + " and " + T + " : " + backspaceCompare(S, T));
		
		S = "ab##";
		T = "c#d#";
		System.out.println("Strings " + S + " and " + T + " : " + backspaceCompare(S, T));
		
		S = "a##c";
		T = "#a#c";
		System.out.println("Strings " + S + " and " + T + " : " + backspaceCompare(S, T));
		
		S = "a#c";
		T = "b";
		System.out.println("Strings " + S + " and " + T + " : " + backspaceCompare(S, T));
		
		S = "geee#e#ks";
		T = "gee##eeks";
		System.out.println("Strings " + S + " and " + T + " : " + backspaceCompare(S, T));
	}

}
