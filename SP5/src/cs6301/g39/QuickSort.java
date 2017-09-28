package cs6301.g39;

import java.util.Random;

public class QuickSort {

	public int partition(int[] arr, int p, int r) {
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

	private void swap(int[] arr, int i, int r) {
		int temp = arr[i];
		arr[i] = arr[r];
		arr[r] = temp;

	}

	void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	void quickSort(int[] arr, int p, int r) {
		if (p < r) {
			int q = partition(arr, p, r);
			quickSort(arr, p, q);
			quickSort(arr, q + 1, r);
		}
	}

	public int partition1(int[] arr, int p, int r) {
		Random rand = new Random();
		int i = rand.nextInt(r - p + 1) + p;
		int x = arr[i];
		i = p ;
		int j = r;

		while (true) {
			while (arr[i] < x)
				i++;
			while (arr[j] > x)
				j--;
			if (i >= j)
				return j;
			swap(arr, i, j);
			i++;
			j--;
		}
	}

	void quickSort1(int[] arr) {
		quickSort1(arr, 0, arr.length - 1);
	}

	void quickSort1(int[] arr, int p, int r) {
		if (p < r) {
			int q = partition1(arr, p, r);
			quickSort(arr, p, q);
			quickSort(arr, q, r);
		}
	}

}
