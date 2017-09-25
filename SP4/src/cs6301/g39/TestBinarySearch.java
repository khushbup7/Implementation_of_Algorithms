/** Class to test Binary Search
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/23
 */

package cs6301.g39;

public class TestBinarySearch {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,9,10,12,16,18,21};
		Integer[] arr1 = new Integer[arr.length];
		for(int i=0; i< arr.length;i++)
			arr1[i] = arr[i];
		
		Integer x = 11;
		System.out.println(BinarySearch.binarySearch(arr1, x));
		
		
	}
}
