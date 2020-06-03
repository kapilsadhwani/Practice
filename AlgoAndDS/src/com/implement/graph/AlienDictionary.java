package com.implement.graph;
// A Java program to order of  
// characters in an alien language 
import java.util.*; 
  
// Class to represent a graph 
class GraphAlien { 
  
    /* 
     * An array (of LinkedLists) representing the graph as an adjacency list; List of vertices
     * Each Vertex will have neighbors in its LinkedList
     * adjacencyList[0] ----> 'a' alphabet in Alien dictionary. The index is calculated by currentAlpha - 'a'
     * adjacencyList[1] ----> 'b' alphabet in Alien dictionary. The index is calculated by 'b' - 'a'
	 */
    private final LinkedList<Integer>[] adjacencyList; 
  
    GraphAlien (int nVertices) { 
        adjacencyList = new LinkedList[nVertices]; 
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) { 
            adjacencyList[vertexIndex] = new LinkedList<Integer>(); 
        } 
    } 
  
    // function to add an edge to graph 
    void addEdge(int startVertex, int endVertex) { 
        adjacencyList[startVertex].add(endVertex); 
    } 
  
    private int getNoOfVertices() { 
        return adjacencyList.length; 
    } 
  
    // A recursive function used by topologicalSort (DFS in postorder)
    private void topologicalSortUtil(int currentVertex, boolean[] visited, 
                                     Stack<Integer> stack) { 
        // Mark the current node as visited. 
        visited[currentVertex] = true; 
  
        // Recur for all the vertices adjacent to this vertex 
        for (int adjacentVertex : adjacencyList[currentVertex]) { 
            if (!visited[adjacentVertex]) { 
                topologicalSortUtil(adjacentVertex, visited, stack); 
            } 
        } 
  
        // Push current vertex to stack which stores result 
        stack.push(currentVertex); 
    } 
  
    // prints a Topological Sort of the complete graph 
    void topologicalSort() { 
        Stack<Integer> stack = new Stack<>(); 
  
        // Mark all the vertices as not visited 
        boolean[] visited = new boolean[getNoOfVertices()];
  
        // Call the recursive helper function to store Topological  
        // Sort starting from all vertices one by one 
        for (int i = 0; i < getNoOfVertices(); i++) { 
            if (!visited[i]) { 
                topologicalSortUtil(i, visited, stack); 
            } 
        } 
  
        // Print contents of stack 
        while (!stack.isEmpty()) { 
            System.out.print((char)('a' + stack.pop()) + " "); 
        } 
    } 
} 
  
public class AlienDictionary { 
    /*
     * This function finds and prints order of characters from a sorted array of words. 
     * alpha is number of possible alphabets starting from 'a'. For simplicity, this 
     * function is written in a way that only first 'alpha' characters can be there  
     * in words array. For example if alpha is 7, then words[] should contain words 
     * having only 'a', 'b','c' 'd', 'e', 'f', 'g'  
     * 
     * Basically, k represents number of Vertices in the Graph
     */
    private static void printOrder(String[] words, int k) { 
        // Create a graph with 'k' vertices 
        GraphAlien graph = new GraphAlien(k); 
  
        for (int i = 1; i < words.length; i++) { 
            // Take the current two words and find the first mismatching character 
            String word1 = words[i - 1]; 
            String word2 = words[i]; 
            int minLength = Math.min(word1.length(), word2.length());
            for (int j = 0; j < minLength; j++) { 
                // If we find a mismatching character, then add an edge 
                // from character of word1 to that of word2 
                if (word1.charAt(j) != word2.charAt(j)) { 
                    graph.addEdge(word1.charAt(j) - 'a', word2.charAt(j) - 'a'); 
                    
                    //We break now because subsequent characters do not tell us anything meaningful
                    break; 
                } 
            } 
        } 
  
        // We now have our adjacency set built
        // Time to do some topological sorting
        graph.topologicalSort(); 
    } 
  
    // Driver program to test above functions 
    public static void main(String[] args) {
        /*String[] words = {"ecd", "ecb",  "ac", "add",  "cbdd"}; 
        printOrder(words, 5);*/
    	
    	String[] words = {"baa", "abcd",  "abca", "cab",  "cad"}; 
        printOrder(words, 4);
    } 
} 