package cupidonline.model;

public enum Sesso {
	MASCHIO,FEMMINA;
	
	public static Sesso valueOfChar(char s) {
		return s=='M' ? MASCHIO : FEMMINA;
	}

	public static Sesso valueOfChar(String s) {
		if (s.length()!=1 || !("MF".contains(s))) throw new IllegalArgumentException("Illegal sex");
		return valueOfChar(s.charAt(0));
	}
}
