
// Starter code for lp1.

// Change following line to your group number
// Changed type of base to long: 1:15 PM, 2017-09-08.
package cs6301.g39;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;


public class Num  implements Comparable<Num> {

    static long defaultBase = 10;  // This can be changed to what you want it to be.
    static long base = defaultBase;  // Change as needed
    LinkedList<Long> value = new LinkedList<Long>();
    boolean sign = false;

    /* Start of Level 1 */
    Num(String s) {
    	if(s.lastIndexOf('-') >= 0)
    		sign = false;
    	else
    		sign = true;
    	
    	for(int i = 0; i < s.length(); i++) {
    		StringBuilder tempStr = new StringBuilder();
    		tempStr.append(s.charAt(i));
    		value.addFirst(Long.valueOf(tempStr.toString()));
    	}
    }

    Num(long x) {
    	if(x<0) {
    		x = Math.abs(x);
    		sign = false;
    	}
    	else
    		sign = true;
    	
    	while(x > 0) {
    		value.add(x%base);
    		x = x / base;
    	}
    }

    static Num add(Num a, Num b) {
    	Num result = new Num(0);
    	
    	if(a.sign == false && b.sign == true)
    		return subtract(b, a);
    	else if(a.sign == true && b.sign == false)
    		return subtract(a,b);
    	else if(a.sign == true && b.sign == true)
    		result.sign = true;
    	else
    		result.sign =  false;
    		
    	long carry = 0;
    	long sum = 0;
    	
    	
    	Iterator<Long> itA = a.value.iterator();
    	Iterator<Long> itB = b.value.iterator();
    	
    	while(itA.hasNext() || itB.hasNext() || carry > 0) {
    		sum = Num.next(itA) + Num.next(itB) + carry;
    		carry = sum/base;
    		result.value.add(sum%base);
    	}
	return result;
    }

    static Num subtract(Num a, Num b) {
    	b.sign = !b.sign;
    	return add(a, b);
	
    }

    // Implement Karatsuba algorithm for excellence credit
    static Num product(Num a, Num b) {
    	if(Long.getLong(a.toString()) < 10 || Long.getLong(b.toString())<10)
    		return new Num(Long.getLong(a.toString()) * Long.getLong(b.toString()));
    		
    	long m = Math.max(a.value.size(), b.value.size()); 
    	long m2 = m/2;
    	
    	String aStr = a.toString();
    	String bStr = b.toString();
    	
    	Num aHigh = new Num(aStr.substring(0, aStr.length()-(int)m2));
    	Num aLow = new Num(aStr.substring(aStr.length()-(int)m2+1));
    	Num bHigh = new Num(bStr.substring(0, bStr.length()-(int)m2));
    	Num bLow = new Num(bStr.substring(bStr.length()-(int)m2+1));
    	
    	Num z0 = product(aLow, bLow);
    	Num z1 = product(add(aLow, aHigh), add(bLow, bHigh));
    	Num z2 = product(aHigh, bHigh);
    	
    	Num base2m2 = new Num((long)Math.pow(10,2*m2));
    	Num basem2 = new Num((long)Math.pow(10,m2));
    	
    	Num temp = subtract(subtract(z1, z2), z0);
    	
	return add(z0, add(product(z2,base2m2), product(temp,basem2)));
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
    
    public static long next(Iterator<Long> it) {
    	return it.hasNext() ? it.next() : 0; 
    }
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
