package sudoku.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import sudoku.model.SudokuBoard;

public abstract class AbstractController implements Controller {
	protected SudokuBoard sudoku;

	public  AbstractController(SudokuBoard sud) {
		sudoku = sud;
	}

	public int getSize() {
		return sudoku.getSize();
	}

	public abstract String getCellLabel(int row, int col);

	public abstract boolean setCell(int row, int col, String value);

	
	public boolean endGame() {
		
		return sudoku.isFull();
	}
	
	
	public String getEmptyCell()
	{
		return "Mancano ancora "+sudoku.getEmpyCellNumber()+"\nnumeri da inserire";
		
	}
	
	public void saveGame()
	{ PrintWriter pr;
	
	try {
		pr = new PrintWriter(new FileWriter("Soluzione.txt"));
		pr.write(sudoku.toString());
		pr.flush();
		pr.close();
	} catch (IOException e) {

		e.printStackTrace();
	}
	 	
	}
	
}
