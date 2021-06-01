package favoliere.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class MySintetizzatore implements Sintetizzatore{
	private List<Azione> azione = new ArrayList<>();
	private List<Conclusione> conclusione = new ArrayList<>();
	private List<Personaggio> personaggi = new ArrayList<>();
	private List<Scenario> scenari = new ArrayList<>();
	
	public MySintetizzatore( List<Personaggio> personaggi,
			List<Scenario> scenari, List<Azione> azione, List<Conclusione> conclusione) {
		this.azione = azione;
		this.conclusione = conclusione;
		this.personaggi = personaggi;
		this.scenari = scenari;
	}

	public List<Azione> getAzioni() {
		return azione;
	}

	public List<Conclusione> getConclusioni() {
		return conclusione;
	}

	public List<Personaggio> getPersonaggi() {
		return personaggi;
	}

	public List<Scenario> getScenari() {
		return scenari;
	}
	
	public Favola generaFavola(FasciaEta eta, Impressionabilita livello) throws NoSuchTaleException {
		
		Optional<Set<Personaggio>> personaggiBuoni = sorteggia(this.personaggi, 2, Tipologia.POSITIVO);
		Optional<Set<Personaggio>> Cattivi = sorteggia(this.personaggi, 1, Tipologia.NEGATIVO);
		Optional<Azione> azione = sorteggia(this.azione, livello.getGradoDurezza());
		Optional<Scenario> scena = sorteggia(this.scenari, eta.getGradoComplessita());
		Conclusione conc = sorteggia(this.conclusione);
		
		
		if(personaggiBuoni.isEmpty()) {
			throw new NoSuchTaleException("Non personaggi Buoni");
		}else if( Cattivi.isEmpty() ) {
			throw new NoSuchTaleException("No cattivi");
		}else if( azione.isEmpty()) {
			throw new NoSuchTaleException("Non esistono azioni con il grado di durezza richiesto");
		}else if( scena.isEmpty()) {
			throw new NoSuchTaleException("No scena");
		}else if(conc == null) {
			throw new NoSuchTaleException("No conclusione");
		}
		
		return new Favola(new Esordio(personaggiBuoni.get(), Cattivi.get()), scena.get(), azione.get(), conc);
	}
	
	
	private Optional<Set<Personaggio>> sorteggia(List<Personaggio> lista, int n, Tipologia tipo) throws NoSuchTaleException{
		Set<Personaggio> setPersonaggi = new TreeSet<>();
		List<Personaggio> filtrati = new ArrayList<>();
		int i = 0;
		int dim = 0;
		
		for(Personaggio element : lista) {
			if(element.getTipologia().equals(tipo)) {
				filtrati.add(element);
			}
		}
		
		if(filtrati.size() < n && tipo.compareTo(Tipologia.POSITIVO) == 0) {
			throw new NoSuchTaleException("Non ci sono sufficienti personaggi del tipo richiesto");
		}else if(filtrati.size() < n && tipo.compareTo(Tipologia.NEGATIVO) == 0) {
			throw new NoSuchTaleException("Non ci sono sufficienti personaggi del tipo richiesto");
		}
		
		while(setPersonaggi.size() != n) {
			setPersonaggi.add(filtrati.get(new Random().nextInt(filtrati.size())));
		}
		

		return Optional.of(setPersonaggi);
		
		
	}
	
	<T> T sorteggia(List<T> lista) {
		Random r = new Random();
		return lista.get(r.nextInt(lista.size()));
	}
	
	<T extends ConIndice> Optional<T> sorteggia(List<T> lista, int upperBound){
		List<T> filtrati = new ArrayList<>();
		for(T element : lista) {
			if(element.getIndice() <= upperBound) {
				filtrati.add(element);
			}
		}
		System.out.println(lista.toString());
		System.out.println(filtrati.toString());
		
		if(filtrati.isEmpty()) {
			return Optional.empty();
		}else {
			return Optional.of(filtrati.get(new Random().nextInt(filtrati.size())));
		}
	}
}
