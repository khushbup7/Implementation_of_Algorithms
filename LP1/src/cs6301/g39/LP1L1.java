/** Driver class for Lp1 - Level 1
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/20
 */

// Change following line to your group number
package cs6301.g39;

public class LP1L1 {
    public static void main(String[] args) {
    	Num x = new Num("98765432123456789012456789012646378589165127456376");
    	Num y = new Num("56698364876147630847612984618476284587653095761286");
    	Num z = new Num(98765432123456789L);
    	Num e = new Num("85849037612648764376549098612765874365348765673543");
    	Num f = new Num("566983648761476308476145");
    	Num c = Num.subtract(x, y);
    	Num d = Num.product(c, z);
    	Num g = Num.subtract(f, e);
    	Num a = Num.power(z, 8);
    	System.out.println(c);
    	System.out.println(d);
    	System.out.println(g);
    	System.out.println(a);
    	z.printList();
    }
}
