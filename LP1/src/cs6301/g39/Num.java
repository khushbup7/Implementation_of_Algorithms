// Starter code for lp1.

// Change following line to your group number
// Changed type of base to long: 1:15 PM, 2017-09-08.
package cs6301.g39;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Num implements Comparable<Num> {

	static long defaultBase = 10; // This can be changed to what you want it to
									// be.
	static long base = defaultBase; // Change as needed
	LinkedList<Long> value = new LinkedList<Long>();
	boolean sign = false;

	private Num() {
	}

	private Num(LinkedList<Long> value, boolean sign) {
		this.value = value;
		this.sign = sign;
	}

	/* Start of Level 1 */
	Num(String s) {

		if (s.lastIndexOf('-') >= 0) {
			sign = true;
			s = s.replace("-", "");
		}

		for (int i = 0; i < s.length(); i++) {
			StringBuilder tempStr = new StringBuilder();
			tempStr.append(s.charAt(i));
			value.addFirst(Long.valueOf(tempStr.toString()));
		}
		
		removeLeadingZeros(this);
	}

	Num(long x) {
		if (x < 0) {
			x = Math.abs(x);
			sign = true;
		}

		if (x == 0)
			value.add(x);

		while (x > 0) {
			value.add(x % base);
			x = x / base;
		}
	}

	static Num add(Num a, Num b) {
		LinkedList<Long> res;
		int sign = a.sign ? -1 : 1;
		boolean resSign;
		if (a.sign == b.sign)
			return new Num(add(a.value, b.value), a.sign);

		int cmpMag = compareMagnitude(a.value, b.value);

		if (cmpMag == 0)
			return new Num(0);

		res = (cmpMag > 0 ? subtract(a.value, b.value) : subtract(b.value, a.value));

		resSign = (sign * cmpMag) == -1 ? true : false;
		return new Num(res, resSign);

	}

	static LinkedList<Long> add(List<Long> a, List<Long> b) {
		long carry = 0;
		long sum = 0;
		LinkedList<Long> result = new LinkedList<Long>();

		Iterator<Long> itA = a.iterator();
		Iterator<Long> itB = b.iterator();

		while (itA.hasNext() || itB.hasNext() || carry > 0) {
			sum = Num.next(itA) + Num.next(itB) + carry;
			carry = sum / base;
			result.add(sum % base);
		}
		return result;
	}

	static Num subtract(Num a, Num b) {

		LinkedList<Long> res;
		int sign = a.sign ? -1 : 1;
		boolean resSign;
		if (a.sign != b.sign)
			return new Num(add(a.value, b.value), a.sign);

		int cmpMag = compareMagnitude(a.value, b.value);

		if (cmpMag == 0)
			return new Num(0);

		res = (cmpMag > 0 ? subtract(a.value, b.value) : subtract(b.value, a.value));

		resSign = (sign * cmpMag) == -1 ? true : false;
		return new Num(res, resSign);
	}

	static LinkedList<Long> subtract(List<Long> a, List<Long> b) {
		LinkedList<Long> result = new LinkedList<Long>();

		Iterator<Long> itA = a.iterator();
		Iterator<Long> itB = b.iterator();

		int borrow = 0;
		long valueA, valueB;

		while (itA.hasNext() || itB.hasNext()) {
			valueA = next(itA) + borrow;
			valueB = next(itB);

			if (valueA >= valueB) {
				borrow = 0;
				result.add(valueA - valueB);
			} else {
				borrow = -1;
				valueA = Num.base + valueA;
				result.add(valueA - valueB);
			}
		}
		return result;
	}

	// Implement Karatsuba algorithm for excellence credit
	static Num product(Num a, Num b) {
		
		Num res = Karatsuba(a, b);
		res.sign = a.sign ^ b.sign;
		
		return res;
	}
	
	private static Num Karatsuba(Num a, Num b) {
		
		String aStr = a.toString();
		String bStr = b.toString();
		
		if (aStr.length() < 2 || bStr.length() < 2) {
			return new Num(Long.parseLong(a.toString()) * Long.parseLong(b.toString()));
		}

		long m = Math.min(a.value.size(), b.value.size());
		long k = m / 2;

		Num aHigh = new Num(aStr.substring(0, aStr.length() - (int) k));
		Num aLow = new Num(aStr.substring(aStr.length() - (int) k));
		Num bHigh = new Num(bStr.substring(0, bStr.length() - (int) k));
		Num bLow = new Num(bStr.substring(bStr.length() - (int) k));

		Num z0 = Karatsuba(aLow, bLow);
		Num z1 = Karatsuba(add(aLow, aHigh), add(bLow, bHigh));
		Num z2 = Karatsuba(aHigh, bHigh);

		Num temp = subtract(subtract(z1, z2), z0);
		
		return add(z0, add(Num.shift(z2, 2 * k), Num.shift(temp, k)));
	}

	// Use divide and conquer
	static Num power(Num a, long n) {

		Num res = new Num();
		if(n%2 == 0)
			res.sign = false;
		else
			res.sign = a.sign;
		
		if (n == 0)
			return new Num(1);
		else if (n == 1)
			return a;
		else
			res = power(a, n / 2);

		if (n % 2 == 0)
			return product(res, res);
		else
			return product(product(res, res),a);

	}

	// Shift operation
	static Num shift(Num a, long n) {
		for (int i = 0; i < n; i++) {
			a.value.addFirst((long) 0);
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
		
		Num res = new Num();
		
		LinkedList<Long> pList = new LinkedList<Long>(n.value);
		boolean pSign = n.sign;
		Num nPower = new Num(pList, pSign);
		
		if(isEven(nPower))
			res.sign = false;
		else
			res.sign = a.sign;
	
		res = pow(a,nPower);
		
		return res;
	}
	
	
	private static Num pow(Num a, Num n) {
		
		if (n.value.size() == 0)
			return new Num(1);
		
		long leastSigDig = n.value.removeFirst();
		
		return product(power(pow(a, n), base), power(a, leastSigDig));
	}
	
	private static boolean isEven(Num n) {
		if(base%2 == 0) {
			System.out.println(n.value.getFirst());
			return n.value.getFirst()%2 == 0 ? true : false;
		}
		else {
			long sum = 0;
			for(Long a : n.value)
				sum += a;
			System.out.println(sum);
			return sum%2 == 0 ? true : false;
		}
	}

	static Num squareRoot(Num a) {
		return null;
	}
	/* End of Level 2 */

	// Utility functions

	public static long next(Iterator<Long> it) {
		return it.hasNext() ? it.next() : 0;
	}

	// compare "this" to "other": return +1 if this is greater, 0 if equal, -1
	// otherwise
	public int compareTo(Num other) {
		int mag = compareMagnitude(this.value, other.value);
		int sign = this.sign ? -1 : 1;
		int othersign = other.sign ? -1 : 1;

		return (sign == othersign ? sign * mag : (sign > othersign ? 1 : -1));
	}

	private static int compareMagnitude(LinkedList<Long> a, LinkedList<Long> b) {
		if (a.size() < b.size())
			return -1;
		if (a.size() > b.size())
			return 1;

		Iterator<Long> itA = a.iterator();
		Iterator<Long> itB = b.iterator();
		long valueA, valueB;

		while (itA.hasNext() && itB.hasNext()) {
			valueA = next(itA);
			valueB = next(itB);
			if (valueA < valueB)
				return -1;
			else if (valueA > valueB)
				return 1;
		}
		return 0;
	}

	// Output using the format "base: elements of list ..."
	// For example, if base=100, and the number stored corresponds to 10965,
	// then the output is "100: 65 9 1"
	void printList() {
		System.out.print(base + " :");
		for (Long l : value)
			System.out.print(" " + l);
		System.out.println("\n" + sign);
	}

	// Return number to a string in base 10
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Stack<Long> valStack = new Stack<Long>();

		for (Long l : value)
			valStack.push(l);

		while (!valStack.isEmpty()) {
			sb.append(valStack.pop().toString());
		}

		return sb.toString();
	}
	
    static void removeLeadingZeros(Num a) {
		Stack<Long> valStack = new Stack<Long>();
		
		while(!a.value.isEmpty())
			valStack.push(a.value.removeFirst());
		
		while(valStack.peek() == 0) 
			valStack.pop();
		
		while(!valStack.isEmpty())
			a.value.addFirst(valStack.pop());

	}

	public long base() {
		return base;
	}

}
