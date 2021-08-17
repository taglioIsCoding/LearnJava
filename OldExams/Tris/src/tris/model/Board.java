package tris.model;

public interface Board {
	String getRow(int i);
	String getColumn(int i);
	String getDiagonal(int i);
	boolean winning() ;
	boolean winningRow(int i);
	boolean winningColumn(int i);
	boolean winningDiagonal(int i);
	String toString() ;
	boolean adjacent(Board that);
	int distanceFrom(Board that);
}

