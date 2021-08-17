package mastermind.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class Gioco {

	protected Combinazione segreta;
	private List<Combinazione> tentativi;
	private List<Risposta> risposte;
	private int numTentativi, maxTentativi, dim;
	private Status status;
	
	public Gioco(int maxTentativi, int dim) {
		this.maxTentativi=maxTentativi;
		tentativi = new ArrayList<>();
		risposte = new ArrayList<>();
		numTentativi = 0;
		this.dim=dim;
		this.segreta=new Combinazione(dim);
		sorteggiaCombinazione(segreta);
		status = Status.IN_CORSO;
	}
	
	protected abstract void sorteggiaCombinazione(Combinazione segreta);
	protected abstract Risposta calcolaRisposta(Combinazione tentativo);

	public int tentativiEffettuati(){
		return numTentativi;
	}

	public int maxTentativi(){
		return maxTentativi;
	}

	public int tentativiRimasti(){
		return maxTentativi-numTentativi;
	}
	
	public int dimensione() {
		return dim;
	}
	
	public Combinazione tentativo(int index) {
		if (index>=numTentativi) throw new IllegalArgumentException("Tentativo " + index + " inesistente: l'ultimo è il " + numTentativi);
		return tentativi.get(index);
	}
	
	public Risposta risposta(int index) {
		if (index>=numTentativi) throw new IllegalArgumentException("Risposta " + index + " inesistente: l'ultima è la " + numTentativi);
		return risposte.get(index);
	}
	
	public Optional<Combinazione> ultimoTentativo() {
		if (numTentativi==0) return Optional.empty();
		return Optional.of(tentativo(numTentativi-1));
	}

	public Optional<Risposta> ultimaRisposta() {
		if (numTentativi==0) return Optional.empty();
		return Optional.of(risposta(numTentativi-1));
	}
	
	public Status tenta(Combinazione tentativo) {
		tentativi.add(tentativo);
		Risposta r = calcolaRisposta(tentativo);
		risposte.add(r);
		numTentativi++;
		if (r.vittoria()) status=Status.VITTORIA;
		else if (numTentativi<maxTentativi) status=Status.IN_CORSO;
		else status=Status.PERSO;
		return status;
	}

	public String situazione() {
		return IntStream
			.range(0, numTentativi)
			.mapToObj(i -> (i+1) + ") " + tentativi.get(i).toString() + "\t\t" + risposte.get(i).toString()).collect(Collectors.joining(System.lineSeparator()));
	}

	public String combinazioneSegreta() {
		return segreta.toString();
	}
	
	public Status stato() {
		return status;
	}
	
	@Override
	public String toString() {
		return 	"Situazione:" + System.lineSeparator() + situazione() + System.lineSeparator() +
				"Gioco: " + this.stato() + System.lineSeparator();
	}

}

