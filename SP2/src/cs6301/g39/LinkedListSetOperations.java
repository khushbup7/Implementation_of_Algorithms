package cs6301.g39;

import java.util.Iterator;
import java.util.List;

public class LinkedListSetOperations {
	 public static<T extends Comparable<? super T>> void intersect(List<T> l1, List<T> l2, List<T> outList) {
		 Iterator<T> it1 = l1.iterator();
		 Iterator<T> it2 = l2.iterator();
		 T next1 = it1.next();
		 T next2 = it2.next();
		 while(next1 != null && next2 != null) {
			 if(next1.compareTo(next2) < 0) {
				 next1 = it1.hasNext()? it1.next() : null;
			 }
			 else if(next1.compareTo(next2) > 0) {
				 next2 = it2.hasNext()? it2.next() : null;
			 }
			 else if(next1.compareTo(next2) == 0) {
				 outList.add(next1);
				 next1 = it1.hasNext()? it1.next() : null;
				 next2 = it2.hasNext()? it2.next() : null;
			 }
		 }
	 }
	 
	 public static<T extends Comparable<? super T>> void union(List<T> l1, List<T> l2, List<T> outList) {
		 Iterator<T> it1 = l1.iterator();
		 Iterator<T> it2 = l2.iterator();
		 T next1 = it1.next();
		 T next2 = it2.next();
		 while(next1 != null && next2 != null) {
			 
			 if(next1.compareTo(next2) < 0) {
				 outList.add(next1);
				 next1 = it1.hasNext()? it1.next() : null;
			 }
			 else if(next1.compareTo(next2) > 0) {
				 outList.add(next2);
				 next2 = it2.hasNext()? it2.next() : null;
			 }
			 else if(next1.compareTo(next2) == 0) {
				 outList.add(next1);
				 next1 = it1.hasNext()? it1.next() : null;
				 next2 = it2.hasNext()? it2.next() : null;
			 }
		 }
		 while(next1 != null) {
			 outList.add(next1);
			 next1 = it1.hasNext()? it1.next() : null;
		 }
		 while(next2 != null) {
			 outList.add(next2);
			 next2 = it2.hasNext()? it2.next() : null;
		 }
	 }
	 
	 public static<T extends Comparable<? super T>> void difference(List<T> l1, List<T> l2, List<T> outList) {
		 Iterator<T> it1 = l1.iterator();
		 Iterator<T> it2 = l2.iterator();
		 T next1 = it1.next();
		 T next2 = it2.next();
		 while(next1 != null && next2 != null) {
			 
			 if(next1.compareTo(next2) < 0) {
				 outList.add(next1);
				 next1 = it1.hasNext()? it1.next() : null;
			 }
			 else if(next1.compareTo(next2) > 0) {
				 next2 = it2.hasNext()? it2.next() : null;
			 }
			 else if(next1.compareTo(next2) == 0) {
				 next1 = it1.hasNext()? it1.next() : null;
				 next2 = it2.hasNext()? it2.next() : null;
			 }
		 }
		 while(next1 != null) {
			 outList.add(next1);
			 next1 = it1.hasNext()? it1.next() : null;
		 }
	}

}
