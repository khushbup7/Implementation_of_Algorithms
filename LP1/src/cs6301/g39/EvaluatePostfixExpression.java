package cs6301.g39;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class EvaluatePostfixExpression {
	
	static HashMap<String, Num> varLookup = new HashMap<String, Num>();
	static ArrayList<Character> operatorList = new ArrayList<>(Arrays.asList('+', '-', '*', '/', '^', '%', '|'));
	
	public static void evaluate(String var, String expr) throws Exception{
		
		if(isNumeric(expr)) {
			Num n = new Num(expr);
			varLookup.put(var, n);
			System.out.println(n.toString());
		}
		else {
			List<Token<?>> tokens = Tokenizer.tokenize(expr);
			
			Stack<Num> evalStack = new Stack<Num>();
						
			Num res = new Num();
			for(Token<?> t : tokens){
				String tokenStr = t.toString();
				
				if(t.isOperator) {
					Num operand1;
					Num operand2;
					switch(tokenStr) {
						case "+":
							operand1 = evalStack.pop();
							operand2 = evalStack.pop();
							res = Num.add(operand1, operand2);
							evalStack.push(res);
							break;
						case "-":
							operand2 = evalStack.pop();
							operand1 = evalStack.pop();
							res = Num.subtract(operand1, operand2);
							evalStack.push(res);
							break;
						case "*":
							operand1 = evalStack.pop();
							operand2 = evalStack.pop();
							res = Num.product(operand1, operand2);
							evalStack.push(res);
							break;
						case "/":
							operand2 = evalStack.pop();
							operand1 = evalStack.pop();
							res = Num.divide(operand1, operand2);
							evalStack.push(res);
							break;
						case "%":
							operand1 = evalStack.pop();
							operand2 = evalStack.pop();
							res = Num.mod(operand1, operand2);
							evalStack.push(res);
							break;
						case "^":
							operand1 = evalStack.pop();
							operand2 = evalStack.pop();
							res = Num.power(operand1, operand2);
							evalStack.push(res);
							break;
						case "|":
							operand1 = evalStack.pop();
							res = Num.squareRoot(operand1);
							evalStack.push(res);
							break;
					}
					
				}
				else
					evalStack.push(varLookup.get(tokenStr));
			}
			
			varLookup.put(var, evalStack.pop());
			System.out.println(varLookup.get(var).toString());
		}		
	}
	
	/**
	 * @param str
	 *            - a string
	 * @return - if the string is number or not
	 */
	static boolean isNumeric(String str) {
		return str != null && (str.matches("0") || str.matches("[1-9][0-9]*"));
	}

}
