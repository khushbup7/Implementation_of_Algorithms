
/** @author 
 *  Binary search tree map (starter code)
 *  Implement this class using one of the BST implementations: BST, AVLTree, RedBlackTree, or, SplayTree.
 *  Do not use TreeMap or any of Java's maps.
 **/

package cs6301.g39;

import java.util.Iterator;

import cs6301.g39.BST.EntryCreator;

public class BSTMap<K extends Comparable<? super K>, V> implements Iterable<K> {

	private AVLTree<K> tree = null;

	private EntryCreator<K> creator = new BST.EntryCreator<K>() {

		@Override
		public BST.Entry<K> createNewEntry(K x, BST.Entry<K> left, BST.Entry<K> right) {
			return new BSTMapEntry<K, V>(left, right, x, null);
		}

	};

	BSTMap() {
		tree = new AVLTree<K>(creator);
	}

	public V get(K key) {
		return null;
	}

	public boolean put(K key, V value) {
		V prev = null;
		tree.add(key);
		BST.Entry<K> t = (tree.stack != null && !tree.stack.isEmpty()) ? (BST.Entry<K>) tree.stack.pop() : tree.root;
		t = (t != null && t.element.compareTo(key) > 0) ? t.left : t.right;
        BSTMapEntry<K, V> mapEntry = (BSTMapEntry<K, V>) t;
        if (mapEntry.value != null) 
            prev = mapEntry.value;
        mapEntry.value = value;

        return true;
	}

	// Iterate over the keys stored in the map, in order
	public Iterator<K> iterator() {
		return null;
	}

	protected static class BSTMapEntry<K extends Comparable<? super K>, V> extends AVLTree.Entry<K> {

		protected V value = null;

		protected BSTMapEntry(BST.Entry<K> left, BST.Entry<K> right, K key, V value) {
			super(key, left, right);
			this.value = value;
		}
	}
}
