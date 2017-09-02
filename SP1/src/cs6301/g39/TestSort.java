/** TestSort class to test mergesort and insertion sort.
 * Uses shuffle class to shuffle the elements of the array
 * Uses Timer class to calculate run times of the algorithms
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/08/26
 */

package cs6301.g39;

import cs6301.g00.Shuffle;
import cs6301.g00.Timer;

public class TestSort {
	public static void main(String[] args) {
		// n = array size
		int n = 1000000;

		int[] arint = new int[n];
		for (int i = 0; i < n; i++) {
			arint[i] = i;
		}
		Shuffle.shuffle(arint);
		int[] tmpint = new int[n];

		// Starting merge sort on int
		Timer timer = new Timer();
		timer.start();
		MergeSort.mergeSort(arint, tmpint);
		timer.end();
		System.out.println("Time to merge sort array of int (" + n + "):");
		System.out.println(timer);

		Integer[] arrInt = new Integer[n];
		for (int i = 0; i < n; i++) {
			arrInt[i] = new Integer(i);
		}
		Shuffle.shuffle(arrInt);

		// Starting merge sort on Integers
		Integer[] tmpInt = new Integer[n];
		timer.start();
		MergeSort.mergeSort(arrInt, tmpInt);
		timer.end();
		System.out.println("Time to merge sort array of Integers (" + n + "):");
		System.out.println(timer);

		// Starting insertion sort on Integers
		arrInt = new Integer[n];
		for (int i = 0; i < n; i++) {
			arrInt[i] = new Integer(i);
		}
		Shuffle.shuffle(arrInt);
		timer.start();
		NSquareSort.nSquareSort(arrInt);
		timer.end();
		System.out.println("Time to insertion sort array of Integers (" + n + "):");
		System.out.println(timer);

	}

}
