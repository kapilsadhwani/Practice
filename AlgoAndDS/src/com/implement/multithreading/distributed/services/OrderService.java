package com.implement.multithreading.distributed.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.implement.multithreading.distributed.model.OrderQueue;
import com.implement.multithreading.distributed.producers.BurgerProducer;
import com.implement.multithreading.distributed.producers.CoffeeProducer;
import com.implement.multithreading.distributed.producers.PizzaProducer;

public class OrderService {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		try {
			List<OrderQueue<String>> queueList = new ArrayList<OrderQueue<String>>();

			// Coffee Queue
			OrderQueue<String> coffeeQ = new OrderQueue<String>(10, "CoffeeQ");
			
			// Pizza Queue
			OrderQueue<String> pizzaQ = new OrderQueue<String>(10, "PizzaQ");
			
			// Burger Queue
			OrderQueue<String> burgerQ = new OrderQueue<String>(10, "BurgerQ");

			// Producer and Consumer will point to queues available in this list.
			queueList.add(coffeeQ);
			queueList.add(pizzaQ);
			queueList.add(burgerQ);

			// Producer that will add data to the respective queues by deleting to worker producers
			ProducerService producer = new ProducerService(queueList);
			executorService.submit(producer);
			
			// Consumer which will be listening to all the queues and delegates to workers asynchronously.
			ConsumerService consumer = new ConsumerService(queueList);
			executorService.submit(consumer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			executorService.shutdown();
		}
	}
}