/** Implemented Binary Search to return the index of the largest element that is less than or equal to x.
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/23
 *  Usage : BinarySearch.binarSearch(T[] arr, T x)
 */

package cs6301.g39;

public class BinarySearch {
	// Preconditions: arr[0..n-1] is sorted, and arr[0] <= x < arr[n-1].
	// Returns index i such that arr[i] <= x < arr[i+1].
	public static <T extends Comparable<? super T>> int binarySearch(T[] arr, T x) {
		return binarySearch(arr, x, 0, arr.length - 1);
	}

	/**
	 * @param arr
	 *            - given array
	 * @param x
	 *            - element to search
	 * @param start
	 *            - start index of the interval to search
	 * @param end
	 *            - end index of the interval to search
	 * @return index of the largest element less than or equal to x.
	 */
	public static <T extends Comparable<? super T>> int binarySearch(T[] arr, T x, int start, int end) {
		int mid = (start + end) / 2;
        if (arr[mid-1].compareTo(x) < 0 && arr[mid].compareTo(x) >= 0)
			return mid - 1;
		if (arr[mid].compareTo(x) < 0)
			return binarySearch(arr, x, mid + 1, end);
		if (arr[mid].compareTo(x) > 0)
			return binarySearch(arr, x, start, mid);
		return -1;
	}
}
