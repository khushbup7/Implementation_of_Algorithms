package cs6301.g39;

import java.util.LinkedList;
/** 
 * TestGraph class to test diameter and BreadthFirstSearch
 * 
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.2: 2017/09/02
 */
import cs6301.g00.Graph;

public class TestGraph {
	
	public static void main(String[] args) {
		int n = 10;
		Graph g = new Graph(n);
		g.addEdge(g.getVertex(1), g.getVertex(2), 1);
		g.addEdge(g.getVertex(1), g.getVertex(3), 1);
		g.addEdge(g.getVertex(2), g.getVertex(4), 1);
		g.addEdge(g.getVertex(2), g.getVertex(5), 1);
		g.addEdge(g.getVertex(3), g.getVertex(6), 1);
		g.addEdge(g.getVertex(3), g.getVertex(7), 1);
		g.addEdge(g.getVertex(4), g.getVertex(8), 1);
		g.addEdge(g.getVertex(4), g.getVertex(9), 1);
		g.addEdge(g.getVertex(8), g.getVertex(10), 1);
		
		LinkedList<Graph.Vertex> l1 = Diameter.diameter(g);
		
		System.out.println("The diameter of a given graph is:" + l1.size());
		System.out.println("The longest path along the diameter is:");
		for(int i = 0; i<l1.size()-1;i++) {
			System.out.print(l1.get(i).toString() + " -> ");
		}
		System.out.println(l1.get(l1.size()-1).toString());
	}
		
		
		
	
	
}
