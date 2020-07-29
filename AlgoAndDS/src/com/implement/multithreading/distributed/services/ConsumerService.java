package com.implement.multithreading.distributed.services;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.implement.multithreading.distributed.consumers.BurgerConsumer;
import com.implement.multithreading.distributed.consumers.CoffeeConsumer;
import com.implement.multithreading.distributed.consumers.PizzaConsumer;
import com.implement.multithreading.distributed.model.OrderQueue;

public class ConsumerService implements Runnable{
    protected List<OrderQueue<String>> queues = null;
    ExecutorService executorService = null;
    
    public ConsumerService(List<OrderQueue<String>> queues) {
        this.queues = queues;
        this.executorService = Executors.newFixedThreadPool(queues.size());
    }
    
	public void run() {
		Thread.currentThread().setName("consumer-service");
		
		System.out.println(Thread.currentThread().getName()
				+ ": Registering Consumers...");
		try {
			for (OrderQueue<String> queue: queues) {
				//distribute the processing for coffee, pizza, burgers
				if(queue.getName().equalsIgnoreCase("CoffeeQ")) {
					executorService.submit(new CoffeeConsumer(queue));
				}else if(queue.getName().equalsIgnoreCase("PizzaQ")) {
					executorService.submit(new PizzaConsumer(queue));
				}else if(queue.getName().equalsIgnoreCase("BurgerQ")) {
					executorService.submit(new BurgerConsumer(queue));
				}
			    
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.executorService.shutdown();
		}
	}
}