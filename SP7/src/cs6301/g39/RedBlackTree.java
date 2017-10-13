
/** Starter code for Red-Black Tree
 */
package cs6301.g39;

import java.util.Comparator;

public class RedBlackTree<T extends Comparable<? super T>> extends BST<T> {
    static class Entry<T> extends BST.Entry<T> {
        boolean isRed;
        Entry(T x, Entry<T> left, Entry<T> right) {
            super(x, left, right);
            isRed = true;
        }
    }

    RedBlackTree() {
	super();
    }
    
    public boolean add(T x) {
    	boolean b = super.add(x);
    	if(!b)
    		return b;
    	else {
    		RedBlackTree.Entry<T> parent = (RedBlackTree.Entry<T>)stack.peek();
    		if(!parent.isRed)  //New node is craeted red, so if parent is black, nothing needs to be changed
    			return true;
    		else {//parent is red, tree needs to be repaired
    			RedBlackTree.Entry<T> t = (RedBlackTree.Entry<T>) (parent.left.element.compareTo(x) == 0 ? parent.left : parent.right);
    			repair(t);
    			//TODO color the root black after every repair
    		}
    	}
		return false;
    	
    }
    
    public void repair(RedBlackTree.Entry<T> t) {
    	RedBlackTree.Entry<T> parent = (RedBlackTree.Entry<T>)stack.pop();
    	RedBlackTree.Entry<T> grandParent = (RedBlackTree.Entry<T>)stack.pop();
    	RedBlackTree.Entry<T> uncle = (RedBlackTree.Entry<T>)(grandParent.left == parent ? grandParent.right : grandParent.left);
    	
    	if(parent == root) //root is red now, but is colored black by add after repair
    		return;
    	
    	while(t.isRed) {
    		if(t == root || parent == root || !parent.isRed)
    			return;
    		
    		if(uncle.isRed) { //if u_t is red : case-1
    			parent.isRed = false;
    			uncle.isRed = false;
    			grandParent.isRed = true;
    			t = grandParent;
    			continue;
    		}
    		else {  //case-2 & 3 : uncle is black
    			if(parent == grandParent.left && t == parent.left) {//LL - 2a
    				
    			}
    			else if(parent == grandParent.right && t == parent.right) { //RR - 2b
    				
    			}
    			else if(parent == grandParent.left && t == parent.right) { //LR - 3a
    				
    			}
    			else {//RL - 3b
    				
    			}
    		}
    	}
    	
    }
}

