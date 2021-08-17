package cupidonline.model;

import java.util.Optional;
import java.util.OptionalInt;

public class Preferenza 
{
	private Sesso sesso;
	private String citta, provincia, regione;
	private Optional<SegnoZodiacale> zodiaco;
	private Optional<Colore> capelli, occhi;
	private Optional<Float> altezza;
	private OptionalInt peso;
	private int etaMin, etaMax;
	
	public Preferenza(Sesso sesso, int etaMin, int etaMax, Optional<SegnoZodiacale> zodiaco, Optional<Colore> capelli2, Optional<Colore> occhi, Optional<Float> altezza, OptionalInt peso, String citta, String provincia,
			String regione) {
		super();
		this.etaMin = etaMin;
		this.etaMax = etaMax;
		this.zodiaco = zodiaco;
		this.capelli = capelli2;
		this.occhi = occhi;
		this.citta = citta;
		this.provincia = provincia;
		this.regione = regione;
		this.altezza = altezza;
		this.peso = peso;
		this.sesso=sesso;
	}
	
	public int getEtaMax()
	{
		return etaMax;
	}
	
	public int getEtaMin()
	{
		return etaMin;
	}
	
	public boolean etaInRange(int x) {
		return getEtaMin()<= x && x <=getEtaMax();
	}
	
	public int etaOutOfRange(int x) {
		return Math.min(Math.abs(getEtaMin()-x), Math.abs(x-getEtaMax()));
	}
	
	public Sesso getSesso()
	{
		return sesso;
	}
	
	public Optional<SegnoZodiacale> getSegnoZodiacale() {
		return zodiaco;
	}

	public Optional<Colore> getColoreCapelli() {
		return capelli;
	}

	public Optional<Colore> getColoreOcchi() {
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

	public Optional<Float> getAltezza() {
		return altezza;
	}

	public OptionalInt getPeso() {
		return peso;
	}	
}
