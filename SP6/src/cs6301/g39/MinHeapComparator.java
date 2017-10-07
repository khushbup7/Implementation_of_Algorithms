/** Class to implement comparator for min heap
 * @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 * Ver 1.0: 2017/10/04
*/

package cs6301.g39;

import java.util.Comparator;

public class MinHeapComparator implements Comparator<Integer> {
	// For a min heap, elements need to be sorted in descending order

	@Override
	public int compare(Integer a, Integer b) {
		if (a > b)
			return -1;
		else if (a < b)
			return 1;
		return 0;
	}
	
}
