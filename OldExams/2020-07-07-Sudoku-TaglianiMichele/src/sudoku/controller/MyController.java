package sudoku.controller;

import java.util.OptionalInt;

import sudoku.model.SudokuBoard;

public class MyController extends AbstractController{
	
	public MyController(SudokuBoard sudokuBoard) {
		super(sudokuBoard);
	}

	@Override
	public String getCellLabel(int row, int col) {
		OptionalInt optionalInt = this.sudoku.getCell(row, col);
		
		if(optionalInt.isEmpty()) {
			return " ";
		}else {
			return Integer.toString(optionalInt.getAsInt());
		}
		
		
	}

	@Override
	public boolean setCell(int row, int col, String value) {
		if(value.isBlank()) {
			this.sudoku.clearCell(row, col);
			return true;
		}
		return this.sudoku.setCell(row, col, Integer.parseInt(value));
	}

}
