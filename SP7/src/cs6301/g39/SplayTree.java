/** Class to implement Splay Tree
 * @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 * Ver 1.0: 2017/10/21
*/
package cs6301.g39;

import java.util.Scanner;

public class SplayTree<T extends Comparable<? super T>> extends BST<T> {

	SplayTree() {
		super();
	}
	
	// adds element x to the tree
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

	// removes element x from the tree if found 
	public T remove(T x) {
		T removed = super.remove(x);
		if (removed != null && stack != null && !stack.isEmpty() && stack.peek() != null) { 
			if(removed != x){
				Entry<T> parent = (stack != null && !stack.isEmpty()) ? (Entry<T>) stack.peek() : root;
				if(x.compareTo(parent.element) < 0){
					if(parent == root) 
						return null;
					splay(parent.left);

				}
				else{
					if(parent == root) 
						return null;
					splay(parent.right);
				}
			}
			else{
				Entry<T> parent = (stack != null && !stack.isEmpty()) ? (Entry<T>) stack.pop() : root;
				splay(parent);
				return removed;
			}
		}
		return null;
	}

	// gets the recently updated element to the root
	public void splay(Entry<T> x) {
		if (x.element == root.element) return ;
		while(stack.peek() != null) {
			if(x == root.left) { // case L
				root = rightRotate(root);
				break;
			} 
			else if(x == root.right) { // case R
				root = leftRotate(root);
				break;
			}
			else {
				Entry<T> parent = !stack.isEmpty() ? stack.pop() : null;
				Entry<T> grandparent = !stack.isEmpty() ? stack.pop() : null;
				Entry<T> temp = null ;
				Entry<T> attachment = null;
				if(grandparent.left == parent && parent.left == x) { // case LL
					temp = rightRotate(grandparent);
					attachment = stack.peek();
					if(attachment == null) {
						root = rightRotate(temp);
					}
					else {
						temp = rightRotate(temp);
						attach(attachment, temp);
					}
				}
				else if (grandparent.right == parent && parent.right == x) { // case RR
					temp = leftRotate(grandparent);
					attachment = stack.peek();
					if(attachment == null) {
						root = leftRotate(temp);
					}
					else {
						temp = leftRotate(temp);
						attach(attachment, temp);
					}
				}
				else if(grandparent.left == parent && parent.right == x) {	// case LR
					temp = (Entry<T>) leftRotate(grandparent.left);
					grandparent.left = temp;
					attachment = stack.peek();
					if(attachment == null) {
						root = rightRotate(grandparent);
					}
					else {
						temp = rightRotate(grandparent);
						attach(attachment, temp);
					}
				}
				else if(grandparent.right == parent && parent.left == x) { // case RL
					temp= (Entry<T>) rightRotate(grandparent.right);
					grandparent.right = temp;
					attachment = stack.peek();
					if(attachment == null) {
						root = leftRotate(grandparent);
					}
					else {
						temp = leftRotate(grandparent);
						attach(attachment, temp);
					}
				}


			}
		}
	}
	
	// attach the subtree back to the BST after rotations
	public void attach(Entry<T> attachment, Entry<T> x){
		if(x.element.compareTo(attachment.element) > 0)
			attachment.right = x;
		else attachment.left = x;
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