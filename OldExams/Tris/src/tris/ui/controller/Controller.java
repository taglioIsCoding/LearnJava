package tris.ui.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public interface Controller {
	
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}

	public void print();
	public String getCurrentPlayer();
	public String getNextPlayer();
	public boolean storeStatus(String status);
	boolean winningStatus();

}
