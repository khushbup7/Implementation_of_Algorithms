package cs6301.g39;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ShuntingYard {
	
	    static Map<String,Integer> precisionTable = new HashMap<String,Integer>(){/**
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
	}};
	
	public static String shuntingYardParser(List<Token<?>> l1) {
		
		LinkedList<Token<?>> outputQueue = new LinkedList<Token<?>>();
		
		Stack<Token<?>> oprStack = new Stack<Token<?>>();
		
		for(int i = 0; i<l1.size(); i++) {
			
			Token<?> token = l1.get(i);
			String tokenStr = token.toString(); 
			//if the token is an operand
			if(!token.isOperator) {
				outputQueue.add(token);
			}
			
			//if the token is "("
			else if(tokenStr.equals("(")){
				oprStack.push(token);
			}
			
			//if the token is ")"
			else if(tokenStr.equals(")")) {
				if(!oprStack.isEmpty()) {
					while(!oprStack.peek().toString().equals("(")) {
						outputQueue.add(oprStack.pop());
					}
				oprStack.pop();
				}
			}
			
			//if the token is any other operator
			else {
				while(!oprStack.isEmpty()){
					if(precisionTable.get(oprStack.peek().toString()) >= precisionTable.get(tokenStr) && !token.toString().equals("^"))
						outputQueue.add(oprStack.pop());
					else
						break;
				}
				oprStack.push(token);
			}
		}
		
		//If at the end stack is not empty
		while(!oprStack.isEmpty()) {
			outputQueue.add(oprStack.pop());	
		}
			
		StringBuilder postfixExpr = new StringBuilder();
		for(Token<?> t : outputQueue) {
			postfixExpr.append(t.toString() + " ");
		}
		
		return postfixExpr.toString();
	}

}
