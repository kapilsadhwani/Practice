package com.implement.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.implement.graph.Graph.Vertex;

class CourseScheduleI {
	private boolean detectCycle(List<Integer>[] graph, int currentVertex, Set<Integer> visited, Set<Integer> path) {
		if (graph[currentVertex] == null) {
            return false;
        }
		
		if (path.contains(currentVertex)) // Already visited within same path
			return true;

		if (visited.contains(currentVertex))	// Was visited before and no cycle found
			return false;

		visited.add(currentVertex);
		path.add(currentVertex);

		List<Integer> neighbors = graph[currentVertex];
		for (Integer n : neighbors) {
			if (detectCycle(graph, n, visited, path)) {
				return true;
			}
		}

		path.remove(currentVertex);
		return false;
	}

	// Detect cycle in a graph
	public boolean hasCycle(int numCourses, List<Integer>[] graph) {
		// Mark all the vertices as not visited using Set or HashTable 
		Set<Integer> visited = new HashSet<Integer>();		// or boolean[] visited = new boolean[numCourses];
		Set<Integer> path = new HashSet<Integer>();

		// Call the helper function to detect cycle for all vertices one by one
		for (int i = 0; i < numCourses; ++i){
			if (detectCycle(graph, i, visited, path))
				return true;
		}

		return false;
	}
	
	// Using Cycle-detection Algo in Graphs
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		// Represent the graph using adjacency-lists;
		List<Integer>[] graph = new ArrayList[numCourses];

		for (int i = 0; i < prerequisites.length; i++) {
			int v1 = prerequisites[i][0];
			int v2 = prerequisites[i][1];
			
			// Here index [0] depends on [1], Hence we draw [1] --> [0]
			if (graph[v2] == null) {
				graph[v2] = new ArrayList<Integer>();
			}
			graph[v2].add(v1);
		}
		
		return hasCycle(numCourses, graph) == false;
	}
	
	// Using inDegree and outDegree
	static class GNode {
		public Integer inDegrees = 0;
		public List<Integer> outNodes = new LinkedList<Integer>();
	}

	public boolean canFinishIODegree(int numCourses, int[][] prerequisites) {

		if (prerequisites.length == 0)
			return true; // no cycle could be formed in empty graph.

		// course -> list of next courses
		HashMap<Integer, GNode> graph = new HashMap<>();

		// build the graph first
		for (int[] relation : prerequisites) {
			// relation[1] -> relation[0]
			GNode prevCourse = this.getCreateGNode(graph, relation[1]);
			GNode nextCourse = this.getCreateGNode(graph, relation[0]);

			prevCourse.outNodes.add(relation[0]);
			nextCourse.inDegrees += 1;
		}

		// We start from courses that have no prerequisites.
		int totalDeps = prerequisites.length;
		LinkedList<Integer> nodepCourses = new LinkedList<Integer>();
		for (Map.Entry<Integer, GNode> entry : graph.entrySet()) {
			GNode node = entry.getValue();
			if (node.inDegrees == 0)
				nodepCourses.add(entry.getKey());
		}

		int removedEdges = 0;
		while (nodepCourses.size() > 0) {
			Integer course = nodepCourses.pop();

			/*
			 * Consider current course as previous course and completed.
			 * Look for next courses and delete edges (inDegree to nextCourse) one by one
			 */
			List<Integer> neighbors = graph.get(course).outNodes;
			for (Integer nextCourse : neighbors) {
				GNode childNode = graph.get(nextCourse);
				childNode.inDegrees -= 1;
				removedEdges += 1;
				if (childNode.inDegrees == 0)
					nodepCourses.add(nextCourse);
			}
		}

		if (removedEdges != totalDeps)
			// if there are still some edges left, then there exist some cycles
			// Due to the dead-lock (dependencies), we cannot remove the cyclic
			// edges
			return false;
		else
			return true;
	}

	/**
	 * Retrieve the existing <key, value> from graph, otherwise create a new
	 * one.
	 */
	protected GNode getCreateGNode(HashMap<Integer, GNode> graph, Integer course) {
		GNode node = null;
		if (graph.containsKey(course)) {
			node = graph.get(course);
		} else {
			node = new GNode();
			graph.put(course, node);
		}
		return node;
	}
}