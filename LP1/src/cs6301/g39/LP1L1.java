// Sample code for Level 1 driver for lp1

// Change following line to your group number
package cs6301.g39;

import java.math.BigInteger;

public class LP1L1 {
    public static void main(String[] args) {
    /*	
	Num x = new Num(999);
	Num y = new Num("8");
	Num z = Num.add(x, y);
	System.out.println(z);
	Num a = Num.power(x, 8);
	System.out.println(a);
	z.printList();
	*/
    	
    	String a = "9223372036854775808";
    	BigInteger n = new BigInteger(a,2);
    	//System.out.println(Long.toString(a, 2).length());
    	System.out.println(n);
    	
    }
}
