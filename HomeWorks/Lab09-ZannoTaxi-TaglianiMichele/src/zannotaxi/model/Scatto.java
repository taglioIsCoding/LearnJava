package zannotaxi.model;

public class Scatto {
	private int tempo;
	private double spazio;
	private double costo;
	
	public Scatto(int tempo, double spazio, double costo) {
		this.tempo = tempo;
		this.spazio = spazio;
		this.costo = costo;
	}

	public int getTempo() {
		return tempo;
	}

	public double getSpazio() {
		return spazio;
	}

	public double getCosto() {
		return costo;
	}	
}
