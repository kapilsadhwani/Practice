package com.prep.implement.dp;

import java.util.Arrays;
import java.util.HashMap;

public class Fibonacci {

	static HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();
	
	private static int fibMemo(int N, int[] cache) {
		if (cache[N] > -1) {
			return cache[N];
		}
		
		// Already computed
		/*if (N < 2) {
			result = N;
		}*/
		
		int result = fibMemo(N - 1, cache) + fibMemo(N - 2, cache);

		// keep the result in cache.
		cache[N] = result;
		return result;
	}

	private static int fibR(int N) {
		if( N < 2) return N;
		
		int[] cache = new int[N+1];
		
		Arrays.fill(cache, -1);
		
		cache[0] = 0;
		cache[1] = 1;
		
		return fibMemo(N, cache);
	}
	
	private static int fib(int N) {
		if( N < 2) return N;
		
		int[] cache = new int[N+1];
		
		cache[0] = 0;
		cache[1] = 1;
		
		for(int i=2; i<=N; i++){
			cache[i] = cache[i-1] + cache[i-2];
		}
		
		return cache[N];
	}

	public static void main(String[] args) {
		System.out.println("Fibonacci(2) is Recursive: " + fibR(0) + ", DP: " + fib(0));
		System.out.println("Fibonacci(2) is Recursive: " + fibR(1) + ", DP: " + fib(1));
		System.out.println("Fibonacci(2) is Recursive: " + fibR(2) + ", DP: " + fib(2));
		System.out.println("Fibonacci(3) is Recursive: " + fibR(3) + ", DP: " + fib(3));
		System.out.println("Fibonacci(4) is Recursive: " + fibR(4) + ", DP: " + fib(4));
		System.out.println("Fibonacci(5) is Recursive: " + fibR(5) + ", DP: " + fib(5));
		System.out.println("Fibonacci(6) is Recursive: " + fibR(6) + ", DP: " + fib(6));
		System.out.println("Fibonacci(7) is Recursive: " + fibR(7) + ", DP: " + fib(7));
	}
}