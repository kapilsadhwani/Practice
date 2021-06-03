package com.implement.pepcoding.graph;
import java.io.*;
import java.util.*;

import com.implement.pepcoding.graph.Graph.Edge;
import com.implement.pepcoding.graph.Graph.Pair;

/*
 * Input :

7
8
0 1 10
1 2 10
2 3 10
0 3 10
3 4 10
4 5 10
5 6 10
4 6 10
6
3
 
 */

public class SpreadInfection {
   static class Edge {
      int src;
      int nbr;
      int wt;

      Edge(int src, int nbr, int wt){
         this.src = src;
         this.nbr = nbr;
         this.wt = wt;
      }
   }
   
	static class Pair {
		int v;
		int time;

		public Pair(int v, int time) {
			this.v = v;
			this.time = time;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.v + "@" + this.time;
		}
	}
   
	// Count of people infected by time t
	public static void checkInfectionSpread(ArrayList<Edge>[] graph, int src, int t) {
		int[] visited = new int[graph.length];

		Queue<Pair> queue = new LinkedList<>();
		int count = 0;

		// Enqueue starting vertex
		queue.offer(new Pair(src, 1));

		Pair rem;

		while (queue.size() > 0) {
			// r m* w n*

			// Dequeue a vertex from queue and process
			rem = queue.poll();
			
			if (visited[rem.v] > 0) {
				continue;
			}
			
			if (rem.time > t) {
				break;
			}
			
			// Mark vertex as visited
			visited[rem.v] = rem.time;

			count++;
			/*
			 * Get all neighbors If a neighbor is not visited before, then
			 * enqueue and mark it as visited
			 */

			for (Edge e : graph[rem.v]) {
				if (visited[e.nbr] == 0) {
					queue.offer(new Pair(e.nbr, rem.time + 1));
				}
			}
		}

		System.out.println("Number of persons infected: " + count);
	}
   
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int vtces = Integer.parseInt(br.readLine());
		ArrayList<Edge>[] graph = new ArrayList[vtces];
		for (int i = 0; i < vtces; i++) {
			graph[i] = new ArrayList<>();
		}

		int edges = Integer.parseInt(br.readLine());
		for (int i = 0; i < edges; i++) {
			String[] parts = br.readLine().split(" ");
			int v1 = Integer.parseInt(parts[0]);
			int v2 = Integer.parseInt(parts[1]);
			int wt = Integer.parseInt(parts[2]);
			graph[v1].add(new Edge(v1, v2, wt));
			graph[v2].add(new Edge(v2, v1, wt));
		}

		int src = Integer.parseInt(br.readLine());
		int t = Integer.parseInt(br.readLine());
		
		// write your code here
		checkInfectionSpread(graph, src, t);
	}

}