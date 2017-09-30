/** SelectkLargets class contains methods that implement 3 versions of selecting K largest elements from an array
 * @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/28
 *  Ver 1.1: 2017/09/29
 *  Usage: SelectkLargest.select1(int[] arr, int k)
 *  	   SelectkLargest.select2(int[] arr, int k)
 *  	   SelectkLargest.select3(int[] arr, int k) 
 * */

package cs6301.g39;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

public class SelectkLargest {
	
	/** a function that implements the algorithm using priority queue with max-heap
	 * @param arr - An array
	 * @param k - No of largest elements to be selected from an array.
	 * @return - array of k largest elements of arr
	 */
	public static int[] select1(int[] arr, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(arr.length, Collections.reverseOrder());
		for(int i : arr)
			queue.add(i);
		
		// k largest elements can be found by performing k remove() operations on max-heap
		int[] res = new int[k];
		for(int i = 0; i < k; i++) {
			res[i] = queue.remove();
		}
		return res;
	}
	
	/** a function that implements the algorithm using priority queue with min-heap
	 * @param arr - An array
	 * @param k - No of largest elements to be selected from an array.
	 * @return - array of k largest elements of arr
	 */
	public static int[] select2(int[] arr, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k);
		int length = arr.length;

		if(k > length)
			return arr;
		
		int i;
		// priority queue(min-heap) contains only k elements
		for(i = 0; i< k; i++) 
			queue.add(arr[i]);
		
		//if a new element is larger than root(min element) then we remove the root and add the new element to the heap 
		for(; i < length; i++) {
			if(arr[i] > queue.peek()) {
				queue.remove();
				queue.add(arr[i]);
			}
		}
		return toArray(queue);
	}
	
	/** a function that implements the O(n) select algorithm
	 * @param arr - An array
	 * @param k - No of largest elements to be selected from an array.
	 * @return - array of k largest elements of arr
	 */
	public static int[] select3(int[] arr, int k) {
		int n = arr.length;
		if(k<=0)
			return null;
		if(k>n)
			return arr;
		
		select(arr, 0, n, k);
		
		// k largest elements are at arr[n-k .... n-1]
		int[] res = new int[k];
		int j = 0;
		for(int i = n-k; i < n; i++) {
			res[j++] = arr[i];
		}
		return res;
	}
	
	/** Helper select function for O(n) select algorithm
	 * @param arr - an array of integers
	 * @param p - start index
	 * @param n - end index
	 * @param k - kth largest element is to be found
	 * @return - kth largest element of arr[ p....p+n-1]
	 */
	public static int select(int[] arr, int p, int n, int k) {
		int r = p+n-1;
		
		//if n is less than 13, use insertion sort to sort the array
		if(n<=13) {
			NSquareSort.nSquareSort(arr, p, r);
			return arr[p+n-k];
		}
		else {
			int q = partition(arr, p, r);  //returns the index of the pivot element
			int left = q-p;
			int right = r-q;
			
			// kth largest element of arr[p...r] is also kth largest of arr[q+1...r]
			if(right >= k)
				return select(arr, q+1, right, k);
			//Pivot element happens to be kth largest element 
			else if(right+1 == k)
				return arr[q];
			//kth largest in arr[p...r] is [k+(right-1)]th largest in A[p...q-1]
			else
				return select(arr,p,left, k-(right+1));
		}
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
	
	/** function to partition an array in two parts based on a random pivot
	 * @param arr - an array of integers
	 * @param p - begin index
	 * @param r - end index
	 * @return - an index of pivot element in the array
	 */
	public static int partition(int[] arr, int p, int r) {
		Random rand = new Random();
		int i = rand.nextInt(r - p + 1) + p;
		swap(arr, i, r);
		int x = arr[r];
		i = p - 1;
		for (int j = p; j <= r - 1; j++) {
			if (arr[j] < x) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, r);
		return i + 1;
	}

	/** function to swap elements at two indexes in an array
	 * @param arr - an array of integers
	 * @param i , r - indexes to be swapped in arr
	 */
	private static void swap(int[] arr, int i, int r) {
		int temp = arr[i];
		arr[i] = arr[r];
		arr[r] = temp;

	}
}
