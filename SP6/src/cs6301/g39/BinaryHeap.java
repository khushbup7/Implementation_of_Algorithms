	// Ver 1.0:  Starter code for bounded size Binary Heap implementation @author rbk

package cs6301.g39;

import java.util.Comparator;

public class BinaryHeap<T> {
	T[] pq;
	Comparator<T> c;
	int size;

	/**
	 * Build a priority queue with a given array q, using q[0..n-1]. It is not
	 * necessary that n == q.length. Extra space available can be used to add new
	 * elements.
	 */
	public BinaryHeap(T[] q, Comparator<T> comp, int n) {
		pq = q;
		c = comp;
		size = n;
	}

	public void insert(T x) throws Exception {
		add(x);
	}

	public T deleteMin() {
		return remove();
	}

	public T min() {
		return peek();
	}

	public void add(T x) {
		if (size == pq.length)
			throw new ArrayIndexOutOfBoundsException();
		move(size, x);
		percolateUp(size);
		size++;
	}

	public T remove() {
		if (size == 0)
			throw new ArrayIndexOutOfBoundsException();
		T min = pq[0];
		move(0, pq[--size]);
		percolateDown(0);
		return min;
	}

	public T peek() {
		if (size == 0)
			throw new ArrayIndexOutOfBoundsException();
		return pq[0];
	}

	public void replace(T x) {
		/*
		 * TO DO. Replaces root of binary heap by x if x has higher priority (smaller)
		 * than root, and restore heap order. Otherwise do nothing. This operation is
		 * used in finding largest k elements in a stream.
		 */
		if (c.compare(x, pq[0]) > 0) {
			move(0, x);
			percolateDown(0);
		}
	}

	/** pq[i] may violate heap order with parent */
	void percolateUp(int i) { /* to be implemented */
		T x = pq[i];
		while (i > 0 && c.compare(x, pq[parent(i)]) < 0) {
			move(i, pq[parent(i)]);
			i = parent(i);
		}
		move(i, x);
	}

	/** pq[i] may violate heap order with children */
	void percolateDown(int i) {
		T x = pq[i];
		int ch = 2 * i + 1;
		while (ch <= size - 1) {
			if (ch < size - 1 && c.compare(pq[ch], pq[ch + 1]) >= 0)
				ch++;
			if (c.compare(x, pq[ch]) <= 0)
				break;
			move(i, pq[ch]);
			i = ch;
			ch = 2 * i + 1;
		}
		move(i, x);
	}

	int parent(int i) {
		return (i - 1) / 2;
	}

	void move(int i, T x) {
		pq[i] = x;
	}

	/** Create a heap. Precondition: none. */
	void buildHeap() {
		for (int i = (size - 2) / 2; i >= 0; i--)
			percolateDown(i);
	}

	/*
	 * sort array A[]. Sorted order depends on comparator used to build heap. min
	 * heap ==> descending order max heap ==> ascending order
	 */
	public static <T> void heapSort(T[] A, Comparator<T> comp) {
		BinaryHeap<T> heap = new BinaryHeap<T>(A, comp, A.length);
		heap.buildHeap();
		for (int i = A.length - 1; i >= 0; i--) {
			A[i] = heap.remove();
		}
	}
}
