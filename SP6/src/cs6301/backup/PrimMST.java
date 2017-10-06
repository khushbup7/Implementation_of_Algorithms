
/* Ver 1.0: Starter code for Prim's MST algorithm */

package cs6301.g39;

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
		PrimMST.PrimVertex parent;
		
		PrimVertex(Graph.Vertex u) {
			seen = false;
			parent = null;
			d = Infinity;
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
		int wmst = 0;

		// SP6.Q4: Prim's algorithm using PriorityQueue<Edge>:

		return wmst;
	}

	public int prim2(Graph.Vertex s) {
		int wmst = 0;
		PrimVertex src = getVertex(s);
		src.d = 0;
		
		IndexedHeap<PrimVertex> pq = new IndexedHeap<PrimVertex>(node, new PrimVertexComparator(), g.size());
		pq.buildHeap();
		
//		while(!pq.isEmpty() ) {
//			PrimVertex u = pq.remove();
//			u.seen = true;
//			wmst = wmst + u.d;
//			Graph.Vertex s1 = g.getVertex(u);
//			u.iterator();
//			while(edgeItr.hasNext()) {
//				Graph.Edge e = edgeItr.next();
//				Graph.Vertex vGraph = e.otherEnd(s);
//				PrimVertex v = getVertex(vGraph);
//				if(!v.seen && e.weight < v.d) {
//					v.d = e.weight;
//					v.parent = u;
//				}
//				percolateUp(v.getIndex());
//			}
//		}

//		// Node v ∈ V −S stores in v.d, the weight of a smallest edge that connects v to some u∈S
//		for( Edge e: u ) do
//		v ← e.otherEnd( u )
//		if ! v.seen and e.weight < v.d then
//		v.d ← e.weight; v.parent ← u;
//		percolateUp( index of v in q ) // How do we find the index of v in q?
//		return

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
