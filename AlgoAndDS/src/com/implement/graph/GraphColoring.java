package com.implement.graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GraphColoring {
	static class GraphNode {

	    private String label;
	    private Set<GraphNode> neighbors;
	    private Optional<String> color;

	    public GraphNode(String label) {
	        this.label = label;
	        neighbors = new HashSet<GraphNode>();
	        color = Optional.empty();
	    }

	    public String getLabel() {
	        return label;
	    }

	    public Set<GraphNode> getNeighbors() {
	        return Collections.unmodifiableSet(neighbors);
	    }

	    public void addNeighbor(GraphNode neighbor) {
	        neighbors.add(neighbor);
	    }

	    public boolean hasColor() {
	        return color.isPresent();
	    }

	    public String getColor() {
	        return color.get();
	    }

	    public void setColor(String color) {
	        this.color = Optional.ofNullable(color);
	    }
	}

	public static void colorGraph(GraphNode[] graph, String[] colors) {

		for (GraphNode node : graph) {
			Set<GraphNode> neighbors = node.getNeighbors();

			if (neighbors.contains(node)) {
				throw new IllegalArgumentException(String.format(
						"Legal coloring impossible for node with loop: %s",
						node.getLabel()));
			}

			// get the node's neighbors' colors, as a set so we
			// can check if a color is illegal in constant time
			Set<String> illegalColors = new HashSet<>();
			for (GraphNode neighbor : neighbors) {
				if (neighbor.hasColor()) {
					illegalColors.add(neighbor.getColor());
				}
			}

			// assign the first legal color
			for (String color : colors) {
				if (!illegalColors.contains(color)) {
					node.setColor(color);
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphNode a = new GraphNode("a");
		GraphNode b = new GraphNode("b");
		GraphNode c = new GraphNode("c");

		a.addNeighbor(b);
		b.addNeighbor(a);
		b.addNeighbor(c);
		c.addNeighbor(b);

		GraphNode[] graph = new GraphNode[] { a, b, c };
		String[] colors = {"Blue", "Green", "Yellow", "Orange", "Red"};
		
		colorGraph(graph, colors);
	}


}
