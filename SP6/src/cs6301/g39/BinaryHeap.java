/** Class to implement Binary heap and basic operations on it. It is a min heap or max heap based on the comparator used.
 * It also implements heapsort algorithm.
 * @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 * Ver 1.0:  Starter code for bounded size Binary Heap implementation
 * Ver 1.1: 2017/10/04
 * Usage: BinaryHeap b = new BinaryHeap(T[] q, Comparator<T> comp, int n)
*/

package cs6301.g39;

import java.util.Comparator;

public class BinaryHeap<T> {
	T[] pq;				//array of elements of type T
	Comparator<T> c;    //comparator of type T 
	int size;			//size of the binary heap

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

	//insert new element x to heap
	public void insert(T x) throws Exception {
		add(x);
	}

	//deleting the minimum element fom heap
	public T deleteMin() {
		return remove();
	}

	//get the minimum element from the heap
	public T min() {
		return peek();
	}

	//Helper method for insert
	public void add(T x) {
		if (size == pq.length)
			throw new ArrayIndexOutOfBoundsException();
		move(size, x);
		percolateUp(size);
		size++;
	}

	//Helper method for deleteMin(). "Minimum" element is always at index 0, i.e. at root
	public T remove() {
		if (size == 0)
			throw new ArrayIndexOutOfBoundsException();
		T min = pq[0];
		move(0, pq[--size]);
		percolateDown(0);  //restore the heap order after removing the minimum element
		return min;
	}

	//Helper method for min(). "Minimum" element is always at index 0, i.e. at root
	public T peek() {
		if (size == 0)
			throw new ArrayIndexOutOfBoundsException();
		return pq[0];
	}

		/* Replaces root of binary heap by x if x has higher priority (smaller)
		 * than root, and restore heap order. Otherwise do nothing. 
		 * This operation is used in finding largest k elements in a stream.
		 */
	public void replace(T x) {
		if (c.compare(x, pq[0]) > 0) {
			move(0, x);
			percolateDown(0);
		}
	}

	/* Restore heap order in direction from leaf node to the root. pq[i] may violate heap order with parent */
	void percolateUp(int i) {
		T x = pq[i];
		while (i > 0 && c.compare(x, pq[parent(i)]) < 0) {
			move(i, pq[parent(i)]);
			i = parent(i);
		}
		move(i, x);
	}

	/* Restore heap order in direction from root to leaf node. pq[i] may violate heap order with children */
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

	/* Return parent idex of index i */
	int parent(int i) {
		return (i - 1) / 2;
	}

	/* move element x to pq[i] */
	void move(int i, T x) {
		pq[i] = x;
	}

	/* Create a heap. Precondition: none. */
	void buildHeap() {
		for (int i = (size - 2) / 2; i >= 0; i--)
			percolateDown(i);
	}

	/*
	 * sort array A[]. Sorted order depends on comparator used to build heap. 
	 * min heap ==> descending order 
	 * max heap ==> ascending order
	 */
	public static <T> void heapSort(T[] A, Comparator<T> comp) {
		BinaryHeap<T> heap = new BinaryHeap<T>(A, comp, A.length);
		heap.buildHeap();
		for (int i = A.length - 1; i >= 0; i--) {
			A[i] = heap.remove();
		}
	}
}
