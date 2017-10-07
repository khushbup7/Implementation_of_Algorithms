/** PrimMST class implements comparator for PrimVertex class based on the minimum distance of the vertex from the MST
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/10/05 compare function implementation
 */
package cs6301.g39;

import java.util.Comparator;

import cs6301.g39.PrimMST.PrimVertex;

public class PrimVertexComparator implements Comparator<PrimVertex> {

	public int compare(PrimVertex u, PrimVertex v) {
		if (u.d < v.d)
			return -1;
		else if (u.d == v.d)
			return 0;
		else
			return 1;
	}

}
