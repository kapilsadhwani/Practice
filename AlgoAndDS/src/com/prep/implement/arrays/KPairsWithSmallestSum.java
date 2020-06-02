package com.prep.implement.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class KPairsWithSmallestSum {
	/*private List<List<Integer>> result = new ArrayList<List<Integer>>();
	
	class QueueNode implements Comparable<QueueNode> {
		int array_idx, element_idx, value;
		
		QueueNode(int array_idx, int element_idx, int value) {
			this.array_idx = array_idx;
			this.element_idx = element_idx;
			this.value = value;
		}

		@Override
		public int compareTo(QueueNode n) {
			// TODO Auto-generated method stub
			return value - n.value;
		}
		
	}

	private int addPairs(int value, int element_idx, int[] arr, int remainingK) {
		int count = 0;
		// TODO Auto-generated method stub
		for(int i=element_idx; i<arr.length && count<remainingK; i++){
			result.add(Arrays.asList(value, arr[i]));
			count++;
			
			if(count == remainingK || arr[i] == value) 
				break;
		}
		
		return count;
	}

	public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		if(nums1.length == 0 || nums2.length == 0 || k == 0)
			return result;
		
		PriorityQueue<QueueNode> queue = new PriorityQueue<QueueNode>(2);
		
		queue.offer(new QueueNode(0, 0, nums1[0]));
		queue.offer(new QueueNode(1, 0, nums2[0]));
		
		QueueNode minNode = null;
		int newIndex;
		int pairCount=0;
		
		while(pairCount < k && queue.size() == 2){
			minNode = queue.poll();
			
			newIndex = minNode.element_idx + 1;
			
			if(minNode.array_idx == 0){
				pairCount += addPairs(minNode.value, queue.peek().element_idx, nums2, k - pairCount);
				
				if(newIndex < nums1.length)
					queue.offer(new QueueNode(minNode.array_idx, newIndex, nums1[newIndex]));
			}else{
				pairCount += addPairs(minNode.value, queue.peek().element_idx, nums1, k - pairCount);
				
				if(newIndex < nums2.length)
					queue.offer(new QueueNode(minNode.array_idx, newIndex, nums2[newIndex]));
			}
		}
		
		return result;
	}*/
	
	int[][] dirs = {{0,1},{1,0},{1,1}};
	
	public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

		List<List<Integer>> result = new ArrayList<>();

		// EDGE CASE
		if (nums1 == null || nums2 == null || nums1.length == 0
				|| nums2.length == 0)
			return result;

		// visited array
		boolean[][] visited = new boolean[nums1.length][nums2.length];

		// Min Heap
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
			return (a[0] + a[1]) - (b[0] + b[1]);
		});

		int[] temp = new int[] { nums1[0], nums2[0], 0, 0 };
		pq.add(temp);
		visited[0][0] = true;

		while (!pq.isEmpty()) {
			int[] arr = pq.poll();
			List<Integer> ls = new ArrayList<>();
			ls.add(arr[0]);
			ls.add(arr[1]);
			result.add(ls);

			if (result.size() == k)
				break;
			int i = arr[2], j = arr[3];

			for (int[] dir : dirs) {
				int dx = i + dir[0], dy = j + dir[1];
				if (dx < 0 || dx >= nums1.length || dy < 0
						|| dy >= nums2.length || visited[dx][dy])
					continue;
				pq.add(new int[] { nums1[dx], nums2[dy], dx, dy });
				visited[dx][dy] = true;
			}
		}

		return result;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums1 = {1,7,11};
		int[] nums2 = {2,4,6};
		int k=3;
		
		KPairsWithSmallestSum pairs = new KPairsWithSmallestSum();
		
		List<List<Integer>> result = null;
		
		/*result = pairs.kSmallestPairs(nums1, nums2, k);
		
		if(result.size() > 0)
			System.out.println(result);
		else
			System.out.println("Invalid input !!!");
			
		result.clear();
		
		nums1 = new int[]{1,1,2};
		nums2 = new int[]{1,2,3};
		k=2;
		
		result = pairs.kSmallestPairs(nums1, nums2, k);
		
		if(result.size() > 0)
			System.out.println(result);
		else
			System.out.println("Invalid input !!!");
			
		result.clear();
		
		nums1 = new int[]{1,2};
		nums2 = new int[]{3};
		k=3;

		result = pairs.kSmallestPairs(nums1, nums2, k);
		
		if(result.size() > 0)
			System.out.println(result);
		else
			System.out.println("Invalid input !!!");
		
		result.clear();*/
		
		nums1 = new int[]{1,1,2};
		nums2 = new int[]{1,2,3};
		k=10;
		result = pairs.kSmallestPairs(nums1, nums2, k);
		
		if(result.size() > 0)
			System.out.println(result);
		else
			System.out.println("Invalid input !!!");
	}

}
