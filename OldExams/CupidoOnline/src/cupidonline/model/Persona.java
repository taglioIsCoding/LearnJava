package cupidonline.model;

import java.time.LocalDate;
import java.time.Period;

public class Persona implements Comparable<Persona>
{

	private String id, citta, provincia, regione;
	private LocalDate dataNascita;
	private SegnoZodiacale zodiaco;
	private Colore capelli, occhi;
	private float altezza;
	private int peso;
	private Sesso sesso;

	public Persona(String id, Sesso sesso, LocalDate dataNascita, SegnoZodiacale zodiaco, Colore capelli2, Colore occhi, float altezza, int peso, String citta, String provincia,
			String regione) {
		super();
		this.id = id;
		this.zodiaco = zodiaco;
		this.capelli = capelli2;
		this.occhi = occhi;
		this.citta = citta;
		this.provincia = provincia;
		this.regione = regione;
		this.dataNascita = dataNascita;
		this.altezza = altezza;
		this.peso = peso;
		this.sesso=sesso;
	}
	
	public String getId()
	{
		return id;
	}
	
	public Sesso getSesso()
	{
		return sesso;
	}
	
	public boolean stessoSesso(Persona that) {
		return this.getSesso()==that.getSesso();
	}

	public SegnoZodiacale getSegnoZodiacale() {
		return zodiaco;
	}

	public Colore getColoreCapelli() {
		return capelli;
	}

	public Colore getColoreOcchi() {
		return occhi;
	}

	public String getCitta() {
		return citta;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getRegione() {
		return regione;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}
	
	public int getEta() {
		return Period.between(this.getDataNascita(), LocalDate.now()).getYears();
	}

	public float getAltezza() {
		return altezza;
	}

	public int getPeso() {
		return peso;
	}

	public String toString()
	{
		return getId() + "=" + getEta() + " anni";
	}

	public int compareTo(Persona that) {
		return this.getId().compareTo(that.getId());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(altezza);
		result = prime * result + ((capelli == null) ? 0 : capelli.hashCode());
		result = prime * result + ((citta == null) ? 0 : citta.hashCode());
		result = prime * result + ((dataNascita == null) ? 0 : dataNascita.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((occhi == null) ? 0 : occhi.hashCode());
		result = prime * result + peso;
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
		result = prime * result + ((regione == null) ? 0 : regione.hashCode());
		result = prime * result + ((sesso == null) ? 0 : sesso.hashCode());
		result = prime * result + ((zodiaco == null) ? 0 : zodiaco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null || Persona.class!=obj.getClass()) return false;
		Persona that = (Persona) obj;
		return this.compareTo(that)==0;
	}
	
	
	
}
