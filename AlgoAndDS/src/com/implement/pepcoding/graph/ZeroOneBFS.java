package com.implement.pepcoding.graph;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * Input :

7
7
0 1
2 1
2 3
4 5
5 1
6 3
6 4
0
6
 */

public class ZeroOneBFS {
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
		int wsf;
		
		public Pair(int v, int wsf) {
			this.v = v;
			this.wsf = wsf;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.v + "@" + this.wsf;
		}
	}
   
	// BFS traversal of a given Graph
	private static void bfsTraversal01(ArrayList<Edge>[] graph, int src, int dest) {
		LinkedList<Pair> queue = new LinkedList<>();
		boolean[] visited = new boolean[graph.length];
		
		// Enqueue starting vertex to the end of the list
		queue.addLast(new Pair(src,0));

		Pair rem;
		
		while (queue.size() > 0) { // or !queue.isEmpty()
			// r m* w n*
			
			// Dequeue a vertex from beginning of the list
			rem = queue.removeFirst();
			
			if(rem.v == dest){
				System.out.println(rem.wsf);
				return;
			}
			
			if(visited[rem.v] == true){
				continue;
			}
			
			// Mark vertex as visited
			visited[rem.v] = true;

			/*
			 *  Get all neighbors
			 *  If a neighbor is not visited before, then enqueue
			 *  and mark it as visited
			 */
			
			for (Edge e : graph[rem.v]) {
				if (visited[e.nbr] == false) {
					if(e.wt == 0){
						queue.addFirst(new Pair(e.nbr, rem.wsf + e.wt));
					}else{
						queue.addLast(new Pair(e.nbr, rem.wsf + e.wt));
					}
				}
			}
		}
		
		System.out.println("-1");
	}
	   
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int vtces = Integer.parseInt(br.readLine());
      ArrayList<Edge>[] graph = new ArrayList[vtces];
      for(int i = 0; i < vtces; i++){
         graph[i] = new ArrayList<>();
      }

      int edges = Integer.parseInt(br.readLine());
      for(int i = 0; i < edges; i++){
         String[] parts = br.readLine().split(" ");
         int v1 = Integer.parseInt(parts[0]);
         int v2 = Integer.parseInt(parts[1]);

         graph[v1].add(new Edge(v1, v2, 0));	// Given Edge (Cost: 0)
         graph[v2].add(new Edge(v2, v1, 1));	// Reversed Edge (Cost: 1)
      }

      int src = Integer.parseInt(br.readLine());	// We can start with 0
      int dest = Integer.parseInt(br.readLine());	// We can reach end

      // write your code here
      
      bfsTraversal01(graph, src, dest);
    }

}