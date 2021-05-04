package zannotaxi.model;

import java.util.Optional;

public class TariffaADistanza implements ITariffaTaxi {
	
	private double costoMassimo;
	private double costoMinimo;
	private double distanzaDiScatto;
	private double vasloreScatto;
	private double velocitaMinima;
	private double velocitaMassima;
	private String nome;
	
	

	public TariffaADistanza( String nome, double velocitaMinima, double velocitaMassima,
			 double costoMinimo,double costoMassimo, double vasloreScatto, double distanzaDiScatto) {
		super();
		this.costoMassimo = costoMassimo;
		this.costoMinimo = costoMinimo;
		this.distanzaDiScatto = distanzaDiScatto;
		this.vasloreScatto = vasloreScatto;
		this.velocitaMinima = velocitaMinima;
		this.velocitaMassima = velocitaMassima;
		this.nome = nome;
	}

	public double getCostoMassimo() {
		return costoMassimo;
	}

	public double getCostoMinimo() {
		return costoMinimo;
	}

	public double getDistanzaDiScatto() {
		return distanzaDiScatto;
	}

	public double getVelocitaMinima() {
		return velocitaMinima;
	}

	public double getVelocitaMassima() {
		return velocitaMassima;
	}

	@Override
	public String getNome() {
		return this.nome;
	
	}
	

	@Override
	public double getValoreScatto() {
		return vasloreScatto;
	}

	@Override
	public Optional<Scatto> getScattoCorrente(int tempoTrascorsoDaUltimoScatto, double spazioPErcorsoUltimoScatto,
			double costoCorrente) {
		boolean effettuaScatto = false;
		double velocitaMedia = (spazioPErcorsoUltimoScatto/tempoTrascorsoDaUltimoScatto)*3.6f;
		
		if (velocitaMedia >= velocitaMinima && velocitaMedia < velocitaMassima && costoCorrente>= costoMinimo && costoCorrente < costoMassimo
				&& Math.round(spazioPErcorsoUltimoScatto)>= distanzaDiScatto) {
			effettuaScatto = true;
		}
		return effettuaScatto
				? Optional.of(new Scatto(tempoTrascorsoDaUltimoScatto,distanzaDiScatto, getValoreScatto()))
				: Optional.empty();
	}
	
}
