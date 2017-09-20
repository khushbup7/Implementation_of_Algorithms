package cs6301.g39;

import java.math.BigInteger;

public class Fibonacci {

	static BigInteger expFibonacci(int n){
		BigInteger b = expFibonacciHelper(BigInteger.valueOf(n));
		return b;
	}

	static BigInteger expFibonacciHelper(BigInteger num){
		if(num.equals(BigInteger.ZERO) || num.equals(BigInteger.ONE))
			return num;
		return expFibonacciHelper(num.subtract(BigInteger.ONE)).add(expFibonacciHelper(num.subtract(new BigInteger("2"))));
	}

	static BigInteger linearFibonacci(int n){
		BigInteger[] F = new BigInteger[n+1];
		F[0] = BigInteger.ZERO;
		F[1] = BigInteger.ONE;

		for(int i=2; i<=n;i++){
			F[i] = F[i-1].add(F[i-2]);
		}
		return F[n];
	}

	static BigInteger logFibonacci(int n){
		// One matrix of 2x1 for V1
		// One matrix of 2X2 raised to power n-1
		// step 1 call power function and that returns 2x2 matrix
		// step 2 call multiply function and that will return 2X1 matrix
		//return first element of 2x1 matrix which is your ans
		BigInteger[][] matrix = new BigInteger[][]{{BigInteger.ONE,BigInteger.ONE},{BigInteger.ONE,BigInteger.ZERO}};
		BigInteger[][] v1 = new BigInteger[][]{{BigInteger.ONE},{BigInteger.ZERO}};
		power(matrix, n-1);
		multiplyAgain(matrix, v1); // not needed but that's how the expression is--check again
		return v1[0][0];

	}

	static void power(BigInteger[][] mat, int p){
		BigInteger[][] matrix = new BigInteger[][]{{BigInteger.ONE,BigInteger.ONE},{BigInteger.ONE,BigInteger.ZERO}};
		if( p == 0 || p == 1)
			return;
		power(mat, p/2);
		product(mat, mat);
		if (p%2 != 0){
			product(mat, matrix);
		   }


	}

	static void product(BigInteger u[][], BigInteger v[][])
	{
		BigInteger x =  u[0][0].multiply(v[0][0]).add(u[0][1].multiply(v[1][0]));
		BigInteger y =  u[0][0].multiply(v[0][1]).add(u[0][1].multiply(v[1][1]));
		BigInteger z =  u[1][0].multiply(v[0][0]).add(u[1][1].multiply(v[1][0]));
		BigInteger w =  u[1][0].multiply(v[0][1]).add(u[1][1].multiply(v[1][1]));

		u[0][0] = x;
		u[0][1] = y;
		u[1][0] = z;
		u[1][1] = w;
	}

	static void multiplyAgain(BigInteger u[][], BigInteger v[][]){
		BigInteger x =  u[0][0].multiply(v[0][0]).add(u[0][1].multiply(v[1][0]));
		BigInteger z =  u[1][0].multiply(v[0][0]).add(u[1][1].multiply(v[1][0]));

		v[0][0] = x;
		v[1][0] = z;
	}

	public static void main(String[] args) {
		BigInteger exp_result =expFibonacci(10);
		BigInteger linear_result =linearFibonacci(197);
		BigInteger log_result = logFibonacci(297);
		System.out.println("Fib	exp_result: " + exp_result.toString());
		System.out.println("Fib	linear_result: " + linear_result.toString());
		System.out.println("Fib	log_result: " + log_result.toString());
	}

}
