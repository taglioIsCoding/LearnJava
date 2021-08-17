package sudoku.model;

import java.util.OptionalInt;



public class SudokuBoard {
	private final int DIM = 9;
	private OptionalInt board[][] = new OptionalInt[DIM][DIM];
	private int fullCellNumber;
	
	public SudokuBoard() {
		for(int i = 0; i < DIM ; i++) {
			this.setCellRow(i, new int[] {0,0,0,0,0,0,0,0,0,0,0});
		}
		this.fullCellNumber = 0;
	}
	
	public void setCellRow(int row, int[] numValues) {
		// might throw NPE if the referenced board cell is null
		if(row > DIM-1 || row < 0) {
			throw new IllegalArgumentException("riga non valida");
		}
		
		for(int i = 0; i < DIM ; i++) {
			try {
				if( numValues[i] > DIM || numValues[i] < 0) {
				throw new IllegalArgumentException("numero non valido");
				}
			} catch (Exception e) {
				throw new IllegalArgumentException("numero di numeri non valido");
			}
			
		}
		
		for (int col=0; col<DIM; col++) {
			if(numValues[col] != 0) {
				board[row][col] = OptionalInt.of(numValues[col]);
				this.fullCellNumber++;
			}else {
				board[row][col] = OptionalInt.empty();
			}
			 
		}
	}
	
	public OptionalInt getCell(int row, int col) {
		if(row > DIM || row < 0) {
			throw new IllegalArgumentException("riga non valida");
		}
		if(col > DIM || col < 0) {
			throw new IllegalArgumentException("col non valida");
		}
		
		return this.board[row][col];
	}
	
	public int getSize() {
		return this.DIM;
	}
	
	public void clearCell(int row, int col) {
		if(row > DIM || row < 0) {
			throw new IllegalArgumentException("riga non valida");
		}
		if(col > DIM || col < 0) {
			throw new IllegalArgumentException("col non valida");
		}
		this.fullCellNumber--;
		this.board[row][col] = OptionalInt.empty();
	}
	
	
	public boolean setCell(int row, int col, int digit) {
		if(row > DIM || row < 0) {
			throw new IllegalArgumentException("riga non valida");
		}
		if(col > DIM || col < 0) {
			throw new IllegalArgumentException("col non valida");
		}
		if(digit > DIM || digit < 0) {
			throw new IllegalArgumentException("numero non valido");
		}
		
		
		for (int colcheck=0; colcheck<DIM; colcheck++) {
			if (this.board[row][colcheck].isEmpty()) {
				continue;
			}else if(this.board[row][colcheck].getAsInt() == digit) {
				return false;
			}
		}
		
		for (int rowcheck=0; rowcheck<DIM; rowcheck++) {
			if (this.board[rowcheck][col].isEmpty()) {
				continue;
			}else if (this.board[rowcheck][col].getAsInt() == digit) {
				return false;
			}
		}
		
		int startRow = row - row % 3;
		int startCol = col - col % 3;
		for(int i = startRow; i < 2; i++) {
			for(int j = startCol; j < 2; j++) {
				if (this.board[i][j].isEmpty()) {
					continue;
				}else if (this.board[i][j].getAsInt() == digit) {
					return false;
				}
			}
		}
		
		if (this.board[row][col].isEmpty()) {
			fullCellNumber++;	
		}
		this.board[row][col] = OptionalInt.of(digit);
		return true;
		
	}
	
	
	public int getEmpyCellNumber() {
		return (DIM*DIM) - this.fullCellNumber;
	}
	
	public boolean isFull() {
		return (DIM*DIM) == this.fullCellNumber ? true : false ;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int k=0; k<DIM*DIM; k++) {
			sb.append(board[k/DIM][k%DIM].isPresent() ? board[k/DIM][k%DIM].getAsInt() : " ");
			sb.append(k%DIM==DIM-1 ? "" : '\t');
		}
		return sb.toString();
		
	}
	
	
}
