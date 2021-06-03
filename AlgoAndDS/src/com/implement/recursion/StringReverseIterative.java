package com.implement.recursion;

import java.util.Scanner;

// abcdef"123.;3.2.1"fed;cba
// Never a foot too far, evenm.

public class StringReverseIterative {
	public static void reverseString(char[] s) {
		if (s == null || s.length <= 1)
			return;

		int i = 0, j = s.length - 1;
		char temp;

		while (i < j) {
			temp = s[i];
			s[i] = s[j];
			s[j] = temp;

			i++;
			j--;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        scan.close();

        char[] inputArr = input.toCharArray();
        
        reverseString(inputArr);
        System.out.println(String.valueOf(inputArr));
	}

}
