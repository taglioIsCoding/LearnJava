package cupidonline.ui.controller;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;
import java.util.stream.Collectors;

import cupidonline.model.Corrispondenza;
import cupidonline.model.Cupido;
import cupidonline.model.MyCupido;
import cupidonline.model.Persona;
import cupidonline.model.Preferenza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MyController implements Controller {
	private Map<String,Persona> mappaIscritti;
	private Cupido cupido;
	private Map<String, Preferenza> mappaPreferenze;

	public MyController(Map<String, Persona> mappaIscritti, Map<String, Preferenza> mappaPreferenze) {
		this.mappaIscritti=mappaIscritti;
		this.mappaPreferenze=mappaPreferenze;
		this.cupido=new MyCupido(mappaIscritti,mappaPreferenze);
	}

	@Override
	public ObservableList<String> getNomiIscritti() {
		Collection<Persona> listaIscritti = mappaIscritti.values();
		Collection<String> listaNomiIscritti = listaIscritti.stream().map(Persona::getId).collect(Collectors.toList());
		ObservableList<String> listaOsservabileIscritti = FXCollections.observableArrayList(listaNomiIscritti);
		FXCollections.sort(listaOsservabileIscritti);
		return listaOsservabileIscritti;
	}
	
	@Override
	public Optional<Preferenza> getPreferenza(String pName) {
		return Optional.ofNullable(mappaPreferenze.get(pName));
	}
	
	@Override
	public SortedSet<Corrispondenza> trovaCorrispondenze(String pName) {
		return cupido.trovaCorrispondenze(pName);
	}

	@Override
	public SortedSet<Corrispondenza> trovaCorrispondenze(String pName, Preferenza pPref) {
		return cupido.trovaCorrispondenze(pName, pPref);
	}

	@Override
	public Persona getPersona(String pName) {
		return mappaIscritti.get(pName);
	}

}
