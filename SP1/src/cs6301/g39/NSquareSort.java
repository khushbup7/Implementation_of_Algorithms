/** NSquareSort class implements insertion sort on generic arrays
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/08/28
 *  Usage:  NSquareSort.nSquareSort(int[] arr)
 */

package cs6301.g39;

public class NSquareSort {
	static <T extends Comparable<? super T>> void nSquareSort(T[] arr) {
		int n = arr.length;
		for (int i = 1; i < n; i++) {
			T key = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j].compareTo(key) == 1) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
	}
}
