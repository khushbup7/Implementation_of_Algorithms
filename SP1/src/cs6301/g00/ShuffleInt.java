/** @author rbk : based on algorithm described in a book
 *  Ver 1.0: 2017/08/08
 */

package cs6301.g00;
import java.util.Random;

/* Shuffle the elements of an array arr[from..to] randomly */
public class ShuffleInt {

    public static void shuffle(int[] arr) {
	shuffle(arr, 0, arr.length-1);
    }

    public static void shuffle(int[] arr, int from, int to) {
	int n = to - from  + 1;
	Random rand = new Random();
	for(int i=1; i<n; i++) {
	    int j = rand.nextInt(i);
	    swap(arr, i+from, j+from);
	}
    }

    static void swap(int[] arr, int x, int y) {
	    int tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
    }

    static void printArray(int[] arr, String message) {
	printArray(arr, 0, arr.length-1, message);
    }

    static void printArray(int[] arr, int from, int to, String message) {
	System.out.print(message);
	for(int i=from; i<=to; i++) {
	    System.out.print(" " + arr[i]);
	}
	System.out.println();
    }

    public static void main(String[] args) {
	int n = 10;
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
	    arr[i] = i;
	}
	printArray(arr, "Before:");
	shuffle(arr);
	printArray(arr, "After:");
    }
}

/** Sample output:
Before: 0 1 2 3 4 5 6 7 8 9
After: 3 7 9 8 2 4 6 1 0 5
*/