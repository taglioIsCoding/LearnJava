package tris.model;

import java.util.Arrays;
import java.util.StringTokenizer;

public class MyBoard implements Board {

	private char[][] board = new char[3][3];
	
	public MyBoard(char[][] board) {
		if(board.length != 3) {
			throw new IllegalArgumentException("Matrice errata");
		}
		
		for(int i = 0; i < 3; i++) {
			if(board[i].length != 3){
				throw new IllegalArgumentException("Matrice errata col");
			}
		}
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(BoardValue.getAllValues().indexOf(board[i][j] + "") == -1) {
					throw new IllegalArgumentException("Matrice errata");
				}
			}
		}
		
		this.board = board;
	}
	
	public MyBoard(String board) {
		
		if(board.length() != 9) {
			throw new IllegalArgumentException("Stringa errata");
		}
		
		int k = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
					this.board[i][j] = board.charAt(k);
					k++;
			}
		}
		
	}
	
	@Override
	public String getRow(int i) {
		String tosendString = "";
		
		if(i<0 || i> 2) {
			throw new IllegalArgumentException("errata riga");
		}
		
		for(int j = 0; j < 3; j++) {
			tosendString = tosendString + this.board[i][j];
		}
		
		return tosendString;
	}

	@Override
	public String getColumn(int i) {
		String tosendString = "";
		
		if(i<0 || i> 2) {
			throw new IllegalArgumentException("errata colonna");
		}
		
		for(int j = 0; j < 3; j++) {
			tosendString = tosendString + this.board[j][i];
		}
		return tosendString;
	}

	@Override
	public String getDiagonal(int i) {
		
		if(i < 0 || i> 1) {
			throw new IllegalArgumentException("errata diagonale");
		}
		
		StringBuilder sBuilder = new StringBuilder();
		if(i == 0) {
			for(int j = 0 ; j < 3 ; j++) {
			sBuilder.append(this.board[j][j]);
			}
		}else {
			for(int j = 2 ; j >= 0 ; j--) {
				sBuilder.append(this.board[2-j][j]);
			}
		}
		
		return sBuilder.toString();
	}

	@Override
	public boolean winning() {
		
		for(int i = 0; i < 3; i++) {
			if(this.winningRow(i) || this.winningColumn(i)) {
				return true;
			}
		}
		
		for(int i = 0; i < 2; i++) {
			if(this.winningDiagonal(i)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean winningSequence(String s) {
		if(s.charAt(0)== ' ') {
			return false;
		}
		if(s.charAt(0) ==s.charAt(1) && s.charAt(1) ==s.charAt(2)){
			return true;
		}
		return false;
	}

	@Override
	public boolean winningRow(int i) {
		String rowString = this.getRow(i);
		if(rowString.charAt(0)== ' ') {
			return false;
		}
		if(rowString.charAt(0) ==rowString.charAt(1) && rowString.charAt(1) ==rowString.charAt(2)){
			return true;
		}
		return false;
	}

	@Override
	public boolean winningColumn(int i) {
		String colString = this.getColumn(i);
		if(colString.charAt(0)== ' ') {
			return false;
		}
		if(colString.charAt(0) ==colString.charAt(1) && colString.charAt(1) ==colString.charAt(2)){
			return true;
		}
		return false;
	}

	@Override
	public boolean winningDiagonal(int i) {
		String diaString = this.getDiagonal(i);
		if(diaString.charAt(0)== ' ') {
			return false;
		}
		if(diaString.charAt(0) ==diaString.charAt(1) && diaString.charAt(1) ==diaString.charAt(2)){
			return true;
		}
		return false;
	}

	@Override
	public boolean adjacent(Board that) {
		if(distanceFrom(that) != 1) {
			return false;
		}
		
		int x = 0;
		int o = 0;
		
		for(int i = 0; i < 3; i++) {
			String rowTha = that.getRow(i);
			
			//System.out.println(rowTha.split("X").length);
			
			StringTokenizer stk1 = new StringTokenizer(rowTha, "X");
			StringTokenizer stk4 = new StringTokenizer(rowTha, "O");
			
			x += rowTha.length() - rowTha.replaceAll("X","").length();
			rowTha = that.getRow(i);
			o += rowTha.length() - rowTha.replaceAll("O","").length();
		System.out.println(rowTha.split("X"));
//			System.out.println(stk4.countTokens());
//			System.out.println("------------");
		}
		
	System.out.println(x);
	System.out.println(o);
		if(x - o > 1) {
			//System.out.println(x-o);
			return false;
		}
		
		return true;
	}

	@Override
	public int distanceFrom(Board that) {
		int celleDiff = 0;
		
		for(int i = 0; i < 3; i++) {
			String rowThi = this.getRow(i);
			String rowTha = that.getRow(i);
			
			for(int j = 0; j < 3; j++) {
				if(rowTha.charAt(j) != rowThi.charAt(j)) {
					celleDiff++;
				}
			}
			
			
		}
		
		return celleDiff;
	}

	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
					sBuilder.append(board[i][j]);
					if(j != 2) {
						sBuilder.append("\t");
					}
					if(j == 2 && i != 2) {
						sBuilder.append(System.lineSeparator());
					}
			}
		}
		
		return sBuilder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyBoard other = (MyBoard) obj;
		if (this.distanceFrom(other) != 0)
			return false;
		return true;
	}
	
	
}
