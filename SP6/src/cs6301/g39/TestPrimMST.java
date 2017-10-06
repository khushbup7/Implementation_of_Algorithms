package cs6301.g39;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestPrimMST {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in;
		if (args.length > 0) {
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		} else {
			in = new Scanner(System.in);
		}
		
		Graph g = Graph.readGraph(in);
		PrimMST mst = new PrimMST(g);
		Graph.Vertex src = g.getVertex(1);
		int wmst = mst.prim1(src);
		System.out.println(wmst);
	}

}
