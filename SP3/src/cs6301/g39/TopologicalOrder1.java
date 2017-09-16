/** Algorithm 1. Remove vertices with no incoming edges, one at a
*  time, along with their incident edges, and add them to a list.
*/

package cs6301.g39;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalOrder1 extends GraphAlgorithm<TopologicalOrder1.TOPVertex> {
	
	public static class TOPVertex {
		int inDegree;
		int top;

		TOPVertex(Graph.Vertex u) {
			inDegree = u.revAdj.size();
			top = -1;
			if(inDegree == 0)
				queue.add(u);
		}
	}
	
	int topNum;

	static Queue<Graph.Vertex> queue = new LinkedList<Graph.Vertex>();
	List<Graph.Vertex> topList = new ArrayList<Graph.Vertex>();
	
	public TopologicalOrder1(Graph g) {
		super(g);
		
		topNum = 0;
		node = new TOPVertex[g.size()];

		for(Graph.Vertex u: g) {
		    node[u.getName()] = new TOPVertex(u);
		    
		}
	}

	public List<Graph.Vertex> toplogicalOrder1(Graph g) {
		int topNum = 0;
		while(!queue.isEmpty()) {
			Graph.Vertex curVertex = queue.remove();
			getVertex(curVertex).top = ++topNum;
			this.topList.add(curVertex);
			
			for(Graph.Edge e : curVertex.adj) {
				Graph.Vertex v = e.otherEnd(curVertex);
				if(--getVertex(v).inDegree == 0) 
					queue.add(v);
			}
		}
		
		if(topNum != g.size()) 
			return null;
		
		return this.topList;
	}
}
