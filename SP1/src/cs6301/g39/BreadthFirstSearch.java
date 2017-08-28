/** BreadthFirstSearch class for running breadth first search algorithm on a given class
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/08/28
 *  Usage:  Timer timer = new Timer();
 *          timer.start();
 *          timer.end();
 *          System.out.println(timer);  // output statistics
 *  Corrected memory calculations to 1048576 instead of 1000000
 */
package cs6301.g39;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import cs6301.g00.Graph;

public class BreadthFirstSearch {
	
	
	/**
	 * Method to run a BFS on a given graph
	 * @param G Given graph G
	 * @param s Starting node
	 * @param n Number of vertices in a graph
	 * @return
	 */
	public static ArrayList<Graph.Vertex> BFS(Graph G, Graph.Vertex s, int n){

		ArrayList<Graph.Vertex> ret = new ArrayList<Graph.Vertex>();
		
		int[][] n1 = new int[2][n];
		
		Queue<Graph.Vertex> bfsQueue = new LinkedList<Graph.Vertex>(); 
		
		bfsQueue.add(s);
		
		while(!bfsQueue.isEmpty()) {
			
			Graph.Vertex tmp1 = bfsQueue.remove();
			n1[0][tmp1.getName()] = 1;
			n1[1][tmp1.getName()] = 1; //
			
			//TODO adj is made public by us. Verify it with professor before submitting
			while(!tmp1.adj.isEmpty()) {
				Graph.Edge e1 = tmp1.adj.remove(0);
				
				if(n1[0][e1.otherEnd(tmp1).getName()] == 0)
					bfsQueue.add(e1.otherEnd(tmp1));
			}
		}
		
		ret.add(s);
		
		return ret;
		
	}
}
