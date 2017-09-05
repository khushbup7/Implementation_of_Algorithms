/** 
 *  Class for running breadth first search algorithm on a given graph
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.2: 2017/09/02
 *  
 *  Usage: BreadFirstSearch.BFS(g, s)
 *  Here, g is a graph and s is a starting node in that graph. 
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
	 * Method to run a Breadth First Search on a given graph
	 * 
	 * @param G
	 *            - Given graph G
	 * @param s
	 *            - Starting node
	 * @return - a path from s to the node at maximum distance from s
	 */
	public static LinkedList<Graph.Vertex> BFS(Graph g, Graph.Vertex s) {

		int n = g.size();

		boolean[] marked = new boolean[n]; // array to check if certain node is visited or not
		int[] distance = new int[n]; // array to store distance of a node from s
		int[] predecessor = new int[n]; // array to store predecessor of each node during BFS
		Arrays.fill(distance, -1);
		Arrays.fill(predecessor, -1);

		Queue<Graph.Vertex> bfsQueue = new LinkedList<Graph.Vertex>();

		bfsQueue.add(s);
		distance[s.getName()] = 0;
		predecessor[s.getName()] = 0;

		while (!bfsQueue.isEmpty()) {

			Graph.Vertex tmp1 = bfsQueue.remove();
			marked[tmp1.getName()] = true;

			Iterator<Graph.Edge> it = tmp1.iterator();
			while (it.hasNext()) {

				Graph.Edge e1 = it.next();

				if (marked[e1.otherEnd(tmp1).getName()] == false) {
					bfsQueue.add(e1.otherEnd(tmp1));
					distance[e1.otherEnd(tmp1).getName()] = distance[tmp1.getName()] + 1;
					predecessor[e1.otherEnd(tmp1).getName()] = tmp1.getName();
				}
			}
		}

		// Finding the node that is at maximum distance from s
		int maxDistance = 0;
		int maxNodeIndex = 0;
		for (int i = 0; i < n; i++) {
			if (distance[i] > maxDistance) {
				maxDistance = distance[i];
				maxNodeIndex = i;
			}
		}

		// forming a pair of vertices s and a vertex that is at maximum distance from s
		// since longest path between these two vertices is needed
		ArrayList<Graph.Vertex> ret = new ArrayList<Graph.Vertex>();
		ret.add(s);
		ret.add(g.getVertex(maxNodeIndex + 1));

		return longestPath(ret, predecessor, g);
	}

	/**
	 * @param list1
	 *            - pair of vertices between which longest path is need to be found
	 * @param pred
	 *            - integer array to store the predecessor node of every node in the
	 *            graph
	 * @param g
	 *            - graph on which BFS is performed
	 * @return - longest path between two pair of vertices
	 */
	private static LinkedList<Graph.Vertex> longestPath(ArrayList<Graph.Vertex> list1, int[] pred, Graph g) {

		LinkedList<Graph.Vertex> lp = new LinkedList<Graph.Vertex>();

		Graph.Vertex source = list1.get(0);
		Graph.Vertex destination = list1.get(1);

		// traversing through predecessor array to find a path between two vertices,
		// source and destination
		int tmp = destination.getName();
		while (tmp != source.getName()) {
			lp.addFirst(g.getVertex(tmp + 1));
			tmp = pred[tmp];
		}
		lp.addFirst(source);
		return lp;
	}

}
