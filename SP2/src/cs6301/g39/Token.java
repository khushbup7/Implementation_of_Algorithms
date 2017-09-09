/** Token class for representing a token in the arithmetic expression
 *  A token can be an operand (a number or a variable) or an operator.  
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/06 
 */

package cs6301.g39;

public class Token<T> {

	T element;				//a variable representing generic token 
	boolean isOperator;		//flag representing if the token is an operator or not

	Token(T c) {
		element = c;
		isOperator = false;
	}

	public String toString() {
		return this.element.toString();
	}
}
