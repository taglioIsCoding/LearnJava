package tris.model;

public enum BoardValue {
	EMPTY(" "), X("X"), O("O");
	
	private BoardValue(String s) {
		this.s=s;
	}
	
	private String s;
	
	public String get() { return s; }
	
	public String toString() {
		return this.get();
	}
	
	public BoardValue other() {
		if (this==EMPTY) throw new IllegalArgumentException("non esiste l'avversario di ' '");
		return this==X ? O : X;
	}
	
	public static String getAllValues() {
		return EMPTY.toString() + X.toString() + O.toString();
	}

}
