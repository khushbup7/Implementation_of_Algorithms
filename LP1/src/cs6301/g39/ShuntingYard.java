/** ShuntingYard class for implementing Shunting Yard algorithm.
 *  This algorithm returns a post-fix expression equivalent to given in-fix expression.
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/08 
 *  Ver 1.1: 2017/09/20
 *  Usage: ShuntingYard.shuntingYardParser(List<Token<?>> l1)
 */

package cs6301.g39;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ShuntingYard {

	// a hashmap storing the precedence values of operators
	static Map<String, Integer> precedenceTable = new HashMap<String, Integer>() {
		/**
		* 
		*/
		private static final long serialVersionUID = 1L;
		{
			put("(", 0);
			put("+", 1);
			put("-", 1);
			put("*", 2);
			put("/", 2);
			put("^", 3);
			put("!", 4);
			put("|", 4);
		}
	};

	/**
	 * @param l1
	 *            - list of tokens from tokenizer
	 * @return - an equivalent post-fix expression as a list of tokens
	 */
	public static List<Token<?>> shuntingYardParser(List<Token<?>> l1) {

		LinkedList<Token<?>> outputQueue = new LinkedList<Token<?>>(); // Output Queue

		Stack<Token<?>> oprStack = new Stack<Token<?>>(); // Operator Stack

		for (int i = 0; i < l1.size(); i++) {

			Token<?> token = l1.get(i);
			String tokenStr = token.toString();
			// if the token is an operand
			if (!token.isOperator) {
				outputQueue.add(token);
			}

			// if the token is "("
			else if (tokenStr.equals("(")) {
				oprStack.push(token);
			}

			// if the token is ")"
			else if (tokenStr.equals(")")) {
				if (!oprStack.isEmpty()) {
					while (!oprStack.peek().toString().equals("(")) {
						outputQueue.add(oprStack.pop());
					}
					oprStack.pop();
				}
			}

			// if the token is any other operator
			else {
				while (!oprStack.isEmpty()) {
					if (precedenceTable.get(oprStack.peek().toString()) >= precedenceTable.get(tokenStr)
							&& !token.toString().equals("^"))
						outputQueue.add(oprStack.pop());
					else
						break;
				}
				oprStack.push(token);
			}
		} 
		
		// If at the end of reading tokens, stack is not empty
		while (!oprStack.isEmpty()) {
			outputQueue.add(oprStack.pop());
		}
		
		//Return the postfix expression as a list of tokens
		return outputQueue; 
	}

}
