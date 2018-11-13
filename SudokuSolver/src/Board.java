
public class Board {
	private int board[][] = new int[9][9];

	public boolean set(int row, int col, int number) {
		if (row < 9 && row >= 0 && col < 9 && col >= 0 && number < 9 && number > 0 && board[row][col] != 0) {
			board[row][col] = number;
			return true;
		}
		return false;
	}

	public int getNum(int row, int col) {
		if (row < 10 && row > 0 && col < 10 && col > 0) {
			return board[row][col];
		}
		return -1;
	}

	public int remove(int row, int col) {
		int temp = board[row][col];
		board[row][col] = 0;
		return temp;
	}

	public void clear(int row, int col) {
		board = new int[9][9];
	}

	public boolean solve() {
		return solve(0, 0);
	}

	private boolean solve(int row, int col) {
		if (row == 9) {
			return true;
		}

		for (int num = 1; num < 10; num++) {
			if (col == 9) {
				solve(row + 1, 0);
			}
			set(row, col, num);
			//Möjligtvis måste detta ändras då vi ska kolla ruleCheck efter varje set.
			if (!solve(row, col + 1)) {
				remove(row, col);
			}
			else return true;

		}

		return false;
	}
	
	private boolean ruleCheck(int row, int col, int number) {
		for (int c = 0; c < 9; c++) {
			if (getNum(row,c) == number && c != col) return false;
		}
		for (int r = 0; r < 9; r++) {
			if (getNum(r,col) == number && r != row) return false;
		}
		
		int rMin = row - row % 3;
		int rMax = rMin + 3;
		
		int cMin = col - col % 3;
		int cMax = cMin + 3;
		
		for (int r = rMin; r < rMax; r++) {
			for (int c = cMin; c < cMax; c++) {
				if (getNum(r,c) == number && (c != col && r != row)) return false;
			}
		}
		return true;
	}
}
