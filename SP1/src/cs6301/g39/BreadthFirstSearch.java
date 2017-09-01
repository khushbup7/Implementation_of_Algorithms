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
import java.util.Arrays;
import java.util.Iterator;
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
	public static LinkedList<Graph.Vertex> BFS(Graph g, Graph.Vertex s){

		ArrayList<Graph.Vertex> ret = new ArrayList<Graph.Vertex>();
		int n = g.size();
		
		boolean[] marked = new boolean[n];
		int[] distance = new int[n];
		Arrays.fill(distance, -1);
		
		Queue<Graph.Vertex> bfsQueue = new LinkedList<Graph.Vertex>(); 
		
		bfsQueue.add(s);
		distance[s.getName()] = 0;
		
		while(!bfsQueue.isEmpty()) {
			
			Graph.Vertex tmp1 = bfsQueue.remove();
			marked[tmp1.getName()] = true;
			
			Iterator<Graph.Edge> it = tmp1.iterator(); 
			while(it.hasNext()) {
				
				Graph.Edge e1 = it.next();
				
				if(marked[e1.otherEnd(tmp1).getName()] == false) {
					bfsQueue.add(e1.otherEnd(tmp1));
					distance[e1.otherEnd(tmp1).getName()] = distance[tmp1.getName()] + 1;
				}
				it.remove();
			}
		}
		
		int maxDistance = 0;
		int maxNodeIndex = 0;
		for(int i = 0; i < n; i++) {
			if(distance[i] > maxDistance) {
				maxDistance = distance[i];
				maxNodeIndex = i;
			}
		}
			
		ret.add(s);
		ret.add(g.getVertex(maxNodeIndex));
		
		return longestPath(ret);
	}
	
	private static LinkedList<Graph.Vertex> longestPath(ArrayList<Graph.Vertex> list1) {
		
		return null;
	}
	
}
