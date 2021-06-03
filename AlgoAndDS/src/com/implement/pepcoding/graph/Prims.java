package com.implement.pepcoding.graph;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

/*
 * Given a graph and a source vertex. The vertices represent computers and the edges 
 * represent length of LAN wire required to connect them.
 * Find the minimum length of wire required to connect all PCs over a network. 
 * Print the output in terms of which all PCs need to be connected, and the length of wire between them.
 * 
 * Input :
7
8
0 1 10
1 2 10
2 3 10
0 3 40
3 4 2
4 5 3
5 6 3
4 6 8
0
 */

public class Prims {
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
   
   static class Pair implements Comparable<Pair> {
		int v;
		int av;
		int wt;
		
		public Pair(int v, int av, int wt) {
			this.v = v;
			this.av = av;
			this.wt = wt;
		}

		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			return this.wt - o.wt;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.v + "@" + this.av + "@" + this.wt;
		}
	}
   
	/*
	 *  BFS traversal with Priority Queue
	 *  Same as Dijkstra, except do put cumulative weights in queue. Only add the edge weight
	 */
   
	private static void minWireUsingPrims(ArrayList<Edge>[] graph, int src) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[graph.length];

		// Enqueue starting vertex
		pq.offer(new Pair(src, -1, 0));

		Pair rem;

		while (pq.size() > 0) { // or !queue.isEmpty()
			// r m* w n*

			// Dequeue a vertex from queue and print it
			rem = pq.poll();

			if (visited[rem.v] == true) {
				continue;
			}

			// Mark vertex as visited
			visited[rem.v] = true;

			// Work
			if(rem.av > -1){
				System.out.println("[" + rem.v  + "-" + rem.av + "@" + rem.wt + "]");
			}

			/*
			 * Get all neighbors If a neighbor is not visited before, then
			 * enqueue and mark it as visited
			 */

			for (Edge e : graph[rem.v]) {
				if (visited[e.nbr] == false) {
					pq.offer(new Pair(e.nbr, rem.v, e.wt));
				}
			}
		}
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

		// write your code here
		minWireUsingPrims(graph, src);
	}

}