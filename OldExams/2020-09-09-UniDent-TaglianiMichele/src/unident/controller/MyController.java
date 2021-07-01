package unident.controller;

import java.util.Set;

import unident.model.AttivitaFormativa;
import unident.model.Carriera;
import unident.model.Esame;

public class MyController implements Controller {

	private Set<AttivitaFormativa> pianoDidattico;
	private Carriera carriera;

	public MyController(Set<AttivitaFormativa> pianoDidattico) {
		this.pianoDidattico=pianoDidattico;
		this.carriera=new Carriera(pianoDidattico);
	}

	@Override
	public Set<AttivitaFormativa> pianoDidattico() {
		return this.pianoDidattico;
	}
	
	@Override
	public void aggiornaCarriera(Esame esame) {
		this.carriera.registra(esame);
	}

	@Override
	public String stampaCarriera() {
		return carriera.toString();
	}

}
