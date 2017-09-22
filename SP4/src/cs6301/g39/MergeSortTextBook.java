package cs6301.g39;

public class MergeSortTextBook {
	static void mergeSort(int[] arr) {

		mergeSort(arr, 0, arr.length - 1);
	}

	static void mergeSort(int[] arr, int left, int right) {

		if (left < right) {
			int mid = (left + right) / 2; // find middle index
			mergeSort(arr, left, mid); // merge sort on the first
											// half of the array
			mergeSort(arr, mid + 1, right); // merge sort on the
													// second half of the
													// array
			merge(arr, left, mid, right); // merge first and second half of
												// the array
		}
	}
	
	private static void merge(int[] arr, int left, int mid, int right) {

		int index = left;
		int n1 = mid - left + 1;
		int n2 = right - mid;
		/* create temp arrays */
		int L[] = new int [n1];
        int R[] = new int [n2];
	 
	    /* Copy data to temp arrays L[] and R[] */
	    for (int i = 0; i < n1; i++)
	        L[i] = arr[left + i];
	    for (int j = 0; j < n2; j++)
	        R[j] = arr[mid + 1+ j];
	    
	    int i = 0, j = 0;
	    
        // Initial index of merged subarry array
        int k = left;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
	}
}
