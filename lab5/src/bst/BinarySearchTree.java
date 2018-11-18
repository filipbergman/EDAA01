package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
	int size;

	public static void main(String args[]) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		BSTVisualizer bstv = new BSTVisualizer("Träed", 500, 500);
		tree.add(8);
		tree.add(7);
		tree.add(6);
		tree.add(5);
		tree.add(4);
		tree.add(3);
		tree.add(2);
		tree.add(1);
		tree.rebuild();
		bstv.drawTree(tree);
		tree.printTree();
	}

	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {

	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x
	 *            element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		return addEl(x, root);
	}

	private boolean addEl(E x, BinaryNode<E> binaryNode) {
		// När man lägger till roten
		if (binaryNode == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		} else {
			if (binaryNode.element.equals(x)) {
				return false;
			}
			// Om elementet är större än elementet man är på
			if (x.compareTo(binaryNode.element) > 0) {
				// Om det finns ett element till höger om elementet man är på
				if (binaryNode.right != null) {
					return addEl(x, binaryNode.right);
				}
				// Om det inte finns något element till höger om elementet man är på
				else {
					binaryNode.right = new BinaryNode<E>(x);
					size++;
					return true;
				}
			} else {
				if (binaryNode.left != null) {
					return addEl(x, binaryNode.left);
				} else {
					binaryNode.left = new BinaryNode<E>(x);
					size++;
					return true;
				}
			}
		}
	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		return heightCal(root);
	}

	private int heightCal(BinaryNode<E> binaryNode) {
		if (binaryNode == null) {
			return 0;
		} else {
			return 1 + Math.max(heightCal(binaryNode.left), heightCal(binaryNode.right));
		}
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		print(root);
	}

	private void print(BinaryNode<E> binaryNode) {
		if (binaryNode != null) {
			print(binaryNode.left);
			System.out.print(binaryNode.element + " ");
			print(binaryNode.right);
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		E[] a = (E[]) new Comparable[size];
		toArray(root, a, 0);
		root = buildTree(a, 0, size-1);
	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index]. Returns the index of the last inserted element + 1 (the
	 * first empty position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		if (n != null) {
			index = toArray(n.left, a, index);
			a[index++] = n.element;
			index = toArray(n.right, a, index);
			return index;
		} else
			return index;
		
	}

	/*
	 * Builds a complete tree from the elements a[first]..a[last]. Elements in the
	 * array a are assumed to be in ascending order. Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		// När man bara har ett alternativ att välja
		if(first > last) return null;
		
		// När det finns något barn till noden man är på
		int mid = (last + first) / 2;
		BinaryNode<E> node = new BinaryNode<E>(a[mid]);
		node.left = buildTree(a, first, mid-1);
		node.right = buildTree(a, mid+1, last);
		
		return node;
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

}
