package com.implement.pepcoding.graph;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

import com.implement.pepcoding.graph.Graph.Edge;

/*
 * Given a graph and a source vertex. The vertices represent computers and the edges 
 * represent length of LAN wire required to connect them.
 * Find the minimum length of wire required to connect all PCs over a network. 
 * Print the output in terms of which all PCs need to be connected, and the length of wire between them.
 * 
 * Input :
7
9
0 1 10
1 2 10
2 3 10
0 3 40
3 4 2
4 5 3
5 6 3
4 6 8
2 5 5

==========================
Input : Negative cycle detection
4
4
0 1 1
1 2 -1
2 3 -1
3 0 -1
 */

public class BellmanFord {
	static class Edge {
		int src;
		int nbr;
		int wt;

		Edge(int src, int nbr, int wt) {
			this.src = src;
			this.nbr = nbr;
			this.wt = wt;
		}
	}
   
	private static void shortestPath(int vtces, int[][] edges) {
		int[] path = new int[vtces];

		// Initially all vertices are at Infinite path
		Arrays.fill(path, Integer.MAX_VALUE);

		// Pick a source path
		path[0] = 0;

		/*
		 * Iterate V - 1 times and fill path array In each iteration (i), you
		 * will find shortest path (in terms of edge weight), for a vertex that
		 * is at length i (or greater) from the sources i.e For each vertex the
		 * shortest path will be found in at max ith iteration
		 */
		for (int i = 0; i < vtces - 1; i++) {
			// Check for all edges
			for (int e = 0; e < edges.length; e++) {
				// Relax edge for this vertex
				int u = edges[e][0];
				int v = edges[e][1];
				int wt = edges[e][2];

				if (path[u] == Integer.MAX_VALUE) {
					continue;
				}

				if (path[u] + wt < path[v]) {
					path[v] = path[u] + wt;
				}
			}
		}

		System.out.println(Arrays.toString(path));
		
		/*// While printing, ignore starting point
		for (int v = 1; v < vtces; v++) {
			if(path[v] != Integer.MAX_VALUE){
				System.out.print(path[v] + " ");
			}else{
				System.out.print("1000000000 ");
			}
			System.out.println();
		}*/

	}
	
	private static boolean isNegativeWeightCycle(int vtces, int[][] edges) {
		int[] path = new int[vtces];

		// Initially all vertices are at Infinite path
		Arrays.fill(path, Integer.MAX_VALUE);

		// Pick a source path
		path[0] = 0;

		/*
		 * Iterate V - 1 times and fill path array In each iteration (i), you
		 * will find shortest path (in terms of edge weight), for a vertex that
		 * is at length i (or greater) from the sources i.e For each vertex the
		 * shortest path will be found in at max ith iteration
		 */
		for (int i = 0; i < vtces - 1; i++) {
			// Check for all edges
			for (int e = 0; e < edges.length; e++) {
				// Relax edge for this vertex
				int u = edges[e][0];
				int v = edges[e][1];
				int wt = edges[e][2];

				if (path[u] == Integer.MAX_VALUE) {
					continue;
				}

				if (path[u] + wt < path[v]) {
					path[v] = path[u] + wt;
				}
			}
		}
		
		/*
		 * To detect negative weight cycle, iterate one more time
		 * and if any weight is relaxing, return true. Otherwise return false
		 */
		for (int e = 0; e < edges.length; e++) {
			// Relax edge for this vertex
			int u = edges[e][0];
			int v = edges[e][1];
			int wt = edges[e][2];

			if (path[u] == Integer.MAX_VALUE) {
				continue;
			}

			if (path[u] + wt < path[v]) {
				// Negative Cycle found
				return true;
			}
		}
		

		return false;
	}
   
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int vtces = Integer.parseInt(br.readLine());
		int edges = Integer.parseInt(br.readLine());
		
		// Array of edges - Edge[src,nbr,wt]
		int[][] graph = new int[edges][3];
		String[] parts;
		
		for (int i = 0; i < edges; i++) {
			parts = br.readLine().split(" ");
			int v1 = Integer.parseInt(parts[0]);
			int v2 = Integer.parseInt(parts[1]);
			int wt = Integer.parseInt(parts[2]);
			graph[i][0] = v1;
			graph[i][1] = v2;
			graph[i][2] = wt;
		}

		// write your code here
		shortestPath(vtces, graph);
		System.out.println("Negative Weight Cycle: " + isNegativeWeightCycle(vtces, graph));
	}

}