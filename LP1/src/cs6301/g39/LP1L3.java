/** Driver class for Lp1 - Level 3
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/19
 */

package cs6301.g39;

import java.util.List;
import java.util.Scanner;

public class LP1L3 {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		LP1L3 x = new LP1L3();

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
		String s;
		String currentVar = "";
		while ((s = in.nextLine()).compareTo(";") != 0) {
			// Discarding last space and semicolon after that from each input line
			int ind = s.lastIndexOf(" ");
			if (ind >= 0)
				s = new StringBuilder(s).replace(ind, ind + 2, "").toString();

			String[] exprSplit = s.split(" = ");
			currentVar = exprSplit[0];
			// Tokenizing the expression
			List<Token<?>> tokens = Tokenizer.tokenize(exprSplit[1]);
			// Evaluating the postfix form of the expression
			EvaluatePostfixExpression.evaluate(exprSplit[0], tokens);
			System.out.println(EvaluatePostfixExpression.varLookup.get(currentVar).toString());
		}
		
		//printing the value of the last variable that was assigned a value
		EvaluatePostfixExpression.varLookup.get(currentVar).printList();
	}

}
