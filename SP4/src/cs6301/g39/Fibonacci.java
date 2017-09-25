package cs6301.g39;

import java.math.BigInteger;
import cs6301.g00.Timer;

public class Fibonacci {

	/**
	 * @param n
	 * @return  : nth Fibonacci number
	 */
	static BigInteger linearFibonacci(int n){
		BigInteger[] F = new BigInteger[n+1];
		F[0] = BigInteger.ZERO;
		F[1] = BigInteger.ONE;

		for(int i=2; i<=n;i++){
			F[i] = F[i-1].add(F[i-2]);
		}
		return F[n];
	}

	/**
	 * @param n
	 * @return  : nth Fibonacci number
	 */
	static BigInteger logFibonacci(int n){
		BigInteger[][] matrix = new BigInteger[][]{{BigInteger.ONE,BigInteger.ONE},{BigInteger.ONE,BigInteger.ZERO}};
		power(matrix, n-1);
		return matrix[0][0];

	}

	/**
	 * @param mat : matrix whose power is to be found
	 * @param p	  : required power of the matrix
	 */
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

	/**
	 * @param u : first matrix to be multiplied
	 * @param v : second matrix to be multiplied
	 */
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

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.start();
		BigInteger linearResult = linearFibonacci(40000);
		timer.end();
		System.out.println("nth Fibonacci number using linear algorithm : " + linearResult.toString());
		System.out.println("Time taken is:");
		System.out.println(timer);
		
		timer = new Timer();
		timer.start();
		BigInteger logResult = logFibonacci(40000);
		timer.end();
		System.out.println("nth Fibonacci number using log algorithm : " + logResult.toString());
		System.out.println("Time taken is:");
		System.out.println(timer);
		
	}

}
