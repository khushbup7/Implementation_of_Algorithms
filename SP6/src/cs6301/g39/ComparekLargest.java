package cs6301.g39;

import java.util.Arrays;
import java.util.List;

import cs6301.g00.Shuffle;
import cs6301.g00.Timer;

public class ComparekLargest {
	public static void main(String[] args) {
		int n = 2000000;
		int k = 2000;
		Integer[] arrInt = new Integer[n];
		for (int i = 0; i < n; i++) {
			arrInt[i] = new Integer(i);
		}
		
		Timer timer = new Timer();
		timer.start();
		List<Integer> kLargest = SelectkLargest.selectUsingJavaPQ(arrInt, k);
		timer.end();
		System.out.println("Time to select " + k + " largest from increasing ordered integers (" + n + ") by using Java's PQ:");
		System.out.println(timer);
		
		Shuffle.shuffle(arrInt);
		
		timer = new Timer();
		timer.start();
		kLargest = SelectkLargest.selectUsingJavaPQ(arrInt, k);
		timer.end();
		System.out.println("Time to select " + k + " largest from randomly sorted integers (" + n + ") by using Java's PQ:");
		System.out.println(timer);
//		for(Integer i: kLargest)
//			System.out.print(i + " ");
		
		System.out.println();
		
		Arrays.sort(arrInt);
		timer = new Timer();
		timer.start();
		Integer[] temp = new Integer[k];
		kLargest = SelectkLargest.selectUsingCustomPQ(arrInt, k, temp, new MaxHeapComparator());
		timer.end();
		System.out.println("Time to select " + k + " largest from increasing ordered integers (" + n + ") by custom PQ:");
		System.out.println(timer);
		
		Shuffle.shuffle(arrInt);
		
		kLargest = null;
		temp = new Integer[k];
		timer = new Timer();
		timer.start();
		kLargest = SelectkLargest.selectUsingCustomPQ(arrInt, k, temp, new MaxHeapComparator());
		timer.end();
		System.out.println("Time to select " + k + " largest from randomly sorted integers (" + n + ") by using custom PQ:");
		System.out.println(timer);
		
//		for(Integer i : kLargest)
//			System.out.print(i + " ");
		
	}
}
