/** Class to implement AVL Tree
 * @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 * Ver 1.0: 2017/10/19
*/
package cs6301.g39;

import java.util.Scanner;

public class AVLTree<T extends Comparable<? super T>> extends BST<T> {
	
	// Entry of a AVLTree. Contains additional info - height of the node
	static class Entry<T> extends BST.Entry<T> {
		int height;

		Entry(T x, BST.Entry<T> left, BST.Entry<T> right) {
			super(x, left, right);
			height = 0;
		}

		private int getHeight() {
			return height;
		}

		// updates height of the node from child nodes
		private void updateHeight() {
			if (left == null && right == null) {
				height = 0;
				return;
			}
			height = getMaxChildHeight() + 1;
		}

		// gets height of the child which has maximum height of the two 
		private int getMaxChildHeight() {
			int leftChildHeight = (this.left == null) ? 0 : ((Entry<T>) this.left).height;
			int rightChildHeight = (this.right == null) ? 0 : ((Entry<T>) this.right).height;
			return Math.max(leftChildHeight, rightChildHeight);
		}

		// returns balance of the tree. Balance = height of left child - height of right child
		private int getBalance() {
			int leftChildHeight = (this.left == null) ? 0 : ((Entry<T>) this.left).height;
			int rightChildHeight = (this.right == null) ? 0 : ((Entry<T>) this.right).height;
			return leftChildHeight - rightChildHeight;
		}
	}

	AVLTree() {
		super();
		this.creator = new BST.EntryCreator<T>() {

			@Override
			public BST.Entry<T> createNewEntry(T x, BST.Entry<T> left, BST.Entry<T> right) {
				return new AVLTree.Entry<T>(x, left, right);
			}

		};
	}

	public AVLTree(EntryCreator<T> creator) {
		super(creator);
	}

	// adds element x to the tree
	public boolean add(T x) {
		if (super.add(x)) {
			Entry<T> t = (stack != null && !stack.isEmpty()) ? (Entry<T>) stack.pop() : null;
			if (t != null && t.getHeight() == 0) {
				t.height = 1;
				balanceAfterAdd(x);
			}
		}
		return true;
	}

	// called if the tree has an imbalance after add
	private void balanceAfterAdd(T x) {
		Entry<T> prev = null;
		while (stack.peek() != null) {
			Entry<T> t = (Entry<T>) stack.pop();

			if (prev != null && t.element.compareTo(prev.element) > 0)
				t.left = prev;
			if (prev != null && t.element.compareTo(prev.element) < 0)
				t.right = prev;

			t.updateHeight();
			int balance = t.getBalance();

			if (balance > 1 || balance < -1) {
				Entry<T> child = null;

				if (balance > 1 && x.compareTo(t.left.element) < 0) {
					t = (Entry<T>) rightRotate(t);
					child = (Entry<T>) t.right;
				}
				if (balance < -1 && x.compareTo(t.right.element) > 0) {
					t = (Entry<T>) leftRotate(t);
					child = (Entry<T>) t.left;
				}
				if (balance > 1 && x.compareTo(t.left.element) > 0) {
					child= (Entry<T>) leftRotate(t.left);
					t.left = child;
					t = (Entry<T>) rightRotate(t);
				}
				if (balance < -1 && x.compareTo(t.right.element) < 0) {
					child = (Entry<T>) rightRotate(t.right);
					t.right = child;
					t = (Entry<T>) leftRotate(t);
				}

				child.updateHeight();
				t.updateHeight();
				prev = t; // track child to update parent
				if (stack.peek() == null)
					root = t;
			}
		}
	}

	// remove element x form the tree
	public T remove(T x) {
		T rem = super.remove(x);
		if (rem != null && stack != null && !stack.isEmpty() && stack.peek() != null) {
			Entry<T> t = (Entry<T>) stack.pop();
			int prevHeight = t.getHeight();
			t.updateHeight();
			if (prevHeight != t.getHeight())
				balanceAfterDelete();
		}
		return rem;
	}

	// called if the tree has an imbalance after remove
	private void balanceAfterDelete() {
		while (stack.peek() != null) {
			Entry<T> t = (Entry<T>) stack.pop();
			t.updateHeight();
			int balance = t.getBalance();

			if (balance > 1 || balance < -1) {
				Entry<T> child = null;
				if (balance > 1 && ((Entry<T>) t.left).getBalance() >= 0) {
					t = (Entry<T>) rightRotate(t);
					child = (Entry<T>) t.right;
				}

				if (balance > 1 && ((Entry<T>) t.left).getBalance() < 0) {
					child = (Entry<T>) leftRotate(t.left);
					t.left = child;
					t = (Entry<T>) rightRotate(t);
				}

				if (balance < -1 && ((Entry<T>) t.right).getBalance() <= 0) {
					t = (Entry<T>) leftRotate(t);
					child = (Entry<T>) t.left;
				}
				if (balance < -1 && ((Entry<T>) t.right).getBalance() > 0) {
					child = (Entry<T>) rightRotate(t.right);
					t.right = child;
					t = (Entry<T>) leftRotate(t);
				}

				child.updateHeight();
				t.updateHeight();
				if (stack.peek() == null)
					root = t;
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
					
					 Comparable<?>[] arr = t.toArray(); 
					 System.out.print("Final: "); 
					 for(int i=0; i<t.size; i++) { 
						 System.out.print(arr[i] + " "); 
					 }
					 System.out.println();
					
					return;
				}
			}
		} finally {
			in.close();
		}
	}

}
