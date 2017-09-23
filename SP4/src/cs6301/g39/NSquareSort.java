/** NSquareSort class implements insertion sort on int arrays
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/23
 *  Usage:  NSquareSort.nSquareSort(int[] arr)
 */

package cs6301.g39;

public class NSquareSort {
	static void nSquareSort(int[] arr, int left, int right) {
		int n = right - left + 1;
		for (int i = left + 1; i < left + n; ++i) {
			int key = arr[i];
			int j = i - 1;
			while (j >= left && arr[j] > key ) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
	}
}
