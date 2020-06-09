package com.implement.multithreading;

public class FooBar {
	static synchronized void bar() {
		System.out.print("bar ");
	}

	public static synchronized void main(String[] args) {
		Thread t = new Thread() {
			public void run() {
				System.out.println("Inside run...");
				bar();
			}
		};

		System.out.println("Before run...");
		t.run();
		System.out.print("foo ");
	}
}
