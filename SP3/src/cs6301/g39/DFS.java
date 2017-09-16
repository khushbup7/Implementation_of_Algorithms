package cs6301.g39;

public class DFS extends GraphAlgorithm<DFS.DFSVertex> {
	public DFS(Graph g) {
		super(g);
		node = new DFSVertex[g.size()];
		// Create array for storing vertex properties
		for (Graph.Vertex u : g) {
			node[u.getName()] = new DFSVertex(u);
		}
		// Set source to be at distance 0
		// getVertex(src).distance = 0;
	}

	public static final int INFINITY = Integer.MAX_VALUE;

	// Class to store information about a vertex in this algorithm
	static class DFSVertex {
		boolean seen;
		Graph.Vertex parent;
		int distance; // distance of vertex from source
		int componentNo; // 

		DFSVertex(Graph.Vertex u) {
			seen = false;
			parent = null;
			distance = INFINITY;
			componentNo = -1;
		}
		
		public boolean isSeen() {
			return seen;
		}

		public void setSeen(boolean seen) {
			this.seen = seen;
		}

		public Graph.Vertex getParent() {
			return parent;
		}

		public void setParent(Graph.Vertex parent) {
			this.parent = parent;
		}

		public int getDistance() {
			return distance;
		}

		public void setDistance(int distance) {
			this.distance = distance;
		}

		public int getComponentNo() {
			return componentNo;
		}

		public void setComponentNo(int componentNo) {
			this.componentNo = componentNo;
		}
	}
}
