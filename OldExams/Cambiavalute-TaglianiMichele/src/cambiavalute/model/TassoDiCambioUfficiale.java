package cambiavalute.model;

public class TassoDiCambioUfficiale {
	private String siglaValuta, nomeValuta, nomePaese;
	private double tassoDiCambio;
	
	public TassoDiCambioUfficiale(String siglaValuta, String nomeValuta, String nomePaese, double tassoDiCambio) {
		this.siglaValuta = siglaValuta;
		this.nomeValuta = nomeValuta;
		this.nomePaese = nomePaese;
		this.tassoDiCambio = tassoDiCambio;
	}

	public String getSiglaValuta() {
		return siglaValuta;
	}
	
	public String getNomeValuta() {
		return nomeValuta;
	}
	
	public String getNomePaese() {
		return nomePaese;
	}

	public double getTassoDiCambio() {
		return tassoDiCambio;
	}

	@Override
	public String toString() {
		return siglaValuta + " (" + nomePaese + ") " + tassoDiCambio;
	}
	
}
