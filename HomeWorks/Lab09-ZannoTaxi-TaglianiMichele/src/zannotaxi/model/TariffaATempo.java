package zannotaxi.model;

import java.util.Optional;

public class TariffaATempo implements ITariffaTaxi {

	private String nome;
	private int tempoDiScatto;
	private double valoreScatto;
	private double velocitaMassima;
	private double velocitaMinima;
	
	public TariffaATempo( String nome, double velocitaMinima, double velocitaMassima,
			  double vasloreScatto, int tempoDiScatto) {
		super();
		this.tempoDiScatto = tempoDiScatto;
		this.valoreScatto = vasloreScatto;
		this.velocitaMinima = velocitaMinima;
		this.velocitaMassima = velocitaMassima;
		this.nome = nome;
	}
	
	
	@Override
	public String getNome() {
		return this.nome;
	}

	public int getTempoDiScatto() {
		return tempoDiScatto;
	}

	public double getVelocitaMassima() {
		return velocitaMassima;
	}

	public double getVelocitaMinima() {
		return velocitaMinima;
	}

	@Override
	public Optional<Scatto> getScattoCorrente(int tempoTrascorsoDaUltimoScatto, double spazioPErcorsoUltimoScatto,
			double costoCorrente) {
		boolean effettuaScatto = false;
		double velocitaMedia = (spazioPErcorsoUltimoScatto/tempoTrascorsoDaUltimoScatto)*3.6f;
		if (velocitaMedia >= velocitaMinima && velocitaMedia < velocitaMassima && tempoTrascorsoDaUltimoScatto >= tempoDiScatto ) {
			effettuaScatto = true;
		}
		return effettuaScatto
				? Optional.of(new Scatto(tempoTrascorsoDaUltimoScatto,spazioPErcorsoUltimoScatto, getValoreScatto()))
				: Optional.empty();
	}

	@Override
	public double getValoreScatto() {
		return this.valoreScatto;
	}

}
