package mastermind.model;

import java.util.Arrays;
import java.util.StringJoiner;

public class Combinazione {

	private PioloDiGioco[] combinazione;
	private int dim;
	
	public Combinazione(int dim) {
		combinazione = new PioloDiGioco[dim];
		this.dim = dim;
		Arrays.fill(combinazione, PioloDiGioco.VUOTO);
	}
	
	public PioloDiGioco getPiolo(int index) {
		// might throw NPE if the referenced board cell is null
		return combinazione[index];
	}
	
	public void setPiolo(int index, PioloDiGioco c) {
		// might throw NPE if the referenced board cell is null 
		combinazione[index] = c; 
	}

	public int dim() {
		return dim;
	}
	
	public String toString() {
		StringJoiner sb = new StringJoiner(",");
		for(PioloDiGioco c:combinazione) {
			sb.add(c.toString());
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(combinazione);
		result = prime * result + dim;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Combinazione other = (Combinazione) obj;
		if (!Arrays.equals(combinazione, other.combinazione)) return false;
		if (dim != other.dim) return false;
		return true;
	}
	
}

