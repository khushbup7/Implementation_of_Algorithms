/**
 * 
 */
package cs6301.g39;

import java.util.List;

import cs6301.g00.Graph;
import cs6301.g00.Graph.Edge;
import cs6301.g00.Graph.Vertex;
import cs6301.g39.DMSTGraph.DMSTEdge;

/**
 *
 */
public class DirectedMinimumSpanningTree {

	static int minimumSpanningTree(Graph g, Vertex start, List<Edge> dmst) {
		DMSTGraph xg = new DMSTGraph(g);
		
		// use this somewhere
		g = new Graph(g, xg.dv);

		// //Step-1 : delta-u
		//
		int minWeight = Integer.MAX_VALUE;
		for (Graph.Vertex u : g) {
			if(u == null)
				break;
			if (u.equals(start))
				continue;
			for (Graph.Edge e : u.revAdj) {
				if (e.getWeight() < minWeight)
					minWeight = e.getWeight();
			}
			for (Graph.Edge e : u.revAdj) {
				e.setWeight(e.getWeight() - minWeight);
				if(e.getWeight() > 0)
					((DMSTEdge) e).disabled = true;
			}
			minWeight = Integer.MAX_VALUE;
		}

		// //Step-2 : SCC and DFSVisit

		BFSHash bfs = new BFSHash(g);
		bfs.bfs(g.getVertex(start.getName()+1));
		for(BFSHash.BFSVertex v : bfs.vertexMap.values()) {
			if(!v.seen)
				break;
		} 
			
		int numSCC = StronglyConnectedComponents.stronglyConnectedComponents(g);
		// if(numSCC < 2) {
		// for(XGraph.XVertex xu : xg.xv) {
		// for(XGraph.XEdge xe : xu.xrevadj) {
		// if(xe.weight != 0)
		// xe.disabled = true;
		// }
		// minWeight = Integer.MAX_VALUE;
		// }
		// //TODO DFSVisit(start)
		// //means we can find MST just by doing DFS
		// }
		// else { //more than two SCC
		//
		// //Shrinking Step
		// }

		return 0;
	}

}
