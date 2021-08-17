package cupidonline.ui.controller;

import java.util.Optional;
import java.util.SortedSet;
import cupidonline.model.Corrispondenza;
import cupidonline.model.Persona;
import cupidonline.model.Preferenza;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public interface Controller
{
	public ObservableList<String> getNomiIscritti();
	public Optional<Preferenza> getPreferenza(String pName);
	public Persona getPersona(String pName); 
	
	public SortedSet<Corrispondenza> trovaCorrispondenze(String pName);
	public SortedSet<Corrispondenza> trovaCorrispondenze(String pName, Preferenza pPref);
	
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
}
