/** Base Class for Graph algorithms
 *  @author rbk
 *  Ver 1.0: 2017/09/16
 *  
*/

package cs6301.g39;

public class GraphAlgorithm<T> {
    Graph g;
    // Algorithm uses a parallel array for storing information about vertices
    protected T[] node;

    public GraphAlgorithm(Graph g) {
	this.g = g;
    }

    T getVertex(Graph.Vertex u) {
	return Graph.Vertex.getVertex(node, u);
    }
}
