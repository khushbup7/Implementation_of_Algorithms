// Sample code for Level 1 driver for lp1

// Change following line to your group number
package cs6301.g39;


public class LP1L1 {
    public static void main(String[] args) {
	Num x = new Num(-656532);
	Num y = new Num("-34758");
	System.out.println(Num.product(x,y));
	System.out.println(Num.product(x,y).sign);
    }
}

