package com.implement.multithreading;

public class LazyInitialization {
	private static boolean initialized = false;
	private static Thread t = new Thread(new Runnable() {
		public void run() {
			System.out.println("Thread run begin...");
			initialized = true;
			System.out.println("Thread run end...");
		}
	});

	static {
		System.out.println("Inside static block...");
		t.start();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Inside main...");
		try {
			System.out.println("Main waiting...");
			t.join();
			System.out.println("Wait over...");
		} catch (InterruptedException e) {
			throw new AssertionError(e);
		}

		System.out.println(initialized);
	}
}
