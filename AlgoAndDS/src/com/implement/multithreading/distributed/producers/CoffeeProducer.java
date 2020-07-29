package com.implement.multithreading.distributed.producers;

import com.implement.multithreading.distributed.model.OrderQueue;

public class CoffeeProducer implements Runnable {
	protected OrderQueue<String> queue = null;
	protected String name;

	public CoffeeProducer(OrderQueue<String> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			Thread.currentThread().setName("coffee-producer");
			
			System.out.println(Thread.currentThread().getName()
					+ ": Started and creating orders...");
			
			for (int i = 1; i < 11; i++) {
				queue.getQueue().put("# " + i);
				Thread.sleep(500);
			}
			
			Thread.sleep(2000);
			
			for (int i = 11; i < 16; i++) {
				queue.getQueue().put("# " + i);
				Thread.sleep(500);
			}
			
			queue.getQueue().put("#");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}