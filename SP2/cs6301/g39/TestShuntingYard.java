/** TestShuntingYard class for testing Shunting Yard Algorithm.
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/08 
 */

package cs6301.g39;

import java.util.List;

public class TestShuntingYard {

	public static void main(String[] args) {

		String str = "((1+2!)*4)+(4/2)*9+8!+(9/3)";

		List<Token<?>> l1 = Tokenizer.tokenize(str);

		System.out.println(ShuntingYard.shuntingYardParser(l1));
	}

}
