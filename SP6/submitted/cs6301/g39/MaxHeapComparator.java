/** Class to implement comparator for max heap for INtegers
 * @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 * Ver 1.0: 2017/10/04
*/

package cs6301.g39;

import java.util.Comparator;

public class MaxHeapComparator implements Comparator<Integer> {
	// For a max heap, elements need to be sorted in ascending order

	@Override
	public int compare(Integer a, Integer b) {
		if (a < b)
			return -1;
		else if (a > b)
			return 1;
		return 0;
	}
}
