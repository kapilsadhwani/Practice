package com.implement.stack;

import java.util.Comparator;
import java.util.PriorityQueue;

class LastStoneWeight {	
	public static int lastStoneWeight(int[] stones) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(stones.length, 
				new Comparator<Integer>() {
					// Place in descending order
					@Override
					public int compare(Integer o1, Integer o2) {
						// TODO Auto-generated method stub
						return o2.compareTo(o1);
					}
				});

		for (int stone : stones) {
            pq.offer(stone);
        }

		while (pq.size() > 1) {
			pq.offer(pq.poll() - pq.poll());
		}

		
		return pq.poll();
	}

	// Driver Code
	public static void main(String[] args) {
		int[] stones = {1,7,9,5,3,16,22,38};
		System.out.println(lastStoneWeight(stones));
	}
}
