package cs6301.g39;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cs6301.g00.ArrayIterator;
import cs6301.g00.Graph;

public class DMSTGraph extends Graph {

    /**
     * Nested class to represent a vertex of a graph
     */

    public static class DMSTVertex extends Vertex {
    	
    	boolean disabled;
		
    	DMSTVertex(Vertex u) {
			super(u);
			disabled = false;
		}
    	
    	boolean isDisabled() {
			return disabled;
		}

		void disable() {
			disabled = true;
		}
		
		@Override
		public Iterator<Edge> iterator() {
			return new DMSTVertexIterator(this);
		}

		class DMSTVertexIterator implements Iterator<Edge> {
			DMSTEdge cur;
			Iterator<Edge> it;
			boolean ready;

			DMSTVertexIterator(DMSTVertex u) {
				this.it = u.adj.iterator();
				ready = false;
			}

			public boolean hasNext() {
				if (ready) {
					return true;
				}
				if (!it.hasNext()) {
					return false;
				}
				cur = (DMSTEdge) it.next();
				while (cur.isDisabled() && it.hasNext()) {
					cur = (DMSTEdge) it.next();
				}
				ready = true;
				return !cur.isDisabled();
			}

			public DMSTEdge next() {
				if (!ready) {
					if (!hasNext()) {
						throw new java.util.NoSuchElementException();
					}
				}
				ready = false;
				return cur;
			}

			public void remove() {
				throw new java.lang.UnsupportedOperationException();
			}
		}
    }
    
    public static class DMSTEdge extends Edge {
    	boolean disabled;
    	DMSTEdge src; // used in expansion to trace back

		DMSTEdge(DMSTVertex from, DMSTVertex to, int weight) {
			super(from, to, weight);
			disabled = false;
		}

		boolean isDisabled() {
			DMSTVertex xfrom = (DMSTVertex) super.fromVertex();
			DMSTVertex xto = (DMSTVertex) super.toVertex();
			return disabled || xfrom.isDisabled() || xto.isDisabled();
		}
    }
    
    DMSTVertex[] dv;
    public DMSTGraph(Graph g) {
		super(g);
		dv = new DMSTVertex[2 * g.size()]; // Extra space is allocated in array for nodes to be added later
		for (Vertex u : g) {
			dv[u.getName()] = new DMSTVertex(u);
		}

		// Make copy of edges
		for (Vertex u : g) {
			List<DMSTEdge> adj = new LinkedList<DMSTEdge>();
 			for (Edge e : u) {
				Vertex v = e.otherEnd(u);
				DMSTVertex d1 = getVertex(u);
				DMSTVertex d2 = getVertex(v);
				adj.add(new DMSTEdge(d1, d2, e.getWeight()));
			}
 			u.adj.clear();
 			for(Edge e : adj)
 				u.adj.add(e);
 			
 			List<DMSTEdge> revAdj = new LinkedList<DMSTEdge>();
 			for (Edge e : u.revAdj) {
				Vertex v = e.otherEnd(u);
				DMSTVertex d1 = getVertex(u);
				DMSTVertex d2 = getVertex(v);
				revAdj.add(new DMSTEdge(d1, d2, e.getWeight()));
			}
 			u.revAdj.clear();
 			for(Edge e : revAdj)
 				u.revAdj.add(e);
		}
    }
    
    @Override
	public Vertex getVertex(int n) {
		return dv[n - 1];
	}

	DMSTVertex getVertex(Vertex u) {
		return Vertex.getVertex(dv, u);
	}
	
	@Override
	public Iterator<Vertex> iterator() {
		return new DMSTGraphIterator(this);
	}

	class DMSTGraphIterator implements Iterator<Vertex> {
		Iterator<DMSTVertex> it;
		DMSTVertex xcur;

		DMSTGraphIterator(DMSTGraph dg) {
			this.it = new ArrayIterator<DMSTVertex>(dg.dv, 0, dg.size() - 1); // Iterate over existing elements only
		}

		public boolean hasNext() {
			if (!it.hasNext()) {
				return false;
			}
			xcur = it.next();
			while (xcur.isDisabled() && it.hasNext()) {
				xcur = it.next();
			}
			return !xcur.isDisabled();
		}

		public Vertex next() {
			return xcur;
		}

		public void remove() {
		}

	}
}