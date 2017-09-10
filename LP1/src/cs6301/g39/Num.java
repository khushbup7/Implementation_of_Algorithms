
// Starter code for lp1.

// Change following line to your group number
// Changed type of base to long: 1:15 PM, 2017-09-08.
package cs6301.g39;

import java.util.LinkedList;
import java.util.Stack;


public class Num  implements Comparable<Num> {

    static long defaultBase = 10;  // This can be changed to what you want it to be.
    long base = defaultBase;  // Change as needed
    LinkedList<Long> value = new LinkedList<Long>();

    /* Start of Level 1 */
    Num(String s) {
    	for(int i = 0; i < s.length(); i++) {
    		value.addFirst(new Long(s.charAt(i)));
    	}
    }

    Num(long x) {
    	while(x > 0) {
    		value.add(x%base);
    		x = x / base;
    	}
    }

    static Num add(Num a, Num b) {
	return null;
    }

    static Num subtract(Num a, Num b) {
	return null;
    }

    // Implement Karatsuba algorithm for excellence credit
    static Num product(Num a, Num b) {
    	//TODO check if one of the number is smaller or not
    
    		
    	long m = Math.max(a.value.size(), b.value.size()); 
    	long m2 = m/2;
    	
    	
	return null;
    }

    // Use divide and conquer
    static Num power(Num a, long n) {
	return null;
    }
    /* End of Level 1 */

    /* Start of Level 2 */
    static Num divide(Num a, Num b) {
	return null;
    }

    static Num mod(Num a, Num b) {
	return null;
    }

    // Use divide and conquer
    static Num power(Num a, Num n) {
	return null;
    }

    static Num squareRoot(Num a) {
	return null;
    }
    /* End of Level 2 */


    // Utility functions
    // compare "this" to "other": return +1 if this is greater, 0 if equal, -1 otherwise
    public int compareTo(Num other) {
	return 0;
    }
    
    // Output using the format "base: elements of list ..."
    // For example, if base=100, and the number stored corresponds to 10965,
    // then the output is "100: 65 9 1"
    void printList() {
    	for(Long l : value)
    		System.out.println(l);
    }
    
    // Return number to a string in base 10
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	Stack<Long> valStack = new Stack<Long>();
    	
    	for(Long l : value)
    		valStack.push(l);
    	
    	while(!valStack.isEmpty()) {
    		sb.append(valStack.pop().toString());
    	}
    		
    	return sb.toString();
    }

    public long base() { return base; }
}
