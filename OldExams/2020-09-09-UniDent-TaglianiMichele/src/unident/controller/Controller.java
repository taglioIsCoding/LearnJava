package unident.controller;

import java.util.Set;

import unident.model.AttivitaFormativa;
import unident.model.Esame;

public interface Controller {
	Set<AttivitaFormativa> pianoDidattico();
	void aggiornaCarriera(Esame esame);
	String stampaCarriera();
}
