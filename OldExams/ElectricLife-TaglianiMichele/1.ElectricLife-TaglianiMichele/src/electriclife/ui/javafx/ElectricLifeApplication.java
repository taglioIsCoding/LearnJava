package electriclife.ui.javafx;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import electriclife.persistence.BadFileFormatException;
import electriclife.persistence.MyTariffeReader;
import electriclife.persistence.TariffeReader;
import electriclife.ui.controller.Controller;
import electriclife.ui.controller.MyController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ElectricLifeApplication extends Application {

	private Controller controller;

	public void start(Stage stage) {
		controller = createController();
		if (controller == null)
			return;
		stage.setTitle("Electric Life");
		ElectricLifePane root = new ElectricLifePane(controller);
		
		Scene scene = new Scene(root, 750, 500, Color.ALICEBLUE);
		stage.setScene(scene);
		stage.show();
	}

	protected Controller createController() {
		try {
			TariffeReader tariffeReader = new MyTariffeReader(new FileReader("Tariffe.txt"));
			Controller c = new MyController();
			c.leggiTariffe(tariffeReader);
			return c;
		} catch (FileNotFoundException e) {
			Controller.alert("Errore di I/O", "Impossibile aprire uno dei due file", "Errore nella creazione del reader o del writer");
			return null;
		}
		catch (IOException e) {
			Controller.alert("Errore di I/O", "Impossibile leggere il file delle tariffe", "Errore durante la lettura");
			return null;
		}
		catch (BadFileFormatException e) {
			Controller.alert("Errore nel formato del file", "File tariffe errato", "File tariffe non caricato");
			return null;
		}
	}

	public static void main(String[] args) {
		System.setProperty("java.locale.providers", "JRE");
		launch(args);
	}

}
