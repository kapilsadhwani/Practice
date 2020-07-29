package com.implement.multithreading.distributed.services;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.implement.multithreading.distributed.model.OrderQueue;
import com.implement.multithreading.distributed.producers.BurgerProducer;
import com.implement.multithreading.distributed.producers.CoffeeProducer;
import com.implement.multithreading.distributed.producers.PizzaProducer;

public class ProducerService implements Runnable{
    protected List<OrderQueue<String>> queues = null;
    ExecutorService executorService = null;
    
    public ProducerService(List<OrderQueue<String>> queues) {
        this.queues = queues;
        this.executorService = Executors.newFixedThreadPool(queues.size());
    }
    
	public void run() {
		Thread.currentThread().setName("producer-service");
		
		System.out.println(Thread.currentThread().getName()
				+ ": Registering Producers...");
		try {
			for (OrderQueue<String> queue: queues) {
				//distribute the processing for coffee, pizza, burgers
				if(queue.getName().equalsIgnoreCase("CoffeeQ")) {
					executorService.submit(new CoffeeProducer(queue));
				}else if(queue.getName().equalsIgnoreCase("PizzaQ")) {
					executorService.submit(new PizzaProducer(queue));
				}else if(queue.getName().equalsIgnoreCase("BurgerQ")) {
					executorService.submit(new BurgerProducer(queue));
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