package cs6301.g39;

import java.util.PriorityQueue;

public class kWayMerge {
	public static void mergeSort(int[] arr, int k) {
		
		int[] temp = new int[arr.length];
		int fragmentSize = arr.length /k;
		int st = 0;
		for(int i = 0; i < k; i++) {
			MergeSort.mergeSort(arr, st, st + fragmentSize, temp);
			st = st + fragmentSize;
		}
		
		
		//PriorityQueue<Integer, Integer> pq = new PriorityQueue<Integer, Integer>();
	}
}
