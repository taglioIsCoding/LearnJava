package flightTracker.ui.controller;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import flightTracker.model.Flight;
import flightTracker.model.Point;
import flightTracker.persistence.BadFileFormatException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class Controller {

	private String[] availableFlightFiles;
	
	public Controller(String[] availableFlightFiles) {
	 	if (availableFlightFiles==null || availableFlightFiles.length==0) throw new IllegalArgumentException("null or empty array of available flights in Controller constructor");
		this.availableFlightFiles=availableFlightFiles;
	}



	public ObservableList<String> getFlights() {
		return FXCollections.observableArrayList(availableFlightFiles);
	}

	public abstract List<Point> getPoints(Flight flight) ;
		
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}

	public abstract Flight load(String flightId, Reader reader) throws IOException, BadFileFormatException;
	
}
