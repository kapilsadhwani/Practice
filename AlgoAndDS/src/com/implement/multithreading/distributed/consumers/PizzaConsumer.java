package com.implement.multithreading.distributed.consumers;

import com.implement.multithreading.distributed.model.OrderQueue;

public class PizzaConsumer implements Runnable {
	protected OrderQueue<String> queue = null;

	public PizzaConsumer(OrderQueue<String> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			Thread.currentThread().setName("pizza-consumer");
			
			System.out.println(Thread.currentThread().getName()
					+ ": Pizza consumer started and processing orders...");

			while (true) {
				String order = queue.getQueue().take();
				if (order.equals("#")) {
					System.out.println(Thread.currentThread().getName() + ": "
							+ " processed all " + queue.getName()
							+ " orders. Exiting now...");
					return;
				}
				System.out.println(Thread.currentThread().getName() + ": Completed Order" + order);
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
