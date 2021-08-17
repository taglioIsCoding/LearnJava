package battleship.model;

import java.util.Arrays;

public class ShipBoard {
	private ShipItem[][] board;
	private static final int DIM = 8;

	public ShipBoard() {
		board = new ShipItem[DIM][DIM];
		Arrays.stream(board).forEach(arr -> Arrays.fill(arr, ShipItem.EMPTY));
	}
	
	public ShipBoard(String eightLines) {
		board = new ShipItem[DIM][DIM];
		String[] lines = eightLines.split("\\r?\\n");
		for (int i=0; i<DIM; i++) setCellRow(i, lines[i]);
	}

	public void setCellRow(int row, String line) {
		//System.out.println("CHECK:: |" + line + "|");
		String[] items = line.split("\\s");
		//System.out.println("CHECK:: " + Arrays.asList(items));
		if (row < 0 || row >= DIM || items.length!=DIM)
			throw new IllegalArgumentException("Errore nei parametri");
		for (int col = 0; col < DIM; col++) {
				board[row][col] = ShipItem.of(items[col].trim());
		}
	}

	public ShipItem getCell(int row, int col) {
		if (row < 0 || row >= DIM || col < 0 || col >= DIM)
			throw new IllegalArgumentException("Errore nei parametri");
		return board[row][col];
	}

	public int getSize() {
		return DIM;
	}
	
	public int countShipCellsInRow(int row) {
		int ships = 0;
		for(int i = 0 ; i < DIM; i++) {
			if(board[row][i] != ShipItem.EMPTY && board[row][i] != ShipItem.SEA) {
				ships++;
			}
		}
		return ships; 
	}

	public int countShipCellsInColumn(int column) {
		int ships = 0;
		for(int i = 0 ; i < DIM; i++) {
			if(board[i][column] != ShipItem.EMPTY && board[i][column] != ShipItem.SEA) {
				ships++;
			}
		}
		return ships;
	}
	
	public int[] getCountingRow() {
		int hint[] = new int[DIM];
		for(int i = 0 ; i < DIM; i++) {
			hint[i] = this.countShipCellsInColumn(i);
		}
		
		return hint; 
	}

	public int[] getCountingCol() {
		int hints[] = new int[DIM];
		for(int i = 0 ; i < DIM; i++) {
			hints[i] = this.countShipCellsInRow(i);
		}
		return hints;
	}
	
	public void clearCell(int row, int col)	{
		if (row < 0 || row >= DIM || col < 0 || col >= DIM )
			throw new IllegalArgumentException("Errore nei parametri");
		   board[row][col]=ShipItem.EMPTY;
	}
	
	public void setCell(int row, int col, ShipItem item) {
		if (row < 0 || row >= DIM || col < 0 || col >= DIM)
			throw new IllegalArgumentException("Errore nei parametri");
		board[row][col] = item;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k < DIM * DIM; k++) {
			sb.append(board[k / DIM][k % DIM].getValue());
			sb.append(k % DIM == DIM - 1 ? System.lineSeparator() : ' ');
		}
		return sb.toString();
	}

}
