package unident.model;

import java.util.OptionalInt;

public enum Voto {
	ASSENTE, RITIRATO, RESPINTO,
	V18(18), V19(19), V20(20), V21(21), V22(22), V23(23), V24(24), V25(25), 
	V26(26), V27(27), V28(28), V29(29), V30(30), V30L(30,true);
	
	private OptionalInt voto;
	private boolean lode;

	private Voto(int voto) {
		if (voto<18 || voto>30) throw new IllegalArgumentException("Voto non valido: " + voto);
		this.voto=OptionalInt.of(voto);
		this.lode=false;
	}
	
	private Voto() {
		this.voto = OptionalInt.empty();
	}
	
	private Voto(int voto, boolean lode) {
		if (voto!=30) throw new IllegalArgumentException("Voto non valido: " + voto);
		this.voto=OptionalInt.of(voto);
		this.lode=true;
	}
	
	public static Voto of(String votoAsString) {
		try {
			Integer.parseInt(votoAsString); // just to test syntax
			return Voto.valueOf("V"+votoAsString);
		}
		catch(NumberFormatException e) {
			switch (votoAsString) {
				case "30L": return Voto.valueOf("V"+votoAsString);
				case "ASSENTE":
				case "RESPINTO":
				case "RITIRATO": return Voto.valueOf(votoAsString);
				default: throw new IllegalArgumentException("Voto non valido: " + votoAsString);
			}
		}
	}
	
	public OptionalInt getValue() {
		return voto;
	}
	
	public boolean getLode() {
		return lode;
	}
	
	@Override
	public String toString() {
		return getValue().isEmpty() ? super.toString() : getValue().getAsInt()+(getLode() ? "L" : "");
	}
}
