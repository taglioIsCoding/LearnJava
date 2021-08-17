package cupidonline.ui.javafx;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import cupidonline.model.Persona;
import cupidonline.model.Preferenza;
import cupidonline.persistence.BadFileFormatException;
import cupidonline.persistence.MyIscrittiReader;
import cupidonline.persistence.MyPreferenzeReader;
import cupidonline.persistence.PreferenzeReader;
import cupidonline.persistence.IscrittiReader;
import cupidonline.ui.controller.Controller;
import cupidonline.ui.controller.MyController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CupidoApplication extends Application {

	private Controller controller;

	public void start(Stage stage) {
		controller = createController();
		if (controller == null) {
			Controller.alert("Errore irrecuperabile", "Errore nella creazione del controller", "Addio");
			System.exit(1);
		}
		stage.setTitle("Cupido On Line");
		CupidoPane root = new CupidoPane(controller);
		
		Scene scene = new Scene(root, 750, 660, Color.ALICEBLUE);
		stage.setScene(scene);
		stage.show();
	}

	protected Controller createController() {
		IscrittiReader iscrittiReader = null;
		PreferenzeReader prefReader = null;
		Map<String,Persona> iscritti = null;
		Map<String,Preferenza> preferenze = null;
		try {
			iscrittiReader = new MyIscrittiReader();
			iscritti = iscrittiReader.caricaIscritti(new FileReader("Iscritti.txt"));
		} catch (FileNotFoundException e) {
			Controller.alert("Errore di I/O", "Errore nell'apertura del file iscritti", e.getMessage());
			return null;
		}
		catch (IOException e) {
			Controller.alert("Errore di I/O", "Errore durante la lettura del file iscritti", e.getMessage());
			return null;
		}
		catch (BadFileFormatException e) {
			Controller.alert("Errore di formato", "Formato errato negli iscritti", e.getMessage());
			return null;
		}
		try {
			prefReader = new MyPreferenzeReader();
			preferenze = prefReader.caricaPreferenze(new FileReader("Preferenze.txt"));
			Controller c = new MyController(iscritti, preferenze);
			return c;
		} catch (FileNotFoundException e) {
			Controller.alert("Errore di I/O", "Errore nell'apertura del file preferenze", e.getMessage());
			return null;
		}
		catch (IOException e) {
			Controller.alert("Errore di I/O", "Errore durante la lettura del file preferenze", e.getMessage());
			return null;
		}
		catch (BadFileFormatException e) {
			Controller.alert("Errore di formato", "Formato errato nel file preferenze", e.getMessage());
			return null;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
