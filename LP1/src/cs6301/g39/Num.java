// Starter code for lp1.
// Change following line to your group number
// Changed type of base to long: 1:15 PM, 2017-09-08.

/** Num class with all the basic arithmetic operations for Big Integers
 * @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 * Ver 1.0: 2017/09/13 Initial class
 */
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
	boolean sign = false; // true indicates number is negative, false indicates
							// number is false

	// Constructors
	/**
	 * Constructor to initialize blank Num
	 */
	Num() {
	}

	/**
	 * @param value
	 *            - Linked list of value to represent the Number
	 * @param sign
	 *            - boolean value for sign
	 */
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

		removeLeadingZeros();
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

	// Addition
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

	// Addition Helper
	/**
	 * @param a
	 *            - LinkedList of values of number 1
	 * @param b
	 *            - LinkedList of values of number 2
	 * @return - LinkedList of values of number 1 + number 2
	 */
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

	//
	/**
	 * @param a
	 *            - LinkedList of values of number 1
	 * @param b
	 *            - LinkedList of values of number 2
	 * @return - LinkedList of values of number 1 - number 2
	 */
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
    
		LinkedList<Long> res = Karatsuba(a.value, b.value);
	//	res.sign = a.sign ^ b.sign;

		return new Num(res, a.sign ^ b.sign);
		//return res;
	}

	// Multiplication Helper - Uses Karatsuba
	/**
	 * @param a
	 *            - LinkedList of values of number 1
	 * @param b
	 *            - LinkedList of values of number 2
	 * @return - LinkedList of values of number 1 * number 2
	 */
	private static LinkedList<Long> Karatsuba(LinkedList<Long> a, LinkedList<Long> b) {
		
		int aSize = a.size();
        int bSize = b.size();
        int s = Math.max(aSize, bSize);
        if (s == aSize) {
            for (int i = 0; i < s - bSize; i++)
                b.addLast((long) 0);
            	bSize = b.size();
        } else {
            for (int i = 0; i < s - aSize; i++)
                a.addLast((long) 0);
            	aSize = a.size();
        }

		if (aSize < 2 || bSize < 2) {
			 LinkedList<Long> resTemp = new LinkedList<Long>();
			 Num n = new Num(Long.parseLong(toString(a)) * Long.parseLong(toString(b)));
			 resTemp.addAll(n.value);
			 return resTemp;
		}

		long m = Math.min(a.size(), b.size());
		long k = m / 2;
		
		LinkedList<Long> aHigh = new LinkedList<Long>();
		LinkedList<Long> aLow = new LinkedList<Long>();
		LinkedList<Long> bHigh = new LinkedList<Long>();
		LinkedList<Long> bLow = new LinkedList<Long>();
		
		/*
		Num aHigh = new Num(aStr.substring(0, aStr.length() - (int) k));
		Num aLow = new Num(aStr.substring(aStr.length() - (int) k));
		Num bHigh = new Num(bStr.substring(0, bStr.length() - (int) k));
		Num bLow = new Num(bStr.substring(bStr.length() - (int) k));
		*/
		
		for (int i = 0; i < a.size(); i++) {
			 			if (i < k) {
			 				aLow.add(a.get(i));
			 				bLow.add(b.get(i));
			 			} else {
			 				aHigh.add(a.get(i));
			 				bHigh.add(b.get(i));
			 			}
			 		}
		
		LinkedList<Long> z0 = Karatsuba(aLow, bLow);
		LinkedList<Long> z1 = Karatsuba(add(aLow, aHigh), add(bLow, bHigh));
		LinkedList<Long> z2 = Karatsuba(aHigh, bHigh);
		LinkedList<Long> temp = subtract(subtract(z1, z2), z0);

		/*
		Num z0 = Karatsuba(aLow, bLow);
		Num z1 = Karatsuba(add(aLow, aHigh), add(bLow, bHigh));
		Num z2 = Karatsuba(aHigh, bHigh);
		

		Num temp = subtract(subtract(z1, z2), z0); */

		return add(z0, add(Num.shift(z2, 2 * k), Num.shift(temp, k)));
	}

	// Use divide and conquer
	static Num power(Num a, long n) {

		Num res = new Num();
		if (n % 2 == 0)
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
			return product(product(res, res), a);

	}

	/* End of Level 1 */

	/* Start of Level 2 */
	static Num divide(Num a, Num b) throws Exception {
		if (b.isZero()) {
			throw new ArithmeticException("Cannot divide by zero");
		}

		int cmpMag = compareMagnitude(a.value, b.value);
		if (cmpMag < 0)
			return new Num(0);

		boolean resSign = a.sign ^ b.sign;
		LinkedList<Long> res = new LinkedList<Long>();

		if (cmpMag == 0) {
			res.add((long) 1);
			return new Num(res, resSign);
		}

		res = divide(new Num(1), new Num(toString(a.value)), a, b);
		System.out.println(res.toString());
		return new Num(res, resSign);
	}

	/**
	 * Divide by binary search
	 * 
	 * @param start
	 *            - start number for the binary search interval
	 * @param end
	 *            - end number for the binary search interval
	 * @param dividend
	 *            - Number tp be divided
	 * @param divisor
	 *            - Divisor
	 * @return
	 */
	private static LinkedList<Long> divide(Num start, Num end, Num dividend, Num divisor) {
		LinkedList<Num> middleNumbers = getMiddleNumbers(start, end);

		Num productLeft = product(middleNumbers.getFirst(), divisor);
		Num productRight = product(middleNumbers.getLast(), divisor);

		int leftCompare = compareMagnitude(productLeft.value, dividend.value);
		int rightCompare = compareMagnitude(productRight.value, dividend.value);

		if (leftCompare <= 0 && rightCompare > 0) {
			return middleNumbers.getFirst().value;
		}

		if (leftCompare < 0 && rightCompare < 0)
			return divide(middleNumbers.getLast(), end, dividend, divisor);

		if (leftCompare > 0 && rightCompare > 0)
			return divide(start, middleNumbers.getFirst(), dividend, divisor);

		return null;
	}

	static Num mod(Num a, Num b) throws Exception {

		Num aByB = divide(a, b);
		Num productB = product(aByB, b);
		return new Num(subtract(a, productB).value, b.sign);

	}

	// Use divide and conquer
	/**
	 * @param a
	 *            Base
	 * @param n
	 *            Exponent
	 * @return - Power(Base, Coefficent)
	 */
	static Num power(Num a, Num n) {

		Num res = new Num();

		LinkedList<Long> pList = new LinkedList<Long>(n.value);
		boolean pSign = n.sign;
		Num nPower = new Num(pList, pSign);

		if (isEven(nPower))
			res.sign = false;
		else
			res.sign = a.sign;

		res = pow(a, nPower);

		return res;
	}

	/**
	 * Returns magnitude for Power function above
	 * 
	 * @param a
	 *            Base
	 * @param n
	 *            Exponent
	 * @return Power(Base, Exponent)
	 */
	private static Num pow(Num a, Num n) {

		if (n.value.size() == 0)
			return new Num(1);

		long leastSigDig = n.value.removeFirst();

		return product(power(pow(a, n), base), power(a, leastSigDig));
	}

	// SquareRoot
	static Num squareRoot(Num a) {
		if (a.isZero()) {
			return new Num(0);
		}

		LinkedList<Long> res = new LinkedList<Long>();
		res = squareRoot(new Num(1), new Num(toString(a.value)), a);

		return new Num(res, false);
	}

	/**
	 * Square root by binary search
	 * 
	 * @param start
	 *            - start number for the binary search
	 * @param end
	 *            - end number for the binary search
	 * @param a
	 *            - given Number
	 * @return - LinkedList for the squareroot value
	 */
	static private LinkedList<Long> squareRoot(Num start, Num end, Num a) {
		LinkedList<Num> middleNumbers = getMiddleNumbers(start, end);

		Num productLeft = product(middleNumbers.getFirst(), middleNumbers.getFirst());
		Num productRight = product(middleNumbers.getLast(), middleNumbers.getLast());

		int leftCompare = compareMagnitude(productLeft.value, a.value);
		int rightCompare = compareMagnitude(productRight.value, a.value);

		if (leftCompare <= 0 && rightCompare > 0) {
			return middleNumbers.getFirst().value;
		}

		if (leftCompare < 0 && rightCompare < 0)
			return squareRoot(middleNumbers.getLast(), end, a);

		if (leftCompare > 0 && rightCompare > 0)
			return squareRoot(start, middleNumbers.getFirst(), a);

		return null;
	}

	/* End of Level 2 */

	// Utility functions

	// compare "this" to "other": return +1 if this is greater, 0 if equal, -1
	// otherwise
	public int compareTo(Num other) {
		int mag = compareMagnitude(this.value, other.value);
		int sign = this.sign ? -1 : 1;
		int othersign = other.sign ? -1 : 1;

		return (sign == othersign ? sign * mag : (sign > othersign ? 1 : -1));
	}

	/**
	 * @param a
	 *            - LinkedList of values for Number a
	 * @param b
	 *            - LinkedList of values for Number b
	 * @return - int -1, 0, 1 for negative, equal and positive
	 */
	private static int compareMagnitude(LinkedList<Long> a, LinkedList<Long> b) {
		if (a.size() < b.size())
			return -1;
		if (a.size() > b.size())
			return 1;

		Iterator<Long> itA = a.descendingIterator();
		Iterator<Long> itB = b.descendingIterator();
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
		this.removeLeadingZeros();
		System.out.print(base + " :");
		for (Long l : value)
			System.out.print(" " + l);
	}

	// Return number to a string in base 10
	public String toString() {
		this.removeLeadingZeros();
		StringBuilder sb = new StringBuilder();
		if (sign)
			sb.append("-");
		
		String valueString = toString(this.value); 
		sb.append(valueString);
		return sb.toString();
	}

	/**
	 * @param l1
	 *            - LinkedList to be converted to String
	 * @return - String of values in the LinkedList
	 */
	public static String toString(LinkedList<Long> l1) {
		StringBuilder sb = new StringBuilder();
		Stack<Long> valStack = new Stack<Long>();

		for (Long l : l1)
			valStack.push(l);

		while (!valStack.isEmpty()) {
			sb.append(valStack.pop().toString());
		}

		return sb.toString();
	}

	/**
	 * Removes leading zeros in the value of this Number
	 */
	void removeLeadingZeros() {
		Stack<Long> valStack = new Stack<Long>();

		while (!this.value.isEmpty())
			valStack.push(this.value.removeFirst());

		while (!valStack.isEmpty() && valStack.peek() == 0)
			valStack.pop();

		while (!valStack.isEmpty())
			this.value.addFirst(valStack.pop());

		if (this.value.isEmpty()) {
			this.value.add((long) 0);
			return;
		}

	}

	public long base() {
		return base;
	}

	/**
	 * @param it
	 *            - iterator of the list
	 * @return - next value in the list
	 */
	public static long next(Iterator<Long> it) {
		return it.hasNext() ? it.next() : 0;
	}

	/**
	 * @return returns if the number is zero
	 */
	boolean isZero() {
		for (Long l : this.value) {
			if (l != 0)
				return false;
		}
		return true;
	}

	/**
	 * Shift operation for Karatsuba
	 * 
	 * @param z2
	 *            - LinkedList in which numbers need to be shifted
	 * @param n
	 *            - shift by n
	 * @return - new List of Numbers with the shift
	 */
	static LinkedList<Long> shift(LinkedList<Long> a, long n) {
		for (int i = 0; i < n; i++) {
			a.addFirst((long) 0);
		}
		return a;
	}

	/**
	 * Divide the given number ny two
	 * 
	 * @param a
	 *            - LinkedList of the number to be divided
	 * @return - LinkedList of the resulting number
	 */
	private static LinkedList<Long> DivideByTwo(LinkedList<Long> a) {

		LinkedList<Long> res = new LinkedList<Long>();

		Stack<Long> valStack = new Stack<Long>();
		for (Long l : a)
			valStack.push(l);

		Long carry = (long) 0;
		while (!valStack.isEmpty()) {
			Long l = valStack.pop();

			long div = carry * base + l;
			carry = div % 2;
			res.addFirst(div / 2);

		}
		return res;
	}

	/**
	 * Checks if the number is even number
	 * 
	 * @param n
	 *            - Number to be checked
	 * @return - true if even, false otherwise
	 */
	private static boolean isEven(Num n) {
		if (base % 2 == 0) {
			System.out.println(n.value.getFirst());
			return n.value.getFirst() % 2 == 0 ? true : false;
		} else {
			long sum = 0;
			for (Long a : n.value)
				sum += a;
			System.out.println(sum);
			return sum % 2 == 0 ? true : false;
		}
	}

	/**
	 * Returens the middle two numbers between the start and the end number
	 * 
	 * @param start
	 *            - starting number
	 * @param end
	 *            - end number
	 * @return - List of two Numbers
	 */
	private static LinkedList<Num> getMiddleNumbers(Num start, Num end) {
		LinkedList<Num> res = new LinkedList<Num>();
		LinkedList<Long> mid = DivideByTwo(add(start, end).value);
		Num midNum = new Num(mid, false);
		midNum.removeLeadingZeros();

		LinkedList<Long> midPlusOne = new LinkedList<Long>(midNum.value);
		Num midNumPlusOne = new Num(midPlusOne, false);
		midNumPlusOne = add(midNumPlusOne, new Num(1));

		res.add(midNum);
		res.add(midNumPlusOne);
		return res;
	}

}
