/** Driver class for Lp1 - Level 4
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/20
 */

package cs6301.g39;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class LP1L4 {

	static ArrayList<Expression> nExpr = new ArrayList<Expression>(); // ArrayList of expressions in the program
	static Hashtable<Integer, Integer> lineNoLookup = new Hashtable<Integer, Integer>(); // Table mapping line no to
																							// appropriate index in
																							// ArrayList

	public static void main(String[] args) throws Exception {

		Scanner in;
		if (args.length > 0) {
			int base = Integer.parseInt(args[0]);
			System.out.println(base);
		}

		in = new Scanner(System.in);
		LP1L4 x = new LP1L4();

		x.processInput(in);

		in.close();
	}

	/**
	 * A function to process the input from console
	 * 
	 * @param in
	 *            - scanner object that is used to read the input from
	 * @throws Exception
	 */
	void processInput(Scanner in) throws Exception {

		// Processing the input and storing it accordingly
		String s;

		int ind = 0;
		while ((s = in.nextLine()).compareTo(";") != 0) {

			String lineNoStr = null;

			// Discarding last space and semicolon after that from each input line
			ind = s.lastIndexOf(" ");
			if (ind >= 0)
				s = new StringBuilder(s).replace(ind, ind + 2, "").toString();

			// Checking to see if a line starts with line no., if so record the line number
			// into a varaible
			if (Character.isDigit(s.charAt(0))) {
				ind = s.indexOf(" ");
				if (ind >= 0) {
					lineNoStr = s.substring(0, ind);
					s = new StringBuilder(s).replace(0, ind + 1, "").toString(); // removing the line no part from the
																					// input
				}
			}

			Expression expr = new Expression();

			// If line no was recorded, put that information in hashtable
			if (lineNoStr != null) {
				expr.lineNo = Integer.parseInt(lineNoStr);
				lineNoLookup.put(Integer.parseInt(lineNoStr), nExpr.size() + 1);
			}

			String[] exprSplit;

			// If the line is an assignment operation, infix expression or postfix
			// expression, then it is
			// needed to be tokenized and parsed using shunting-yard algorithm
			if (s.contains("=")) {
				exprSplit = s.split(" = ");
				expr.variableName = exprSplit[0];
				List<Token<?>> l = Tokenizer.tokenize(exprSplit[1]);
				expr.tokenizedExpr = ShuntingYard.shuntingYardParser(l);
			}

			// If the expression is of the form "lineno var ? NZ : NR" then variable 'var'
			// and jump locations 'NZ' and 'NR' are recorded
			else if (s.contains("?")) {
				exprSplit = s.split(" \\? ");
				expr.variableName = exprSplit[0];
				expr.isGoto = true;
				String[] jumpStr = exprSplit[1].split(" : ");
				for (String str : jumpStr) {
					Token<Integer> t = new Token<Integer>(Integer.parseInt(str));
					expr.tokenizedExpr.add(t);
				}
			}

			// Else, the expression is simply a variable name
			else
				expr.variableName = s;

			// Adding the expression to ArrayList
			nExpr.add(expr);
		}

		// Evaluation of expressions in the program

		String currentVar = null; // last variable that was assigned the value

		for (int i = 0; i < nExpr.size(); i++) {
			Expression ex = nExpr.get(i);
			currentVar = ex.variableName;

			// if expression is just a variable name, list of tokens will be empty and value
			// of the variable can be printed
			if (ex.tokenizedExpr.isEmpty()) {
				System.out.println(EvaluatePostfixExpression.varLookup.get(ex.variableName).toString());
				continue;
			}
			// if expression is a jump statement, determine appropriate jump/goto location.
			else if (ex.isGoto) {
				// Check if variable is zero or not and jump accordingly from lookup table
				if (EvaluatePostfixExpression.varLookup.get(ex.variableName).isZero()) {
					if (ex.tokenizedExpr.size() > 1)
						i = lineNoLookup.get(Integer.parseInt(ex.tokenizedExpr.get(1).toString())) - 2;
					else
						continue;
				} else
					i = lineNoLookup.get(Integer.parseInt(ex.tokenizedExpr.get(0).toString())) - 2;
			}
			// Else, it is a postfix expression and thus it needs to be evaluated
			else {
				EvaluatePostfixExpression.evaluate(ex.variableName, ex.tokenizedExpr);
			}

			// if the expression doesn't have a line no, value of the variable is to be
			// printed
			if (ex.lineNo == -1) {
				System.out.println(EvaluatePostfixExpression.varLookup.get(ex.variableName).toString());
			}
		}

		// printing the value of the last variable that was assigned a value
		EvaluatePostfixExpression.varLookup.get(currentVar).printList();

	}
}
