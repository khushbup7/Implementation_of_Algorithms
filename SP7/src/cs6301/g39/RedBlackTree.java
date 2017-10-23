/** Class implementing Red-Black Trees and basic operations i.e. add and remove on it
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/10/13
 *  Ver 1.1: 2017/10/22
 *  Usage: RedBlackTree t = new RedBlackTree();
		t.add(int x);
		t.remove(int x);
 */
package cs6301.g39;

import java.util.Iterator;
import java.util.Scanner;

public class RedBlackTree<T extends Comparable<? super T>> extends BST<T> {
	
	// Entry of a RedBlackTree. Contains additional info - color of the node
    static class Entry<T> extends BST.Entry<T> {
        boolean isRed;
        
        Entry(T x, BST.Entry<T> left, BST.Entry<T> right) {
            super(x, left, right);
            isRed = true;
        }
    }

    //Constructor
    RedBlackTree() {
    	super();
    	this.creator = new BST.EntryCreator<T>() {

		@Override
		public BST.Entry<T> createNewEntry(T x, BST.Entry<T> left, BST.Entry<T> right) {
				return new RedBlackTree.Entry<T>(x, left, right);
			}
		};
    }
    
    public RedBlackTree(EntryCreator<T> creator) {
		super(creator);
	}
    
    //method to add element x to the tree
    public boolean add(T x) {
    	boolean b = super.add(x);
    	if(!b)
    		return b;
    	else {
    		Entry<T> parent = (stack != null && !stack.isEmpty()) ? (Entry<T>) stack.peek() : null;
    		if(parent==null )
    		{
    			((RedBlackTree.Entry<T>) root).isRed = false;
    			return true;
    		}
    		
    		if(!parent.isRed)  //New node is craeted red, so if parent is black, nothing needs to be changed
    			return true;
    		else {			   //parent is red, tree needs to be repaired
    			Entry<T> t = (Entry<T>) (parent.element.compareTo(x) > 0 ? parent.left : parent.right);
    			repair(t);
    		}
    		
    		//Root is changed to black after every repair operation
    		((RedBlackTree.Entry<T>) root).isRed = false;
    	}
		return true;
    	
    }
    
    //Method to repair the tree if need to after the add operation
    void repair(Entry<T> t) {
    	Entry<T> parent = (Entry<T>)stack.pop();
        Entry<T> grandParent = (Entry<T>)stack.pop();
    	Entry<T> uncle = (Entry<T>)(grandParent.left == parent ? grandParent.right : grandParent.left);
    	
    	if(parent == root) //root is red now, but is colored black by add after repair
    		return;
    	
    	while(t.isRed) {
    		if(t == root || parent == root || !parent.isRed)
    			return;
    		
    		if(uncle != null && uncle.isRed) { //if uncle of t is red : case-1
    			parent.isRed = false;
    			uncle.isRed = false;
    			grandParent.isRed = true;
    			t = grandParent;
    			continue;
    		}
    		else {  //case-2 & 3 : uncle of t is black
    			Entry<T> t1,t2;
    			if(parent == grandParent.left && t == parent.left) {//LL rotation
    				t2 = (Entry<T>)rightRotate(grandParent);
    				if(!stack.isEmpty() && stack.peek() != null)
    					stack.peek().left = t2;
    				parent.isRed = false;
    				grandParent.isRed =  true;
    			}
    			else if(parent == grandParent.right && t == parent.right) { //RR rotation
    				t2 = (Entry<T>)leftRotate(grandParent);
    				if(!stack.isEmpty() && stack.peek() != null)
    					stack.peek().right = t2;
    				parent.isRed = false;
    				grandParent.isRed =  true;
    			}
    			else if(parent == grandParent.left && t == parent.right) { //LR rotation
    				t1 = (Entry<T>)leftRotate(parent);
    				grandParent.left = t1;
    				t2 = (Entry<T>)rightRotate(grandParent);
    				parent.isRed = false;
    				grandParent.isRed =  true;
    			}
    			else {//RL rotation
    				t1 = (Entry<T>)rightRotate(parent);
    				grandParent.right = t1;
    				t2 = (Entry<T>)leftRotate(grandParent);
    				parent.isRed = false;
    				grandParent.isRed =  true;
    			}
    			
    			//update the root node, if need to (when the rotation was performed on root node)
    			if(stack.peek() == null)
    				root = t2;
    		}
    	}
    	
    }
    
    //method to remove node x from the tree
    public T remove(T x) {
    	T elem= super.remove(x);
    	
    	//if the element was not present in the tree, return.
    	if(elem == null)
    		return elem;
    	
    	Entry<T> t = (Entry<T>)stack.pop();
    	
    	//If the element was present, the tree to be fixed after remove opeation
    	fix(t);
    	
    	return null;
    }
    
    //method to fix the tree after remove operation
    void fix(Entry<T> t) {
    	
    	Entry<T> parent = (Entry<T>)stack.pop();
    	Entry<T> sibling = (Entry<T>)(t == parent.left ? parent.right : parent.left);
    	
    	while(t!=root) {
    		if(t.isRed){   //if the node is red, recolor it to black and remove it
    			t.isRed = false;
    			return;
    		}
    		else if(sibling.isRed) { //Case-5, if the sibling is red
    			boolean temp;
    			if(sibling == parent.left) {
    				rightRotate(sibling);
    				temp = sibling.isRed;
    				sibling.isRed = parent.isRed;
    				parent.isRed = temp;
    			}
    			else {
    				leftRotate(sibling);
    				temp = sibling.isRed;
    				sibling.isRed = parent.isRed;
    				parent.isRed = temp;
    			}
    			sibling = (Entry<T>)(t == parent.left ? parent.right : parent.left);
    		}
    		else if(!sibling.isRed) { //if the sibling is black
    			Entry<T> lcSibling = ((Entry<T>)sibling.left);
    			Entry<T> rcSibling = ((Entry<T>)sibling.right);
    			
    			//if both the children of a sibling node are also black
    			if(lcSibling!=null && rcSibling!= null && !lcSibling.isRed && !rcSibling.isRed) {
    				sibling.isRed = true;
        			t = parent;
    			}
    			
    			//if one of the child of the sibling node is red
    			else if((lcSibling!=null && !lcSibling.isRed) || (rcSibling!= null && !rcSibling.isRed)) { 
    				Entry<T> rc = lcSibling.isRed ? lcSibling : rcSibling;
    				boolean temp;
    				if(sibling == parent.left && rc == sibling.left) {//LL rotation
        				rightRotate(parent);
        				temp = sibling.isRed;
        				sibling.isRed = parent.isRed;
        				parent.isRed = temp;
        				rc.isRed =  false;
        				return;
        			}
    				if(sibling == parent.right && rc == sibling.right) {//RR rotation
        				leftRotate(parent);
        				temp = sibling.isRed;
        				sibling.isRed = parent.isRed;
        				parent.isRed = temp;
        				rc.isRed =  false;
        				return;
        			}
    				if(sibling == parent.left && rc == sibling.right) {//LR rotation
        				leftRotate(sibling);
        				temp = sibling.isRed;
        				sibling.isRed = rc.isRed;
        				rc.isRed = temp;
        				
        				rightRotate(parent);
        				temp = sibling.isRed;
        				sibling.isRed = parent.isRed;
        				parent.isRed = temp;
        				rc.isRed =  false;
        				return;
        			}
        			else {//RL rotation
        				rightRotate(sibling);
        				temp = sibling.isRed;
        				sibling.isRed = rc.isRed;
        				rc.isRed = temp;
        				
        				leftRotate(parent);
        				temp = sibling.isRed;
        				sibling.isRed = parent.isRed;
        				parent.isRed = temp;
        				rc.isRed =  false;
        				return;
        			}
    			}
    			else {  //both children of sibling are red
    				return;
    			}
    			
    		}
    		
    		else {
    			return;
    		}
    		
    	}
    	
    }
    
    //main method
    public static void main(String[] args) {
		RedBlackTree<Integer> t = new RedBlackTree<>();
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
    
    @Override
    public void printTree() {
		System.out.print("[" + size + "]");
		printTree((Entry<T>)root);
		System.out.println();
	}

	// Inorder traversal of tree
	void printTree(Entry<T> node) {
		if (node != null) {
			printTree(node.left);
			System.out.print(" " + node.isRed);
			printTree(node.right);
		}
	}

}

