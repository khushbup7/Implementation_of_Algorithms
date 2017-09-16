package cs6301.g39;

import java.util.Iterator;

public class IsDAG {
	
	public static boolean isDAG(Graph g) {
		
		Iterator<Graph.Vertex> itOrder = g.iterator();
		DFS dfsTrav = new DFS(g);
		dfsTrav.dfs(itOrder);
		
		if(!dfsTrav.isDAG)
			return false;

		return true;
	}

}
