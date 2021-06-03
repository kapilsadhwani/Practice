package com.implement.pepcoding.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/*
 * Input:
7
7
0 1
1 2
2 3
0 3
4 5
5 6
4 6
 */
public class TopologicalSort {
	static class Edge {
		int src;
		int nbr;

		Edge(int src, int nbr) {
			this.src = src;
			this.nbr = nbr;
		}
	}
	
	// Traverse in Post order way
	private static void topologicalSort(ArrayList<Edge>[] graph, int src, boolean[] visited, Stack<Integer> st) {
		// Mark this node as visited
		visited[src] = true;

		// Traverse neighbors
		for (Edge e : graph[src]) {
			if (visited[e.nbr] == false) {
				topologicalSort(graph, e.nbr, visited, st);
			}
		}
		
		st.push(src);
	}
	
	private static void topologicalSort(ArrayList<Edge>[] graph) {
		Stack<Integer> st = new Stack<>();
		
		boolean[] visited = new boolean[graph.length];
		
		for (int v = 0; v < graph.length; v++) {
			if(visited[v] == false){
				topologicalSort(graph, v, visited, st);
			}
		}
		
		while(st.size() > 0)
			System.out.println(st.pop());
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
			graph[v1].add(new Edge(v1, v2));
		}

		// write your code here
		topologicalSort(graph);
	}

}
