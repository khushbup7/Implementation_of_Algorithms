/** Class to test Strongly Connected Components
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/16
*/
package cs6301.g39;

public class SCCTest {
	public static void main(String[] args) {
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
