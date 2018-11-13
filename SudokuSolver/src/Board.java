
public class Board {
	private int board[][] = new int[9][9];
	
	
	public boolean set (int row, int col, int number) {
		if (row < 10 && row > 0 && col < 10 && col > 0 && number < 10 && number > 0) {
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
	public int remove (int row, int col) {
		int temp = board[row][col];
		board[row][col] = 0;
		return temp;
	}
	public void clear (int row, int col) {
		board = new int[9][9];
	}
	
	public boolean solve() {
		return false;
	}
	
	private boolean solve(int i, int j) {
		return false;
	}
}
