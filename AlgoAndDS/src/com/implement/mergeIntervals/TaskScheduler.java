package com.implement.mergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class TaskScheduler {
	public static int leastInterval(char[] tasks, int n) {
		int[] map = new int[26];
		for (char c : tasks)
			map[c - 'A']++;
		
		PriorityQueue<Integer> queue = new PriorityQueue<>(26,
				Collections.reverseOrder());
		
		for (int f : map) {
			if (f > 0)
				queue.add(f);
		}
		
		int totalTime = 0;
		List<Integer> temp = new ArrayList<>();
		
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
				
				if (queue.isEmpty() && temp.size() == 0)
					break;
				
				cooling++;
			}
			
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
