package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
    int size;
    
    public static void main(String args[]) {
    	BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
    	BSTVisualizer bstv = new BSTVisualizer("Träed", 500, 500);
    	tree.add(3);
    	tree.add(10);
		tree.add(2);
		tree.add(9);
		tree.add(1);
		tree.add(30);
		tree.add(11);
		tree.add(12);
		bstv.drawTree(tree);
    }
    
	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		return addEl(x, root);
	}
	
	private boolean addEl(E x, BinaryNode<E> binaryNode) {
		// När man lägger till roten
		if(binaryNode == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		} else {
			if(binaryNode.element.equals(x)) {
				return false;
			}
			// Om elementet är större än elementet man är på
			if(x.compareTo(binaryNode.element) > 0) {
				// Om det finns ett element till höger om elementet man är på
				if(binaryNode.right != null) { 
					return addEl(x, binaryNode.right); 
				}
				// Om det inte finns något element till höger om elementet man är på
				else {
					binaryNode.right = new BinaryNode<E>(x);
					size++;
					return true;
				}
			} else {
				if(binaryNode.left != null) {
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
	 * @return the height of the tree
	 */
	public int height() {
		return heightCal(root);
	}
	
	private int heightCal(BinaryNode<E> binaryNode) {
		if(binaryNode == null) {
			return 0;
		} else {
			return 1 + Math.max(heightCal(binaryNode.left), heightCal(binaryNode.right));
		}
	}
	
	/**
	 * Returns the number of elements in this tree.
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
		if(binaryNode != null) {
			print(binaryNode.left);
			System.out.println(binaryNode.element);
			print(binaryNode.left);
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {

	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index].
	 * Returns the index of the last inserted element + 1 (the first empty
	 * position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		return 0;
	}
	
	/*
	 * Builds a complete tree from the elements a[first]..a[last].
	 * Elements in the array a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		return null;
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
