package bikerent.ui.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public interface Controller
{
	public ObservableList<String> getCityNames();
	
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	public double getRentCost(String citta, LocalDate dataInizio, LocalTime oraInizio, LocalDate dataFine, LocalTime oraFine);
}
