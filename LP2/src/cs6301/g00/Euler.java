
// change following line to your group number
package cs6301.g00;

import java.util.List;

import cs6301.g00.Graph.Edge;
//import cs6301.g39.Graph;

import java.util.Iterator;
import java.util.LinkedList;

public class Euler extends GraphAlgorithm<Euler.EulerVertex> {
	int VERBOSE;
	List<Graph.Edge> tour;
	Graph.Vertex start; //new

	//new
	static class EulerVertex {
		List<Graph.Edge> unprocessed_edges;
		List<List<Graph.Edge>> subTour; // sub tour from this edge

		EulerVertex(Graph.Vertex u) {
			unprocessed_edges = new LinkedList<>(u.adj);// copy from adj
			subTour = new LinkedList<>();
		}

		public Iterator<Edge> iterator() { return unprocessed_edges.iterator(); }

	}

	// Constructor
	Euler(Graph g, Graph.Vertex start) {
		super(g); //new
		VERBOSE = 1;
		this.start = start; //new
		tour = new LinkedList<>();
		/*new*/
		node = new EulerVertex[g.size()];
		// Create array for storing vertex properties
		for (Graph.Vertex u : g) {
			node[u.getName()] = new EulerVertex(u);
		}
		/* end new */

	}

	public List<Graph.Edge> findEulerTour() {
		findTours();
		if(VERBOSE > 9) { printTours(); }
		stitchTours();
		return tour;
	}

	boolean isEulerian() {
		//new - add the sCC class and check if number of components  == 1
		int scc = 1;
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

		for(Graph.Vertex u : g){
			if(getVertex(u).unprocessed_edges.size() > 0)
				findTours(u);
		}

		printTours();
	}

	void findTours( Graph.Vertex start){
		Graph.Vertex u = start;
		Graph.Vertex v = start;
		List<Graph.Edge> subtourlist = new LinkedList<>();
		Graph.Edge e;
		boolean flag = true;
		do{
			flag = false;
			Iterator<Graph.Edge> it = getVertex(u).iterator();
			while (it.hasNext()) {
				flag = true;
				e = it.next();
				subtourlist.add(e);
				getVertex(u).unprocessed_edges.remove(0);
				v = e.otherEnd(u);
				break;

			}
			u = v;
		}while(flag);
		getVertex(start).subTour.add(subtourlist);
	}

	void printTours() {
		for(Graph.Vertex u : g){
			//print tours starting from each vertex
			printTours(u);
		}
	}

	void printTours(Graph.Vertex start) {
		EulerVertex eu = getVertex(start);
		System.out.print(start.toString() + " :");
		for(List<Graph.Edge> l : eu.subTour){
			for(Graph.Edge e : l){
				System.out.print(e);
			}
		}
		System.out.println("");
	}


	// Stitch tours into a single tour using the algorithm discussed in class
	void stitchTours() {
	}

	void setVerbose(int v) {
		VERBOSE = v;
	}

}
