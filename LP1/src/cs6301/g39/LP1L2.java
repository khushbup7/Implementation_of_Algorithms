/** Driver class for Lp1 - Level 2
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/20
 */

package cs6301.g39;

public class LP1L2 {
	public static void main(String[] args) {
		Num x = new Num(999);
		Num y = new Num("8");
		Num z = Num.add(x, y);
		System.out.println(z);
		Num a = Num.power(x, y);
		System.out.println(a);
		z.printList();
	}
}
