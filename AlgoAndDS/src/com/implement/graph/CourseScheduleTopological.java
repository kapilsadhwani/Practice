package com.implement.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

class CourseScheduleTopological {
	// Class to represent a graph
	class GraphCourse {
		private LinkedList<Integer>[] adjacencyList;

		GraphCourse(int nVertices) {
			adjacencyList = new LinkedList[nVertices];
			for (int i = 0; i < nVertices; i++) {
				adjacencyList[i] = new LinkedList<Integer>();
			}
		}

		// function to add an edge to graph
		void addEdge(int src, int dest) {
			adjacencyList[src].add(dest);
		}

		private int getNoOfVertices() {
			return adjacencyList.length;
		}

		// A recursive function used by topologicalSort (DFS in postorder)
		private boolean hasCycle(int currentVertex, boolean[] visited,
				Stack<Integer> stack, boolean[] path) {

			if (path[currentVertex]) {
				return true;
			}
			
			path[currentVertex] = true;

			// Recur for all the vertices adjacent to this vertex
			for (int n : adjacencyList[currentVertex]) {
				if (!visited[n]) {
					if(hasCycle(n, visited, stack, path))
						return true;
				}
			}

			// Push current vertex to stack which stores result
			stack.push(currentVertex);
			visited[currentVertex] = true;	// Imp in case of cycles
			
			path[currentVertex] = false;
			
			return false;
		}

		// prints a Topological Sort of the complete graph
		private int[] topologicalSort() {
			Stack<Integer> stack = new Stack<>();

			// Mark all the vertices as not visited
			boolean[] visited = new boolean[getNoOfVertices()];

			// To detect cycles
			boolean[] path = new boolean[getNoOfVertices()];

			// Call the recursive helper function to store Topological
			// Sort starting from all vertices one by one
			for (int i = 0; i < getNoOfVertices(); i++) {
				if (!visited[i]) {
					if (hasCycle(i, visited, stack, path)) {
						return new int[0];
					}
				}
			}

			int[] order = new int[getNoOfVertices()];
			
			for (int i = 0; i < order.length; i++) {
				order[i] = stack.pop();
			}

			return order;
		}
	}

	public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return new int[0];
        
        if (prerequisites == null || prerequisites.length == 0) {
            int[] order = new int[numCourses];
            
            for(int i = 0; i < numCourses; i++)
                order[i] = i;
            
            return order;
        }

		// Create a graph of k vertices
		GraphCourse graph = new GraphCourse(numCourses);

		// Create the adjacency list representation of the graph
		for (int i = 0; i < prerequisites.length; i++) {
			int next = prerequisites[i][0];
			int prev = prerequisites[i][1];

			graph.addEdge(prev, next);
		}

		// We now have our adjacency set built
		// Time to do some topological sorting
		return graph.topologicalSort();
    }

	public static void main(String[] args) {
		CourseScheduleTopological cst = new CourseScheduleTopological();
		
		int numCourses = 2;
		int[][] prerequisites;
		prerequisites = new int[][] { { 1, 0 } };

		System.out.println(Arrays.toString(cst.findOrder(numCourses,
				prerequisites)));

		numCourses = 4;

		prerequisites = new int[][] { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };

		System.out.println(Arrays.toString(cst.findOrder(numCourses,
				prerequisites)));
		
		numCourses = 2;

		prerequisites = new int[][] { { 0, 1}, { 1, 0 } };

		System.out.println(Arrays.toString(cst.findOrder(numCourses,
				prerequisites)));
	}
}