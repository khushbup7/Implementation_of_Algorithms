package cs6301.g39;


public class TestSortableList {
	public static void main(String[] args) {
		SortableList<Integer> s = new SortableList<>();
		s.add(5);
		s.add(6);
		s.add(2);
		s.add(9);
		s.add(1);
		s.add(0);
		SortableList.mergeSort(s);
		s.printList();
	}
	
	
}
