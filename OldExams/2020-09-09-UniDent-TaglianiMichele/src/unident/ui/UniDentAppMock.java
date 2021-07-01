package unident.ui;

import java.util.Set;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import unident.controller.Controller;
import unident.controller.MyController;
import unident.model.AttivitaFormativa;
import unident.model.Ssd;
import unident.model.Tipologia;

public class UniDentAppMock extends Application {

	public static void alert(String title, String headerMessage, String contentMessage) {
		javafx.scene.control.Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("MOCK - UniDent University");
	
		Set<AttivitaFormativa> pianoDidattico = Set.of(
				new AttivitaFormativa("Fondamenti di Informatica T-2", Tipologia.A, Ssd.INGINF05, 12),
				new AttivitaFormativa("Fondamenti di Informatica T-1", Tipologia.A, Ssd.INGINF05, 12),
				new AttivitaFormativa("Laboratorio di Amministrazione di Sistemi T", Tipologia.F, Ssd.INGINF05, 6),
				new AttivitaFormativa("Lingua Inglese B-2", Tipologia.E, Ssd.SENZASETTORE, 6)
				); 

		Controller controller = new MyController(pianoDidattico);
		
		MainPane mainPanel = new MainPane(controller);

		Scene scene = new Scene(mainPanel, 900, 400, Color.AQUAMARINE);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	

}
