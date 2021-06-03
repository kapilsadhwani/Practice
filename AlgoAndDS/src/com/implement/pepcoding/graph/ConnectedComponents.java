package com.implement.pepcoding.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * Input:
7
5
0 1 10
2 3 10
4 5 10
5 6 10
4 6 10
 */
public class ConnectedComponents {
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
	
	// A utility function to exhaust a connected component from currentVertex
	private static void exhaustCC(ArrayList<Edge>[] graph, int src, 
			ArrayList<Integer> comp, boolean[] visited) {
		// Mark this node as visited
		visited[src] = true;
		
		// Add this node to the component list
		comp.add(src);

		// Traverse neighbors
		for(Edge edge : graph[src]){
			   if(visited[edge.nbr] == false){
				   exhaustCC(graph, edge.nbr, comp, visited);
		   		}
		   }
	}
	
	private static int getConnectedComponents(ArrayList<Edge>[] graph, int vtces) {
		ArrayList<ArrayList<Integer>> comps = new ArrayList<>();

		boolean[] visited = new boolean[vtces];
		int count = 0;
		
		for (int v = 0; v < vtces; v++) {
			if(visited[v] == false){
				ArrayList<Integer> comp = new ArrayList<>();
				exhaustCC(graph, v, comp, visited);
				comps.add(comp);
				count++;
			}
		}
		
		System.out.println(comps);
		
		return count;
	}
	
	private static boolean isGraphConnected(ArrayList<Edge>[] graph, int vtces){
		int count = getConnectedComponents(graph, vtces);
		
		return count == 1;
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

		// write your code here
		int count = getConnectedComponents(graph, vtces);
		System.out.println("Number of connected components : " + count);
		
		System.out.println("Is connected graph? " + isGraphConnected(graph, vtces));
	}

}
