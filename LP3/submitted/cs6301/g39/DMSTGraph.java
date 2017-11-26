/** Class to represent DMST Graph
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/11/12
 */
package cs6301.g39;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cs6301.g00.ArrayIterator;
import cs6301.g00.Graph;

public class DMSTGraph extends Graph {

	DMSTVertex[] dv;
	int numVertices;
	int numEdges;
	static int enabledVertices;
	static int enabledEdges;
	

	static class DMSTVertex extends Vertex {

		boolean disabled;
		List<Edge> drevAdj;
		List<Vertex> componentVertices;
		int componentNo;

		public DMSTVertex(Vertex u) {
			super(u);
			componentNo = -1;
			drevAdj = new LinkedList<Edge>();
			disabled = false;
			componentVertices = new LinkedList<Vertex>();
		}

		boolean isDisabled() {
			return disabled;
		}

		void disable() {
			disabled = true;
			enabledVertices--;
		}
		
		void enable() {
			disabled = false;
			enabledVertices++;
		}

		@Override
		public Iterator<Edge> iterator() {
			return new DMSTVertexIterator(this);
		}
		
		@Override
		public Iterator<Edge> reverseIterator() {
			return new DMSTVertexRevIterator(this);
		}
	}

	static class DMSTVertexIterator implements Iterator<Edge> {
		Edge cur;
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
			cur = it.next();
			while (((DMSTEdge) cur).isDisabled() && it.hasNext()) {
				cur = it.next();
			}
			ready = true;
			return !((DMSTEdge) cur).isDisabled();
		}

		public Edge next() {
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
	
	static class DMSTVertexRevIterator implements Iterator<Edge> {
		Edge cur;
		Iterator<Edge> it;
		boolean ready;

		DMSTVertexRevIterator(DMSTVertex u) {
			this.it = u.revAdj.iterator();
			ready = false;
		}

		public boolean hasNext() {
			if (ready) {
				return true;
			}
			if (!it.hasNext()) {
				return false;
			}
			cur = it.next();
			while (((DMSTEdge) cur).isDisabled() && it.hasNext()) {
				cur = it.next();
			}
			ready = true;
			return !((DMSTEdge) cur).isDisabled();
		}

		public Edge next() {
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

	static class DMSTEdge extends Edge {
		boolean disabled;
		DMSTEdge src;
		int origWeight;

		DMSTEdge(Vertex from, Vertex to, int weight, int name) {
			super(from, to, weight, name);
			origWeight = weight;
			src = null;
			disabled = false;
		}
		
		public DMSTEdge(Edge e) {
			super(e);
			src = null;
			disabled = false;
		}

		boolean isDisabled() {
			DMSTVertex dfrom = (DMSTVertex) fromVertex();
			DMSTVertex dto = (DMSTVertex) toVertex();
			return disabled || dfrom.isDisabled() || dto.isDisabled();
		}

		void disable() {
			disabled = true;
			enabledEdges--;
		}
		
		void enable() {
			disabled = false;
			enabledEdges++;
		}
	}

	public DMSTGraph(Graph g) {
		super(g);
		dv = new DMSTVertex[2 * g.size()]; // Extra space is allocated in array for nodes to be added later
		numVertices = g.size();
		numEdges = g.edgeSize();
		enabledVertices = numVertices;
		enabledEdges = numEdges;
		
		for (Vertex u : g) {
			dv[u.getName()] = new DMSTVertex(u);
		}

		for (Vertex u : g) {
			List<Edge> adj = new LinkedList<Edge>();
			DMSTVertex du = getVertex(u);

			for (Edge e : u) {
				DMSTVertex dv = getVertex(e.otherEnd(u));
				DMSTEdge de = new DMSTEdge(du, dv, e.getWeight(), e.getName());
				adj.add(de);
				dv.drevAdj.add(de);
			}
			du.adj = adj;
		}
		
		for (Vertex u : dv) {
			if(u == null)
				break;
			DMSTVertex du = (DMSTVertex) u;
			du.revAdj = du.drevAdj;
		} 
	}
	
	public DMSTGraph(DMSTGraph g, Vertex[] arr) {
		super(g, arr);
		dv = (DMSTVertex[]) arr;
		numVertices = g.numVertices;
		numEdges = g.numEdges;
		enabledVertices = numVertices;
		enabledEdges = numEdges;
	}
	
	public DMSTEdge addEdge(DMSTEdge de) {
		de.fromVertex().adj.add(de);
		de.toVertex().revAdj.add(de);
		numEdges++;
		enabledEdges++;
		return de;
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
			this.it = new ArrayIterator<DMSTVertex>((DMSTVertex[]) getVertexArray(), 0, dg.size() - 1); // Iterate over existing elements only
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

	public void addVertex(DMSTVertex u) {
		dv[numVertices] = u;
		numVertices++;
		enabledVertices++;
	}
	
	@Override
	public int size() {
		return numVertices;
	}
}