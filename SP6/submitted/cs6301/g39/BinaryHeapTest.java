/** Driver Class to test Binary heap and basic operations on it. 
 * @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 * Ver 1.0: 2017/10/04
*/

package cs6301.g39;

import cs6301.g00.Shuffle;

public class BinaryHeapTest {

	public static void main(String[] args) {
		int n = 2000;
		if(args.length > 0)
			n = Integer.parseInt(args[0]);
		
		Integer[] arrInt = new Integer[n];
		
		for (int i = 0; i < n; i++) {
			arrInt[i] = new Integer(i);
		}
		
		//Heap sort with min heap
		Shuffle.shuffle(arrInt);
		BinaryHeap.heapSort(arrInt, new MinHeapComparator());
		for (int i = 0; i < n; i++) {
			 System.out.print(" " + arrInt[i]);
		}

		//Heap sort with max heap
		Shuffle.shuffle(arrInt);
		BinaryHeap.heapSort(arrInt, new MaxHeapComparator());
		for (int i = 0; i < n; i++) {
			 System.out.print(" " + arrInt[i]);
		 }
	 }

	}
