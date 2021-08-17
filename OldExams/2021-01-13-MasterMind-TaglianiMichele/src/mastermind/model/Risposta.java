package mastermind.model;

import java.util.Arrays;
import java.util.StringJoiner;

public class Risposta {

	private PioloRisposta[] risposta;
	private int dim;
	
	public Risposta(int size) {
		risposta = new PioloRisposta[size];
		this.dim = size;
		Arrays.fill(risposta, PioloRisposta.VUOTO);
	}
	
	public PioloRisposta getPiolo(int index) {
		// might throw NPE if the referenced board cell is null
		return risposta[index];
	}
	
	public void setPiolo(int index, PioloRisposta pr) {
		// might throw NPE if the referenced board cell is null 
		risposta[index] = pr; 
	}

	public int dim() {
		return dim;
	}
	
	public boolean vittoria() {
		for(PioloRisposta r:risposta) {
			if (!r.equals(PioloRisposta.NERO)) return false;
		}
		return true;
	}
	
	public String toString() {
		StringJoiner sb = new StringJoiner(",");
		for(PioloRisposta r:risposta) {
			sb.add(r.toString());
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(risposta);
		result = prime * result + dim;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Risposta other = (Risposta) obj;
		if (!Arrays.equals(risposta, other.risposta)) return false;
		if (dim != other.dim) return false;
		return true;
	}
	
}

