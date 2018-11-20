package bst;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {
	BinarySearchTree<Integer> tree;

	@BeforeEach
	void setUp() throws Exception {
		tree = new BinarySearchTree<Integer>();
	}

	@Test
	void testAdd() {
		tree.add(3);
		tree.add(10);
		tree.add(2);
		tree.add(9);
		assertTrue("Root element is wrong", tree.root.element.equals(3));
		assertTrue("Root.right element is wrong", tree.root.right.element.equals(10));
		assertTrue("Root.left element is wrong", tree.root.left.element.equals(2));
		assertTrue("Root.right.left element is wrong", tree.root.right.left.element.equals(9));
	}

	@Test
	void testHeight() {
		tree.add(3);
		tree.add(10);
		tree.add(2);
		tree.add(9);
		assertTrue("Wrong height", tree.height() == 3);
	}
	
	@Test
	void testEmptyTreeHeight() {
		assertEquals(0, tree.height());
	}

	@Test
	void testSize() {
		tree.add(3);
		tree.add(10);
		tree.add(2);
		tree.add(9);
		tree.add(9);
		assertTrue("Wrong size", tree.size() == 4);
	}
	
	@Test
	void testSizeEmptyTree() {
		assertEquals(0, tree.size());
	}

}
