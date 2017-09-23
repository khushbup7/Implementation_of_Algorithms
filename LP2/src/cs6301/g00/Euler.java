
// change following line to your group number
package cs6301.g00;

import java.util.List;
import java.util.LinkedList;

public class Euler extends GraphAlgorithm<Euler.EulerVertex> {
	int VERBOSE;
	List<Graph.Edge> tour;
	Graph.Vertex start; //new

	//new
	static class EulerVertex {
		List<Graph.Edge> unprocessed_edges;
		List<List<Graph.Edge>> subTour; // sub tour from this edge

		//maintains the visited edges- to be compared with graph vertex adj list for which next edge should be taken
		EulerVertex(Graph.Vertex u) {
			unprocessed_edges = new LinkedList<>();
			subTour = new LinkedList<>();// pass this as parameter in find tour helper to maintain subtour from that vertex
		}
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

	// To do: function to find an Euler tour
	public List<Graph.Edge> findEulerTour() {
		findTours();
		if(VERBOSE > 9) { printTours(); }
		stitchTours();
		return tour;
	}

	/* To do: test if the graph is Eulerian.
	 * If the graph is not Eulerian, it prints the message:
	 * "Graph is not Eulerian" and one reason why, such as
	 * "inDegree = 5, outDegree = 3 at Vertex 37" or
	 * "Graph is not strongly connected"
	 */
	boolean isEulerian() {
		//new - add the sCC class and check if number of components  == 1
		int scc = 1;
		if(scc != 1){
			System.out.println("Graph is not Eulerian");
			System.out.println("Reason: Graph is not strongly connected");
			return false;	
		}
		// checks indegree is equal to outdegree
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
		// new - will call find tour helper or overloaded method
		//Iterate vertices of Graph for(Grpah.vertex u: g)
		//new- in while loop can find the unexplored edges by comparing the size of graph.Vertex and corresponding EulerVertex visited list
	}
	
	void findTours( Graph.Vertex start, List<Graph.Edge> tour){
		// check with trial if GraphVertex or EulerVertex needs to be passed
		
		//new- in while loop can find the unexplored edges of u by comparing the size of graph.Vertex and corresponding EulerVertex visited list
		// create graph edge object = assign the current edge
		// add the new edge object to Euler vertex of start- subtour list
	}

	/* Print tours found by findTours() using following format:
	 * Start vertex of tour: list of edges with no separators
	 * Example: lp2-in1.txt, with start vertex 3, following tours may be found.
	 * 3: (3,1)(1,2)(2,3)(3,4)(4,5)(5,6)(6,3)
	 * 4: (4,7)(7,8)(8,4)
	 * 5: (5,7)(7,9)(9,5)
	 *
	 * Just use System.out.print(u) and System.out.print(e)
	 */
	void printTours() {
	}

	// Stitch tours into a single tour using the algorithm discussed in class
	void stitchTours() {
	}

	void setVerbose(int v) {
		VERBOSE = v;
	}

}
