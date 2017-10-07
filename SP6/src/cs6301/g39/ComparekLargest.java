/** Driver Class for SP6 - Q8. Comparing various implementation for selecting k-largest
 * @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 * Ver 1.0: 2017/10/05
*/

package cs6301.g39;

import java.util.Arrays;
import java.util.List;

import cs6301.g00.Shuffle;
import cs6301.g00.Timer;

public class ComparekLargest {
	public static void main(String[] args) {
		int n = 25000000;
		int k = 3000;
		
		if(args.length > 0) {
			n = Integer.parseInt(args[0]);
			k = Integer.parseInt(args[1]);
		}
		
		Integer[] arrInt = new Integer[n];
		for (int i = 0; i < n; i++) {
			arrInt[i] = new Integer(i);
		}
		
		Timer timer = new Timer();
		
		// Selecting k largest elements from increasing ordered integers by using Java's priority queue
		timer.start();
		List<Integer> kLargest = SelectkLargest.selectUsingJavaPQ(arrInt, k);
		timer.end();
		System.out.println("Time to select " + k + " largest from increasing ordered integers (" + n + ") by using Java's PQ:");
		System.out.println(timer);
		//for(Integer i: kLargest)
			//System.out.print(i + " ");
		
		Shuffle.shuffle(arrInt);
		
		// Selecting k largest elements from randomly sorted integers by using Java's priority queue
		timer = new Timer();
		timer.start();
		kLargest = SelectkLargest.selectUsingJavaPQ(arrInt, k);
		timer.end();
		System.out.println("Time to select " + k + " largest from randomly sorted integers (" + n + ") by using Java's PQ:");
		System.out.println(timer);
		//for(Integer i: kLargest)
			//System.out.print(i + " ");
		
		System.out.println();
		
		// Selecting k largest elements from increasing ordered integers by using custom implementation of priority queue
		Arrays.sort(arrInt);
		timer = new Timer();
		timer.start();
		Integer[] temp = new Integer[k];
		kLargest = SelectkLargest.selectUsingCustomPQ(arrInt, k, temp, new MaxHeapComparator());
		timer.end();
		System.out.println("Time to select " + k + " largest from increasing ordered integers (" + n + ") by custom PQ:");
		System.out.println(timer);
		//for(Integer i: kLargest)
			//System.out.print(i + " ");
		
		Shuffle.shuffle(arrInt);
		
		// Selecting k largest elements from randomly sorted integers by using custom implementation of priority queue
		kLargest = null;
		temp = new Integer[k];
		timer = new Timer();
		timer.start();
		kLargest = SelectkLargest.selectUsingCustomPQ(arrInt, k, temp, new MaxHeapComparator());
		timer.end();
		System.out.println("Time to select " + k + " largest from randomly sorted integers (" + n + ") by using custom PQ:");
		System.out.println(timer);
		//for(Integer i: kLargest)
			//System.out.print(i + " ");
				
	}
}
