/** Class to implement Depth First Search on a given graph
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/15
 *  Ver 1.1: 2017/09/16
 *  Usage: DFS df = new DFS(g);
 *         Iterator<Graph.Vertex> it = g.iterator();
 *  	   df.dfs(it);
 */

package cs6301.g39;

import java.util.Iterator;
import java.util.LinkedList;

import cs6301.g00.Graph;

public class DFS extends GraphAlgorithm<DFS.DFSVertex> {

	public static final int INFINITY = Integer.MAX_VALUE;

	// Class to store information about a graph vertex with respect to DFS algorithm
	static class DFSVertex {
		boolean seen;
		DFS.DFSVertex parent;
		int distance; // distance of vertex from source
		int componentNo; // Component number that a vertex belongs to
		int top;
		int fin; // finish time of a vertex

		// Constructor for DFSVertex class
		DFSVertex(Graph.Vertex u) {
			seen = false;
			parent = null;
			distance = INFINITY;
			componentNo = -1;
			top = -1;
			fin = -1;
		}
	}

	// Variables that are maintained during the DFS traversal of a tree.
	int topNum;
	int cNo;
	int finTime;
	LinkedList<Graph.Vertex> decFinList = new LinkedList<Graph.Vertex>(); // list containing vertices in decreasing
																			// order of finish time
	boolean isDAG; // a flag indicating if a graph is a DAG or not

	// Constructor for DFS class
	public DFS(Graph g) {
		super(g);
		node = new DFSVertex[g.size()];
		// Create array for storing vertex properties
		for (Graph.Vertex u : g) {
			node[u.getName()] = new DFSVertex(u);
		}

		topNum = g.size();
		cNo = 0;
		finTime = 0;
		isDAG = true;
	}

	/**
	 * A function that implements DFS algorithm. It processes vertices in order that
	 * the iterator provides.
	 * 
	 * @param it
	 *            - iterator of vertices of graph G
	 */
	void dfs(Iterator<Graph.Vertex> it) {

		while (it.hasNext()) {
			Graph.Vertex u = it.next();

			if (!seen(u)) {
				cNo++;
				DFSVisit(u);
			}
		}
	}

	/**
	 * A function that visits a vertex during DFS traversals
	 * 
	 * @param u
	 *            - a vertex u of graph G
	 */
	public void DFSVisit(Graph.Vertex u) {

		DFSVertex dvU = getVertex(u);
		dvU.seen = true;
		dvU.distance = ++finTime;
		dvU.componentNo = cNo;

		Iterator<Graph.Edge> outEdgeItr = u.iterator();

		while (outEdgeItr.hasNext()) {
			Graph.Edge e = outEdgeItr.next();
			Graph.Vertex v = e.otherEnd(u);
			DFSVertex dvV = getVertex(v);

			if (!seen(v)) {
				dvV.parent = dvU;
				DFSVisit(v);
			} 
		}

		dvU.fin = ++finTime;
		dvU.top = topNum--;
		decFinList.addFirst(u);
	}

	/**
	 * @param u
	 *            - a vertex of graph G
	 * @return - true if vertex is already seen during DFS, false otherwise
	 */
	boolean seen(Graph.Vertex u) {
		return getVertex(u).seen;
	}

	/**
	 * Function to reinitialize the variables after every call to dfs
	 */
	void reinitialize() {
		for (Graph.Vertex u : g) {
			DFSVertex bu = getVertex(u);
			bu.seen = false;
			bu.parent = null;
			bu.distance = INFINITY;
			bu.componentNo = -1;
			bu.top = -1;
			bu.fin = -1;
		}

		topNum = g.size();
		cNo = 0;
		finTime = 0;
		isDAG = true;
		decFinList.removeAll(decFinList);
	}
}
