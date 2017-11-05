/**
 * 
 */
package cs6301.g39;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cs6301.g00.Graph;
import cs6301.g00.Graph.Edge;
import cs6301.g00.Graph.Vertex;
import cs6301.g39.DFS.DFSVertex;
import cs6301.g39.DMSTGraph.DMSTEdge;
import cs6301.g39.DMSTGraph.DMSTVertex;

public class DirectedMinimumSpanningTree {

	DMSTGraph dg;
	int minWeight;

	public DirectedMinimumSpanningTree(Graph g) {
		dg = new DMSTGraph(g);
		dg = new DMSTGraph(dg, dg.dv);
		minWeight = 0;
	}

	int getMinWeightSpanningTree(Vertex start, List<Edge> dmst) {

		DMSTVertex dstart = dg.getVertex(start);

		while (true) {
			// reduce weights
			List<DMSTEdge> disabledEdges = new LinkedList<DMSTEdge>();
			int weightReduced = reduceWeights(start, disabledEdges);

			// dfs
			DFS dfs = new DFS(dg);
			dfs.DFSVisit(dstart);

			minWeight += weightReduced;
			// if the root can reach all nodes using zero weight edges than this is MST
			if (dfs.decFinList.size() == DMSTGraph.enabledVertices) {
				getMSTEdges(dfs.decFinList, dmst);
				break;
			}

			StronglyConnectedComponents scc = new StronglyConnectedComponents(dg);
			scc.stronglyConnectedComponents();

			enableEdges(disabledEdges);
			shrinkGraph(scc);

			// ttest code/
//			for (Vertex u : dg) {
//				for (Edge e : u)
//					System.out.println("from " + e.fromVertex() + " to " + e.toVertex());
//			}
		}
		return minWeight;
	}

	private void enableEdges(List<DMSTEdge> disabledEdges) {
		for (DMSTEdge de : disabledEdges)
			de.enable();
	}

	private void shrinkGraph(StronglyConnectedComponents scc) {
		int numSCC = scc.numComponents;

		// create hashset for each component
		ArrayList<HashSet<Vertex>> vertexComponents = new ArrayList<>(numSCC);
		for (int i = 0; i < numSCC; i++)
			vertexComponents.add(new HashSet<Vertex>());

		// assign vertex to respective hashset
		for (int i = 0; i < dg.size(); i++) {
			DFSVertex u = scc.dfsTrav.node[i];
			if (u != null) {
				Vertex du = dg.getVertex(i + 1);
				((DMSTVertex) du).componentNo = u.componentNo;
				vertexComponents.get(u.componentNo - 1).add(du);
			}
		}
		
		ArrayList<DMSTVertex> newVertices = new ArrayList<>();
		int currComponentNo = 0;

		// shrink component if size > 1
		for (HashSet<Vertex> hs : vertexComponents) {
			currComponentNo++;
			if (hs.size() > 1) {

				// create new vertex for the component vertices
				Vertex cVertex = new Vertex(dg.numVertices);
				DMSTVertex dmstCVertex = new DMSTVertex(cVertex);
				dmstCVertex.componentVertices.addAll(hs);

				// Get min weight edge from/to each component from/to this component
				Edge[] minFromEdges = new Edge[numSCC];
				Edge[] minToEdges = new Edge[numSCC];

				getMinFromToEdges(minFromEdges, minToEdges, hs, scc);

				createNewEdges(minFromEdges, minToEdges, dmstCVertex, currComponentNo);

				for (Vertex u : hs)
					((DMSTVertex) u).disable();
				dmstCVertex.disable();
				dg.addVertex(dmstCVertex);
				newVertices.add(dmstCVertex);
			}
		}
		for(DMSTVertex u: newVertices) 
			u.enable();
	}

	private void createNewEdges(Edge[] minFromEdges, Edge[] minToEdges, DMSTVertex dmstCVertex, int currComponentNo) {
		for (Edge e : minFromEdges) {
			if (e != null) {
				DMSTVertex dv = (DMSTVertex) getOtherComponentVertex(e, currComponentNo);
				DMSTEdge de = dg.addEdge(new DMSTEdge(dv, dmstCVertex, e.getWeight(), dg.edgeSize() + 1));
				de.src = (DMSTEdge) e;
				((DMSTEdge) e).disable();
			}
		}

		for (Edge e : minToEdges) {
			if (e != null) {
				DMSTVertex dv = (DMSTVertex) getOtherComponentVertex(e, currComponentNo);
				DMSTEdge de = dg.addEdge(new DMSTEdge(dmstCVertex, dv, e.getWeight(), dg.edgeSize() + 1));
				de.src = (DMSTEdge) e;
				((DMSTEdge) e).disable();
			}
		}
	}

	private void getMinFromToEdges(Edge[] minFromEdges, Edge[] minToEdges, HashSet<Vertex> hs,
			StronglyConnectedComponents scc) {
		for (Vertex u : hs) {
			DFSVertex dfsu = scc.dfsTrav.getVertex(u);
			for (Edge e : u) {
				Vertex v = e.otherEnd(u);
				DFSVertex dfsv = scc.dfsTrav.getVertex(v);
				if (dfsu.componentNo != dfsv.componentNo)
					minToEdges[dfsv.componentNo - 1] = getMinWeightEdge(minToEdges[dfsv.componentNo - 1], e);
				else
					((DMSTEdge) e).disable();

			}

			Iterator<Edge> revIt = u.reverseIterator();
			while (revIt.hasNext()) {
				Edge e = revIt.next();
				Vertex v = e.otherEnd(u);
				DFSVertex dfsv = scc.dfsTrav.getVertex(v);
				if (dfsu.componentNo != dfsv.componentNo)
					minFromEdges[dfsv.componentNo - 1] = getMinWeightEdge(minFromEdges[dfsv.componentNo - 1], e);
				else
					((DMSTEdge) e).disable();
			}
		}
	}

	private Vertex getOtherComponentVertex(Edge e, int currComponentNo) {
		DMSTVertex from = (DMSTVertex) e.fromVertex();
		if (from.componentNo == currComponentNo)
			return e.toVertex();
		else
			return e.fromVertex();
	}

	private Edge getMinWeightEdge(Edge s, Edge t) {
		if (s == null)
			return t;
		if (s.getWeight() > t.getWeight()) {
			((DMSTEdge) s).disable();
			return t;
		} else {
			((DMSTEdge) t).disable();
			return s;
		}
	}

	private void getMSTEdges(LinkedList<Vertex> decFinList, List<Edge> dmst) {
		// TODO

	}

	int reduceWeights(Vertex start, List<DMSTEdge> disabledEdges) {

		int weightReduced = 0;
		for (Vertex u : dg) {
			DMSTVertex du = (DMSTVertex) u;
			int minWeight = Integer.MAX_VALUE;
			if (u == null)
				break;
			if (u.equals(start))
				continue;
			Iterator<Edge> revIt = du.reverseIterator();
			while (revIt.hasNext()) {
				Edge e = revIt.next();
				if (e.getWeight() < minWeight)
					minWeight = e.getWeight();
			}

			revIt = du.reverseIterator();
			while (revIt.hasNext()) {
				Edge e = revIt.next();
				e.setWeight(e.getWeight() - minWeight);
			}

			revIt = du.reverseIterator();
			while (revIt.hasNext()) {
				Edge e = revIt.next();
				if (e.getWeight() > 0) {
					((DMSTEdge) e).disable();
					disabledEdges.add((DMSTEdge) e);
				}
			}
			weightReduced += minWeight;
		}
		return weightReduced;
	}

}
