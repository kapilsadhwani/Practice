package com.implement.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ConnectedComponents {
	// A utility function to exhaust a connected component from Vertex currentVertex
	private void exhaustCC(List<Integer>[] graph, int currentVertex, boolean[] visited) {
		if (graph[currentVertex] == null) {
            return;
        }
		
		// Mark this cell as visited
		visited[currentVertex] = true;
		
		List<Integer> neighbors = graph[currentVertex];

		for (Integer n: neighbors) {
			if (!visited[n]) {
				exhaustCC(graph, n, visited);
			}
		}
	}
	
	public void bfs(List<Integer>[] graph, int currentVertex, HashSet<Integer> visited){
		if (graph[currentVertex] == null) {
            return;
        }
		
	    Queue<Integer> q = new LinkedList<Integer>();
	    
	    q.offer(currentVertex);
	    visited.add(currentVertex);
	    
	    List<Integer> neighbors;
	    while(!q.isEmpty()){
	        int current = q.poll();
	        
	        neighbors = graph[current];
	        
	        for(int n : neighbors){
	            if(!visited.contains(n)){
	                q.offer(n);
	                visited.add(n);
	            }
	        }
	    }
	}

	public int countComponents(int n, int[][] edges) {
		// 1. Represent the graph using adjacency-lists;
		List<Integer>[] graph = new ArrayList[n];
		
		for (int i = 0; i < edges.length; i++) {
			int v1 = edges[i][0];
			int v2 = edges[i][1];
			if (graph[v1] == null) {
				graph[v1] = new ArrayList<Integer>();
			}
			if (graph[v2] == null) {
				graph[v2] = new ArrayList<Integer>();
			}
			graph[v1].add(v2);
			graph[v2].add(v1);
		}

		// 2. Mark all the vertices as not visited 
		boolean[] visited = new boolean[n];
		
		int count = 0;
		
		// 3. Iterate the graph from 0 to n - 1;
		for (int i = 0; i < graph.length; i++) {
			// 4. if the vertex hasn't been visited yet, DFS it and update count;
			if (!visited[i]) {
				exhaustCC(graph, i, visited);
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
