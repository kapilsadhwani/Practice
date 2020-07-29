package com.implement.multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {

	public static void main(String[] args) {
		// get count of available cores
		int coreCount = Runtime.getRuntime().availableProcessors();
		
		// TODO Auto-generated method stub
		ExecutorService cpuExecService = Executors.newFixedThreadPool(coreCount);
		
		for(int i=0; i<20; i++){
			cpuExecService.execute(new CPUIntensiveTask());
		}
		
		cpuExecService.shutdown();
		
		// TODO Auto-generated method stub
		ExecutorService ioExecService = Executors.newFixedThreadPool(20);

		for(int i=0; i<10; i++){
			ioExecService.execute(new IOTask());
		}
		
		ioExecService.shutdown();
		
		System.out.println("Thread Name: " + Thread.currentThread().getName());
		
		/*
		 *  new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue)
			new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, 
														new ArrayBlockingQueue<Runnable>(capacity))
		*/
		ExecutorService executorService = new ThreadPoolExecutor
										(10, 
										 100, 
										 120, 
										 TimeUnit.SECONDS, 
										 new ArrayBlockingQueue<Runnable>(300));
		
		try {
			executorService.execute(new Task());
			executorService.shutdown();
		} catch (RejectedExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 *  new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue)
			new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, 
														new ArrayBlockingQueue<Runnable>(capacity))
		*/
		ExecutorService service = new ThreadPoolExecutor
										(10, 
										 100, 
										 120, 
										 TimeUnit.SECONDS, 
										 new ArrayBlockingQueue<Runnable>(300),
										 new CustomRejectionHandler());
		
		try {
			service.execute(new Task());
			service.shutdown();
		} catch (RejectedExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static class Task implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Task Thread Name: " + Thread.currentThread().getName());
		}
		
	}
	
	static class CPUIntensiveTask implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("CPUTask Thread Name: " + Thread.currentThread().getName());
		}
		
	}
	
	static class IOTask implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("IOTask Thread Name: " + Thread.currentThread().getName());
		}
		
	}
	
	private static class CustomRejectionHandler implements RejectedExecutionHandler{

		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			// TODO Auto-generated method stub
			System.err.println("Task rejected");
		}
		
	}

}
