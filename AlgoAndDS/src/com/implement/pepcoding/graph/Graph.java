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
8
0 1 10
1 2 10
2 3 10
0 3 10
3 4 10
4 5 10
5 6 10
4 6 10
0
6
================================
 * Hamiltonian Path or Cycle Input :

7
9
0 1 10
1 2 10
2 3 10
0 3 10
3 4 10
4 5 10
5 6 10
4 6 10
2 5 10
0
6 <-- will be ignored
================================
 * BFS Input :
7
8
0 1 10
1 2 10
2 3 10
0 3 10
3 4 10
4 5 10
5 6 10
4 6 10
2
6 <-- will be ignored
================================
 * DFS Input :
7
8
0 1 10
1 2 10
2 3 10
0 3 10
3 4 10
4 5 10
5 6 10
4 6 10
2
6 <-- will be ignored
 */

public class Graph {
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
		int level;
		String psf;

		public Pair(int v, String psf, int level) {
			this.v = v;
			this.psf = psf;
			this.level = level;
		}
		
		public Pair(int v, String psf) {
			this.v = v;
			this.psf = psf;
		}
		
		public Pair(int v, int level) {
			this.v = v;
			this.level = level;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.v + "@" + this.psf;
		}
	}
   
   // Using DFS
   public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited){
	   if (src == dest){
		   return true;
	   }
	   
	   visited[src] = true;
	   for(Edge edge : graph[src]){
		   if(visited[edge.nbr] == false){
			   boolean hasNbrPath = hasPath(graph, edge.nbr, dest, visited);
			   
			   if(hasNbrPath){
				   return true;
			   }
	   		}
	   }
	   
	   return false;
   }
   
   // Using DFS
   public static void printPaths(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, String psf){
	   if (src == dest){
		   System.out.println(psf);
		   return;
	   }
	   
	   visited[src] = true;
	   for(Edge edge : graph[src]){
		   if(visited[edge.nbr] == false){
			   printPaths(graph, edge.nbr, dest, visited, psf + edge.nbr);
	   		}
	   }
	   
	   visited[src] = false;
   }
   
	public static void hamiltonianPathOrCycle(ArrayList<Edge>[] graph, int osrc, int src,
			HashSet<Integer> visited, String psf) {
		// Visited contains all node except current src

		// All are visited
		if (visited.size() == graph.length - 1) {
			System.out.print(psf);

			boolean cycleFound = false;
			// Check if 'osrc' is its neighbor
			for (Edge edge : graph[src]) {
				if (edge.nbr == osrc) {
					cycleFound = true;
					break;
				}
			}

			if (cycleFound == true) {
				System.out.println("*");
			} else {
				System.out.println(".");
			}

			return;
		}

		visited.add(src);

		for (Edge edge : graph[src]) {
			if (visited.contains(edge.nbr) == false) {
				hamiltonianPathOrCycle(graph, osrc, edge.nbr, visited, psf + edge.nbr);
			}
		}

		visited.remove(src);
	}
	
	// Check for Hamiltonian Path or Cycle
	public static void hamiltonianPathOrCycle(ArrayList<Edge>[] graph, int src) {
		HashSet<Integer> visited = new HashSet<>();
		hamiltonianPathOrCycle(graph, src, src, visited, src + "");
	}
   
	// BFS traversal of a given Graph
	private static void bfsTraversal(ArrayList<Edge>[] graph, int src) {
		Queue<Pair> queue = new LinkedList<>();
		boolean[] visited = new boolean[graph.length];
		
		// Enqueue starting vertex
		queue.offer(new Pair(src, src + ""));

		Pair rem;
		
		while (queue.size() > 0) { // or !queue.isEmpty()
			// r m* w n*
			
			// Dequeue a vertex from queue and print it
			rem = queue.poll();
			
			if(visited[rem.v] == true){
				continue;
			}
			
			// Mark vertex as visited
			visited[rem.v] = true;
			
			System.out.println(rem);

			/*
			 *  Get all neighbors
			 *  If a neighbor is not visited before, then enqueue
			 *  and mark it as visited
			 */
			
			for (Edge e : graph[rem.v]) {
				if (visited[e.nbr] == false) {
					queue.offer(new Pair(e.nbr, rem.psf + e.nbr));
				}
			}
		}
	}
	
	// Iterative DFS traversal of a given Graph
	private static void dfsIterative(ArrayList<Edge>[] graph, int src) {
		Stack<Pair> st = new Stack<>();
		boolean[] visited = new boolean[graph.length];

		// Push starting vertex
		st.push(new Pair(src, src + ""));

		Pair rem;

		while (st.size() > 0) { // or !queue.isEmpty()
			// r m* w n*

			// Dequeue a vertex from queue and print it
			rem = st.pop();

			if (visited[rem.v] == true) {
				continue;
			}

			// Mark vertex as visited
			visited[rem.v] = true;

			System.out.println(rem.v+"@"+rem.psf);

			/*
			 * Get all neighbors If a neighbor is not visited before, then
			 * enqueue and mark it as visited
			 */

			for (Edge e : graph[rem.v]) {
				if (visited[e.nbr] == false) {
					st.push(new Pair(e.nbr, rem.psf + e.nbr));
				}
			}
		}
	}
	
	// BFS traversal to detect a cycle
	private static boolean isCyclic(ArrayList<Edge>[] graph, int src, boolean[] visited) {
		Queue<Pair> queue = new LinkedList<>();

		// Enqueue starting vertex
		queue.offer(new Pair(src, src + ""));

		Pair rem;

		while (queue.size() > 0) { // or !queue.isEmpty()
			// r m* w n*

			// Dequeue a vertex from queue and process
			rem = queue.poll();

			if (visited[rem.v] == true) {
				// Cycle found
				return true;
			}

			// Mark vertex as visited
			visited[rem.v] = true;

			/*
			 * Get all neighbors If a neighbor is not visited before, then
			 * enqueue and mark it as visited
			 */

			for (Edge e : graph[rem.v]) {
				if (visited[e.nbr] == false) {
					queue.offer(new Pair(e.nbr, rem.psf + e.nbr));
				}
			}
		}
		
		return false;
	}
	
	// Detect cycle in a graph
	public static void isCyclic(ArrayList<Edge>[] graph) {
		boolean[] visited = new boolean[graph.length];

		// Call the helper function to detect cycle for all vertices one by one
		for (int i = 0; i < graph.length; ++i) {
			if (visited[i] == false) {
				boolean cycle = isCyclic(graph, i, visited);
				if (cycle) {
					System.out.println("Given graph is cyclic");
					return;
				}
			}
		}

		System.out.println("Given graph is Acyclic");
	}
	
	// BFS traversal to detect if a graph is Bipartite
	private static boolean isBipartite(ArrayList<Edge>[] graph, int src, int[] visited) {
		Queue<Pair> queue = new LinkedList<>();

		// Enqueue starting vertex
		queue.offer(new Pair(src, src + "", 0));

		Pair rem;

		while (queue.size() > 0) { // or !queue.isEmpty()
			// r m* w n*

			// Dequeue a vertex from queue and process
			rem = queue.poll();

			if (visited[rem.v] != -1) {
				// Represents a Cycle, check for Bipartite
				if(rem.level != visited[rem.v]){
					return false;
				}
			}else{
				// Mark vertex as visited using its level
				visited[rem.v] = rem.level;
			}	
			
			/*
			 * Get all neighbors If a neighbor is not visited before, then
			 * enqueue and mark it as visited
			 */

			for (Edge e : graph[rem.v]) {
				if (visited[e.nbr] == -1) {
					queue.offer(new Pair(e.nbr, rem.psf + e.nbr, rem.level + 1));
				}
			}
		}

		return true;
	}
	
	/*
	 * Is a Graph Bipartite
	 * Note -> A graph is called bipartite if it is possible to split it's vertices in two sets of mutually 
	 * exclusive and exhaustive vertices such that all edges are across sets.
	 */
	public static void isBipartite(ArrayList<Edge>[] graph) {
		int[] visited = new int[graph.length];
		Arrays.fill(visited, -1);

		// Call the helper function to check bipartiteness for all vertices one by one
		for (int i = 0; i < graph.length; ++i) {
			if (visited[i] == -1) {
				boolean bipartite = isBipartite(graph, i, visited);
				if (bipartite == false) {
					System.out.println("Given graph is not Bipartite");
					return;
				}
			}
		}

		System.out.println("Given graph is Bipartite");
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
         int wt = Integer.parseInt(parts[2]);
         graph[v1].add(new Edge(v1, v2, wt));
         graph[v2].add(new Edge(v2, v1, wt));
      }

      int src = Integer.parseInt(br.readLine());
      int dest = Integer.parseInt(br.readLine());

      // write your code here
      
      boolean visited[] = new boolean[vtces];
      boolean path = hasPath(graph, src, dest, visited);
      
      System.out.println("Has Path (src" + " -> " + dest + "): " + path);
      System.out.println("Paths:");
      
      visited = new boolean[vtces];
      printPaths(graph, src, dest, visited, src + "");
      
      System.out.println("=====================================");
      hamiltonianPathOrCycle(graph, src);
      
      System.out.println("=====================================");
      System.out.println("BFS Traversal:");
      bfsTraversal(graph, src);
      
      isCyclic(graph);
      isBipartite(graph);
      
      System.out.println("=====================================");
      System.out.println("DFS Iterative");
    		  
      dfsIterative(graph, src);
    }

}