package com.implement.pepcoding.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/*
 * Kosaraju Algorithm
 * 
 * Step 1: Fill a stack, using dfsPostOrder
 * Step 2: Check if top of stack is a mother vertex
 * 		   i.e pop and check current can visit all other vertices
 * Input:
8
9
0 1
1 2
2 3
4 5
6 0
6 2
6 4
6 5
6 7

Output: 6
 */
public class MotherVertex {
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
		count++;

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
	
	static int count;

	private static int findMotherVertex(ArrayList<Edge>[] graph, int vtces) {
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
		
		visited = new boolean[vtces];
		int rem = st.size() > 0 ? st.pop() : -1;
		
		if(rem != -1){
			count = 0;
			exhaustCC(graph, rem, visited);
			
			if(count == vtces){
				return rem;
			}
		}
		
		return -1;
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
		int motherVrtx = findMotherVertex(graph, vtces);
		System.out.println("Mother Vertex: " + motherVrtx);
	}

}
