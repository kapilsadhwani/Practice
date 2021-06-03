package com.implement.pepcoding.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import sun.nio.cs.ext.ISCII91;

import com.implement.pepcoding.graph.Multisolver.Edge;

/*
 * Input:
7
5
0 1
2 3
4 5
5 6
4 6
 */
public class PerfectFriends {
	static class Edge {
		int v;
		int n;

		Edge(int v, int n) {
			this.v = v;
			this.n = n;
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
		for (Edge edge : graph[src]) {
			if (visited[edge.n] == false) {
				exhaustCC(graph, edge.n, comp, visited);
			}
		}
	}

	private static int numberOfWays(ArrayList<Edge>[] graph, int vtces) {
		ArrayList<ArrayList<Integer>> comps = new ArrayList<>();

		boolean[] visited = new boolean[vtces];
		
		for (int v = 0; v < vtces; v++) {
			if (visited[v] == false) {
				ArrayList<Integer> comp = new ArrayList<>();
				exhaustCC(graph, v, comp, visited);
				comps.add(comp);
			}
		}
		
		int pairs = 0;
		
		for (int i = 0; i < comps.size(); i++) {
			for (int j = i + 1; j < comps.size(); j++) {
				int c1 = comps.get(i).size();
				int c2 = comps.get(j).size();

				pairs = pairs + c1 * c2;
			}
		}

		return pairs;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	      int n = Integer.parseInt(br.readLine());
	      int k = Integer.parseInt(br.readLine());

		// write your code here
		ArrayList<Edge>[] graph = new ArrayList[n];
		for (int v = 0; v < n; v++) {
			graph[v] = new ArrayList<>();
		}

		for (int e = 0; e < k; e++) {
			String line = br.readLine();
			String[] parts = line.split(" ");
			int v1 = Integer.parseInt(parts[0]);
			int v2 = Integer.parseInt(parts[1]);
			graph[v1].add(new Edge(v1, v2));
			graph[v2].add(new Edge(v2, v1));
		}

		System.out.println("Number of ways : " + numberOfWays(graph, n));
	}

}
