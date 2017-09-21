/** Expression class that represents an expression/statement in a program.
 *  its attributes are line no, its tokenized form, varibale name (if any) and flag representing if it is a jump statement or not.
 *  Each line in an input program is an object of Expression class.
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/20
 *  Usage: Expression ex = new Expression();
 */

package cs6301.g39;

import java.util.LinkedList;
import java.util.List;

public class Expression {
	
	int lineNo;			//line no associted with expression. if not given in an input program, it is -1
	List<Token<?>> tokenizedExpr;	//tokenized form of an expression (always in postfix notation)
	String variableName;		//variable that is being evaluated or assigned a value in the expression
	boolean isGoto;		//flag representing if it is a jump statement or not
	
	Expression(){
		lineNo = -1;
		variableName = null;
		isGoto = false;
		tokenizedExpr = new LinkedList<Token<?>>();
	}
	
	

}
