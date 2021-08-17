package crosswords.model;

import java.util.Arrays;

public class Scheme {

	private int[][] board;
	private int size;
	
	public Scheme(int size) {
		board = new int[size][size];
		Arrays.stream(board).forEach(arr -> Arrays.fill(arr, -1));
		this.size = size;
	}
	
	public void setCellRow(int row, int[] numValues) {
		// might throw NPE if the referenced board cell is null
		for (int col=0; col<size; col++) {
			board[row][col] = numValues[col]; 
		}
	}
	
	public int[] getCellRow(int row)
	{
		return board[row];
	}
	
	
	public int getCell(int row, int col) {
		return board[row][col];
	}

	public int getSize() {
		return size;
	}

	public boolean isConfigured() {
		for (int row=0; row<size; row++)
			for (int col=0; col<size; col++)
				if(board[row][col]==-1) return false;
		return true;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int k=0; k<size*size; k++) {
			sb.append(board[k/size][k%size]);
			sb.append(k%size==size-1 ? System.lineSeparator() : '\t');
		}
		return sb.toString();
	}
	
}

