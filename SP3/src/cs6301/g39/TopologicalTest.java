package cs6301.g39;

public class TopologicalTest {
	public static void main(String[] args) {
		Graph g = new Graph(9);
		g.directed = true;

		    
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
		    
		    Graph g1 = new Graph(11);
			g1.directed = true;
			    
			    g1.addEdge(g1.getVertex(10), g1.getVertex(6), 1);
			    g1.addEdge(g1.getVertex(3), g1.getVertex(10), 1);
			    g1.addEdge(g1.getVertex(6), g1.getVertex(3), 1);
			    g1.addEdge(g1.getVertex(11), g1.getVertex(6), 1);
			    g1.addEdge(g1.getVertex(1), g1.getVertex(11), 1);
			    g1.addEdge(g1.getVertex(4), g1.getVertex(1), 1);
			    g1.addEdge(g1.getVertex(11), g1.getVertex(4), 1);
			    g1.addEdge(g1.getVertex(11), g1.getVertex(3), 1);
			    g1.addEdge(g1.getVertex(4), g1.getVertex(9), 1);
			    g1.addEdge(g1.getVertex(9), g1.getVertex(11), 1);
			    g1.addEdge(g1.getVertex(5), g1.getVertex(4), 1);
			    g1.addEdge(g1.getVertex(5), g1.getVertex(7), 1);
			    g1.addEdge(g1.getVertex(5), g1.getVertex(8), 1);
			    g1.addEdge(g1.getVertex(7), g1.getVertex(8), 1);
			    g1.addEdge(g1.getVertex(8), g1.getVertex(2), 1);
			    g1.addEdge(g1.getVertex(2), g1.getVertex(7), 1);
			    g1.addEdge(g1.getVertex(2), g1.getVertex(3), 1);
			    
		    
		System.out.println("is:" + StronglyConnectedComponents.stronglyConnectedComponents(g1));

	}
}
