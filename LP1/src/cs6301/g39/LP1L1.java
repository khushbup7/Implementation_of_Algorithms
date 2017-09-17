// Sample code for Level 1 driver for lp1

// Change following line to your group number
package cs6301.g39;


public class LP1L1 {
    public static void main(String[] args) {
		Num x = new Num(-589);
		
		Num y = new Num("-8976");
		
		System.out.println(x.compareTo(y));
		Num.add(x, y).printList();
		
		Num.subtract(x, y).printList();
    }
}

