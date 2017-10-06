
/* Ver 1.0: Starter code for Prim's MST algorithm */

package cs6301.g39;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import cs6301.g00.Timer;

import java.io.FileNotFoundException;
import java.io.File;

public class PrimMST extends GraphAlgorithm<PrimMST.PrimVertex> {
	static final int Infinity = Integer.MAX_VALUE;
	
	class PrimVertex implements Index {
		int d, index;
		boolean seen;
		Graph.Vertex parent;
		
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
		PriorityQueue<Graph.Edge> q = new PriorityQueue<>(g.size(), new Comparator<Graph.Edge>() {
			public int compare(Graph.Edge a, Graph.Edge b) {
				if(a.weight < b.weight)
					return -1;
				else if(a.weight > b.weight)
					return 1;
				else return 0;
			}
		});
		// SP6.Q4: Prim's algorithm using PriorityQueue<Edge>:
		// start
		getVertex(s).seen = true;
		int wmst = 0;
		for(Graph.Edge e : s.adj) q.add(e);
		while(q.size() != 0) {
			Graph.Edge currentEdge = q.remove();
			getVertex(currentEdge.from).seen = true;
			if(!getVertex(currentEdge.to).seen) {
				getVertex(currentEdge.to).seen = true;
				getVertex(currentEdge.to).parent = currentEdge.from;
				wmst = wmst + currentEdge.weight;
				for(Graph.Edge e2 : currentEdge.to.adj) {
					if(!getVertex(e2.otherEnd(currentEdge.to)).seen) 
						q.add(e2);
				}
			}
		}

		return wmst;
	}

	public int prim2(Graph.Vertex s) {
		int wmst = 0;

		// SP6.Q6: Prim's algorithm using IndexedHeap<PrimVertex>:

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
		int wmst = mst.prim1(s);
		timer.end();
		System.out.println(wmst);
	}
}
