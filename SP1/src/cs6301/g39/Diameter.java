package cs6301.g39;

import cs6301.g00.Graph;
import cs6301.g39.BreadthFirstSearch;
import java.util.LinkedList;


public class Diameter {
	
	
	public static LinkedList<Graph.Vertex> diameter(Graph g) {
		
		Graph.Vertex v1 = g.getVertex(0);
		LinkedList<Graph.Vertex> bfs1 = BreadthFirstSearch.BFS(g,v1);
		return null;
	}

}
