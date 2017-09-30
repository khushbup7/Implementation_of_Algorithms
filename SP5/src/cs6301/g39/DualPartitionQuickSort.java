package cs6301.g39;

import java.util.Random;

public class DualPartitionQuickSort {

	public int[] dualPivotPartition(int[] arr, int p, int r) {
		int[] partitionIndex = new int[2];
		Random rand = new Random();
		int ind1 = rand.nextInt(r - p + 1) + p ;
		
		int ind2 = 0;
		while(true) {
			rand = new Random();
			ind2 = rand.nextInt(r - p + 1) + p ;
			if(ind2!=ind1)
				break;
		}

		if (arr[ind1] < arr[ind2]) {
			swap(arr, ind1, p);
			swap(arr, ind2, r);
		}
		else {
			swap(arr, ind1, r);
			swap(arr, ind2, p);
		}
		
		int x1 = arr[p];
		int x2 = arr[r];

		int k = p + 1;
		int i = p + 1;
		int j = r - 1;

		while (i <= j) {
			if (x1 <= arr[i] && arr[i] <= x2)
				i++;
			else if (arr[i] < x1) {
				swap(arr, i, k);
				k++;
				i++;
			} else if (arr[j] > x2)
				j--;
			else if (arr[i] > x2 && arr[j] < x1) {
				if(i==k)	
					swap(arr,k,j);
				else {
					int temp1 = arr[k];
					arr[k] = arr[j];
					arr[j] = arr[i];
					arr[i] = temp1;
				}	
				k++;
				i++;
				j--;
			} 
			else if (arr[i] > x2 && x1 <= arr[j] && arr[j] <= x2) {
				swap(arr, i, j);
				i++;
				j--;
			} 
			else {

			}
		}

		swap(arr, p, k - 1);
		swap(arr, j + 1, r);
		
		partitionIndex[0] = k;
		partitionIndex[1] = j;
		
		return partitionIndex;
	}

	private void swap(int[] arr, int i, int r) {
		int temp = arr[i];
		arr[i] = arr[r];
		arr[r] = temp;

	}
	
	void dpQuickSort(int[] arr) {
		dpQuickSort(arr, 0, arr.length - 1);
	}

	void dpQuickSort(int[] arr, int p, int r) {
		if (p < r) {
			int[] q = dualPivotPartition(arr, p, r);
			dpQuickSort(arr, p, q[0]-2);
			dpQuickSort(arr, q[1]+2, r);
			if(arr[q[0]-1] != arr[q[1]+1])
				dpQuickSort(arr, q[0], q[1]);
		}

	}

}
