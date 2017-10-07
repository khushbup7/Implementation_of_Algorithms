
/* Ver 1.0: Starter code for Prim's MST algorithm */

package cs6301.g39;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import cs6301.g00.Timer;
import cs6301.g39.IndexedHeap;

import java.io.FileNotFoundException;
import java.io.File;

public class PrimMST extends GraphAlgorithm<PrimMST.PrimVertex> {
	static final int Infinity = Integer.MAX_VALUE;

	class PrimVertex implements Index {
		int d, index;
		boolean seen;
		PrimVertex parent;
		Graph.Vertex self;

		PrimVertex(Graph.Vertex u) {
			seen = false;
			parent = null;
			d = Infinity;
			self = u;
		}

		public void putIndex(int i) {
			index = i;
		}

		public int getIndex() {
			return index;
		}
	}

	public PrimMST(Graph g) {
		super(g);
		node = new PrimVertex[g.size()];
		// Create array for storing vertex properties
		for (Graph.Vertex u : g) {
			node[u.getName()] = new PrimVertex(u);
		}
	}

	public int prim1(Graph.Vertex s) {
		PriorityQueue<Graph.Edge> q = new PriorityQueue<>(g.size(), new Comparator<Graph.Edge>() {
			public int compare(Graph.Edge a, Graph.Edge b) {
				if (a.weight < b.weight)
					return -1;
				else if (a.weight > b.weight)
					return 1;
				else
					return 0;
			}
		});
		// SP6.Q4: Prim's algorithm using PriorityQueue<Edge>:
		// start
		getVertex(s).seen = true;
		int wmst = 0;
		for (Graph.Edge e : s.adj)
			q.add(e);
		while (q.size() != 0) {
			Graph.Edge currentEdge = q.remove();
			Graph.Vertex unseenVertex = getVertex(currentEdge.from).seen ? currentEdge.to : currentEdge.from;
			if (getVertex(unseenVertex).seen)
				continue;

			PrimVertex v = getVertex(unseenVertex);
			v.seen = true;
			v.parent = getVertex(currentEdge.from);
			wmst = wmst + currentEdge.weight;
			for (Graph.Edge e2 : unseenVertex.adj) {
				if (!getVertex(e2.otherEnd(unseenVertex)).seen)
					q.add(e2);
			}
		}

		return wmst;
	}

	public int prim2(Graph.Vertex s) {
		int wmst = 0;
		PrimVertex t = getVertex(s);
		t.d = 0;
		
		PrimVertex[] nodes = new PrimVertex[node.length];
		System.arraycopy(node, 0, nodes, 0, node.length);
		IndexedHeap<PrimVertex> heap = new IndexedHeap<>(nodes, new PrimVertexComparator(), g.size());
		heap.buildHeap();
		
		while (!heap.isEmpty()) {
			PrimVertex u = heap.remove();
			u.seen = true;
			wmst = wmst + u.d;
			for (Graph.Edge e : u.self.adj) {
				
				PrimVertex v = getVertex(e.otherEnd(u.self));
				if (!v.seen && e.weight < v.d) {
					v.d = e.weight;
					v.parent = u;
					heap.decreaseKey(v);
				}
			}
		}

		return wmst;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in;

		if (args.length > 0) {
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		} else {
			in = new Scanner(System.in);
		}

		Graph g = Graph.readGraph(in);
		Graph.Vertex s = g.getVertex(1);

		Timer timer = new Timer();
		PrimMST mst = new PrimMST(g);
		int wmst = mst.prim2(s);
		timer.end();
		System.out.println(wmst);
	}

}
