package com.implement.multithreading.distributed.model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class OrderQueue<T> {

	private BlockingQueue<T> queue;
	private String name;

	public OrderQueue(int capacity, String name) {
		this.name = name;
		queue = new ArrayBlockingQueue<T>(capacity);
	}

	public BlockingQueue<T> getQueue() {
		return queue;
	}

	public String getName() {
		return name;
	}

}
