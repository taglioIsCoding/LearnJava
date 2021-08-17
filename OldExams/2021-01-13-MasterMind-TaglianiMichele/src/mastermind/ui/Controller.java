package mastermind.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import mastermind.model.Combinazione;
import mastermind.model.Status;

public interface Controller {
	
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}

	void save();
	void close();

	public int maxTentativi();
	public int dimensioneCodice();
	public Status status();
	public int tentativiEffettuati();
	public int tentativiRimasti();
	public Status tenta(Combinazione tentativo);
	public String situazione();
	public String combinazioneSegreta();

	void restart();

}
