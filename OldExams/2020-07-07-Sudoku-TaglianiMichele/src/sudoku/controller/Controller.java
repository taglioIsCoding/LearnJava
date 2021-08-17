package sudoku.controller;

public interface Controller {
	public int getSize();
	public String getCellLabel(int row, int col);
	public boolean setCell(int row, int col, String value);
	public boolean endGame();
	public void saveGame();
	public String getEmptyCell();
}
