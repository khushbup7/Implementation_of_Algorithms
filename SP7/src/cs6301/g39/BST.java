/** @author 
 *  Binary search tree (starter code)
 **/

package cs6301.g39;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class BST<T extends Comparable<? super T>> implements Iterable<T> {
	Stack<Entry<T>> stack;
	protected EntryCreator<T> creator = null;

	static class Entry<T> {
		T element;
		Entry<T> left, right;

		Entry(T x, Entry<T> left, Entry<T> right) {
			this.element = x;
			this.left = left;
			this.right = right;
		}
	}

	Entry<T> root;
	int size;

	public BST() {
		root = null;
		size = 0;
		this.creator = new EntryCreator<T>() {

			@Override
			public Entry<T> createNewEntry(T x, Entry<T> left, Entry<T> right) {
				return new Entry<T>(x, left, right);
			}

		};
	}

	public BST(EntryCreator<T> creator) {
		this.creator = creator;
	}

	/**
	 * TO DO: Is x contained in tree?
	 */
	public boolean contains(T x) {
		Entry<T> t = find(x);
		return (t != null && t.element == x);
	}

	/**
	 * TO DO: Is there an element that is equal to x in the tree? Element in tree
	 * that is equal to x is returned, null otherwise.
	 */
	public T get(T x) {
		Entry<T> t = find(x);
		if (t != null && t.element == x)
			return t.element;
		else
			return null;
	}

	public Entry<T> find(T x) {
		stack = new Stack<Entry<T>>();
		stack.push(null);
		return find(root, x);
	}

	public Entry<T> find(Entry<T> t, T x) {
		if (t == null || t.element == x)
			return t;

		while (true) {
			if (x.compareTo(t.element) < 0) {
				if (t.left == null)
					break;
				else {
					stack.push(t);
					t = t.left;
				}
			} else if (x == t.element)
				break;
			else { // x > t.element
				if (t.right == null)
					break;
				else {
					stack.push(t);
					t = t.right;
				}
			}
		}

		return t;
	}

	/**
	 * TO DO: Add x to tree. If tree contains a node with same key, replace element
	 * by x. Returns true if x is a new element added to tree.
	 */
	public boolean add(T x) {
		Entry<T> newNode = this.creator.createNewEntry(x, null, null);

		if (root == null) {
			root = newNode;
			size = 1;
			return true;
		}

		Entry<T> t = find(x);
		if (x == t.element) {
			t.element = x; // replacing the element
			return false;
		} else if (x.compareTo(t.element) < 0)
			t.left = newNode;
		else
			t.right = newNode;

		stack.push(t);
		size++;
		return true;
	}

	/**
	 * TO DO: Remove x from tree. Return x if found, otherwise return null
	 */
	public T remove(T x) {

		if (root == null)
			return null;

		Entry<T> t = find(x);

		if (t.element != x)
			return null;

		T result = t.element;
		if (t.left == null || t.right == null)
			bypass(t);
		else { // t has 2 children
			stack.push(t);
			Entry<T> minRight = find(t.right, t.element);
			t.element = minRight.element;
			bypass(minRight);
		}
		size--;
		return result;
	}

	private void bypass(Entry<T> t) {
		Entry<T> pt = stack.peek();
		Entry<T> c = t.left == null ? t.right : t.left;

		if (pt == null) // t is root
			root = c;
		else if (pt.left == t) // t is a left child
			pt.left = c;
		else // t is a right child
			pt.right = c;
	}

	/**
	 * TO DO: Iterate elements in sorted order of keys
	 */
	public Iterator<T> iterator() {
		return new BSTIterator<>(this);
	}

	Entry<T> rightRotate(Entry<T> root) {
		Entry<T> left = root.left;
		Entry<T> temp = left.right;

		// Perform rotation
		left.right = root;
		root.left = temp;

		return left;
	}
	
	Entry<T> leftRotate(Entry<T> root) {
		Entry<T> right = root.right;
		Entry<T> temp = right.left;

		// Perform rotation
		right.left = root;
		root.right = temp;

		return right;
	}

	protected static interface EntryCreator<T extends Comparable<? super T>> {
		public Entry<T> createNewEntry(T x, Entry<T> left, Entry<T> right);
	}

	public static void main(String[] args) {
		BST<Integer> t = new BST<>();
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

	// TODO: Create an array with the elements using in-order traversal of tree
	public Comparable<?>[] toArray() {
		Comparable<?>[] arr = new Comparable[size];
		/* write code to place elements in array here */
		Iterator<T> it = this.iterator();
		int i = 0;
		while (it.hasNext()) {
			arr[i] = it.next();
		}

		return arr;
	}

	public void printTree() {
		System.out.print("[" + size + "]");
		printTree(root);
		System.out.println();
	}

	// Inorder traversal of tree
	void printTree(Entry<T> node) {
		if (node != null) {
			printTree(node.left);
			System.out.print(" " + node.element);
			printTree(node.right);
		}
	}

}
/*
 * Sample input: 1 3 5 7 9 2 4 6 8 10 -3 -6 -3 0
 * 
 * Output: Add 1 : [1] 1 Add 3 : [2] 1 3 Add 5 : [3] 1 3 5 Add 7 : [4] 1 3 5 7
 * Add 9 : [5] 1 3 5 7 9 Add 2 : [6] 1 2 3 5 7 9 Add 4 : [7] 1 2 3 4 5 7 9 Add 6
 * : [8] 1 2 3 4 5 6 7 9 Add 8 : [9] 1 2 3 4 5 6 7 8 9 Add 10 : [10] 1 2 3 4 5 6
 * 7 8 9 10 Remove -3 : [9] 1 2 4 5 6 7 8 9 10 Remove -6 : [8] 1 2 4 5 7 8 9 10
 * Remove -3 : [8] 1 2 4 5 7 8 9 10 Final: 1 2 4 5 7 8 9 10
 * 
 */
