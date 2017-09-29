package cs6301.g39;

import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

public class SelectkLargest {
	public static int[] select1(int[] arr, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(arr.length, Collections.reverseOrder());
		for(int i : arr)
			queue.add(i);
		
		int[] res = new int[k];
		for(int i = 0; i < k; i++) {
			res[i] = queue.remove();
		}
		return res;
	}
	
	public static int[] select2(int[] arr, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k);
		int length = arr.length;

		if(k > length)
			return arr;
		
		int i;
		for(i = 0; i< k; i++) 
			queue.add(arr[i]);
		
		for(; i < length; i++) {
			if(arr[i] > queue.peek()) {
				queue.remove();
				queue.add(arr[i]);
			}
		}
		return toArray(queue);
	}
	
	public static int[] toArray(PriorityQueue<Integer> pq) {
		int[] res = new int[pq.size()];
		int j =0;
		for(Integer i : pq)
			res[j++] = i;
		return res;
			
	}
}
