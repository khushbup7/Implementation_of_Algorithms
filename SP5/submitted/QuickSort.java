/** Class to implement QuickSort by different partition algorithms.
 * @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/28
 *  Usage: QuickSort q = new QuickSort();
		q.quickSort(int[] arr);
		q.quickSort1(int[] arr);
*/
package cs6301.g39;

import java.util.Random;

// Class that implements different versions of quicksort
public class QuickSort {

	// Algorithm 1 of partition
	/**
	 * @param arr
	 *            - Array to be sorted
	 */
	void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	/**
	 * @param arr
	 *            - Overloaded method for QuickSort
	 * @param p
	 *            - start index for sorting
	 * @param r
	 *            - end index for sorting
	 */
	private void quickSort(int[] arr, int p, int r) {
		if (p < r) {
			int q = partition(arr, p, r);
			quickSort(arr, p, q);
			quickSort(arr, q + 1, r);
		}
	}

	/**
	 * Partitions the given array around a randomly selected pivot element
	 * 
	 * @param arr
	 *            - array to be sorted
	 * @param p
	 *            - start index of the partition
	 * @param r
	 *            - end index of the partition
	 * @return - index of the pivot element
	 */
	private int partition(int[] arr, int p, int r) {
		Random rand = new Random();
		int i = rand.nextInt(r - p + 1) + p; // randomly select index of the pivot element
		swap(arr, i, r);
		int x = arr[r];
		i = p - 1;
		for (int j = p; j <= r - 1; j++) {
			if (arr[j] < x) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, r);
		return i + 1;
	}

	/**
	 * Sorts the elements at indices i and r in the given array
	 * 
	 * @param arr
	 *            - given array
	 * @param i
	 *            - first index
	 * @param r
	 *            - second index
	 */
	private void swap(int[] arr, int i, int r) {
		int temp = arr[i];
		arr[i] = arr[r];
		arr[r] = temp;

	}

	// Algorithm 2 of partition
	/**
	 * @param arr
	 *            - Array to be sorted
	 */
	void quickSort1(int[] arr) {
		quickSort1(arr, 0, arr.length - 1);
	}

	/**
	 * @param arr
	 *            - Overloaded method for QuickSort
	 * @param p
	 *            - start index for sorting
	 * @param r
	 *            - end index for sorting
	 */
	private void quickSort1(int[] arr, int p, int r) {
		if (p < r) {
			int q = partition1(arr, p, r);
			quickSort(arr, p, q);
			quickSort(arr, q, r);
		}
	}
	/**
	 * Partitions the given array around a randomly selected pivot element
	 * 
	 * @param arr
	 *            - array to be sorted
	 * @param p
	 *            - start index of the partition
	 * @param r
	 *            - end index of the partition
	 * @return - index of the pivot element
	 */
	private int partition1(int[] arr, int p, int r) {
		Random rand = new Random();
		int i = rand.nextInt(r - p + 1) + p;
		int x = arr[i];
		i = p;
		int j = r;

		while (true) {
			while (arr[i] < x)
				i++;
			while (arr[j] > x)
				j--;
			if (i >= j)
				return j;
			swap(arr, i, j);
		}
	}
}
