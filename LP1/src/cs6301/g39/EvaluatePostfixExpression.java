/** EvaluatePostfixExpression class for evaluating a postfix expression using a stack
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/19
 *  Ver 1.1:2017/09/18 
 *  Usage: EvaluatePostfixExpression.evaluate(String var, List<Token<?>> l1)
 */
package cs6301.g39;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class EvaluatePostfixExpression {
	
	static HashMap<String, Num> varLookup = new HashMap<String, Num>();				//hashmap to store the varaibles and their values calculaed during execution of a program
	static ArrayList<Character> operatorList = new ArrayList<>(Arrays.asList('+', '-', '*', '/', '^', '%', '|'));		//ArrayList of operators
	
	public static void evaluate(String var, List<Token<?>> tokens) throws Exception{
		
		String str = tokens.toString();
		
		int index = 0;
		if(str.contains("[")){
			index = str.indexOf("[");
			str = new StringBuilder(str).replace(index, index+1,"").toString();
		}
		if(str.contains("]")) {
			index = str.indexOf("]");
			str = new StringBuilder(str).replace(index, index+1,"").toString();
		}
		
		//If a token is a number, it corrosponds to an assignment operation, thus appropriate values are added to the operand hashmap
		if(isNumeric(str)) {
			Num n = new Num(str);
			varLookup.put(var, n);
		}
		//else, it is a postfix expression and thus it needs to be evaluated.
		else {
			Stack<Num> evalStack = new Stack<Num>();
						
			Num res = new Num();
			for(Token<?> t : tokens){
				String tokenStr = t.toString();
				
				//If a token is an operator, appropriate number of operands are popped from the stack, operation is performed and result is pushed back to stack
				if(t.isOperator) {
					Num operand1;
					Num operand2;
					switch(tokenStr) {
						//"add" operation
						case "+":
							operand1 = evalStack.pop();
							operand2 = evalStack.pop();
							res = Num.add(operand1, operand2);
							evalStack.push(res);
							break;
						//"subtract" operation
						case "-":
							operand2 = evalStack.pop();
							operand1 = evalStack.pop();
							res = Num.subtract(operand1, operand2);
							evalStack.push(res);
							break;
						//"product" operation
						case "*":
							operand1 = evalStack.pop();
							operand2 = evalStack.pop();
							res = Num.product(operand1, operand2);
							evalStack.push(res);
							break;
						//"division" operation
						case "/":
							operand2 = evalStack.pop();
							operand1 = evalStack.pop();
							res = Num.divide(operand1, operand2);
							evalStack.push(res);
							break;
						//"mod" operation
						case "%":
							operand2 = evalStack.pop();
							operand1 = evalStack.pop();
							res = Num.mod(operand1, operand2);
							evalStack.push(res);
							break;
						//"power" operation
						case "^":
							operand2 = evalStack.pop();
							operand1 = evalStack.pop();
							res = Num.power(operand1, operand2);
							evalStack.push(res);
							break;
						//"Square-root operation
						case "|":
							operand1 = evalStack.pop();
							res = Num.squareRoot(operand1);
							evalStack.push(res);
							break;
					}
					
				}
				//Else, it is an operand, thus it is pushed into stack
				else {
					//If an operand is a variable, value of it is taken from operand hashmap and pushed into stack
					if(!Character.isDigit(tokenStr.charAt(0)))
						evalStack.push(varLookup.get(tokenStr));
					//else, an operand is a number
					else {
						evalStack.push(new Num(tokenStr));
					}
				}
			}
			
			//If a variable is not in operand hashmap, we put it in with its value or we replace its value with newly calculated value
			if(!varLookup.containsKey(var))
				varLookup.put(var, evalStack.pop());
			else
				varLookup.replace(var, evalStack.pop());
		}		
	}
	
	/**
	 * @param str
	 *            - a string
	 * @return - if the string is number or not
	 */
	static boolean isNumeric(String str) {
		//System.out.println(str);
		return str != null && (str.matches("0") || str.matches("[1-9][0-9]*"));
	}

}
