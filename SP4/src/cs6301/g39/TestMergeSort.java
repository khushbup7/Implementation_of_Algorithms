package cs6301.g39;

import cs6301.g00.Shuffle;
import cs6301.g00.Timer;

public class TestMergeSort<T> {
	
	static int n = 20;
	public static void main(String[] args) {
		int[] arint = new int[n];
		initializeArray(arint);
		
		// Starting merge sort - Algorithm 1
		Shuffle.shuffle(arint);
		Timer timer = new Timer();
		timer.start();
		MergeSortTextBook.mergeSort(arint);
		timer.end();
//		System.out.println("Time to merge sort array of int (" + n + "):");
//		System.out.println(timer);
		
		// Starting merge sort - Algorithm 2
		Shuffle.shuffle(arint);
		timer = new Timer();
		int[] tmp = new int[n];
		timer.start();
		MergeSort2.mergeSort(arint, tmp);
		timer.end();
//		System.out.println("Time to merge sort array of int (" + n + "):");
//		System.out.println(timer);
		
		// Starting merge sort - Algorithm 3
		Shuffle.shuffle(arint);
		timer = new Timer();
		tmp = new int[n];
		timer.start();
		MergeSort3.mergeSort(arint, tmp);
		timer.end();
		System.out.println("Time to merge sort array of int (" + n + "):");
		System.out.println(timer);
		
		
		
//		for(int a : arint)
//			System.out.print(" " + a);
				
	}
	
	static void initializeArray(int[] a) {
		for (int i = 0; i < n; i++) {
			a[i] = i;
		}
	}
	
}
