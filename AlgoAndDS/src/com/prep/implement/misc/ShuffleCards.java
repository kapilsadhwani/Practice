package com.prep.implement.misc;

import java.util.Random;

public class ShuffleCards {
	public static void shuffle(Object[] deck) {
        Random random = new Random();
        for (int i = 0; i < deck.length - 1; ++i) {  //deck.length-1:ignoring last iteration with only 1 card remaining
            int r = random.nextInt(deck.length - i) + i;
            Object tmp = deck[i];
            deck[i] = deck[r];
            deck[r] = tmp;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] deck = {5, 7, 6, 1, 9, 2, 4, 10, 8, 3,
						 14, 20, 13, 16, 17, 12, 15, 11, 18, 19,
						 27, 23, 21, 29, 25, 28, 24, 22, 26, 30,
						 36, 37, 31, 39, 40, 38, 33, 35, 34, 32,
						 50, 46, 44, 48, 42, 49, 45, 47, 43, 41,
						 52, 51
						};
		
		shuffle(deck);
		
		for (Integer card : deck) System.out.print(card + " ");
        System.out.println();
	}

}
