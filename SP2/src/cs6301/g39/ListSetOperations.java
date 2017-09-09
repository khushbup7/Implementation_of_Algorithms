/** Lists implementing sorted sets, functions for union, intersection, and set difference of the sets.
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/06 Initial class
 *  Ver 1.1: 2017/09/08 Created next method for easy and fail safe version of iterator.next()
 *  Usage:  ListSetOperations.intersect(List<T> l1, List<T> l2, List<T> outList)
 *          ListSetOperations.union(List<T> l1, List<T> l2, List<T> outList)
 *          ListSetOperations.difference(List<T> l1, List<T> l2, List<T> outList)
 */
package cs6301.g39;

import java.util.Iterator;
import java.util.List;

public class ListSetOperations {
	public static <T extends Comparable<? super T>> void intersect(List<T> l1, List<T> l2, List<T> outList) {
		Iterator<T> it1 = l1.iterator();
		Iterator<T> it2 = l2.iterator();
		T next1 = it1.next();
		T next2 = it2.next();
		while (next1 != null && next2 != null) {
			if (next1.compareTo(next2) < 0) {
				next1 = next(it1);
			} else if (next1.compareTo(next2) > 0) {
				next2 = next(it2);
			} else if (next1.compareTo(next2) == 0) {
				outList.add(next1);
				next1 = next(it1);
				next2 = next(it2);
			}
		}
	}

	public static <T extends Comparable<? super T>> void union(List<T> l1, List<T> l2, List<T> outList) {
		Iterator<T> it1 = l1.iterator();
		Iterator<T> it2 = l2.iterator();
		T next1 = it1.next();
		T next2 = it2.next();
		while (next1 != null && next2 != null) {

			if (next1.compareTo(next2) < 0) {
				outList.add(next1);
				next1 = next(it1);
			} else if (next1.compareTo(next2) > 0) {
				outList.add(next2);
				next2 = next(it2);
			} else if (next1.compareTo(next2) == 0) {
				outList.add(next1);
				next1 = next(it1);
				next2 = next(it2);
			}
		}
		while (next1 != null) {
			outList.add(next1);
			next1 = next(it1);
		}
		while (next2 != null) {
			outList.add(next2);
			next2 = next(it2);
		}
	}

	public static <T extends Comparable<? super T>> void difference(List<T> l1, List<T> l2, List<T> outList) {
		Iterator<T> it1 = l1.iterator();
		Iterator<T> it2 = l2.iterator();
		T next1 = it1.next();
		T next2 = it2.next();
		while (next1 != null && next2 != null) {

			if (next1.compareTo(next2) < 0) {
				outList.add(next1);
				next1 = next(it1);
			} else if (next1.compareTo(next2) > 0) {
				next2 = next(it2);
			} else if (next1.compareTo(next2) == 0) {
				next1 = next(it1);
				next2 = next(it2);
			}
		}
		while (next1 != null) {
			outList.add(next1);
			next1 = next(it1);
		}
	}

	public static <T> T next(Iterator<T> it) {
		return it.hasNext() ? it.next() : null;

	}

}
