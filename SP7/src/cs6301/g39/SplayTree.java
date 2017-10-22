/** Starter code for Splay Tree
 */
package cs6301.g39;

import java.util.Iterator;
import java.util.Scanner;


public class SplayTree<T extends Comparable<? super T>> extends BST<T> {

	SplayTree() {
		super();
	}

	public boolean add(T x) {
		if (super.add(x)) {
			Entry<T> t = (stack != null && !stack.isEmpty()) ? (Entry<T>) stack.peek() : null;
			if (t != null) {
				if(t.element == x)
					splay(t);
				else if(t.element.compareTo(x) > 0){
					splay(t.left);
				}
				else splay(t.right);
			}
		}
		return true;
	}

	public T remove(T x) {
		// find if found splay(parent) and return removed
		//  else splay the last node reached
		return null;
	}

	public void splay(Entry<T> x) {
		if (x.element == root.element) return ;
		while(stack.peek() != null) {
			/* if t is left (or right) child of root then // Zig 
				Perform a single rotation [R] (or [L]) at
				root to bring t to the root of the tree */
			if(x == root.left) {
				root = rightRotate(root);
				break;
			} 
			else if(x == root.right) {
				root = leftRotate(root);
				break;
			}
			else {
				Entry<T> parent = !stack.isEmpty() ? stack.pop() : null;
				Entry<T> grandparent = !stack.isEmpty() ? stack.pop() : null;
				Entry<T> temp = null ;
				Entry<T> attachment = null;
				if(grandparent.left == parent && parent.left == x) {
					temp = rightRotate(grandparent);
					attachment = stack.peek();
					if(attachment == null) root = rightRotate(temp);
					else attachment.left = rightRotate(temp);
				}
				else if (grandparent.right == parent && parent.right == x) {
					temp = leftRotate(grandparent);
					attachment = stack.peek();
					if(attachment == null) root = leftRotate(temp);
					else attachment.right = leftRotate(temp);
				}
				else if(grandparent.left == parent && parent.right == x) {	
					temp = (Entry<T>) leftRotate(grandparent.left);
					grandparent.left = temp;
					attachment = stack.peek();
					if(attachment == null) {
						root = rightRotate(grandparent);
					}
					else {
						temp = rightRotate(grandparent);
						if(temp.element.compareTo(attachment.element) > 0)
							attachment.right = temp;
						else attachment.left = temp;
					}
				}
				else if(grandparent.right == parent && parent.left == x) {
					temp= (Entry<T>) rightRotate(grandparent.right);
					grandparent.right = temp;
					attachment = stack.peek();
					if(attachment == null) {
						root = leftRotate(grandparent);
					}
					else {
						temp = leftRotate(grandparent);
						if(temp.element.compareTo(attachment.element) > 0)
							attachment.right = temp;
						else attachment.left = temp;
					}
				}


			}
		}
	}

	public static void main(String[] args) {
		SplayTree<Integer> t = new SplayTree<Integer>();
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