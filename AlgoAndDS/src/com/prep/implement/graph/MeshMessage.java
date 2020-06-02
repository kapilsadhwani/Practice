package com.prep.implement.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MeshMessage {
	private static String[] reconstructPath(Map<String, String> previousNodes,
			String startNode, String endNode) {

		List<String> reversedShortestPath = new ArrayList<String>();

		// start from the end of the path and work backwards
		String currentNode = endNode;

		while (currentNode != null) {
			reversedShortestPath.add(currentNode);
			currentNode = previousNodes.get(currentNode);
		}

		// reverse our path to get the right order
		// by flipping it around, in place
		Collections.reverse(reversedShortestPath);
		return reversedShortestPath.toArray(new String[reversedShortestPath
				.size()]);
	}

	public static String[] bfsGetPath(Map<String, String[]> graph,
			String startNode, String endNode) {

		if (!graph.containsKey(startNode)) {
			throw new IllegalArgumentException("Start node not in graph");
		}
		if (!graph.containsKey(endNode)) {
			throw new IllegalArgumentException("End node not in graph");
		}

		/*
		 * We're using an ArrayDeque instead of an ArrayList because we want an efficient 
		 * first-in-first-out (FIFO) structure with O(1) inserts and removes. If we use an ArrayList, 
		 * appending would be O(1), but removing elements from the front would be O(n)
		 */
		
		Queue<String> nodesToVisit = new ArrayDeque<>();
		nodesToVisit.add(startNode);

		// keep track of how we got to each node
		// we'll use this to reconstruct the shortest path at the end
		// we'll ALSO use this to keep track of which nodes we've
		// already visited
		Map<String, String> howWeReachedNodes = new HashMap<String, String>();
		howWeReachedNodes.put(startNode, null);

		while (!nodesToVisit.isEmpty()) {
			String currentNode = nodesToVisit.remove();

			// stop when we reach the end node
			if (currentNode.equals(endNode)) {
				return reconstructPath(howWeReachedNodes, startNode, endNode);
			}

			for (String neighbor : graph.get(currentNode)) {
				if (!howWeReachedNodes.containsKey(neighbor)) {
					nodesToVisit.add(neighbor);
					howWeReachedNodes.put(neighbor, currentNode);
				}
			}
		}

		// if we get here, then we never found the end node
		// so there's NO path from startNode to endNode
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
