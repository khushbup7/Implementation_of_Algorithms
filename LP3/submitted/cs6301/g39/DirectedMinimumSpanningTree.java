/** Class to find weight of a directed minimum spanning treee
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/11/5 shrink the graph
 *  Ver 2.0: 2017/11/12 expand the graph
 */
package cs6301.g39;

import java.util.ArrayList;
import java.util.Arrays;
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
	Vertex start;
	DMSTVertex dstart;
	int origNumOfVertices;

	public DirectedMinimumSpanningTree(Graph g) {
		dg = new DMSTGraph(g);
		origNumOfVertices = dg.numVertices;
		dg = new DMSTGraph(dg, dg.dv);
		minWeight = 0;
	}

	int getMinWeightSpanningTree(Vertex start, List<Edge> dmst) {

		this.start = start;
		DFS dfs = new DFS(dg);
		dfs.DFSVisit(start);

		if (dfs.decFinList.size() != dg.size())
			return -1;

		dstart = dg.getVertex(start);
		List<DMSTEdge> disabledEdges;

		while (true) {
			// reduce weights
			disabledEdges = new LinkedList<DMSTEdge>();

			int weightReduced = reduceWeights(start, disabledEdges);

			// dfs
			dfs = new DFS(dg);
			dfs.DFSVisit(dstart);

			minWeight += weightReduced;
			// if the root can reach all nodes using zero weight edges than this is MST
			if (dfs.decFinList.size() == DMSTGraph.enabledVertices) {
				break;
			}

			StronglyConnectedComponents scc = new StronglyConnectedComponents(dg);
			scc.stronglyConnectedComponents();

			enableEdges(disabledEdges);
			shrinkGraph(scc);
		}

		expandGraph(dmst);

		return minWeight;
	}

	//  reduce weights of the incoming edges of each vertex
	private int reduceWeights(Vertex start, List<DMSTEdge> disabledEdges) {

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
				if (e.getWeight() < minWeight) {
					minWeight = e.getWeight();
				}
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
			if (minWeight != Integer.MAX_VALUE) {
				weightReduced += minWeight;
			}
		}
		return weightReduced;
	}

	// shrink graph to create new vertices for each of the strongly connected components
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

				getMinFromToEdges(minFromEdges, minToEdges, hs, scc, currComponentNo);

				createNewEdges(minFromEdges, minToEdges, dmstCVertex, currComponentNo);

				for (Vertex u : hs) {
					((DMSTVertex) u).disable();
					if (u.equals(dstart))
						dstart = dmstCVertex;
				}
				dmstCVertex.componentNo = currComponentNo;
				dg.addVertex(dmstCVertex);
			}
		}
	}

	// get minimum edge from/to each componenet from current component
	private void getMinFromToEdges(Edge[] minFromEdges, Edge[] minToEdges, HashSet<Vertex> hs,
			StronglyConnectedComponents scc, int currComponentNo) {
		for (Vertex u : hs) {
			DMSTVertex dmstu = (DMSTVertex) u;
			for (Edge e : u) {
				Vertex v = e.otherEnd(u);
				DMSTVertex dmstv = (DMSTVertex) v;
				if (dmstu.componentNo != dmstv.componentNo)
					minToEdges[dmstv.componentNo - 1] = getMinWeightEdge(minToEdges[dmstv.componentNo - 1], e);

			}

			Iterator<Edge> revIt = u.reverseIterator();
			while (revIt.hasNext()) {
				Edge e = revIt.next();
				Vertex v = e.otherEnd(u);
				DMSTVertex dmstv = (DMSTVertex) v;
				if (dmstu.componentNo != dmstv.componentNo)
					minFromEdges[dmstv.componentNo - 1] = getMinWeightEdge(minFromEdges[dmstv.componentNo - 1], e);
			}
		}
	}

	// create new edges for the newly created vertex
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

	// get the vertex of other component for the current edge
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
		if (s.getWeight() >= t.getWeight()) {
			((DMSTEdge) s).disable();
			return t;
		} else {
			((DMSTEdge) t).disable();
			return s;
		}
	}

	// expand the shrunken graph to get edges for the MST
	private void expandGraph(List<Edge> dmst) {
		Edge[] arr = new Edge[origNumOfVertices];
		for (int i = dg.numVertices; i > 0; i--) {
			DMSTVertex u = (DMSTVertex) dg.getVertex(i);
			if (isShrunkenVertex(u)) {
				// enable component vertices
				for (Vertex componentVertex : u.componentVertices) {
					DMSTVertex dcomponentVertex = (DMSTVertex) componentVertex;
					dcomponentVertex.enable();
				}

				// dfs to find MST of subgraph
				DFS dfs = new DFS(dg);
				dfs.DFSVisit(u.componentVertices.get(0));
				getMSTEdges(dfs.decFinList, arr);
				for (Edge e : u) {
					// for all edges going out of this shrunken vertex
					DMSTEdge src = ((DMSTEdge) e).src;
					if (e.getWeight() == 0) {
						src.enable();
						// src.setWeight(e.getWeight());
					}
				}
				Iterator<Edge> it = u.reverseIterator();
				while (it.hasNext()) {
					Edge e = it.next();
					DMSTEdge src = ((DMSTEdge) e).src;
					if (e.getWeight() == 0) {
						src.enable();
						src.setWeight(e.getWeight());
					}
				}
				u.disable();
			} else {
				if (arr[u.getName()] == null) {
					Iterator<Edge> it = u.reverseIterator();
					int minWeight = Integer.MAX_VALUE;
					DMSTEdge minEdge = null;
					while (it.hasNext()) {
						DMSTEdge e = (DMSTEdge) it.next();
						if (e.getWeight() == 0 && e.origWeight < minWeight) {
							minWeight = e.origWeight;
							minEdge = e;
						}
					}
					arr[u.getName()] = minEdge;
				}
			}
		}
		arr[start.getName()] = null;
		dmst.addAll(Arrays.asList(arr));
	}

	// get edges of the MST for the subgraph
	private void getMSTEdges(LinkedList<Vertex> decFinList, Edge[] arr) {
		Iterator<Vertex> it = decFinList.iterator();
		Vertex prev = it.next();
		while (it.hasNext()) {
			Vertex u = it.next();
			for (Edge e : prev.adj) {
				if (e.otherEnd(prev).equals(u) && !isShrunkenVertex((DMSTVertex) prev)
						&& !isShrunkenVertex((DMSTVertex) u)) {
					arr[u.getName()] = e;
					break;
				}
			}
			prev = u;
		}
	}

	private boolean isShrunkenVertex(DMSTVertex u) {
		if (u.componentVertices.size() > 0)
			return true;
		return false;
	}

	private void enableEdges(List<DMSTEdge> disabledEdges) {
		for (DMSTEdge de : disabledEdges)
			de.enable();
	}
}
