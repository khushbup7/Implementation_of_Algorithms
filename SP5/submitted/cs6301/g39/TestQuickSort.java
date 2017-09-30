/** Class to test QuickSort class for two algorithms of Partition
 * @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/28
*/

package cs6301.g39;

import cs6301.g00.Shuffle;
import cs6301.g00.Timer;

public class TestQuickSort {
	public static void main(String[] args) {
		QuickSort q = new QuickSort();
		int n = 20000;
		if(args.length > 0) {n = Integer.parseInt(args[0]);}
		
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = i;
		}
		Shuffle.shuffle(arr);
		
		Timer timer = new Timer();
		timer.start();
		q.quickSort(arr);
		timer.end();
		System.out.println("Time to quick sort random array of int (" + n + ") by Partition1:");
		System.out.println(timer);
		
		Shuffle.shuffle(arr);
		
		timer.start();
		q.quickSort1(arr);
		timer.end();
		System.out.println("Time to quick sort random array of int (" + n + ") by Partition2:");
		System.out.println(timer);
		
		
		for (int i = n-1; i >=0 ; i--) {
			arr[i] = i;
		}
		
		timer.start();
		q.quickSort(arr);
		timer.end();
		System.out.println("Time to quick sort descending array of int (" + n + ") by Partition1:");
		System.out.println(timer);
		
		int j = 0;
		for (int i = n-1; i >=0 ; i--) {
			arr[j] = i;
			j++;
		}
		
		timer.start();
		q.quickSort1(arr);
		timer.end();
		System.out.println("Time to quick sort descending array of int (" + n + ") by Partition2:");
		System.out.println(timer);
		
	}

//	private static void printArray(int[] arr) {
//		for(int i : arr) {
//			System.out.print(" " + i);
//		}
//	}
}
