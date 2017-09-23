/** Test class to test the different algorithms for mergesort
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/23
 */
package cs6301.g39;

import cs6301.g00.Shuffle;
import cs6301.g00.Timer;

public class TestMergeSort<T> {
	
	static int n = 22000000;
	public static void main(String[] args) {
		int[] arint = new int[n];
		initializeArray(arint);
		
		// Starting merge sort - Algorithm 1
		Shuffle.shuffle(arint);
		Timer timer = new Timer();
		timer.start();
		MergeSort1.mergeSort(arint);
		timer.end();
		System.out.println("Time to merge sort array of int (" + n + ") by Algorithm 1:");
		System.out.println(timer);
		
		// Starting merge sort - Algorithm 2
		Shuffle.shuffle(arint);
		timer = new Timer();
		int[] tmp = new int[n];
		timer.start();
		MergeSort2.mergeSort(arint, tmp);
		timer.end();
		System.out.println("Time to merge sort array of int (" + n + ") by Algorithm 2:");
		System.out.println(timer);
		
		// Starting merge sort - Algorithm 3
		Shuffle.shuffle(arint);
		timer = new Timer();
		tmp = new int[n];
		timer.start();
		MergeSort3.mergeSort(arint, tmp);
		timer.end();
		System.out.println("Time to merge sort array of int (" + n + "): by Algorithm 3");
		System.out.println(timer);
		
		// Starting merge sort - Algorithm 3
		Shuffle.shuffle(arint);
		timer = new Timer();
		tmp = new int[n];
		timer.start();
		MergeSort4.mergeSort(arint);
		timer.end();
		System.out.println("Time to merge sort array of int (" + n + "): by Algorithm 4");
		System.out.println(timer);
		
	}
	
	static void initializeArray(int[] a) {
		for (int i = 0; i < n; i++) {
			a[i] = i;
		}
	}
	
}
