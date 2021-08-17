package mastermind.ui;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import mastermind.model.Combinazione;
import mastermind.model.Gioco;
import mastermind.model.MasterMind;
import mastermind.model.Status;
import mastermind.persistence.GameSaver;
import mastermind.persistence.MyGameSaver;

public class MyController implements Controller {
	private GameSaver printer;
	private Gioco gioco;
	private int dim, maxTentativi;
	private String filename;
	private PrintWriter pw; 
	
	public MyController(int maxTentativi, int dim, String filename) {
		this.dim=dim;
		this.maxTentativi=maxTentativi;
		this.filename=filename;
		init(maxTentativi, dim, filename);
	}
	
	private void init(int maxTentativi, int dim, String filename) {
		this.gioco=new MasterMind(maxTentativi,dim);
		this.pw = null; 
		try {
			pw = new PrintWriter(filename);			
			this.printer = new MyGameSaver(pw);
		} catch (FileNotFoundException e) {
			Controller.alert("Errore", "Stampa su file fallita", e.getMessage());
		}
	}
		
	@Override
	public void save() {
		printer.save(gioco);
	}
	
	@Override
	public void close() {
		printer.close();
	}

	@Override
	public int maxTentativi() {
		return maxTentativi;
	}
	
	@Override
	public void restart() {
		init(maxTentativi, dim, filename);		
	}

	@Override
	public int dimensioneCodice() {
		return dim;
	}

	@Override
	public Status status() {
		return gioco.stato();
	}

	@Override
	public int tentativiEffettuati() {
		return gioco.tentativiEffettuati();
	}

	@Override
	public int tentativiRimasti() {
		return gioco.tentativiRimasti();
	}

	@Override
	public Status tenta(Combinazione tentativo) {
		return gioco.tenta(tentativo);
	}

	@Override
	public String situazione() {
		return gioco.situazione();
	}

	@Override
	public String combinazioneSegreta() {
		return gioco.combinazioneSegreta();
	}	
}
