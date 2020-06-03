package com.implement.graph;

// Java program to print BFS traversal from a given source vertex. 
// BFS(int s) traverses vertices reachable from s. 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

// This class represents a directed graph using adjacency list representation 
class Graph {
	private int N; // No. of vertices
	private Vertex[] vertices;

	// Constructor
	Graph(int v) {
		this.N = v;
		this.vertices = new Vertex[v];
	}

	// Function to add an edge into the graph [u --> v]
	void addEdge(Vertex u, Vertex v) {
		u.addNeighbor(v);
	}

	void createGraph(boolean shortest) {
		if (this.N > 6) {
			for (int i = 0; i < this.N; i++) {
				this.vertices[i] = new Vertex((i + 1) * 10);
			}
		} else {
			for (int i = 0; i < this.N; i++) {
				this.vertices[i] = new Vertex(i + 1);
			}
		}
		if (this.N == 5 && !shortest) {
			/*
			 * Graph:				1 
			 * 					  _/ \_ 
			 *                   2 		3 
			 *                   \_   _/ 
			 *                   	4 
			 *                   	| 
			 *                   	~~ 
			 *                   	5
			 */
			addEdge(this.vertices[0], this.vertices[1]); // 1 --> 2
			addEdge(this.vertices[0], this.vertices[2]); // 1 --> 3
			addEdge(this.vertices[1], this.vertices[3]); // 2 --> 4
			addEdge(this.vertices[2], this.vertices[3]); // 3 --> 4
			addEdge(this.vertices[3], this.vertices[4]); // 4 --> 5
		} else if (this.N == 5) {
			/*
			 * Graph:			1 
			 * 				  _/ \_ 
			 * 				2 	  _	3 --> 5 
			 * 				\_ 	 / 
			 * 				   4
			 */
			addEdge(this.vertices[0], this.vertices[1]); // 1 --> 2
			addEdge(this.vertices[0], this.vertices[2]); // 1 --> 3
			addEdge(this.vertices[1], this.vertices[3]); // 2 --> 4
			addEdge(this.vertices[3], this.vertices[2]); // 4 --> 3
			addEdge(this.vertices[2], this.vertices[4]); // 3 --> 5
		} else if (this.N == 6) {
			/*
			 * Graph:	 		  5 _ 	  6 
			 * 					_/   \ 	_/ \_ 
			 * 				   2 	   3 	 1 
			 * 					 \_ _/ 
			 * 					   4
			 */
			addEdge(this.vertices[5], this.vertices[2]); // 6 --> 3
			addEdge(this.vertices[5], this.vertices[0]); // 6 --> 1
			addEdge(this.vertices[4], this.vertices[1]); // 5 --> 2
			addEdge(this.vertices[2], this.vertices[4]); // 3 --> 5
			addEdge(this.vertices[2], this.vertices[3]); // 3 --> 4
			addEdge(this.vertices[1], this.vertices[3]); // 2 --> 4
		} else if (this.N == 7) {
			/*
			 * Graph: 		40 -----> 20 -----> 50 
			 * 				|		/  |  \		  \__
			 * 				| 	   /   |   \	    _ 70
			 * 				| 	  /	   |    \	   /
			 * 				|_	_/	   |     \_	  /
			 * 				  10 ---> 30 ----> 60
			 */
			addEdge(this.vertices[3], this.vertices[0]); // 40 --> 10
			addEdge(this.vertices[3], this.vertices[1]); // 40 --> 20
			addEdge(this.vertices[1], this.vertices[0]); // 20 --> 10
			addEdge(this.vertices[1], this.vertices[2]); // 20 --> 30
			addEdge(this.vertices[1], this.vertices[4]); // 20 --> 50
			addEdge(this.vertices[1], this.vertices[5]); // 20 --> 60
			addEdge(this.vertices[0], this.vertices[2]); // 10 --> 30
			addEdge(this.vertices[2], this.vertices[5]); // 30 --> 60
			addEdge(this.vertices[4], this.vertices[6]); // 50 --> 70
			addEdge(this.vertices[5], this.vertices[6]); // 60 --> 70
		}
	}

	// prints BFS traversal from a given source or starting point s
	private void BFS(Vertex start, Set<Vertex> visited, Queue<Vertex> queue) {
		// if(visited.contains(start)) return;

		// Mark the current node as visited and enqueue it
		visited.add(start);
		queue.offer(start);

		Vertex currentNode;
		Set<Vertex> neighbors;

		while (queue.size() != 0) { // or !queue.isEmpty()
			// Dequeue a vertex from queue and print it
			currentNode = queue.poll();
			System.out.print(currentNode + " ");

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			neighbors = currentNode.getNeighbors();

			for (Vertex n : neighbors) {
				if (!visited.contains(n)) {
					visited.add(n);
					queue.offer(n);
				}
			}

			/*
			 * neighbors.forEach(n -> { if (!visited.contains(n)){
			 * visited.add(n); queue.offer(n); } });
			 */
		}
	}

	// The function to do BFS traversal. It uses BFS(Vertex s, Set<Vertex>
	// visited)
	public void BFS() {
		Set<Vertex> visited = new HashSet<Vertex>();

		// Create a queue for BFS
		Queue<Vertex> queue = new LinkedList<Vertex>();

		BFS(this.vertices[3], visited, queue); // BFS starting with 40

		// Call the helper function to print BFS traversal
		// starting from all vertices one by one
		for (int i = 0; i < N; ++i)
			if (!visited.contains(this.vertices[i]))
				BFS(this.vertices[i], visited, queue);
	}

	// prints all not yet visited vertices reachable from s
	private void DFS(Vertex start, Set<Vertex> visited, Stack<Vertex> stack) {
		// if(visited.contains(start)) return;

		// Mark the current node as visited and push it
		visited.add(start);
		stack.push(start);

		Vertex currentNode;
		Set<Vertex> neighbors;

		while (stack.size() > 0) {
			// Pop a vertex from stack and print it
			currentNode = stack.pop();

			System.out.print(currentNode + " ");

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			neighbors = currentNode.getNeighbors();

			for (Vertex n : neighbors) {
				if (!visited.contains(n)) {
					visited.add(n);
					stack.push(n);
				}
			}
		}
	}

	// The function prints all vertices in DFS manner
	public void DFS() {
		Set<Vertex> visited = new HashSet<Vertex>();

		// Create a stack for DFS
		Stack<Vertex> stack = new Stack<Vertex>();

		DFS(this.vertices[3], visited, stack); // DFS starting with 40

		// Call the helper function to print DFS traversal
		// starting from all vertices one by one
		for (int i = 0; i < N; ++i)
			if (!visited.contains(this.vertices[i]))
				DFS(this.vertices[i], visited, stack);
	}

	private void DFSRecursive(Vertex s, Set<Vertex> visited) {
		if (s == null)
			return;

		System.out.print(s + " ");
		visited.add(s);

		Set<Vertex> neighbors = s.getNeighbors();
		for (Vertex n : neighbors) {
			if (!visited.contains(n))
				DFSRecursive(n, visited);
		}
	}

	// RTC for Graph = O(V+E), E=Sum of degrees of all the vertices and V=once for each vertex
	public void DFSRecursive(Vertex start) {
		Set<Vertex> visited = new HashSet<Vertex>();

		if (start == null)
			return;

		DFSRecursive(start, visited);
	}

	// Find shortest path from start to end
	public List<Vertex> shortestPath(Vertex start, Vertex end) {
		Map<Vertex, Vertex> backRefs = new HashMap<Vertex, Vertex>();

		// Create a queue for BFS
		Queue<Vertex> queue = new LinkedList<Vertex>();

		// Mark the current node as visited and enqueue it. Use backRef as
		// visited set
		queue.offer(start);
		backRefs.put(start, null);

		Vertex currentNode;
		Set<Vertex> neighbors;

		while (queue.size() != 0) { // or !queue.isEmpty()
			// Dequeue a vertex from queue and print it
			currentNode = queue.poll();

			if (currentNode.equals(end)) {
				break;
			}

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			neighbors = currentNode.getNeighbors();

			for (Vertex n : neighbors) {
				if (!backRefs.containsKey(n)) {
					queue.offer(n);
					backRefs.put(n, currentNode);
				}
			}
		}

		if (!backRefs.containsKey(end))
			return null;

		List<Vertex> path = new ArrayList<Vertex>();
		currentNode = end;

		while (currentNode != null) {
			path.add(currentNode);
			currentNode = backRefs.get(currentNode);
		}

		Collections.reverse(path);

		return path;
	}

	private boolean detectCycle(Vertex s, Set<Vertex> visited, Set<Vertex> path) {
		if (path.contains(s))	// Already visited within same path
			return true;

		if (visited.contains(s))
			return false;

		visited.add(s);
		path.add(s);

		Set<Vertex> neighbors = s.getNeighbors();
		for (Vertex n : neighbors) {
			if (detectCycle(n, visited, path)) {
				return true;
			}
		}

		path.remove(s);
		return false;
	}

	// Detect cycle in a graph
	public boolean hasCycle() {
		Set<Vertex> visited = new HashSet<Vertex>();
		Set<Vertex> path = new HashSet<Vertex>();

		// Call the helper function to detect cycle for all vertices one by one
		for (int i = 0; i < N; ++i)
			if (detectCycle(this.vertices[i], visited, path))
				return true;

		return false;
	}

	private Vertex cloneGraph(Vertex v, Map<Vertex, Vertex> cloneMap) {
		if (cloneMap.containsKey(v))
			return cloneMap.get(v);

		Vertex clone = new Vertex(v.val);
		cloneMap.put(v, clone);

		Set<Vertex> neighbors = v.getNeighbors();

		for (Vertex n : neighbors) {
			addEdge(clone, cloneGraph(n, cloneMap));
		}

		return clone;
	}

	// Clone a graph
	private Vertex cloneGraph(Vertex vertex) {
		Map<Vertex, Vertex> cloneMap = new HashMap<Vertex, Vertex>();
		return cloneGraph(vertex, cloneMap);
	}

	private void topologicalSort(Vertex start, Set<Vertex> visited, Stack<Vertex> stack) {
		// Mark the current node as visited and push it
		visited.add(start);

		Set<Vertex> neighbors = start.getNeighbors();

		// Get all adjacent vertices of the vertex s and recurse
		neighbors.forEach(n -> {
			if (!visited.contains(n))
				topologicalSort(n, visited, stack);
		});

		stack.push(start);
	}

	// The function prints all vertices in Topological Sort manner
	public void topologicalSort() {
		Set<Vertex> visited = new HashSet<Vertex>();
		Stack<Vertex> stack = new Stack<Vertex>();

		// Call the helper function starting from all vertices one by one
		for (int i = 0; i < N; ++i)
			if (!visited.contains(this.vertices[i]))
				topologicalSort(this.vertices[i], visited, stack);

		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}

	// Driver method to
	public static void main(String args[]) {
		Graph g = new Graph(7);
		g.createGraph(false);

		System.out.println("Following is traversal of the graph with "
				+ g.vertices.length + " nodes in BFS manner");
		g.BFS();

		System.out.println("\nFollowing is traversal of the graph with "
				+ g.vertices.length + " nodes in DFS manner");
		g.DFS();

		System.out.println("\nFollowing is traversal of the cloned graph with "
				+ g.vertices.length
				+ " nodes in DFS manner starting with node "
				+ (g.vertices.length == 5 ? 1 : g.vertices.length == 6 ? 6
						: g.vertices[3]));
		if (g.vertices.length == 5)
			g.DFSRecursive(g.vertices[0]);
		else if (g.vertices.length == 6)
			g.DFSRecursive(g.vertices[g.vertices.length - 1]);
		else
			g.DFSRecursive(g.vertices[3]);

		System.out.println("\nFollowing is shortest path from 1 to "
				+ g.vertices.length);
		List<Vertex> path = null;

		if (g.vertices.length == 5) {
			path = g.shortestPath(g.vertices[0], g.vertices[4]);
		} else if (g.vertices.length == 6) {
			path = g.shortestPath(g.vertices[5], g.vertices[3]);
		} else {
			path = g.shortestPath(g.vertices[3], g.vertices[6]);
		}

		if (path != null) {
			for (Vertex v : path) {
				System.out.print(" --> " + v);
			}
		}

		System.out.println("\nDoes Graph with " + g.vertices.length
				+ " nodes have a cycle? " + g.hasCycle());

		System.out.println("\nFollowing is traversal of the cloned graph with "
				+ g.vertices.length
				+ " nodes in DFS manner starting with node "
				+ (g.vertices.length == 5 ? 1 : g.vertices.length == 6 ? 6
						: g.vertices[3]));
		if (g.vertices.length == 5)
			g.DFSRecursive(g.cloneGraph(g.vertices[0]));
		else if (g.vertices.length == 6)
			g.DFSRecursive(g.cloneGraph(g.vertices[g.vertices.length - 1]));
		else
			g.DFSRecursive(g.cloneGraph(g.vertices[3]));

		System.out.println("\nTopological Sort : ");
		g.topologicalSort();
	}

	static class Vertex implements Comparable<Vertex> {
		private int val;
		private Set<Vertex> neighbors;

		public Vertex(int value) {
			this.val = value;
			this.neighbors = new HashSet<Vertex>();
		}

		public int getVal() {
			return this.val;
		}

		public Set<Vertex> getNeighbors() {
			return this.neighbors;
		}

		public void addNeighbor(Vertex v) {
			this.neighbors.add(v);
		}

		@Override
		public int compareTo(Vertex vertex) {
			if (vertex != null)
				return this.val - vertex.val;
			return 1;
		}

		// @Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Vertex))
				return false;

			return (this.val == ((Vertex) obj).val);
		}

		@Override
		public int hashCode() {
			return this.val;
		}

		public String toString() {
			return String.valueOf(this.val);
		}
	}
}