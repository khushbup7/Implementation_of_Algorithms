/** Tokenizer class for parsing/tokenizing the given arithmetic expression into tokens.
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/08 
 *  Usage: Tokenizer.tokenize(expr)
 */

package cs6301.g39;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Tokenizer {

	static ArrayList<Character> operatorList = new ArrayList<>(Arrays.asList('+', '-', '*', '/', '!', '^', '(', ')'));

	/**
	 * @param expr
	 *            - an arithmetic expression that is to be tokenized
	 * @return - list of tokens
	 */
	public static List<Token<?>> tokenize(String expr) {

		List<Token<?>> t = new LinkedList<Token<?>>();

		for (int i = 0; i < expr.length();) {

			// If a token is a white space
			if (expr.charAt(i) == ' ')
				i++;

			// If a token is an operator, add it to the list of tokens
			else if (operatorList.contains(expr.charAt(i))) {
				Token<Character> tok = new Token<Character>(expr.charAt(i));
				tok.isOperator = true;
				t.add(tok);
				i++;
			}

			// If a token is an operand, parse accordingly and add it to the operand
			else {
				String temp = getOperand(expr, i);
				// if an operand is a number
				if (isNumeric(temp)) {
					// TODO differentiate between int and double
					Token<Double> tok = new Token<Double>(Double.valueOf(temp));
					tok.isOperator = false;
					t.add(tok);
				}
				// if an operand is a variable
				else {
					Token<String> tok = new Token<String>(temp);
					tok.isOperator = false;
					t.add(tok);
				}

				i = i + temp.length();
			}
		}

		return t;
	}

	/**
	 * @param input
	 *            - an arithmetic expression
	 * @param startIndex
	 *            - index at which an operand (number or variable) starts
	 * @return - an operand(a number or a variable)
	 */
	public static String getOperand(String input, int startIndex) {
		int endIndex = startIndex;
		for (; endIndex < input.length();) {
			if (operatorList.contains(input.charAt(endIndex)) || input.charAt(endIndex) == ' ') {
				break;
			} else
				endIndex++;
		}

		return input.substring(startIndex, endIndex);
	}

	/**
	 * @param str
	 *            - a string
	 * @return - if the string is number or not
	 */
	public static boolean isNumeric(String str) {
		return str != null && str.matches("[-+]?\\d*\\.?\\d+");
	}

}
