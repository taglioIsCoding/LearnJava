package minirail.ui.controller;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import minirail.model.Line;
import minirail.model.LineStatus;
import minirail.model.Train;

public interface Controller {
	
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}

	Line getLine();
	List<Train> getTrains();
	LineStatus getLineStatus();
	void clock(double period); 
	void setMoving(Train t);
	void setStopped(Train t);
	String getLog();
}
