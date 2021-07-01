package unident.ui;

import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import unident.controller.Controller;
import unident.controller.MyController;
import unident.model.AttivitaFormativa;
import unident.persistence.PianoDidatticoReader;
import unident.persistence.UniDentPianoDidatticoReader;


public class UniDentApp extends Application {

	public static void alert(String title, String headerMessage, String contentMessage) {
		javafx.scene.control.Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("UniDent University");
		try {
				PianoDidatticoReader pianoReader = new UniDentPianoDidatticoReader();
				Set<AttivitaFormativa> pianoDidattico = pianoReader.readAll(new FileReader("PianoDidattico.txt"));
				Controller controller = new MyController(pianoDidattico);
				
				MainPane mainPanel = new MainPane(controller);
				Scene scene = new Scene(mainPanel, 900, 500, Color.AQUAMARINE);
				stage.setScene(scene);
				stage.show();
				
		} catch (IOException e) {
			alert(
					"ERRORE DI I/O",
					"Errore di lettura: impossibile leggere i dati",
					"Dettagli: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			alert(
					"ERRORE DI I/O",
					"Formato dei file errato: impossibile leggere i dati",
					"Dettagli: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
