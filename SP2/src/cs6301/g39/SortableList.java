package cs6301.g39;

import java.util.Iterator;

import cs6301.g00.SinglyLinkedList;

public class SortableList<T extends Comparable<? super T>> extends SinglyLinkedList<T> {
    void merge(SortableList<T> otherList) { 
    	// Merge this list with other list
    }
    void mergeSort() { 
    	//Sort this list
    	Iterator head = this.iterator();
    }
    public static<T extends Comparable<? super T>> void mergeSort(SortableList<T> list) {
    	list.mergeSort();
    }
}
