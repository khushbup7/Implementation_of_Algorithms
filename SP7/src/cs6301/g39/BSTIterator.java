package cs6301.g39;

import java.util.Iterator;
import java.util.Stack;

public class BSTIterator<T extends Comparable<? super T>> implements Iterator<T> {

	Stack<BST.Entry<T>> itrStack;
	BST.Entry<T> current;

	BSTIterator(BST<T> bst) {
		itrStack = new Stack<BST.Entry<T>>();
		current = bst.root;
		while (current != null) {
			itrStack.push(current);
			current = current.left;
		}
	}

	@Override
	public boolean hasNext() {
		return !itrStack.isEmpty();
	}

	@Override
	public T next() {
		BST.Entry<T> nextNode = null;
		if (!itrStack.isEmpty()) {
			nextNode = itrStack.pop();
			current = nextNode.right;
			while (current != null) {
				itrStack.push(current);
				current = current.left;
			}
		}
		return nextNode.element;
	}

}
