package bikerent.ui.javafx;

import java.io.IOException;
import java.util.Map;

import bikerent.model.Rate;
import bikerent.persistence.BadFileFormatException;
import bikerent.persistence.RateReader;
import bikerent.tests.persistence.TariffeReaderMock;
import bikerent.ui.controller.Controller;
import bikerent.ui.controller.MyController;

public class RentABikeApplicationMock extends RentABikeApplication {

	@Override
	protected Controller createController() {
		RateReader tariffeReader = new TariffeReaderMock();
		try {
			Map<String, Rate> mappaTariffe = tariffeReader.readRates(null);
			return new MyController(mappaTariffe);
		}
		catch (IOException | BadFileFormatException e) {
			Controller.alert("Errore", "Impossibile aprire file iscritti o preferenze", "Errore o nell'apertura del file, o nella lettura, o nel formato");
			return null;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
