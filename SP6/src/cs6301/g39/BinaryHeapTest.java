package cs6301.g39;

import cs6301.g00.Shuffle;

public class BinaryHeapTest {

	public static void main(String[] args) {
		int n = 2000;
		Integer[] arrInt = new Integer[n];
		for (int i = 0; i < n; i++) {
			arrInt[i] = new Integer(i);
		}
		Shuffle.shuffle(arrInt);

		BinaryHeap.heapSort(arrInt, new MinHeapComparator());

//		Shuffle.shuffle(arrInt);
//
//		BinaryHeap.heapSort(arrInt, new MaxHeapComparator());

		// Uncomment to print array
		 for (int i = 0; i < n; i++) {
			 System.out.print(" " + arrInt[i]);
		 }
	}

}
