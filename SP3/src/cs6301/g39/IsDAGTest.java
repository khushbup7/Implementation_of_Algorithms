/** Class to test IsDAG class
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/16
*/
package cs6301.g39;

public class IsDAGTest {

	public static void main(String[] args) {
		Graph g3 = new Graph(3);
		g3.directed = true;
		g3.addEdge(g3.getVertex(1), g3.getVertex(2), 1);
		g3.addEdge(g3.getVertex(3), g3.getVertex(1), 1);
		g3.addEdge(g3.getVertex(2), g3.getVertex(3), 1);

		System.out.println(IsDAG.isDAG(g3));
	}
}
