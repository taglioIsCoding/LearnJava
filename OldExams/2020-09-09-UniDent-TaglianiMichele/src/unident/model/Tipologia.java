package unident.model;

public enum Tipologia {

	A("di base"), B("caratterizzanti"), C("affini o integrative"),
	D("a scelta dello studente"), E("prova finale e conoscenza della lingua inglese"),
	F("altre");

	private String descrizione;

	private Tipologia(String descrizione) {
		this.descrizione=descrizione;
	}

	public String getDescrizione() {
		return descrizione;
	}
}
