package cs6301.g39;

import cs6301.g00.Shuffle;

public class TestSelection {
	public static void main(String[] args) {
		int n = 20000;

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = i;
		}
		Shuffle.shuffle(arr);
		
		for(int i : SelectkLargest.select1(arr, 10))
			System.out.print(" " + i);
		
		System.out.println();
		Shuffle.shuffle(arr);
		
		for(int i : SelectkLargest.select2(arr, 10))
			System.out.print(" " + i);
	}
}
