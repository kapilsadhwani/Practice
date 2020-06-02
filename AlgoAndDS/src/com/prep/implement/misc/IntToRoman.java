package com.prep.implement.misc;

public class IntToRoman {

	public String intToRoman(int n) {
        int[] s = new int[4];
        for (int i = 0; i < 4; i++) {
            s[i] = n % 10;
            n /= 10;
        }

        String[][] map = new String[][]{
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                {"", "M", "MM", "MMM"}};

        String result = "";

        int k = 3;
        while (s[k] == 0) k--;

        while (k >= 0) {
            result += map[k][s[k]];
            k--;
        }

        return result;

    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IntToRoman itr = new IntToRoman();
		System.out.println(itr.intToRoman(123));
	}

}
