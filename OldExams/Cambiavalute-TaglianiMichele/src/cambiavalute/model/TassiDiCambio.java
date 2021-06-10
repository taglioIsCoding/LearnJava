package cambiavalute.model;

public class TassiDiCambio {
	private String siglaValuta;
	private double prezzoAcquisto, prezzoVendita;
	
	public TassiDiCambio(String siglaValuta, double prezzoAcquisto, double  prezzoVendita) {
		this.siglaValuta = siglaValuta;
		this.prezzoAcquisto = prezzoAcquisto;
		this.prezzoVendita = prezzoVendita;
	}

	public String getSiglaValuta() {
		return siglaValuta;
	}

	public double getPrezzoAcquisto() {
		return prezzoAcquisto;
	}

	public double getPrezzoVendita() {
		return prezzoVendita;
	}

	@Override
	public String toString() {
		return siglaValuta + "\t sell " + prezzoVendita + "\t buy " + prezzoAcquisto;
	}
	
}
