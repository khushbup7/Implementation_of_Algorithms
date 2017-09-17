
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
    
    private Num() {}

    /* Start of Level 1 */
    Num(String s) {
    	if(s.lastIndexOf('-') >= 0) {
    		sign = true;
    		s = s.replace("-", "");
    	}
    		
    	
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
    		sign = true;
    	}
    	
    	if(x==0) {
    		value.add(x);
    	}
    	while(x > 0) {
    		value.add(x%base);
    		x = x / base;
    		size++;
    	}
    }

    static Num add(Num a, Num b) {
    	Num result = new Num();
    	
    	if(a.sign && b.sign)
    		result.sign = true;
    	if(!a.sign && b.sign) {
    		b.sign = false;
    		return subtract(a,b);
    	}
    	if(a.sign && !b.sign) {
    		a.sign = false;
    		return subtract(b,a);
    	}

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
    	Num res = new Num();
    	
    	if(a.sign ^ b.sign) {
    		b.sign = !b.sign;
    		return add(a,b);
    	} 
    		
    	if(a.compareTo(b) == 0) {
    		return new Num(0);
    	}
    	
    	if(a.compareTo(b) < 0) {
    		//a.sign = !a.sign;
    		res = subtract(b, a);
    		res.sign = true;
    		return res;
    	}
    		
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
    	if(this.size < other.size || (this.sign && !other.sign))
    		return -1;
    	if(this.size > other.size || (!this.sign && other.sign))
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
    	if(this.sign && other.sign)
    		return res * -1;
	return res;
    }
    
    // Output using the format "base: elements of list ..."
    // For example, if base=100, and the number stored corresponds to 10965,
    // then the output is "100: 65 9 1"
    public void printList() {
    	System.out.print(base + " :");
    	for(Long l : value)
    		System.out.print(" " + l);
    	System.out.println("\n" +sign);
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
