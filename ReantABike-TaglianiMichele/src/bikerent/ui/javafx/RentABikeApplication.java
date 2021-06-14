package bikerent.ui.javafx;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import bikerent.model.Rate;
import bikerent.persistence.BadFileFormatException;
import bikerent.persistence.MyRateReader;
import bikerent.persistence.RateReader;
import bikerent.ui.controller.Controller;
import bikerent.ui.controller.MyController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RentABikeApplication extends Application {

	private Controller controller;

	public void start(Stage stage) {
		controller = createController();
		if (controller == null) {
			Controller.alert("Errore irrecuperabile", "Errore nella creazione del controller", "Addio");
			System.exit(1);
		}
		stage.setTitle("BikeRent - Move yourself!");
		BikeRentPane root = new BikeRentPane(controller);
		
		Scene scene = new Scene(root, 570, 200, Color.ALICEBLUE);
		stage.setScene(scene);
		stage.show();
	}

	protected Controller createController() {
		RateReader tariffeReader = null;
		Map<String,Rate> mappaTariffe = null;
		try {
			tariffeReader = new MyRateReader();
			mappaTariffe = tariffeReader.readRates(new FileReader("CityRates.txt"));
		} catch (FileNotFoundException e) {
			Controller.alert("Errore di I/O", "Errore nell'apertura del file Rates", e.getMessage());
			return null;
		}
		catch (IOException e) {
			Controller.alert("Errore di I/O", "Errore durante la lettura del file Rates", e.getMessage());
			return null;
		}
		catch (BadFileFormatException e) {
			Controller.alert("Errore di formato", "Formato errato nelle Rate", e.getMessage());
			return null;
		}
		return new MyController(mappaTariffe);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
