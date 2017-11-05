package cs6301.g39;

import java.util.Scanner;

import cs6301.g00.Graph;
import cs6301.g00.Graph.Vertex;
import cs6301.g00.Graph.Edge;
import cs6301.g39.DMSTGraph.DMSTEdge;
import cs6301.g39.DMSTGraph.DMSTVertex;

public class Test {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int start = in.nextInt(); // root node of the MST
		Graph g = Graph.readDirectedGraph(in);

		DMSTGraph dg = new DMSTGraph(g);
		dg = new DMSTGraph(dg, dg.dv);
		
		for (Vertex u : dg) {
			DMSTVertex du = (DMSTVertex) u;
			if (du.getName() == 1 || du.getName() == 4)
				du.disable();

			for (Edge e : du) {
				if (e.getWeight() == 6) {
					DMSTEdge de = (DMSTEdge) e;
					de.disable();
				}
			}
		}

		System.out.println("Enabled Vertices");
		for (Vertex u : dg) {
			System.out.print(u.getName() + " ");
		}
		
		System.out.println();
		System.out.println("Enabled Edges");
		for (Vertex u : dg) {
			for (Edge e : u) {
				System.out.print(e.getName() + " ");
			}
		}
	}
}
