/** MergeSort class which implements mergesort on an integer array - Implements Algorithm 4
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/23
 *  Usage:  MergeSort4.mergeSort(int[] arr)
 */
package cs6301.g39;

public class MergeSort4 {

	static int threshold = 17;

	/**
	 * @param arr
	 *            - Array to be sorted
	 */
	static void mergeSort(int[] arr) {
		int[] B = new int[arr.length];
		for (int i = 0; i < arr.length; i++)
			B[i] = arr[i];
		mergeSort(arr, B, 0, arr.length - 1);
	}

	/**
	 * Helper method for mergeSort - sorts primitive int values array
	 * 
	 * @param arr
	 *            - given array
	 * @param B
	 *            - copy of the given array
	 * @param p
	 *            - start index for the sort
	 * @param r
	 *            - end index for the sort
	 */
	static void mergeSort(int[] arr, int[] B, int p, int r) {
		if ((r - p) < threshold) {
			NSquareSort.nSquareSort(arr, p, r);
		} else {
			int q = (p + r) / 2; // find middle index
			mergeSort(B, arr, p, q); // merge sort on the first
										// half of the array
			mergeSort(B, arr, q + 1, r); // merge sort on the
											// second half of the
											// array
			merge(B, arr, p, q, r); // merge first and second half of
									// the array
		}
	}

	/**
	 * @param arr
	 *            - given array
	 * @param B
	 *            - copy of the given array used to store temporary values
	 * @param p
	 *            - start index of the first subarray to be merged
	 * @param q
	 *            - end index of the first array to be merged
	 * @param r
	 *            - end index of the second subarray to be merged
	 */
	private static void merge(int[] arr, int[] B, int p, int q, int r) {

		int i = p;
		int j = q + 1;
		for (int k = p; k <= r; k++) {
			if (j > r || (i <= q && arr[i] <= arr[j]))
				B[k] = arr[i++];
			else
				B[k] = arr[j++];
		}
	}
}
