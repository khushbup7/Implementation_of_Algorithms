/** Algorithm 1. Remove vertices with no incoming edges, one at a
*  time, along with their incident edges, and add them to a list.
*/

package cs6301.g39;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cs6301.g00.GraphAlgorithm;

public class TopologicalOrder1 extends GraphAlgorithm<TopologicalOrder1.TopVertex> {
	
	public TopologicalOrder1(Graph g) {
		super(g);
		
		// TODO Auto-generated constructor stub
	}



	static class TopVertex {
		int inDegree;
		TopVertex(Graph.Vertex u) {
			
		}
	}
	
	
	
	   public static List<Graph.Vertex> toplogicalOrder1(Graph g) {
		   
		   List<Graph.Vertex> result = new ArrayList<Graph.Vertex>();
		   Queue<Graph.Vertex> queue = new LinkedList<Graph.Vertex>();
		   
		   //Iterator<Graph>
		   return null;
	   }
}
