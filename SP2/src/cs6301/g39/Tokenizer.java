package cs6301.g39;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Tokenizer {
	
	static ArrayList<Character> operatorList = new ArrayList<>(Arrays.asList('+', '-','*','/','!','^','(',')'));
	
	public static List<Token<?>> tokenize(String expr) {

		List<Token<?>> t = new LinkedList<Token<?>>();
		
		for(int i = 0; i<expr.length();) {
			
			//If a character is a white space
			if(expr.charAt(i) == ' ')
				i++;
			
			//If a character is an operator
			else if(operatorList.contains(expr.charAt(i))) {
				Token<Character> tok = new Token<Character>(expr.charAt(i));
				tok.isOperator = true;
				t.add(tok);
				i++;
			}
			
			//If a character is an operand
			else {
				String temp = getOperand(expr, i);
				if(isNumeric(temp)) {
					Token<Double> tok = new Token<Double>(Double.valueOf(temp));
					tok.isOperator = false;
					t.add(tok);
				}
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
	
	
	public static String getOperand(String input, int startIndex){
		int endIndex = startIndex;
		for(;endIndex < input.length();) {
			if(operatorList.contains(input.charAt(endIndex)) || input.charAt(endIndex) == ' ') {
				break;
			}
			else
				endIndex++;
		}
		
		return input.substring(startIndex, endIndex);
	}
	
	public static boolean isNumeric(String str) {  
	    return str != null && str.matches("[-+]?\\d*\\.?\\d+");  
	}
	
}
