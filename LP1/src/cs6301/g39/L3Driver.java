package cs6301.g39;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class L3Driver {
	
	public static void main(String[] args) throws FileNotFoundException, Exception{
			Scanner in;
	        if (args.length > 0) {
	            File inputFile = new File(args[0]);
	            in = new Scanner(inputFile);
	        } else {
	            in = new Scanner(System.in);
	        }
	        
	        String s;
	        String currentVar = "";
	        while((s = in.nextLine()).compareTo(";") != 0) {
	        	int ind = s.lastIndexOf(" ");
	        	if( ind>=0 )
	        		s = new StringBuilder(s).replace(ind, ind+2,"").toString();
	        	String[] exprSplit = s.split(" = ");
	        	currentVar = exprSplit[0];
	        	EvaluatePostfixExpression.evaluate(exprSplit[0], exprSplit[1]);
	        }
	        
	        EvaluatePostfixExpression.varLookup.get(currentVar).printList();
	        in.close();
	}

}
