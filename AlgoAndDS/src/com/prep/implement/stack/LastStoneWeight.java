package com.prep.implement.stack;

import java.util.Comparator;
import java.util.PriorityQueue;

class LastStoneWeight {
	/*public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) {
            pq.offer(stone);
        }

        for (int i = 0; i < stones.length - 1; i++) {
            pq.offer(pq.poll() - pq.poll());
        }
        return pq.poll();
    }*/
	
	public static int lastStoneWeight(int[] stones) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(stones.length, 
				new Comparator<Integer>() {

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
		int[] stones = {2,7,4,1,8,1};
		System.out.println(lastStoneWeight(stones));
	}
}
