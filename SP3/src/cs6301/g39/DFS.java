package cs6301.g39;

import java.util.Iterator;
import java.util.LinkedList;

public class DFS extends GraphAlgorithm<DFS.DFSVertex> {

	public static final int INFINITY = Integer.MAX_VALUE;
	
	// Class to store information about a vertex in this algorithm
	static class DFSVertex {
		boolean seen;
		DFS.DFSVertex parent;
		int distance; // distance of vertex from source
		int componentNo; // 
		int top;
		int fin;

		DFSVertex(Graph.Vertex u) {
			seen = false;
			parent = null;
			distance = INFINITY;
			componentNo = -1;
			top = -1;
			fin = -1;
			
		}	
	}
	
	int topNum;
	int cNo;
	int finTime;
	LinkedList<Graph.Vertex> decFinList = new LinkedList<Graph.Vertex>();
	
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
	}
	
	void dfs(Iterator<Graph.Vertex> it) {
		
		while(it.hasNext()) {
			Graph.Vertex u = it.next();
			
			if(!seen(u)) {
				cNo++;
				DFSVisit(u);
			}
		}
	}
	
	void DFSVisit(Graph.Vertex u){
		
		DFSVertex dvU = getVertex(u);
		dvU.seen = true;
		dvU.distance = ++finTime;
		dvU.componentNo = cNo;
		
		Iterator<Graph.Edge> outEdgeItr = u.iterator();
		
		while(outEdgeItr.hasNext()) {
			Graph.Edge e = outEdgeItr.next();
			Graph.Vertex v = e.otherEnd(u);
			DFSVertex dvV = getVertex(v);
			
			if(!seen(v)){
				dvV.parent = dvU;
				DFSVisit(v);
			}
		}
		
		dvU.fin = ++finTime;
		dvU.top = topNum--;
		decFinList.addFirst(u);
	}
	
		boolean seen(Graph.Vertex u) {
		return getVertex(u).seen;
	    }
		
		/*
	    Graph.Vertex getParent(Graph.Vertex u) {
		return getVertex(u).parent;
	    }
	    */

	    int distance(Graph.Vertex u) {
		return getVertex(u).distance;
	    }
	    
	    void reinitialize() {
	    	for(Graph.Vertex u: g) {
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
			decFinList.removeAll(decFinList);
	    }
}
