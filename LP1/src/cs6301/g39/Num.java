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
    int size = 0;
    
    private Num() {
    }

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
    		size++;
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
    		size++;
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
    	
    	if(a.sign && !b.sign) {
    		b.sign = !b.sign;
    		return Num.add(a, b);
    	}
    	
    	if(!a.sign && b.sign) {
    		b.sign = !b.sign;
    		return Num.add(a, b);
    	}
    	
    	if(a.compareTo(b) < 0) {
    		Num res = Num.subtract(b, a);
    		res.sign = false;
    	}
    		
    	Num res = new Num(0);
    	Iterator<Long> itA = a.value.iterator();
    	Iterator<Long> itB = b.value.iterator();
    	
    	int borrow = 0;
    	long valueA, valueB;
    	while(itA.hasNext()  || itB.hasNext()) {
    		valueA = next(itA) + borrow;
    		valueB = next(itB);
    		
    		if(valueA >= valueB) {
    			borrow = 0;
    			res.value.add(valueA - valueB);
    		}
    		else {
    			borrow = -1;
    			valueA =  Num.base + valueA;
    			res.value.add(valueA - valueB);
    		}
    	}
    	return res;
	
    }

    // Implement Karatsuba algorithm for excellence credit
    static Num product(Num a, Num b) {
    	    	
    	if(a.toString().length() < 2 || b.toString().length() < 2) {
    		System.out.println("a in f :" + a.toString());
    		System.out.println("b in f :" + b.toString());
    		
    		return new Num(Long.parseLong(a.toString()) * Long.parseLong(b.toString()));
    	}
    		
    		
    	long m = Math.max(a.value.size(), b.value.size()); 
    	long k = m/2;
    	
    	String aStr = a.toString();
    	String bStr = b.toString();
    	
    	Num aHigh = new Num(aStr.substring(0, aStr.length()-(int)k));
    	
    	Num aLow = new Num(aStr.substring(aStr.length()-(int)k));
    	Num bHigh = new Num(bStr.substring(0, bStr.length()-(int)k));
    	Num bLow = new Num(bStr.substring(bStr.length()-(int)k));
    	
    	Num z0 = product(aLow, bLow);
    	Num z1 = product(add(aLow, aHigh), add(bLow, bHigh));
    	Num z2 = product(aHigh, bHigh);
    		
    	Num temp = subtract(subtract(z1, z2), z0);
    	
	return add(z0, add(Num.shift(z2,2*k), Num.shift(temp, k)));
    }

    // Use divide and conquer
    static Num power(Num a, long n) {
    	
    	Num res =  new Num();
    	if(n == 0)
    		return new Num(1);
    	else if(n == 1)
    		return a;
    	else 
    		res = power(a, n/2);
    	
    	if(n%2 == 0)
    		return product(res, res);
    	else
    		return product(a, product(res, res));
    }
    
    //Shift operation
    static Num shift(Num a, long n) {
    	for(int i = 0; i < n; i++) {
    		a.value.addFirst((long)0);
    	}
    	return a;
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

    	//TODO do edge/base cases
    	
    	if(n.value.size() == 0)
    		return new Num(1);
    	
    	Num res = new Num();
    	
    	if()
    	
    	long leastSigDig = n.value.removeFirst();
    	res = product(power(power(a, n),base), power(a, leastSigDig));
    	
	return res;
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
    	if(this.size < other.size)
    		return -1;
    	if(this.size > other.size)
    		return 1;
    	
    	int res = 0;
    	Iterator<Long> itA = this.value.iterator();
    	Iterator<Long> itB = other.value.iterator();
    	long valueA, valueB;
    	
    	while(itA.hasNext() && itB.hasNext()) {
    		valueA = next(itA);
    		valueB = next(itB);
    		if(valueA < valueB)
    			res = -1;
    		else if(valueA > valueB)
    			res = 1;
    	}
	return res;
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
