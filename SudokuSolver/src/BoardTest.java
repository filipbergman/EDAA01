import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
	Board board;
	
	@BeforeEach
	void setUp() throws Exception {
		board = new Board();
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}
	
	@Test
	void testGetNum() {
		board.set(1, 3, 9);
		assertEquals(9, board.getNum(1, 3));
	}
	
	@Test
	void testSetonBusy() {
		board.set(1, 3, 9);
		assertTrue(!board.set(1, 3, 9));
	}

	@Test
	void testEmpty() {
		assertTrue(board.solve());
		assertEquals(9, board.getNum(0,8));
	}
	
	@Test
	void testUnsolvable() {
		board.set(0, 2, 8);
		board.set(1, 1, 8);
		assertFalse(board.solve());
	}
	
	@Test
	void testFig1() {
		board.set(0, 2, 8);
		board.set(0, 5, 9);
		board.set(0, 7, 6);
		board.set(0, 8, 2);
		
		board.set(1, 8, 5);
		
		board.set(2, 0, 1);
		board.set(2, 2, 2);
		board.set(2, 3, 5);
		
		board.set(3, 3, 2);
		board.set(3, 4, 1);
		board.set(3, 7, 9);
		
		board.set(4, 1, 5);
		board.set(4, 6, 6);
		
		board.set(5, 0, 6);
		board.set(5, 7, 2);
		board.set(5, 8, 8);
		
		board.set(6, 0, 4);
		board.set(6, 1, 1);
		board.set(6, 3, 6);
		board.set(6, 5, 8);
		
		board.set(7, 0, 8);
		board.set(7, 1, 6);
		board.set(7, 4, 3);
		board.set(7, 6, 1);
		
		board.set(8, 6, 4);
		
		assertTrue(board.solve());
//		System.out.println(board);
	}

}
