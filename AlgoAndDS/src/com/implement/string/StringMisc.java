package com.implement.string;

public class StringMisc {
	// function takes a string and returns the
	// maximum depth nested parenthesis

	public static int maxDepth(String S) {
		int current_max = 0; // current count
		int max = 0; // overall maximum count
		int n = S.length();

		// Traverse the input string
		for (int i = 0; i < n; i++) {
			if (S.charAt(i) == '(') {
				current_max++;

				// update max if required
				if (current_max > max) {
					max = current_max;
				}
			} else if (S.charAt(i) == ')') {
				if (current_max > 0) {
					current_max--;
				} else {
					// Unbalanced string
					return -1;
				}
			}
		}

		// finally check for unbalanced string
		if (current_max != 0) {
			return -1;
		}

		return max;
	}
	
	// Java program to convert only first character of each word uppercase in a sentence
	static String convertToCamelCase(String str) {
		// Create a char array of given String
		char ch[] = str.toCharArray();
		for (int i = 0; i < str.length(); i++) {
			// If first character of a word is found
			if ((i == 0 || ch[i - 1] == ' ') && ch[i] != ' ') {

				// If it is in lower-case
				if (ch[i] >= 'a' && ch[i] <= 'z') {

					// Convert to Upper-case
					ch[i] = (char) (ch[i] - 'a' + 'A');
				}
			}

			// If apart from first character, Any one is in Upper-case
			else if (ch[i] >= 'A' && ch[i] <= 'Z')

				// Convert to Lower-Case
				ch[i] = (char) (ch[i] - 'A' + 'a');
		}

		return String.valueOf(ch);
	}

	// Java program to convert to opposite case in a sentence
	static String convertToOppCase(String str) {
		// Create a char array of given String
		char ch[] = str.toCharArray();
		for (int i = 0; i < str.length(); i++) {
			// If first character of a word is found
			if (ch[i] != ' ') {
				// If it is in lower-case
				if (ch[i] >= 'a' && ch[i] <= 'z') {
					// Convert into Upper-case
					ch[i] = (char) (ch[i] - 'a' + 'A');
				}

				// If it is in Upper-case
				else if (ch[i] >= 'A' && ch[i] <= 'Z')
					// Convert into Lower-Case
					ch[i] = (char) (ch[i] + 'a' - 'A');
			}
		}

		// Convert the char array to equivalent String
		String st = new String(ch);
		return st;
	}
	
	static boolean isLower(char ch){
		return ch >= 'a' && ch <= 'z';
	}

	// Java program to convert to opposite case in a sentence
	static String convertToUpperOrLowerCase(String str) {
		// Create a char array of given String
		char ch[] = str.toCharArray();
		int idx=0;
		
		while(ch[idx] == ' '){
			idx++;
			
			if(idx == ch.length) return str;
		}
		
		boolean toLower = isLower(ch[idx]) ? true : false;
		
		for (int i = idx+1; i < str.length(); i++) {
			// If first character of a word is found
			if (ch[i] != ' ') {
				if(toLower){
					// If it is in Upper-case, convert into Lower-case
					if (ch[i] >= 'A' && ch[i] <= 'Z')
						ch[i] = (char) (ch[i] + 'a' - 'A');
				}else{	// Convert to Upper				
					// If it is in lower-case, convert into Upper-case
					if (ch[i] >= 'a' && ch[i] <= 'z') {
						ch[i] = (char) (ch[i] - 'a' + 'A');
					}
				}
			}
		}

		// Convert the char array to equivalent String
		String st = new String(ch);
		return st;
	}
	
	// function to print string in sorted order 
	static char[] sortString(String str){ 
	    // Hash array to keep count of characters. 
	    // Initially count of all charters is  
	    // initialized to zero. 
		int MAX_CHAR = 26;
	    int charCount[] = new int[MAX_CHAR];
	    char[] result = new char[str.length()];
	      
	    // Traverse string and increment count of characters 
	    for (int i=0; i<str.length(); i++) 
	        // 'a'-'a' will be 0, 'b'-'a' will be 1, 
	        // so for location of character in count  
	        // array we will do str[i]-'a'. 
	        charCount[str.charAt(i)-'a']++;     
	      
	    // Traverse the hash array and print  
	    // characters 
	    int k=0;
	    for (int i=0;i<MAX_CHAR;i++) 
	    	for (int j=0;j<charCount[i];j++) 
	        	result[k++] = (char)('a'+i); 
	    
	    return result;
	}

	public static String stringShift(String s, int[][] shift) {
		if(s == null || s.length() <= 1) return s;
		
		int rightShift = 0;
		
		for(int[] operation : shift){
			/*
			 *  Formula used: effective shift = ((2*direction) - 1) * amount of shifts.
			 *  Will give -1 or 1 for Left or Right respectively
			 */
			rightShift = rightShift + ((2 * operation[0] - 1) *  operation[1]);
		}
		
		// Effective shifts are zero. No shifting required. 
		if(rightShift % s.length() == 0) return s;
		
		if(rightShift > 0){
			rightShift = rightShift % s.length();
		}else{
			rightShift = (rightShift % s.length() + s.length()) % s.length();
		}
		
		// Shifting right
		// Index to the first character of our new String
		int pos=0;
		pos = s.length() - rightShift;
		
		StringBuilder sb = new StringBuilder();
		char[] chars = s.toCharArray();
		
		for(int i=pos; i<s.length(); i++){
			sb.append(chars[i]);
		}
		
		for(int i=0; i<pos; i++){
			sb.append(chars[i]);
		}
		
		/*for(int i=0; i<s.length(); i++){
			sb.append(chars[pos]);
			pos++;
			
			// We reached end of the string, reset pos to 0
			if(pos >= chars.length){
				pos = 0;
			}
		}*/
		
		return sb.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "( ((X)) (((Y))) )"; 
        System.out.println(maxDepth(s));
        
        String str = "geEk fOR GeEKS";
        System.out.println(convertToCamelCase(str));
        System.out.println(convertToOppCase(str));
        
        System.out.println(convertToUpperOrLowerCase(str));
        System.out.println(convertToUpperOrLowerCase(convertToCamelCase(str)));
        
        String toBeSorted = "geeksforgeeks";     
        System.out.println(String.valueOf(sortString(toBeSorted)));
        
        s = "abc";
        int[][] shift = {
        					{0, 2},
        					{1, 1}
        				};
        
        System.out.println(stringShift(s, shift));
        
        s = "xqgwkiqpif";
       shift = new int[][]
    		   {
	    		   {1, 4},
	    		   {0, 7},
	    		   {0, 8},
	    		   {0, 7},
	    		   {0, 6},
	    		   {1, 3},
	    		   {0, 1},
	    		   {1, 7},
	    		   {0, 5},
	    		   {0, 6}
    		   };
       
       System.out.println(stringShift(s, shift));
       
       System.out.println("=============");
       
       String hello1 = "hello";
       String hello2 = "hello";	// Interning : Gets from Intern Pool
       String hello3 = new String("hello");
       
       System.out.println(hello1 == hello2);
       System.out.println(hello1 == hello3);
       System.out.println(hello2 == hello3);
       
	}

}
