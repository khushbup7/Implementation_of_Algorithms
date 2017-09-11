package cs6301.g39;

import cs6301.g00.SinglyLinkedList;

public class SortableList<T extends Comparable<? super T>> extends SinglyLinkedList<T> {
    void merge(SortableList<T> otherList) { 
    	// Merge this list with other list
    	Entry<T> tail0 = this.head.getNext();
		Entry<T> tail1 = otherList.head.getNext();
		Entry<T> c = this.head;
		while (tail0!=null && tail1!= null){
			if(tail0.getElement().compareTo(tail1.getElement()) <=0 ) {
				c.setNext(tail0);
				tail0 = tail0.getNext();
			}
			else {
				c.setNext(tail1);
				tail1 = tail1.getNext();
			}
			c = c.getNext();
		}
		if(tail0 == null)
			c.setNext(tail1);
		else c.setNext(tail0);
		
    }
    void mergeSort() { 
    	//Sort this list
    	if(this.size < 2)
    		return;
    	int middle = this.size/2;
    	int c = 0;
    	
    	Entry<T> tail0 = head;
		
    	while(c < middle) {
    		tail0 = tail0.getNext();
    		c++;
    	}
    	Entry<T> head1 = tail0.getNext();
    	tail0.setNext(null);
		
		SortableList<T> second = new SortableList<T>();
		second.head.setNext(head1);
		
		
		second.size = this.size - middle;
		this.size = middle;
		this.mergeSort();
		second.mergeSort();
		this.merge(second);
		this.size += second.size;
		
		
    }
    public static<T extends Comparable<? super T>> void mergeSort(SortableList<T> list) {
    	list.mergeSort();
    }
}
