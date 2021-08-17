package ghigliottina.model;

public class Terna {
	private String word1, word2;
	private Esatta esatta;

	public Terna(String word1, String word2, Esatta esatta) {
		if(word1==null || word1.isBlank()) throw new IllegalArgumentException("Prima parola nulla o vuota: |" + word1 + "|");
		if(word2==null || word2.isBlank()) throw new IllegalArgumentException("Seconda parola nulla o vuota: |" + word2 + "|");
		this.word1 = word1;
		this.word2 = word2;
		this.esatta=esatta;
	}

	public String getWord1() {
		return word1;
	}

	public String getWord2() {
		return word2;
	}
	
	public Esatta getCorrect() {
		return esatta;
	}

	public String getCorrectWord() {
		return esatta.equals(Esatta.FIRST) ? this.getWord1() : this.getWord2();
	}
	
	public String getWrongWord() {
		return esatta.equals(Esatta.FIRST) ? this.getWord2() : this.getWord1();
	}

	@Override
	public String toString() {
		return "Terna [word1=" + word1 + ", word2=" + word2 + ", esatta=" + esatta + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((esatta == null) ? 0 : esatta.hashCode());
		result = prime * result + ((word1 == null) ? 0 : word1.hashCode());
		result = prime * result + ((word2 == null) ? 0 : word2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Terna other = (Terna) obj;
		if (esatta != other.esatta) return false;
		if (word1 == null) {
			if (other.word1 != null) return false;
		} else if (!word1.equals(other.word1)) return false;
		if (word2 == null) {
			if (other.word2 != null) return false;
		} else if (!word2.equals(other.word2))
			return false;
		return true;
	}
	
}
