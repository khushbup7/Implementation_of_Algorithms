package cs6301.g39;

public class TopologicalTest {
	public static void main(String[] args) {
		Graph g = new Graph(9);
		g.directed = true;
		
		//Directed acyclic graph
		g.addEdge(g.getVertex(1), g.getVertex(2), 1);
		g.addEdge(g.getVertex(2), g.getVertex(4), 1);
		g.addEdge(g.getVertex(1), g.getVertex(7), 1);
		g.addEdge(g.getVertex(7), g.getVertex(6), 1);
		g.addEdge(g.getVertex(7), g.getVertex(4), 1);
		g.addEdge(g.getVertex(3), g.getVertex(2), 1);
		g.addEdge(g.getVertex(5), g.getVertex(6), 1);
		g.addEdge(g.getVertex(9), g.getVertex(5), 1);
		g.addEdge(g.getVertex(9), g.getVertex(3), 1);
		g.addEdge(g.getVertex(8), g.getVertex(3), 1);
		g.addEdge(g.getVertex(8), g.getVertex(9), 1);
		g.addEdge(g.getVertex(8), g.getVertex(1), 1);
		TopologicalOrder1 tp = new TopologicalOrder1(g);
		System.out.println(tp.toplogicalOrder1(g));

	}
}
