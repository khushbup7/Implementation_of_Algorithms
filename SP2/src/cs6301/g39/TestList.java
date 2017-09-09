/** Test class for testing the ListSetOperations class
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  
 */
package cs6301.g39;

import java.util.LinkedList;
import java.util.List;

public class TestList {
	public static void main(String args[]) {
		List<Integer> l1 = new LinkedList<Integer>();
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(6);
		l1.add(9);
		List<Integer> l2 = new LinkedList<Integer>();
		l2.add(4);
		l2.add(5);
		l2.add(7);
		l2.add(8);
		List<Integer> outList = new LinkedList<Integer>();
		ListSetOperations.union(l1, l2, outList);
		System.out.println(outList);
		outList = new LinkedList<Integer>();
		ListSetOperations.intersect(l1, l2, outList);
		System.out.println(outList);
		outList = new LinkedList<Integer>();
		ListSetOperations.difference(l1, l2, outList);
		System.out.println(outList);
	}

}
