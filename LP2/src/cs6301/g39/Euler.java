package cs6301.g39;

import java.util.List;
import cs6301.g39.Graph.Edge;
import java.util.Iterator;
import java.util.LinkedList;

public class Euler extends GraphAlgorithm<Euler.EulerVertex> {
	int VERBOSE;
	List<Graph.Edge> tour;
	Graph.Vertex start;

	static class EulerVertex {
		List<Graph.Edge> unprocessed_edges;
		List<Graph.Edge> subTour;

		EulerVertex(Graph.Vertex u) {
			unprocessed_edges = new LinkedList<>(u.adj);
			subTour = new LinkedList<>();
		}

		public Iterator<Edge> iterator() { return unprocessed_edges.iterator(); }
		
		public Iterator<Edge> subtour_iterator() { return subTour.iterator(); }

		
	}

	// Constructor
	Euler(Graph g, Graph.Vertex start) {
		super(g);
		VERBOSE = 1;
		this.start = start;
		tour = new LinkedList<>();
		node = new EulerVertex[g.size()];
		// Create array for storing vertex properties
		for (Graph.Vertex u : g) {
			node[u.getName()] = new EulerVertex(u);
		}

	}

	public List<Graph.Edge> findEulerTour() {
		findTours();
		if(VERBOSE > 9) { printTours(); }
		stitchTours();
		return tour;
	}

	boolean isEulerian() {
		int scc = StronglyConnectedComponents.stronglyConnectedComponents(g);
		System.out.println(scc);
		if(scc != 1){
			System.out.println("Graph is not Eulerian");
			System.out.println("Reason: Graph is not strongly connected");
			return false;	
		}
		// checks in-degree is equal to out-degree
		for(Graph.Vertex u : g){
			if(u.adj.size() != u.revAdj.size()){
				System.out.println("Graph is not Eulerian");
				System.out.println("Reason: In-degree is not equal to out-degree");
				return false;
			}
		}

		return true;
	}

	// Find tours starting at vertices with unexplored edges
	void findTours() {
		findTours(start, getVertex(start).subTour);
		for(Graph.Vertex u : g){
			if(getVertex(u).unprocessed_edges.size() > 0)
				findTours(u, getVertex(u).subTour);
		}
		printTours();
	}

	void findTours(Graph.Vertex start, List<Graph.Edge> subTour){
		Graph.Vertex u = start;
		Graph.Vertex v = start;
		Graph.Edge e;
		boolean flag = true;
		do{
			flag = false;
			Iterator<Graph.Edge> it = getVertex(u).iterator();
			while (it.hasNext()) {
				flag = true;
				e = it.next();
				subTour.add(e);
				getVertex(u).unprocessed_edges.remove(0);
				v = e.otherEnd(u);
				break;

			}
			u = v;
		}while(flag);
	}

	void printTours() {
		//print tours starting from each vertex
		for(Graph.Vertex u : g){
			printTours(u);
		}
	}

	void printTours(Graph.Vertex start) {
		EulerVertex eu = getVertex(start);
		System.out.print(start.toString() + " :");
			for(Graph.Edge e : eu.subTour){
				System.out.print(e);
			} 
		System.out.println("");
	}


	// Stitch tours into a single tour using the algorithm discussed in class
	void stitchTours() {	
			explore(start);
	}

	void explore(Graph.Vertex u){
		Graph.Vertex temp = u;
		if(getVertex(temp).subTour.size() == 0)
			return;
		Iterator<Graph.Edge> it = getVertex(temp).subtour_iterator();
		while(it.hasNext()){
			Graph.Edge e = it.next();
			tour.add(e);
			it.remove();
			Graph.Vertex v = e.otherEnd(temp);
			explore(v);
			v=temp;
		}		
	}
	void setVerbose(int v) {
		VERBOSE = v;
	}

}
