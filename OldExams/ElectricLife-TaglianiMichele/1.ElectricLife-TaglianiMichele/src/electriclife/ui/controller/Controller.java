package electriclife.ui.controller;

import java.io.IOException;
import java.time.LocalDate;

import electriclife.model.Bolletta;
import electriclife.model.Tariffa;
import electriclife.persistence.BadFileFormatException;
import electriclife.persistence.BollettaWriter;
import electriclife.persistence.TariffeReader;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public interface Controller
{
	void leggiTariffe(TariffeReader tariffaReader) throws IOException, BadFileFormatException;
	ObservableList<Tariffa> getTariffe();
	Bolletta creaBolletta(LocalDate date, String nomeTariffa, int consumo);
	void stampaBolletta(Bolletta b, BollettaWriter bollettaWriter); 

	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
}
