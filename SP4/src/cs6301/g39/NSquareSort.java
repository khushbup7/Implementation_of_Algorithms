/** NSquareSort class implements insertion sort on generic arrays
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/08/28
 *  Usage:  NSquareSort.nSquareSort(int[] arr)
 */

package cs6301.g39;

public class NSquareSort {
	static void nSquareSort(int[] arr, int left, int right) {
		int n = right - left + 1;
		for (int i = left + 1; i < left + n; ++i) {
			int key = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > key ) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
	}
}
