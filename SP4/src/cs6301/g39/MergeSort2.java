/** MergeSort class which implements mergesort on an integer array - implements Algorithm 2
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/22
 *  Usage:  MergeSort2.mergeSort(int[] arr, int[] tmp)

 */
package cs6301.g39;

public class MergeSort2 {

	/**
	 * Main method for merge sort of int
	 * 
	 * @param arr
	 *            - Given array of integers to be sorted
	 * @param tmp
	 *            - Given array to store temporary values
	 */
	static void mergeSort(int[] arr, int[] tmp) {

		mergeSort(arr, 0, arr.length - 1, tmp);
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
	static void mergeSort(int[] arr, int left, int right, int[] tmp) {

		if (left < right) {
			int mid = (left + right) / 2; // find middle index
			mergeSort(arr, left, mid, tmp); // merge sort on the first
													// half of the array
			mergeSort(arr, mid + 1, right, tmp); // merge sort on the
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

	
}
