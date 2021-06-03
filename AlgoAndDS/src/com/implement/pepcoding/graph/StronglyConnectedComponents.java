package com.implement.pepcoding.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/*
 * Kosaraju Algorithm
 * 
 * Step 1: Generate specific order in a stack, using dfsPostOrder
 * Step 2: Reverse all edges;
 * Step 3: While stack is not empty, pop, exhaust neighbors
 * 			and increment count by 1
 * 
 * Input:
13
15
0 1
1 2
2 3
2 4
3 0
4 5
5 6
6 7
7 4
8 5
8 9
9 10
9 12
10 11
11 8
Output: 4
 */
public class StronglyConnectedComponents {
	static class Edge {
		int src;
		int nbr;

		Edge(int src, int nbr) {
			this.src = src;
			this.nbr = nbr;
		}
	}
	
	// A utility function to exhaust a connected component from currentVertex
	private static void exhaustCC(ArrayList<Edge>[] graph, int src, boolean[] visited) {
		// Mark this node as visited
		visited[src] = true;

		// Traverse neighbors
		for (Edge edge : graph[src]) {
			if (visited[edge.nbr] == false) {
				exhaustCC(graph, edge.nbr, visited);
			}
		}
	}
	
	// Traverse in Post order way
	private static void dfsPostOrder(ArrayList<Edge>[] graph, int src, boolean[] visited, Stack<Integer> st) {
		// Mark this node as visited
		visited[src] = true;

		// Traverse neighbors
		for (Edge e : graph[src]) {
			if (visited[e.nbr] == false) {
				dfsPostOrder(graph, e.nbr, visited, st);
			}
		}
		
		st.push(src);
	}
	
	// Transpose of a given graph
	private static ArrayList<Edge>[] transpose(ArrayList<Edge>[] graph, int vtces) {
		ArrayList<Edge>[] newGraph = new ArrayList[vtces];
		for (int i = 0; i < vtces; i++) {
			newGraph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < vtces; i++) {
			for(Edge e: graph[i]){
				newGraph[e.nbr].add(new Edge(e.nbr, i)); 
			}
		}
		
		return newGraph;
	}

	private static int kosaraju(ArrayList<Edge>[] graph, int vtces) {
		Stack<Integer> st = new Stack<Integer>();
		boolean[] visited = new boolean[vtces];

		/*
		 * Step 1: Generate specific order
		 */
		for (int v = 0; v < vtces; v++) {
			if (visited[v] == false) {
				dfsPostOrder(graph, v, visited, st);
			}
		}
		
		/*
		 * Step 2: Reverse all edges
		 */
		ArrayList<Edge>[] newGraph = transpose(graph, vtces);
		
		/*
		 * Step 3: While stack is not empty, pop, exhaust neighbors
		 * and increment count by 1
		 */
		
		visited = new boolean[vtces];
		int count = 0;
		
		while(st.size() > 0){
			int rem = st.pop();
			
			if(visited[rem] == false){
				exhaustCC(newGraph, rem, visited);
				count++;
			}
		}
		return count;
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
		int count = kosaraju(graph, vtces);
		System.out.println("Number of strongly connected components: " + count);
	}

}
