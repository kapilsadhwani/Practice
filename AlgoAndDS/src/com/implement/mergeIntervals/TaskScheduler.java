package com.implement.mergeIntervals;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class TaskScheduler {
	public static int leastInterval(char[] tasks, int n) {
		int[] map = new int[26];
		for (char c : tasks)
			map[c - 'A']++;
		
		// Map Heap
		/*PriorityQueue<Integer> queue = new PriorityQueue<>(26,
				Collections.reverseOrder());*/
		
		PriorityQueue<Integer> queue = new PriorityQueue<>(26,
				(a,b) -> b - a);
		
		for (int num : map) {
			if (num > 0)
				queue.add(num);
		}
		
		int totalTime = 0;
		List<Integer> temp = new LinkedList<>();
		
		while (!queue.isEmpty()) {
			// Do this after every interval of n
			int cooling = 0;
			
			while (cooling <= n) {
				if (!queue.isEmpty()) {
					
					// If tasks has more than one instance (or frequency) to finish
					if (queue.peek() > 1)
						temp.add(queue.poll() - 1);
					else
						queue.poll();
				}
				
				totalTime++;
				cooling++;

				if (queue.isEmpty() && temp.size() == 0)
					break;
			}
			
			// Add executed tasks back in the queue
			for (int l : temp){
				queue.add(l);
			}
			
			temp.clear();
		}
		return totalTime;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] tasks = { 'A','A','A','B','B','B','C','C','E','E'};
		int cooling = 2;
		
		System.out.println(leastInterval(tasks, cooling));
	}
}
