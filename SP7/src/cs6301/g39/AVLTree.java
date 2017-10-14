
/** Starter code for AVL Tree
 */
package cs6301.g39;

import java.util.Iterator;
import java.util.Scanner;

public class AVLTree<T extends Comparable<? super T>> extends BST<T> {
	static class Entry<T> extends BST.Entry<T> {
		int height;

		Entry(T x, Entry<T> left, Entry<T> right) {
			super(x, left, right);
			height = 0;
		}

		private int getHeight() {
			return (this == null) ? 0 : height;
		}

		private void updateHeight() {
			height = getMaxChildHeight() + 1;
		}

		private int getMaxChildHeight() {
			int leftChildHeight = ((Entry<T>) this.left).getHeight();
			int rightChildHeight = ((Entry<T>) this.right).getHeight();
			return Math.max(leftChildHeight, rightChildHeight);
		}

		private int getBalance() {
			int leftChildHeight = ((Entry<T>) this.left).getHeight();
			int rightChildHeight = ((Entry<T>) this.right).getHeight();
			return Math.abs(leftChildHeight - rightChildHeight);
		}
	}

	AVLTree() {
		super();
	}

	public boolean add(T x) {
		if (super.add(x)) {
 			Entry<T> t = (stack != null && !stack.isEmpty()) ? (Entry<T>) stack.pop() : null;
// 			AVLTree.Entry<T> node = new 
//			if (t != null && t.getHeight() == 0) {
//				t.height = 1;
//				updateAncestors(x);
//			}
		}
		return true;
	}

	private void updateAncestors(T x) {
		while (stack.peek() != null) {
			Entry<T> t = (Entry<T>) stack.pop();

			t.updateHeight();

			int balance = t.getBalance();
			if (balance > 1 && x.compareTo(t.left.element) < 0) {
				t = (Entry<T>) rightRotate(t);
				((Entry<T>) t.right).updateHeight();
			}

		}

	}

	public static void main(String[] args) {
		AVLTree<Integer> t = new AVLTree<Integer>();
		Scanner in = new Scanner(System.in);
		try {
			while (in.hasNext()) {
				int x = in.nextInt();
				if (x > 0) {
					System.out.print("Add " + x + " : ");
					t.add(x);
					t.printTree();
				} else if (x < 0) {
					System.out.print("Remove " + x + " : ");
					t.remove(-x);
					t.printTree();
				} else {
					/*
					 * Comparable[] arr = t.toArray(); System.out.print("Final: "); for(int i=0;
					 * i<t.size; i++) { System.out.print(arr[i] + " "); } System.out.println();
					 */

					Iterator<Integer> itr = t.iterator();
					while (itr.hasNext()) {
						System.out.print(" " + itr.next());
					}
					return;
				}
			}
		} finally {
			in.close();
		}
	}

}
