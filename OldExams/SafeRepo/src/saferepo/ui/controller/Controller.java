package saferepo.ui.controller;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import saferepo.model.VersionedDocument;
import saferepo.persistence.SadPersisterException;

public interface Controller {
	
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}

	public static boolean askConfirm(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		return alert.showAndWait().get() == ButtonType.OK;
	}
	
	public static boolean info(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		return alert.showAndWait().get() == ButtonType.OK;
	}
	
	public ObservableList<VersionedDocument> getVersions(String documentID);
	public ObservableList<String> getDocumentIDs();
	
	public VersionedDocument retrieve(VersionedDocument vdoc, Path path) throws SadPersisterException;
	public VersionedDocument store(String documentID, Path path) throws SadPersisterException;
	public boolean delete(String documentID) throws SadPersisterException;
	
}
