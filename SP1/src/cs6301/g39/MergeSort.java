/** MergeSort class which implements mergesort on an integer array as well as generic arrays
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/08/26
 *  Usage:  MergeSort.mergeSort(int[] arr, int[] tmp)
 *  		MergeSort.mergeSort(T[] arr, T[] tmp)
 */
package cs6301.g39;

public class MergeSort {

	/**
	 * Main method for merge sort of int
	 * 
	 * @param arr
	 *            - Given array of integers to be sorted
	 * @param tmp
	 *            - Given array to store temporary values
	 */
	static void mergeSort(int[] arr, int[] tmp) {

		mergeSortDivide(arr, 0, arr.length - 1, tmp);
	}

	/**
	 * Helper method for mergeSort - sorts primitive int values array
	 * 
	 * @param arr
	 *            - Given array to be sorted
	 * @param left
	 *            - left index
	 * @param right
	 *            - right index
	 * @param tmp
	 *            - Given array to store temporary values
	 */
	static void mergeSortDivide(int[] arr, int left, int right, int[] tmp) {

		if (left < right) {
			int mid = (left + right) / 2; // find middle index
			mergeSortDivide(arr, left, mid, tmp); // merge sort on the first
													// half of the array
			mergeSortDivide(arr, mid + 1, right, tmp); // merge sort on the
														// second half of the
														// array
			merge(arr, left, mid, right, tmp); // merge first and second half of
												// the array
		}
	}

	/**
	 * Helper method for mergeSort - merges the two sorted sub arrays into one
	 * 
	 * @param arr
	 *            - Given array to be sorted
	 * @param left
	 *            - left index
	 * @param mid
	 *            - middle index
	 * @param right
	 *            - right index
	 * @param tmp
	 *            -Given array to store temporary values
	 */
	private static void merge(int[] arr, int left, int mid, int right, int[] tmp) {

		int i = left;
		int j = mid + 1;
		int index = left;
		while (i <= mid && j <= right) {
			if (arr[i] <= arr[j])
				tmp[index++] = arr[i++];
			else
				tmp[index++] = arr[j++];
		}
		while (i <= mid) {
			tmp[index++] = arr[i++];
		}
		while (j <= right) {
			tmp[index++] = arr[j++];
		}
		for (int ct = left; ct <= right; ct++) {
			arr[ct] = tmp[ct];
		}
	}

	/**
	 * Main method for merge sort of Generic array
	 * 
	 * @param arr
	 *            - Given generic array of elements
	 * @param tmp
	 *            - Given generic array to store temporary values
	 */
	static <T extends Comparable<? super T>> void mergeSort(T[] arr, T[] tmp) {

		mergeSortDivide(arr, 0, arr.length - 1, tmp);
	}

	/**
	 * Helper method for mergeSort - sorts a generic array
	 * 
	 * @param arr
	 *            - Given generic array of elements
	 * @param left
	 *            - left index
	 * @param right
	 *            - right index
	 * @param tmp
	 *            - Given generic array to store temporary values
	 */
	static <T extends Comparable<? super T>> void mergeSortDivide(T[] arr, int left, int right, T[] tmp) {

		if (left < right) {
			int mid = (left + right) / 2; // find middle index
			mergeSortDivide(arr, left, mid, tmp);// merge sort on the first half
													// of the array
			mergeSortDivide(arr, mid + 1, right, tmp);// merge sort on the
														// second half of the
														// array
			merge(arr, left, mid, right, tmp);// merge first and second half of
												// the array
		}
	}

	/**
	 * Helper method for mergeSort - merges the two sorted sub arrays into one
	 * 
	 * @param arr
	 *            - Given generic array to be sorted
	 * @param left
	 *            - left index
	 * @param mid
	 *            - middle index
	 * @param right
	 *            - right index
	 * @param tmp
	 *            -Given generic array to store temporary values
	 */
	private static <T extends Comparable<? super T>> void merge(T[] arr, int left, int mid, int right, T[] tmp) {
		int i = left;
		int j = mid + 1;
		int index = left;
		while (i <= mid && j <= right) {
			if (arr[i].compareTo(arr[j]) == -1)
				tmp[index++] = arr[i++];
			else
				tmp[index++] = arr[j++];
		}
		while (i <= mid) {
			tmp[index++] = arr[i++];
		}
		while (j <= right) {
			tmp[index++] = arr[j++];
		}
		for (int ct = left; ct <= right; ct++) {
			arr[ct] = tmp[ct];
		}
	}

}
