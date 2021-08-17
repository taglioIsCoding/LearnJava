package cupidonline.model;

public class Corrispondenza implements Comparable<Corrispondenza> {
	private String p, q;
	private int indice;
	
	public Corrispondenza(String p, String q, int indice) {
		super();
		this.p = p;
		this.q = q;
		this.indice = indice;
	}
	public String getNomePersona1() {
		return p;
	}
	public String getNomePersona2() {
		return q;
	}
	public int getIndice() {
		return indice;
	}
	
	@Override
	public int compareTo(Corrispondenza that) {
		return -Integer.compare(this.getIndice(),that.getIndice());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + indice;
		result = prime * result + ((p == null) ? 0 : p.hashCode());
		result = prime * result + ((q == null) ? 0 : q.hashCode());
		return result;
	}
	
	@Override 
	public boolean equals(Object o) {
		if(o.getClass()!=Corrispondenza.class) return false;
		Corrispondenza that = (Corrispondenza) o;
		return this.getNomePersona1().equals(that.getNomePersona1()) && this.getNomePersona2().equals(that.getNomePersona2()) && this.getIndice()==that.getIndice();
	}
	
	@Override
	public String toString() {
		return "Corrispondenza fra " + p + " e " + q + ": " + getIndice();
	}
}
