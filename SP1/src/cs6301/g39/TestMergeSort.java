package cs6301.g39;

import cs6301.g00.ShuffleInt;
import cs6301.g00.Shuffle;
import cs6301.g00.Timer;

public class TestMergeSort {
	public static void main(String[] args) {
		int[] arint = new int[1000000];
		for(int i =0 ; i < 1000000; i++) {
			arint[i] = i;
		}
		ShuffleInt.shuffle(arint);
		int[] tmpint = new int[arint.length];
		
		// Starting merge sort on int
		Timer timer = new Timer();
		timer.start();
		MergeSort.mergeSort(arint, tmpint);
		timer.end();
		System.out.println("Time to sort array of int:");
		System.out.println(timer);
		//printArray(arint);

		
		Integer[] arrInt = new Integer[1000000];
		for(int i = 0; i<1000000; i++) {
			arrInt[i] = new Integer(i);
		}
		
		Shuffle.shuffle(arrInt);
		Integer[] tmpInt = new Integer[arrInt.length];

		// Starting merge sort on Integers
		timer.start();
		MergeSort.mergeSort(arrInt, tmpInt);
		timer.end();
		System.out.println("Time to sort array of Integers:");
		System.out.println(timer);
				

//		printArray(arrInt);
	}
	
	private static void printArray(int[] ar) {
		for(int i : ar) {
			System.out.println(i);
		}
	}
	
	private static<T extends Comparable<? super T>> void printArray(T[] ar) {
		for(T i : ar) {
			System.out.println(i);
		}
		
	}
}
