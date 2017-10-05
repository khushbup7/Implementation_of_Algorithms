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
