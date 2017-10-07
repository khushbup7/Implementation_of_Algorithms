/** SelectkLargets class contains methods that implement 3 versions of selecting K largest elements from an array
 * @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/28
 *  Ver 1.1: 2017/10/05
 *  Usage: SelectkLargest.selectUsingJavaPQ(int[] arr, int k)
 *  	   SelectkLargest.selectUsingCustomPQ(int[] arr, int k)
 * */

package cs6301.g39;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SelectkLargest<T> {
	
	/** a function that implements the algorithm of selecting k largest elements using priority queue provided by java 
	 * @param arr - An array
	 * @param k - No of largest elements to be selected from an array.
	 * @return - array of k largest elements of arr
	 */
	public static<T extends Comparable<? super T>> List<T> selectUsingJavaPQ(T[] arr, int k) {
		PriorityQueue<T> queue = new PriorityQueue<T>(k);
		int length = arr.length;

		if(k<=0)
			return null;
		if(k > length)
			return Arrays.asList(arr);
		
		int i;
		// priority queue(min-heap) contains only k elements
		for(i = 0; i< k; i++) 
			queue.add(arr[i]);
		
		//if a new element is larger than root(min element) then we remove the root and add the new element to the heap 
		for(; i < length; i++) {
			if(arr[i].compareTo(queue.peek()) > 0) {
				queue.remove();
				queue.add(arr[i]);
			}
		}
		List<T> kLargest = new ArrayList<T>();
		for( i = 0; i < k; i++)
			kLargest.add(queue.remove());
		return kLargest;
	}
	
	/** A function that implements the algorithm selecting k largest elements using custom priority queue implementation.
	 * Custom implementation is using Binary Heap (min heap). 
	 * @param arr - An array
	 * @param k - No of largest elements to be selected from an array.
	 * @return - array of k largest elements of arr
	 */
	public static<T extends Comparable<? super T>> List<T> selectUsingCustomPQ(T[] arr, int k, T[] temp, Comparator<T> comp) {
		
		int length = arr.length;

		if(k<=0)
			return null;
		if(k > length)
			return Arrays.asList(arr);
		
		int i;
		for(i = 0; i< temp.length;  i ++)
			temp[i] = arr[i];
		
		//create a priority queue using binary heap
		BinaryHeap<T> heap = new BinaryHeap<T>(temp, comp, k);
		heap.buildHeap();
		
		//if a new element is larger than root(min element) then we remove the root and add the new element to the heap 
		for(; i < length; i++) {
			if(arr[i].compareTo(heap.peek()) > 0) {
				heap.replace(arr[i]);    //remove + add operation is performed using single heap operation "replace"
			}
		}
		List<T> kLargest = new ArrayList<T>();
		for(i = 0; i < k; i++)
			kLargest.add(heap.remove());
		return kLargest;
	}
	
	/**
	 * @param pq - a priority queue
	 * @return - an array representation of priority queue 'pq'
	 */
	public static int[] toArray(PriorityQueue<Integer> pq) {
		int[] res = new int[pq.size()];
		int j =0;
		for(Integer i : pq)
			res[j++] = i;
		return res;
			
	}
}
