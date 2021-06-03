package com.implement.pepcoding.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Input:
2
1
1 0
Output: 0 1 
=================================================
4
4
1 0
2 0
3 1
3 2
Output: 0 1 2 3
=================================================
1
0
Output: 0
=================================================
2
2
1 0
0 1
Output: Empty array
 */

class CourseScheduleTopological {
	static class Edge {
		int src;
		int nbr;

		Edge(int src, int nbr) {
			this.src = src;
			this.nbr = nbr;
		}
	}

	/*
	 * Topological sort using Kahn's Algorithm
	 * Iterative Topological sort
	 */
	public static int[] findOrder(int numCourses, int[][] prerequisites) {
		if (numCourses == 0) return new int[0];
        
        if (prerequisites == null || prerequisites.length == 0) {
            int[] order = new int[numCourses];
            
            for(int i = 0; i < numCourses; i++)
                order[i] = i;
            
            return order;
        }
        
        // Create Graph
		ArrayList<Edge>[] graph = new ArrayList[numCourses];
		for (int i = 0; i < numCourses; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < prerequisites.length; i++) {
			int u = prerequisites[i][0];
			int v = prerequisites[i][1];
			
			/*
			 *  u --> v i.e Course u depends on course v
			 *  i.e v should be completed before u
			 */
			graph[u].add(new Edge(u, v));
		}
		
		
		// Kahn's Algorithm
		int[] indegree = new int[numCourses];
		int[] ans = new int[numCourses];

		// Calculate indegree for all the vertices
		for (int v = 0; v < numCourses; v++) {
			// Increment indegree of all the neighbors
			for (Edge e : graph[v]) {
				indegree[e.nbr]++;
			}
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int v = 0; v < numCourses; v++) {
			if (indegree[v] == 0) {
				queue.offer(v);
			}
		}

		/*
		 * Add in reverse order of Topological sort
		 */
		int idx = numCourses - 1;
		while (queue.size() > 0) {
			int rem = queue.poll();

			// This node has indegree 0, so include it in the ans
			ans[idx] = rem;
			idx--;

			/*
			 * Decrement indegree of its neighbors by 1 and If for any neighbor
			 * the indegree is 0, add it to the queue
			 */
			for (Edge e : graph[rem]) {
				indegree[e.nbr]--;

				if (indegree[e.nbr] == 0) {
					queue.offer(e.nbr);
				}
			}
		}

		if (idx >= 0) {
			return new int[0];
		} else {
			return ans;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int numCourses = Integer.parseInt(br.readLine());
		int edges = Integer.parseInt(br.readLine());

		int[][] prerequisites = new int[edges][2];
		String[] parts;

		for (int i = 0; i < edges; i++) {
			parts = br.readLine().split(" ");
			int v1 = Integer.parseInt(parts[0]);
			int v2 = Integer.parseInt(parts[1]);

			prerequisites[i][0] = v1;
			prerequisites[i][1] = v2;
		}

		// write your code here
		int[] ans = findOrder(numCourses, prerequisites);

		for (int val : ans) {
			System.out.print(val + " ");
		}
	}
}