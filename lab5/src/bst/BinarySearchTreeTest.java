package bst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testAdd() {
		BinarySearchTree<Integer> test = new BinarySearchTree<Integer>();
		test.add(3);
		test.add(1);
		test.add(2);
		test.add(20);
		assertTrue(test.root.equals(3));
	}

	@Test
	void testHeight() {
		fail("Not yet implemented");
	}

	@Test
	void testSize() {
		fail("Not yet implemented");
	}

}
