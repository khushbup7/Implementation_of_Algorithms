/** MergeSort class which implements mergesort on an integer array as well as generic arrays
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/08/26
 *  Usage:  MergeSort.mergeSort(int[] arr, int[] tmp)
 *  		MergeSort.mergeSort(T[] arr, T[] tmp)
 */
package cs6301.g39;

public class MergeSort {

	// A. Merge Sort on a array of integers.
	static void mergeSort(int[] arr, int[] tmp) {
		mergeSortDivide(arr, 0, arr.length-1, tmp);
	}
	
	//A1. Merge Sort - Helper on a array of integers.
	static void mergeSortDivide(int[] arr, int left, int right, int[] tmp) {
		
		if(left < right) {
			int mid = (left + right)/2;
			mergeSortDivide(arr, left, mid, tmp);
			mergeSortDivide(arr, mid + 1, right, tmp);
			merge(arr, left, mid, right, tmp);
		}
	}
	
	//A1. Merge Sort - Helper on a array of integers.
	private static void merge(int[] arr, int left, int mid, int right, int[] tmp) {
		int i = left;
		int j = mid + 1;
		int index = left;
		while(i <= mid && j <= right) {
			if(arr[i] <= arr[j]) 
				tmp[index++] = arr[i++];
			else tmp[index++] = arr[j++];
		}
		while(i <= mid) {
			tmp[index++] = arr[i++];
		}
		while(j <= right) {
			tmp[index++] = arr[j++];
		}
		for(int ct = left; ct <= right; ct++) {
			arr[ct] = tmp[ct];
		}
	}

	// B. Merge Sort on a generic array of Integers.
	static <T extends Comparable<? super T>> void mergeSort(T[] arr, T[] tmp) {
		mergeSortDivide(arr, 0, arr.length-1, tmp);
	}
	
	
	//A1. Merge Sort - Helper on a array of integers.
		static <T extends Comparable<? super T>> void mergeSortDivide(T[] arr, int left, int right, T[] tmp) {
			
			if(left < right) {
				int mid = (left + right)/2;
				mergeSortDivide(arr, left, mid, tmp);
				mergeSortDivide(arr, mid + 1, right, tmp);
				merge(arr, left, mid, right, tmp);
			}
		}
		
		//A1. Merge Sort - Helper on a array of integers.
		private static<T extends Comparable<? super T>> void merge(T[] arr, int left, int mid, int right, T[] tmp) {
			int i = left;
			int j = mid + 1;
			int index = left;
			while(i <= mid && j <= right) {
				if(arr[i].compareTo(arr[j]) == -1) 
					tmp[index++] = arr[i++];
				else tmp[index++] = arr[j++];
			}
			while(i <= mid) {
				tmp[index++] = arr[i++];
			}
			while(j <= right) {
				tmp[index++] = arr[j++];
			}
			for(int ct = left; ct <= right; ct++) {
				arr[ct] = tmp[ct];
			}
		}
	
}
