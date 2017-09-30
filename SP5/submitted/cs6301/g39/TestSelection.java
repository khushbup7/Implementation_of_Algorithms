/** Driver class for SP5 - Problem 3
 * It is for testing methods that implement 3 versions of selecting K largest elements from an array
 * @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/29
 * */

package cs6301.g39;

import cs6301.g00.Shuffle;
import cs6301.g00.Timer;

public class TestSelection {
	public static void main(String[] args) {
		int n = 30000000;
		int k = 10;

		if(args.length > 0) { 
			n = Integer.parseInt(args[0]); 
			k = Integer.parseInt(args[1]);
		}
		
		int[] arr = new int[n];
		int[] result =  new int[k];
		for (int i = 0; i < n; i++) {
			arr[i] = i;
		}
		
		Shuffle.shuffle(arr);
		
		Timer timer = new Timer();
		timer.start();
		result = SelectkLargest.select1(arr, k);
		timer.end();
		System.out.println("Time to select " + k + " elements from random array of int (" + n + ") by algorithm-1(max-heap):");
		System.out.println(timer);
		for(int i : result)
			System.out.print(" " + i);
		
		System.out.println();
		System.out.println();
		Shuffle.shuffle(arr);
		
		
		timer.start();
		result = SelectkLargest.select2(arr, k);
		timer.end();
		System.out.println("Time to select " + k + " elements from random array of int (" + n + ") by algorithm-2(min-heap):");
		System.out.println(timer);
		for(int i : result)
			System.out.print(" " + i);
		
		System.out.println();
		System.out.println();
		Shuffle.shuffle(arr);
		
		timer.start();
		result = SelectkLargest.select3(arr, k);
		timer.end();
		System.out.println("Time to select " + k + " elements from random array of int (" + n + ") by algorithm-3(O(n) select algorithm):");
		System.out.println(timer);
		for(int i : result)
			System.out.print(" " + i);
	}
}
