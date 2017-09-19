// Sample code for Level 1 driver for lp1

// Change following line to your group number
package cs6301.g39;

import java.util.LinkedList;

public class LP1L1 {
    public static void main(String[] args) {
	Num x = new Num(-65);
	Num y = new Num("-4");
	//System.out.println(Num.power(x,y));
	//Num.add(x, y).printList();
	//Num.subtract(x, y).printList();
	
	
	LinkedList<Integer> l1 = new LinkedList<Integer>();
	l1.add(1);
	l1.add(2);
	LinkedList<Integer> l2 = new LinkedList<Integer>(l1);
	l2.removeFirst();
	System.out.println(l1);
	System.out.println(l2);
	}
}

