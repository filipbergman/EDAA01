
public class Board {
	private int board[][] = new int[9][9];

	/**
	 * Inserts the specified number in the specified slot (row, col) on the board if
	 * the slot is empty and inside the board and the number is in the right range.
	 * 
	 * @param row, col, number
	 *            elements to be inserted
	 * @return true if the the number was inserted
	 */
	public boolean set(int row, int col, int number) {
		if (row < 9 && row >= 0 && col < 9 && col >= 0 && number < 10 && number > 0 && board[row][col] == 0) {
			board[row][col] = number;
			return true;
		}
		return false;
	}

	/**
	 * Returns the number on the slot(row, col) if the place is not empty.
	 * 
	 * @param row, col
	 *            elements to be inserted
	 * @return the number on the slot(row, col) if the place is not empty.
	 */
	public int getNum(int row, int col) {
		if (row < 9 && row >= 0 && col < 9 && col >= 0) {
			return board[row][col];
		}
		return -1;
	}

	/**
	 * Removes the number on the slot(row,col) and returns the number that was removed.
	 * 
	 * @param row, col
	 *            elements to be inserted
	 * @return the number that was removed
	 */
	public int remove(int row, int col) {
		int temp = board[row][col];
		board[row][col] = 0;
		return temp;
	}

	/**
	 * Removes all the slots on the board.
	 * 
	 * @param row, col
	 *            elements to be inserted
	 */
	public void clear(int row, int col) {
		board = new int[9][9];
	}

	/**
	 * Tries to solve the current board.
	 * 
	 * @return true if the board was solved
	 */
	public boolean solve() {
		return solve(0, 0);
	}

	private boolean solve(int row, int col) {
		if (row == 9) {
			return true;
		}

		for (int num = 1; num < 10; num++) {
			if (col == 9) {
				if (solve(row + 1, 0))
					return true;
			} else {
				boolean numbSet = set(row, col, num);

				if (ruleCheck(row, col, getNum(row, col))) {
					if (!solve(row, col + 1)) {
						if (!numbSet)
							break;
						remove(row, col);
					} else
						return true;
				} else if (numbSet) remove(row, col);
			}
		}

		return false;
	}

	private boolean ruleCheck(int row, int col, int number) {
		for (int c = 0; c < 9; c++) {
			if (getNum(row, c) == number && c != col)
				return false;
		}
		for (int r = 0; r < 9; r++) {
			if (getNum(r, col) == number && r != row)
				return false;
		}

		int rMin = row - row % 3;
		int rMax = rMin + 3;

		int cMin = col - col % 3;
		int cMax = cMin + 3;

		for (int r = rMin; r < rMax; r++) {
			for (int c = cMin; c < cMax; c++) {
				if (getNum(r, c) == number && (c != col && r != row))
					return false;
			}
		}
		return true;
	}
	
//	@Override
//	public String toString() {
//		String string = "";
//		for(int row = 0; row < 9; row++) {
//			for(int col = 0; col < 9; col++) {
//				string += getNum(row,col) + " ";
//			}
//			string += "\n";
//		}
//		return string;
//	}
}
