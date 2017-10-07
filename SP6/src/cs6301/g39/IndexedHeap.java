// Ver 1.0:  Starter code for Indexed heaps

package cs6301.g39;

import java.util.Comparator;

import cs6301.g39.BinaryHeap;

public class IndexedHeap<T extends Index> extends BinaryHeap<T> {
	/** Build a priority queue with a given array q */
	public IndexedHeap(T[] q, Comparator<T> comp, int n) {
		super(q, comp, n);
	}

	/** restore heap order property after the priority of x has decreased */
	public void decreaseKey(T x) {
		percolateUp(x.getIndex());
	}
	
	public boolean isEmpty() {
		if(size == 0)
			return true;
		return false;
	}
	
	@Override
	void move(int i, T x) {
		pq[i] = x;
		x.putIndex(i);
	}
}
