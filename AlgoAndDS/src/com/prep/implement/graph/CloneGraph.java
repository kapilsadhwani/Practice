package com.prep.implement.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Definition for a Node.
class GraphNode {
    public int val;
    public List<GraphNode> neighbors;

    public GraphNode() {}

    public GraphNode(int _val,List<GraphNode> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};

class CloneGraph {
    public GraphNode cloneGraph(GraphNode node) {
        if (node == null) {
            return node;
        }

        // Hash map to save the visited node and it's respective clone
        // as key and value respectively. This helps to avoid cycles.
        HashMap<GraphNode, GraphNode> visited = new HashMap();

        // Put the first node in the queue
        Queue<GraphNode> queue = new LinkedList<GraphNode> ();
        queue.offer(node);
        // Clone the node and put it in the visited dictionary.
        visited.put(node, new GraphNode(node.val, new ArrayList()));

        GraphNode currentNode;
        
        // Start BFS traversal
        while (!queue.isEmpty()) {
            // Pop a node say "n" from the from the front of the queue.
        	currentNode = queue.poll();
            // Iterate through all the neighbors of the node "n"
            for (GraphNode n: currentNode.neighbors) {
                if (!visited.containsKey(n)) {
                    // Clone the neighbor and put in the visited, if not present already
                    visited.put(n, new GraphNode(n.val, new ArrayList()));
                    // Add the newly encountered node to the queue.
                    queue.offer(n);
                }
                
                // Add the clone of the neighbor to the neighbors of the clone node "n".
                visited.get(currentNode).neighbors.add(visited.get(n));
            }
        }

        // Return the clone of the node from visited.
        return visited.get(node);
    }
}